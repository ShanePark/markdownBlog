# PostgreSQL) 모든 테이블에서 특정 값 찾기

## 모든 테이블 찾기

특정 값을 검색 할 때, 모든 테이블에서 찾아봐야 할 경우가 종종 있습니다. 

이번에 특정 데이터의 완전 삭제 기능을 구현하고 있었는데 모든 테이블을 FK로 연결한 건 아니라서 관련된 데이터들이 모두 삭제 되었는지가 확실하지 않았습니다. 

FK 와 cascade 를 활용해서 싹 다 지워버리면 편하기야 하겠지만 의도치 않은 사이드이펙트가 나올 수도 있습니다.  특정 데이터를 삭제 할 경우 관련된 데이터를 무작정 제거하는게 아닌 다른 비즈니스 로직을 수행해야 하는 경우도 있을 수 있고, 실제로 데이터를 지우지 않고 상태만 변경해야 할 경우도 있습니다. 

클라이언트의 요구사항은 다양하기 때문에 한가지 방법만을 고집 할 수는 없습니다.   비관계형 데이터베이스까지 커버해야 할 경우마저 있네요. Guava의 Event Bus를  사용해 비즈니스 로직과 후 처리 로직을 분리하는 아주 좋은 방법을 쓰면 Open-Closed Principle을 따르는 깔끔한 코드를 작성 할 수도 있습니다.

​			

모든 데이터 베이스를 한번에 뒤지는 방법에는 두가지 방법이 있습니다.

1. 데이터베이스 전체 덤핑 한 뒤 greb 을 이용해 찾기
2. function 만들어 찾기

   ​		

검색 할 때마다 데이터베이스 컨텐츠를 덤핑하기엔 그닥 효율적이지 않습니다. 고맙게도 stack overflow에서 우리를 위해 pl/sql function 을 만들어 둔 개발자가 있어 우리는 가져다 쓰기만 하면 됩니다.

​		

## 1. 데이터베이스 전체 덤핑한 후 grep 하기

```bash
$ pg_dump --data-only --inserts -U postgres your-db-name > a.tmp
$ grep United a.tmp
INSERT INTO countries VALUES ('US', 'United States');
INSERT INTO countries VALUES ('GB', 'United Kingdom');

```

같은 기능이지만 --inserts 를 --coloum-inserts 로 변경 하면 컬럼명을 출력 결과에 포함 시킬 수 있습니다. 이 경우에는 특정 컬럼 이름으로 검색 할수도 있습니다만, 컬럼명을 찾을 목적이라면 data 대신 schema를 덤프 하는게 낫겠습니다.

```bash
$ pg_dump --data-only --column-inserts -U postgres your-db-name > a.tmp
$ grep country_code a.tmp
INSERT INTO countries (iso_country_code, iso_country_name) VALUES ('US', 'United  States');
INSERT INTO countries (iso_country_code, iso_country_name) VALUES ('GB', 'United Kingdom');

```

​		

## 2. function 만들어 찾기

개인적으로 이 함수를 정말 자주 호출해서 사용 하고 있습니다. 한번 추가해두면 함수만 호출해 사용하면 되기 때문에 간단합니다.

```sql
CREATE OR REPLACE FUNCTION search_columns(
    needle text,
    haystack_tables name[] default '{}',
    haystack_schema name[] default '{}'
)
RETURNS table(schemaname text, tablename text, columnname text, rowctid text)
AS $$
begin
  FOR schemaname,tablename,columnname IN
      SELECT c.table_schema,c.table_name,c.column_name
      FROM information_schema.columns c
        JOIN information_schema.tables t ON
          (t.table_name=c.table_name AND t.table_schema=c.table_schema)
        JOIN information_schema.table_privileges p ON
          (t.table_name=p.table_name AND t.table_schema=p.table_schema
              AND p.privilege_type='SELECT')
        JOIN information_schema.schemata s ON
          (s.schema_name=t.table_schema)
      WHERE (c.table_name=ANY(haystack_tables) OR haystack_tables='{}')
        AND (c.table_schema=ANY(haystack_schema) OR haystack_schema='{}')
        AND t.table_type='BASE TABLE'
  LOOP
    FOR rowctid IN
      EXECUTE format('SELECT ctid FROM %I.%I WHERE cast(%I as text)=%L',
       schemaname,
       tablename,
       columnname,
       needle
      )
    LOOP
      -- 더 자세한 결과를 받아보기 위해서는 아래 줄의 주석을 제거해주세요.
      -- RAISE NOTICE 'hit in %.%', schemaname, tablename;
      RETURN NEXT;
    END LOOP;
 END LOOP;
END;
$$ language plpgsql;

```

​	

## 사용법

​	

- public schema의 모든 테이블에서 조회하기: 

```sql
select * from search_columns('foobar');
 schemaname | tablename | columnname | rowctid 
------------+-----------+------------+---------
 public     | s3        | usename    | (0,11)
 public     | s2        | relname    | (7,29)
 public     | w         | body       | (0,2)
(3 rows)

```

​	

- 특정 테이블에서 조회하기: 

```sql
 select * from search_columns('foobar','{w}');
 schemaname | tablename | columnname | rowctid 
------------+-----------+------------+---------
 public     | w         | body       | (0,2)
(1 row)

```

​	

- 특정 테이블 집합에서 조회하기: 

```sql
select * from search_columns('foobar', array(select table_name::name from information_schema.tables where table_name like 's%'), array['public']);
 schemaname | tablename | columnname | rowctid 
------------+-----------+------------+---------
 public     | s2        | relname    | (7,29)
 public     | s3        | usename    | (0,11)
(2 rows)

```

​	

- Get a result row with the corresponding base table and and ctid

```sql
select * from public.w where ctid='(0,2)';
 title |  body  |         tsv         
-------+--------+---------------------
 toto  | foobar | 'foobar':2 'toto':1
 
```

​	

​		

## 그 외 다양한 활용

- 정확한 제약조건 대신 grep 처럼 정규식을 활용한 검색을 할 수도 있습니다.

  `SELECT ctid FROM %I.%I WHERE cast(%I as text)=%L`

  > 이렇게 바뀔 수도 있습니다:

  `SELECT ctid FROM %I.%I WHERE cast(%I as text) ~ %L`

- 민감하지 않은 비교를 위해서는 이렇게 쓸 수도 있습니다: 

  `SELECT ctid FROM %I.%I WHERE lower(cast(%I as text)) = lower(%L)`



​	

## 활용 예

​	

```sql
select * from search_columns('23fb9d28-3976-4b87-9545-403c45f8b8c8');
```

​	

특정 UUID 를 검색 한다면, 관련된 모든 데이터를 전체 테이블에서 찾아줍니다.

![peek](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/postgresql/search-value-in-all-table.assets/peek.webp)

모든 테이블을 다 찾기 때문에 데이터베이스의 크기가 클때 검색 시간은 꽤 걸리지만 그래도 덤핑해서 검색하거나 한 테이블씩 모두 찾을 때에 비해서는 훨씬 효율적입니다. 

테이블명, 컬럼명, rowctid가 모두 나오기 때문에 한눈에 쉽게 알아볼 수 있습니다. 이상입니다.

**References**

- https://stackoverflow.com/questions/5350088/how-to-search-a-specific-value-in-all-tables-postgresql

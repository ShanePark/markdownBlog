# PostgreSQL) 모든 테이블에서 특정 값 찾기

특정 값을 모든 테이블에서 찾기

> 참고자료 https://stackoverflow.com/questions/5350088/how-to-search-a-specific-value-in-all-tables-postgresql

​	

두가지 방법이 있습니다.

1. 데이터베이스 전체 덤핑 해서 찾기
2. function 만들어 찾기

​	

2번을 추천합니다.

​	

## 1. 데이터베이스 전체 덤핑한 후 grep 하기

```bash
$ pg_dump --data-only --inserts -U postgres your-db-name > a.tmp
$ grep United a.tmp
INSERT INTO countries VALUES ('US', 'United States');
INSERT INTO countries VALUES ('GB', 'United Kingdom');

```

he same utility, pg_dump, can include column names in the output. Just change `--inserts` to `--column-inserts`. That way you can search for specific column names, too. But if I were  looking for column names, I'd probably dump the schema instead of the  data.

```bash
$ pg_dump --data-only --column-inserts -U postgres your-db-name > a.tmp
$ grep country_code a.tmp
INSERT INTO countries (iso_country_code, iso_country_name) VALUES ('US', 'United  States');
INSERT INTO countries (iso_country_code, iso_country_name) VALUES ('GB', 'United Kingdom');
```

​	

## 2. function 만들어 찾기

```plsql
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
      -- uncomment next line to get some progress report
      -- RAISE NOTICE 'hit in %.%', schemaname, tablename;
      RETURN NEXT;
    END LOOP;
 END LOOP;
END;
$$ language plpgsql;

```

사용법

- Search in all tables within public schema:

```
select * from search_columns('foobar');
 schemaname | tablename | columnname | rowctid 
------------+-----------+------------+---------
 public     | s3        | usename    | (0,11)
 public     | s2        | relname    | (7,29)
 public     | w         | body       | (0,2)
(3 rows)
```

- Search in a specific table:

```
 select * from search_columns('foobar','{w}');
 schemaname | tablename | columnname | rowctid 
------------+-----------+------------+---------
 public     | w         | body       | (0,2)
(1 row)
```

- Search in a subset of tables obtained from a select:

```
select * from search_columns('foobar', array(select table_name::name from information_schema.tables where table_name like 's%'), array['public']);
 schemaname | tablename | columnname | rowctid 
------------+-----------+------------+---------
 public     | s2        | relname    | (7,29)
 public     | s3        | usename    | (0,11)
(2 rows)
```

- Get a result row with the corresponding base table and and ctid:

```
select * from public.w where ctid='(0,2)';
 title |  body  |         tsv         
-------+--------+---------------------
 toto  | foobar | 'foobar':2 'toto':1
```

## Variants

- To test against a regular expression instead of strict equality, like grep, this part of the query:

  `SELECT ctid FROM %I.%I WHERE cast(%I as text)=%L`

  may be changed to:

  `SELECT ctid FROM %I.%I WHERE cast(%I as text) ~ %L`

- For case insensitive comparisons, you could write:

  `SELECT ctid FROM %I.%I WHERE lower(cast(%I as text)) = lower(%L)`




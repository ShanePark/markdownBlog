

# MacOS PostgreSQL 설치 하고 테이블 생성, 조회하기

​		

## PostgreSQL

> PostgreSQL은 확장 가능성 및 표준 준수를 강조하는 객체-관계형 데이터베이스 관리 시스템의 하나 입니다. 오픈소스 RDBMS로서 사용율은 Oracle, MySQL, Microsoft SQL에 이어 4위를 기록하고 있으며 특히 MacOS 서버의 경우에는 PostgreSQL이 기본 데이터베이스 입니다.

​	

​		

설치를 위해 brew에 postgresql 을 검색해 보았습니다.

```bash
brew search postgresql
```

![](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804111252355.png)

```bash
brew install postgresql 
```

을 입력 해서 default 버전인 13.3 버전을 설치하겠습니다.

​	

![image-20210804111016817](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804111016817.png)

설치가 완료되었습니다. To start postgresql : 이라며 친절하게 시작하는 방법을 알려주네요, 그대로 입력합니다.

​	

```
brew services start postgresql
```

![image-20210804111122961](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804111122961.png)

​	

설치가 완료되었으니, 버전을 확인 해 보겠습니다.

```bash
postgres -V
```

![image-20210804111632615](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804111632615.png)



## 이제 DB에 접속 해 보겠습니다.

​	

![image-20210804112153207](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804112153207.png)

> https://www.postgresql.org/docs/current/postgres-user.html

Default 접속 계정을 확인하려고 PostgreSQL 의 Document를 확인하는데, PostgreSQL이 설치시 자동으로 계정을 생성해준다고 써있습니다.

​	

정말인지 접속 해서 확인 해 보겠습니다.

```bash
psql postgres
```

![image-20210804112349018](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804112349018.png)

아래 명령어로 role 리스트를 확인 할 수 있습니다.

```bas
\du
```



![image-20210804112516669](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804112516669.png)

![image-20210804112558176](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804112558176.png)

> 정말로 shane 이라는 Superuser가 생성되어 있습니다. Q 를 입력해 빠져나옵니다.

​	

## 이제 Client로 접속 해 보겠습니다.

> PostgreSQL Client Tool로는 TablePlus, Psequel(MYSQL의 Sequel Pro 입니다), SQLPro, Postico 등이 있다고 하는데요. 저는 DBeaver가 설치되어 있어 있던 걸 사용하겠습니다.



![image-20210804111653995](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804111653995.png)

>  PostgreSQL 을 선택 하고

​	

![image-20210804111718836](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804111718836.png)	

> 필요한 Driver를 다운 받습니다.



![image-20210804112817533](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804112817533.png)



> PostgreSQL 에서 위에서 확인한 shane을 입력해 Test Connection 을 수립해보니 정상적으로 연결이 됩니다.



​		

## study라는 이름의 database를 만들어보겠습니다.

```bash
create database study;
```



![image-20210804114635428](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804114635428.png)

​		

testuser라는 이름의 user를 생성합니다.

```
create user testuser with encrypted password 'testpass';
```



![image-20210804114757062](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804114757062.png)

​	

testuser에게 데이터 베이스 생성 권한도 부여합니다.

```
alter user testuser createdb
```

![image-20210804130407989](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804130407989.png)

> \du 로 확인해보니 권한이 잘 설정 되었습니다.

​	

### 생성한 유저에게 아까만든 study 데이터베이스에 대한 모든 권한을 주겠습니다.

```bas
grant all privileges on database study to testuser;
```

​	![image-20210804115213814](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804115213814.png)



### 연습용 Database기 때문에 모든 권한을 부여하지만 권한 설정을 자세하게 하려면 아래를 참고해주세요

![image-20210804114911171](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804114911171.png)

> https://www.postgresql.org/docs/13/sql-grant.html 에서 확인 할 수 있습니다.



### 데이터 베이스 리스트 보려면 아래와 같이 입력합니다.

```ba
\list
```

![image-20210804115845086](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804115845086.png)



### 아래 명령으로 테이블 리스트를 볼 수 있습니다.

```bash
\dt
```

> 아직 아무 테이블도 존재하지 않아서 아래 사진처럼 Did not find any relations. 라고 합니다.



### \connect 데이터베이스 이름을 입력하면 해당 데이터베이스로 연결합니다.

```bas
\connect study
```

![image-20210804120013637](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804120013637.png)

​		

추가한 사용자로 접속해보겠습니다. -U 명령어와 username 을 입력 합니다.

```ba
psql postgres -U testuser
```



![image-20210804124947663](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804124947663.png)

> postgres-# 에서 postgres=> 으로 바뀌었습니다. #은 super user를 뜻 합니다.



새로생성한 study Database에 새로 생성한 testuser로 DBeaver 에서 접속 해 보겠습니다.



![image-20210804123255425](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804123255425.png)



![image-20210804123526458](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804123526458.png)



테이블이 하나도 없으니 emp 테이블을 만들겠습니다.

```sql
CREATE TABLE dept (
    deptno    NUMERIC CONSTRAINT PK_DEPT PRIMARY KEY,
    dname    VARCHAR(14) ,
    loc        VARCHAR(13) 
) ;
CREATE TABLE emp (
    empno    NUMERIC CONSTRAINT PK_EMP PRIMARY KEY,
    ename    VARCHAR(10),
    job        VARCHAR(9),
    mgr        NUMERIC,
    hiredate    DATE,
    sal        NUMERIC(7,2),
    comm    NUMERIC(7,2),
    deptno    NUMERIC(2) CONSTRAINT FK_DEPTNO REFERENCES DEPT 
);
```



데이터도 넣어줍니다.

```sql
-- Insert test data - dept
INSERT INTO dept VALUES    (10,'ACCOUNTING','NEW YORK');
INSERT INTO dept VALUES (20,'RESEARCH','DALLAS');
INSERT INTO dept VALUES    (30,'SALES','CHICAGO');
INSERT INTO dept VALUES    (40,'OPERATIONS','BOSTON');
-- Insert test data - emp
INSERT INTO emp VALUES (7369,'SMITH','CLERK',7902,to_date('17-12-1980','dd-mm-yyyy'),800,NULL,20);
INSERT INTO emp VALUES (7499,'ALLEN','SALESMAN',7698,to_date('20-2-1981','dd-mm-yyyy'),1600,300,30);
INSERT INTO emp VALUES (7521,'WARD','SALESMAN',7698,to_date('22-2-1981','dd-mm-yyyy'),1250,500,30);
INSERT INTO emp VALUES (7566,'JONES','MANAGER',7839,to_date('2-4-1981','dd-mm-yyyy'),2975,NULL,20);
INSERT INTO emp VALUES (7654,'MARTIN','SALESMAN',7698,to_date('28-9-1981','dd-mm-yyyy'),1250,1400,30);
INSERT INTO emp VALUES (7698,'BLAKE','MANAGER',7839,to_date('1-5-1981','dd-mm-yyyy'),2850,NULL,30);
INSERT INTO emp VALUES (7782,'CLARK','MANAGER',7839,to_date('9-6-1981','dd-mm-yyyy'),2450,NULL,10);
INSERT INTO emp VALUES (7788,'SCOTT','ANALYST',7566,to_date('13-07-87','dd-mm-yyyy')-85,3000,NULL,20);
INSERT INTO emp VALUES (7839,'KING','PRESIDENT',NULL,to_date('17-11-1981','dd-mm-yyyy'),5000,NULL,10);
INSERT INTO emp VALUES (7844,'TURNER','SALESMAN',7698,to_date('8-9-1981','dd-mm-yyyy'),1500,0,30);
INSERT INTO emp VALUES (7876,'ADAMS','CLERK',7788,to_date('13-07-87','dd-mm-yyyy')-51,1100,NULL,20);
INSERT INTO emp VALUES (7900,'JAMES','CLERK',7698,to_date('3-12-1981','dd-mm-yyyy'),950,NULL,30);
INSERT INTO emp VALUES (7902,'FORD','ANALYST',7566,to_date('3-12-1981','dd-mm-yyyy'),3000,NULL,20);
INSERT INTO emp VALUES (7934,'MILLER','CLERK',7782,to_date('23-1-1982','dd-mm-yyyy'),1300,NULL,10);
```



이제 emp 테이블을 조회 하면

```sql 
select * 
from emp;
```



![image-20210804135906609](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804135906609.png)



문제 없이 조회 결과를 받아 올 수 있습니다.



```sql
select empno, ename, dname
from emp
	inner join dept on (emp.deptno = dept.deptno);
```

![image-20210804141139949](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804141139949.png)

> join 도 Oracle이나 MySQL 에서 썼던것처럼 ANSI SQL을 그대로 사용하면 됩니다.



```
select dept.deptno, dname, count(empno)
from dept
	left outer join emp on (emp.deptno = dept.deptno)
group by dept.deptno, dname
order by deptno;
```

![image-20210804141723203](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804141723203.png)

group by 집계 함수도 사용 해 보았습니다. Oracle 데이터베이스를 처음 공부할 때 배우는 내용인데요, deptno=40에 해당하는 emp가 없기 때문에 count(*) 을 넣으면 의도한 값이 나오지 않습니다.  count(empno)라고 입력합니다.



```sql
select deptno, ename, sal
from(
	select deptno, ename, sal, rank() over(partition by deptno order by sal, empno) as rank
	from emp) e
where rank = 1;
```

이번에는 각 부서별 최저 급여를 받는 사원 정보를 조회해 보았습니다.

![](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804143931366.png)



여기에서 특이한 점을 찾았는데요, Oracle에서는 아래와 같이 썼어도 괜찮았는데

```sql
select deptno, ename, sal
from(
	select deptno, ename, sal, rank() over(partition by deptno order by sal, empno) as rank
	from emp)
where rank = 1;
```



PostgreSQL 에서는 FROM절에 있는 Subquery  (Inline View) 는 반드시 alias를 가져야 한다고 하며 실행이 되지 않습니다.

![image-20210804143347457](https://github.com/Shane-Park/markdownBlog/raw/master/database/postgresql/macpostgresql.assets/image-20210804143347457.png)





```sql
select deptno, ename, sal
from emp e 
where e.sal = (select min(sal) 
				from emp 
				where deptno=e.deptno)
order by deptno;
```

다른 방식의 서브쿼리로도 해결 할 수 있지만, 이 방법은 최저 급여를 받는 사원이 여러명일때 그 여러명이 모두 나오기 때문에 조회 결과가 의도와 다를 수 있습니다.



이상으로 MacOS 에서 PostgreSQL 설치하고 접속하는 방법에 대한 포스팅을 마치겠습니다.
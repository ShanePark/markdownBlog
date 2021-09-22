# 데이터베이스 스키마 자동 생성

>  참고 강의 : 우아한 형제들 김영한 팀장님 https://www.inflearn.com/course/ORM-JPA-Basic/dashboard	

​			

## hibernate.hbm2ddl.auto

hibernate 에서는 해당 프로퍼티를 설정 함으로서 SessionFactory 가 생성 될 때 스키마 DDL(Data Definition Language)을 검증하고 내보낼 수 있습니다. hibernate.dialect 속성을 통해 데이터베이스에 적절한 DDL을 생성 하는데 이렇게 생성된 DDL은 개발 단계에서만 사용하는게 좋습니다.

​					

**hibernate.hbm2ddl.auto** 속성의 value 에 들어 갈 수 있는 값은 아래와 같습니다.

| 옵션        | 하는일                                                   |
| ----------- | -------------------------------------------------------- |
| create      | 기존 테이블을 삭제 한 뒤에 새로 생성                     |
| create-drop | create가 하는일 + 어플리케이션 종료시에 해당 테이블 DROP |
| update      | 변경 된 내용만 반영                                      |
| validate    | 엔티티와 테이블이 정상 매핑되어 있는지만 확인            |
| none        | 사용하지 않음                                            |

​		

## create

persistence.xml 설정파일에 아래와 같은 property를 넣어 줍니다.

```bash
<property name="hibernate.hbm2ddl.auto" value="create"/>
```

​		

그러고 나서 이제 실행을 해 보면..

![image-20210922115820020](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922115820020.png)

위에서 보이는 것 처럼 drop table, create table을 먼저 수행합니다.

​	

당연하겠지만, 새로 만든 테이블이기에 기존 자료들이 모두 삭제되고 테이블 스키마만 남게 됩니다.

![image-20210922115940184](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922115940184.png)

​	

거기에 Entity 에 phone 이라는 이름의 String 타입 데이터를 추가한다면		

![image-20210922120055559](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922120055559.png)

​	

이번에는 create 할 때 phone 까지 만들어 줍니다.

​	

![image-20210922120236461](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922120236461.png)

![image-20210922120256839](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922120256839.png)

원래 같았으면 데이터베이스 스키마가 변화 할 때 마다 다시 DROP 해주고, CREATE 테이블 날리고, 가끔은 ALTER도 해 주고 번거로운 작업이 있었는데 한단한 설정 만으로 편하게 새로 만들 수 있습니다.

​		

## create-drop

이번에는 create-drop 을 해 보겠습니다.

```xml
<?xml version="1.0" encoding="utf-8" ?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.2">
    <persistence-unit name="hello">
        <properties>
            <!-- 필수 속성 -->
            <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
            <property name="javax.persistence.jdbc.user" value="sa"/>
            <property name="javax.persistence.jdbc.password" value=""/>
            <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/test"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
            <!-- 옵션 -->
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>
            <property name="hibernate.use_sql_comments" value="true"/>
            <property name="jdbc.batch_size" value="10"/>
            <property name="hibernate.hbm2ddl.auto" value="create-drop"/>
        </properties>
     </persistence-unit>
</persistence>

```

똑같은 내용을 실행 하면

![image-20210922120638210](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922120638210.png)

어플리케이션이 종료되기 직전에 다시 drop table 시키는 것을 확인 할 수 있습니다.

​	

실제로 테이블이 삭제되고 남아있지 않습니다.

![image-20210922120850051](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922120850051.png)

​	

## Update

이번에는 update를 테스트 해 보겠습니다. persistence.xml에서 아래의 property 를 설정 한 뒤에

```xml
<property name="hibernate.hbm2ddl.auto" value="update"/>
```

​	

일단 테이블이 삭제되었으니 기본적인  ID와 NAME 만으로 실행 해서 테이블을 새로 생성 해 주고요.	

```java
package com.tistory.shanepark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

```

​	

그다음에 address 라는 항목을 추가 해 보겠습니다.

![image-20210922121128219](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922121128219.png)

​	

그러고 나서 실행..

![image-20210922121148864](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922121148864.png)

위에 보이는 것 처럼 더이상 drop 이나 create 가 되지 않고 alter table이 되었습니다. 기존의 테이블 상태를 확인 한 후에 변경 사항만 간단하게 적용 하게 되었습니다.

![image-20210922121311462](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922121311462.png)

당연히 데이터 베이스 상에 해당 내용이 적용 되어 있습니다.

​		

그렇다면 이번엔 지우는 건 어떨까요? 방금 추가한 address 를 삭제해서 실행 해보면.

![image-20210922121357736](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922121357736.png)

​	

이번에는 아무 일도 일어나지 않습니다. update는 추가하는 것만 됩니다.

![image-20210922121410579](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922121410579.png)

​	

## validate

validate는 엔티티와 테이블이 정상 매핑되어 있는지만 확인 해줍니다. validate로 변경 한 후에

```xml
<property name="hibernate.hbm2ddl.auto" value="validate"/>
```

​		

이번에는 department를 추가 해서 

![image-20210922121756323](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922121756323.png)

어플리케이션을 그대로 실행 해 보겠습니다.

​	

이번에는 에러가 발생 합니다. 

![image-20210922121822980](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922121822980.png)

​		

에러메시지에 써 있는 것 처럼 MEMBER 테이블에 department 가 없기 때문에 Schema-validation 에서 실패 했습니다.

```
Schema-validation: missing column [department] in table [MEMBER]
```

​	

## none

해당 기능을 사용하지 않으려면 none 으로 value를 적어 주면 됩니다. hibernate.hdm2ddl.auto property 자체를 삭제하거나 주석 해도 되고, name 에 none이라는 건 없기 때문에  아무거나 입력 해도 똑같지만, 관례상 사용하지 않을 때에는 none이라고 씁니다.

​	

## 주의사항

 운영중인 서버에서는 절대, 절대 create, create-drop, update를 사용하면 안됩니다. 유투브 개발바닥의 호돌님이 배달의 민족 정산 시스템을 담당하시던 시절 데이터베이스 테이블을 날려 먹었던 대형 사고도 application.yml에서 운영 프로필에 해당 옵션이 없다 보니 default profile에서 spring.jpa.ddl-auto:create 를  끌어 와서 운영서버에서 create 옵션이 적용 되어 버렸던 거죠. 우아한 형제들 내에서도 손에 꼽을 만한 대형 사고였다고 합니다.

개발 초기에는 create 또는 update를 사용 하면 편하지만 테스트 서버에서는 update 또는 validate를 사용 하거나 애초에 사용 하지 않는게 좋고, 스테이징이나 운영 서버에서는 사용하지 않는게 좋습니다. update도 잘못되어서 ALTER COLUMN이 나가면 데이터베이스 컬럼이 LOCK되어 버리기 때문입니다.

​	

마지막으로 DDL 생성시 제약 조건을 추가 할 수 있습니다.

아래 보이는 것 처럼  Entity에 length나 nullable 옵션을 걸어 준다면

![image-20210922124423841](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922124423841.png)

​	

쿼리가 나갈때도 varchar(30) 혹은 not null 같은 제약 조건을 추가 해 줍니다.	

![image-20210922124454340](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/jpa/autoddl.assets/image-20210922124454340.png)

>  DDL 생성 기능은 DDL을 자동 생성 할 때만 사용되고, JPA의 실행 로직에는 영향을 주지 않습니다.

​	

이상입니다. 이런 기능을 진작 알았다면 아꼈을 수많은 시간과 순간들이 주마등처럼 스쳐 지나갑니다. 

> 하지만 정말 주의해서 사용해야 하는 기능입니다. 


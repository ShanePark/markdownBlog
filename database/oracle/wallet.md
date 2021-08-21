# Mac) Oracle FreeTier 이용해 웹 어플리케이션 배포하기 6) 오라클 전자지갑 사용해 프로젝트와 연결하기



[Mac) Oracle FreeTier 이용해 웹 어플리케이션 배포하기 4) 무료 데이터 베이스 만들기 및 데이터 이관하기](https://shanepark.tistory.com/173) 글에서 만든 무료 Oracle Cloud Free Tier 의 데이터베이스를 프로젝트와 연결 해 보겠습니다.

정말 많은 분들이 무료 데이터 베이스를 만드는데 관심이 있었지만 난이도가 난이도다 보니 실패한 분들이 꽤 많은 것 같았습니다. 어렵게 어렵게 성공을 했는데 도저히 프로젝트와는 어떻게 연결하는지 어려움을 겪는 분들이 상당히 많아서 이메일도 많이 받았는데요.

저도 아직 시도해본적 없는 내용을 무책임하게 관련 reference만 던져드리는 것도 죄송스럽게 생각되어서 주말을 이용해서 직접 한번 해 보았습니다. 막상 해보니 생각보다 꽤 어려워서 쉽지 않으셨겠구나 싶었습니다.



![image-20210821094604223](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821094604223.png)

### 일단 위에 보이는 것 처럼 SQL Developer에서 전자지갑으로 연결이 가능 한 상태가 준비 되어 있어야 합니다. 아직 오라클 전자지갑을 통한 데이터베이스 연결 준비가 되어있지 않다면 [Mac) Oracle FreeTier 이용해 웹 어플리케이션 배포하기 4) 무료 데이터 베이스 만들기 및 데이터 이관하기](https://shanepark.tistory.com/173) 를 먼저 해주세요.

​	

## 지금부터 간단한 프로젝트를 생성 해서 데이터베이스와 Oracle 전자지갑 이용해 DB 연결을 하는 것을 함께 해 보겠습니다. 쉽지 않은 내용이니 차근차근 읽으며 해주시고 막히는 부분은 댓글로 남겨주세요.



## 1 스프링 부트 프로젝트 생성

> 기존에 프로젝트가 있는 분들은 그냥 사용중인 프로젝트 이용하시면 됩니다.

![image-20210821095232354](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821095232354.png)

스프링 부트 프로젝트와 DB 연결을 해보기 위해 스프링 부트 프로젝트를 생성 합니다.



![image-20210821095435493](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821095435493.png)

국비 학원 수강하시는 분들 중에 m1 맥북을 샀다가 고생하는 분들이 대부분인 것 같은데요, 그래서 대부분의 국비 학원에서 사용하는 Mybatis 를 이용해 DB 접속을 하겠습니다. 참 ! 위에 있는 Spring Data JPA 는 추가하지 말아주세요. 저도 나중에 뺐습니다.

Finish 를 눌러 프로젝트 생성을 완료합니다.

​	

### 프로젝트가 준비되었습니다.

![image-20210821095702185](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821095702185.png)

일단 최소한 데이터 베이스 접속을 위해 기본 구조를 만들어 보겠습니다.



VO 객체를 만들기 위한 간단한 쿼리 입니다. snippet 으로 저장 해 두었다가 자주 사용하면 편합니다. TABLE_NAME 에 원하는 테이블 명만 입력하면 VO 객체 생성에 필요한 적절한 코드를 생성해줍니다. SQL Developer에서 해당 쿼리를 실행 해보세요.

```sql
SELECT CASE WHEN NULLABLE='N' AND (DATA_TYPE='VARCHAR2' OR DATA_TYPE='CHAR')
       THEN '@NotBlank'
       WHEN NULLABLE='N' AND DATA_TYPE = 'NUMBER' THEN '@NotNull @Min(0)'
       ELSE '' END
      || DECODE(DATA_TYPE, 'NUMBER', '', '@Size(max='||DATA_LENGTH||')')     
      ||  ' private '||
      DECODE( DATA_TYPE , 'NUMBER', 'Integer ', 'String ' )||
      LOWER(COLUMN_NAME)||';'
 FROM COLS
WHERE TABLE_NAME = 'ISSUE'
ORDER BY COLUMN_ID;
```

![image-20210821100326471](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821100326471.png)

​	

### 위에서 조회된 쿼리 결과를

![image-20210821100535881](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821100535881.png)

간단하게 붙여넣기만 하면 VO 객체가 생성됩니다. Javax.Validation이 필요한데 가지고 있질 않네요.

​	

프로젝트를 우클릭 하고, Spring -> Add Starters 를 클릭하면

![image-20210821100717809](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821100717809.png)

![image-20210821100744496](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821100744496.png)

얼마든지 나중에도 Dependency를 추가 할 수 있습니다.

​	

![image-20210821100857223](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821100857223.png)

그럼 추가 전 후의 차이를 미리 보기 할 수 있습니다. 변경할 내용을 체크 한 뒤에 Finish 를 클릭합니다.

​	

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
```

사실 이렇게 귀찮게 하지 않고 위의 depency만 붙여주어도 됩니다.



![image-20210821101331074](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821101331074.png)

그럼 이제 javax.validation을 import 해서 빨간불이 모두 사라졌습니다.



## 일단 지갑으로 연결을 하기 전에 기존의 방식대로 연결을 하는 프로젝트를 생성 하고, 전자지갑으로 연결하도록 변경을 해 보도록 하겠습니다.

​		

패키지 구조는 아래와 같습니다.

![image-20210821105344285](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821105344285.png)



오랜만에 Mybatis를 써보려니 설정할 것도 많고 얼마 되지도 않았는데 익숙하지 않아서 놀랐네요.

그냥 제가 gaia.best 라는 최종 프로젝트로 진행했던 어플리케이션에서 사용하는 데이터베이스에서 아무 테이블이다 따 와서 하다 보니 issue 라는 테이블을 기준으로 설명 해 드리겠습니다. 본인이 사용하시는 아무 테이블이나 쓰시면 되겠습니다.



### IssueVO.java

```java
package com.shane.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueVO {
	@NotNull @Min(0) private Integer issue_sid;
	@NotNull @Min(0) private Integer issue_no;
	@NotNull @Min(0) private Integer mem_no;
	 private Integer label_no;
	 private Integer milest_sid;
	@NotNull @Min(0) private Integer proj_no;
	@NotBlank@Size(max=200) private String issue_title;
	@Size(max=7) private String issue_create_date;
	@Size(max=7) private String issue_start_date;
	@Size(max=7) private String issue_end_date;
	@NotBlank@Size(max=1) private String issue_status;
	 private Integer issue_priority;
	 private Integer progress;
}

```

​	

### IssueDao.java

```java
package com.shane.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shane.vo.IssueVO;

@Mapper
public interface IssueDao {
	
	List<IssueVO> issueList();
	
}

```

​	

### IssueController.java

```java
package com.shane.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shane.dao.IssueDao;
import com.shane.vo.IssueVO;

@RestController
@RequestMapping("/")
public class IssueController {
	
	@Autowired
	IssueDao dao;
	
	@GetMapping
	public List<IssueVO> list(){
		return dao.issueList();
	}
	
}

```

​	

### issue.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.shane.dao.IssueDao">
  <select id="issueList" resultType="IssueVO">
    select * from issue
  </select>
</mapper>
```

​	

이제 가장 중요한

### application.properties

```properties
# datasource
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/xe
spring.datasource.username=user
spring.datasource.password=password

# vo location
mybatis.type-aliases-package=com.shane.vo

# xml location
mybatis.mapper-locations=classpath:mappers/*.xml
```



이렇게 간단한 어플리케이션을 생성 해서 작동 해 보았습니다.

![image-20210821105939556](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821105939556.png)

​	

POSTMAN을 통해 해당 URL로 GET 요청을 보내니 데이터를 잘 받아옵니다.

### 단순히 요청에 대한 응답을 보내는 지 정도만 체크하고 싶고, GET 방식이라면 그냥 주소창에 넣고 입력 하셔도 괜찮습니다.

![image-20210821110028490](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821110028490.png)

​	

## 그럼 이제 전자지갑으로 접속을 변경시켜 보겠습니다.

https://docs.oracle.com/en/cloud/paas/autonomous-database/adbsa/connect-jdbc-thin-wallet.html#GUID-5ED3C08C-1A84-4E5A-B07A-A5114951AA9E 

http://javaperformancetools.blogspot.com/2017/06/oracle-wallet-jdbc-jpa-configuration.html

> 위의 두 링크를 참고했는데요, 가장 결정적인건 이전 글에서 댓글 달아주셨던 help님의 힌트가 가장 컸습니다.

일단 지갑을 압축 풀 필요는 없지만 혹시 풀어본다면 tnsnames.ora 파일에서 제일 위에 shane_high 가 보입니다. 각자 본인의 _high 이름을 체크 하셔서 해당 이름으로 접속 해야 합니다. 무작정 test_high 이런 식으로 쓰면 접속이 되지 않습니다.

![image-20210821112724130](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821112724130.png)



### application.properties

```properties
# datasource
spring.datasource.url=jdbc:oracle:thin:@shane_high?TNS_ADMIN=/Users/shane/documents/Wallet_shane
spring.datasource.username=shane
spring.datasource.password=passwordpassword
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver

# vo location
mybatis.type-aliases-package=com.shane.vo

# xml location
mybatis.mapper-locations=classpath:mappers/*.xml
```



일단 위와 같이 변경해서 시도 해보았습니다.



![image-20210821113259914](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821113259914.png)



몇가지 에러가 뜨는데요, 해당 에러들을 해결 하면 될 것 같습니다.



![image-20210821113516004](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821113516004.png)



일단 블로그에 댓글 달아주신 help 님이 OJDBC 문제로 고생 하셨었다고 하는데 저도 stack trace에 ojdbc8 이 뜨는걸 보니 이걸 우선 해결 해 보겠습니다.



### ojdbc 10으로 변경

​	

pom.xml 에서 ojdbc를 8 에서 10으로 변경합니다.

```xml
		<!-- https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc10 -->
		<dependency>
		    <groupId>com.oracle.database.jdbc</groupId>
		    <artifactId>ojdbc10</artifactId>
		    <version>19.11.0.0</version>
		</dependency>
```



ojdbc를 10으로 갈아끼우고 나니 실행할 때 아래와 같은 에러가 발생했는데요

```
Registered driver with driverClassName=oracle.jdbc.driver.OracleDriver was not found, trying direct instantiation.
```



Oracle 9 이후로는 oracle.jdbc.driver.OracleDriver 대신 oracle.jdbc.OracleDriver 를 이용하기 때문입니다.

```properties
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```

드라이버 이름을 위와 같이 변경합니다.



![image-20210821114935495](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821114935495.png)

### 이제 Oracle 지갑을 이용하기 위해 osdt_cert, osdt_core, oraclepki 를 추가해줍니다.

> 이때 제가 댓글 열심히 달았던 내용을 보니 제가 동문서답 한 내용들이 많이 보여 부끄러웠습니다.

​	

```xml
		<dependency>
		    <groupId>com.oracle.database.security</groupId>
		    <artifactId>oraclepki</artifactId>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.oracle.database.security/osdt_cert -->
		<dependency>
		    <groupId>com.oracle.database.security</groupId>
		    <artifactId>osdt_cert</artifactId>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.oracle.database.security/osdt_core -->
		<dependency>
		    <groupId>com.oracle.database.security</groupId>
		    <artifactId>osdt_core</artifactId>
		</dependency>
		<dependency>
```



이제 다시 시도 해 보겠습니다.

![image-20210821115321842](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821115321842.png)

분명 오류 메시지인데 왜이렇게 기쁠까요 ? 비밀번호는 잘 입력한 것 같은데 보니 username 을 확인해보니 admin 으로 써야 하는데 shane 이라고 써놨었습니다. 해당 내용을 변경 한 후 다시 시도 해보면..



![image-20210821115612007](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821115612007.png)

![image-20210821121732152](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/database/oracle/wallet.assets/image-20210821121732152.png)

성공했습니다.  쉽지 않았네요. 저보다 먼저 고생하며 앞에서 길을 열어준 "help"님 덕분에 많은 분들에게 해당 내용을 공유할 수 있게 되어 기쁩니다.



## 맥북을 구매하셔서 오라클 데이터베이스를 사용하는데 큰 어려움이 있으시겠지만 모두 개발자로서의 큰 성장의 트리거가 되셨으면 좋겠습니다. 수고하셨습니다.



마지막으로 pom.xml과 application.properties 공유 해 드리고 마치겠습니다.



### pom.xml

```xml
# datasource
spring.datasource.url=jdbc:oracle:thin:@shane_high?TNS_ADMIN=/Users/shane/documents/Wallet_shane
spring.datasource.username=admin
spring.datasource.password=passwordpassword
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# vo location
mybatis.type-aliases-package=com.shane.vo

# xml location
mybatis.mapper-locations=classpath:mappers/*.xml
```



### application.properties

```properties
# datasource
spring.datasource.url=jdbc:oracle:thin:@shane_high?TNS_ADMIN=/Users/shane/documents/Wallet_shane
spring.datasource.username=admin
spring.datasource.password=passwordpassword
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# vo location
mybatis.type-aliases-package=com.shane.vo

# xml location
mybatis.mapper-locations=classpath:mappers/*.xml
```



전체 코드는 https://github.com/Shane-Park/markdownBlog/tree/master/projects/oraclewallet 에서 확인하실 수 있습니다.
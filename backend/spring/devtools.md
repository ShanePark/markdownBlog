# SpringBoot 프로젝트에서 HTML, CSS 등 정적자원 Hot Reload 시키기 - IntelliJ IDEA 인텔리제이

​	

단순한 html 파일만 변경 하는데도 Eclipse 쓸때는 별 문제 없이 바로 바로 새로고침이 되었던 것 같은데 인텔리제이를 사용하려니 서버를 재시작 하지 않으면 변경사항이 적용이 안되더라구요.

인터넷을 검색 해도 옛날 정보라서 검색해서 체크하라던 내용도 없고, 하란 대로 해도 안되서 여기 저기 다 검색 하다가 해결을 해서 정보를 나누려고 포스팅 합니다.

​		

### 1. Preferences 에서 Compirer에 Build Project automatically 를 체크해줍니다.

![image-20210825220857546](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/devtools.assets/image-20210825220857546.png)

​		

구글에서 검색 했을 때에는 registry에 가서 **compiler.automake.allow.when.app.running** 를 체크하라는 검색 결과가 상당히 많았는데, 지금 최신 버전에서 registry에거 아무리 해당 내용 찾아도 없습니다. 해당 내용은 이동되었습니다.

​	

### 2. 마찬가지로 Preferences 에서 Advanced Settings에 가면 제일 위에 Compiler 항목을 체크해줍니다.

>  이 내용이 위에서 말하던 allow.when.app.running 항목인데 새로운 위치로 이동 되었습니다.

![image-20210825221549377](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/devtools.assets/image-20210825221549377.png)

​		

### 3. Run/Debug 설정을 변경해줍니다.

저는 다른거 다 해도 계속 안되서 고생을 했었는데 결국 Edit Configurations가 필요했습니다. 

우측 상단에 있는 드롭박스 메뉴를 클릭하고 Edit Configurations 로 들어갑니다.

![image-20210825222055250](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/devtools.assets/image-20210825222055250.png)

​	

### 그다음에 해당 Application을 선택 하고, Running Application Update Policies를 변경해줍니다.

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/devtools.assets/image-20210825222201154.png)

On 'Update' action 과 On frame deactivation이 기본은 Do nothing 으로 되어 있습니다. Update Classes and Resources로 변겨 해 줍니다. 

​	

### pom.xml에 spring-boot-devtools 를 추가해줍니다. 

> gradle이라면 알아서 잘 하실 거라고 생각합니다.

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
		</dependency>
```

​	

### application.properties 에 아래 두 줄을 추가해줍니다.

> 마찬가지로 application.yml 쓰시는 분들도 알아서 잘 추가 하실 거라고 믿습니다.

```properties
spring.devtools.livereload.enabled=true
spring.devtools.restart.enabled=true
```



그러면 이제 서버를 재시작 하고 나면, HTML 등 정적 자원을 변경 했을때는 서버를 굳이 껐다 켜지 않아도 새로고침만 해도 변경 사항이 적용 되는 것을 확인 할 수 있습니다.




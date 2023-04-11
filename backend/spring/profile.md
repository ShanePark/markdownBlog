# SpringBoot ) application.yml 프로필별 따로 사용



application.properties 가 상황에 따라 개발환경/ 배포환경 등으로 나눠서 설정이 필요한 경우가 있습니다.

필요 할 떄마다 파일을 수정해가며 하면 번거롭기 때문에 보통 여러개의 프로필을 만들어 두고 필요에 따라 사용하는게 편리합니다.



application.yml (혹은 application.properties) 파일은 기본 설정 파일입니다.

후에 application-dev.yml 혹은 application-dep.yml 파일을 생성한다면 dev 혹은 dep가 profile이 됩니다. 그리고 또한 프로필에 들어간 설정들은 기존의 설정을 overwriting합니다. **같은 속성명**으로 **다른 변수 값**을 주면 됩니다.



그럼 이제 새로 만든 프로필로 어플리케이션을 실행하도록 설정하는 방법을 알아보겠습니다.



1. Eclipse(STS) 에서의 설정 입니다.



1. 해당 프로젝트를 우클릭 하고 - properties를 들어갑니다.

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/profile.assets/img.webp)



2. Run/Debug Setting 으로 갑니다.

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/profile.assets/img-20210819214830379.webp)

이때 해당 프로젝트를 아직 한번도 실행하지 않았었다면 이렇게 아무것도 나오지 않습니다. 일단 한번 실행 해 주고 설정 하는것도 좋습니다.

​	

3. 한번이라도 실행 했다면 스프링 부트 Configuration이 보여집니다. Edit 을 누릅니다.

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/profile.assets/img-20210819214830370.webp)

 	

4. Arguments 를 클릭하고, VM arguments 란에 아래와 같이 입력합니다.

```
-Dspring.profiles.active=dev
```

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/profile.assets/img-20210819214830372.webp)



5. 그러면 이제 application-dev.yml 파일 혹은 application-dev.properties 파일을 기본 프로필로 이용합니다.

설정 전 후 비교를 해보면 아래 보이는 것 처럼 원래는 어플리케이션을 실행했을때 default profiles: default 라고 나오지만

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/profile.assets/img-20210819214830353.webp)



6. 설정후에는

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/profile.assets/img-20210819214830374.webp)

이렇게 The following profiles are **active : dev** 로 나오는 것을 확인 할 수 있습니다.

​	

​	

2. 이번에는  IntelliJ IDEA 에서의 설정 입니다.

​	

![image-20210902085016491](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/profile.assets/image-20210902085016491.webp)

우측 상단에 Edit Configurations.. 라는 드랍박스 메뉴를 클릭 합니다.

​	

![image-20210902085041400](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/profile.assets/image-20210902085041400.webp)

Active profiles를 적는 란이 바로 있네요. 거기에 그냥 dev 를 써주면 끝 입니다. Eclipse 보다 간단하네요.

​	

![image-20210902085109736](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/profile.assets/image-20210902085109736.webp)

​	

간단하게 바로 문제 없이 작동이 됩니다.



이상입니다.
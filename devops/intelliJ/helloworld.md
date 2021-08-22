# MacOS ) IntelliJ IDEA 처음 사용해보기

​		

![image-20210821161806428](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821161806428.png)

https://www.jetbrains.com/ko-kr/lp/language-pack/

​	

## IntelliJ IDEA 한국어 언어 팩 출시 기념 30% 할인을 진행하고 있습니다. 

8월 끝날 때 까지 할인을 한다고 하는데 1년차 구독료가 굉장히 비싸기 때문에 $149의 30% 면 무려 $44.7 을 절약 할 수 있습니다. 한화로는 5만원이 넘네요.

​		

예전부터 IntelliJ IDEA를 사용 해 보고 싶었는데, 여러 가지 중 가장 큰 이유는 M1 맥북에서의 Eclipse가 참 불안정 하다는 점 이었습니다. 

​	툭하면 오류나고, 버그나고. 뭐만 하면 빌드가 안되는데 건들지도 않았기 때문에 그게 또 Project Clean을 하면 새로 빌드하면서 문제가 해결되는 웃지 못할 상황이 자주 연출 되었습니다.

​		

### 또한 그중에서도 제일 불편했던 점은, 맥북으로 Eclipse를 쓰는 분들이라면 모두 공감하시겠지만

![eclipse](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/eclipse.gif)

한글 입력시 마지막 글자가 자꾸 사라진다는 겁니다. 몇 년이 지나도 고쳐지지 않는 걸로 봐서는 고칠 생각이 전혀 없는 것 같습니다.

윈도우 사용자들은 위 이미지를 봐도 저게 뭔데? 싶겠지만 맥북 사용자들은 진짜 웃겨도 웃지 못합니다..

​		

### 그러한 이유에서 IntelliJ의 사용을 많이 고민 해 왔습니다. 

​	

회사에 요청하면 충분히 개발자들에 대한 투자를 아끼지 않는 분위기라서 들어주실 것 같긴 한데

​	

![image-20210821162622217](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821162622217.png)

​	

기업이 구독을 하려면 한 사람당 $499.00 이라는 무지막지한 요금을 부과합니다. Windows에서는 아직 멀쩡하고 모두가 익숙한 Eclipse를 놔두고 연 $499를 투자 했을때 그만큼 이상의 Business Value가 따라오는가에 대해 확신이 서지 않습니다. 아직 사용해 보지도 않은 IDE기 때문에 그 사용에 대한 납득이나 설득을 시킬 자신도 없고 그럴 이유도 없습니다.

​	

![image-20210821162715155](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821162715155.png)

반면 개인이 사용하면 연 $149로 훨씬 저렴합니다. 내가 이만큼의 돈을 내고 IntelliJ IDEA를 이용한다면, 그 다음 해에 내 연봉을 IntelliJ IDEA를 사용하지 않았을 때에 비해 $149 이상을 올리는데 기여할 수 있을까에 대한 답은 그닥 어렵지 않습니다. 

​			

![image-20210821163601698](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821163601698.png)

개인적인 구매를 해서 회사에서 사용하는건 아무런 제약이 없습니다.

​			

![image-20210821163527497](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821163527497.png)

단지 그 돈을 내는 주체가 회사인가 개인인가에 의해서 가격이 크게 갈리는 것 뿐입니다.

개인적으로는 꽤나 합리적인 과금 방식이라고 생각합니다. 

​	

### 그러한 이유에서

30% 할인이 종료되는 8월 내에, 구독을 할 것인지 말 것인지 결정을 위해 IntelliJ IDEA를 써보기로 결정했습니다.

처음 다운받으면, Free 30-day trial을 할 수 있기 때문에 부담없이 사용 해 볼 수 있습니다.	

![image-20210821164158278](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821164158278.png)

https://www.jetbrains.com/idea/download/#section=mac

위 링크에서 다운 받을 수 있습니다. m1 맥북을 사용중이라면 화살표를 눌러 Apple Silicon 버전을 다운 받아 설치 할 수 있습니다.
	

### 설치 과정은 간단하기 때문에 생략합니다.

​	

### 프로젝트 선택

![image-20210821155127720](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821155127720.png)

처음 IntelliJ IDEA를 열면 project를 생성하거나 Open 할 수 있습니다. Git에서도 받아 올 수 있네요.

​		

### 새로운 프로젝트를 한번 생성 해 보고, 기존의 프로젝트도 한번 열어보겠습니다.

​	

## 프로젝트 생성

+New Project 버튼을 눌러 생성합니다.

![image-20210821155239790](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821155239790.png)

처음에는 SDK: 라고, 사용할 JDK 버전을 선택 해 줘야 합니다. 기존에 본인이 가지고 있는 JDK 를 사용하셔도 되고, download를 누르면 IntelliJ가 제공하는 많은 최신의 JDK가 있기 때문에 다운받아서 사용하셔도 됩니다.

​	

template을 사용하려면 체크 하지만 저는 그냥 Next를 누르겠습니다.

![image-20210821155252505](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821155252505.png)

​	

그리고 이제 Project name 및 location을 선택합니다.

![image-20210821155334120](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821155334120.png)

​	

![image-20210821155343188](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821155343188.png)

경로가 없다면 만들어 주면 됩니다.

​	

![image-20210821155410321](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821155410321.png)

간단하게 프로젝트가 생성되었습니다. src를 우클릭하고 New - Java Class를 생성해줍니다.

​	

![image-20210821155524319](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821155524319.png)

당연히 HelloWorld를 빼놓을 수 없겠죠.

​	

![image-20210821155542913](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821155542913.png)

우클릭해서 Run 하면 됩니다.

맥북에서의 단축키는 Ctrl + Shift + R 입니다.

​	

![image-20210821155602424](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821155602424.png)



간단하게 Hello world 가 출력 됩니다.





## Eclipse로 생성한 Spring Boot 어플리케이션 띄워보기

이번에는 Eclipse로 생성했던 프로젝트를 IntelliJ IDEA 에서 띄워보겠습니다.

​	

![image-20210821160756965](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821160756965.png)

프로젝트 목록에서 상단의 Open을 클릭합니다.

​	

![image-20210821160835112](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821160835112.png)

이클립스에서는 프로젝트 열 때부터 Maven, Gradle 등을 선택해서 정확히 열었어야 하는데, IntelliJ IDEA는 그냥 폴더 열듯 열면 됩니다. vuejs 프로젝트를 열어보겠습니다.

​	

![image-20210821160857817](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821160857817.png)

프로젝트 설정이 2개 발견되었다고 합니다. Maven project 기 때문에 Maven project를 선택해서 Open 하겠습니다.

​	

간단한 경고문이 나오는데 신뢰 할 수 있는 프로젝트라면 Trust Project를 선택합니다.

![image-20210821160906467](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821160906467.png)

​	

![image-20210821161007297](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821161007297.png)

JPA Buddy 라는 플러그인을 깔으라고 뜨길래 설치 해줬습니다.

​	

![image-20210821161033636](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821161033636.png)

프로젝트가 빌드가 되면 Run을 한번 해봅니다. 우측 상단에 버튼이 있습니다.

​	

![image-20210821161140051](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821161140051.png)

Eclipse 사용했을 때와 다를 것 없이 스프링 부트 프로젝트가 실행 됩니다.

너무 당연하게도, 서버도 아무런 문제 없이 잘 되었기 때문에 지금까지의 인상은 꽤 좋습니다. 

​	

## 일단 단점을 딱히 찾을 수 없었는데 처음 사용해본 소감으로

- 빌드가 안정적이고 빠릅니다.

- 개발 환경은 더 빠르고 버벅임이 없습니다.

- 인터페이스가 굉장히 깔끔합니다.

- Git 환경이 기본적으로 잘 되어있습니다.

- 굉장히 실용적입니다.

  

![image-20210821170259602](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821170259602.png)

> 엔드포인트를 한눈에 보기 쉽게 나열해줍니다.

​	

![image-20210821170355200](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821170355200.png)

> 내장되어있는 Git 또한 나무랄 데가 없습니다.

​	

![image-20210821170525503](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821170525503.png)

Dependencies에는 심지어 Github Star수가 몇개인지, 어떤 License 인지 까지 나옵니다.

​	

![search](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/search.gif)

기본적으로 제공하는 검색 기능이 말도 안되게 빠릅니다. 이클립스의 Ctrl+H 를 생각하면 사실 검색 기능만으로도 충분히 넘어 올 이유가 될 수 있습니다.

​	

![image-20210821185321512](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210821185321512.png)

Eclipse도 그렇지만, 다양한 Plugin을 제공합니다. 다만 이클립스에서는 플러그인의 충돌이나 호환성 등 문제가 있었지만 인텔리제이는 보다 안정적입니다. 특히 요즘 사용중인 Pebble template의 플러그인이 Eclipse에는 없는데 IntelliJ IDEA에는 있습니다.



그래서 저는 8월이 끝나기 전에 30% 할인된 가격으로 구독을 하기로 결정했습니다. 당장 업무에서 사용하기에는 무리가 있고 단축키를 익히는데 시간이 필요하겠지만, 개인적인 공부나 프로젝트에서 활용하며 점차 그 사용을 늘려 볼 계획입니다.

![image-20210822093227801](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210822093227801.png)

![image-20210822093135000]https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/helloworld.assets/image-20210822093135000.png)

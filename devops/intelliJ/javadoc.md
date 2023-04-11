# IntelliJ IDEA 에서 javadoc 주석 다는 단축키 지정하기



### Eclipse에서는 Command+Option+J ( Windwos는 Alt + Shift + J) 를 입력 하면 간단하게 javadoc comment 를 달 수 있습니다.

​	

예를 들어 아래와 같은 코드가 있을 때

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/javadoc.assets/image-20210911083716887.webp height=500 width=750 alt=1>

​	

arrayConverter 메서드에 커서를 올린 후, 해당 단축키를 입력 하면..

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/javadoc.assets/image-20210911083746080.webp width=750 height=400 alt=2>

아주 간단하게 javadoc을 작성 할 수 있습니다.

하지만 IntelliJ IDEA 에서는 해당 단축키가 없기 때문에 /** 를 입력 후 엔터를 치는 방식으로 입력 해야 하는데요,

​	

​	

### 지금부터 그 단축키를 지정 해 보도록 하겠습니다.

​		

![image-20210911084532663](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/javadoc.assets/image-20210911084532663.webp)

일단 인텔리제이 에서 해당 Action의 이름을 찾아 보니 fix doc comment 라고 쓰여 있습니다.

​	

![image-20210911084706606](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/javadoc.assets/image-20210911084706606.webp)

혹은 Option(Alt) 엔터를 눌러서 Add Javadoc을 하는 방법도 있는데요,

​	

단축키로 지정해두고 쓰는게 편하겠습니다.

Preferences -> Keymap 에 가서 Fix doc comment를 찾습니다.

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/javadoc.assets/image-20210911090604334.webp)

​	

이제 우클릭 후 Add Keyboard shortcut 을 하고

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/javadoc.assets/image-20210911090613861.webp)

​	

원하는 단축키를 넣어 줍니다.

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/javadoc.assets/image-20210911090819086.webp)

​	

OK 를 눌러 저장 후 테스트 해 봅니다.

![Sep-11-2021 09-09-23](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/intelliJ/javadoc.assets/ggif.webp)	

​	아주 마음에 듭니다 !  이상입니다.


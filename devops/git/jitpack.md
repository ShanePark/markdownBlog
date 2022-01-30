# 나만의 라이브러리 만들어 jitPack으로 배포하고 Maven/Gradle 에서 사용하기

## Intro

### 나만의 유틸리티 / 모듈 프로젝트를 배포 해야 하는 이유

1. 중복 코드

 프로젝트를 진행하다 보면 여러 프로젝트 에서 공통으로 사용되는 유틸리티성 클래스를 만들 때가 있습니다. Apache의 Commons같은 경우가 재 사용 가능한 자바 기반의 컴포넌트들을 아놓은 통합 프로젝트로서 그런 용도로 사용됩니다. 하지만 모든 사용자에게 맞는건 아니여서 누구든 자주 사용하던 자신만의 유틸성 클래스를 꼭 만들게 되는데요.

 여기저기 사용 된다고 해서 한번 만든 유틸성 클래스를 여러곳에 복사해서 붙여넣으면, 작업하다가 어디에선가 문제가 발견되었을때 여태까지 해당 클래스를 사용한 모든 클래스를 하나 하나 열어 코드를 일일히 찾아서 바꿔줘야 하는 번거로움이 있습니다. 자신만의 하나의 유틸리티성 통합 프로젝트를 만들어 놓고 간단히 maven을 이용한 의존성 추가 만으로 참조 할 수 있게 된다면 훨씬 편리하겠습니다.	

2. 라이센스 문제

인터넷에서 쉽게 찾을 수 있는 여러가지의 Open Source 라고 해도 다 같은 오픈 소스가 아니기 때문에 항상 사용할 때에는 Licence 부분을 잘 읽어 보아야 합니다. 여기서 간단하게 몇가지 흔히 사용되는  라이센스들에 대해 간략하게 짚어보자면..

![license](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/license.png)

> https://www.whitesourcesoftware.com/resources/blog/open-source-licenses-trends-and-predictions/

- [Apache License](https://www.apache.org/licenses/LICENSE-2.0)

> 소스 코드 공개 의무는 없지만, 아파치 라이선스 코드를 수정해 배포하는 경우에는 꼭 아파치 라이센스 버전 2.0을 포함 시켜야 하고 아파치 재단에서 만든 소프트웨어임을 밝혀야 합니다.

- [GPL](https://www.gnu.org/licenses/gpl-3.0.en.html)

> 지고 있다고 해도 여전히 굉장히 많은 오픈소스들이 따르고 있는 라이센스로, 어떤 목적이나 형태로도 사용 할 수 있지만 사용하거나 변경된 프로그램을 배포할 경우 무조건 동일한 라이센스인  GPL로만 공개해야 합니다. 흔히들 라이센스가 전염된다고 칭하며 리스크가 높습니다.

-  [MIT](https://www.mit.edu/~amini/LICENSE.md)

> 꽤나 핫한 라이센스라고 생각되는데요, 라이선스의 저작권 관련 명시만 지켜주는 가장 느슨한 조건을 가진 라이선스 중 하나 입니다. jQuery, angular, Vue.js 등이 여기 속합니다.

- [WTFPL](http://www.wtfpl.net)

> do What The Fuck you want to Public License 의 준말로 흔한 라이센스는 아닌데, 제가 [Ajax 비동기 요청 발생시 로딩바 만들기](https://shanepark.tistory.com/148) 를 할 때 처음 만났던 라이센스 입니다. 재밌어서 포함시켜 보았습니다.

위에서 살펴 보았듯이 GPL 라이센스를 사용한다면 소스 코드의 공개 의무가 생겨버리게 됩니다. 그렇게 되면 코드를 어딘가에 공개 해야 하는데, 프로젝트를 Github에 올려 두고 바로 참조해서 사용한다면 라이센스 문제를 해결 할 수 있습니다.

<br><br>

그럼 지금부터 나만의 모듈 프로젝트를 만들고 등록까지 해 보겠습니다.

## 배포할 프로젝트 준비

### 프로젝트 생성

간단한 jar를 만들 것 이기 때문에 일반 자바 프로젝트를 생성하면 됩니다. gradle이나 maven으로 만들어도 되겠습니다. 저는 maven으로 프로젝트를 만들었습니다.

![Screenshot 2021-09-12 at 12.09.50 PM](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/50.png)

![Screenshot 2021-09-12 at 12.11.32 PM](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/32.png)

​		

### 클래스 및 메서드 작성		

다른 프로젝트에서 import 해서 사용할 것 이기 때문에 base package 에다 만들면 안됩니다. 뭐가 됐던 본인만의 package 계층을 만들어주세요. 저는 com.tistory.shanepark 패키지 구조를 만들었습니다.

​	

![image-20210912092516816](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912092516816.png)

일단 간단하게 NumberUtil 이라는 클래스를 생성 해 보았습니다.	

![image-20210912095353135](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912095353135.png)

​	

참고로 intelliJ 에서는 테스트 할 클래스 위에 커서를 올린 후 Option(Alt) + Enter 를 누르면 Test를 생성 할 수 있습니다.

![image-20210912095422059](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912095422059.png)

![image-20210912095521912](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912095521912.png)

### 테스트

의도한 대로 작동하는지 테스트를 진행 해 봅니다.

![image-20210912095908022](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912095908022.png)

문제없이 test case 들을 통과 합니다.

이번에는 만드는 김에 제가 알고리즘 문제들을 풀이 할 때 자주 쓰는, 자바스크립트 형태의 배열을 자바에서의 형태로 바꿔주는 유틸도 하나 만들어 보았습니다. 	

![image-20210912100329402](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912100329402.png)

이제 지금 까지 만든 유틸 클래스를 다른 프로젝트에서 가져다 쓰도록 해 보겠습니다.	

## 기존의 방법

> 따라하진 말고 눈으로만 보시면 됩니다.

일단 같은 workspace 에 참조할 프로젝트를 불러와야 합니다.

![image-20210912100548445](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912100548445.png)

​		

그 다음에는 간단하게 Build Path에 해당 프로젝트를 추가 하는 방법으로 참조 할수 있는데요.

![image-20210912100804400](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912100804400.png)

프로젝트 우클릭 -> Build Path -> Configure Build Path... 에 들어가서

​	

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912101907828.png)

해당 프로젝트를 참조 시키면 됩니다. OK 를 눌러 참조 하고,

​	

이제 확인 해 보면 shaneutils 라는 프로젝트에 있는 클래스들을 바로 사용 할 수 있습니다.

![image-20210912101114726](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912101114726.png)

​	

작동도 이상없이 하는 것을 확인 했습니다.

![image-20210912102127250](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912102127250.png)

​	

하지만 이런식으로 이클립스의 워크 스페이스에 올리고, BuildPath에 추가를 해서 사용하는건 제약이 참 많습니다. 아에 Maven 혹은 Gradle로 해당 의존성을 관리 할 수 있다면 정말 좋겠습니다. 특히 Github을 통해 해당 버전을 관리 하면서 언제든 내가 원하는 대로 버전을 관리 하며 사용 한다면 금상 첨화겠네요.

​	

## JitPack 활용해 배포

### 사전 준비

물론 지금은 이해하기 쉽게 간단한 유틸 모듈만을 예로 들었지만, 그 활용은 사용하기 나름입니다. 지금부터 천천히 하나씩 해 보겠습니다.	

일단 헷갈리지 않게 Build Path 에 추가 했던 것은 remove 버튼을 눌러 제거 해 줍니다.

![image-20210912102505475](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912102505475.png)

​		

![image-20210912102541648](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912102541648.png)

자 이제 의도한 대로 빨간불이 들어 왔습니다.

### Github에 저장소 생성

일단 아까 만든 shaneutils 라는 프로젝트를 Github에 올려야 합니다.

IntelliJ IDEA에서는 Terminal 기능을 바로 제공하기 때문에 바로 켜서 git init을 실행합니다.

```bash
git init
```

![image-20210912102959154](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912102959154.png)

![image-20210912103039610](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912103039610.png)

​	

![image-20210912103148186](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912103148186.png)

Eclipse 에도 Terminal이 있긴 한 것 같은데.. 해봤는데 아무것도 뜨지 않고 사용 방법을 모르겠더라고요. 그냥 해당 경로 찾아가서 git init 하시면 됩니다.. 혹시 git이 없다면 git을 설치 하시면 되구요. Mac은 기본적으로 깔려 있던 것으로 기억합니다.



그러고 이제 commit을 해줍니다.	

![image-20210912103507853](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912103507853.png)

​	

이게 아무래도 Git에 대해 설명하는 포스팅이 아니다 보니 Git을 잘 모르는 분들은 좀 헤맬 수도 있겠네요.. 죄송합니다. 그래도 간단한 기능만을 사용하기 때문에 막히는 부분이 있다면 기본 git 명령어 정도만 찾아 보면 충분히 할 수 있을 거라고 생각합니다.

하지만 역시 IntelliJ IDEA를 사용하지 않는 분들도 있을 테니 IDE의 Git 말고 최대한 터미널을 이용해 해 보겠습니다.

```bash
git status
```

를 입력 해서 상태를 조회 하구요.

![image-20210912103711395](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912103711395.png)

​	

```bash
git add .
```

를 입력해서 작업한 내용들을 스테이징 영역에 올립니다.

​			

이제 커밋을 할 준비가 되었습니다. git commit을 입력합니다.

```bash
git commit
```

![image-20210912104609146](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912104609146.png)

​	

그러면 기본 git editor로 설정된 에디터가 실행 됩니다. 저의 경우에는  vi 에디터가 실행 되었는데, 윈도우즈 환경에서는 어떤 텍스트 편집기가 뜨는지 잘 모르겠네요. git config --local core.editor notepad 명령어를 입력 해서 텍스트 편집기를 메모장으로 바꿀 수 있습니다.

![image-20210912105000731](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912105000731.png)

커밋 메시지를 남기고 저장 해 줍니다. 산넘어 산이네요 Git 사용법으로 조금 샜다가 이제 vim 사용법도 적어야 할 판인데.. 일단 너무 주제에서 벗어나기 때문에, 이렇게 까지 하지 않아도 되고 커밋 메시지를 한줄 만 남기셔도 됩니다. ( vim 에서 나가려면 esc 를 누르고 :q! 입력 후 엔터 치면 됩니다.)

​			

만약 커밋 메시지가 한줄 이라면 굳이 에디터를 띄우지 않고

```bash
git commit -m "남길 커밋 메시지"
```

와 같이 메시지를 남길 수도 있습니다.	

​	

![image-20210912105349033](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912105349033.png)

​	![image-20210912105403150](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912105403150.png)

​	

commit이 완료되었습니다.  Github으로 push 해야 겠네요.

push 는 인증 절차가 들어가기 때문에 저는 지금부터는 가장 쉬운 Github Desktop을 이용하겠습니다. 사실 Commit 도 Github desktop을 이용했디먄 더 쉽게 하실 수 있었습니다.

![image-20210912105552097](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912105552097.png)

Github desktop 을 (없다면 다운받고) 켜서, Add Existing Repository 를 해서 방금 만든 git 프로젝트를 불러옵니다.

![image-20210912105816254](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912105816254.png)

​			

![image-20210912105847202](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912105847202.png)

>  아까 commit 했던 내용들이 나와 있네요.

​		

우측 상단의 Publish repository 를 클릭 합니다.

![image-20210912105941144](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912105941144.png)

이제 등록 할 Github 저장소 정보를 입력 해야 하는데요, Keep this code private 체크를 하지 않도록 해주세요. private 으로 해도 jitpack 이용해 배포 할 수 없는 건 아니지만, 우리의 지금 의도와는 다르기 때문에 public을 기준으로 포스팅 하겠습니다.

​				

![image-20210912110113515](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912110113515.png)

이제 Github에 들어가서 확인 해 보면, 방금 추가한 저장소가 등록 된 것을 확인 할 수 있습니다. 딴길로 많이 다녀왔는데 지금부터가 본론이 되겠네요..

### JitPack

jitpack 사용에 대한 메뉴얼을 개인적으로 확인 하고 싶다면 https://jitpack.io 를 방문 해 주시면 됩니다.

저의 경우에는 회사 스승님이 미리 작성해두신 매뉴얼을 jitpack 공식 문서와 함께 참고해서 하니 어렵지 않게 할 수 있었습니다.					

Release 를 관리 한다면,

![image-20210912110658387](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912110658387.png)

우측의 Create a new release 를 눌러 버전을 관리 하며 관리되는 tag 들을 각각 의존 하게 할 수 있습니다.

하지만 저희는 아직 Release 관리에 대한 지식이 없다는 전제 하에 Master 브랜치의 SNAPSHOT을 의존 하도록 해 보겠습니다.

![image-20210912111758955](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912111758955.png)

>  [jitpack.io](https://jitpack.io)에 접속 해서 저장소 주소를 붙여 넣기 한 후 Look up 을 클릭합니다.

![image-20210912111952671](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912111952671.png)

> 그러면 Commit 내용들이 쭉 나옵니다. 

Release 가 있다면 제일 좌측의 Release를 눌르거나 특정 Branch 를 의존 하려면 Branches 를 클릭하면 되겠네요. 저는 그냥 SNAPSHOT (master branch의 최근 내용) 을 참조하도록 해 보겠습니다. 한참 의존유틸을 바쁘게 수정 하면서 함께 프로젝트를 진행 중이라면 SNAPSHOT을 참조하는게 좋습니다. 물론 안정화 된 이후에는 RELEASE를 참조하는게 좋겠죠.

-SNAPSHOT 에 있는 Get it 을 클릭 합니다.

![image-20210912131139188](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912131139188.png)

![image-20210912122908578](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912122908578.png)

Get it 이 되는 동안 빨간색이 뱅글 뱅글 돕니다. 빌드에 실패했다면 Delete.DS_Store 커밋 옆에 붙은 것 처럼 빨간색 문서가 뜨게 되는데요, 저의 경우에는 java 14로 빌드 했다가 실패해서 8로 바꿨습니다. JitPack에서 java 8 이상의 메이븐 컴파일러를 지원 하지 않는 것 같더라고요.

![image-20210912123235066](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912123235066.png)

>  빌드가 완료 된다면 이렇게 초록색 문서 형태가 나옵니다. -SNAPSHOT 의 경우에는 따로 초록색으로 변하지 않았던 걸로 기억합니다.

Get it 을 클릭합니다.

![image-20210912112143059](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912112143059.png)

이제 해당 의존성을 어떻게 추가 할 지에 대해 나옵니다. 본인이 사용하시는 빌드관리 툴에 따라 Gradle 혹은 Maven 탭을 클릭 해서 사용 하시면 됩니다.

이제 해당 유틸을 의존 할 프로젝트에 가서 build.gradle 혹은 pom.xml 을 엽니다.

#### gradle		

build.gradle 파일을 엽니다.

![image-20210912112410712](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912112410712.png)

![image-20210912112556998](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912112556998.png)

How to 에 나온 내용들을 추가 해 줍니다. repositories 에는 

```groovy
maven { url 'https://jitpack.io' }
```

그리고 dependencies 에는

```groovy
implementation 'com.github.Shane-Park:shaneutils:Tag'
```

를 추가 해 줍니다. Tag 의 경우에는 릴리즈 태그가 따로 없다면 master-SNAPSHOT 으로 하시면 됩니다. 특정 커밋을 참조하고 싶으면 jitpack.io 에서 Version 에 있던 10자리 문장을 복사해서 Tag 란에 넣으면 됩니다.

예시

```groovy
implementation 'com.github.Shane-Park:shaneutils:83fc899d41'			
```

이제 빌드를 새로 해줍니다.

![image-20210912112806553](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912112806553.png)

​		

#### Maven

pom.xml 파일을 엽니다.

```xml
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>

```

```xml
	<dependency>
	    <groupId>com.github.Shane-Park</groupId>
	    <artifactId>shaneutils</artifactId>
	    <version>-SNAPSHOT</version>
	</dependency>

```

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912113254188.png)

마찬가지로 위의 내용들을 적절한 위치에 입력 후에 maven > update project(Option/Alt + F5) 를 하면 되겠습니다.

### 확인

이제 잘 적용이 완료 되었다면

![image-20210912113522111](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912113522111.png)

> Maven Dependencies 에 shaneutils 가 추가 된 것을 확인 할 수 있습니다.

​	

![image-20210912114706750](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912114706750.png)

>  Gradle도 잘 추가 되었습니다.

이제 메서드가 동작하는지 확인 해봅니다

- 일단  gradle 로 의존성을 추가한 programmers 프로젝트 입니다.

![image-20210912132426733](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912132426733.png)

STool 의존을 잘 받아와서 해당 모듈을 문제 없이 사용 합니다.

- 이번에는 Maven으로 의존성을 추가한 vuejs 프로젝트 입니다.

![image-20210912132543852](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912132543852.png)

역시 문제없이 모듈을 잘 사용 합니다!

​	

이상으로 jitpack 을 이용한 나만의 오픈 소스 만들기를 해 보았습니다. 내게 필요한 모듈을 관리 할 때 뿐만 아니라, 오픈 소스를 사용하는데 해당 오픈 소스에 버그가 있어 내가 고쳐 쓰는데 Pull Request 를 받아 주기 전 까지 마냥 기다릴 수 없는 경우라거나 혹은 내가 개조 해서 사용 하고 싶을 때도 활용해서 사용 할 수 있는 정말 유용한 툴 입니다.

### 비용

![image-20210912134550300](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/jitpack.assets/image-20210912134550300.png)

JitPack은 Open-source를 사용한다면 항상 무료이며, 만약 private 저장소를 사용 하고 싶다면 소정의 돈을 내고 사용 해야 합니다.

생각보다 포스팅 내용이 길고 양이 방대해졌지만 한번 해낸다면 앞으로의 개발이 훨씬 즐거워지고 생산성이 극대화 될 것이라고 확신합니다. 꼭 한번씩 해보시길 추천드립니다. 긴글 읽느라 고생많으셨습니다.
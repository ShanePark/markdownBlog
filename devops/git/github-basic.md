​	

<h1>수업 내용 깃허브에 올려 동기화하기</h1>

> 본 문서는 markdown 문법 으로 작성되었습니다.
> 	

| <span id="content" style="font-size:2em">Contents</span>     |
| :----------------------------------------------------------- |
| [1. github 가입하기](#index1)                                |
| [2. Repository (저장소) 만들기](#index2)                     |
| [3. Sourcetree 다운로드 및 설치](#index3)                    |
| [4. Sourcetree를 사용해 내 프로젝트를 Repository에 업로드 (동기화, Commit &amp; push) 하기](#index4) |
| [5. 새로운 컴퓨터에 기존 Repository 에서 프로젝트를 받아오기 (동기화, Pull)](#index5) |
| [6. 수정된 코드를 저장소에 반영하기 ( Commit &amp; push )](#index6) |
|                                                              |

​	

<a style="font-size:2em" href="#content" class="index" id="index1">1. Github 가입하기</a>

어렵지 않습니다.

>[github.com/](https://github.com/)


위 주소에 들어가서 편안하게 회원 가입 하시면 됩니다.

아이디도 나중에 마음에 안들면 바꿀 수 있지만, 저도 바꿔봤는데 몇가지 불편한 점이 한동안 따라다니기 때문에 처음에 신중하게 아이디를 만드시길 추천합니다.

​	

​	

<a style="font-size:2em" href="#content" class="index" id="index2">2. 저장소 만들기</a>

Repositories 메뉴에서 우측 상단의 New 를 눌러서 생성하시면됩니다.

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github1.png)

저장소 이름을 쓰는데, 뭐 잘못쓴다고 큰일 나는건 아니니까 편한 대로 작성하시면 됩니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github2.png)

다른 사람이 내 저장소를 못보게 하고 싶으면 Private 로 하시면 됩니다.

하지만 꼭 숨겨야 할 이유가 아닌 이상은

제 생각에 수업내용 기록하는 저장소 한개 정도는 Public 으로 두시는 것도 나쁘지 않다고 생각합니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github3.png)

순식간에 저장소가 생성되었습니다. 뭐 어려워 보이는 화면이 훅 들어오지만, 당황할 필요 없습니다.

Github에서의 세팅은 이걸로 끝입니다.

​	

​	

<a style="font-size:2em" href="#content" class="index" id="index3">3. 소스트리 다운로드</a>


[www.sourcetreeapp.com/](https://www.sourcetreeapp.com/)

Sourcetree | Free Git GUI for Mac and Windows

A Git GUI that offers a visual representation of your repositories. Sourcetree is a free Git client for Windows and Mac.


![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github4.png)

Windows 를 쓰시는 분이면 Download for Windows 를 선택해서 다운 받으시면 됩니다.

다행히도 소스트리 설치 하는 방법에 대해서는 인터넷에 정보가 많으니 혹시 막힌다면 검색을 해보시면 됩니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github5.png)

소스트리를 사용하기 위해서는 Bitbucket 회원가입이 필요한데요, 나중에 이 Bitbucket 아이디와 Github 아이디를 혼동하지 않도록 조심하세요.

이메일 주소를 등록할때는 Github에 등록할 이메일과 같아야 나중에 소스트리에서 커밋했을때 초록불이 들어오지 않는 불상사를 예방할 수 있습니다.

저도 커밋은 했는데 초록불이 들어오지 않아서 보니 소스트리에 등록한 이메일이 Github에 등록되지 않아서 다른 사용자로 인식했던 건데요, Github 에서 회원정보-이메일 등록을 통해 해결 할 수 있었습니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github6.png)

본인의 아이디가 없다면 회원 가입을 하시면 등록 완료 하며 다음 화면으로 넘어 갈 수  있습니다.

**Mercurial**도 같이 설치할지를 선택하는데, 해도 그만 안해도 그만입니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github7.png)

SSH 키가 뭔지 모르신다면 그냥 '아니오'를 선택하시면 됩니다.

본인이 SSH 키를 가지고 계신 분들은 등록 하시면 됩니다.

설치가 완료되었습니다.

​	

​	

<a style="font-size:2em" href="#content" class="index" id="index4">4\. Sourcetree를 사용해 내 프로젝트를 Repository에 업로드 (동기화, Commit & push) 하기</a>

​	


최대한, 처음 설치했을때 모습과 비슷하게 보이려고 tab을 다 닫아봤는데, 저의 Local repositories에 있는 내용들은 무시하시면 됩니다.

Remote를 누르고 Add an account 를 해서 본인의 Github 계정으로 로그인 해 주세요.

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github8.png)

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github9.png)

OAuth 를 이용하면 아이디 비번 치지 않고도 크롬에 로그인 되어 있는 깃허브 정보를 통해 쉽게 로그인 하실 수 있습니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github10.png)

로그인이 완료 되면 저와 같은 화면을 보실 수 있습니다.

우측 에 있는 Refresh 를 눌러줍니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github11.png)

제가 가지고 있는 저장소의 목록이 나옵니다. 저는 방금 만든 basicJava 저장소에 제 컴퓨터에 있는 basicJava 폴더를 동기화 시킬 예정입니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github12.png)

동기화 시키는 요령이야 사람마다 다르겠지만, 제가 여러번의 시행 착오를 통해 깨달은 간단하게 따라하는 방법을 알려드리겠습니다. 혹시 본인 실수로 삭제할까 불안한 분들은 미리 해당 폴더를 .zip 파일로 만들거나 하셔서 백업해두세요.

​	

\* 지금부터는 차근차근 확실하게 하나씩 놓치지 말고 잘 따라해주세요.

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github13.png)

1. ### Github에 동기화시키고자 하는 폴더의 이름을 변경합니다.

   ​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github14.png)

2) ### 새로만들기 - 폴더를 한 뒤 새 폴더를 만들어줍니다. 이름은 해당 폴더의 원래 이름으로 합니다.

그러면 내가 동기화 하고자 하는 폴더 명을 가진 빈 폴더가 생성됩니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github15.png)

3. ### 다시 Sourcetree로 돌아와, 내가 동기화 하고자 하는 저장소를 선택해 Clone 버튼을 누릅니다.

   ​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github16.png)

그러면 여기에서 첫줄에는 자동으로 내가 동기화 하고자 하는 저장소의 주소가 들어가게됩니다.

4. ### 두번째 줄은 수정이 필요합니다. Browse 를 눌러 동기화 하고자 하는 폴더를 찾아줍니다.

   ​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github17.png)

5) ### 여기에서 BasicJava Copy 를누르는 실수를 하지 않도록 하세요. 어차피 아래꺼 눌러도 동기화가 되지 않습니다. Clone 할때는 빈 폴더만 선택 할 수 있습니다. 그리고 그게 BaiscJava Copy로 동기화 하고자 하는 폴더를 피신시킨 이유입니다.

본인이 동기화 하고자 하는 폴더명을 선택하고 '폴더 선택'을 눌러줍니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github18.png)

모든 준비가 끝났습니다.

세번째줄은 이름을 바꿔도 그만 안바꿔도 그만입니다.

잘 모르시겠으면 그냥 바꾸지 않고 진행하시는게 맘 편해요. 바꾼다고 별일은 없습니다.

Clone 버튼을 누릅니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github19.png)

6) ### 아무일도 일어나지 않은 것 같지만, 모든 일이 끝났습니다.

Github의 저장소와 본인 컴퓨터의 해당 폴더는 서로 연결이 되었고, 이제 해당 폴더에 피신시켰던 파일들만 다시 이동해주면 됩니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github20.png)

7. ### 다시 Copy 폴더로 돌아와, 모든 파일들을 제자리로 돌려주시면 됩니다.

   ​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github21.png)

복사하기로 가져와도 되고, 잘라내기로 가져와도 됩니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github22.png)

동기화 된 폴더에 붙여넣기 해줍니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github23.png)

### 8). 잘라내기로 가져왔다면 BasicJava Copy에 혹시 못가져온 파일은 없는지 우클릭-속성 으로 정보를 조회합니다.

못가져온 파일이 있다면 숨긴 항목을 못 가져왔을 확률이 크니, 

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github24.png)

보기 - 숨긴 항목을 체크해서 혹시 못가져온 파일이 있는건 아닌지 확인 해 봅니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github25.png)

9) ### Copy 폴더마저 지우고 나면, 이제 폴더는 말끔하게 이전의 상태로 정리가 완료되었습니다.

이제 SourceTree로 돌아가보겠습니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github26.png)

파일 상태에 보면 새로 등록된 파일들이 잔뜩 있는걸 확인 할 수 있습니다.

### 10 ) Stage All 을 눌러 모두 등록 해 줍니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github27.png)

11. ### 맨 아래 에 있는 Push changes immediately to 를 체크해줍니다.

    ​	

나중에 Git을 좀 더 다양하게 활용 할때가 된다면 체크를 안하는 날이 오실텐데, 당분간은 어차피 본인의 저장소에 하나밖에 없는 Branch 에 커밋을 하는거니깐, Push를 자동으로 하시는게 편합니다.

커밋메시지도 남겨줍니다. 대충 첫 커밋이니 innitial commit 이라고 썼는데, 나중에는 커밋할대마다 파일 변경에 대해 무엇이 변경되었고 왜 변경 했는지에 대해 설명을 남겨주시는 칸 입니다.

다 준비되었으면 오른쪽 아래 Commit을 눌러줍니다.

여기에서 변경사항이 10MB 이상이라면 경고문이 뜹니다.

우리는 처음이라서 변경사항이 많은거라 타당한 이유를 가지고 있습니다. 그냥 하라고 확인 눌러줍니다.



12) ### 이제 깃허브 아이디 , 비번을 쓰라는 화면과 뭔가 선택하는 체크박스가 나옵니다.

\- 깃허브 아이디, 비번 쓰실때 신중하게 정확하게 쓰셔야 합니다. 처음에 팀원분 Github 알려드릴때 이거 잘못 타이밍 했다가 해결하는데 꽤나 애 먹은 적이 있습니다. 

\- ! 혹시나 잘못 쓰신분은 커밋할때 아이디 , 비번 틀렸다는 에러가 나오는데 제가 지금까지 해본 바로는 방법이 전혀 없었습니다. 소스트리를 삭제해야 하는데, 삭제했다가 다시 깔아도 아이디 비번을 이 프로그램이 모두 다 여전히 기억하고 있습니다. 구글에 '소스트리 완전삭제' 검색해서 완전히 삭제 한 후 다시 설치 하셔야 합니다.

\- 처음이면 체크박스 나오면서 뭐 이것 저것 중에 선택하라고 나올텐데,

저는 잘 모르겠어서 no helper 선택 두번 해서 사용하고 있습니다. 이것 저것 다 해보긴 했었는데 지금까진 딱히 사용상에 차이를 잘 모르겠습니다. 그냥 no helper 하시면 될 듯 합니다. 제 기억 상 같은 화면이 두번 뜹니다.

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github28.png)

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github29.png)

### 13 . 커밋이 완료되었습니다 ! Nothing to commit.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github30.png)

14. ### 깃허브에서 본인 저장소에 들어가보시면  업로드 된 내용을 확인 하실 수 있습니다.



축하합니다. 여기까지 따라 오셨다면 Github에 수업내용을 올리는데 성공하셨습니다.

​	

​	

<a style="font-size:2em" href="#content" class="index" id="index5">5\. 새로운 컴퓨터에서 기존 Repository 에서 프로젝트를 받아오기 (동기화, Pull)</a>



​	새로운 컴퓨터에서 같은 저장소를 동기화 하는 방법 설명해드리겠습니다.

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github31.png)

 등록하고자 하는 컴퓨터에는 해당 폴더가 없을테니 이렇게 시작하겠습니다.

위에 했던 작업들과 비슷하니까 쉽게 따라하실 수 있습니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github32.png)

### 1 ) 새폴더 만들기를 합니다.  저는 1.BasicJava 라는 이름으로 만들었습니다.

저장소에서 파일들을 가져올 폴더를 만들어 줍니다. 

폴더 안에는 아무런 파일도 있으면 안됩니다. 비어 있어야 합니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github33.png)

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github34.png)

### 2 ) 새로운 컴퓨터에 소스트리 다운로드 하고 로그인 합니다.

​	

### 3 ) 소스트리에서 Remote-  Github 아이디 로그인 - 저장소 선택 - Clone 눌러줍니다. 

위에 5) 번에서 했던 내용과 아에 똑같습니다.    
clone 버튼을 바로 누르지 마세요! 

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github35.png)

### 4 ) Clone 바로 누르지 마세요! 두번째에 있는  Browse 버튼 눌러서, 

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github36.png)

5. ### 방금 만든 폴더 찾아서 폴더 선택 해 줍니다.

   ​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github37.png)

Clone 누르면 끝입니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github38.png)

새로 만든 폴더에 저장소 내용이 동기화 되었습니다.

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github39.png)

### 6 . 폴더에 들어가보면 잘 동기화 된 것을 확인 할 수 있습니다.

​	

​	

<a style="font-size:2em" href="#content" class="index" id="index6">6\. 수정된 코드를 저장소에 반영하기 (Commit & push)]</a>

​	

![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github40.png)
![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github41.png)

예시로 java 파일 한개를 수정 한 뒤 저장해보겠습니다.

​	![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github42.png)

오른쪽에서 수정된 내용을 확인 할 수 있습니다.

​	

확인 후 커밋하려면 아까처럼

1) Stage all 누른 뒤

2)커밋 메시지 입력

3) push changes immediately to origin/master 체크

4) commit 누르기 

하면 쉽게 끝납니다.

​	![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github43.png)

파일 올라간 것 확인 뒤 메시지 쓰고 Commit 까지 누르면 커밋이 됩니다.

​	![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github44.png)

저장소에 돌아와 보면 새로 커밋한 시간 (33초전) 과

변경된 메시지 ( test commit) 
![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github45.png)

그리고 변경 된 파일 까지 확인 할 수 있습니다.

​	

저장소가 수정되었다면 다른 컴퓨터에서는 해당 수정된 내용을 받아와야 겠죠???

​	![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github46.png)
본인 개인 컴퓨터에서 작업을 했고, 이제 학원에 돌아와서 소스트리를 킨다면 ( 반대의 경우에도 마찬가지)

Pull 버튼에 보통 1 숫자가 들어와 있습니다.

안들어와있어도 꼭 pull 을 해주셔야 합니다. 

​	![image](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-basic.assets/github47.png)

Pull 버튼을 누르면 무난하게 해당 컴퓨터에도 수정 내용을 받아오실 수 있습니다.



---



간단하게 쓰고 싶었는데 작성하다 보니 생각했던것 보다 내용이 훨씬 길어졌습니다.

사용하시다 보면 머지않아 Pull 혹은 Push 중에  충돌(Conflict) 라는 끔찍한 경험을 하게 됩니다.

한단계 본인이 더 성장하는 좋은 기회라고 긍정적으로 생각하고 싸워 승리하시면 됩니다.

workspace를 통째로 커밋하고, pull 하는 경우에는 장단점이 있습니다.

장점 : 환경설정 한 내용이 모두 그대로 따라와서 새로 환경설정 할 필요가 없다.

단점 : metadata 파일이 무지막지 하게 변경되어서 커밋 할때마다 나도 모르는 수많은 파일들이 함께 commit 된다.

workspace 통째로 커밋해서 사용하시다가 , 나중에 좀 익숙해지면 프로젝트만 받아오고 수정 하시는게 좋습니다. 

저는 이걸 깨닫기 까지 무려 7주가 걸렸습니다. metaData들의 conflict에 지칠때쯤 이 길로 오시면 되고 그때까지는 workspace 통째로 커밋하셔도 push 와 pull 만 잊지 않는다면 잘 사용하실수 있습니다.

긴글 본다고 고생하셨습니다!



나중에 Git에 익숙해지신다면 git innit 명령어를 이용해 쉽게 할 수 있는 내용이지만 초보자 입장에서 차근 차근 따라하며 최대한 이해하기 쉽게 작성 해 보았습니다.



><a style="font-size:1.5em" href="#content" class="index">처음으로 돌아가기</a>
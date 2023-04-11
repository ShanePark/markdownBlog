# 맥북 마우스 뒤로가기 & 앞으로가기 버튼 활성화

원래는 맥북을 쓸 때 트랙패드만 사용 해 왔는데, 회사에서 개발을 할 때는 MacOS 가 아닌 Ubuntu 환경에서 개발을 하다 보니 마우스를 많이 쓰게 되었습니다. 집에서의 개발보다 회사에서의 개발이 더 익숙해 질 때가 되니, 맥북을 사용 할 때도 트랙패드만으로는 부족한 부분이 있어서 마우스도 연결해 함께 사용 하기 시작했습니다.

​	

다만 웹 브라우저를 사용 할 때, 마우스의 뒤로가기 버튼과 앞으로 가기 버튼이 먹히지 않는 것을 발견하고 해당 문제를 해결 했습니다.

Karabiner 어플리케이션을 사용해 설정 하는데요, `한영` 키 조작 때문에 macOS 를 사용하는 대부분의 분들은 Karabiner도 이미 설치 되어 있다고 생각 합니다.

혹시 Karabiner가 아직 설치 되어 있지 않다면 아래의 글을 먼저 확인 해 주세요.

> [Karabiner 설치해 맥북 한영키 설정하기.](https://shanepark.tistory.com/165)

​		

생각보다 간단하니 얼른 설정 해 보겠습니다.

<img src="https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/backAndForwardButton.assets/image-20211023102817120.webp" width=300 height=300 alt=main>

일단 메뉴바에서 Karabiner 를 확인 해 보면 Launch EventViewer 라는게 있습니다. 실시간으로 입력되는 이벤트들을 감지 해 주는 건데, 뒤로가기와 앞으로 가기 버튼을 눌렀을 때 어떤 키 입력이 되는지 확인 하기 위해 실행 합니다.

​	

![image-20211023103923780](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/backAndForwardButton.assets/image-20211023103923780.webp)

권한 설정 관련해서 팝업이 뜰 수 있는데 System Preferences > Security & Privacy > Input Monitoring 에서 Karabiner-EventViewer에 관련 권한을 부여 해야 인풋들을 해당 프로세스가 분석 할 수 있습니다.

​	

![image-20211023104324453](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/backAndForwardButton.assets/image-20211023104324453.webp)

아래에 스샷을 찍기 위해 입력 한 키 말고, 윗 부분을 보면 뒤로가기 버튼이 `button4` 그리고 앞으로 가기 버튼은 `button5` 임을 알 수 있습니다. 그럼 해당 버튼을 각각 뒤로가기와 앞으로가기 기능에 매칭 해 주면 됩니다.

​	

![image-20211023104434109](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/backAndForwardButton.assets/image-20211023104434109.webp)

Karabiner를 켜고 Complex modifications에 들어가면 `+ Add rule` 버튼이 있습니다. 클릭 해 줍니다.

​	

![image-20211023104519314](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/backAndForwardButton.assets/image-20211023104519314.webp)

뒤로가기는 `button4` 그리고 앞으로가기는 `button5` 였기 때문에 가장 위에 있는 기능을 `+ Enable` 시켜 줍니다. 

​	

>  그런데 아마 처음 여기 들어오시는 분들은 해당 목록들이 나오지 않을 텐데요. 위에 있는 `Import more rules from the internet`버튼을 클릭 한 후에 `Change mouse buttons` 를 검색 해서 rev2 써 있는 내용을 Import 하시면 저 처럼 위의 내용들이 나옵니다.

![image-20211023105143228](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/backAndForwardButton.assets/image-20211023105143228.webp)

​		

![image-20211023104555822](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/backAndForwardButton.assets/image-20211023104555822.webp)

추가가 되었지만 아직 끝난건 아닙니다.

​		

![image-20211023104641701](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/backAndForwardButton.assets/image-20211023104641701.webp)

마지막으로 사용하고 있는 디바이스를 체크 해 주어야 합니다. 사용하고 있는 G403 마우스가 목록에 나오네요.

이렇게 등록 한 후에는 마우스 뒤로가기, 앞으로가기 버튼이 잘 잘동하는 것을 확인 하실 수 있습니다.


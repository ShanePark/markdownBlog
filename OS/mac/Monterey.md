# MacOS) Monterey Public Beta 설치하기

​	

## Monterey

Monterey는 Apple이 WWDC21에서 발표한 macOS Big Sur의 차기 버전입니다. 

동명의 미국 California 주의 해변가에 있는 아름다운 도시의 이름을 따왔는데요, San Francisco Bay Area에서 차로 약 2시간 거리에 있는 대표 휴양지 입니다. Bir Sur 에서도 굉장히 가깝습니다.

​	

![image-20211009095609326](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009095609326.webp)

> https://en.wikipedia.org/wiki/MacOS_Monterey#Release_history

사실 Monterey의 첫번째 베타버전이 출시된지는 꽤나 시간이 지났습니다. 6월 7일이니 무려 4개월 전 인데요. 첫번째 베타부터 참여하기에는 불편함이 많을 것 같아 많이 기다렸는데, 이제 출시가 꽤나 임박했다는 생각이 들어 사용하기에 크게 불편함이 없을 거라는 생각이 들었고, 정식 출시 전에 미리 사용을 해서 Apple이 조금이라도 더 나은 소프트웨어를 개발하는데 보탬이 될 피드백을 주고 싶었습니다.

​	

## 주요 기능 및 개선 사항

FaceTime

- 공간 음향
- 인물 사진 모드
- 음식 분리 모드
- 와이드 스펙트럼 모드
- 격자 보기
- FaceTime 링크
- 캘린더 앱 호환
- 웹으로 FaceTime 참여 ( Apple 기기가 아니어도 사용 가능 )
- SharePlay: 함께보기
- SharePlay: 함께듣기
- SharePlay: 화면 공유하기

메시지

- 나와 공유된 항목
- 사진 컬렉션
- 간편한 사진 저장

Safari

- 간결해진 탭 막대
- 더 보기 메뉴
- 통합된 스마트 검색 필드
- 새롭게 디자인된 탭
- 탭 그룹
- 새롭게 디자인된 사이드바
- 새로운 개인 정보 기능
- HTTPS 업그레이드 ( Safari가 보안이 취약한 HTTP 사이트를 HTTPS를 지원하는 것으로 알려진 사이트로 자동 업그레이드 ) 

유니버설 컨트롤

- Mac과 iPad를 넘나들며 키보드, 마우스, 트랙패드 사용
- 기기 간 컨텐츠 드래그 앤 드롭
- 설정 없이 바로

AirPlay on Mac

- AirPlay로 콘텐츠 Mac에 전송
- 디스플레이 미러링 또는 확장 ( Mac을 보조 디스플레이로 활용해 iPhone이나 iPad의 디스플레이를 확장시킬 수 있습니다! )
- Airplay 2 스피커로 사용
- 유선 또는 무선으로 전송

실시간 텍스트

- 사진 속 텍스트를 interactive 방식으로 사용
- 시각 자료 찾아보기

iCloud+

- **iCloud Private Relay**: 기기 밖으로 전송되는 트래픽이 암호화 됩니다. 모든 요청은 두개의 분리된 인터넷 릴레이를 거쳐 전송되기 때문에 Apple을 포함한 그 누구도 사용자의 IP주소, 위치, 인터넷 브라우징 활동 등의 정보를 이용해 사용자에 관한 자세한 프로필을 생성할 수 없습니다.
- 나의 이메일 가리기
- HomeKit 보안 비디오
- 맞춤 이메일 도메인

그 외

- 새로운 집중모드

- 새로운 알림

- 개선된 메모

- 향상된 단축어

- 새로워진 지도
- 안녕하세요 화면 보호기
- find My AirPods
- 향상된 Finder
- 그 외 수많은 업데이트



여러가지 변경 사항이 있지만 그중에서도 **iCloud Private Relay**가 가장 매력적입니다. iCloud paid plan을 사용하고 있으면 그게 가장 저렴한 $0.99 요금제일 지라도 무료로 사용 할 수 있습니다. 이미 iPhone에서는 iOS15 업그레이드 후 잘 사용하고 있는데 사실 아이폰보다는 맥에서 그 활용도가 훨씬 큽니다. 업데이트를 서두르는 이유중 하나도 이 기능입니다.

> https://support.apple.com/en-us/HT212614

​	

## 설치 준비

아래의 Apple 베타 소프트웨어 프로그램 사이트를 통해 진행 하시면 됩니다.

https://beta.apple.com/sp/ko/betaprogram/welcome?locale=ko

​	

사이트에 방문하면

![image-20211009093214526](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009093214526.webp)	

제일 먼저 로그인을 해 주어야 하는데요. 로그인은 그냥 애플 계정으로 하시면 됩니다.

​		

![image-20211009093248186](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009093248186.webp)

처음 가입하면 약관에 동의해야 합니다.

​		

저희는 macOS Monterey를 설치합니다, 선택해줍니다.

![image-20211009093324876](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009093324876.webp)	

시작하기에 있는 `Mac을 등록 하십시오` 를 클릭 합니다.

​	

![image-20211009093534996](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009093534996.webp)

기기 등록 절차에 대해 나옵니다. Mac 백업하기는 아무래도 특별한 사정이 없다면 반드시 하는게 좋습니다. 필수는 아니지만 강력이 권장하는데는 이유가 있습니다. **베타 소프트 웨어**를 설치하고 있다는걸 명심하세요.

​			

Time Machine 백업에 시간이 제법 소요되니 제일 먼저 해줍니다. Spotlight (Cmd + Space) 를 켜서 Time Machine 이라고 입력 하면 바로 나옵니다. 저는 Alfred 를 사용중이긴 하지만 Spotlight 에서도 똑같습니다.

![image-20211009102134287](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009102134287.webp)

백업 데이터를 저장할 외장하드나 외장 SSD를 미리 연결 해둡니다.

​		

![image-20211009093443690](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009093443690.webp)

Time Machine을 켜면 간단한 설정만 하면 자동으로 백업을 시작합니다.

​		

타임머신 백업이 진행되는 동안 마저 할 일을 진행 하고 있으면 시간을 절약 할 수 있습니다. 

다음 순서인 Mac 등록하기를 진행합니다. macOS 공개 베타 액세스 유틸리티 다운로드를 클릭해서 다운 받습니다.

![image-20211009093547511](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009093547511.webp)

​		

그러고는 다운 받은 파일을 실행 해줍니다.

![image-20211009093700281](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009093700281.webp)

​	

![image-20211009093745098](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009093745098.webp)

Time Machine 백업이 없다고 경고를 합니다만 백업 중이기 때문에 크게 신경 쓰지 않아도 됩니다.

​		

지금 설치하는건 OS가 아니고 Public Beta Access 유틸리티 입니다.

![image-20211009093818753](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009093818753.webp)

Continue를 계속 눌러줍니다. 

​	

![image-20211009093840036](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009093840036.webp)

총 211 KB의 용량을 사용한다고 나오며 Install 버튼이 나옵니다.

​		

![image-20211009094027758](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009094027758.webp)	

> 설치가 완료되었습니다.

​	

이제 설치 후에는 자동으로 Feedback Assitant가 실행됩니다. 베타 사용을 하면서 개선 사항들을 바로바로 Apple에 보낼 수 있습니다. 우리 나라 기업들도 참 본받았으면 하는 내용이고 부럽기도 하네요. 전에 네이버에 한번 피드백을 주고 싶었는데 그 과정에 경악해서 그 때 이후로 다시는 피드백하지 않습니다. 반면 쿠팡은 확실히 피드백 창구가 잘 열려있더라고요. 피드백에 대한 답변도 확실합니다.

![image-20211009094004022](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009094004022.webp)

​	

그러고 바로 또 하나의 창이 뜹니다. 

![image-20211009094053586](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009094053586.webp)

>  macOS Monterey beta를 설치할 준비가 되었네요.

​		

하지만 아직 Time Machine 백업이 끝나지 않은 상황 인데요..

![image-20211009094226403](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009094226403.webp)

​		

타임머신 백업이 아직 진행중이지만 우리는 Upgrade Now를 미리 눌러줍니다. 무려 11.73 GB의 새로운 OS를 다운 받는데도 시간이 꽤나 걸리기 때문이죠. 500Mbps/s 의 인터넷 서비스 기준으로  다운로드 받는데 약 20 분 정도 걸렸습니다.

​	

![image-20211009094630062](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009094630062.webp)

혹시 다운로드가 먼저 끝나서 타임머신 백업이 제대로 이루어 지지 못할까 걱정은 하지 않으셔도 됩니다. 다운로드가 끝난다고 해서 자동으로 바로 install이 되지는 않습니다.

​		

![image-20211009102918713](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009102918713.webp)

타임머신 백업이 끝났습니다.

​	

## Monterey 설치

​	

![image-20211009102944555](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009102944555.webp)

​	Continue를 누르고 몇 가지 약관에 동의 하고

​	

![image-20211009103032990](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009103032990.webp)

설치할 드라이브를 선택 해 줍니다.

​	

![image-20211009103102400](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009103102400.webp)

그러면 설치가 시작 되는데요 m1 맥북에어 기준으로 10:30 AM 에 시작 했는데요.

​		

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009103917808.webp)

8분이 지난 10:38 AM애 확인 하니 1분이 남았다고 나옵니다. 처음에 한시간 걸린다고 나왔다고 너무 겁먹지 않아도 됩니다.

​		

![image-20211009104232938](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009104232938.webp)

딱 10분이 걸리고, Restart 버튼이 나왔네요.

​		

![image-20211009110629482](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009110629482.webp)	

이후 재부팅 하며 설치는 총 20분 가량 소요되어 전체 설치에는 30분이 걸렸습니다.

막상 부팅 되고 나니 외관상으로는 큰 차이가 없어보여 업데이트가 된게 맞나 싶기도 합니다.

​	

![image-20211009130016688](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009130016688.webp)

이제 설정에 들어가보면 Private Relay가 생겼습니다.

​		

![image-20211009130251074](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/Monterey.assets/image-20211009130251074.webp)

설정을 켜고 Safari로 브라우징을 해보니 ip도 vpn을 사용하는 것 처럼 숨겨집니다. 다른 브라우저를 켤 때는 적용이 안되고 Safari를 할 때만 되더라고요. 다만 항상 켜두기에는 ping이 높아지고 다운로드 속도가 느려지기 때문에 필요할 때만 사용하는게 좋겠습니다.

​	

그 외에도 많은 새로운 기능들을 둘러보는 재미가 있습니다. 딱히 베타버전의 불편함은 아직까지 느끼지 못했습니다. 이상입니다.

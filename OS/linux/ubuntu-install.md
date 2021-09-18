# Ubuntu 20.04LTS 설치하기 



[우분투 Ubuntu 설치 USB 만들기 및 windows 멀티부팅 셋팅](https://shanepark.tistory.com/229) 에 이어지는 글 입니다.

​	

​		

## USB로 부팅하기

![IMG_3200](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3200.jpeg)

윈도우에서 간단하게 USB로 부팅하는 기능이 있기는 하지만 보통의 경우 BIOS 설정에서 부팅 우선순위를 변경해줍니다.

제가 Ubuntu 설치 디스크로 만든 SanDisk Ultra USB 를 제일 위의 우선순위로 올려 둡니다. 

​	

그러고 나서 설정을 저장 하면 자연스럽게 USB로 부팅이 진행됩니다.

​	

![IMG_3224](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3224.jpeg)

Ubuntu 를 선택 해 줍니다.

​		

![IMG_3225](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3225.jpeg)	

그러면 이제 Checking disks를 하게 되는데요. 이게 아마 설치 USB를 검사 하는 것 같습니다. 시간이 제법 걸리던데 처음 할 때는 검사를 끝까지 했었는데요. 이게 USB가 잘 만들어 졌다는 확신이 잇으면 Ctrl + C 를 입력해서 검사를 건너 뛸 수도 있습니다.

​		

![IMG_3201](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3201.jpeg)

Welcome 이 나옵니다. 좌측에서는 사용할 언어를 선택 하실 수 있구요. Try Ubuntu 와 Install Ubuntu 메뉴가 나옵니다. 언어를 선택 한 후에 우리는 Ubuntu를 설치 할 것 이기 때문에 Install Ubuntu 버튼을 눌러 줍니다.

​	

혹시 이전글을 보고 RTS 설정을 해지하지 않았다면 아래와 같이 **Turn off RST** 오류를 만날 수 있습니다.

![IMG_3202](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3202.jpeg)

​		

그럴때는 당황하지 않고 BIOS 로 들어가서 SATA Operation 설정을 RAID On 에서 AHCI 로 변경 해 주면 됩니다.

![IMG_3203](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3203-1948050.jpeg)

하지만 사용하고 있는 Windows와 멀티 부팅을 설정 할 예정이라면 무작정 AHCI 로 변경하지 마시고 먼저 아래 글을 참고 해 주세요.

아래와 같이 하지 않으면 나중에 Ubuntu를 설치 하고 나서 Windows를 사용 할 수 없게 될 수도 있습니다. 물론 완전 날아가는건 아니여서 복구는 가능 한데, 번거롭기 때문에 설정을 미리 잘 해주세요.

[우분투 Ubuntu 설치 USB 만들기 및 windows 멀티부팅 셋팅](https://shanepark.tistory.com/229) 

​		

Keyboard layout은 Korean 한국어로 설정 해 줘야겠죠. 

가끔 iBus 에러가 발생해서 설치과정에 입력이 안될 수도 있으니 Keyboard layout도 English로 해서 설치 한 후에 나중에 한글입력을 추가 하는 것도 괜찮습니다.

![IMG_3228](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3228.jpeg)

​		

​	와이파이가 사용 가능하다면 와이파이도 연결 해 줍니다.

![IMG_3229](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3229.jpeg)

​	

![IMG_3204](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3204.jpeg)

Updates and other software 에 대한 설정인데요. 필요한 software 들을 다운 받도록 위와 같이 설정 해줍니다.

참 ! 필요한 소프트 웨어 알아서 설치해서 쓸 분들은 Normal installation 하지 말고 Minimal installation 하시는 걸 추천 드립니다. 

​	

## Installation type

![IMG_3205](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3205.jpeg)	

어떻게 설치 할 것인지에 대한 선택 입니다.

첫번째인 install Ubuntu alongside Windows Boot Manager는 무난하게 같이 쓰기 좋아 보이네요. 

두번째인 Erase disk and install Ubuntu 는 기존에 있던 모든 파티션도 다 날려 버립니다. Windows를 썼다면 복구 파티션도 날려버리기 때문에 잘 생각해서 선택해주세요. 저는 집에서 서버용으로 사용하는 컴퓨터는 다 밀어버리기 위해 두번째를 선택을, 업무용 노트북은 세번째 옵션인  Something else를 선택해서 진행 했습니다.

​	

세번째 옵션으로 쭉 진행 해 보겠습니다.

![IMG_3206](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3206.jpeg)

미리 만들어 둔 공간을 이제 partition으로 할당 해 줍니다. Logigal / Beginning of this space 로 설정 하고 Mount point는 루트(/) 로 파티션을 생성 했습니다.

​	

![IMG_3207](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3207.jpeg)

그러고 이제 방금 만든 Mount point / 의 파티션은 Forat? 에 v 체크를 해 주고요, 제일 위에 있는 /dev/드라이브이름 을 선택해 Install Now 버튼을 클릭 합니다.

​	

![IMG_3208](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3208.jpeg)

그러면 이제 아래의 파티션에 변화가 생긴다는 경고가 나옵니다. 잘 읽어 보고 Continue를 눌러 진행 합니다.

​		

![IMG_3231](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3231.jpeg)

시간대는  Seoul로 설정 해 주고 컴퓨터 이름과 유저 이름을 설정하는 화면도 나오는데 본인에 맞게 설정 해 줍니다.

​	

![IMG_3232](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/IMG_3232.jpeg)

그러면 본격적으로 설치가 진행 됩니다.

​	

설치가 완료 된 후에는 재부팅을 하게 되는데요, 그 때 기존의 설치 USB를 빼 줍니다.

​	

![Screenshot from 2021-09-18 15-22-35](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu-install.assets/Screenshot from 2021-09-18 15-22-35.png)

​	

설치가 완료 되었습니다 !

수고하셨습니다.

​	
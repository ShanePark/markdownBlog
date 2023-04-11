# Ubuntu 20.04 원격 데스크탑 사용하기

​	

Ubuntu를 사용하다 보면 ssh 접속을 이용해 굉장히 편하게 사용을 할 수 있는데요. 가끔 SSH 로는 충분하지 않아서 GUI로 외부에서 접속해서 사용 해야 할 일이 생길 때가 있을 수 있습니다.

그럴때를 위해서 따로 프로그램을 설치 해 두자니 기분도 찝찝하고 뭔가 설치한다는 행위 자체가 미니멀리스트 들에게는 썩 유쾌하지 않은 경험입니다. Ubuntu나 MacOS 혹은 Windows 모두 자체적으로 원격 데스크톱 접속을 할 수 있는 기능을 내장 하고 있기 때문에 이미 있는 기능들만 잘 사용한다면 원격접속도 해낼 수 있습니다. 지금부터 저와 하나씩 설정을 함께 해 보겠습니다.

​		

## Ubuntu 설정

​	

Sharing 을 검색 해서 실행 합니다.

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/1.webp width=750 height=500 alt=1>

​	

여기에서 Screen Sharing 이 보이는 분도 안보이는 분도 있을 겁니다. 저도 집에 있는 ubuntu 중에 처음 할 때는 있어서 기본으로 있는 줄알았는데 두번째 컴퓨터의 셋팅을 할 때 보니 처음에 없더라고요.

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/2.webp width=750 height=500 alt=2>

​	

### Screen Sharing 이 없으면

Screen sharing이 이미 보인다면 아래로 쭉 내리시면 됩니다.

```bash
sudo apt update
```

일단 apt를 업데이트 해줍니다.

​	

```bash
sudo apt install vino
```

이제 vino를 설치 해 줍니다. vino는 GNOME desktop 환경의 vnc server 입니다. 지금은 gnome-remote-desktop 을 권장하는 차원에서 기본으로 설치되어 있지 않은 듯 합니다만 Client건 Server건 어디에도 최소한의 설치로 빠르게 원격 접속 환경을 구축을 하려면 제가 지금 포스팅 하는 내용대로 하시면 됩니다. 정말 간단하게 할 수 있어요.

​	

vino는 493 kB 밖에 안되어서 정말 금방 설치 할 수 있습니다.	

![3](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/3.webp)

​	

설치 후에 다시 Sharing에 들어가 보면

![4](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/4.webp)

이제는 Screen Sharing을 볼 수 있습니다.

​	

좌측 상단의 스위치를 켜서 On 으로 바꿔줍니다.

또한 New connection must ask for access가 기본 설정으로 되어 있는데요, 그럴 경우에는 해당 서버에서 클라이언트의 접속 요청에 승낙을 해야 만 원격조정을 할 수 있기 때문에 아래의 Require a password 를 눌러서 비밀번호를 정해 줍니다. 그러면 따로 허락을 구하지 않고 접속 할 수 있습니다.

![5](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/5.webp)

​	

###  /org/gnome/desktop/remote-access/require-encryption 옵션 끄기

혹시 아직  dconf Editor가 없는 분들은 설치 해주세요.

```bash
$ sudo apt-get install dconf-editor
```

![6](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/6.webp)

​		

dconfEditor를 켜고  /org/gnome/desktop/remote-access/require-encryption 에 찾아가 설정을 off로 변경해줍니다.![7](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/7.webp)

​	

이제 모든 준비는 끝났습니다. 접속을 해 보도록 하겠습니다.

​	

## 원격에서 접속

### 1. MacOS 에서 접속

맥북에서는 기본적으로 vnc 뷰어가 내장이 되어 있기 때문에 따로 프로그램을 설치 하지 않아도 됩니다.

Command+K 를 입력하거나 상단 메뉴바의 GO(이동) > Connect to Server..(서버에 연결)을 누릅니다.

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/36.webp)

​	

그리고는 vnc://주소 를 입력합니다. 당연히 내부 아이피도 이용 가능하고, Port Forwarding을 해 두었다면 외부 아이피로도 접속이 가능합니다. ipTime 공유기를 사용한다면 192.168.0.1에서 Advanced Setup > NAT/Routing > Port Forwarding 에서 변경 할 수 있으며 vnc은 5900번 포트를 사용합니다.

![image-20210922174940764](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/image-20210922174940764.webp)

​	

이제 정말 접속 해 봅니다.

![image-20210922174906671](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/image-20210922174906671.webp)

​	비밀번호를 입력 하라고 합니다.

![image-20210922175039200](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/image-20210922175039200.webp)

​	

그래서 비밀번호를 잘 입력 하면 .. 해당 컴퓨터로 접속이 됩니다.

![image-20210922175143018](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/image-20210922175143018.webp)

​	

### 2. Ubuntu 에서 Ubuntu로 접속

Ubuntu 에서는 내장 패키지인 리미너 원격 데스트탑 클라이언트를 사용 하는데요, 혹시 없다면 설치해줍니다.

```bash
sudo apt install remmina
```



![8](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/8.webp)

​	

아마 보통의 경우는 설치가 되어 있을 거에요. 실행 해 줍니다.

![9](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/9.webp)

​	

처음 실행 하면 아래와 같은 모습인데요

![10](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/10.webp)

​	

RDP 를 VNC 로 변경 한 후 접속할 아이피 주소를 입력 합니다.

![11](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/11.webp)

​	

그러면 비밀번호를 입력 하라고 나옵니다. 비밀번호를 입력 하면..

![12](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/12.webp)

​	

역시 바로 쉽게 접속이 됩니다.

![13](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/vnc.assets/13.webp)

​	

### 3. Windows에서 접속

Windows 는 아쉽게도 내장된 VNC 프로그램이 없습니다. VNC 뷰어를 다운 받아야 하는데요. Chrome의 VNC Viewer를 활용하는게 가장 흔했던 것 같은데 요즘 찾아보니 공식 VNC이 사라졌더라고요. Chrome 도 점점 뭔가 Apple 처럼 폐쇄적인 방향으로 변해하고 있는게 아닌가 싶습니다. 최근엔 Hola VPN도 퇴출되었더라고요. 

https://www.realvnc.com/en/connect/download/vnc/

위의 링크에서 Windows 용 vnc 뷰어를 다운받아서 하시면 됩니다. googledp vnc viewer를 검색했을 때 가장 위에 나오기 때문에 신뢰 할 수 있는 소프트 웨어 인 듯 합니다.

제가 Windows 디바이스가 없어서 스크린샷은 생략 하도록 하겠습니다.

​	

지금까지 Ubuntu의 vnc 접속에 대해 알아보았습니다. 

생각보다 설치할 게 없었으며 특히 Mac에서 Ubuntu 로의 접속은 너무나도 간단하고 편리 했습니다. 이상입니다.
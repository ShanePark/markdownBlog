# 우분투 Ubuntu 설치 USB 만들기 및 windows 멀티부팅 셋팅



## 우분투 설치용 iso 다운로드

https://ubuntu.com/download/desktop

<img src="https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210917194545200.webp" width=750 height=450 alt="first">

위의 공식 홈페이지에서 다운 받을 수 있습니다.

저는 업무에서 사용하려다 보니 안정성을 위해 20.04 LTS 버전을 선택 했는데요, 21.04 버전도 비슷하게 많이 사용되고 있기 때문에 선택하서도 무방할 듯 합니다.



>  아래의 카카오 mirror 를 이용해서 다운 받으셔도 됩니다. 

http://mirror.kakao.com/ubuntu-releases/20.04/

![image-20210916083806146](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916083806146.webp)



## Rufus 다운로드 및 실행

Ubuntu 설치 iso 이미지를 다운 받는동안 가만히 기다리지 않고 계속 할일을 하도록 하겠습니다.

역시 공식 홈페이지를 이용하면 됩니다. 1.1MB로 금방 다운 받습니다.

https://rufus.ie/en/

![image-20210916083730471](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916083730471.webp)



## 설치 USB 만들기

다운받은 Rufus를 실행 하면 다음과 같은 화면이 나옵니다. "장치" 에서 우분투 설치 USB를 만들 장치를 선택 합니다.

![image-20210916083841197](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916083841197.webp)

​	

이제 위에서 다운받은 이미지 선택을 해줍니다.

![image-20210916083906106](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916083906106.webp)

​	

부트 선택 에 이미지가 선택이 된 것을 확인 합니다.

![image-20210916083925405](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916083925405.webp)

​	

"시작" 버튼을 눌러 디스크를 만듭니다.

​	

ISOHybrid 이미지가 감지 되어 ISO 혹은 DD 이미지 모드로 쓸 수 있다고 하는데, ISO 이미지 모드 사용을 권장 한다고 합니다. 바로 OK 를 선택합니다.

![image-20210916083954050](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916083954050.webp)



​	필요한 파일을 다운 받는다고 하니 "예"를 눌러줍니다.

![image-20210916084003021](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916084003021.webp)

​	

​	이제 설치 USB를 만듭니다. 포맷을 진행 하기 때문에 백업할 데이터가 있다면 미리 해줘야 합니다.

![image-20210916084015885](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916084015885.webp)

이제 확인 버튼을 클릭 하면 쓰기 작업이 시작되며 꽤 오랜 시간이 걸립니다. 저는 3분 가량 걸렸습니다.

쓰기 작업을 하는 동안 준비 작업을 이어서 하겠습니다.

​		

## USB 부팅을 위한 세팅하기

윈도우의 빠른 시작 설정때문에 USB로 부팅이 안 될 수 있습니다. 해당 설정을 변경해줍니다.

​	

제어판에 들어가서

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916084256538.webp)

​	

전원 및 절전 메뉴에 들어갑니다. 	

![image-20210916084317997](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916084317997.webp)



그리고 우측의 추가 전원 설정에 들어가면 아래와 같은 화면이 나옵니다. 전원 단추 작동 설정을 클릭합니다.

![image-20210916084337268](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916084337268.webp)

​	

여기에서 "빠른 시작 켜기(권장)" 을 꺼 줘야 합니다. 위쪽에 파랗게 써있는 **현재 사용할 수 없는 설정 변경** 버튼을 클릭합니다.

![image-20210916084410018](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916084410018.webp)

​	

빠른 시작 켜기 체크를 해제해줍니다.

![image-20210916084422302](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916084422302.webp)

​	

## 파티션 나누기

![image-20210916084434504](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916084434504.webp)

USB 쓰기 작업이 모두 끝났습니다. 저는 3분 11초 걸렸네요.

​	

USB를 확인해보니 이런식으로 설치 관련 파일들이 들어가 있습니다.

![image-20210916084502992](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916084502992.webp)

​	

이제 저희는 Ubuntu 설치를 위한 파티션을 할당 해 보겠습니다.

윈도우 + R 을 누르고, 

```
diskmgmt.msc
```

를 입력합니다.

![image-20210916084545536](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916084545536.webp)

​	

디스크 관리 화면이 나옵니다.

여기에서 본인이 원하시는 대로 디스크를 나누시면 됩니다. 저는 총 500GB의 용량을 Windows와 Ubuntu 에 각각 절반씩 할당 해 두었지만 Ubuntu에 개발환경이 준비되는 대로 Windows의 할당량을 서서히 줄일 예정입니다. 용량을 할당 까지 하면 안되고, 미할당 상태로 두었다가 Ubuntu 설치할 때에 할당해줍니다.

![image-20210916084602661](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916084602661.webp)

​	

## 중요) SSD 설정을 RAID 에서 AHCI 로 변경

Intel Rapid Storage Technology 설정이 기본으로 되어있어서 제가 고생을 했습니다. Ubuntu를 설치할 때에는 SATA 설정을 AHCI로 해 두어야 하는데, RAID 로 되어 있어서 Ubuntu 설치가 되지 않았습니다. 그래서 별 고민 없이 BIOS 설정에 들어가서 RAID를 AHCI로 변경 해 주었는데 그렇게 하니 Windows로 부팅이 되지 않았습니다.

Windows 를 사용하지 않을 예정이거나 포맷 해버릴 것 이라면 상관 없지만 지금 설치되어 있는 Windows를 한동안 더 사용할 생각이 있다면 Windows에서도 설정을 AHCI로 변경 해 주어야 합니다. RAID -> AHCI 에서 다시 AHCI -> RAID 로 해서 아래 설정을 해 주었습니다. 아래의 설정이 끝나고 나서 RAID -> AHCI로 변경하시면 됩니다.

​	

CMD를 관리자 권한으로 실행 합니다.

![image-20210916005719257](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916005719257.webp)

​	

아래의 명령어를 입력 합니다.

```
bcdedit /set safeboot minimal
```

​		

![image-20210916010245843](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916010245843.webp)



이제 컴퓨터를 끄고 AHCI로 설정을 변경 해 줍니다. BIOS에 진입해서 하면 됩니다.

이제 다시 켜면 안전모드로 부팅됩니다.

​			

![image-20210916010535623](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916010535623.webp)

​	

아까처럼 CMD 를 관리자 권한으로 실행 합니다.

![image-20210916010626995](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916010626995.webp)



이번에는 아래의 명령어를 입력 합니다.

```
bcdedit /deletevalue safeboot
```

![image-20210916010700691](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/install.assets/image-20210916010700691.webp)

​	

이로써 Windows 에서의 멀티부팅을 위한 모든 설정과 Ubuntu 설치 USB 만들기를 마쳤습니다.

Ubuntu 설치 과정은 아래 글에서 이어집니다.

[Ubuntu 20.04LTS 설치하기](https://shanepark.tistory.com/230)


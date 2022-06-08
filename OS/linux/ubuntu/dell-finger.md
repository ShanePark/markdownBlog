# Ubuntu 20.04 Dell XPS 노트북 지문인식 로그인하기



reference

> https://mrcoffee.io/blog/setting-up-fingerprint-reader-ubuntu-20-and-dell-xps

이번에 Windows 에서 Ubuntu 로 넘어 오면서 불편한 점 중 하나가 로그인 할 때 암호를 치는 번거로움 이었습니다.

그래서 해당 기능을 추가할 수 있을까 궁금해 여기저기 찾아 보다가 결국 성공해 정보를 나눕니다.

​	

일단 지문 인식 Device가 Ubuntu에서 인식이 되는지 찾아보았습니다.

https://linux-hardware.org/?id=usb:27c6-5395&hwid=401e4e7bdfc4

![image-20210918183843289](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/dell-finger.assets/image-20210918183843289.png)

​	

Detect가 되니 가능성이 있네요. 지금부터 해보겠습니다.

​	

![1](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/dell-finger.assets/1.png)

일단 Settings의 Users에 가면 지문인식 설정을 할 수 없는게 확인 됩니다.

​		

일단 fprintd가 없다면 설치를 해줍니다. 저는 이미 있었네요.

![2](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/dell-finger.assets/2.png)

​	

```bash
# 1. 처음엔 이쪽 명령을 실행
echo "
deb http://dell.archive.canonical.com/updates/ focal-dell public
deb http://dell.archive.canonical.com/updates/ focal-oem public
deb http://dell.archive.canonical.com/updates/ focal-somerville public
deb http://dell.archive.canonical.com/updates/ focal-somerville-melisa public" | \
sudo tee -a /etc/apt/sources.list.d/focal-dell.list

# 2. Add the Dell repository key (F9FDA6BED73CDC22) to apt
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys F9FDA6BED73CDC22
sudo apt update

# 3. Install fingerprint reader packages
sudo apt install -y \
    oem-somerville-melisa-meta \
    oem-somerville-meta \
    libfprint-2-tod1-goodix \
    tlp-config
```

이제 위의 명령어들을 위에서부터 세번에 걸쳐 나눠 실행 합니다.

​	

열심히 필요한 패키지들이 설치 됩니다.

![Screenshot from 2021-09-18 19-56-11](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/dell-finger.assets/3.png)

설치를 마쳤으니 이제 확인 해 보겠습니다.

​	

Setting- Users에 다시 들어가봅니다.

![Screenshot from 2021-09-18 19-56-41](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/dell-finger.assets/4.png)

​	

Fingerprint Login이 추가 되었습니다 !! 우측 상단의 Unlock을 눌러 해지하고

Ringerprint Login에 Disabled > 된 것을 클릭 합니다.

> 여기에서 driver를 찾을 수 없다는 에러가 나온다면 재부팅을 한번 해 주어야 합니다. 최초 등록시 재부팅이 필요할 수 있습니다.

이제 Scan new fingerprint 버튼을 선택 합니다.

![Screenshot from 2021-09-18 19-57-58](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/dell-finger.assets/5.png)

​		

이제 등록하고자 하는 손가락을 선택 하구요

![Screenshot from 2021-09-18 19-58-10](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/dell-finger.assets/6.png)

​	

지문 리더에 손가락을 올리라고 하네요. 손가락을 한번씩 여러번 올리면 점점 게이지가 찹니다.	

![Screenshot from 2021-09-18 19-58-29](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/dell-finger.assets/7.png)

​	

총 열번 정도 손가락을 떼었다 붙였다 하면 등록이 완료됩니다.

![Screenshot from 2021-09-18 19-58-56](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/dell-finger.assets/8.png)

이제 화면을 잠시 잠궈보겠습니다.

​		

이제는 비밀번호를 입력 하거나 지문을 입력하라는 메시지가 나오네요.	

![IMG_3239](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/dell-finger.assets/IMG_3239.jpeg)

​	

손가락을 찍어 보도록 하겠습니다.

​	

![IMG_3241](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/dell-finger.assets/IMG_3241.gif)

​	

비밀번호를 입력 하지 않았지만 지문인식만으로 로그인이 됩니다 ! 
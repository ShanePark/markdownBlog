# 우분투에서 아이폰 / 아이패드 미러링 하기

​	 

사실은 양방향 미러링을 하는 방법을 시도 해 보고 있었는데, 스크린 캐스트를 통해 단방향 미러링을 먼저 성공 하게 되었습니다. 반응이 굉장히 빠르기 때문에 분명 여러가지 유용하게 사용 될 것 같아 포스팅을 정리해 두려 합니다.

https://rodrigoribeiro.site/2020/08/09/mirroring-ipad-iphone-screen-on-linux/

어느 교수님이 학생들에게 좀 더 좋은 원격 강의를 제공하려고 시도하다 성공하였다고 포스팅을 해주셨고 저도 도움을 받았습니다.

​		

## 필요한 패키지 받기 

```bash
sudo apt-get install cmake
sudo apt-get install libssl-dev libavahi-compat-libdnssd-dev libgstreamer1.0-dev libgstreamer-plugins-base1.0-dev gstreamer1.0-libav

# 마지막 줄은 인텔 그래픽을 쓸 경우에만 해주세요 저는 필요 없었습니다
sudo apt-get install gstreamer1.0-vaapi

```

​			

## UxPlay 설치

https://github.com/antimof/UxPlay 저장소를 통째로 클론 해 와야 합니다.

```bash
git clone https://github.com/antimof/UxPlay.git
```

​		

그러고는 해당 UxPlay 폴더에 가서 아래의 커맨드들을 입력 합니다.

```bash
mkdir build
cd build
cmake ..
make

```

​		

빌드가 완료 되었습니다.

![image-20211001220128420](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/iphone.assets/image-20211001220128420.png)

​			

빌드가 완료 되었으니 이제 실행 합니다. build 경로에서 실행 해 주세요.

![image-20211001220226724](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/iphone.assets/image-20211001220226724.png)	

이제 리눅스에서의 준비는 끝났습니다.

![image-20211001222359064](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/iphone.assets/image-20211001222359064.png)

​	

## 아이폰 / 아이패드 미러링

제어센터를 열어 줍니다. 그러고 스크린 미러링을 눌러주는데요, 블루투스 아이콘 아래의 모니터 2개 모양이 스크린 미러링 입니다.

<img src="https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/iphone.assets/IMG_3362.PNG" alt="IMG_3362" style="zoom:150%;" />

미러링에 뜬 UxPlay를 눌러줍니다.

![IMG_3363](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/iphone.assets/IMG_3363.PNG)

선택을 하면 체크 모양이 나옵니다.

![IMG_3364](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/iphone.assets/IMG_3364.PNG)



이제 바로 우분투에 화면이 떠야 하는데 저는 raop_ntp receive timeout 에러가 뜨더라고요 그럴 경우에는

https://rodrigoribeiro.site/2021/03/01/mirroring-ipad-iphone-screen-on-linux-solutions-to-known-issues/ 여기에 몇가지 해결 책이 있는데요, 저는 아래 plugin을 설치 하니 바로 되었습니다.

```bash
sudo apt-get install gstreamer1.0-plugins-bad
```

​	

이후 미러링이 잘 진행됩니다. 리눅스에서 gif로 녹화하며 화질이 안좋아 보이지만 사실 화질도 괜찮게 나옵니다. 반응속도도 정말 빠르고요.

![peek](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/iphone.assets/peek.gif)

​	

사용하기에 따라 정말 유용하게 사용할 수 있을 것 같습니다. 이상입니다.
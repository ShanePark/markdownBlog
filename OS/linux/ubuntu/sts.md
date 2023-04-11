# Ubuntu) STS4 (Spring Tools Suite 4 for Eclipse) 설치하고 바로 가기 만들기

​		

Ubuntu에서 tar.gz 파일을 다운 받아서 설치하는건 STS를 설치 할 때 처음 겪었습니다. 

앞으로 자주 겪을 일이고 하니 한번 정리 해 보았습니다.

​		

일단 설치 파일을 먼저 다운 받습니다.	

https://spring.io/tools

![image-20210919094725557](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/sts.assets/image-20210919094725557.webp)

​			

금방 다운로드가 됩니다.

![image-20210919094742699](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/sts.assets/image-20210919094742699.webp)

​	

tar.gz 파일을 받았으니 압축을 풀어줘야 겠네요. Ctrl + Alt + T 를 눌러 Terminal 을 켭니다.	

​	

Downloads 폴더에 spring-tool-suite-4-4.12.0.RELEASE-e4.21.0-linux.gtk.x86_64.tar.gz 이 있습니다.

![image-20210919095733028](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/sts.assets/image-20210919095733028.webp)	

​		

압축을 풀겠습니다. 

​		

```bash
tar -zxvf spring-tool-suite-4-4.12.0.RELEASE-e4.21.0-linux.gtk.x86_64.tar.gz
```

![image-20210919095948902](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/sts.assets/image-20210919095948902.webp)

​	

압축이 다 풀렸습니다. sts-4.12.0.RELEASE/ 라는 폴더 이름으로 압출이 풀어졌네요.

![image-20210919100232933](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/sts.assets/image-20210919100232933.webp)

​	

Downloads 보다는 적당한 위치에 놓는게 좋으니 lib 폴더로 이동시키겠습니다.

```bash
sudo mv sts-4.12.0.RELEASE/ /lib
```

​			

이제 /lib/sts-4.12.0.RELASE 폴더에 STS가 위치 했습니다.

​	![image-20210919101520661](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/sts.assets/image-20210919101520661.webp)

​	

SpringTollSuite4 를 실행 할 준비가 되었습니다. 아래 명령어를 이용 해 켜면 실행이 됩니다.

```bash
./SpringToolSuite4
```

![image-20210919102430459](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/sts.assets/image-20210919102430459.webp)

다만 deprecated 된 방법 이라고 경고합니다.

![image-20210919102538759](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/sts.assets/image-20210919102538759.webp)

그래도 실행은 잘 되었습니다.

​	

이제 dock에 바로가기 아이콘을 추가 해 보도록 하겠습니다.

Ubuntu 20.04 기준으로 Application 들 목록은 /usr/share/applications/ 경로 입니다.

```bash
cd /usr/share/applications/
```

​	

이제 이 경로에 .desktop 파일을 생성 해 주어야 합니다.

```bash
sudo vi sts4.desktop
```

​	

그래서 아래의 내용을 본인의 상황에 맞게 편집 해 붙여 넣어주세요.

```bash
[Desktop Entry]
Name=sts-4.12.0.RELEASE
Exec=/lib/sts-4.12.0.RELEASE/SpringToolSuite4
Comment=sts-4.12.0.RELEASE
Terminal=false
Type=Application
Icon=/lib/sts-4.12.0.RELEASE/icon.xpm
```

​		

​	![image-20210919103212520](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/sts.assets/image-20210919103212520.webp)

만약 저장하려는데 위와 같이 저장이 되지 않으면 권한 문제 입니다. 꼭 sudo 로 실행해주세요.

​		

이제 Application 목록에 가서 Frequent 말고 All 을 누르면 Spring Suite 4 가 등록 되어 있는게 확인 됩니다!

![image-20210919103426136](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/sts.assets/image-20210919103426136.webp)

​	

이제는 우클릭을 하고 Add to Favorites를 선택하면 간단하게 Dock 에 추가 할 수 있습니다.

![image-20210919103506402](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/sts.assets/image-20210919103506402.webp)

​	

이상입니다.

만약 아래와 같이 실행을 했을 때 Dock에 아이콘이 중복으로 2개 되는 현상이 생긴다면

![image-20210919103631480](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/sts.assets/image-20210919103631480.webp)

​	

아래 글을 참고해서 해결하시면 됩니다.

[Ubuntu ) 실행시 Dock에 아이콘 현상 중복 해결](https://shanepark.tistory.com/235)

​	

이상입니다. 수고하셨습니다.
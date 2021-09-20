# 개발자를 위한 Ubuntu 초기 설정 하기	

​	

![How To Install Ubuntu Linux inside Windows - Techi Signals](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/Install-Ubuntu-Linux.jpg)	

[개발자를 위한 Mac 초기 설정 하기](https://shanepark.tistory.com/167) 포스팅을 작성한지 두달이 조금 넘었습니다. Mac을 처음 구입하시는 분들이 처음에 세팅을 한번에 하기 위해 방문을 많이 해주시다 보니 보통 매일 블로그 내 인기 글 3등 안에는 항상 들어가는 포스팅 인데, 다른 분들에게는 도움이 얼마나 되는 지 잘 몰라도 적어도 저에게는 처음부터 세팅을 다시 해야하거나 다른 분들의 세팅을 도와 줄때마다 다시 찾아보게 되어 스스로 큰 도움이 되고 있습니다. 처음 Ubuntu 를 설치 하며 어려움을 겪었었고, 후에도 집에서 개인 서버로 사용하던 윈도우 노트북도 윈도우를 아에 밀어버리고 우분투로 바꿔 보고, 또 스스로도 잘못 건들었다가 다시 설치하는 등 시행착오를 몇 번 겪다 보니 정리를 해 두는게 좋다는 생각이 들어 하나 둘 씩 정리하기 시작했습니다. 

​	

아래의 내용 중 본인에게 필요한 내용만 순서에 맞춰 설치하시면 됩니다.

>  필수에 가깝다고 생각이 드는 항목들은 따로 앞에 ***** 표시를 해 두었습니다.

​			

## * 한글 키보드 입력 설정

> 보통 키보드 설정을 제일 먼저 합니다. 아래 글을 참고 해주세요.

[Ubuntu 20.04 키보드 한글 입력 설정 하기](https://shanepark.tistory.com/231)

​	

## Dock 커스터마이징

> 저는 키보드 설정 후에는 보통 dock을 입맛에 맞게 설정합니다. 아래 글을 참고해주세요.

[Ubuntu 20.04 Mac OS 처럼 Dock 가운데 위치하게 변경하기](https://shanepark.tistory.com/233)

​		

회사에서는 노트북 볼륨을 꺼 두고 써서 몰랐는데, 우분투에서의 비프음이 굉장히 거슬리더라고요. 해당 비프음도 꺼줍니다.

[Ubuntu Terminal에서 백스페이스/방향키 누를때 삐 하는 비프음 안나게 하기](https://shanepark.tistory.com/234)



## * JDK 설치 

![black and silver laptop computer on table](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/photo-1517694712202-14dd9538aa97ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80)

> 각자 필요한 버전에 맞춰 8 대신 넣으면 됩니다. 해당 명령어로 설치가 끝납니다.

```bash
$ sudo apt-get install openjdk-8-jdk
```

​	

## 노트북 지문인식 등록

![round black and white light](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/photo-1585079374502-415f8516dcc3ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80)

>  필요한 분만 하면 됩니다. 솔직히 굳이 하지 않아도 되는 기능이긴 합니다. 아래 링크를 참고해주세요.

[Ubuntu 20.04 Dell XPS 노트북 지문인식 로그인하기](https://shanepark.tistory.com/232)

​	

## GitKraken

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/gk-product-2.png)

SourceTree의 경우 Linux 버전이 없어 GitKraken 이 대안으로는 가장 괜찮아 보였습니다.

물론 CUI 를 사용하는게 기능이 훨씬 강력하기는 하지만 Git의 진입 장벽을 생각할 때 GUI 를 거치지 않기는 쉽지 않습니다.

**snap**

```bash
sudo snap install gitkraken --classic
```

**.deb**

```bash
wget https://release.gitkraken.com/linux/gitkraken-amd64.deb
sudo dpkg -i gitkraken-amd64.deb

```

​		

# Apple Music  & Spotify

![green and white logo illustration](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/photo-1611339555312-e607c8352fd7ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80)

음악이 개발을 더욱 즐겁게 해줍니다.

**snap**

```bash
# Apple Music
$ sudo snap install apple-music-for-linux
# Spotify
$ sudo snap install spotify

```

**.deb**

```bash
curl -sS https://download.spotify.com/debian/pubkey_0D811D58.gpg | sudo apt-key add - 
echo "deb http://repository.spotify.com stable non-free" | sudo tee /etc/apt/sources.list.d/spotify.list
sudo apt-get update && sudo apt-get install spotify-client

```

​	

## Postman

![Using Variables and Chaining Requests in Postman - Vonage Developer Blog](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/Blog_Postman2_1200x600.png)

API 테스트를 편하게 할 수 있습니다.

**snap**

```bash
sudo snap install postman
```

​	

## IntelliJ IDEA 혹은 Eclipse

![IntelliJ IDEA 2021.2 Release Candidate Is Out! | The IntelliJ IDEA Blog](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/BlogFeatured_IntelliJ-IDEA-2x-2400x1350.png)

STS 4 를 설치할 경우 아래의 글을 참고해주세요.

[Ubuntu) STS4 (Spring Tools Suite 4 for Elipse) 설치하고 바로 가기 만들기](https://shanepark.tistory.com/236)

IntelliJ IDEA를 사용한다면 저는 snap 으로 설치 했습니다.

```shell
sudo snap install intellij-idea-ultimate --classic
```

​	

## * Kolourpaint

![KolourPaint screenshot.png](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/300px-KolourPaint_screenshot.png)

Linux 에서의  Microsoft Paint (그림판) 입니다. 간단한 이미지 편집을 하기 위해 사용합니다.

```bash
 $ sudo apt-get install kolourpaint4
```



## Docker

![Empowering App Development for Developers | Docker](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/docker_facebook_share.png)

Windows 에서 Ubuntu 로 넘어오게 된 가장 큰 계기입니다. Docker를 사용하면 정말 편하게 격리된 컨테이너들을 구성해 가상화의 장점을 살릴 수 있습니다. 사실상 업계 표준인 만큼 접근성이 높으며 사용에 굉장히 편리합니다. 관련 레퍼런스도 어렵지 않게 찾아 볼 수 있으며 사용자들이 작성해 둔 패키지/이미지들이 넘쳐나기 때문에 뭔가를 정말 간단하게 할 수 있습니다. 윈도우즈에서도 WSL2(Windows Subsystem for Linux)를 이용해 사용은 가능 했지만 메모리나 안정성 문제로 불편함이 있었습니다.

글이 길어져 링크를 나누었습니다. 아래 글을 확인해주세요.

[Ubuntu 20.04 LTS ) Docker 설치하기](https://shanepark.tistory.com/237)

​	

## * DBeaver

![GitHub - dbeaver/dbeaver: Free universal database tool and SQL client](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/f3f5c080-808b-11ea-9713-2bea65875d95.png)

모든 데이터베이스를 한가지 툴로 관리 할 수 있으니 정말 편리합니다.

```shell
sudo snap install dbeaver-ce
```

​			

## SSH 접속 허용

![text](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/photo-1629654297299-c8506221ca97ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80)

다른 컴퓨터에서도 접속 하고 싶다면 SSH 접속을 허용 해줍니다. 아래의 링크를 참고해주세요.

[Ubuntu 20.04 LTS ) SSH 접속 허용하기](https://shanepark.tistory.com/239)

​		

## vim 편집기 설치

Ubuntu 환경에서 기본 설치되어있는 vim을 이용해보니 방향키를 누를 때 이상한 문자가 입력 된다던가 하는 불편함이 있었습니다. 그래서 기본으로 설치되어 있는게 향상된 vim이 아닌가 싶어 -version을 입력 해 보니

![image-20210920093024091](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20210920093024091.png)

Vi IMproved 라고 나오긴 했습니다. 방향키야 hjkl를 사용하면 된다지만 백스페이스 라던가 몇가지 불편한 점이 있어서 새로 설치를 해 보려고 합니다.

​		

바로 설치를 해 보겠습니다.

```bash
sudo apt-get update
sudo apt-get install vim

```

![image-20210920093725359](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20210920093725359.png)

설치가 끝났습니다. 

-version 을 해보니 완전 똑같이 나와서. 뭐야, 이전이랑 똑같은게 아니야 ? 할 수 있는데요.

![image-20210920093946034](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20210920093946034.png)

​		

vi test.txt 입력해서 새로 편집기를 띄워 보니 백스페이스, 방향키 등이 의도한 대로 잘 동작이 됩니다.

![image-20210920094102444](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20210920094102444.png)	

​		

이제 vim을 설치했으니 몇가지 설정을 해 두는게 좋습니다.

```bash
vi ~/.vimrc
```

![image-20210920094256866](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20210920094256866.png)

```bash
" Syntax Highlighting
if has("syntax")
        syntax on
endif

" 검색 관련 설정
set ignorecase "검색시 대소문자 무시
set hlsearch " 검색 단어 하이라이트

" 에디터 관련 설정
set number "Line Number 표시
set cindent "자동 들여쓰기
set autoindent
set ts=4 " Tab 너비(보여줄때)
set sts=4 " Tab 너비(작성할때)
set shiftwidth=4 " 자동 인덴트 너비
set showmatch "짝이 되는 괄호 하이라이트
```

간단하게 이정도 설정만 저장 해 두고 쓰면서 필요할 때 더 추가합니다.

​	

## * SSH key 생성하기	

> 더 자세한 내용은 [SSH key 생성하고, 서버에 등록해서 비밀번호 없이 접속하기](https://shanepark.tistory.com/195?category=1222202) 글을 참고해주세요.

SSH 접속을 위한 key를 생성 해 둡니다. 처음에는 키를 저장할 경로를 묻는데요, default 경로가 (/home/{user}/.ssh/id_rsa) 입니다. 굳이 변경을 하지 않으려면 그냥 엔터키를 입력 하면 됩니다.

두번째, 세번째에서는 passphrase(추가로 사용할 암호, 기본값 없음)을 입력하는데 굳이 입력 하지 않아도 됩니다.

```bash
ssh-keygen -t rsa
```

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20210920101205629.png)

생성이 완료되었습니다.

```bash
cat ~/.ssh/id_rsa.pub
```

를 입력해서 public 키를 읽을 수 있구요, 원격 접속 할 컴퓨터에 등록해서 사용 하시면 편합니다.

> [SSH key 생성하고, 서버에 등록해서 비밀번호 없이 접속하기](https://shanepark.tistory.com/195?category=1222202) 

​	

## VLC Media Player

![image-20210920114245540](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20210920114245540.png)

요즘에는 동영상 파일을 직접 재생할 일이 잘 없기는 하지만 전혀 없진 않아서 필요 할 떄가 있습니다.

디자인이 다소 투박해 보이지만 호환성 면에서는 존재하는 미디어 플레이어 중 최고로, Linux, MacOS, Windows는 물론 심지어 Android 나 iOS에서도 돌아갈 정도로 포팅이 잘 되어있고 자체 코덱을 내장하여 코덱을 따로 받을 필요도 없습니다.

```bash
sudo snap install vlc
```

​			

앞으로도 초기 설정에 더 필요한 내용이 있다고 생각될 때는 본 글을 꾸준히 업데이트 하도록 하겠습니다.


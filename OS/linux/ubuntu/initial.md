# 개발자를 위한 Ubuntu 필수 설치와 설정	

![How To Install Ubuntu Linux inside Windows - Techi Signals](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/Install-Ubuntu-Linux.jpg)	

> 꾸준히 최신 정보로 업데이트 되는 글 입니다.

## 들어가기전에

### 서론

[개발자를 위한 Mac 초기 설정 하기](https://shanepark.tistory.com/167) 포스팅을 작성한지 두달이 조금 넘었습니다. Mac을 처음 구입하시는 분들이 처음에 세팅을 한번에 하기 위해 꾸준히 방문해 주시는 포스팅 인데, 다른 분들에게 얼마나 도움이 되는지 정확히는 모르지만 적어도 저에게는 처음부터 세팅을 다시 해야하거나 다른 분들의 세팅을 도와 줄때마다 다시 찾아보게 되어 스스로 큰 도움을 받고 있습니다. 

처음 Ubuntu 를 설치 하며 많은 어려움을 겪었었고, 후에도 집에서 개인 서버로 사용하던 윈도우 노트북도 윈도우를 아에 밀어버리고 우분투로 바꿔 보고, 또 스스로도 잘못 건들었다가 다시 설치하는 등 시행착오를 몇 번 겪다 보니 정리를 해 두는게 좋다는 생각이 들어 하나 둘 씩 정리하기 시작했습니다. 

최근에도 회사에서 우분투가 꼬여버려 개발환경을 다시 세팅해야 하는 일이 있었는데, 해당 글을 참고하며 금방 진행 할 수 있었습니다.

### 진행 순서

아래의 내용 중 본인에게 필요한 내용만 순서에 맞춰 설치하시면 됩니다.

최대한 초기 세팅 순서에 맞춰 배열을 해 두었지만, 우분투를 이미 몇번 설치 해 본 경험이 있는 분이라면 컨텐츠 테이블을 확인 하며 각자 필요하신 내용을 먼저 진행 하셔도 좋습니다.

## 필수 설정

### 한글 키보드 입력 설정

보통 한글 입력 때문에 키보드 설정을 제일 먼저 하는게 편합니다. 맥북을 설정할때도 Karabiner를 제일 먼저 설치하는 것 과 같은 맥락 입니다. iBus에 한계가 분명 있긴 하지만 기본적으로 iBus 설정은 무조건 하는게 낫다고 생각합니다. 

Ubuntu를 처음 설치한다면 iBus 키보드 설정만 하시면 되지만, 최종적으로는 Tian 입력기를 설치하는게 좋습니다. 일단 iBus를 쓰다가 불편함이 있을 때 Tian을 설치 하시면 됩니다.

- iBus 키보드 설정

> [Ubuntu 20.04 키보드 한글 입력 설정 하기](https://shanepark.tistory.com/231)

- fcitx 키보드 설정

>  [fctix입력기 설치해 intelliJ 한글입력 해결하기](https://shanepark.tistory.com/262?category=1222202)
>
>  iBus보다는 낫지만 Tian을 사용하는걸 추천합니다.

- Tian(nimf) 입력기 설치

> [Ubuntu) 끝판왕 한글 입력기 Tian (nimf)](https://shanepark.tistory.com/293)
>
> 말그대로 한글 입력 끝판왕입니다. 이것 저것 다 써봤는데 Tian이 최고 입니다.

### 비프음 끄기

회사에서는 노트북 볼륨을 꺼 두고 써서 몰랐는데, 우분투에서의 비프음이 굉장히 거슬리더라고요. 해당 비프음도 꺼줍니다.

[Ubuntu Terminal에서 백스페이스/방향키 누를때 삐 하는 비프음 안나게 하기](https://shanepark.tistory.com/234)

### SSH key 생성

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

### SSH 접속 허용

![text](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/photo-1629654297299-c8506221ca97ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80)

다른 컴퓨터에서도 접속 하고 싶다면 SSH 접속을 허용 해줍니다. 아래의 링크를 참고해주세요.

> [Ubuntu 20.04 LTS ) SSH 접속 허용하기](https://shanepark.tistory.com/239)

### open 명령어로 nautilus 실행

아래의 명령어로 터미널에서 현재 작업중인 폴더를 탐색 할 수 있는데요

```zsh
nautilus .
```

nautilus 를 항상 입력하는건 귀찮으니 open 명령어로 alias를 지정 해 줍니다.

아래의 내용을 zsh 사용중이라면 `~/.zshrc`에 bash라면 `~/.bashrc`에 등록 해 줍니다.

```zsh
alias open="nautilus"
```

### 자동 잠금 방지

기본적인 설정으로 몇 분 동안 사용하지 않으면 잠금이 되어 버리는데, 사무실에서 사용할 땐 불편 할 수 있으니 설정을 풀어놓고 사용하는 편 입니다.

Settings -> Privacy -> ScreenLock에서

Blank Screen Delay를 Never로, Automatic Screen Lock을 체크 해제 합니다.

![a](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/a.png)

![b](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/b.png)

![c](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/c.png)

### Ctrl+Alt+방향키 키바인딩 삭제

워크스페이스간 이동은 `Super+PageUp/PageDown`이 이미 배정이 되어 있는데요, 추가로 Ctrl+Alt+방향키가 배정되어있는 바람에 이클립스라도 쓰는 날엔 라인 복사를 할 수 없어서 불편합니다. 텐키리스 키보드에서 PageUp/Down은 다 붙어 있으니, 굳이  Ctrl+Alt를 사용하는 단축키로 남겨 둘 필요도 없고, 저는 맥북에서도 데스크톱 전환을 컨트롤+방향키로 하다 보니 해당 키바인딩은 꼭 삭제 합니다.

- dconf Editor 를 켜고 org.gnome.desktop.wm.keybindings에 가서

![image-20211208095711980](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211208095711980.png)

switch-to-workspace 를 검색합니다.

![image-20211124090259043](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211124090259043.png)

위에 보이는 것 처럼 up과 down에 있는 모든 내용을 

![image-20211124090435799](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211124090435799.png)

Use default value를 해제 한 후에, `, '<Control><Alt>Down'` 를 통째로 지우고 Apply 를 하면 됩니다. Up, Down 모두 번갈아 가며 지워 줍니다. 그러고 나면 Ctrl+Alt+방향키를 눌러도 workspace 전환이 되지는 않습니다. 자유롭게 해당 단축키를 다른 필요한 곳에 할당 해서 사용 할 수 있습니다.

마찬가지로 좌,우도 없애줘야 하는데요, intelliJ IDEA 에서 해당 키를 `Navigate back / forward`로 사용하고 있기 때문입니다.

위에서 처럼 눈으로 보며 삭제 할 수 있지만, 사실 아래의 명령어로 간단하게 없앨 수 있습니다.

```zsh
gsettings set org.gnome.desktop.wm.keybindings switch-to-workspace-left "[]"
gsettings set org.gnome.desktop.wm.keybindings switch-to-workspace-right "[]"
```



### 시스템 종료 단축키 만들기

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/img.png)

저는 종료할 때 윈도우의 Alt+F4 같은 간단한 종료 단축키가 필요한데 우분투에는 마땅히 없더라고요.

그래서 Super+F4 를 종료 단축키로 만들어서 사용하고 있습니다. 키보드 shortcut 설정에서 아래의 커맨드를 원하시는 Shortcut으로 설정 해서 사용하면 됩니다. `Super+F4`를 입력한다고 바로 꺼지는건 아니고, 입력하고 60초 후에 자동으로 종료되거나 혹은 Super+F4 를 누르고 지금 종료 버튼 한번 눌러주면 됩니다.

```zsh
gnome-session-quit --power-off
```

## 필수설치

### vim 편집기 설치

Ubuntu 환경에서 기본 설치되어있는 vim을 이용해보니 방향키를 누를 때 이상한 문자가 입력 된다던가 하는 불편함이 있었습니다. 그래서 기본으로 설치되어 있는게 향상된 vim이 아닌가 싶어 -version을 입력 해 보니

![image-20210920093024091](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20210920093024091.png)

Vi IMproved 라고 나오긴 했습니다. 방향키야 hjkl를 사용하면 된다지만 백스페이스 라던가 몇가지 불편한 점이 있어서 새로 설치를 해 보려고 합니다.

- 바로 설치를 해 보겠습니다.

```bash
sudo apt-get update
sudo apt-get install vim

```

![image-20210920093725359](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20210920093725359.png)

설치가 끝났습니다. 

-version 을 해보니 완전 똑같이 나와서. 뭐야, 이전이랑 똑같은게 아니야 ? 할 수 있는데요.

![image-20210920093946034](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20210920093946034.png)

<br><br>vi test.txt 입력해서 새로 편집기를 띄워 보니 백스페이스, 방향키 등이 의도한 대로 잘 동작이 됩니다.

![image-20210920094102444](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20210920094102444.png)	

<br><br>

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
set ts=2 " Tab 너비(보여줄때)
set sts=2 " Tab 너비(작성할때)
set shiftwidth=4 " 자동 인덴트 너비
set showmatch "짝이 되는 괄호 하이라이트
```

>  간단하게 이정도 설정만 저장 해 두고 쓰면서 필요할 때 더 추가합니다.

### JDK 설치 

![black and silver laptop computer on table](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/photo-1517694712202-14dd9538aa97ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80)

> 각자 필요한 버전에 맞춰 8 대신 넣으면 됩니다. 해당 명령어로 설치가 끝납니다.

```bash
$ sudo apt-get install openjdk-8-jdk
```

### LibreOffice

Linux의 Microsoft Office 입니다. 무료로 사용 할 수 있습니다.

![image-20211125144106550](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211125144106550.png)

> https://www.libreoffice.org/download/download/

위의 링크에서 원하는 버전을 다운 받습니다.

다운을 받은 후에는 압축을 풀어 줍니다.

```zsh
tar -xvf LibreOffice_7.2.2_Linux_x86-64_deb.tar.gz
```

![image-20211125144209993](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211125144209993.png)

압축이 풀렸으면 DEBS 내의 모든 `.deb`파일을 설치 해 줍니다.

```zsh
sudo dpkg -i *.deb
```

![image-20211125144336873](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211125144336873.png)

잠시 기다리면 모든 설치가 완료됩니다.

![image-20211125144421314](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211125144421314.png)

### zsh와 oh-my-zsh

bash 보다는 zsh가 많이 쓰이는 추세입니다.

> [Ubuntu에 oh-my-zsh 설치](https://shanepark.tistory.com/248)

### Git~~Kraken~~hub Desktop 설치

~~SourceTree의 경우 Linux 버전이 없어 GitKraken 이 대안으로는 가장 괜찮아 보였습니다. 물론 CUI 를 사용하는게 기능이 훨씬 강력하기는 하지만 Git의 진입 장벽을 생각할 때 GUI 를 거치지 않기는 쉽지 않습니다.~~

> GitKraken이 사실상 유료기 때문에 더이상 추천하지 않습니다.  SSH 방식으로 저장소를 클론 하고 Github Desktop을 사용하시는 것을 권장합니다. Github Desktop의 설치는 아래 링크를 확인 해 주세요.

[Ubuntu 20.04 우분투 Github Desktop 설치하기](https://shanepark.tistory.com/252)	

그래도 GitKraken을 설치하고 싶다면?

```bash
wget https://release.gitkraken.com/linux/gitkraken-amd64.deb
sudo dpkg -i gitkraken-amd64.deb

```

### IntelliJ IDEA 

![IntelliJ IDEA 2021.2 Release Candidate Is Out! | The IntelliJ IDEA Blog](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/BlogFeatured_IntelliJ-IDEA-2x-2400x1350.png)

IntelliJ IDEA를 사용한다면 저는 처음에는 snap 으로 설치 했었습니다.

```shell
sudo snap install intellij-idea-ultimate --classic
```

그런데 snap으로 설치하면 사용할때 이상하게도 로딩도 너무 느리고 사용하기가 불편 하더라고요.

![image-20211021084558418](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211021084558418.png)

그래서 삭제 뒤 위에 안내된 것 처럼 공식 홈페이지에 나온 방법 대로 설치 해서 사용해보니, MacOS에서 경험했던 훌륭한 속도 그대로  잘 사용 하고 있습니다.

IntelliJ IDEA 다운로드 링크도 첨부 해 둡니다.

![image-20211021084925005](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211021084925005.png)

> https://www.jetbrains.com/idea/download/#section=linux

### Eclipse

STS 4 를 설치할 경우 아래의 글을 참고해주세요.

>  [Ubuntu) STS4 (Spring Tools Suite 4 for Elipse) 설치하고 바로 가기 만들기](https://shanepark.tistory.com/236)

### Docker

![Empowering App Development for Developers | Docker](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/docker_facebook_share.png)

Windows 에서 Ubuntu 로 넘어오게 된 가장 큰 계기입니다. Docker를 사용하면 정말 편하게 격리된 컨테이너들을 구성해 가상화의 장점을 살릴 수 있습니다. 사실상 업계 표준인 만큼 접근성이 높으며 사용에 굉장히 편리합니다. 관련 레퍼런스도 어렵지 않게 찾아 볼 수 있으며 사용자들이 작성해 둔 패키지/이미지들이 넘쳐나기 때문에 뭔가를 정말 간단하게 할 수 있습니다. 윈도우즈에서도 WSL2(Windows Subsystem for Linux)를 이용해 사용은 가능 했지만 메모리나 안정성 문제로 불편함이 있었습니다.

글이 길어져 링크를 나누었습니다. 아래 글을 확인해주세요.

> [Ubuntu 20.04 LTS ) Docker 설치하기](https://shanepark.tistory.com/237)

### DBeaver

![GitHub - dbeaver/dbeaver: Free universal database tool and SQL client](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/f3f5c080-808b-11ea-9713-2bea65875d95.png)

모든 데이터베이스를 한가지 클라이언트 만으로 관리 할 수 있으니 정말 편리합니다.

![image-20211123221145003](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211123221145003.png)

> https://dbeaver.io/download/

Linux Debian package를 다운 받아서 설치 하면 됩니다.

dpkg 로 설치해 주시면 됩니다. 개인적인 취향 차이일 수 있지만 저는 snap은 최대한 지양합니다.

```zsh
dpkg -i ~/Downloads/dbeaver-ce_21.2.5_amd64.deb
```

### Postman

![Using Variables and Chaining Requests in Postman - Vonage Developer Blog](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/Blog_Postman2_1200x600.png)

API 테스트를 편하게 할 수 있습니다.

**snap**(비추천)

```bash
sudo snap install postman
```

 사실 snap으로 설치하는게 쉽기는 하지만, 개인적으로 어떤 어플이든 작동이 굉장히 느려져서 현재는 snap으로 설치된 모든 어플들을 다 수동으로 설치해 사용하고 있습니다. 수동으로 설치하신다면..

1. 일단 먼저 https://www.postman.com/downloads/ 에서 다운받습니다. Linux 64-bit를 받으면 되겠네요.

   ![image-20211105155753299](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211105155753299.png)

2. 다운받은 파일을 opt 폴더에 압축 해제합니다.

   ```zsh
   sudo tar -zxvf  ./Postman-linux-x86_64-8.12.5.tar.gz -C /opt/
   ```

3. 바로 가기 파일을 생성 해 줍니다.

   ```zsh
   vi ~/.local/share/applications/Postman.desktop
   ```

4. Postman.desktop 에는 아래의 내용을 넣습니다.

   ```properties
   [Desktop Entry]
   Encoding=UTF-8
   Name=Postman
   Exec=/opt/Postman/app/Postman %U
   Icon=/opt/Postman/app/resources/app/assets/icon.png
   Terminal=false
   Type=Application
   Categories=Development;
   
   ```

### VLC Media Player

![image-20210920114245540](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20210920114245540.png)

요즘에는 동영상 파일을 직접 재생할 일이 잘 없기는 하지만 전혀 없진 않아서 필요 할 떄가 있습니다.

디자인이 다소 투박해 보이지만 호환성 면에서는 존재하는 미디어 플레이어 중 최고로, Linux, MacOS, Windows는 물론 심지어 Android 나 iOS에서도 돌아갈 정도로 포팅이 잘 되어있고 자체 코덱을 내장하여 코덱을 따로 받을 필요도 없습니다.

```bash
sudo apt install vlc
```

### Kolourpaint

![KolourPaint screenshot.png](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/300px-KolourPaint_screenshot.png)

Linux 에서의  Microsoft Paint (그림판) 입니다. 간단한 이미지 편집을 하기 위해 사용합니다.

```bash
 $ sudo apt-get install kolourpaint4<br>
```

## 선택 설정

### Dock 커스터마이징

> 저는 키보드 설정 후에는 보통 dock을 입맛에 맞게 설정합니다. 아래 글을 참고해주세요. 
>
> 테마를 전부 macOS 처럼 바꾸고 싶다면 이번건 넘기고, 바로 아래의 MacOS 테마 입히기를 하시면 됩니다.

[Ubuntu 20.04 Mac OS 처럼 Dock 가운데 위치하게 변경하기](https://shanepark.tistory.com/233)

### MacOS 테마 입히기

![ubuntu](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/ubuntu.png)

개인적으로 macOS의 환경을 좋아해서 macOS 처럼 만들어두고 사용하고 있습니다. 아래 링크를 따라하면 어렵지 않게 가능합니다.

>  [Ubuntu MacOS 처럼 만들기 GTK Themes](https://shanepark.tistory.com/251)

### 상단 바 없애기

모니터가 크고 해상도가 넓으면 크게 상관 없겠지만 공간 활용을 최대한 하기 위해 상단 bar와 하단 dock을 모두 없애고 사용 하고 있습니다. 필요하시면 하시면 됩니다.

일단 Gnome-tweaks 가 없다면 먼저 설치 해 주어야 합니다.

```bash
$ sudo apt install gnome-tweaks
```

그러고 나서 topbar를 없앨 수 있는 extention을 설치 해 줍니다.

```bash
sudo apt install gnome-shell-extension-autohidetopbar
```

그러고 나서 재부팅을 하고 나서 Tweaks 를 실행해주면 아래와 같이 Extentions 에 hide top bar 옵션이 추가 된 것이 확인 됩니다.

![image-20211001144534045](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211001144534045.png)

이 설정을 켜주면 이제 상단이 밀릴때 상단 바가 안으로 들어가는데요,

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/autohide-top-panel.gif)

> ref : https://fostips.com/auto-hide-top-panel-debian-ubuntu-fedora/

평소에 숨어있다가 마우스를 올릴 때만 나오게 하려면 아래와 같이 설정 하면 됩니다.

![image-20211001145441964](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211001145441964.png)

가장 위에 있는 Show Panel when mouse approaches edge of the screen 옵션을 켜주고 가장 아래의 Intellihide 에 있는 두개의 옵션을 꺼주면 됩니다.

### 노트북 지문인식 등록

![round black and white light](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/photo-1585079374502-415f8516dcc3ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1000&q=80)

>  필요한 분만 하면 됩니다. 솔직히 굳이 하지 않아도 되는 기능이긴 합니다. 아래 링크를 참고해주세요.

[Ubuntu 20.04 Dell XPS 노트북 지문인식 로그인하기](https://shanepark.tistory.com/232)

### 바탕화면 아이콘 숨기기

지극히 개인적인 취향 이지만, 저는 바탕화면에 홈과 휴지통이 있는걸 좋아하지 않습니다. 휴지통은 dock에 달아서 사용합니다.

```zsh
gsettingsset org.gnome.shell.extensions.desktop-icons show-trashfalse
gsettingsset org.gnome.shell.extensions.desktop-icons show-homefalse

```

### Fusuma 설치

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/img.gif)

데스크탑이 아닌 노트북 사용자 라던가, 혹은 별도의 터치패드를 연결해서 사용한다면 터치 제스처를 다양하게 사용해 MacOS 만큼 생산성을 높일 수 있는 fusuma 라는 어플리케이션이 있습니다.

내용이 다소 간단하지만은 않아서 따로 포스팅을 작성 하였습니다. 아래 링크를 참고해주세요.

> [Ubuntu) 터치패드 및 트랙패드 활용하기 Fusuma](https://shanepark.tistory.com/257)

## 선택 설치

### Google Chrome

FireFox가 정말 좋긴 하지만, 개발할때 Chrome이 없으면 곤란합니다.. 사실 필수 설치로 보내야 하는 항목 입니다.

> https://www.google.com/intl/ko/chrome/

### Visual Studio Code

![image-20211123223857894](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211123223857894.png)

간단히 메모장 용도로 사용하기에도 훌륭할 만큼 가볍습니다. `.deb` 파일을 다운 받아서 설치 하시면 쉽습니다.

> https://code.visualstudio.com/download

```zsh
sudo dpkg -i 파일명.deb
```

### Albert

![Untitled](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/Untitled.png)

MacOS에서의 Alfred를 정확히 따라하는 Albert 입니다. 이름부터가 노리고 지은걸 알 수 있습니다.

아래의 링크에 설치 방법이 나와 있습니다.

> https://albertlauncher.github.io/installing/

위의 링크에 나온 순서대로 설치를 진행 하겠습니다.

```zsh
curl "https://build.opensuse.org/projects/home:manuelschneid3r/public_key" | sudo apt-key add -
```

![Untitled](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/Untitled-7674177.png)

아래는 Ubuntu 20.04 기준의 설치 방법 입니다.

```zsh
echo 'deb http://download.opensuse.org/repositories/home:/manuelschneid3r/xUbuntu_20.04/ /' | sudo tee /etc/apt/sources.list.d/home:manuelschneid3r.list
curl -fsSL https://download.opensuse.org/repositories/home:manuelschneid3r/xUbuntu_20.04/Release.key | gpg --dearmor | sudo tee /etc/apt/trusted.gpg.d/home_manuelschneid3r.gpg > /dev/null
sudo apt update
sudo apt install albert

```

혹시 다른 버전을 사용 하신다면, 아래 링크를 확인하셔서 본인에게 알맞는 버전의 설치방법을 확인 해 주세요.

> https://software.opensuse.org/download.html?project=home:manuelschneid3r&package=albert

![Untitled2](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/Untitled2.png)

설치 된 후엔, 그냥 검색하면 아무것도 나오지 않기 때문에 Applications 를 체크 해야 합니다. 위에선 Files를 체크 했지만 FIles를 체크 하면 굉장히 불편하기 때문에 Applications랑 Calculator만 체크 하고 사용하시길 권장 합니다.

![image-20211124155400697](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211124155400697.png)

General 설정에서 단축키로도 `Alt + Space`를 설정 해서 MacOS에서 Spotlight나 Alfred 쓰듯 사용 하면 됩니다. `Autostart on log`을 반드시 체크 해주세요. 그렇지 않으면 컴퓨터를 새로 켤 때 마다 일일히 Albert를 실행 해 주어야 합니다.

### Typora

![image-20211105163432374](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211105163432374.png)

마크다운 에디터로 Typora를 사용하고 있습니다.

 여러가지 마크다운 에디터를 사용 해 봤지만, 블로그 글을 작성하는데는 Typora가 가장 좋았습니다.

일단 공식 사이트에서 안내하는 방법은 아래와 같습니다.

```zsh
# or use
# sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys BA300B7755AFCFAE
wget -qO - https://typora.io/linux/public-key.asc | sudo apt-key add -

# add Typora's repository
sudo add-apt-repository 'deb https://typora.io/linux ./'
sudo apt-get update

# install typora
sudo apt-get install typora

```

하지만 제가 다운로드를 시도 해보니 key가 만료되어서 저장소에 접속이 되지 않았습니다.

> 글을 수정하며 확인해보니 지금은 위의 방법으로 설치가 되니 그대로 진행 해주세요.

![image-20211105163031170](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211105163031170.png)

> https://typora.io/#linux

그래도 다행히도 download binary file 링크가 있어서 binary file을 다운 받아 설치 할 수 있었습니다. apt-get을 위와 같이 먼저 시도 해 보시고 안된다면 binary file을 다운 받아서 설치 하시면 됩니다.

다운로드 링크 : https://typora.io/linux/Typora-linux-x64.tar.gz

해당 파일을 다운 받으면 bin이라는 이름의 폴더가 생깁니다. 그 아래에  `Typora-linux-x64` 폴더가 있는데요, 해당 폴더를 `/opt` 경로에 풀어 주었습니다.

그러고는 아래와 같이 바로가기를 생성 해 주었습니다.

```zsh
vi ~/.local/share/applications/Typora.desktop
```

```properties
  1 [Desktop Entry]
  2 Encoding=UTF-8
  3 Name=Typora
  4 Exec=/opt/Typora-linux-x64/Typora %U
  5 Icon=/opt/Typora-linux-x64/resources/assets/icon/icon_128x128.png
  6 Terminal=false
  7 Type=Application
  8 Categories=Development;

```

### Notion 설치

![image-20211027092241360](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/image-20211027092241360.png)

Notion에서 공식 우분투용 프로그램을 지원하지는 않지만, 감사하게도 개발자들이 힘을 모아 만든 Lotion 프로젝트 덕에 Notion을 native 앱으로 사용 할 수 있습니다. Notion 사용자라면, 아래의 링크에서 설치 방법을 확인 해 주세요.

> [Ubuntu) notion App 설치](https://shanepark.tistory.com/265)

### Apple Music  & Spotify

![green and white logo illustration](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/initial.assets/photo-1611339555312-e607c8352fd7ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80)

음악을 좋아하시면 설치하세요

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

## The end

앞으로도 초기 설정에 더 필요한 내용이 있다고 생각될 때는 본 글을 꾸준히 업데이트 하도록 하겠습니다.

최근 개발 머신을 한번 날려먹은 덕에 글을 통째로 개선하며, 스스로 불편했던 부분들을 모두 개선 하였습니다.

질문이 있거나 불편함이 있는 부분은 편하게 피드백 해주시면 수시간 내 답글로 도와드리겠습니다. 감사합니다. 

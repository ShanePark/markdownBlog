# Ubuntu에 oh-my-zsh 설치

## MacOS 이야기

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926193827237.webp width=600 height=500 alt=1>

MacOS 에서는 Catalina 부터 기본 쉘이 zsh(Z shell)이 되었습니다. m1 맥북을 구매해서 BigSur를 첫 OS로 사용하기 시작한 저도 자연스럽게 zsh를 이용해 왔는데요. 특히 iTerm2 를 설치하고 oh-my-zsh를 올려 사용하며 여러가지 편의성이 었었습니다.

아래 링크는 Mac 에서의 oh-my-zsh 설치에 대한 정보 입니다.

> [oh-my-zsh 설치하기](https://shanepark.tistory.com/60)

​	

## Ubuntu 이야기

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926193452597.webp width=600 hegiht=500 alt=2>

Ubuntu를 설치하면 기본적으로 BASH(Bourne Again Shell)이 기본 쉘로 설치되어 있습니다. 사용하며 딱히 불편 한 점은 없었지만 전부 비슷한 환경을 꾸밀 수는 없더라도 shell 정도는 MacOS에서 사용하는 것과 같은 환경으로 맞춰야 겠다는 생각이 들었습니다. 사소한 차이지만 ~/.bashrc 설정을 바꾸는 대신 평소처럼 ~/.zshrc 를 변경 하면 되며 Terminal 디자인도 일관적인게 좋겠다는 생각이 들었습니다.

​	

## zsh 설치

> oh-my-zsh의 wiki를 참고했습니다. https://github.com/ohmyzsh/ohmyzsh/wiki/Installing-ZSH

Ubuntu에서도 zsh를 설치 해 사용해 보겠습니다.

![image-20210926194011159](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926194011159.webp)

일단 확실하게 zsh가 설치 되어 있는지 확인 을 해줍니다. 설치되어 있다고 해도 zsh 의 버전이 너무 낮으면 oh-my-zsh를 사용 할수 없습니다.

![image-20210926194701519](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926194701519.webp)

ohmyzsh 에서는 5.0.8 버전 이상을 권장합니다.

위에서 bash가 제안해 준 방법으로 zsh를 설치 해 줍니다.

```bash
sudo apt install zsh
```

​	

금방 설치가 끝나는데요,  설치가 끝난 후

```bash
echo $SHELL
```

를 입력해 기본 shell 을 확인 해 보니 설치만 한다고 기본 Shell 이 변경되지는 않더라고요.

![image-20210926194457492](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926194457492.webp)

​	

![image-20210926195017715](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926195017715.webp)	

oh-my-zsh의 instruction에 맞춰 기본 shell을 zsh로 변경해줍니다.

```bash
chsh -s $(which zsh)
```

​	

저는 이렇게 해도 변경이 되지 않았는데요, oh-my-zsh의 안내에 따르면 Zsh가 저의 authorized shell list 에 있지 않거나 chsh 를 수행할 권한이 없는 경우 라는데, 둘다 해당되지 않는 것 같아 우분투를 재부팅을 해 보았습니다. (사실 로그아웃만 해도 충분 하긴 합니다.)

```bash
reboot
```



![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926200352878.webp)

재부팅 후에는 뭐..

```bash
echo $SHELL
```

을 입력해서 굳이 확인 하지 않아도 이미 zsh로 잘 전환이 되었다는 걸 확인 할 수 있게 new user 를 향한 설정에 대한 내용이 떴습니다. 

​	

## oh my zsh 설치

> https://github.com/ohmyzsh/ohmyzsh/wiki

ohmyzsh wiki를 참고해 설치과정을 따라가 보겠습니다.

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926200548162.webp)

​		

curl, wget, fetch 어떤 방법을 사용해도 설치 할 수 있습니다. 저는 curl을 이용하겠습니다.

```bash
sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
```

![image-20210926200748307](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926200748307.webp)

아주 금방 설치가 끝났습니다.

​	

## oh my zsh 설정

일단 간단하게 테마를 먼저 변경해 보겠습니다. MacOS에서도 사용하고 있는 agnoster로 변경 합니다.

이제 앞으로는 ~/.bashrc는 잊고 ~/.zshrc 를 이용해주세요 .bashrc 에서 사용하던 alias나 셋팅들도 여길로 가져오면 됩니다.

```bash
vi ~/.zshrc
```

​	

THEME= 부분을 찾아 agnoster로 변경해줍니다. 11번 라인에 있습니다.

![image-20210926201426344](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926201426344.webp)

​	

```bash
source ~/.zshrc
```

를 입력해 변경사항을 바로 적용시킵니다.

![image-20210926201518712](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926201518712.webp)	

​	

폰트가 깨지네요. powerline fonts를 설치하면 해결됩니다.

```bash
sudo apt-get install fonts-powerline
```

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926202032232.webp)

일단 fonts-powerline을 설치하자 마자 font가 깨지던 현상은 해결됩니다.

Mac에서도 그랬었는데 저는 한글도 사용하기 때문에 네이버에서 만든 한글 코딩폰트인 D2Font를 평소 즐겨 사용하고 있습니다.

​	

https://github.com/naver/d2codingfont 에서 다운 받습니다.

![image-20210926201707236](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926201707236.webp)

​	

​	

압축을 풀고 .ttf 파일을 더블 클릭합니다.

![image-20210926202937487](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926202937487.webp)

​		

그러고 나서 Install을 눌러 설치해줍니다.

![image-20210926203016352](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926203016352.webp)

​	

설치가 끝났으면 Terminal 폰트를 변경 해 줍니다.

Terminal에서 우클릭 -> Preferences에 들어갑니다.	

![image-20210926202343748](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926202343748.webp)

​	

custom font 를 클릭 하고 방금 설치한 D2Coding 폰트를 선택 해 줍니다.

![image-20210926203113305](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926203113305.webp)

​		

Select를 눌러 선택해주면 폰트가 바로 적용됩니다. 맥북에서 쓰던 셋팅과 매우 비슷해지니 마음이 편안해지네요.

![image-20210926203158009](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926203158009.webp)

​	

이번에는 컴퓨터 이름을 감추어 보겠습니다. 너무 쓸데없는 텍스트가 길 필요가 없습니다.

```bash
vi ~/.zshrc
```

​	

적당한 위치에 아래 내용을 추가해줍니다. vi 에서 맨 아래로 이동하는 단축키는 G(대문자) 입니다.

```
prompt_context() {
  if [[ "$USER" != "$DEFAULT_USER" || -n "$SSH_CLIENT" ]]; then
    prompt_segment black default "%(!.%{%F{yellow}%}.)$USER"
  fi
}

```

​	![image-20210926203918015](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926203918015.webp)

​	

내용을 변경 한 후에는 터미널을 껐다 켜거나 source 해줍니다.

```bash
source ~/.zshrc
```

![image-20210926203818489](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/ohmyzsh.assets/image-20210926203818489.webp)

변경사항이 바로 적용되었습니다 !  이제 ~/.bashrc 에서 alias 등 몇가지 가져올 설정만 그대로 가져와서 ~/.zshrc에 그대로 넣어 주시면 불편함 없이 기존의 셋팅들도 가져와서 사용 할 수 있습니다.

​	

이제 모든 설정이 끝났습니다. 터미널을 더 즐겁게 사용할 수 있겠습니다.


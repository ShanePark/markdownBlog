# Linux)  Mac의 text replace Linux에서 흉내내기

> 실행 할 수 있는 Shell Script 만들기

## 개요

<img src="https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/create-shell-script.assets/image-20210919195440966.webp" width=750 height=680 alt=main>

저는 MacBook 을 사용 하면서 text replace 기능을 정말 유용하게 사용 했습니다.

평소에 많이 사용하는 텍스트들을 지정해 두고 해당 텍스트만 입력 하면 기존에 저장해 둔 텍스트로 자동으로 변환 하는 기능인데요, 구글을 찾아 보니 AutoHotkey 를 사용하라는 방법 외에는 뚜렷한 해결 책이 없었습니다.

단순하게 자주 사용하는 텍스트 들을 기록해두었다가 손쉽게 불러오기만 하면 되는 경우기 때문에 shell script 로 만들어 두고 필요 할 때 마다 불러오는 것으로 해결 할 수 있겠다는 생각이 들었습니다. 차근차근 해 보겠습니다.

​		

## 실행 파일 만들기

```bash
vi mdblog.sh
```



저는 mdblog 이란 텍스트를 치환하는게 필요 하기 때문에 파일명을 mdblog.sh 로 지었습니다.

![image-20210919193124111](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/create-shell-script.assets/image-20210919193124111.webp)	

​	

## 기본 문법

- 출력은 echo 와 printf 를 사용 합니다.

- echo는 자동 개행을 하지만 printf는 자동 개행을 하지 않습니다.

- 주석은 # 으로 달 수 있습니다.

- 그 외에는 Terminal 에서 평소 사용하는 명령어들을 사용 하면 됩니다.

- 여타 프로그래밍 언어 처럼 변수나 배열도 사용 할 수 있습니다.

  

  ​	

  

  ```bash
  echo "https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/"
  
  ```

  ​	![image-20210919193457343](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/create-shell-script.assets/image-20210919193457343.webp)

작성을 완료 했으니 저장 합니다.

​		

## 실행

실행은 bash 파일명 으로 할 수 있습니다.

```bash
bash mdblog.sh
```

![image-20210919193822373](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/create-shell-script.assets/image-20210919193822373.webp)

실행 했더니 원하는 텍스트를 잘 표기 해 줍니다.

​	

## alias 등록

일단 home 디렉터리에 있으면 거슬릴 듯 해서 bin 폴더로 이동 시켰습니다.

```bash
sudo mv mdblog.sh /bin
```

​	

그래서 이제는 아래 명령어로 실행 할 수 있습니다.

```bash
bash /bin/mdblog.sh
```

![image-20210919194426081](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/create-shell-script.assets/image-20210919194426081.webp)

​	

Alias 로 등록 해서 앞으로도 간단하게 실행을 하도록 하겠습니다.

터미널창에서 직접 alias 로 등록을 할 경우에는 터미널을 종료 하면 설정이 날아가기 때문에 .bashrc 파일에 alias 를 등록 해줘야 합니다.

```bash
vi ~/.bashrc
```

​	

사용법은 alias {사용할 단축 단어}='{실행할 명령}' 입니다.

```bash
alias mdblog='bash /bin/mdblog.sh'
```

![image-20210919194811414](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/create-shell-script.assets/image-20210919194811414.webp)

적당히 원하는 위치에 아무데나 넣으면됩니다. 저는 4번째 줄에 그냥 바로 넣었습니다.

​		

터미널을 한번 껐다 켠 후에 이제 mdblog 을 입력해서 의도한 대로 잘 되는지 확인 해 봅니다.

![image-20210919194906821](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/create-shell-script.assets/image-20210919194906821.webp)

​	

이제 앞으로 mdblog 만 치면 제가 원하는 문장을 얻을 수 있습니다.

shell script 를 잘 다룰 수 있게 되면 정말 많은 명령들을 손쉽게 실행 할 수 있기 때문에 앞으로 많이 사용 하기 위해 노력 해야겠습니다.

​		

## 추가로 Mac을 사용한다면

zsh

```
$ ~/.zshrc
```

bash

```
$ ~/.bash_profile
```

파일을 수정하시면 alias를 등록 할 수 있습니다.


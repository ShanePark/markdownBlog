# Mac 에 brew 설치하기 및 warning: /opt/homebrew/bin is not in your path. 에러 해결

​	

## Homebrew 란?

Ruby와 Git 으로 개발된 MacOS용 패키지 매니저입니다.
간단하게 mac에 뭔가 설치할때, terminal 에 명령어 한 줄 입력 만으로 설치하게끔 해줍니다.mac을 사용하신다면, 선택이 아닌 필수 입니다. 아직 homebrew 가 없다면 이번기회에 꼭 설치하세요. 몇분 투자로 앞으로의 평생이 아주 편해집니다. 어렵지도 않습니다.

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img.png)

> https://brew.sh/index_ko

​	

Homebrew 설치하기에 보면 명령어가 친절하게 써있습니다. 복사합니다. 아래에 똑같이 써두었습니다. 

```javascript
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```


복사한 명령어를 terminal 을 실행해서 입력합니다.Command + spacebar 를 누르면 spotlight search 가 나오는데

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112929369.png)

거기에 Terminal 을 치고 엔터 치면 됩니다. ter 까지만 쳐도 실행 됩니다.

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948321.png)

복사한 텍스트를 붙여 넣기 하고 엔터 칩니다.

​		

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948342.png)

비밀번호를 입력하라고 나옵니다. 맥북 비밀번호를 입력합니다.

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948364.png)

계속하려면 엔터키, 중단하려면 아무 키나 누르라고 나옵니다. 엔터 키를 눌러 주면 설치가 됩니다.

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948396.png)

엔터키를 누르자마자 이것 저것 자동으로 설치해줍니다.

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948377.png)

Downloading Command Line 이라고 나오면 멈춰 있어 보여도 다운을 받는 중이니 기다려줍니다.

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948384.png)

기다리다 보면 뭔가 열심히 다운도 받고 설치도 합니다.

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948370.png)

설치가 완료되었습니다.

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948375.png)



brew -- version 을 입력해봤는데, brew 명령어가 아직 먹히지 않는데요,
`Warning: /opt/homebrew/bin is not in your PATH`에 나와 있듯이, 아직 PATH 에 등록이 되지 않아서 그렇습니다.
vim 에디터에 들어가서 수정해주면 되지만, 아래 한줄 입력으로 더 간단하게 등록할 수 있습니다.

```java
echo 'export PATH=/opt/homebrew/bin:$PATH' >> ~/.zshrc
```

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948370-5042588.png)




확인 하지 않으셔도 되지만, `vi ~/.zshrc`를 입력해서 직접 에디터를 실행해보면 아래 한줄이 자동으로 입력된 걸 확인할 수 있습니다.

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948382.png)

​	

혹시 위에꺼 따라하다가 vim 에디터를 키셨다면, :q 를 쓰고 엔터를 치면 나올 수 있습니다.
zshrc가 수정된 후에는 꼭 `source ~/.zshrc` 를 입력해서 반영을 해주셔야 합니다. 혹은 터미널을 껐다 켜도 됩니다.

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948427.png)



이제 brew 명령어가 정상적으로 잘 작동합니다!

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948452.png)

​	

brew --version 을 입력하면 버전 확인이 가능합니다.
brew update 로 업데이트도 가능합니다.

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948445.png)

​	이상입니다.

예시로 alfred 를 brew에서 설치해보겠습니다.

```sql
brew search alfred
```

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948447.png)

검색해보니 Casks로 존재합니다. Casks가 아니면 brew install alfred 만 입력하면 되지만, Casks이기 때문에 --cask 옵션을 붙여야 했었는데, 요즘 brew는 cask 옵션을 붙이지 않아도 되긴 하는 것 같더라고요.

```sql
brew install --cask alfred
```

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948411.png)

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948420.png)

명령어가 입력되고 조금 기다리면 스스로 다운로드와 인스톨을 진행합니다.

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948481.png)
설치가 완료되었다는 메시지가 나옵니다.

​	

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/brew.assets/img-20211024112948490.png)



applications 에 보니 정말 설치가 잘 되어 있는걸 확인 할 수 있습니다.

이제 원하는 프로그램들을 설치하실때는 두가지 선택지가 있으신겁니다.

1. 인터넷에 크롬 검색 -> 구글 크롬 홈페이지 -> 크롬 다운로드 클릭 -> 다운받아진 파일을 download 폴더에서 찾음 -> 해당 파일을 실행 -> 다음, 다음, 확인, 동의 버튼 누르며 설정 -> 설치 완료
2. Terminal 에 brew install --cask google-chrome 라고 입력. 끝. 



​	

brew 를 처음 설치할땐 조금 어려움을 느낄수도 있지만, 이제 부터는 뭐든 쉽게 설치하실 수 있습니다. 수고하셨습니다.

​	
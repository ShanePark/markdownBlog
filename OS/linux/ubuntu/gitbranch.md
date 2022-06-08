# Ubuntu 터미널 Git Branch 이름 나오게 하기

​			

**CLI 환경에서 Git을 사용 하다 보면 종종 작업중인 branch를 까먹고 잘못 된 branch에 작업을 하는 경우가 생깁니다.** 

Windows나 MacOS 에서는 Github Desktop과 Source Tree 라는 그런대로 쓸 만한 무료 Git GUI 툴들이 있지만 Linux 환경에서는 수요가 없어서 그런지 GUI툴이 잘 있지도 않고 그나마 Git Kraken의 기능이 소스트리에 비벼볼 만 하던데.. 문제는 private repository나 On-Premises 서버를 사용하는 경우 (Gitlab CE 등) 유로라는 겁니다. 처음에 일주일동안은 뭐 무료로 모든 기능을 사용하게 해주겠다 이러면서 어떤게 paid feature 고 어떤게 for free 인지를 모르게 하더니 일주일 지나니깐 사용중이던 repository 에서 남는게 없더군요..  

SmartGit이 원래 제일 많이 쓰인다고 해서 설치해보니 역시 유료.. 그래서 Open Source 중에 몇가지 다운 받아 봤을 때 그나마 기본적인 기능이라도 문제 없이 하는 Git cola를 사용중입니다. 파이썬으로 개발되었고 pull, push, commit, diff, 파일스테이징 모드 등을 제공 합니다.

​	

### Git-cola

```bash
sudo apt install git-cola
```

![image-20210924204431809](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gitbranch.assets/image-20210924204431809.png)

​		

어쨌든 여러가지 이유로 CLI 환경에서의 Git에도 익숙해 질 필요가 있습니다. Git branch 같은 경우는 GUI 환경에서 작업 할때도 잠깐 아차 하면 실수하게 되는데요, 그것마저 눈에 보이지 않는 환경에서는 실수 할 확률이 더 커지겠죠. 지금부터 간단하게 Terminal 에서 Git Branch 이름을 표시하는 작업을 해 보겠습니다.



programmers 라는 Git 저장소가 있는데요. 지금은 이게 Git 경로가 맞는지 알 방법이 없습니다.

![image-20210924203252180](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gitbranch.assets/image-20210924203252180.png)

​	

이제 필요한 작업을 합니다. .bashrc 파일을 수정 해 주어야 합니다.

```bash
vi ~/.bashrc
```

​		

그러면 Vi Editor 가 뜨는데, 저는 alias를 몇개 지정해 사용하다 보니 alias 에 대한 내용이 있지만 보통은 주석 투성이의 문서가 나올 겁니다.

![image-20210924204902699](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gitbranch.assets/image-20210924204902699.png)

​	

문서의 맨 아래로 가서(단축키 : G) 아래의 내용을 넣어 줍니다.

```bash
# git show Branch name
parse_git_branch() {
     git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/ (\1)/'
}
export PS1="\u@\h \[\033[32m\]\w\[\033[33m\]\$(parse_git_branch)\[\033[00m\] $ "

```

​				

​	![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gitbranch.assets/image-20210924205202428.png)

내용을 넣고 저장(wq)을 하고 나옵니다.	

​	

이제 변경사항을 적용 시켜 주어야 하는데요. .bashrc 파일은 Terminal을 켤 때 불러오는 파일 이기 때문에 보통은 터미널을 껐다 다시 켜야 적용이 됩니다. 하지만 아래와 같이 입력해서 ~/.bashrc 파일을 한번 읽어 주면

```bash
source ~/.bashrc
```

​	

굳이 껐다 켜지 않아도 바로 적용이 되는 것을 확인 할 수 있습니다. 저는 중간에 공백을 너무 많이 넣어서 띄어쓰기가 4개나 들어 갔었네요. 마지막에도 모양이 불편해 (main)과 $ 사이를 하나 더 띄웠는데요, 위에있는 코드블럭은 개선된 사항이니 안심하고 붙여 넣으시면 됩니다.

![image-20210924205441494](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gitbranch.assets/image-20210924205441494.png)	

​	

이번엔 새로운 브랜치로 이동 해 보겠습니다.	

```bash
git checkout -b test
```

​		

이제는 brach 이름이 test 로 바뀐게 바로 확인이 됩니다. 더이상 헷갈일 일이 없겠네요!

![image-20210924205933111](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gitbranch.assets/image-20210924205933111.png)

​	완성입니다.

​		

## oh my zsh

![ohmy](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gitbranch.assets/ohmy.png)

마지막으로 Git branch 이름이 표기되는 것에서 끝나지 않고, oh-my-zsh를 설치 해서 사용한다면 전체적인 Terminal 사용이 정말 쾌적해집니다. 특히나 Git branch 표시도  깔끔하게 되니 관심있는 분은 아래 링크를 확인 해 주세요.

[Ubuntu에 oh-my-zsh 설치](https://shanepark.tistory.com/248)




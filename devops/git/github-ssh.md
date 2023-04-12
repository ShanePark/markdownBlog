# Github / Gitlab SSH 공개 키 등록하기

​		

## Git CLI와 GUI

저는 MacOS에서 Github repository에 접근 할 때 Github Desktop을 사용 하고 있습니다.

<img src="https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210924233758006.webp" width=750 height=500 alt=1>

​	

Github Desktop의 완성도는 꽤나 괜찮은 편입니다. TypeScript로 작성 되어 있으며, [Release Note](https://desktop.github.com/release-notes)를 확인해 보시면 거의 1주일에 한번 꼴로 꾸준히 업데이트가 됩니다. 

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210924233902880.webp width=750 height=600 alt=2>

> 심지어는 Microsoft에 인수된게 무색하게 발빠르게 Apple M1 칩셋에 대응도 했습니다. https://github.com/shiftkey/desktop/ Linux 버전의 Fork도 있어서 한번 테스트 해 보려 합니다.

이렇게 빠르게 치고 올라온 배경에는 Github Desktop이 완전한 Open Source 라는 강점이 있습니다. 반면 atlassian의 Source tree의 경우에는 1년이 다 지나가는 아직까지도 배터리 이슈를 해결하지 못해 도태되어 가는 느낌입니다.

​	

하지만 GUI Git은 단순하게 Git CLI 환경에서 할 수 있는 일들을 좀 더 직관적으로 보기 좋게 해줄 뿐 사실 모든 Git의 기능을 지원하는 Git GUI는 없다고 봐도 됩니다. 단순하게 Fetch, Commit, Push&Pull 만 하고 diff 확인하고 staging 하는 정도라면 Git GUI 에서 할  수 있지만 (사실 해당 기능들도 CLI가 익숙하면 훨씬 빠르게 할 것 같습니다.) 그 외 날려먹은 데이터를 복구 해야 하는 경우가 온다거나 할 때 CLI 환경에서 꼭 작업 해야 하는 경우가 생깁니다. 

최근에야 Github Desktop, Source Tree모두 Cherry Picking도 지원해주고 Github Desktop 에서는 안되지만 Source Tree는 amend 기능도 있는 등등 여러가지 강력한 기능들을 제공 하지만, 확실히 Git의 모든 기능을 제공하는 건 CLI 뿐 입니다.

​	

특히 인증을 생각하면 무조건 SSH로 clone 해서 사용하는게 좋은데요. 

![image-20210924235506407](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210924235506407.webp)

당장에 Github Desktop 으로 clone한 repository인데, CLI 환경에 와서 fetch 만 하려고 해도 대뜸 인증을 요구합니다.

​	

그래서 아이디와 비밀번호를 입력 한다고 해도 fetch가 되지는 않습니다. 

![image-20210924235602403](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210924235602403.webp)

2021년 8월 13일 부로 비밀번호를 입력해 인증하는 걸 보안상의 이유로 막았기 떄문인데요, 8월 13일 이전이었다고 해도 저는 Github에서 2FA (Two-factor Authentication)을 사용하고 있기 때문에 단순히 비밀번호를 입력하는 것 만으로는 인증이 되지 않았을 겁니다.

​	

## Git SSH 접속

그래서 이제  SSH로 접속을 하는게 필요합니다. 단순히 CLI 환경에서 Git 을 사용 할 수 있을 뿐 만 아니라, 번거로운 인증 절차를 아주 효과적이고 높은 수준의 보안으로 간소화 시킬 수 있으며 SSH로 접속이 된다면 Git Client를 가리지 않는다는 정말 큰 장점이 있습니다.

간단하게 설명하자면 Open Source인 Linux용 Github Desktop을 이용해 사설 구축된 Git 저장소로의 접속 및 이용도 가능하다는 것이죠. SSH로 접속하는 걸 모르면 돈 내고 Git Kraken이나 SmartGit 같은걸 쓰게 될거를 SSH 접속을 함으로서 정말 많은 장점을 취할 수 있습니다.

​	

지금 부터 하나씩 함께 해 보겠습니다. 생각보다 그렇게 복잡하지 않습니다.

일단 Github에 들어가서 Settings -> SSH and GPG keys 에 들어갑니다.

![image-20210925001252838](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925001252838.webp)

​	

저는 회사에서 사용하는 노트북의 SSH를 이미 등록 해 두어서 키가 하나 보이는데요, 제 맥북을 한번 등록 해 보겠습니다.

SSH 키를 아직 생성 하지 않았거나 Windows를 사용 하는 분들은 [SSH key 생성하고, 서버에 등록해서 비밀번호 없이 접속하기](https://shanepark.tistory.com/195) 포스팅을 참고 해 주세요. 

* ssh 키 생성

```bash
ssh-keygen -t rsa
```

 	

이제 ssh 공개키를 확인 해야 합니다. 아래의 명령어를 입력 하면 

```bash
cat ~/.ssh/id_rsa.pub
```

![image-20210925001913278](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925001913278.webp)

​	

손쉽게 공개 키를 확인 할 수 있습니다. id_rsa.pub이 공개키고, id_rsa가 개인 키 인데 개인 키는 노출되지 않도록 정말 조심해야 합니다. 공개키는 노출되어도 괜찮습니다. 오히려 ssh 공개키를 여기 저기 등록 할 일이 많이 생깁니다.

​	

그러면 이제 Github 에서 New SSH key 버튼을 누르고

![image-20210925002110631](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925002110631.webp)

​	

Key를 등록 해 줍니다.

![image-20210925002147799](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925002147799.webp)

​	

이제  Key가 등록 되었습니다.

![image-20210925002251791](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925002251791.webp)

​	

아주 간단하게 테스트 할 수 있는데요. Terminal에 아래와 같이 입력합니다.

```bash
ssh git@github.com
```

SSH 키를 등록 하기 전에는 원래 이렇게 떴지만

![image-20210925002228813](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925002228813.webp)

등록이 완료 되면 인증이 완료 되었다는 문구를 확인 할 수 있습니다.

![image-20210925002319315](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925002319315.webp)

​	

## SSH로 Clone 하기

그러면 SSH를 등록 했는데 이걸로 무엇을 할 수 있을까 싶겠는데요 함께 해 보겠습니다.

일단 제가 Github 으로 인증 해서 Clone 해둔 programmers repository 입니다. private 저장소 인데요 아래 보이는 것 처럼 git fetch만 하더라도 인증을 요구합니다.

![image-20210925002540680](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925002540680.webp)

​	

```bash
rm -rf programmers
```

과감하게 programmers 저장소를 삭제합니다.

![image-20210925002632660](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925002632660.webp)

​	

이제 새로 clone을 받을 건데요, 해당  Github 저장소에 들어갑니다. 우측의 Code 버튼을 누르면

![image-20210925002736605](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925002736605.webp)

기본으로 HTTPS로 되어 있는데, 이걸 SSH로 바꿔줍니다. 그럼 git@으로 시작하는 주소가 나오는데요 이걸 복사해서,

​			

이번에는 SSH로 클론 해줍니다. 

```bash
git clone git@github.com:Shane-Park/programmers.git
```

![image-20210925002834356](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925002834356.webp)

아까 SSH 키를 등록 해 두었기 때문에 특별한 인증 절차 없이 바로 잘 됩니다.

​	

![image-20210925002930095](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925002930095.webp)

fetch 도 인증 없이 잘 이루어 집니다.

​	

이렇게 ssh를 통한 인증을 해 두었다면 CLI 뿐만 아니라 어떤 Git Client에서도 해당 Repository를 불러 와서 인증 문제 없이 사용 할 수 있습니다.

​	

## Gitlab CE SSH 접속하기

> Git SSH port가 다르다면?

이번에는 Gitlab CE 처럼 on premise로 Git 저장소를 구축 해 둔 경우에 접속을 하는 방법을 알아보겠습니다. 오늘 회사에 출근하자마자  회사 Gitlab의 SSH port를 여쭈어 봤는데요, 혼자 아무리 22번 포트로 연결을 시도 해도 connection  refused가 떴었기 때문입니다. 그래서 Git SSH 포트가 기본 포트가 아닌 경우에는 포트 번호도 알아야 접속 할 수 있습니다.

​	

gitlab 에서도 User Settings > SSH Keys 에 들어가면 키를 등록 할 수 있습니다.

![image-20210925004733769](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925004733769.webp)

​	

아까처럼 cat으로 공개키를 읽어 와 등록 해 줍니다.

```bash
cat ~/.ssh/id_rsa.pub
```

![image-20210925004857378](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925004857378.webp)

​	

성공적으로 키를 등록 했는데요, 아직은 아무리 접속을 시도 해도 아래와 같은 에러가 나옵니다. 22번 포트가 막혀 있습니다.

```bash
port 22: Connection refused
```

​	

그러면 이제 포트 번호를 명시해서 접속을 해야 하는데요. company 에는 회사 주소가 들어가면 됩니다.

```bash
ssh shane@gitlab.company.co.kr:31922
```

​	

그러면 또 이런 에러를 맞이하게 됩니다. 포트 번호가 well known 포트가 아니다 보니 그렇습니다.

```
ssh: Could not resolve hostname gitlab.company.co.kr:31922: nodename nor servname provided, or not known
```

​	

이제 해당 주소와 포트로 접속을 할 때  ssh로 접속 하게끔 등록을 해 주어야 합니다.

```bash
vi ~/.ssh/config
```

이렇게 .ssh 폴더의 config 파일을 열면 기존에 등록 해 둔 Host가 있으면 몇개가 뜰 거고 처음 설정하는 분은 아무것도 없을 수 있습니다.	

![image-20210925005423703](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925005423703.webp)

저는 몇개가 등록이 되어 있었는데요, 



이제 새로운 등록을 해 보겠습니다. Ubuntu 에서는  IdentityFile에 공개키를 했었는데 Mac에서는 개인 키를 걸어둬야 접속이 되더라고요. 이렇게 입력 하고 각자 사용하시는 company 주소 입력해서 저장 합니다.

```bash
# gitlab company
Host gitlab.company.co.kr
  Hostname gitlab.company.co.kr
  User shane
  Port 31922
  PreferredAuthentications publickey
  IdentityFile ~/.ssh/id_rsa.pub
```

​	

이제 테스트를 해보겠습니다. Github 방식으로 해보니 안되어서 Gitlab 사이트에 들어가 보니 조금 방식이 다르더라구요. 

https://docs.gitlab.com/ee/ssh/ 공식 사이트에서 제안하는 테스트 입니다.

![image-20210925011157365](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925011157365.webp)

```bash
 ssh -T git@gitlab.company.co.kr
```

접속 테스트를 해봅니다.

​		

```
 shane  ~/.ssh  ssh -T git@gitlab.argonet.co.kr
Load key "/Users/shane/.ssh/id_rsa.pub": invalid format
git@gitlab.argonet.co.kr: Permission denied (publickey).
```

저는 Load key invalid format 에러가 났었는데요. openssh 버전에서 뭔가 문제가 있다는 것 같더라고요 https://bbs.archlinux.org/viewtopic.php?pid=1914847 

​	

이런 에러가 나는 분들만, IdentityFile 부분의 마지막 .pub 을 지워 주세요. 저는 Ubuntu 에서는 id_rsa.pub 까지 썼는데 Mac 에서 이상하게 이러더라고요. 보통 chmod로 id_rsa를 400 으로 바꾸어서 해결해도 된다고 하던데 저는 해결되지 않았습니다.

```bash
IdentityFile ~/.ssh/id_rsa
```

​		

무튼 이렇게 Welcome to Gitlab 이 뜨면 성공입니다.

![image-20210925015623229](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925015623229.webp)

GItlab도 Github 에서 한 것 처럼 Repository 찾아가서, Clone 버튼 누르고 Clone with SSH 에 나온 주소를 복사해서 사용 하시면 됩니다. 그렇게 clone 하고 나서 fetch 까지 잘 이루어 지는 모습입니다.

![image-20210925015807676](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/git/github-ssh.assets/image-20210925015807676.webp)

​	

이렇게 Git SSH 등록을 해 보았습니다. 로그인 토큰을 만들어서 접속 하는 방법도 있지만 번거롭기도 하고 로그인 할 때도 매번 귀찮은데.. git credential helper store 를 쓰면 되기는 하지만.. 

SSH가 제일 편하고 한번 등록 해 두면 정말 잘 이용할 수 있고 좋은 듯 합니다. 특히 Source tree는 비밀번호 잘못 입력했다가는 잘못된 비밀번호를 저장해버리고 다시 묻지 않는 정말 귀찮은 상황이 벌어지기 떄문에 [Sourcetree 에서 잘못된 비밀번호로 저장소 접근 안될때 해결방법](https://shanepark.tistory.com/214) 더욱 SSH를 쓰는 편이 좋아 보입니다.

​	

수고하셨습니다.
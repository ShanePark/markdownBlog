# [Ubuntu 20.04 Git GUI 추천] 우분투 Github Desktop 설치

​	

해당 사진은 우분투에 MacOS 테마를 설치 해 두어서 Mac 처럼 보이긴 하지만 Ubuntu 가 맞습니다.

> 해당 테마에 관심이 있으신 분은 [Ubuntu MacOS 처럼 만들기 GTK Themes](https://shanepark.tistory.com/251) 포스팅을 참고해주세요.	

​	

​	

Windows나 MacOS에서는 소스트리가 Git GUI로 흔하게 사용됩니다. 아쉽게도 Linux에서는 사용 할 수 없는데요, 최근에는 m1 맥북이 나왔지만 소스트리가 적절히 대응을 해주지 못하고 있어 많이 아쉽기도 합니다. 심지어 Github Desktop의 치고나오는 속도가 상당해서 조만간 소스트리를 앞질러 줄 수 있지 않을까 기대하고 있습니다. Git Kraken, Smartgit 등은 다 유료인게 부담스러워서 쓸 수 없었고 Git-cola가 그나마 무료길래 한동안 사용했었는데 기능이 너무 제한적입니다. 한참을 고민 하다가 Github Desktop을 설치 해 보았는데 Windows/ MacOS 에서 사용했을 때와 기본 기능들은 100% 일치하게 사용 할 수 있습니다. 

​	

![image-20211001150250111](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/github-desktop.assets/image-20211001150250111-16330696617131.png)

Github Desktop은 순수 오픈소스입니다. 그래서 개발속도도 굉장히 빠르고 거의 매주 새로운 기능이 추가 되고 있습니다. 심지어 친절한 한 개발자가 fork 해서 Linux용으로 만들어 준 덕분에 리눅스에서도 사용 할 수 있게 되었습니다. GitLab CE 등 사설 저장소를 사용할때도 ssh 접속을 하면 인증도 문제가 없기 때문에 사실상 이거 하나면 더이상 다른 Git GUI를 알아볼 필요가 없습니다.

> Git SSH 접속에 대해선 [Github / Gitlab SSH 공개 키 등록하기](https://shanepark.tistory.com/247) 글을 참고해주세요.

​	

## Github Desktop 설치하기

설치 자체는 어렵지 않습니다. https://github.com/shiftkey/desktop 저장소에 모든 게 있는데요.

​	

### Debian/ Ubuntu의 경우

package 저장소를 셋업 합니다.

```bash
$ wget -qO - https://packagecloud.io/shiftkey/desktop/gpgkey | sudo tee /etc/apt/trusted.gpg.d/shiftkey-desktop.asc > /dev/null
$ sudo sh -c 'echo "deb [arch=amd64] https://packagecloud.io/shiftkey/desktop/any/ any main" > /etc/apt/sources.list.d/packagecloud-shiftkey-desktop.list'
$ sudo apt-get update

```

Github Desktop을 설치 합니다.

```bash
$ sudo apt install github-desktop
```

​	

### Red Hat/ CentOS/ Fedora 배포판

저장소 셋업 및 설치

```bash
$ sudo rpm --import https://packagecloud.io/shiftkey/desktop/gpgkey
$ sudo sh -c 'echo -e "[shiftkey]\nname=GitHub Desktop\nbaseurl=https://packagecloud.io/shiftkey/desktop/el/7/\$basearch\nenabled=1\ngpgcheck=0\nrepo_gpgcheck=1\ngpgkey=https://packagecloud.io/shiftkey/desktop/gpgkey" > /etc/yum.repos.d/shiftkey-desktop.repo'

```

```bash
# if yum is your package manager
$ sudo yum install github-desktop

# if dnf is your package manager
$ sudo dnf install github-desktop
```

​	

다만 이렇게 설치했다가 버전이 너무 낮은 경우에는 완전 최신의 버전은 사용하지 못할 수 있습니다. 안정화가 완료 된 버전 위주로 apt 배포를 하기 때문인데요. 최신 버전을 사용하고 싶다면 아래에서 버전을 선택 할 수 있습니다. 저는 최신의 2.9.3을 사용하고 있습니다. 2.8.0 버전에서는 버그가 있었거든요.

> 2.9.0에서도 여전히 버그가 있었지만 2.9.2 부터 해결 된 듯 합니다. https://github.com/shiftkey/desktop/issues/508#event-5389982619

https://github.com/shiftkey/desktop/tags

![image-20211001151326714](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/github-desktop.assets/image-20211001151326714.png)

​	

위에서 버전을 선택 해서 들어 간 뒤에

​	

![image-20211001151553242](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/github-desktop.assets/image-20211001151553242.png)

아래에서 본인의 리눅스에 맞는 설치파일을 다운 받으시면 됩니다. 우분투에서는 .deb 파일을 받아 설치하면 되겠습니다.

​		

설치 후에는 Github Desktop을 검색해서 사용하시면 됩니다. 이상입니다.

![image-20211001151657293](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/github-desktop.assets/image-20211001151657293.png)
# SSH key 생성하고, 서버에 등록해서 비밀번호 없이 접속하기

매번 SSH 접속 할 때 마다 비밀번호 입력하려면 매우 번거롭습니다. 이 때, 신뢰할 수 있는 컴퓨터라면 공개키를 등록 해서 비밀번호 없이 간편하게 접속 할 수 있습니다.

아래와 같이 public key가 있다면 SSH Key 생성 과정은 건너 띌 수 있습니다.

![](https://github.com/Shane-Park/markdownBlog/raw/master/ssh/sshkey.assets/image-20210810153821908.png)



## SSH Key 생성하기

ssh-keygen이 있다면 아래의 명령어를 입력해 간단하게 생성 할 수 있습니다.

Git 을 설치하면서 ssh-keygen 이 딸려왔는지 아니면 wsl2를 설치하면서 왔는지 ssh-keygen이 있었는데 없다면 ssh-keygen을 먼저 설치 해 주셔야 합니다.

WSL이 아직 없다면 [해당 링크](https://shanepark.tistory.com/187) 를 참고해서 먼저 설치해주세요.

```shell
ssh-keygen -t rsa
```

위의 명령을 입력하면 이후 3개의 값을 입력하게 됩니다.

1. 키를 저장할 경로( 기본값 : $HOME/.ssh/id_rsa)
2. passphrase (추가로 사용할 암호, 기본값 없음)
3. passphrase 확인

passphrase는 굳이 입력 하지 않아도 되며, 키를 저장할 경로도 특별한 이유가 없다면 바꾸지 않는 것이 좋습니다.



그러면 .ssh 폴더 아래에 키가 생성됩니다.

![image-20210810153818487](https://github.com/Shane-Park/markdownBlog/raw/master/ssh/sshkey.assets/image-20210810153818487.png)

.pub이 공개키 인데 해당 공개키를 서버에 등록 하면 다음부터 비밀번호를 입력 할 필요가 없습니다.

SSH로 리눅스 서버에 접속해서

```shell
$ mkdir ~/.ssh
```

를 입력해 /.ssh 폴더가 없다면 .ssh 폴더를 만들어줍니다. 그러고는 아래와 같이 입력해서

```shell
$ touch ~/.ssh/authorized_keys
```

authorized_keys 파일을 만들어주고 아래의 입력으로

```bash
chmod 755 ~/.ssh/authorized_keys
```

읽기 쓰기 권한을 설정합니다. 후에는 id_rsa.pub 의 내용을 authorized_keys 파일에 append 해줍니다.

scp를 사용해도 되고 vi를 이용해 붙여넣기를 해도 상관 없습니다.

id_rsa.pub 에 있는 모든 내용을 복사해서

![image-20210810154227911](https://github.com/Shane-Park/markdownBlog/raw/master/ssh/sshkey.assets/image-20210810154227911.png)

접속한 서버의 .ssh 폴더 내에 있는 authorized_keys 파일을 수정해 해당 내용을 모두 붙여 넣고 저장합니다.

```shell
$ vi authorized_keys
```



이제 SSH 접속을 해 보면 더이상 비밀번호를 요구하지 않습니다 !

또한 SSH 접속을 할때 ubuntu@222.123.123.123 이런식으로 입력을 했다면, 아이피 주소를 외우기가 곤란 하기 때문에 매번 귀찮은 상황이 발생 했을 텐데요,

사용자의 .ssh 폴더 내에 확장자가 없는 config 파일을 만들고

![image-20210810154550934](https://github.com/Shane-Park/markdownBlog/raw/master/ssh/sshkey.assets/image-20210810154550934.png)

```json
Host myserver
    HostName 222.123.123.123
    Port 2022
    User ubuntu
```

이런식으로 설정 파일을 저장 해 둔다면,

```she
$ ssh myserver
```

만 입력 하면 간단하게 미리 저장해둔 접속 정보로 SSH 접속을 할 수 있습니다.

config에는 여러 개의 서버 접속 정보도 모두 입력 해 둘 수 있기 때문에 아주 간편합니다.



이상입니다.
# Linux) SSH 비밀번호 로그인 차단하기

한창 작업을 하다가 문득 제가 집에서 돌리고 있는 컴퓨터에 얼마나 자주 접속을 하고 있는지 궁금해졌습니다. 

혼자 사용하는 서버인데도 종종 너무 느려지는것도 이상하고 해서 별 생각 없이 처음으로 로그를 한번 확인 해 보았습니다.

```bash
cat /var/log/auth.log
```

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ssh/ssh-only-key.assets/image-20211006203126847.webp width=750 height=500 alt=1>

세상에나 무수히 많은 Connection 시도와 Failed password 가 수북하게 쌓여 있습니다. 

루마니아 부터 시작해서 온갖 국가의 아이피로부터 시도가 되었고 아이디도 뭐 David, ftpuser, user, uknown 등등 다 넣어봤더라고요. port 번호가 다 이상한게 써있어서 저희집 공유기 port forwarding을 다 확인 해 보았는데 저런 포트는 모두 잘 닫혀 있었습니다.

그래서 다시 알아보니 잘 저기에 표시된 port 번호는 제 컴퓨터의 포트가 아닌 접속을 시도하는 원격 컴퓨터들의 port 번호라고 합니다. 그러면 어쨌든 22번 포트를 이용해 ssh 접속을 시도한게 맞는 것 같습니다.

​		

## BASH HISTORY 확인	

쉘 변수중 $HISTSIZE 가 리눅스에서 사용했던 명령어들의 히스토리를 몇개까지 쌓아 보관할지를 정하는데요

```bash
echo $HISTSIZE
```

를 입력하면 그 값을 볼 수 있습니다.

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ssh/ssh-only-key.assets/image-20211006204205296.webp width=750 height=500 alt=1>

저는 최대 1000개 까지 저장하고 있습니다.

​	

이제 히스토리들을 한번 확인 해 볼 건데요. 

```bash
history 500
```

이렇게 치면 500개까지의 최근 내역을 볼 수 있습니다.

​	

제가 우분투를 설치한 지 얼마 안된 컴퓨터다 보니 천개가 채 안되더라고요. 그래서 쭉 다 로그를 확인 해 보니 다행히도 bash가 털린 기록은 없었습니다.

![image-20211006204316916](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ssh/ssh-only-key.assets/image-20211006204316916.webp)

​	

## SSH 비밀번호로 접속 하기 못하게 막기

아직 털린 기록이 없기는 하지만 저런 무작위 대입 공격이 썩 달갑지는 않습니다. 쉽게 뚫리지는 않는다고 해도 누군가 악의적으로 공격을 한다면 언젠가는 뚫리게 되어있습니다. 조금이라도 보안을 높이기 위해 비밀번호 입력을 통한 접속을 아에 차단 시켜 보겠습니다.

![image-20211006205618917](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ssh/ssh-only-key.assets/image-20211006205618917.webp)

일단 테스트를 위해 ssh 키를 등록 하지 않은, 비밀번호로 접속 할 컴퓨터가 필요 했습니다. 그래서 오라클 클라우드에 띄워 놓은 무료 인스턴스 중 하나로 접속 한 다음에 그 컴퓨터를 이용해 접속 했습니다.

​		

ssh 에 대한 설정은 아래의 파일에서 합니다. 관리자 권한으로만 편집이 됩니다.

```bash
 sudo vi /etc/ssh/sshd_config
```

​	![image-20211006205921562](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ssh/ssh-only-key.assets/image-20211006205921562.webp)

#으로 시작하는 주석을 제외 하고 다른 번호를 쓰면 ssh에 사용하는 Port 번호도 변경 할 수 있습니다. 왠만하면 바꾸는게 좋겠죠.

​		

Password 로 검색을 해봅니다.

```bash
/Password
```

![image-20211006210112252](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ssh/ssh-only-key.assets/image-20211006210112252.webp)

바로 해당하는 옵션을 찾을 수 있네요.

PasswordAuthentication 을 no 로 변경하면 패스워드 접근을 차단합니다.

​	

그외에는 

### PubkeyAuthentication

> 로그인시 공개키 방식에 대한 허용 여부 설정

### PermitRootLogin

> ssh를 이용한 root 계정 로그인 허용 여부

### MaxAuthTries

> 최대 접속 시도 횟수

등을 설정 할 수 있습니다.

​	

![image-20211006210534331](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ssh/ssh-only-key.assets/image-20211006210534331.webp)

PasswordAuthentication 을 no 로 변경했으니 한번 시도 해 보겠습니다.

그 전에 일단  sshd 데몬을 재시작 한번 해줘야 합니다.

```bash
systemctl restart sshd
```

​	

## 접속 시도

![image-20211006210706478](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ssh/ssh-only-key.assets/image-20211006210706478.webp)

네. 이제 비밀번호를 입력한 접속을 아주 단호하게 거부합니다.

​	

하지만 평소처럼 SSH로 접속을 한다면?

![image-20211006210739375](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ssh/ssh-only-key.assets/image-20211006210739375.webp)

접속이 아주 잘 됩니다.

우연한 기회에 좋은 경험을 통해 보안을 한층 쌓아올린 듯 하여 기분이 좋습니다. 

운영중인 서버가 있다면 한번씩 확인 해 보세요.
# Linux, sudo 없이 명령어 실행하기 (예:docker)

## 에러

Got permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock: Get "http://%2Fvar%2Frun%2Fdocker.sock/v1.24/containers/json": dial unix /var/run/docker.sock: connect: permission denied 에러가 발생 했습니다.

```
Got permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock: Get "http://%2Fvar%2Frun%2Fdocker.sock/v1.24/containers/json": dial unix /var/run/docker.sock: connect: permission denied

```

무심히 도커 명령어를 사용하려 할 때 마다 permission denied가 나오는 경험을 많이들 해보셨을 겁니다.

![image-20210929204332090](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/usermod.assets/image-20210929204332090.webp)

비단 docker 뿐만이 아니라, 몇몇 다른 어플리케이션도 굳이 루트 권한 없이 실행했으면 하는게 있을 텐데요, 그럴 때는 간단한 설정 하나 만으로 특정 유저에게 해당 어플리케이션의 사용 권한을 줄 수 있습니다.

## 해결	

```bash
sudo usermod -aG docker {사용자명}
```

위의 명령어를 입력하면 간단하게 사용자들 docker 그룹에 추가 할 수 있는데요, 

- -a 옵션은 사용자를 추가하는 명령어 (append)

- -G 명령어는 그룹 옵션 입니다 (Group)

<br><br>

현재 사용자 아이디를 굳이 직접 칠 필요도 없는데요. $USER 는 지금 접속중인 사용자를 나타냅니다.

![image-20210929204954135](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/usermod.assets/image-20210929204954135.webp)

​	

그러니 한 명령어로 나타낸다면 

```bash
sudo usermod -aG docker $USER
```

를 입력하면 간단하게 해결 됩니다.

​		

명령어를 입력 했으니 이제 docker 명령어를 써 보려고 하면

![image-20210929205038976](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/usermod.assets/image-20210929205038976.webp)

여전히 안되는 것 처럼 보이는데요, 새로 등록 된 그룹은 새로 로그인 했을 때부터 적용됩니다.

```bash
logout
```

을 입력하면 로그아웃 됩니다.

​	

![image-20210929205239562](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/usermod.assets/image-20210929205239562.webp)

이후 다시 로그인 해서 docker 명령어를 사용 해 보았습니다. sudo 권한이 없이도 (Super User Do 의 약자입니다.) 간단하게 docker 명령어를 이용할 수 있습니다.

​	

그닥 복잡하지 않지만 한번 해두면 앞으로 많은 시간을 절약 할 수 있습니다.!


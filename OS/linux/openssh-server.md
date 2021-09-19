# Ubuntu 20.04 LTS ) SSH 접속 허용하기

![Enable SSH on Ubuntu 18.04](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/openssh-server.assets/featured_hud1de18774fc6feb5ae8168196a42a70d_96244_768x0_resize_q75_lanczos.jpg)		

> https://linuxize.com/post/how-to-enable-ssh-on-ubuntu-20-04/

​	

Ubuntu 를 설치 했다면 상황에 따라 외부에서의 SSH 를 허용 해 두어야 할 경우가 있습니다. 보통의 경우 ubuntu 를 설치 했을 때 openssh 서버가 설치 되어 있지 않기 때문에 설치를 해 주어야 합니다.

차근차근 현재 사용중인 Ubuntu 디바이스의 SSH 접속을 허용해 보도록 하겠습니다.

> 참고로 외부 ip 에서의 접속까지 허용을 하려면 공유기에서 22번 port를 port forwarding 해주어야 합니다. iptime 을 사용 한다면 192.168.0.1 로 접속해서 특정 로컬아이피의 포트를 포트 포워딩 해 주면 됩니다.



## 전제조건

반드시 sudo 권한으로 로그인 해야 합니다.

​	

## SSH 기능 켜기

처음에는 openssh-server 를 설치 해줘야 합니다.

```bash
sudo apt update
sudo apt install openssh-server

```

![image-20210919214413094](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/openssh-server.assets/image-20210919214413094.png)

​	

일단 설치가 완료 되면 SSH 서비스가 자동으로 시작됩니다. SSH가 설치 되고 서비스가 정상적으로 구동되고 있는 것을 확인 하기 위해 아래의 커맨드를 입력 합니다.

```bash
sudo systemctl status ssh

```

![image-20210919214607301](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/openssh-server.assets/image-20210919214607301.png)

> 정상적으로 ssh 서비스가 구동 되고 있습니다.

​	

우분투는 기본적으로 UFW 라고 불리는 방화벽 시스템이 있습니다. SSH 포트를 열어줘야 합니다.

```bash
sudo ufw allow ssh
```

![image-20210919214738075](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/openssh-server.assets/image-20210919214738075.png)

​	

SSH가 준비 되었고, 접속 준비가 완료 되었습니다. Linux나 MacOS에서는 SSH client가 기본적으로 설치 되어 있기 때문에 바로 접속을 해 볼 수 있습니다.

​	

내부 아이피는 아래 명령어를 입력해 알 수 있습니다.

```bash
ip a
```

![image-20210919214945303](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/openssh-server.assets/image-20210919214945303.png)

내부아이피가 192.168.0.32 로 확인 됩니다.

​		

제가 가지고 있는 맥북에서 iterm으로 한번 접속 해 보겠습니다.

ssh {계정명}@{ip주소} 형태로 입력 합니다.

```bash
ssh shane@192.168.0.32
```

​	처음에 접속 할 때는 아래와 같이 한번 확인을 해 주는데요, yes를 입력 합니다.

![1 (copy)](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/openssh-server.assets/1 (copy).png)

​	 	

그러면 비밀번호를 입력 하라고 합니다.

비밀번호를 입력 합니다.

![2 (copy)](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/openssh-server.assets/2 (copy).png)

​			

![3 (copy)](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/openssh-server.assets/3 (copy).png)

정말로 접속이 되었습니다.

​	

이번에는 외부에서 접속 하는 것을 해 보겠습니다. 집에서 서버로 사용 할 다른 노트북에 똑같이 ssh 서버를 설치 했습니다.

일단 공유기 설정(192.168.0.1)에 들어가서 Port Forwarding에 22번 port를 추가 해 주었습니다. Apply를 눌러줍니다.

![image-20210919220416348](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/openssh-server.assets/image-20210919220416348.png)

​		

해당 포트를 열고 나서는 외부 아이피로도 ssh 접속을 할 수 있습니다.

![Screenshot from 2021-09-19 22-06-30](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/openssh-server.assets/Screenshot from 2021-09-19 22-06-30.png)

​	

생각보다 어렵지 않았지만, SSH 접속이 가능해졌기 때문에 정말 많은 것을 할 수 있게 되었습니다.
# Ubuntu 20.04 LTS ) Docker 설치하기

​	

![Empowering App Development for Developers | Docker](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/docker.assets/docker_facebook_share.png)

Docker는 Windows 에서 Ubuntu 로 넘어오게 된 가장 큰 계기입니다. Docker를 사용하면 정말 편하게 격리된 컨테이너들을 구성해 가상화의 장점을 살릴 수 있습니다. 사실상 업계 표준인 만큼 접근성이 높으며 사용에 굉장히 편리합니다. 관련 레퍼런스도 어렵지 않게 찾아 볼 수 있으며 사용자들이 작성해 둔 패키지/이미지들이 넘쳐나기 때문에 뭔가를 정말 간단하게 할 수 있습니다. 윈도우즈에서도 WSL2(Windows Subsystem for Linux)를 이용해 사용은 가능 했지만 메모리나 안정성 문제로 불편함이 있었습니다.

https://docs.docker.com/engine/install/ubuntu/

위의 링크에서 더 자세한 설명을 확인 할 수 있습니다.	

​	

## Docker 설치

​	

#### 오래된 버전 삭제하기

혹시 기존의 버전이 있는지 확실히 할 수 있으며, 있다면 최신 버전 설치를 위해 삭제 해줍니다.

```bash
sudo apt-get remove docker docker-engine docker.io containerd runc
```

#### 	

#### repository 설정하기

apt package index를 업데이트 하고 HTTPS를 통해 repository 를 이용하기 위해 pakcage 들을 설치 해줍니다.

```bash
$ sudo apt-get update
 
$ sudo apt-get -y install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
    
```

Docker의 Official GPG Key 를 등록합니다.

```bash
 curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

```

stable repository 를 등록해줍니다.

```bash
 echo \
  "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```

​			

#### Docker Engine 설치하기

아래 명령어를 입력하면 자동으로 최신 버전이 설치 됩니다.

```bash
$ sudo apt-get update
 
$ sudo apt-get install docker-ce docker-ce-cli containerd.io
 
```

​	

#### 설치 완료

설치가 완료된 후에는 

```bash
docker --version
```

를 입력해서 버전을 확인 하거나

![image-20210919181521058](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/docker.assets/image-20210919181521058.png)

​	혹은 hello-world 이미지를 실행 시켜 봅니다.

```bash
 sudo docker run hello-world
```

![image-20210919181654082](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/docker.assets/image-20210919181654082.png)

​			

## Compose 설치

Docker Compose는 여러개의 도커 어플리케이션 컨테이너들을 정의하고 실행 할 수 있게 도와주는 툴 입니다. YAML 파일을 사용해 어플리케이션의 서비스를 설정하고 하나의 커맨드만으로 여러개의 도커 컨테이너들을 사용 할 수 있습니다.

Docker 를 설치 해도 Compose 가 딸려 오는 것은 아니기 때문에 따로 설치 해 주어야 합니다.

​	

```bash
 sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

```

​	

실행 할 수 있는 권한을 부여 합니다.

```bash
 sudo chmod +x /usr/local/bin/docker-compose

```

​	

잘 설치 되었는지 확인해봅니다.

```bash
$ docker-compose --version
docker-compose version 1.29.2, build 5becea4c

```

​		

Windows 나 Mac 에서는 Docker Desktop 이라는 GUI Tool 이 따로 존재하지만, Linux 환경에서는 없어서 굳이 GUI 환경을 사용 하려면 서드파티 어플리케이션을 이용해야 하는 것 같습니다.


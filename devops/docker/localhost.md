# docker container에서 localhost 접속하기 host.docker.internal

​	

### docker에서 컨테이너들이 실행 중인 서버를 바라 봐야 할 경우가 있습니다. 

 Docker Compose를 이용해 여러개의 도커 컨테이너를 실행해서 하나의 어플리케이션을 담당한다고 했을때, 하나의 네트워크로 연결해 사용하게 되는데요. 정작 컨테이너에서 로컬 서버에는 어떻게 접근해야 할까요?

![image-20210821144823960](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821144823960.png)

제 맥북에서 사용중인 Docker에는 컨테이너가 아직 PostgreSQL 하나밖에 없어서, 이 컨테이너를 이용해서 시도 해 보겠습니다.

​	

제 컨테이너 같이 DB 서버라면 사실 필요할 일은 없지만, Apache나 NginX 같은 웹 서버의 경우는 흔하게 필요한 상황이 발생 할 수 있겠습니다.

​	

일단 해당 Container로 접속 해 보겠습니다.

### GUI환경이라면

![image-20210821145005719](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821145005719.png)

위에 보이는 CLI 버튼으로 쉽게 접속 해 터미널을 띄울 수 있습니다.

![image-20210821145031263](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821145031263.png)

간단하죠. 그렇다면 이번에는 CLI 환경에서 한번 차근차근 띄워보겠습니다.

​	

### 1. 터미널을 켭니다

![image-20210821145111588](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821145111588.png)

​	

### 2. 실행중인 Docker 컨테이너 목록

```shell
docker ps
```

명령어로 가동중인 컨테이너들을 볼 수 있습니다. -a 옵션을 붙이면 멈춰있는 컨테이너도 다 표시해줍니다.

![image-20210821145225043](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821145225043.png)

그럼 제일 왼쪽에 79..로 시작하는게 해당 컨테이너의 ID 입니다.

그럼 이제 docker exec 명령어를 통해 해당 컨테이너의 STDIN 표준 입출력을 열어 가상 TTY를 통해 접속합니다.

```bash
 docker exec -it 79edaba9d6cb /bin/bash
```



![image-20210821145747468](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821145747468.png)



그러면 이제 해당 컨테이너에 접속이 완료 된 상태입니다.

​	

### 3. cURL 설치

![image-20210821145813776](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821145813776.png)

curl 을 이용해 테스트 해 보려 했는데 curl이 없습니다.

​	

```bash
apt update
```

로 업데이트를 한번 해주고

![image-20210821145843706](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821145843706.png)

```bash
apt install curl
```



curl을 설치 해 줍니다.

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821150012235.png)

당연히 Y 를 입력해야 설치가 됩니다. 

​	

이제 localhost로 curl 명령을 보내 보겠습니다.

```bash
curl localhost:8080
```

​	

![image-20210821150042104](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821150042104.png)

Failed to connect to localhost port 8080: Connection refused 가 나오네요..

​	

하지만

![image-20210821150118245](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821150118245.png)

​		

저는 분명 localhost에 서버를 하나 띄워 두었습니다.

로컬에서 같은 명령어를 입력 하면

![image-20210821150150657](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821150150657.png)

잘 받아오는 것을 확인할 수 있습니다. Docker에서 localhost는 컨테이너 그 자신의 주소기 때문에 이런 문제가 발생 한 것 입니다.

​	

​	

## 그렇다면 docker container에서 localhost를 어떻게 접근할까?

​	

### 1. 첫번째로 아주 단순하지만 좋지 않은 방법이 있습니다.

바로 내부 아이피를 이용하는 것 입니다. Windows에서는 ipconfig를, Mac에서는  

```bash
ipconfig getifaddr en0
```



를 입력해 간단히 내부아이피 주소를 찾을 수 있습니다.

![image-20210821150703240](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821150703240.png)

그 다음 해당 내부아이피 주소를 이용하면 됩니다.

```bash
curl 192.168.0.19:8080
```



![image-20210821150740984](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821150740984.png)

물론, 접속이 잘 되지만 이건 좋은 방법이 아닙니다. 내부아이피야 인터넷 사용하는 환경에 따라 매번 달라질 수 있는데 그럴때마다 수정을 할 수도 없고 번거롭습니다.

​	

​	

### 2. host.docker.internal를 이용합니다.

```bash
curl host.docker.internal:8080
```



![image-20210821150858573](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/localhost.assets/image-20210821150858573.png)

아주 간단하죠. 환경에 따라 변할 걱정도 할 필요가 없습니다.

이상입니다.






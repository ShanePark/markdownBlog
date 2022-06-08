

# Windows) 컴퓨터 부팅시 마다 발생하는 Docker 에러 해결하기

​	

## Docker 에러 

요즘 컴퓨터를 켤 때마다 docker가 실행되며 에러가 나는데 정말 불편합니다. wsl2를 위해 설치한 Ubuntu 혹은 Docker에 문제가 있는데, wsl2 는 계속 문제가 없었기 때문에 얼마전 업데이트가 된 Docker에서 문제가 발생했을 확률이 크다고 생각 했습니다.

​	

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/distro-error.assets/image-20210914091052614.png)

발생하는 에러의 내용은 아래와 같습니다.

- Microsfot .NET Framework

>  응용 프로그램에서 처리되지 않은 예외가 발생했습니다. [계속] 을 클릭하면 응용 프로그램에서 이 오류를 무시하고 계속합니다. [끝내기] 를 클릭하면 응용 프로그램이 즉시 닫힙니다.
>
> distro stopped unexpectedly

- docker

> An error occured 
>
> System.InvalidOperationException : distro stopped unexpectedly

​	

지금까지는 컴퓨터를 껐다 켜는 방법으로 대응했는데, 이게 컴퓨터를 껐다 켜도 여전히 같은 에러가 발생할 때가 있어서 시간낭비가 심하다고 생각 했습니다.

​		

그래서 간단하게 서비스 재시작 만으로 일단 문제를 해결 할 수 있습니다.

시작 버튼을 누르고 검색에 CMD 를 입력 해 명령 프롬프트를 찾습니다. 관리자 권한으로 실행을 합니다.

![image-20210914084014483](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/distro-error.assets/image-20210914084014483.png)

​	

이제 아래의 명령어를 연달아 입력 합니다.

```bash
net stop lxssmanager
net start lxssmanager
```

​	

그러면 이제 에러 상황에 따라 lxssmanager 서비스가 실행이 되는 중 일 때도 있고, 실행 조차 안되었을 때가 있습니다. 어느 상황에서든 서비스를 재 시작 해야 하기 때문에 stop을 먼저 하는 것이 좋습니다.

![lxssmanager](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/distro-error.assets/lxssmanager.png)

![image-20210914084338772](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/distro-error.assets/image-20210914084338772.png)

​	

이렇게 리눅스 서비스를 재 시작 하고 나서 Docker를 다시 실행 해 보면,

![image-20210914084834063](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/distro-error.assets/image-20210914084834063.png)

> 아무 문제 없이 Docker가 잘 구동됩니다.

​		

앞으로는 번거롭게 재 시작 하지 않고 간단하게 서비스만 다시 띄우는 걸로 해결이 좀 더 간단해 졌습니다.

이제 근본적인 문제도 해결해야 할 차례인데, 정확한 원인이 밝혀지지 않았기 때문에

![image-20210914085708986](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/distro-error.assets/image-20210914085708986.png)

마침 또 Docker가 새로운 update가 나와서 업데이트를 해 보았습니다만, 업데이트 후에도 증상이 계속 같았습니다.

​		

![image-20210914085533769](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/distro-error.assets/image-20210914085533769.png)

![image-20210914085555061](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/distro-error.assets/image-20210914085555061.png)

​	

그래서 Ubuntu 앱을 한번 초기화 해 보았습니다. 그 후로는 당장 에러가 발생하지 않는데 아마 설정값 등 뭔가 꼬인게 있었지 않았을 까 싶습니다. 그래도 완전히 해결되었는지는 좀 더 두고 봐야 할 것 같습니다.

​	

Ubuntu 앱을 새로 설치 하면 기존의 셋팅이 삭제되어 새로 세팅해야 하기 때문에 신중하게 결정하셔야 합니다. 일단 wsl에 배포를 다시 등록해 주고, --set-default, -s Ubuntu 명령어로 기본 wsl 배포로 다시 설정해 주셔야 합니다. 또한 docker 이미지가 꼬일 확률이 있어서 새로 이미지를 빌드 해야 할 수도 있습니다. docker volume 자체는 windows에서

```
\\wsl$\docker-desktop-data\version-pack-data\community\docker\volumes\docker-compose-dev_db
```

이런식으로 hidden network share 에서 접근 할 수 있으며 

​	

물리적인 저장 위치는 아래와 같습니다.

```
C:\Users\(사용자이름)\AppData\Local\Docker\wsl\data\ext4.vhdx
```

​	

그래서 Ubuntu 를 새로 설치한다고 데이터가 날라가진 않으니 너무 걱정하지 않으셔도 되지만 해당 사항들을 해결 할 수 없는 분들은 한동안 lxssmanager 로 임시 대응을 하셔야 합니다.

​	

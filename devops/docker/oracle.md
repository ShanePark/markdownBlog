# Docker로 Oracle Database 11g 띄우기

​	

## 주의

Apple Silicon arm64 방식의 Mac 에서는 적용되지 않습니다. 저도 m1 맥북에어를 사용중이기 때문에 도커에서 오라클 데이터베이스를 띄워보려고 여러 노력을 해봤었지만 지금까지도 전혀 방법이 없습니다. Apple Silicon 환경에서의 오라클 데이터 베이스 사용은 아래 링크를 참고해주세요. 

> [Apple Silicon m1 맥북에서 Oracle Database 사용하기](https://shanepark.tistory.com/208)



## 서론	

예전에 학원에 다닐 때 중간, 최종 프로젝트로 만들어 두었던 프로젝트들을 원래 windows 노트북에서 서버처럼 띄워 두었었는데 툭하면 윈도우 업데이트 등으로 종종 컴퓨터가 멋대로 재시작되는 경험을 하고, 외부에서 접속할때도 ssh 접속이 안되기 때문에  Windows Remote Desktop 등을 사용 하다가 이번에 집에 있는 노트북의 윈도우를 밀어 버리고 ubuntu를 설치했습니다.

다시 서버들과 데이터베이스를 실행하기 위해 오라클 데이터베이스 11g 를 설치하려고 하는데, 작년 하반기쯤 부터엔가 오라클사에서 과거 버전을 다운 받을 수 없도록 링크를 전부 끊어버리는 바람에  찾을 수가 없습니다. 정말 열심히 찾아봤지만 너무 까다로웠고 게다가 지금처럼 서버를 이전하는 경우가 생겼을 때 가상 컨테이너 환경이었다면 훨씬 쉽게 했을거란 생각이 들어 이번에는 Docker 기반으로 오라클 데이터베이스를 설치하기로 했습니다.

​	

## Oracle Dabase

오라클은 공식적인 도커 이미지가 없다보니 몇몇 사람들이 만들어 둔 이미지를 사용해야 합니다. 몇가지가 있긴 했지만 아래의 이미지가 보편적으로 사용 됩니다. 아시겠지만 --name은 컨테이너 이름, -d는 백그라운드에서 실행 -p는 포트에 대한 옵션 입니다.

```bash
docker run --name oracle11g -d -p 1521:1521 jaspeen/oracle-xe-11g
```

이미지가 없다면 금새 다운을 받게 됩니다.

![image-20210920123851964](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/oracle.assets/image-20210920123851964.png)

​	

실행이 잘 되었느지 docker container의 상태를 확인 해 봅니다.		

```bash
docker ps
```

![image-20210920123931777](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/oracle.assets/image-20210920123931777.png)

​		

데이터 베이스가 컨테이너가 돌아가자마자 바로 준비가 되진 않고 운영에 관련된 설정을 하고, oracle instance를 세팅 하고 데이터베이스에 필요한 파일들을 복사 하는데 시간이 좀 걸리는데요. docker logs {컨테이너이름} 명령을 입력 해서 로그를 확인 해 볼 수 있습니다.

```bash
docker logs oracle11g
```

​	

![image-20210920124342439](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/oracle.assets/image-20210920124342439.png)

데이터베이스가 준비 되었다고 하네요 ! 

​	

​	

## Container 접속

​	

이제 컨테이너에 접속을 해봅니다. 

docker exec -it {컨테이너이름} bash 명령어르 컨테이너에 접속 할 수 있습니다.

```bash
docker exec -it oracle11g bash
```

![image-20210920124719422](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/oracle.assets/image-20210920124719422.png)

​	

sqlplus로 접속 해 봅니다. 초기 관리자 ID 와 PW는 system / oracle 입니다.

![image-20210920125019527](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/oracle.assets/image-20210920125019527.png)

​	

보안을 위해 바로 password 를 입력해 비밀번호를 변경 해 줍니다.

```sql
password
```

![image-20210920125229059](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/oracle.assets/image-20210920125229059.png)

​	

간단하게 변경이 되었습니다.

​	

## 외부 접속 허용

데이터베이스를 만들었으면 외부에서의 접속을 허용해야 합니다.

일단 각자 가지고 있는 Database Client를 사용하시면 되는데, Sql Developer를 사용해도 되지만 저는 DBeaver가 설치 되어 있어서 DBeaver를 사용합니다.

![image-20210920130225357](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/oracle.assets/image-20210920130225357.png)

저는 docker를 설치해 Oracle Database를 올린 컴퓨터와 Host 컴퓨터가 따로라서 Host:에 해당 내부 아이피를 입력 했지만, 같은 컴퓨터라면 그냥 localhost를 입력 하시면 됩니다. Database: 는 xe로 꼭 변경해줘야 합니다.

​	

공유기로 포트포워딩을 한다면 외부 아이피에서의 접속도 가능합니다. 생각보다 쉽기 때문에 오라클을 띄울때 굳이 힘들게 다운받아서 설치해서 하지 말고 (사실상 오라클 공식 루트로 11g 설치 파일을 구하긴 정말 어렵습니다) docker를 이용해서 띄우기를 정말 추천합니다.


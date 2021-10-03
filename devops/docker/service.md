# Linux) docker 컨테이너 자동 실행 설정 및 스케쥴 등록

​		

## 메모리 관리의 어려움

집에서 간단한 서버로 사용하고 있는 컴퓨터가 꼭 부팅후 3일정도만 지나면 급격하게 메모리 사용량이 늘어나고 반응속도가 굉장히 저하되고 있습니다. 특히 ssh로 접속 했을 때 타이핑도 바로바로 되지 않을 정도로 심각해서 여러가지 해결책을 모색 하고 있는데요. 일단 사양이 좋지 않은 컴퓨터인데 불구하고 Elastic Search 를 Docker로 돌리다 보니 어느 정도 감안 해야 하는 면도 있나 싶습니다.

지금까지는 3일 마다 컴퓨터를 재 부팅 해 주고 켜야 하는 도커 컨테이너들을 하나씩 일일히 켜주는 방식으로 대응 하고 있었는데요, 아무래도 번거로워서 자동화를 계획 중에 있습니다. 제 생각으로는 트래픽이 발생할 확률이 거의 없는 새벽 4시 쯔음에 해서 스케쥴링에 따라 하루 한번 정도 재부팅을 시켜 준다면 큰 어려움 없이 할 수 있을 것 같은데요. 재부팅을 하더라도 서버가 돌아오는데 까지 걸리는 시간이 1~2분 남짓 밖에 안되기 때문에 일단 큰 문제가 없을 것으로 예상됩니다.

​		

## Docker 컨테이너 자동 실행

스케쥴러에 따라 재부팅을 한다고 했을 때, 컨테이너를 켜줘야 하는 것도 자동화를 해야 하기 때문에 아무래도 서비스로 등록 해 둘 필요성이 생겼습니다. 지금 해당 서버에서 사용하고 있는 컨테이너는 Elastic Search 와 오라클 서버 이렇게 두개가 있는데요, 차근차근 해 보도록 하겠습니다.

​		

1. ssh 로 해당 서버에 접속 합니다. 로컬이라면 이 과정은 필요 없겠네요.

![image-20211003165141590](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/service.assets/image-20211003165141590.png)	

2. service 파일 생성

systemd를 이용해 서비스 파일을 생성후 부팅 할 때 자동으로 실행되도록 활성화 합니다. docker-oracle 이라고 이름 지었습니다.

```bash
sudo vi /etc/systemd/system/docker-oracle.service
```

​		

3. Service 파일 내용 입력

저는 elatic이름의 컨테이너와 oracle이라는 이름의 컨테이너가 있습니다. 일단 지금은 oracle에 대한 내용입니다.

```bash
[Unit]
Wants=docker.service
After=docker.service

[Service]
RemainAfterExit=yes
ExecStart=/usr/bin/docker start oracle
ExecStop=/usr/bin/docker stop oracle

[Install]
WantedBy=multi-user.target

```

​		

4. systemlctl 등록

```
sudo systemctl enable docker-oracle.service
```

​	

같은 작업으로 elastic 서비스도 등록 해 주었습니다.

```bash
sudo vi /etc/systemd/system/docker-elastic.service
```

```bash
[Unit]
Wants=docker.service
After=docker.service

[Service]
RemainAfterExit=yes
ExecStart=/usr/bin/docker start elastic
ExecStop=/usr/bin/docker stop elastic

[Install]
WantedBy=multi-user.target

```

```bash
sudo systemctl enable docker-elastic.service
```

​		

재부팅을 해서 확인을 해 봅니다.

![image-20211003180958875](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/service.assets/image-20211003180958875.png)

재시작 후에 서비스에 등록된 두 컨테이너들이 모두 시작 된 것을 확인 하실 수 있습니다.

​	

## 리눅스 스케쥴러 등록

리눅스용 작업 스케줄러인 cron 도구를 이용합니다. 미리 설정 해 둔 시간에 특정 작업들을 실행하도록 할당 해주는 스케줄러 도구 인데요, 특정한 간격으로 시스템에서 수행해야 하는 일들을 자동화 하는데 사용 할 수 있습니다.

```bash
sudo vi /etc/crontab
```

![image-20211003181857037](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/service.assets/image-20211003181857037.png)	

굉장히 직관적으로 자세한 사용 방법이 써 있네요.

좌측부터 분 / 시간/ 일/ 월/ 주/ 설정을 할 수 있습니다. 그냥 써있는 대로만 넣으면 되겠습니다.



### 등록

일단 테스트를 위해 매시 22분에 컴퓨터가 재부팅 하도록 등록 해 보았습니다.

```bash
# reboot everyday
22 *    * * *   root reboot
```

​	

/etc/crontab을 아래와 같이 저장 해 두었습니다.	

![image-20211003182235241](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/service.assets/image-20211003182235241.png)

​		

정말 잘 작동하는 지 기다려 보겠습니다.

​	

![image-20211003182319066](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/service.assets/image-20211003182319066.png)

21분 55초 에서 딱 5초를 기다리니 remote host 로 부터 커넥션이 끊겼습니다.

​	

잠시 기다렸다가 다시 접속 해 봅니다.

![image-20211003182429943](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/service.assets/image-20211003182429943.png)

22분에 재부팅에 들어갔고, 24분쯤 다시 접속 했을때 컨테이너가 올라온지 43초가 지났다고 하는 걸 보니 대략 재부팅 후 컨테이너가 켜지는데 1분정도 걸리는 걸로 보입니다. 문제없이 재부팅이 되고, 컨테이너도 다 잘 올라오는걸 확인 했습니다.

​	

### 매일 새벽 4시 재부팅 등록

이제 테스트가 끝났으니 재부팅 스케쥴을 변경 해 줍니다. 매일 새벽 4시 정각에 재부팅이 되도록 하였습니다.

```bash
 0 4    * * *   root reboot
```

​	

이제 내일 자고 일어나서 docker container들의 러닝 타임을 체크해보면 정말 새벽4시에 잘 재부팅이 되었는지 확인 할 수 있겠습니다.

서버가 켜둔지 오래 지날때마다 조금씩 느려지는 문제로 고민이 많았는데 일단 임시방편으로는 해결이 될 것 같습니다. 그래도 더 좋은 해결책을 계속해서 찾아 보도록 하겠습니다.

​	


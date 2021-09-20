# docker-compose 이용해 ELK Stack 구축하기

docker나 docker-compose가 아직 준비되어 있지 않다면 아래 글을 참고해서 먼저 설치 해 주세요.

- [Ubuntu 20.04 LTS ) Docker 설치하기](https://shanepark.tistory.com/237)	

- [Windows) Docker 설치하기](https://shanepark.tistory.com/188)

- [MacOS ) m1 맥북 docker 설치하기](https://shanepark.tistory.com/194)

  

  

## 저장소 클론

아래의 저장소에서 docker-compose 설정을 클론하면 아주 쉽게 docker-compose 를 이용한 ELK STACK을 구축 할 수 있습니다.

```bash
git clone https://github.com/deviantony/docker-elk.git
```

## ![Screenshot from 2021-09-20 14-35-32](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/elk.assets/14-35-32.png)



그러고 해당 경로에 가서 docker-compose up -d 를 하면 실행 할 수 있습니다.

```bash
docker-compose up -d
```

필요한 이미지를 다운 받고, 컨테이너가 모두 구동 됩니다.

![image-20210920155055174](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/elk.assets/image-20210920155055174.png)



키바나도 포트인 5601로 접속 해보면  잘 작동되는 것을 확인 할 수 있습니다.

![image-20210920152438739](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/elk.assets/image-20210920152438739.png)



엘라스틱 서치는 9200 포트 입니다.

![image-20210920184100766](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/devops/docker/elk.assets/image-20210920184100766.png)

​	

LogStash를 이용해 관계형 데이터베이스의 자료들을 모두 밀어 넣어 통합 검색을 구현 할 수도 있습니다.

필요한 분은 아래의 링크를 확인 해 주세요.

> [Logstash를 이용해 Oracle 데이터를 Elastic search와 동기화 하기](https://shanepark.tistory.com/136?category=1203908)

​	

하나하나 따로 설치할 때는 버전도 맞춰야 하고 설치할 때 마다 번거로움이 많았는데 docker-compose 를 이용하니 설정도 한번에 할 수 있고 정말 편리해서 좋습니다.
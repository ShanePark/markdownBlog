# 자주 사용하는 리눅스 기본 명령어 모음

​	

### ls (List Segments)

> 현재 위치의 파일 목록을 조회하는 명령어

- -l : 파일들의 상세 정보를 나타냅니다.
- -a : 숨김 파일들을 표시합니다.

![image-20210811091048650](https://github.com/Shane-Park/markdownBlog/raw/master/OS/linux/linux_commands.assets/image-20210811091048650.png)

> 위의 사진에서 처럼 보이지 않던 폴더들이  (.으로 시작하는 폴더들은 hidden 입니다) ls -al 명령어에서는 보여줍니다.

​		

### cd(Change Directory)

> 경로 이동 할 때 사용하는 명령어 입니다.

- cd .. : 상위 디렉터리로 이동
- cd ~ : 홈 디렉터리로 이동
- cd {name} : 현재 경로의 {name} 폴더로 이동  
- cd \ {path}  : 절대경로 {path} 로 이동
- cd - : 이동 하기 전에 있던 최근 디렉터리로 이동

  

### mkdir(Make Directory)

> 새로운 디렉터리를 생성합니다.

- mkdir {name} : {name} 이름의 디렉터리를 생성합니다.
- mkdir -p {path}/{name} : 존재하지 않는 하위 디렉토리까지 생성합니다.

  

### cd(Copy), mv(Move)

> 파일을 복사 / 이동 하는 명령어 입니다.

- cp {name1} {name2} : name1 파일을 name2라는 이름으로 복사합니다.
- mv {name1} {name2} : name1 파일을 name2 로 이름 변경합니다. name1과 name2 의 경로가 다르다면 해당 경로로 파일을 이동합니다.

  

### rm(Remove)

> 파일이나 디렉터리를 삭제하는 명령어 입니다.

- rm {name} : {name} 을 삭제합니다.
- rm -r {dir} : {dir} 이름의 폴더를 삭제합니다.
- rm -rf {path} : -r 은 recursive(재귀), -f는 force(강제) 옵션으로 Access 권한이 없는 파일도 강제로 삭제하는 옵션입니다. 조심해서 사용해야 합니다.

  

### cat(Catenate)

> 파일 이름을 인자로 받아서 그 내용을 쭉 이어주는 명령어입니다. 예를 들어 아래 명령어를 입력하면

```bas
cat .profile
```

![image-20210811093622274](https://github.com/Shane-Park/markdownBlog/raw/master/OS/linux/linux_commands.assets/image-20210811093622274.png)

.profile 내용을 출력해서 보여줍니다.

```bash
cat .profile | more
```

![image-20210811094025034](https://github.com/Shane-Park/markdownBlog/raw/master/OS/linux/linux_commands.assets/image-20210811094025034.png)

more 명령어를 붙이면 한 화면에 다 보여지지 않을때 조금 더 편하게 읽을 수 있게 해줍니다.

​	

### head / tail

> 파일의 앞부분 부터 확인 하는 명령어와, 특정 파일에 추가되는 내용을 모니터링 하는 명령어 입니다.

예를 들면 아래의 명령어를 입력하면 .profile 파일을 위에서부터 10개 행까지 출력합니다.

```bash
head -n 10 .profile
```

![image-20210811094342436](https://github.com/Shane-Park/markdownBlog/raw/master/OS/linux/linux_commands.assets/image-20210811094342436.png)

-f 명령어를 이용하면 실시간으로 추가되는 내용을 모니터링 할 수 있습니다.

```bash
 tail -f .profile
```

​	

### pwd(Print working Directory)

> 현재 경로를 출력합니다

![image-20210811092147288](https://github.com/Shane-Park/markdownBlog/raw/master/OS/linux/linux_commands.assets/image-20210811092147288.png)

​	

### who / whoami

> 각각 호스트에 로그인한 사용자 정보 / 내 접속 정보 를 출력해줍니다.

![image-20210811092440119](https://github.com/Shane-Park/markdownBlog/raw/master/OS/linux/linux_commands.assets/image-20210811092440119.png)

​	

### env

> 환경 변수를 출력합니다.

![image-20210811094554165](https://github.com/Shane-Park/markdownBlog/raw/master/OS/linux/linux_commands.assets/image-20210811094554165.png)

​	

### grep

> 특정 문자열을 검색합니다.

예를 들어 .profile 파일에서 if 라는 문자열을 검색할 때는 아래와 같이 입력합니다.

```bash
grep if .profile
```

![image-20210811094824271](https://github.com/Shane-Park/markdownBlog/raw/master/OS/linux/linux_commands.assets/image-20210811094824271.png)

​	

### | (Pipe line)

> 명령의 결과를 다음 명령으로 넘깁니다. 

위에서 익힌 env, grep 명령어를 파이프 라인과 함께 사용하면

```bash
env | grep user
```

![image-20210811095025207](https://github.com/Shane-Park/markdownBlog/raw/master/OS/linux/linux_commands.assets/image-20210811095025207.png)

이렇게 env명령의 결과에서 user 문자열을 검색 할 수도 있습니다.

​	

### ps(Process Status)

> 프로세스의 상태를 확인하는 명령어입니다.

- -e : 현재 수행하고 있는 프로세스에 관한 정보 확인
- -f : 프로세스 ID와 모 프로세스ID를 포함한 전체 리스트 표시

```bash
ps -ef | grep tomcat
```

![image-20210811095350982](https://github.com/Shane-Park/markdownBlog/raw/master/OS/linux/linux_commands.assets/image-20210811095350982.png)

ps -ef의 결과에서 tomcat 이라는 단어를 검색 해 보았습니다.

​	

### free

> 메모리 상태를 확인하는 명령어 입니다. -h (Human Readable) 옵션과 함께 사용합니다.

![image-20210811095508478](https://github.com/Shane-Park/markdownBlog/raw/master/OS/linux/linux_commands.assets/image-20210811095508478.png)



df (Disk Free)

> 디스크 사용량을 확인합니다. 이 역시 -h 옵션과 함께 사용합니다. 특정 디렉터리를 기준으로 하려면 du 명령어를 사용 하면 됩니다.

![image-20210811095614191](https://github.com/Shane-Park/markdownBlog/raw/master/OS/linux/linux_commands.assets/image-20210811095614191.png)

​	

### wget / curl

> wget은 특정 url의 파일을 다운로드 받을때, curl은 웹 요청을 할 때 사용 할 수 있습니다.

서버를 띄웠는데 외부에서 접속이 안될 때 curl 로 localhost에 신호를 보내서 응답을 정상적으로 받는다면, 방화벽 등의 문제로 범위를 좁힐 수 있습니다.

​	

### scp (SecureCopy)

> 파일을 양방향으로 전송 하게 해줍니다. 
>
> scp {전송할파일} {아이디@전송할서버주소}:{저장될 서버의 디렉터리} 형태로 사용합니다.

- 폴더의 경우에는 -r 옵션을 줘야 합니다.

반대의 경우로 원격지에서 파일을 로컬로 가져올 수도 있습니다

> scp {아이디@원격지서버주소}:{원본위치} {받는위치}

​	

### systemctl

> 서비스를 관리하는 명령어 입니다.

특정 서비스의 상태 확인

```bash
systemctl status {서비스명}
```

MariaDB 서비스 구동

```bash
systemctl start mariadb
```

서비스 종료

```bash
systemctl stop maridb
```

부팅시 자동 시작되도록 등록

```bash
systemctl enable mariadb
```


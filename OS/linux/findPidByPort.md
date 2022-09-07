# Linux) 특정 port 사용중인 프로세스 찾아 죽이기

​		

## 서론

Tomcat을 사용하다 보면 어플리케이션이 정상적으로 종료가 되지 않아서 포트가 충돌 나는 경우가 있습니다. 

비단 Tomcat 만의 문제가 아니고 어떤 프로세스를 사용하던 특정 port를 점유 하고 종료되지 않은 경우가 종종 있는데요, 이럴 때 쉽게 해결하는 방법입니다.

아래는 이클립스에서 톰캣을 실행 하려 할 때, 8080 port가 이미 점유중이라는 에러 메시지 입니다.

![image-20211014100143457](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/findPidByPort.assets/image-20211014100143457.png)

> The Tomcat connector configured to listen on port 8080 failed to start. The port may already be in use or the connector may be misconfigured.

## port 번호로 pid 찾기	

8080 port를 점유하고 있는 프로세스를 찾아보겠습니다.

```bash
sudo ss -lptn 'sport = :8080'
```

![image-20211014100309471](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/findPidByPort.assets/image-20211014100309471.png)

PID 22706 에서 해당 포트를 사용 중 이라는 것을 확인 했습니다.. 또한 Process 이름은 java 입니다.		

## 프로세스 종료하기

### System Monitor로 프로세스 종료

System Monitor를 통해 해당 PID의 프로세스를 찾고

![image-20211014101003289](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/findPidByPort.assets/image-20211014101003289.png)

우클릭을 해서 프로세스를 강제 종료하는 방법이 있습니다. 가장 직관적이며 왠만한 운영체제에서 모두 사용 할 수 있는 방법입니다.

### 명령어로 프로세스 종료

명령어를 이용해 해당 번호에 해당하는 프로세스를 종료한다면 조금 더 간단하고 쉽습니다.

**프로세스를 죽이기 위해 필요한 정보**

> 둘중 하나만 있어도 종료할 수 있습니다.

- Process name: 프로세스 이름

- Process id: 프로세스 아이디

**프로세스를 죽이는 두가지 커맨드**

- kill: Process ID로 프로세스 죽이기
- killall: 프로세스 이름에 해당하는 모든 프로세스 종료하기

>  제가 위에서 종료하고자 하는 'java'의 경우에는 자바를 사용하는 수 많은 다른 프로세스도 함께 종료되어 버리는 불상사가 일어나기 때문에 PID를 정확히 명시해주어서 종료하는 것이 좋습니다.

### 다양한 인터럽트 핸들러 옵션

> 아래의 다양한 옵션들을 활용 할 수 있습니다. 우리에게 익숙한 Ctrl+C가 2번, `SIGINT` 입니다. 일반적인 KILL은 15번 입니다. 

```
1) SIGHUP        2) SIGINT        3) SIGQUIT       4) SIGILL        5) SIGTRAP
6) SIGABRT       7) SIGBUS        8) SIGFPE        9) SIGKILL       10) SIGUSR1
11) SIGSEGV      12) SIGUSR2      13) SIGPIPE      14) SIGALRM      15) SIGTERM
16) SIGSTKFLT    17) SIGCHLD      18) SIGCONT      19) SIGSTOP      20) SIGTSTP
21) SIGTTIN      22) SIGTTOU      23) SIGURG       24) SIGXCPU      25) SIGXFSZ
26) SIGVTALRM    27) SIGPROF      28) SIGWINCH     29) SIGIO        30) SIGPWR
31) SIGSYS       34) SIGRTMIN     35) SIGRTMIN+1   36) SIGRTMIN+2   37) SIGRTMIN+3
38) SIGRTMIN+4   39) SIGRTMIN+5   40) SIGRTMIN+6   41) SIGRTMIN+7   42) SIGRTMIN+8
43) SIGRTMIN+9   44) SIGRTMIN+10  45) SIGRTMIN+11  46) SIGRTMIN+12  47) SIGRTMIN+13
48) SIGRTMIN+14  49) SIGRTMIN+15  50) SIGRTMAX-14  51) SIGRTMAX-13  52) SIGRTMAX-12
53) SIGRTMAX-11  54) SIGRTMAX-10  55) SIGRTMAX-9   56) SIGRTMAX-8   57) SIGRTMAX-7
58) SIGRTMAX-6   59) SIGRTMAX-5   60) SIGRTMAX-4   61) SIGRTMAX-3   62) SIGRTMAX-2
63) SIGRTMAX-1   64) SIGRTMAX
```

보통의 경우에는 kill [pid] 를 입력하면 간단하게 프로세스를 종료 할 수 있습니다.

```bash
kill 22706
```

![image-20211014101222197](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/findPidByPort.assets/image-20211014101222197.png)

프로세스가 종료 된 후 8080 포트를 사용하는 프로세스가 더이상 없는 것이 확인 됩니다. 

이제 톰캣을 켜면 아무 문제 없이 잘 실행이 됩니다.

### kill 이 안된다면?

kill 명령어는 옵션 없이 사용하면 기본적으로 kill -TERM 혹은 kill -15 을 입력했을 때와 같습니다. process 를 안전하게 종료하는 방법 인데요, 컴퓨터를 종료 할 때 시스템 종료를 이용해서 안전하게 끄는 것을 생각하면 됩니다.

반면에 kill -9 옵션은 프로세스를 종료하는 거칠고 안전하지 않은 방법 입니다. 컴퓨터로 치면 그냥 전원 코드를 뽑아버리는 것과 같고, 데이터의 손상을 야기 할 수 있습니다. 하지만 kill 명령어로 종료가 되지 않을때는 kill -9 옵션을 고려해보세요.

> 모든 시그널은 사용자의 핸들러로 대체할 수 있지만, 이 경우에 프로세스가 모든 시그널을 무시하고 계속 살아 남을 수 있기 때문에 이를 방지하기 위해 9번 시그널만 사용자의 핸들러로 대체 할 수 없도록 만들어 져 있습니다.

혹시 MacOS 를 사용하신다면, ss 명령어가 없기 때문에 `lsof -i:8080` 를 사용하시면 됩니다.

![image-20211017214426199](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/findPidByPort.assets/image-20211017214426199.png)

## 쉽게하기

이제 원리를 알았으니 손쉽게 하는 방법을 알아보겠습니다. 매번 프로세스 번호를 찾고 하나씩 종료를 시키는 것은 매우 번거롭습니다.

`fuser` 는 리눅스에서 파일이나 소켓을 어떤 프로세스가 사용하고 있는지를 알려 주는 명령어인데요, fuser로 kill signal을 보내는 방법이 가장 간단했습니다.

위에서처럼 프로세스 번호만 조회하려면 아래와 같이 입력 하면 되며

```bash
fuser 포트번호/tcp
```

![image-20220907173324856](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/findPidByPort.assets/image-20220907173324856.png)

종료는 -k 옵션으로 가능합니다.

```bash
fuser -k [포트번호]/tcp
```

![image-20220907173630537](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/findPidByPort.assets/image-20220907173630537.png)

> 정상적으로 종료가 된 모습니다.

한번에 여러 프로세스를 종료 할 수도 있습니다.

![image-20220907173818048](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/findPidByPort.assets/image-20220907173818048.png)

훨씬 간편합니다. 이상입니다.

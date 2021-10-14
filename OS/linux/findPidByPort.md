# Linux) 특정 port 사용중인 프로세서 찾아 죽이기



![image-20211014100143457](/home/shane/Documents/git/markdownBlog/OS/linux/findPidByPort.assets/image-20211014100143457.png)

> The Tomcat connector configured to listen on port 8080 failed to start. The port may already be in use or the connector may be misconfigured.



```bash
sudo ss -lptn 'sport = :8080'
```



![image-20211014100309471](/home/shane/Documents/git/markdownBlog/OS/linux/findPidByPort.assets/image-20211014100309471.png)

PID 22706 에서 해당 포트를 사용 중입니다.

​	

## System Monitor로 프로세스 종료하기

System Monitor를 통해 해당 PID의 프로세서를 찾고

![image-20211014101003289](/home/shane/Documents/git/markdownBlog/OS/linux/findPidByPort.assets/image-20211014101003289.png)

우클릭을 해서 프로세스를 강제 종료하는 방법이 있습니다.



## 명령어로 프로세스 종료하기



프로세스 죽이기

필요한 것

Process name

Process id

프로세스를 죽이는 두가지 커맨드

- kill – Kill a process by ID
- killall – Kill a process by name



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

​	

대개의 경우

```bash
kill 22706
```

를 입력해 프로세스를 종료 할 수 있습니다. 

![image-20211014101222197](/home/shane/Documents/git/markdownBlog/OS/linux/findPidByPort.assets/image-20211014101222197.png)

프로세스가 종료 된 후 8080 포트를 사용하는 프로세스가 더이상 없는 것이 확인 됩니다. 이제 톰캣을 켜면 아무 문제 없이 실행이 됩니다.

 kill 명령어는 기본적으로 kill -TERM 혹은 kill -15 명령어와 같은데요, process 를 안전하게 종료하는 방법 입니다. 컴퓨터를 종료 할 때 시스템 종료를 이용해서 안전하게 끄는 것을 생각하면 됩니다.

kill -9 옵션은 프로세스를 종료하는 거칠고 안전하지 않은 방법 입니다. 컴퓨터로 치면 그냥 전원 코드를 뽑아버리는 것과 같고, 데이터의 손상을 야기 할 수 있습니다. kill 명령어로 종료가 되지 않을때는 kill -9 옵션을 고려해보세요.


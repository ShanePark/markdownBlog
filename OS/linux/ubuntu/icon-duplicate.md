# Ubuntu ) 실행시 Dock에 아이콘 중복현상 해결

​		

dock 에 힘들게 아이콘을 추가 했는데도, 실행 할 경우 아이콘이 중복으로 2개 실행 되는 경우가 있습니다.

​	

<img src="https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/icon-duplicate.assets/image-20210919103843945.webp" width=750 height=450 alt=1>

​	

한 두개 있을때야 그렇다 쳐도 이게 점점 늘어나게 되면 해결하지 않고는 불편해서 참을 수 없게 됩니다.

![image-20210919104008438](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/icon-duplicate.assets/image-20210919104008438.webp)

​	

그래서 지금부터 해당 현상을 해결하는 근본적인 방법을 같이 나누어 보겠습니다.

​	

## 이유

리눅스의 Gnome 데스크톱을 사용하면서 우리는 Dash to dock을 사용 하고 있습니다. dock 에 추가 할 때 런처에 적절한 StartupWMClass 가 지정되어 있지 않기 때문에 같은 어플리케이션이란 것을 인식 하지 못해서 일어나는 현상 입니다.

​	

일단 어플리케이션의 StartupWMClass 를 알아내야 합니다.

Terminal에 xprop WM_CLASS 명령을 입력 하고 마우스가 십자가 모양으로 바뀌면 실행중인 해당 어플리케이션을 클릭 합니다.

![image-20210919104628270](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/icon-duplicate.assets/image-20210919104628270.webp)

그러면 이렇게 해당 어플리케이션의 WM_CLASS를 알 수 있습니다.



여러개의 어플리케이션을 한번에 볼 수도 있는데요,

- Alt +F2 를 입력 합니다.

- 그러면 아래와 같이 Run a Command 창이 나오는데요 lg 를 입력합니다.

  ![image-20210919104748131](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/icon-duplicate.assets/image-20210919104748131.webp)



그래서 뜨는 창에서 우측의 Windows 를 클릭 하면 여러 app 들의 wmClass 들을 한번에 확인 할 수도 있습니다.

​	

![image-20210919104545731](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/icon-duplicate.assets/image-20210919104545731.webp)

​		

이제 그렇게 알아낸 wmClass 를 등록 해 줘야 합니다.

​	

STS4 를 dock에 등록 해 보겠습니다. WM_CLASS 를 먼저 알아내고.

![image-20210919105600596](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/icon-duplicate.assets/image-20210919105600596.webp)



/usr/share/applications 에서 해당하는 .desktop 파일을 찾아 냅니다. 저는 sts4 이기 때문에 아래와 같이 입력 해서 찾았습니다.

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/icon-duplicate.assets/image-20210919105652806.webp)

​	

vi로 편집기로 엽니다.

```bash
sudo vi /usr/share/applications/sts4.desktop
```

​	

그래서 맨 아래에 내용을 추가 해 줍니다.

```bash
StartupWMClass=SpringToolSuite4
```

![image-20210919105902060](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/icon-duplicate.assets/image-20210919105902060.webp)

​	

이제 실행을 해 보면 더이상 아이콘이 중첩되어 생성되지 않습니다. !

![image-20210919110043708](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/icon-duplicate.assets/image-20210919110043708.webp)
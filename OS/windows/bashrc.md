# Windows 에서 Vim을 vi 명령어로 사용하기 - alias 별칭 사용하기

​	

개인적으로는 Mac을 사용하고 업무용으로는 Windows를 사용하고 있습니다.

아무래도 간단한 편집을 할때는 손이 vi 로 나가게 되는데요, 그래서 windows에도 Vim 에디터를 설치해서 사용하고 있습니다.

​		

```bash
vi
```

​	

![image-20210903132847205](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903132847205.webp)

다만, windows에 설치한 vim Editor는 , vi 를 입력하면 실행이 되지 않습니다.

​	

```bash
vim
```

![image-20210903132927441](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903132927441.webp)

정확히 vim 이라고 입력 해야만 vim 데이터가 실행이 되는데요. 아무래도 mac이나 Linux 환경에서 vi 를 입력해 에디터로 넘어갔던 대부분의 분들이 저와 같이 불편함을 느끼셨을 거라고 생각합니다.

​	

그래서 지금부터 그 문제를 해결해 보려고 합니다. vi 만의 문제가 아니고 windows를 사용할때는 Linux에서의 bashrc 편집을 어떻게 할 지 궁금한 분들에게도 도움이 될 거라고 생각합니다.

​		

### 일단 모든 Alias를 확인 해 보겠습니다.

```bash
Get-Alias
```

![image-20210903133314493](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903133314493.webp)

수많은 Alias 목록이 나옵니다만, vim은 찾아 볼 수 없었습니다.

​	

### 아래와 같이 입력하면 간단하게 vi 를 vim 의 Alias 로 등록 할 수 있습니다.

```bash
Set-Alias vi vim
```

​	

![image-20210903133343308](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903133343308.webp)

![image-20210903133351351](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903133351351.webp)

잘 실행 되죠 ? 그런데 문제가 있습니다.

​	

한번 Terminal / PowerShell 을 껐다 다시 켜면

![image-20210903133422168](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903133422168.webp)

바로 전에 저장해 둔 Alias가 사라지게 됩니다. 해당 설정을 영구 저장해야 할 필요가 있습니다.

지금부터 천천히 해보겠습니다.

​	

### Windows 에서는 $ profile 을 입력해 PowerShell의 profile 경로를 확인 할 수 있습니다.

```
$profile
```

​		

![image-20210903133208987](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903133208987.webp)

경로가 확인이 되었습니다. 저런 경로에 있다고 합니다.

​	

이제 해당 file을 편집 해 보겠습니다. 아.. 또 습관적으로 vi 를 입력해 버렸는데요, 이제 vi 를 등록하고 나면 이 오류도 이제 다시는 안보겠네요. 지긋지긋 해서라도 꼭 변경 해야 겠습니다.

​	

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903133541927.webp)

​	

vim 에디터가 해당 경로의 파일을 편집합니다. 처음엔 아무것도 없는데 위에서 입력한 내용을 똑같이 입력 해 저장합니다.

```bash
Set-Alias vi vim
```

​	

![image-20210903133601793](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903133601793.webp)

​	

이제 저장 하고 Terminal을 새로 켜겠습니다.

​	

​	

![image-20210903133631639](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903133631639.webp)

터미널을 켜자 마자 오히려 더 에러가 납니다. 스크립트를 실행 할 수 없다고 하네요.

​		

아래와 같이 입력해서 ExecutionPolicy를 어떻게 편집 해 볼지 파악해보기로 합니다.

```bash
get-help ExecutionPolicy
```

​	

![image-20210903133850991](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903133850991.webp)

Get과 Set 명령을 사용 할 수 있네요. 

​	

### 일단 Get 으로 지금의 Policy를 확인 해 보겠습니다.	

```bash
Get-ExecutionPolicy
```



![image-20210903133919334](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903133919334.webp)

> Restricted 라고 나오네요. 해당 정책은  ps1 스크립트 파일을 로드하여 실행 할 수 없는 정책 입니다.

​	

### 이젠 Set 명령어를 어떻게 사용 할 수 있을지 help를 통해 알아봅니다.

```bash
get-help Set-ExecutionPolicy
```

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903133936561.webp)

사용할 수 있는 옵션들이 쭉 나오네요, 그 중에 remoteSigned를 사용하겠습니다.

​	

```bash
Set-ExecutionPolicy remoteSigned
```

​	

![image-20210903134117081](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903134117081.webp)

하려고 했더니 관리자 권한으로 실행 해야 만 한다고 합니다.

​	

### 관리자 권한으로 Terminal을 새로 실행 해 줍니다.	

> 우클릭 -> 자세히 - > 관리자 권한으로 실행 하면 됩니다.

![image-20210903134140167](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903134140167.webp)

​	

​	그리고 이제 다시 쳐봅니다.

```bash
Set-ExecutionPolicy remoteSigned
```

​	

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903134351779.webp)

성공 했습니다. 이제 exit를 입력 하고 새로 켭니다.

이제는 더이상 관리자 모드로 켤 필요 없습니다.

​	

![image-20210903134447648](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903134447648.webp)

아무 오류도 없네요. 이제 vi 를 입력하면

​	

![image-20210903134456087](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/windows/bashrc.assets/image-20210903134456087.webp)

vim 에디터가 실행 됩니다 ! m 하나 덜 치는게 쉽지 않았네요. 힘든 길 저와 함께 걷는다고 수고 많으셨습니다!

​	


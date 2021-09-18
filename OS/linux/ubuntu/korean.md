# Ubuntu 20.04 키보드 한글 입력 설정 하기

​	

설치 할 때에 한글 키보드를 설정 했든 안했든 처음에는 한글 입력이 안 되는 경우가 많았습니다.

Ubuntu 에서 한글 입력을 할 수 있도록 설정을 해 보도록 하겠습니다.

​	

![1](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/1.png)

일단 Settings 에 들어갑니다.

​		

후에 Regions & Language 에 들어갑니다. 키보드 설정이 따로 있는게 아니고 해당 설정은 지역 설정에서 하더라구요.

그래서 맨 아래 Manage Installed Languages를 클릭 합니다.

![2](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/2.png)

​		

그러면 이제 모든 Input Source 들이 나옵니다. Korean을 선택해 Add 를 눌러줍니다.

![3](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/3.png)		

101/104 키 말고 제일 위에 거로 해줍니다.

![4](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/4.png)

​			

추가 후에는 이제 Manage installed Languages를 클릭합니다.

![5](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/5.png)


![6](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/6.png)

그러면 이제 필요한 파일들을 Install 해도 되겠냐고 물어보는데요, Install 을 클릭해줍니다.	

​		

![7](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/7.png)

모든 다운로드가 완료 된 후에는 컴퓨터를 재시작 해줍니다.

​	

재시작 후에 같은 설정화면에 다시 돌아와서 이번에는 + 를 눌러보면,

![1](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/1-1951496.png)			

다시 Korean에 들어가봅니다.

![2](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/2-1951509.png)

​	

아까는 없었던 Korean(Hangul) 이 추가 되어 있습니다. Add 를 해줍니다.	

![3](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/3-1951523.png)

​		

이제는 필요 없어진 맨 위의 Korean을 쓰레기통 모양 아이콘을 눌러 삭제 해 줍니다.

![1](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/1-1951637.png)



이제 Korena(Hangul) 만 남았습니다. 톱니바퀴 아이콘을 클릭해 설정 화면에 들어갑니다.	

![2](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/2-1951658.png)

​	

![3](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/3-1951942.png)

새로운 화면이 나왔습니다. 이 상태에서 이제 바로 한/영 키를 눌러 한글을 입력 할 수 있는 분들도 있겠지만 키보드에 따라 hangul 버튼이 없을 수가 있습니다. 혹은 한/영 키가 ALT R 로 되어 있는 경우도 있을 수 있습니다.

​	

그럴경우 일단 우측의 Add 버튼을 클릭 하구요,

​	

이번에 뜨는 팝업 창에서 한/영 키로 사용하고 싶은 키를 입력 합니다.

![4](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/4-1951997.png)

그러고 OK 를 눌러 완료 합니다.

​	

이제 한/영 키 Toggle 로 사용할 Key 목록이 아래와 같이 3개가 되었습니다. OK를 눌러 저장합니다.

​	![5](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/5-1952020.png)

​		

다만 이렇게 지정 해 둔 경우 Alt_R 의 기능이 살아 있기 때문에 타자를 치면서 한/영 변환을 하다가 특정 키가 작동해서 불편 할 수가 있습니다.

만약 Hangul 키가 없어 오른쪽 Alt 를 이용 해야 할 경우에는 우측 Alt 키를 아이에 Hangul 키로 바인딩 하는 것을 추천드립니다.

```shell
$ sudo vi /usr/share/X11/xkb/symbols/altwin
```

이렇게 altwin 파일을 켜서

​	

![image-20210919004635375](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/image-20210919004635375.png)	

​	6번 라인의 key <RALT> 에 해당하는 symbols 부분을  symbols[Group1] = [Hangul] }; 로 변경 해 주시면 됩니다.

대신 이 파일을 수정 한 경우에는 재부팅을 한번 해야 적용이 됩니다.

​		

​	

![6](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/6-1952039.png)

한글이 잘 입력됩니다 ! 수고하셨습니다.
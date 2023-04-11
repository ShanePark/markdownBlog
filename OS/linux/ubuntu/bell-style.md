# Ubuntu Terminal에서 백스페이스/방향키 누를때 삐 하는 비프음 안나게 하기

​	

터미널 에서 뭐 만 입력하려고 하면 자꾸 삐 삐 거리는게 신경이 굉장히 쓰입니다.

간단하게 해당 설정을 변경 할 수있습니다.

​	

아래의 명령어를 입력 해 inputrc 파일을 엽니다.

```bash
sudo vi /etc/inputrc
```

​	

적당히 넣고 싶은 자리에 아래 한줄을 추가합니다.

```bash
set bell-style off
```

![image-20210918225634365](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/bell-style.assets/image-20210918225634365.webp)

​	

이제 wq로 저장 하고 나온 후 터미널을 끄고 새로 켜봅니다.

그러면 이제 터미널에서의 경고음은 더이상 나지 않습니다.

​	

하지만 그 외에도 vi 에디터 내에서의 경고음이라던가 거슬리는 소리가 여전히 몇 군데 남아 있는데요..

dconf Editor를 실행 해 줍니다. 없다면 apt-get install dconf-editor 으로 설치 해 주세요.

![image-20210918225947369](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/bell-style.assets/image-20210918225947369.webp)

​	

이제 org/gnome/desktop/sound 에 있는 event-sounds 설정을 꺼줍니다.

![image-20210918230148375](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/bell-style.assets/image-20210918230148375.webp)

​	

더이상 거슬리는 소리가 발생하지 않습니다.


# Ubuntu에 IntelliJ IDEA설치하고 바로가기 만들기

​	

Ubuntu에서 tar.gz 파일을 다운 받아서 설치하는건 intelliJ  IDEA를 설치 할 때 처음 겪었습니다. 

앞으로 자주 겪을 일이고 하니 한번 정리 해 보았습니다.

​	

일단 가장 쉬운 방법은 snap 을 통해 다운 받는 것 입니다. 

```bash
sudo snap install intellij-idea-ultimate --classic
```

그러면 간단하게 바로가기 까지 생성 해 줍니다. 하지만 하나하나 손으로 설정 하는 방법을 해보겠습니다.



아래 링크에서 다운 받습니다.

https://www.jetbrains.com/idea/download/#section=linux

![image-20210918223557362](/home/shane/Documents/git/markdownBlog/OS/linux/ubuntu/intellij.assets/image-20210918223557362.png)

​	

그러면 이제 download 폴더에 다운이 되어 있을 텐데요, Ctrl + Alt + T로 Terminal을 켜 줍니다.

![image-20210918224338588](/home/shane/Documents/git/markdownBlog/OS/linux/ubuntu/intellij.assets/image-20210918224338588.png)

​		

Downloads 폴더로 이동 해서 ls를 입력 해서 파일명을 확인 합니다.

![image-20210918224434118](/home/shane/Documents/git/markdownBlog/OS/linux/ubuntu/intellij.assets/image-20210918224434118.png)

/opt/ 


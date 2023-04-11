# MacOS ) HEIC 에서 JPG로 변환하는 매크로 등록하기



맥에서는 기본으로 HEIC 라는 사진 포맷을 사용합니다. 이게 한국에서 다수의 웹 사이트는 지원을 하지 않는 경우가 매우 많다보니 불편한 경우가 많이 생기는데요, 하나 하나 사진을 바꾸기도 참 불편합니다.

Mac에서 preview 등으로 간편하게 하는 방법이 있지만 아이에 매크로로 등록을 해놓고 5분 투자로 앞으로 5년 이상 편하게 사용하게끔 한번 함께 등록 해 보셨으면 합니다.

​	

### Automator를 실행 합니다.

> Automator는 기본으로 Mac에 모두 깔려있습니다. Applications 쪽에 들어가서 찾아보시거나 Spotlight 를 켜서 automator를 검색해보세요



<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/heicToJpg.assets/image-20210824212243030.webp width=750 height=200 alt=1>

​	

### 하단의 new Document를 눌러 새로운 문서를 생성 합니다.

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/heicToJpg.assets/image-20210824212406674.webp width=750 height=500>

​	

### Quick Action을 선택합니다.

![image-20210824212433129](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/heicToJpg.assets/image-20210824212433129.webp)

​	

### Copy Finder itmes ( Finder 항목 복사하기)를 더블클릭해 추가해줍니다. 위치는 Desktop으로 설정합니다.

![image-20210824212609724](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/heicToJpg.assets/image-20210824212609724.webp)

​		

### 이번에는 Change Type of images를 선택합니다. To Type: 에는 JPEG를 선택합니다.

![image-20210824212709133](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/heicToJpg.assets/image-20210824212709133.webp)

​	

### 이제 File -> Save... 를 선택하거나 커맨드 +S 를 눌러 저장합니다.

![image-20210824212752859](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/heicToJpg.assets/image-20210824212752859.webp)

​	

### 적당히 이름을 지어 줍니다.

![image-20210824212818406](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/heicToJpg.assets/image-20210824212818406.webp)

​	

이제  변환하고 싶은 이미지들을 모아 우클릭 -> Quick Actions에 들어가보면 images to jpeg가 추가 되어 있습니다.

![image-20210824212903115](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/heicToJpg.assets/image-20210824212903115.webp)

​	

클릭을 하면	

![image-20210824212952400](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/mac/tips/heicToJpg.assets/image-20210824212952400.webp)

아주 간단하게 모든 사진이 jpg로 변환됩니다.

아까 설정한 바탕화면에 모두 저장됩니다.



사실 애초에 사진첩에서 사진을 가져올 때 이미지를 드래그 하면 알아서 변환 해 주지만, 이미 heic 파일로 컴퓨터에 저장 된 경우에 손쉽게 사용하면 되겠습니다.

이상입니다 !



https://www.howtogeek.com/398927/how-to-convert-heic-images-to-jpg-on-a-mac-the-easy-way/
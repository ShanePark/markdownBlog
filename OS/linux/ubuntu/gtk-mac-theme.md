# Ubuntu MacOS 처럼 만들기 GTK Themes

​		

저는 개인적으로 기본 Ubuntu Gnome 인터페이스도 마음에 듭니다. 

Windows 를 사용할 때는 정말 디자인이 영 마음에 들지 않았고 애니메이션이라던가 전체적으로 무거운 느낌 또한 영 별로였는데, Ubuntu를 써보니 왜 진작 하지 않았을까 생각이 들 정도로 여러모로 굉장히 만족하며 사용하고 있습니다. 

저는 퇴근후 집에서는 맥북을 사용하다 보니 MacOS 와 Ubuntu 를 번갈아가며 사용하고 있습니다. 한가지 OS 만을 알고 있을 때에 비해 뚜렷한 장점이 있기 때문에 윈도우-맥-리눅스 중 최소 2가지 OS를 최소한 접해보는건 개인적으로 좋다고 생각합니다. Windows만을 쓸 때는 윈도우의 장점이라고 딱히 내세울만한게 없었 던 것 같은데.. 뭐 가장 많이 사용되는 플랫폼이다보니 왠만한건 다 호환되고 어렸을때 부터 오래 사용해와서 익숙함이랄까 그런게 있겠지만 그에 반해 Linux와 MacOS는 각각의 절대적인 장점이 있다고 생각합니다. 

OS를 번갈아 가면서 작업하다보면 단점도 존재하는데요. 일단 MacOS의 키 배열이 윈도우/리눅스와 많이 다른게 가장 큽니다. 그러고 그 다음으로 이제 인터페이스가 다르다는 건데요. 손에 익은 단축키들은 간단하게 키 매핑으로 어느정도 조절 할 수 있습니다.

거기에 이어서 어느 정도 일관된 인터페이스가 보장된다면 더 좋지 않을까 생각 해 보았습니다.

Ubuntu GNOME의 GTK 테마를 유저들이 워낙 기가막히게 만들어 뒀기 때문에 정말 깜짝 놀랄정도로 비슷하게 만들 수 있습니다.

> 글 시작에 앞서 한 스텝씩 설명 할 필요 없이 테마만 있으면 알아서 설치 할 수 있는 분들은 https://github.com/vinceliuice/WhiteSur-gtk-theme 에서 받아서 바로 설정하시면 됩니다.

​	

## Ubuntu를 MacOS 처럼 만들기	

​	

### 1 GTK 테마 설치

저는 여태까지 McMojave 테마 https://www.gnome-look.org/p/1275087/ 를 사용하고 있었는데요, 이것도 제법 비슷하다고는 생각 했는데 이번에 포스팅을 준비하면서 최신 OS인 BigSur를 본따 만든 WhiteSur Gtk Theme이 있다는 것을 알게 되었습니다.

포스팅은 WhiteSur Gtk Theme을 기준으로 할 테니 과거의 Mojave 테마를 원하는 분은 위의 링크에서 다운 받으시면 됩니다.

아래의 링크에서 WhiteSur Gtk Theme을 다운 받습니다.

https://www.gnome-look.org/p/1403328

![image-20210930230116799](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/image-20210930230116799.png)

​	

우측 상단의 Download 를 클릭 하고

![image-20210930230142136](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/image-20210930230142136.png)

​	원하는 테마를 다운 받으세요. 저는 dark 버전으로 선택 합니다만 여러개 받아서 선택하며 고르셔도 됩니다. 저는 보통 전부 다 다운받습니다. 개당 0.18 MB 밖에 안하잖아요. solid 와 일반버전의 차이는 solid는 불투명, solid가 붙어있지 않은 경우는 투명입니다.

​	

 solid의 경우

![image-20211001083002279](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/image-20211001083002279.png)

solid가 아닐 경우

![image-20211001083101110](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/image-20211001083101110.png)

차이를 아시겠죠 ? 취향에 따라 쓸수 있습니다.

​					

다운이 완료되었으면

![1](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/1.png)

이제 압축을 풀어서 ~/.themes 폴더에 넣어줘야 합니다.

​	

.xz 압축을 풀고

```bash
unxz WhiteSur-dark.tar.xz
```

.tar 아카이브 파일을 해제합니다.

```bash
tar xvf WhiteSur-dark.tar
```

~/.themes 폴더가 없다면 만들고

```
mkdir ~/.themes
```

압축 푼 macOS 테마 폴더를 이동시킵니다.

```bash
mv ~/Downloads/WhiteSur-dark ~/.themes/
```

​	

테마가 준비 되었으니 Gnome Tweaks를 설치해줍니다.

```bash
$ sudo apt update
$ sudo apt install gnome-tweaks

```

![2](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/2.png)

설치는 금방 됩니다.

​	

설치된 Tweaks 를 실행 합니다.

![3](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/3.png)

​	

Apperance 탭에 가면 Themes > Applications 을 설정 할 수 있는데요

![4](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/4.png)

​	

아까 ~/.themes 폴더에 잘 넣어줬다면, 이제 WhiteSur 테마를 선택 할 수 있습니다. 선택하자 마자 바로 바뀌었네요.

![5](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/5.png)

​		

여기에서 이제 Windows Titlebars 설정에서 Titlebar Buttons Placement를 Right에서 Left 로 바꾸면 정말 Mac과 같아집니다.

![6](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/6.png)

​	

생각보다 정말 간단하죠?



## 이어서 나머지 세부 설정

https://www.gnome-look.org/p/1403328 링크를 보면 아래에 하나씩 추가 설정을 할 수 있도록 링크가 다 달려있습니다.

![image-20210930232544047](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/image-20210930232544047.png)

​	

### 1. 아이콘 변경

일단 Icon을 한번 변경 해 볼까요? Icon theme를 다운 받습니다.

![7](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/7.png)

​		

![image-20211001083503898](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/image-20211001083503898.png)

이번에도 여러가지가 있는데요 저는 제일 위에 있는 것을 다운 받았습니다.

​	

테마를 했을 때와 비슷한 과정이지만, 이번에는 ~/.themes 폴더 대신에 ~/.icons 폴더에 압축을 풀어줘야 합니다.

아까는 두번에 나눠서 압축을 풀었지만 사실

```bash
tar -xvf WhiteSur-grey.tar.xz 
```

를 입력 하면 한번에 xz 압축 풀고 아카이브 해제 까지 됩니다.

​	

압축을 풀고 .icons 폴더로 이동시켜줍니다. 폴더가 없다면 역시 만들어줍니다.

```bash
mv ~/Downloads/WhiteSur-grey ~/.icons
```

​		

그러고 나서 또 Tweaks를 실행 시켜 보면

![image-20211001083645196](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/image-20211001083645196.png)

이번에는 Icons에 Whitesur-dark가 생겼습니다. 설정을 해주면

​		

![image-20211001084150890](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/image-20211001084150890.png)

아까보다 더 MacOS에 가까워졌습니다.

​	

### 2. 폰트 변경

MacOS에서는 San Francisco font를 사용하고 있습니다. 개인적으로 사용한다면 검색 하면 많이 나오지만, 배포는 라이센스 문제가 생길 수 있습니다. 고맙게도 Garuda 라 불리는 Lucida Grande 라는 아주 비슷한 대체제가 있는데요 심지어 Ubuntu에 기본으로 포함되어 있기 때문에 귀찮게 찾아볼 필요가 없습니다.	

​	

Twekas 에서 Fonts에 들어가면 폰트를 바꿀 수 있는데요 Interface Text와 Monospace Text를 변경 해 주시면 됩니다.

![11](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/11.png)

​	

Garuda Regular로 해주시면 됩니다.

![10](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/10.png)

​	

폰트까지 바꾸니 더 좋습니다.

![12](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/12.png)

​	

### 3. 마우스 커서 변경 

https://www.pling.com/p/1355701/ 에서 맥북 커서를 받을 수 있습니다.

![image-20211001084949625](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/image-20211001084949625.png)

​	

한가지 특이한점은 cursor도 .cursor 폴더가 아닌 .icons 폴더에 저장한다는 점 입니다.

![image-20211001085020773](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/image-20211001085020773.png)

​	

다운받은 커서를 .icons 폴더에 위치 시키고 나서 다시 Tweaks를 켜면 Cursor에도 McMojave-cursors 가 추가되어 있습니다.

![image-20211001084923315](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/image-20211001084923315.png)

​	

이제 여기에 추가로 

​	

dock 을 변경 할 수도 있고 https://github.com/vinceliuice/WhiteSur-gtk-theme/tree/master/src/other/dash-to-dock

 	

Firefox를 사파리 처럼 만들 수도 있습니다. https://github.com/vinceliuice/WhiteSur-gtk-theme/tree/master/src/other/firefox

![01](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/firefox-whitesur.png)

​	

사실 수작업으로 하나씩 변경 해 보았지만, https://github.com/vinceliuice/WhiteSur-gtk-theme 를 통째로 클론해서 사용 하면 간단한 명령어 만으로 테마를 지정 할 수 있습니다. readme 파일을 읽어서 하라는 대로만 하면되지만 초보자에게는 다소 어려울 수 있어 하나씩 풀어 포스팅 해 보았습니다.

예를 들어 위의 저장소를 클론 한 뒤에 firefox 테마를 적용 시키려면 간단하게

```bash
./tweaks.sh -f 
```

만 실행 하면 끝입니다. 해봤더니 정말 safari 를 쓰는 것 같더라고요.

​		

최종적으로 Theme 제작자가 제안하는 완성본은 아래와 같습니다.

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/gtk-mac-theme.assets/ef3a77dd79e583851b8d38e4d98fcdf694c6c9d6a22bdda79d52208b0f9d4e553917.png)

​	

평소 MacOS를 즐겨 사용하는 사용자들도 충분히 만족 할 수 있는 퀄리티라고 생각합니다.


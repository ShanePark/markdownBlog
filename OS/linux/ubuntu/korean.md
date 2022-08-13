# Ubuntu 20.04 키보드 한글 입력 설정 하기

## Intro

설치 할 때에 한글 키보드를 설정 했든 안했든 처음에는 한글 입력이 안 되는 경우가 많았습니다.

Ubuntu 에서 한글 입력을 할 수 있도록 설정을 해 보도록 하겠습니다.

## iBus 설정

### Settings

일단 제일 먼저 Settings 에 들어갑니다.

![1](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/1.png)

Settings를 켠 후에는 Regions & Language 를 클릭 합니다. 키보드 설정이 따로 있는게 아니고 해당 설정은 지역 설정에서 하더라구요.

아래와 같은 화면이 나오면 Input Sources의 하단에 있는 `+` 버튼을 클릭 합니다.

![2](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/2.png)

​		

그러면 이제 모든 Input Source 들이 쭉 나오는데요, Korean을 선택해 Add 를 눌러줍니다.

![3](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/3.png)

> 혹시 목록에 보이지 않으면 ... 버튼을 클릭 하면 됩니다.		

![4](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/4.png)

> 101/104 키 말고 제일 위에 거로 해줍니다. `Korean` 선택 후에는 Add 버튼을 클릭 합니다.

​			

Korean이 추가 된 모습 입니다. English는 필요 없기 때문에 휴지통 아이콘을 클릭 해 삭제 해 주시면 됩니다.

Korean만 있으면 한/영이 내부에서 전환됩니다.

![5](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/5.png)

추가 후에는 이제 맨 아래의 Manage installed Languages를 클릭합니다.


![6](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/6.png)

> 그러면 한글 키보드가 추가 되었기 때문에 이제 필요한 파일들을 Install 해도 되겠냐고 물어보는데요, Install 을 클릭해줍니다.	

​		

![7](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/7.png)

> 다운로드 되며 설정이 적용되고 있습니다.

모든 다운로드가 완료 된 후에는 컴퓨터를 재시작 해줍니다. 로그오프만 해도 되긴 할겁니다.

### 재시작 후 

재시작 후에 같은 설정화면에 다시 돌아와서 이번에는 다시 언어 추가를 위해 사용했던 `+` 버튼을 클릭 합니다.

![1](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/1-1951496.png)			

언어 목록이 나오면

![2](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/2-1951509.png)

다시 Korean을 클릭 합니다. 그러고 나면..

아까는 없었던 Korean(Hangul) 이 추가 되어 있습니다. Add 를 해줍니다.	

![3](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/3-1951523.png)

> 새로 추가된 Korean(Hangul)

이제는 필요 없어진 맨 위의 Korean을 쓰레기통 모양 아이콘을 눌러 삭제 해 줍니다.

![1](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/1-1951637.png)

> 위에건 제거해줍니다.

이제 Korena(Hangul) 만 남았습니다. 

### 한/영키 설정

톱니바퀴 아이콘을 클릭해 설정 화면에 들어갑니다.	

![2](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/2-1951658.png)

​	

![3](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/3-1951942.png)

새로운 화면이 나왔습니다.  한/영키 토글을 위한 설정을 해 줄 건데요.

이 상태에서 이제 바로 한/영 키를 눌러 한글을 입력 할 수 있는 분들도 있겠지만 키보드에 따라 hangul 버튼이 없을 수가 있습니다. 혹은 한/영 키가 ALT R 로 되어 있는 경우도 있을 수 있습니다.

일단 우측의 Add 버튼을 클릭 하구요,	

이번에 뜨는 팝업 창에서 한/영 키로 사용하고 싶은 키를 입력 합니다.

![4](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/4-1951997.png)

그러고 OK 를 눌러 완료 합니다. 저는 Hangul 키가 따로 없어서 Alt_R 키를 사용 할 수 밖에 없네요.

이제 한/영 키 Toggle 로 사용할 Key 목록이 아래와 같이 3개가 되었습니다. OK를 눌러 저장합니다.

​	![5](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/5-1952020.png)

### 우측 Alt키 바인딩

다만 이렇게 지정 해 둔 경우 Alt_R 의 기능이 살아 있기 때문에 타자를 치면서 한/영 변환을 하다가 특정 키가 작동해서 불편 할 수가 있습니다.

예를 들어서 한영키를 누르며 곧바로 F 키를 눌렀는데, 컴퓨터가 Alt + F 를 입력한걸로 인식해서 엉뚱한 메뉴가 뜰 수 있습니다. 굉장히 불편해요.

그래서 만약 저처럼 Hangul 키가 없어 오른쪽 Alt 를 이용 해야 할 경우에는 우측 Alt 키를 아이에 Hangul 키로 바인딩 하는 것을 추천드립니다.

```bash
$ sudo vi /usr/share/X11/xkb/symbols/altwin
```

이렇게 altwin 파일을 열고

![image-20210919004635375](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/image-20210919004635375.png)	

​	6번 라인의 `key <RALT>` 에 해당하는 symbols 부분을 저처럼  `symbols[Group1] = [Hangul] };` 로 변경 해 주시면 됩니다.

대신 이 파일을 수정 한 경우에는 적용을 위해 또 다시 재부팅(혹은 로그오프)를 한번 해 줍니다.

![6](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/korean.assets/6-1952039.png)

드디어 한글이 잘 입력됩니다 ! 한글 입력하는것부터가 만만치 않습니다.

수고하셨습니다. 

## 마치며

Ubuntu를 처음 설치한다면 처음에는 iBus 키보드 설정만 하시면 되지만, 여러가지 소프트 웨어를 사용하시다 보면 한계를 느끼는 날이 곧 오게 됩니다.

끝글자 오류가 나타나는 어플리케이션들도 있고, wine 에서는 한글 입력이 아에 되지도 않습니다. 일단 iBus를 쓰다가 불편함이 있을 때 다른 입력기를 찾아 보시면 되는데요, 제가 사용해본 한글 입력기들은 아래와 같습니다.

- nimf
- [tian](https://shanepark.tistory.com/293)
- [fcitx](https://shanepark.tistory.com/262)
- [kime](https://shanepark.tistory.com/318)

 지금은 KIME를 주력으로 사용하고 있고, 가장 만족합니다. Perfect 까지는 아니지만 Best 라고는 할 수 있을 것 같습니다.

비교적 최근 우연히 찾은 레퍼런스 들인데, 처음 우분투를 접했을 때 부터 읽었다면 참 좋았을 거라고 생각되는 링크 들을 남기겠습니다.

- [우아한 형제들 기술이사 권남님 블로그](https://kwonnam.pe.kr/wiki/linux/inputmethod#input_method_%EC%9E%85%EB%A0%A5%EA%B8%B0_%ED%95%9C%EA%B8%80)
- [Dawoum wiki 한글입력기](https://dawoum.ddns.net/wiki/%ED%95%9C%EA%B8%80_%EC%9E%85%EB%A0%A5%EA%B8%B0)

추가로 우분투 첫 설치 후 빼먹은 설정이 없도록 아래의 글을 확인 해 주세요!

> [개발자를 위한 Ubuntu 모든 필수 설치와 설정](https://shanepark.tistory.com/242)

이상입니다. 감사합니다.
# Ubuntu) 터치패드 및 트랙패드 활용하기 Fusuma

​	

> https://github.com/iberianpig/fusuma

Fusuma를 사용하면 멀티 터치 제스처를 사용할 수 있습니다. 이 숨은 보석같은 어플리케이션만 설치하면 트랙패드 제스처를 활용해서, 뭔가를 줄이고 늘릴 수 있고, 쓸어 넘기기 등의 명령을 설정 하고 사용 할 수 있습니다.

Fusuma(襖) 는 일본에서의 미닫이 문을 칭하는 말 입니다. 한국어로 발음하면 "후스마" 정도가 되겠네요.

​		

## 특징

- RubyGems를 이용한 손쉬운 설치

- YAML 포맷으로 제스처와 액션 설정

- threshold 및 interval을 이용한 다양한 세부 설정

  > Threadhold는 임계점을 의미하는데요. 터치 감도를 설정한다고 생각하면 되겠습니다.

- 외부 터치패드 연결시 자동 인식

- 플러그인 시스템을 통한 제스처 확장

  

  ​		

## 설치

### 터치패드 장치를 읽기 위한 권한 설정

**중요**: Fusuma 를 통해 touchpad 입력을 받으려면 **반드시** INPUT 그룹의 멤버여야 합니다.

```
$ sudo gpasswd -a $USER input
```

![image-20211008210922249](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008210922249.png)

이제 해당 사항을 적용 시키기 위해서는 logout을 하거나 재부팅을 한번 해주세요.	

```
$ newgrp input
```

​	

### Debian 배포판의 경우 (Ubuntu, Debian, Mint, Pop!OS)

#### 1. libinput-tools 설치

`libinput` 버전이 1.0 보다 높아야 합니다.

```
$ sudo apt-get install libinput-tools
```

![image-20211008211153963](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008211153963.png)

#### 2. Ruby 설치

Fusuma는 Ruby로 실행되기 때문에 먼저 설치해야 합니다.

```
$ sudo apt-get install ruby
```

![image-20211008211305416](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008211305416.png)

#### 3. Fusuma 설치

```
$ sudo gem install fusuma
```

![image-20211008211336740](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008211336740.png)

#### 4. xdotool 설치 (선택)

단축키 전송을 위해서 설치합니다:

```
$ sudo apt-get install xdotool
```

​	![image-20211008213005659](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008213005659.png)

사실 선택이라고 써있어도 왠만한 제스처를 넣으려면 필수 입니다.

​		

### GNOME에서 터치패드가 작동하지 않을 경우

아래의 명령어를 입력 해서 touchPad 이벤트가 GNOME desktop에 전달되도록 합니다:

```
$ gsettings set org.gnome.desktop.peripherals.touchpad send-events enabled
```

​	

## 사용

```
$ fusuma
```

![image-20211008211649914](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008211649914.png)

config file이 발견되지 않았다고 나옵니다. 설정 파일을 만들어 주어야 겠네요.

```bash
$ mkdir -p ~/.config/fusuma        # 설정 폴더 만들기
$ vi ~/.config/fusuma/config.yml # 설정 파일 편집 하기
```

![image-20211008211913941](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008211913941.png)

​		

## Ubuntu 제스처 매핑 예시

> https://github.com/iberianpig/fusuma/wiki/Ubuntu

```
swipe:
  3:
    left:
      command: "xdotool key alt+Right" # History forward
    right:
      command: "xdotool key alt+Left" # History back
    up:
      command: "xdotool key super" # Activity
    down:
      command: "xdotool key super" # Activity
  4:
    left:
      command: "xdotool key ctrl+alt+Down" # Switch to next workspace
    right:
      command: "xdotool key ctrl+alt+Up" # Switch to previous workspace
    up:
      command: "xdotool key ctrl+alt+Down" # Switch to next workspace
    down:
      command: "xdotool key ctrl+alt+Up" # Switch to previous workspace
pinch:
  in:
    command: "xdotool keydown ctrl click 4 keyup ctrl" # Zoom in
  out:
    command: "xdotool keydown ctrl click 5 keyup ctrl" # Zoom out
```

손가락 3개, 4개로 하는 제스처와 손가락 두개 집기로 확대, 축소 하는 제스처기본 설정입니다.

![image-20211008212259338](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008212259338.png)

​			

설정을 저장 한 후 다시 fusuma 를 실행 해 봅니다.

![image-20211008212518477](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008212518477.png)

이제는 설정파일을 읽어오네요. 이후 정말 손가락 세개를 좌, 우로 이동시 웹에서 뒤로가기 앞으로 가기가 되었습니다. 또한 손가락 3개를 위로 올리거나 내리면 Super 키를 입력할 때 처럼 동작 했습니다.

​	

## Mac과 제스처 통일시키기

> https://github.com/iberianpig/fusuma/wiki/Ubuntu-OS-to-mimic-Mac-a-little

마침 이미 작성해 둔게 있더라고요. 하지만 막상 확인해보니 macOS와 전혀 비슷하지 않아 직접 작성해야 했습니다.

​	

손가락 3개로 드래그하기 (Fusuma 버전 2 이상)

```
swipe:
  3:
    begin:
      command: xdotool mousedown 1
    update:
      command: xdotool mousemove_relative -- $move_x, $move_y
      interval: 0.01
      accel: 2
    end:
      command: xdotool mouseup 1
```

​	

손가락 3개 드래그로 뒤로가기/ 앞으로가기

```
swipe:
  3:
    left:
      command: "xdotool key alt+Right" # History forward
    right:
      command: "xdotool key alt+Left" # History back
```

손가락 2개는 swipe가 되지 않습니다. 2: 로 하면 작동을 하지 않더군요..

처음에는 우클릭 때문인가 했는데 Github 에 들어가서 issue 들을 읽어 보니 스크롤 위,아래를 하는 것과 충돌이 심해서 그렇다고 합니다. 어쩔 수 없는 부분입니다.

​			

손가락 4개 위,아래로 super키. 맥에서의 미션컨트롤을 대체 할 수 있습니다.

```
  4:  
    up:
      command: "xdotool key super" # Activity
    down:
      command: "xdotool key super" # Activity

```

​		

손가락 4개 좌,우 이동으로 워크스페이스 변환

```
  4:
    left:
      command: "xdotool key ctrl+alt+Down" # Switch to next workspace
    right:
      command: "xdotool key ctrl+alt+Up" # Switch to previous workspace
```

Mac에서는 정말 많이 사용하죠. 

저는 이클립스를 쓸 때 단축키가 겹치는 것 때문에 ctrl+alt+ up & Down 대신 Super+ Pgup과 Pgdown을 매핑 해 두었다 보니 아래 처럼 변경 했습니다. 보통의 경우는 위에처럼 해주시면 됩니다.

```
    left:
      command: "xdotool key super+0xff55" # Switch to previous workspace , pageUp
    right:
      command: "xdotool key super+0xff56" # Switch to next workspace , pageDown

```

​	

두손가락 pinch로 확대 하고 축소하기

```
pinch:
  2:
    in:
      command: "xdotool keydown ctrl click 4 keyup ctrl"
    out:
      command: "xdotool keydown ctrl click 5 keyup ctrl"

```

이것도 필수 제스쳐죠.

​	

## Threshold 와 Interval

만약 command: 프로퍼티가 비어 있으면, swipe나 pinch는 어떤 커맨드도 수행하지 않습니다.

`threshold:` 는 swipe나 pinch의 민감도를 의미합니다. 기본값은 1로, 만약 swipe의 thradhold가 0.5라면 반응을 시작하는 swipe의 길이가 절반으로 짧아지는 것을 의미합니다.

`interval:`은 wsipe와 pinch 사이의 딜레이를 의미합니다. 기본 값은 역시 1 입니다. 만약 swipe의 interval이 0.5라면 다음 swipe를 인식할 때까지의 인터벌이 절반으로 짧아집니다.

swipe 설정은 개인적으로 1은 너무 길다고 느껴져서 확실히 줄여야 할 필요가 있었습니다. pinch의 인터벌도 줄이지 않으면 정말 답답합니다.

​	

## 최종설정	

```
swipe:
  3:
    left:
      command: "xdotool key alt+Right" # History forward
    right:
      command: "xdotool key alt+Left" # History back        
    begin:
      command: xdotool mousedown 1
    update:
      command: xdotool mousemove_relative -- $move_x, $move_y
      interval: 0.01
      accel: 2
    end:
      command: xdotool mouseup 1
  4:
    up:
      command: "xdotool key super" # Activity
    down:
      command: "xdotool key super" # Activity
    left:
      command: "xdotool key super+0xff55" # Switch to previous workspace , pageUp
    right:
      command: "xdotool key super+0xff56" # Switch to next workspace , pageDown

pinch:
  2:
    in:
      command: "xdotool keydown ctrl click 4 keyup ctrl"
    out:
      command: "xdotool keydown ctrl click 5 keyup ctrl"

threshold:
  swipe: 0.5
  pinch: 0.3

interval:
  swipe: 0.5
  pinch: 0.5

```

최종적으로 제가 셋팅하여 사용 하고 있는 설정 입니다. 맥북 사용자라면 손에 익으실 거라고 생각합니다.	

​	

## 매직트랙패드2

매직 트랙패드 2를 연결하면 사용 할 수 있을까요? 한번 연결을 해 보았습니다. 연결해서 fusuma를 사용하니 자동으로 드라이버를 잡아 주었고, 

```
fusuma -l
```

을 입력해 사용 가능한 디바이스 목록을 확인 하니 방금 연결한 Apple Inc. Magic Trackpad 2 가 목록에 보입니다.

![image-20211008232352778](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008232352778.png)

​	

## 데몬에서 실행

항상 터미널을 켜 둘수는 없으니 데몬에서 켜는게 좋겠네요.  

```bash
fusuma -d 
```

를 입력하면 데몬에서 실행되는데요, 그것보다 아에 자동으로 실행되게끔 gnome-session-properties에 등록 해주는게 좋겠죠.

​	

## 자동실행 (gnome-session-properties)

- `$ which fusuma` 를 입력해 fusuma가 어디에 설치된지 확인 합니다.

![image-20211008232916841](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008232916841.png)

- `$ gnome-session-properties` 를 엽니다. 터미널에 입력하면 실행 됩니다.

![image-20211008232857228](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008232857228.png)

- Fusuma 를 추가 합니다. 위에서 확인한 경로를 입력합니다.

  ![image-20211008233000818](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008233000818.png)

- 커맨드 필드 마지막에 -d 옵션을 넣어 데몬에서 실행되도록 합니다.

![image-20211008233133934](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fusuma.assets/image-20211008233133934.png)

​	

이후 시스템을 한번 껐다 켜면, fusuma가 자동으로 데몬에서 작동됩니다.

​			

## 업데이트

업데이트 할 내용이 있을때는 아래의 명령어를 입력하면 업데이트가 된다고 합니다. 아직은 쓸일이 없습니다.

```
$ sudo gem update fusuma
```

​		

## 마무리

fusuma 덕분에 우분투에서도 트랙패드와 터치패드의 활용이 굉장히 좋아 졌습니다. 특히 매직트랙패드를 연결해서 사용하니 맥북을 사용할때와 상당히 유사한 경험을 할수 있어서 굉장히 만족스럽습니다.

​	


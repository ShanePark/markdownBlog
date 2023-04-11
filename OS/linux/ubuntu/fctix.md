# Ubuntu 20.04) fctix입력기 설치해 intelliJ 한글입력 해결하기

​		

## iBus 입력기의 문제

지금까지 잘 ibus를 사용해왔는데 intelliJ에서 한글 입력시 이상하게 입력되는 현상이 있다는걸 알게 되었습니다.

띄어쓰기를 할 때 자꾸 해당 자리에서 하는 게 아니고 이전 자리에서 하게 되고, 방향키를 입력 할 때에도 마찬가지로 문제가 생겼습니다. 

![image-20211020093734578](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020093734578.webp)



해결을 위해 더이상 iBus 입력기를 사용하지 않고 fctix 입력기로 변경 해 보겠습니다.

​	

## fcitx 한글 입력기 설치

```bash
sudo apt-get update
sudo apt-get install fcitx-hangul
```

이후 재부팅을 해줍니다.

​	

Language Support 실행

![image-20211020090825731](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020090825731.webp)

​	

Keyboard input method system을 fcitx로 변경

![image-20211020090853097](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020090853097.webp)

​	

Install / Remove Languages 를 눌러 Korean 추가

> 저는 혹시 몰라 기존의 Korean을 지우고 다시 깔긴 했지만 별 상관은 없을 듯 합니다.

![image-20211020091205197](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020091205197.webp)

​		

우측 상단에 생긴 아이콘을 클릭하고 Configure를 클릭

![image-20211020091051194](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020091051194.webp)

​	

fcitx-config-gtk 가 없다는 에러가 뜹니다.

![image-20211020085931463](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020085931463.webp)

​	

에러 메시지에서 알려주는 대로 pakcage를 설치해 줍니다.

```bash
sudo apt-get install fcitx-config-gtk
```

![image-20211020090004217](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020090004217.webp)

​	

설치후에는 정상적으로 설정 화면이 뜹니다. 처음에는 Keyboard - English (US) 가 있습니다.

![image-20211020090031592](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020090031592.webp)

​		

좌측 하단의 + 버튼을 클릭 해서 hangul 검색 후 추가

![image-20211020090105285](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020090105285.webp)

​	

Global Config에서 Trigger 설정 (한/영 전환 키)

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020132717132.webp)

Hangul 로 변경 해 주면 됩니다. Extra key for trigger input Method는 다른 한/영 전환 키가 필요하면 추가합니다.

​		

## Hangul키 대신 오른쪽 Alt 키를 사용한다면?

만약 Hangul 키가 없어 오른쪽 Alt 를 이용 해야 할 경우에는 우측 Alt 키를 아에 Hangul 키로 바인딩 하는 것을 추천드립니다.

그대로 오른쪽 Alt 기능을 살려둔다면 한영키를 누르며 키 입력을 할 때 조합키가 종종 보내지는 문제가 발생합니다.

```
$ sudo vi /usr/share/X11/xkb/symbols/altwin
```

이렇게 altwin 파일을 켜서

![image-20210919004635375](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20210919004635375.webp)

 6번 라인의 key 에 해당하는 symbols 부분을 위와같이 symbols\[Group1\] = \[Hangul\] }; 로 변경 해 주시면 됩니다.

대신 이 파일을 수정 한 경우에는 재부팅을 한번 해야 적용이 됩니다.

​		

모든 설정이 끝나고 나니 intelliJ 에서 한글 입력 할 때 문제가 없습니다.

![image-20211020091547012](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020091547012.webp)

​	

하지만 딱 하나, 한/영 키 전환시 자꾸 아래와 같은 팝업이 뜨는게 성가십니다

<img src="https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020092838008.webp" alt="image-20211020092838008" style="zoom: 150%;" />

​	

해당 문제도 설정으로 간편하게 해결 할 수 있습니다.

```bash
gedit ~/.config/fcitx/config 
```

![image-20211020092930569](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020092930569.webp)

`ShowInputWindowAfterTriggering`에 True 라고 써있는 부분을 주석 제거 후 False로 변경합니다.

![image-20211020093024301](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020093024301.webp)

​	

저장 후에는 fcitx 를 재시작 해 줍니다.

```bash
fcitx -r
```

​	

이제 한영키를 전환 할 때 따로 팝업이 뜨지 않습니다.

​		

## Fctix ; 입력시 팝업

하지만 문제를 또 하나 발견했습니다. 한글 상태에서 ; 입력시에 

![image-20211020114350420](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020114350420.webp)

자꾸 이런게 뜨는데 아주 짜증 지대로 입니다.

​	

![image-20211020114421040](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020114421040.webp)

Configuratiuon 에서 Addon 에 들어가 Quickphrase를 찾습니다. Configure를 누르고

​	

![image-20211020114600214](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020114600214.webp)

Trigger Key for Quick Phrase 는 `;` 에서 `None` 으로 변경하고, Do not show spell hint 를 체크합니다.

그러면 이제 더이상 그 짜증나던 팝업이 뜨지 않습니다.

​			

수고하셨습니다. 

​	

## UIM-Byeoru

intelliJ 에서 입력할 때 좌측 하단에 뭔가 뜨던게 거슬려서 uim 입력기도 사용 해 보았습니다.

```bash
sudo apt install uim uim-byeoru
```

​	

벼루를 사용하시려면, 위에서의 설정과 비슷하지만 키보드 입력기를 UIM 으로 변경하고 (재부팅)

![image-20211020131508944](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020131508944.webp)

​	 

Input Method 를 실행 해서 default IM을  Byeoru 로 설정하고, Input method toggle을 "hangul" 로, Byeoru key bindings 1 에서 맨 위 두개를 "hangul" 로 변경 해 주시면 됩니다.

![image-20211020131435379](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020131435379.webp)

혹시 한영 전환이 잘 안되는 것 같으면 우측 하단의 입력기 창 에서 A 를 누르고 "한글글자" 로 변경해 주면 됩니다. 

![image-20211020131600787](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntu/fctix.assets/image-20211020131600787.webp)

​	

개인적으로 아직까지는 fctix가 가장 낫습니다.

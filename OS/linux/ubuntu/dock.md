# Ubuntu 20.04 Mac OS 처럼 Dock 가운데 위치하게 변경하기

​	

rerefence

> https://linuxconfig.org/how-to-customize-dock-panel-on-ubuntu-20-04-focal-fossa-linux

​	

GNONE은 Ubuntu 20.04의 기본 데스크탑 환경 입니다. 처음에 여러분이 Ubuntu 를 띄웠을때는 스크린의 왼쪽에 Dock이 떠 있습니다. Windows 나 Mac 사용자들에게는 익숙하지 않은 화면 인데요. 지금부터 Dock을 커스터마이징 해 보도록 하겠습니다.

![1](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntudock.assets/1.png)

​	

## 해당 튜토리얼을 통해 함께 해볼 내용들

- 설정에서 dock 모양 변경하기

- granular dock pandel 설정을 위해 dconf-Editor 사용 하는 방법 

- 커맨드라인을 통해 설정 바꾸는 방법

- dock panel에 원치 않는 설정이 되었을때 되돌리는 방법

  

아래의 명령어를 입력해 dconf-editor를 설치 해 줍니다.

```shell
$ sudo apt install dconf-editor
```

​			![2](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntudock.assets/2.png)

설치가 완료 되었습니다.



이제 어플리케이션 목록에서 dconf Editor를 사용 할 수 있습니다. 실행 해 주세요.	

![3](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntudock.assets/3.png)	

​	

무시무시한 경고가 나옵니다. 잘못 사용할 경우 application을 망가뜨릴 수 있으니 꼭 유념 해 주세요.

![4](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntudock.assets/4.png)

​		

처음 실행했을 때의 모습은 아래와 같습니다.

![5](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntudock.assets/5.png)

​	

아래의 경로로 찾아가주세요.

```bash
org > gnome > shell > extensions > dash-to-dock
```

​	

그러면 아래와 같은 화면을 확인 할 수 있습니다.

![6](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntudock.assets/6.png)

​	

여기에서 extend-height 를 찾아 비활성화 시켜줘 보겠습니다.

![7](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntudock.assets/7.png)

보이는 것 처럼 dock 이 한 가운데로 이동 하였습니다 !

​	

같은 변화를 command line 을 통해서도 하실 수 있습니다. 가운데 위치한 걸 다시 돌려 보겠습니다.

```bash
$ gsettings set org.gnome.shell.extensions.dash-to-dock extend-height false
```

![8](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntudock.assets/8.png)

​	

이번에는 실행중인 어플리케이션을의 배경색을 다르게 지정 해 보겠습니다.

```bash
$ gsettings set org.gnome.shell.extensions.dash-to-dock unity-backlit-items true
```

​	![9](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntudock.assets/9.png)

보이는 것 처럼 실행되고 있는 아이콘들은 배경색이 회색으로 표시되었습니다.



자유롭게 원하시는 대로 변경 하시다가 원하지 않는 설정이 되었을때는 되돌려야 합니다.

아래처럼 실수로 사이즈를 100 으로 지정 했는데 기본 사이즈로 변경 하고 싶을 때가 있을 수 있습니다.

![10](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntudock.assets/10.png)

​	

그때는 reset 을 해준다면 해당 설정은 기본 설정으로 돌아갑니다.

```bash
$ gsettings reset org.gnome.shell.extensions.dash-to-dock dash-max-icon-size
```

![11](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/OS/linux/ubuntudock.assets/11.png)	

​	

## 결론

본 포스팅을 통해 어떻게 Ubuntu 20.04의 GNOME desktop 환경 설정을 하는지 알아 보았습니다. 기본 설정과 dconf-Editor의 추가적인 설정을 통해 dock panel을 여러분이 원하는대로 변경 할 수 있게 되셨습니다.

고생하셨습니다. 간단하게 원하는 Dock 모양으로 만들어서 사용하세요 
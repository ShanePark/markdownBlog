# 티스토리 다크모드 적용하기

​	

블로그에 들어올 때 마다 새하얀 화면에 너무 눈이 부셔서 다크모드를 적용하기로 했습니다.

쉽지 않은 작업이 될 거라고 생각은 했지만 세부적으로 워낙에 신경쓸 부분이 많기 때문에 생각보다 시간이 제법 소요되었습니다.

간단하게 설정을 변경한다고 끝나는 문제가 아니고 일반모드/ 다크모드 각각의 개별 css를 따로 부여해야 합니다. 단순하게 색반전을 할 수도 있지만 완성도를 위해서는 한땀 한땀 정성이 많이 들어갑니다.

​	 

## 다크모드 적용에 앞서

1. CSS 에 대한 기본 지식이 있어야합니다.

사실 color만 변경하기 때문에 그렇게 어려운 내용은 아니지만 최소 html 이나 css를 접해 본 적은 있어야 합니다.

2. 글 작성/ 수정 블로그 관리 화면은 변경이 되지 않습니다.

해당 부분들의 css는 티스토리가 관리하기 때문에 글 쓸떄 여전히 눈뽕맞는건 감안 하셔야 합니다. 하지만 방문자 입장에서는 쾌적한 다크모드를 온전히 누릘 수 있습니다.

##### 3. 생각보다 많이 바꿔야합니다.

중간에 지쳐서 포기하면 시간낭비가 됩니다. 하다 말면 아에 못써먹어서 할려면 끝까지 해야합니다. 저는 1시간 이상 소요된 듯 합니다. 특히 개발 블로그 특성상 markdown의 사용이나 코드블럭을 사용하는 일이 많아 신경을 추가로 써야 합니다.

 

## 다크모드 만들기 

일단 블로그관리 - > 스킨 편집 메뉴로 갑니다.

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/HTML-CSS/tistory-dark.assets/img.png)

​	

우측 상단의 HTML 편집을 클릭합니다.

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/HTML-CSS/tistory-dark.assets/img-16327139124842.png)

​	

우측 상단의 CSS 를 클릭합니다.

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/HTML-CSS/tistory-dark.assets/img-16327139267324.png)

​	

이제 여기에서 적당한 위치에 아래의 코드를 입력합니다. light mode 보다는 아래에 있어야겠죠? css 파일 제일 아래에 넣으시면 일단 되겠습니다. css 파일이 제가 컨트롤 할 수 있는게 있고 못하는게 있는데 가끔씩은 !important 가 필요합니다. Tistory 내부적으로 관리하는 css의 경우에는 제가 변경은 할 수 없으니 우선순위로라도 밀어 붙이는 겁니다. 아래에 !important가 들어갈 필요는 굳이 없지만, 예시로 넣었습니다.

```css
@media (prefers-color-scheme: dark){
	body{
		background-color: #222 !important;
	}
}

```

​	

일단 위의 @media 설정 안에 들어가는 내용들은 사용자의 설정이 다크 모드일때만 동작 하는 css 들 입니다.

body 의 background를 까맣게 했으니, 이렇게 설정을 저장 한 뒤에 페이지를 한번 확인 해 보시면 난리가 나 있을 겁니다. 까만 글자가 배경에 가려 사라졌으니 또 이번엔 각각 사라진 글자들의 엘리먼트를 찾아서 위의 블럭 안에 넣고 글자 색을 적당하게 (저는 dark grey를 사용합니다) 넣어줘야 합니다. 하나씩 하나씩 불편한 요소들을 다 수정 하면서 하다 보면 점점 괜찮아 지는게 눈에 보입니다.

​	

제가 도와드릴 수 있는건 딱 여기까지 입니다. Chrome 기준 Cmd+Shift+C (Ctrl + Alt + C) 를 누르고 요소를 하나 하나 확인해가며 해당 요소의 클래스명과 css속성을 확인해 @media (prefers-color-scheme: dark) 덮어주면 됩니다.

​		

## 다크모드 켜고 끄기 

추가적으로, MacOS 나 Windows에서는 prefers-color-scheme으로 인식이 되지만, Linux 컴퓨터의 경우에는 Web Browser가 해당 유저가 DarkMode 를 사용중인지를 확인 할 방법이 없기 때문에 지금처럼 사용자 설정을 읽어와서 하는 방법은 불가능합니다. 이럴 때는 제가 지금 사용하는 것 처럼 버튼을 따로 만들어서 toggle 을 시키면 되지만 난이도가 훨씬 올라가기 때문에 어느 정도 익숙해 진 후에 하는 것을 추천드립니다.

javascipt 에서 사용자의 OS에서의 다크모드 적용 여부를 확인 하려면

```javascript
const userPrefersDark = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;

const userPrefersLight = window.matchMedia && window.matchMedia('(prefers-color-scheme: light)').matches;

```

위와 같은 코드를 작성 하시면 됩니다. 각각의 변수는 true/ false 값을 가지고 있습니다. 힌트를 드리자면 저는 javascript의 localStorage를 이용해 dark모드 적용 여부를 클라이언트의 브라우저에 저장 해 둡니다. 해당 값이 없을 경우에는 default로 prefers-color-schme을 읽어 옵니다.

​	

 참고를 위해 제가 작성해 블로그에서 사용 중인 코드를 올려 드립니다.	

```javascript
let isDarkMode = localStorage.getItem("isDarkMode");
if(isDarkMode == null){
    isDarkMode = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
}else{
    isDarkMode = (isDarkMode=='true');
}		

```

​	

이렇게 해서 다크모드 설정에 대한 여부를 저장하고, 각각의 버튼을 만듭니다. dark모드 적용 버튼을 누르면 'body'에 .dark 라는 클래스명을 입힙니다. body 를 불러올 때 스크립트를 제일 먼저 실행하게 해야 처음 다크모드를 불러올때 하얀 화면에 먼저 잠깐 나와서 문서가 깜빡거리는 문제를 해결 할 수 있습니다.

```javascript
	if(isDarkMode){
		$('body').addClass('dark');
	}

```

​	

## 마무리

물론 절대로 쉬운 작업은 아니지만, 한번 한다면 본인의 블로그에 더욱 애착이 생길 만큼 재미있고 효과가 좋은 작업입니다. 한번씩 도전 해 보세요.


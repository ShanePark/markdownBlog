# 블로그에 포스팅 읽는데 걸리는 시간 표시해주기 by reading-time.js

> https://shanepark.tistory.com/224 에 이어지는 글 입니다.

이번에는 블로그에서 직접 적용을 시켜보도록 하겠습니다. 		

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910223242280.webp width=600 height=400 alt=1>

​	블로그 적용하기 전 화면입니다. 작성 일 우측에 공간이 꽤 남기 때문에 해당 공간이 넣으면 되겠습니다.



우측 상단 버튼을 눌러 블로그 관리에 들어 갑니다.	

![image-20210910223356462](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910223356462.webp)

​	

그러고 나서 좌측의 꾸미기 메뉴에 보면 "스킨 편집" 버튼이 있습니다.

![image-20210910223530517](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910223530517.webp)

​	

거기에서는 html 편집 으로 들어갑니다.

![image-20210910223554320](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910223554320.webp)



이제 수정할 요소가 어느 부분에 있는지 찾아야겠죠? Mac 에서는 Command + Shift + C, 윈도우에서는 Ctrl + Shift + C 를 누르고 찾고자 하는 요소에 마우스를 올립니다.

![image-20210910223733603](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910223733603.webp)

>  제목이 있는 요소가 h2.title-article 입니다. 

​	

금방 찾아 줍니다.

![image-20210910223816316](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910223816316.webp)

​	

html 요소를 편집 하기 전에 먼저 chrome 개발자 툴 에서 수정하면서 어떻게 보일지를 대충 수정 해봅니다.

![image-20210910224106285](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910224106285.webp)

​	

![image-20210910224136902](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910224136902.webp)

일단 css 는 나중에 잡기로 하고 대충 이런 식으로 넣으면 될 것 같습니다.

​	

간단하게 입력 했던 css 와

```css
    margin-left: 20px;
    font-size: 1.5em;
```



넣을 요소를 기록 해 둡니다.

```html
<span class="eta"></span>
```

​	

그러고는 해당 하는 위치에 쏙 넣어줍니다.

![image-20210910224327254](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910224327254.webp)

​	

이웃 하는 css 를 확인 해서 

![image-20210910224417471](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910224417471.webp)

​	

style.css 의 2973 라인을 확인 한 후에 

![image-20210910224445672](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910224445672.webp)

​		

찾기 쉽게 나란히 넣어줍니다.

![image-20210910224513216](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910224513216.webp)

​	

이제 자바스크립트도 첨부 해 주어야겠네요.

​		

![image-20210910224631748](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910224631748.webp)

Tistroy 에서 정적자원은 "파일 업로드"를 이용해서 관리 할 수 있습니다.

여기에서 **+추가** 버튼을 누르고

​	

![image-20210910224739721](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910224739721.webp)

js 파일을 업로드 해 준 다음에



업로드 된 파일을 확인 한 후에	

![image-20210910224756109](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910224756109.webp)

​	

적당한 위치에 스크립트 참조 문을 넣어 줍니다.

![image-20210910224901996](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910224901996.webp)

​		

저장하고 페이지를 새로 고침 해서 js 파일을 잘 받아오는지 확인합니다. 문제 없네요 !

![image-20210910225344589](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910225344589.webp)

​	

이제 readingtime.js 를 호출 하도록 script를 작성합니다.

​	

![image-20210910225440324](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910225440324.webp)

body 의 맨 아래쪽으로 내려가니 $(function() 안에, document가 준비 되었을 때 실행 될 함수들이 선언 되어 있습니다. 여기에 넣어주도록 하겠습니다.

​	

아래와 같이 추가 했습니다.

![image-20210910225936457](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910225936457.webp)

```javascript
// reading-time.js 발동
	$('.article-view').readingTime({
		readingTimeAsNumber: false,
		readingTimeTarget: $('.eta'),
		wordsPerMinute: 300,
		round: true,
		prependTimeString: '📚 읽는시간 : ',
		lang: 'kr'
	});
```

> 소스 코드를 문장으로 인식하다 보니, wordsPerMinute 을 꽤나 높여주지 않으면 읽는 시간이 말도 안되게 길어져서 숫자를 올렸다 내렸다 조절 해 보고 있습니다.



잘 작동하는지 확인 해 보겠습니다.	

![image-20210910230354040](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/jsLibrary/readingtime/reading-time-blog.assets/image-20210910230354040.webp)

읽는 데 소요되는 시간이 표시됩니다 ! 꽤나 재밌었네요.

​	

블로그에 이것 저것 적용을 시키며 공부하니 html과 css 는 관심이 좀 떨어지는 분야였는데 재미있게 실습 할 수 있어서 좋습니다.

이상입니다
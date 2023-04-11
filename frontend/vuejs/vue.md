# Vue.js 사용 하기 1 ) 기본 문법

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816194319145.webp width=750 height=500 alt=1>

## Vue.js란?

공식 홈페이지에서 Vue.js는 유저인터페이스를 만들기 위한 진보된 **프레임워크**라고 소개하고 있습니다. 핵심 라이브러리는 오직 view layer 에 포커스가 맞춰져 있습니다. 사용하기 쉽고 다른 라이브러리들이나 이미 생성된 프로젝트들과도 쉽게 통합 할 수 있습니다. 또한 최신 기술들과 지원하는 라이브러리들과 함께 사용한다면 정교한 단일 페이지 어플리케이션을 만들 수도 있다고 합니다. 

개발자들에게 익숙한 프로그래머스 또한 Vue.js로 개발되었습니다.



### 스프링 부트 프로젝트를 하나 생성해보도록 하겠습니다.

사실 Vue.js는 자바스크립트 프레임워크기 때문에 Node.js 를 쓰건 SpringBoot를 사용하건, 아니면 아에 그냥 .html 파일만 만들어서 해도 무방 할 듯 합니다.

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816191954366.webp width=500 height=600 alt=2>

​	

Dependency 는 Web 정도 있으면 일단 될텐데, 봐서 DB도 연동하기 위해 JPA와 PostgreSQL Driver도 추가 해 보았습니다.

다만, JPA를 추가 하면 반드시 datasource 를 입력 해야하기 때문에 

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816192452274.webp)

​	

![image-20210816192530764](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816192530764.webp)

Finish를 눌러 프로젝트를 생성 합니다.



### application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=shane
spring.datasource.password=java
```

쓸지 안쓸지도 모르는 JPA와 PostgreSQL의 의존성을 추가 했기 때문에 일단 DB 접속 정보를 application.properties에 추가 합니다. DB를 쓰지 않는다면 dependency 추가할 때 애초에 빼는게 좋고, 실수로 추가했다면 pom.xml에 가셔서 jpa 관련 dependency를 제거 해 주시면 됩니다.



src/main/resources/static 폴더에 index.html 파일을 만들겠습니다.

![image-20210816193944453](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816193944453.webp)

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vue.js</title>
</head>
<body>
	<h3>Hello Vue.js</h3>
</body>
</html>
```

​	

이제 준비는 끝났습니다. 서버를 실행 하면 index.html 파일의 내용이 나옵니다.

![image-20210816194207196](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816194207196.webp)

## 지금부터 Vue.js 기본을 하나씩 따라해보겠습니다.

https://vuejs.org/v2/guide/ 의 공식 가이드를 참고했습니다.

​	

![image-20210816195246816](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816195246816.webp)

일단 Vue.js를 추가합니다. 저희는 개발자기 떄문에 위에 있는 development version을 사용하겠습니다.

```html
<!-- development version, includes helpful console warnings -->
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
```



해당 내용을 추가 한 후 페이지를 새로 고침 해보니 vue.js 파일을 정상적으로 불러 옵니다.

![image-20210816195444386](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816195444386.webp)

# 

당연히 처음에는 Hello world를 빼놓을 수 없겠죠 ?

![image-20210816195558941](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816195558941.webp)



간단하게 따라 해 봅니다.

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vue.js</title>
<!-- development version, includes helpful console warnings -->
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
</head>
<body>
	<div id="app">{{ message }}</div>

	<script type="text/javascript">
	var app = new Vue({
		  el: '#app',
		  data: {
		    message: 'Hello Vue!'
		  }
		})
	</script>
</body>
</html>
```



Vue 인스턴스를 만들어 el 속성을 설정해 app 이라는 이름의 element 에 종속 시켰습니다. 

data 속성을 통해 message 라는 변수에 Hello Vue! 라는 데이터를 넣었구요. 이제 페이지가 어떻게 나오는지 확인 해 보겠습니다.



![image-20210816200335359](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816200335359.webp)

아주 간단하지만 처음으로 Vue.js 를 이용해 페이지를 만들어 보았습니다.

​	

![image-20210816200434631](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816200434631.webp)



이번에는 v-bind 속성을 한번 보겠습니다. 위의 코드를 복사해서 vue02.html 파일을 작성해보았습니다.

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
</head>
<body>
	<div id="app-2">
		<span v-bind:title="message"> 
			Hover your mouse over me for a few seconds to see my dynamically bound title!
		</span>
	</div>
	
	<script type="text/javascript">
	var app2 = new Vue({
		  el: '#app-2',
		  data: {
		    message: 'You loaded this page on ' + new Date().toLocaleString()
		  }
		})
	</script>
</body>
</html>
```



![image-20210816201136239](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816201136239.webp)



마우스를 몇 초 동안 올리고 있으면 v-bind에 의해 message가 출력 되는 것을 확인 했습니다. 



![image-20210816201250286](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816201250286.webp)

v-bind는 directive 입니다. Vue 에서는 특별한 속성들을 v- 로 시작하는데요, 렌더링 되는 DOM 요소에 특별한 동적인 행동을 추가할 수 있습니다.



## 이번엔 조건문과 반복문을 확인 해 보겠습니다.

​	

![image-20210816201527489](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816201527489.webp)

조건문은 위에 보이는 것 처럼 v-if 속성을 넣어 사용 할 수 있습니다.



### vue03.html

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
</head>
<body>
	<div id="app-3">
	  <span v-if="seen">Now you see me</span>
	</div>
	
	<script type="text/javascript">
	var app3 = new Vue({
		  el: '#app-3',
		  data: {
		    seen: true
		  }
		})
	</script>
</body>
</html>
```



위와 같이 코드를 작성하면

![image-20210816201630034](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816201630034.webp)

seen 변수가 true로 지정되어 있기 때문에 해당 내용이 표시 됩니다. seen:false 라면 아무것도 보이지 않습니다.

​	

이번엔 반복문입니다.

![image-20210816202605489](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816202605489.webp)



### vue04.html

```html
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
</head>

<body>
	<div id="app-4">
		<ol>
			<li v-for="todo in todos">
				{{ todo.text }}
			</li>
		</ol>
	</div>

	<script type="text/javascript">
		var app4 = new Vue({
			el: '#app-4',
			data: {
				todos: [
					{ text: 'Learn JavaScript' },
					{ text: 'Learn Vue' },
					{ text: 'Build something awesome' }
				]
			}
		})
	</script>
</body>

</html>
```



### 저장하고 확인해보면

![image-20210816202552743](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816202552743.webp)

Vue.js 플러그인을 이클립스에 깔았더니 훨씬 낫네요 ! todos 배열의 데이터들을 하나씩 반복하며 보여줍니다.



### 이번엔 input 데이터를 다루어 보겠습니다.



![image-20210816203040203](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816203040203.webp)



### vue05.html

```html
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
</head>

<body>
	<div id="app-5">
		<p>{{ message }}</p>
		<button v-on:click="reverseMessage">Reverse Message</button>
	</div>

	<script type="text/javascript">
		var app5 = new Vue({
			el: '#app-5',
			data: {
				message: 'Hello Vue.js!'
			},
			methods: {
				reverseMessage: function () {
					this.message = this.message.split('').reverse().join('')
				}
			}
		})
	</script>
</body>

</html>
```

![image-20210816203308568](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816203308568.webp)

methods: 속성에 Reverse Message 라는 함수를 선언 해 두고, 해당 버튼에 v-on:click 속성으로 클릭시 해당 함수를 호출하게끔 되어 있습니다. 눌렀을 때 글자가 반대로 출력되는 것이 확인됩니다.



### vue06.html

```html
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
</head>

<body>
	<div id="app-6">
		<p>{{ message }}</p>
		<input v-model="message">
	</div>

	<script type="text/javascript">
		var app6 = new Vue({
			el: '#app-6',
			data: {
				message: 'Hello Vue!'
			}
		})
	</script>
</body>

</html>
```



vue06.html 파일은 input 태그에 텍스트가 변경되면 바로 바로 해당 내용으로 변경되게끔 되어 있습니다.

v-model 속성을 사용해 양방향 데이터 바인딩이 가능합니다.

![image-20210816203628267](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816203628267.webp)



### Composing과 Components

![image-20210816204010685](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vue.assets/image-20210816204010685.webp)



Vue.js에서의 또 다른 중요한 개념인 Component System에 대한 설명입니다. 거대한 규모의 어플리케이션을 구성 할 때, 작은 단위의 사용 가능한 컴포넌트들로 쪼개서 사용하도록 해줍니다. 거의 모든 어플리케이션 인터페이스를 트리 구조의 컴포넌트들로 추상화 할 수 있다고 하네요.

### vue07.html

```html
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
</head>

<body>
	<div id="app-7">
		<ol>
			<todo-item v-for="item in groceryList" v-bind:todo="item" v-bind:key="item.id"></todo-item>
		</ol>
	</div>

	<script>
		Vue.component('todo-item', {
			props: ['todo'],
			template: '<li>{{ todo.text }}</li>'
		})

		var app7 = new Vue({
			el: '#app-7',
			data: {
				groceryList: [
					{ id: 0, text: 'Vegetables' },
					{ id: 1, text: 'Cheese' },
					{ id: 2, text: 'Whatever else humans are supposed to eat' }
				]
			}
		})
	</script>
</body>

</html>
```



작은 몇 개의 단위로 나뉘었습니다. 더욱 큰 어플리케이션에서는 

​	

```html
<div id="app">
  <app-nav></app-nav>
  <app-view>
    <app-sidebar></app-sidebar>
    <app-content></app-content>
  </app-view>
</div>
```

이런 식으로 컴포넌트를 세분화 해서 작성 한다고 합니다.



간단하게 Vue.js를 공부해 보았는데요, 아직 프로젝트에서 사용하기에는 어려움이 있겠지만 진행중인 프로젝트 코드 리딩에는 어느정도 도움이 되지 않을까 생각합니다.



이상입니다. 해당 포스팅의 프로젝트 내 모든 코드들은 https://github.com/Shane-Park/markdownBlog/tree/master/projects/vuejs 에서 확인 하실 수 있습니다.


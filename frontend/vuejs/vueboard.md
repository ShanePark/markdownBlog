# Vue.js 사용 하기 2 ) PostgreSQL로 간단 게시판 작성 해보기

​	

Vue.js, SpringBoot, PostgreSQL, Hibernate 중 익숙하지 않은 기술이 있는 분은 아래의 두 포스팅을 참고하시면 코드를 잘 읽을 수 있습니다.

### [Vue.js 사용 하기 1 ) 기본 문법](https://shanepark.tistory.com/201)

> 윗 글에 이어지는 글입니다.

### [SpringBoot + PostgreSQL + Hibernate ) 간단한 게시판 만들기](https://shanepark.tistory.com/193)

 >  해당 글에서 SpringBoot + PostgreSQL + Hibernate 로 만들어둔 간단 게시판 api를 활용해서 Vue.js 로 화면을 만들어 보겠습니다.

​		

이번에는 Vue.js 를 이용해 아주 간단한 게시판 페이지를 렌더링 해 보도록 하겠습니다.

​	

### 전체적인 패키지 구조는 아래와 같으며 자바 코드는 모두 위의 간단 게시판 만들기에서 작성한 restapi 들을 그대로 가져와서 활용했습니다.

![image-20210817222928597](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vueboard.assets/image-20210817222928597.png)

​	

### 일단 pom.xml 부터 확인해보겠습니다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.5.3</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.shane</groupId>
	<artifactId>vuejs</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>vuejs</name>
	<description>vuejs practice</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```

기본적인 Spring-boot-starter-web 에 jpa, lombok, postgresql을 추가했습니다.

​	

### 이번에는 application.properties 입니다.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=shane
spring.datasource.password=java
spring.mvc.hiddenmethod.filter.enabled=true
spring.sql.init.encoding=UTF-8
```

간단하게 DB 접속 정보를 가지고 있습니다.

​	

### BoardRestController.java

```java
package com.shane.controller;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shane.model.Board;
import com.shane.model.Result;
import com.shane.repositories.BoardRepository;
import com.shane.service.BoardService;

@RestController
@RequestMapping(value="restapi/board")
public class BoardRestController {
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(BoardRestController.class);

	@Autowired
	BoardRepository repository;
	
	@Autowired
	BoardService boardService;
	
	@GetMapping
	public Result retrieveBoardList() {
		Result result = boardService.retrieveBoardList();
		return result;
	}
	
	@GetMapping("/{boardno}")
	public Result retrieveBoard(@PathVariable Integer boardno) {
		Result result = boardService.retrieveBoard(boardno);
		return result;
	}
	
	@PostMapping
	public Result createBoard(@ModelAttribute Board board) {
		Result result = boardService.createBoard(board);
		return result;
	}
	
	@PutMapping
	public Result updateBoard(@ModelAttribute Board board) {
		Result result = boardService.updateBoard(board);
		return result;
	}
	
	@DeleteMapping
	public Result deleteBoard(@RequestParam int boardno) {
		Result result = boardService.deleteBoard(boardno);
		return result;
	}
	
}
```

​	

### ServiceResult.java

```java
package com.shane.enumpkg;

public enum ServiceResult {
	OK, FAIL, NOTEXIST, HASCHILD
}
```

​	

### Board.java

```java
package com.shane.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="board")
@Getter
@Setter
@ToString
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardno;
	
	private String title;
	private String content;
	private String writer;
	
}
```

​	

### ErrorResponse.java

```java
package com.shane.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ErrorResponse {
	private Integer code;
	private String message;
	
	public ErrorResponse(String message) {
		this.message = message;
	}
	
}
```

​	

### Result.java

```java
package com.shane.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Result {
	private ErrorResponse error;
	private Object payload;
}
```

​	

### BoardRepository.java

```java
package com.shane.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shane.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
	public List<Board> findAllByOrderByBoardnoDesc();
	
}
```

​	

### BoardService.java

```java
package com.shane.service;

import com.shane.model.Board;
import com.shane.model.Result;

public interface BoardService {
	public Result createBoard(Board board);
	public Result retrieveBoardList();
	public Result retrieveBoard(int boardno);
	public Result updateBoard(Board board);
	public Result deleteBoard(int boardno);
}
```

​	

### BoardServiceImpl.java

```java
package com.shane.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shane.enumpkg.ServiceResult;
import com.shane.model.Board;
import com.shane.model.ErrorResponse;
import com.shane.model.Result;
import com.shane.repositories.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(BoardServiceImpl.class);

	@Autowired
	BoardRepository repository;
	
	public Result updateBoard(Board board) {
		Optional<Board> search = repository.findById(board.getBoardno());
		Result result = new Result();
		if(search.isPresent()) {
			board = repository.save(board);
			result.setPayload(board);
		}else {
			result.setError(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
		}
		return result;
	}
	
	public Result deleteBoard(int boardno) {
		Result result = new Result();
		boolean isPresent = repository.findById(boardno).isPresent();
		if(!isPresent) {
			result.setError(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
		}else {
			repository.deleteById(boardno);
		}
		return result;
	}

	@Override
	public Result createBoard(Board board) {
		board = repository.save(board);
		Result result = new Result();
		result.setPayload(board);
		return result;
	}

	@Override
	public Result retrieveBoardList() {
		List<Board> list = repository.findAllByOrderByBoardnoDesc();
		Result result = new Result();
		result.setPayload(list);
		return result;
	}

	@Override
	public Result retrieveBoard(int boardno) {
		Optional<Board> optionalBoard = repository.findById(boardno);
		Result result = new Result();
		if(optionalBoard.isPresent()) {
			result.setPayload(optionalBoard.get());
		}else {
			result.setError(new ErrorResponse(ServiceResult.NOTEXIST.toString()));
		}
		return result;
	}

}
```

​	

이제 위의 API들을 가지고 게시판을 간단하게 작성 해 보도록 하겠습니다.

이전 포스팅에서 학습했던 반복문과 조건문, 그리고 Vue의 속성들을 이용해 간단하게 게시판 조회를 해 보도록 하겠습니다.

​	

### vueboard.html

```html
<html>

<head>
	<meta charset="UTF-8">
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<title>title</title>
</head>

<body>
	<h2>Vue Board</h2>
	<table id="board">
		<thead>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>내용</th>
		</thead>
		<tr v-if="!boardlist">
			<td>게시글이 없습니다.</td>
		</tr>
		<tr v-for="board in boardlist">
			<td v-text="board.boardno"></td>
			<td v-text="board.title"></td>
			<td v-text="board.writer"></td>
			<td v-text="board.content"></td>
		</tr>
	</table>
<script>
	let board = new Vue({
		el: '#board',
		data: {
			boardlist : []
		},
		mounted : function(){
			this.load()
		} ,
		methods:{
			load : function(){
				let that = this;
				$.ajax({
					url : '/restapi/board'
				}).done(function(data){
					that.boardlist = data.payload;
				})
			}
		}
	})
</script>
</body>
</html>
```

정말 특별한 내용 없이, vue.js와 jquery를 cdn으로 받아왔습니다. Vue 인스턴스로 만들어 el 속성으로 board 라는 id를 가진 테이블에 종속 시켰습니다. data에 boardlist 배열을 선언 해 두고, load 라는 함수에서 ajax를 통해 해당 url에 get 요청을 보냅니다. 그 응답을 받아 그 payload(게시글 목록)을 해당 Vue 인스턴스의 boardlist 변수에 담습니다. 

mount는 해당 뷰 인스턴스가 준비 되었을 때 실행되는데, load 메서드가 실행 되도록 해 두었습니다.

이제 페이지를 띄워 보면,

![image-20210817224343320](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vueboard.assets/image-20210817224343320.png)

게시글 목록들을 잘 불러옵니다 !

​	

### 이번에는 글 작성을 해보았습니다.

![image-20210817232334253](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vueboard.assets/image-20210817232334253.png)

이렇게 input 태그에 내용을 작성 하고 "등록" 을 누르면

​	

![image-20210817232429467](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vueboard.assets/image-20210817232429467.png)

깜빡임 없이 간단하게 새 글이 맨 위에 등록 됩니다. 중간의 텅 빈 화면에서 눈치 채셨겠지만 저도 꽤나 삽질을 했습니다.



새 글 작성하는 간단한 form 입니다.

```html
	<div id="regform">
		<form v-on:submit.prevent="create">
			<span>제목</span><input type="text" name="title" v-model="title">
			<span>작성자</span><input type="text" name="writer" v-model="writer">
			<span>내용</span><input type="text" name="content" v-model="content">
			<button>등록</button>
		</form>
	</div>
```



v-on:submit.prevent="create"는 해당 form은 submit 해도 preventDefault()를 하며, create라는 method를 호출 하도록 한 것입니다. v-model 을 이용하면 하나하나의 input 태그에 id 값을 따로 주지 않더라도 Vue 인스턴스의 data로 사용 할 수 있습니다.



### 해당하는 Vue 코드를 확인해보겠습니다.

```javascript
let regform = new Vue({
		el:'#regform',
		data : {
			title:''
			,writer:''
			,content:''
		},
		methods:{
			create: function(){
				$.ajax({
					url : '/restapi/board'
					,data : {
						title : regform.title
						,writer : regform.writer
						,content : regform.content
					}
					,method : 'POST'
				}).done(function(data){
					board.load()
				})
			}
		}
	})
```

data에 필요한 변수 명들을 선언 해 주어야 합니다. create 함수 호출시에는 ajax로 비동기 POST 요청을 하고, 성공시에 아까 만들어둔 board의 load 메서드를 호출 해서 간단하게 해당하는 부분만 새로 받아와서 보여줍니다. 기존의 비동기 요청에서는 하나하나 렌더링을 해 줘야 해서 불편했는데 정말 편하게 할 수 있습니다.



### 이번에는 마지막으로 삭제 하는 기능을 추가해보겠습니다.

![image-20210817235044469](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/vueboard.assets/image-20210817235044469.png)



'삭제' 버튼을 클릭하면 간단하게 바로바로 삭제 되도록 구현 했습니다. 위에서 삽질하면서 잔뜩 생겼던 빈 내용들이 싹 사라져서 기분이 좋네요.



```html
<td><button v-on:click="remove(board.boardno)">삭제</button></td>
```

삭제 버튼을 테이블에 간단하게 추가 해 주었습니다. 삭제 버튼을 클릭하면 remove 메서드를 호출 하며 해당 게시판의 넘버를 파라미터로 넘기도록 했습니다.

​	

```javascript
remove : function(boardno){
				$.ajax({
					url : '/restapi/board'
					,method : 'post'
					,data : {
						boardno : boardno
						,_method : 'delete'
					}
				}).done(function(data){
					board.load();
				})
			}
```

이번엔 해당 remove 메서드 입니다.

RESTAPI 에 delete 요청으로 boardno를 데이터로 넘기는데 , delete 요청을 ajax 에서 보내기 위해서 post 요청으로 보내며 _method로 'delete'를 보냈습니다.



### vueboard.html 

```html
<html>

<head>
	<meta charset="UTF-8">
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<title>title</title>
</head>

<body>
	<h2>Vue.js Board</h2>
	<table id="board">
		<thead>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>내용</th>
			<th>삭제</th>
		</thead>
		<tr v-if="!boardlist">
			<td>게시글이 없습니다.</td>
		</tr>
		<tr v-for="board in boardlist">
			<td v-text="board.boardno"></td>
			<td v-text="board.title"></td>
			<td v-text="board.writer"></td>
			<td v-text="board.content"></td>
			<td><button v-on:click="remove(board.boardno)">삭제</button></td>
		</tr>
	</table>
	<br/>
	<div id="regform">
		<form v-on:submit.prevent="create">
			<span>제목</span><input type="text" name="title" v-model="title">
			<span>작성자</span><input type="text" name="writer" v-model="writer">
			<span>내용</span><input type="text" name="content" v-model="content">
			<button>등록</button>
		</form>
	</div>
<script>
	let regform = new Vue({
		el:'#regform',
		data : {
			title:''
			,writer:''
			,content:''
		},
		methods:{
			create: function(){
				$.ajax({
					url : '/restapi/board'
					,data : {
						title : regform.title
						,writer : regform.writer
						,content : regform.content
					}
					,method : 'POST'
				}).done(function(data){
					board.load();
				})
			}
		}
	})
	
	let board = new Vue({
		el: '#board',
		data: {
			boardlist : []
		},
		mounted : function(){
			this.load()
		} ,
		methods:{
			load : function(){
				$.ajax({
					url : '/restapi/board'
				}).done(function(data){
					board.boardlist = data.payload;
				})
			},
			remove : function(boardno){
				$.ajax({
					url : '/restapi/board'
					,method : 'post'
					,data : {
						boardno : boardno
						,_method : 'delete'
					}
				}).done(function(data){
					board.load();
				})
			}
		}
	})
</script>
</body>
</html>
```



그렇게 해서 코드가 제법 길어졌습니다.

C/R/U/D 중에 U 를 제외한 내용들을 간단하게 Vue.js 를 통해서 시도 해 보았습니다.

워낙 간단하기 때문에 남은 "수정" 항목은 위에서 연습한 내용을 바탕으로 한번 씩 스스로 해보시면 되겠습니다. 



수고하셨습니다.

해당 프로젝트의 코드는 https://github.com/Shane-Park/markdownBlog/tree/master/projects/vuejs 에 모두 있습니다.
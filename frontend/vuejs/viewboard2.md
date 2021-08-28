# Vue.js 사용 하기 4 ) PostgreSQL로 간단 게시판 작성 해보기 - 조회 및 수정 기능 추가

​	

> [Vue.js 사용 하기 3) postgreSQL 게시판에서 Modal 이용하기](https://shanepark.tistory.com/203?category=1186787) 에 이어지는 글 입니다.

​	

### 마지막으로 Vue를 활용해 게시판을 조금 더 "사용할 수 있음" 에 가깝게 수정 해 보았습니다.

![image-20210828223218418](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/viewboard2.assets/image-20210828223218418.png)

​		

### 일단 개별 글의 "조회" 기능을 추가 하였습니다.

> 이에 따라 게시판 목록 에서는 삭제 버튼이 사라졌습니다. 

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/viewboard2.assets/image-20210828223545594.png)

각 글의 제목을 누르면 개별 글을 조회 할 수 있도록 하였습니다.

​	

### 수정 버튼을 누르면 게시글 수정이 가능합니다.

<img src="https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/viewboard2.assets/image-20210828223556605.png" alt="image-20210828223556605" style="zoom:50%;" />

게시글 수정은 게시글 작성 모달을 그대로 재활용하며 상황에 따라 다르게 작동하도록 하여 코드의 양을 줄이고 변경에 용이하도록 하였습니다.

​	

<img src="https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/vuejs/viewboard2.assets/image-20210828223646654.png" alt="image-20210828223646654" style="zoom:50%;" />

게시글 작성을 보면 아시겠지만 위의 "수정"과 거의 동일합니다.

​		

서버쪽은 전혀 변한게 없고 vueboard.html 파일만 변경 하였습니다. 전체코드를 먼저 한번에 올리고 파트별로 코드를 차근차근 확인해보겠습니다.

​	

## 전체코드

```html
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<!-- JavaScript Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
	<link rel="stylesheet" type="text/css" href="/style.css" />
	<title>title</title>
	 <script type="text/x-template" id="modal-template">
      <transition name="modal">
        <div class="modal-mask">
          <div class="modal-wrapper">
            <div class="modal-container">
              <div class="modal-header">
                <slot name="header">
                  default header
                </slot>
              </div>
              <div class="modal-body">
                <slot name="body">
                  default body
                </slot>
              </div>
              <div class="modal-footer">
                <slot name="footer">
                  <button class="modal-default-button" @click="$emit('close')">
                    close
                  </button>
                </slot>
              </div>
            </div>
          </div>
        </div>
      </transition>
    </script>
</head>

<body>
	<div id="board">
		<h2>Vue.js Board</h2>
		<table class="table">
			<thead>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>내용</th>
			</thead>
			<tbody>
				<tr v-if="!boardList">
					<td>게시글이 없습니다.</td>
				</tr>
				<tr v-for="b in boardList">
					<td v-text="b.boardNo"></td>
					<td class="title" v-on:click="retrieve(b.boardNo)" v-text="b.title ? b.title : 'untitled'"></td>
					<td v-text="b.writer"></td>
					<td v-text="b.content"></td>
				</tr>
			</tbody>
		</table>
		<button class="btn btn-success" id="show-modal" @click="create()">작성</button>
		<modal v-if="showModal" @close="showModal = false">
			<h3 slot="header" v-text="'게시글 '+menu[status]"></h3>
			<div slot="body">
				<input hidden v-model="board.boardNo"></input>
				제목 <input :disabled="status=='view'" v-model="board.title"></input><br/>
				작성자 <input :disabled="status=='view'" v-model="board.writer"></input><br/>
				내용 <input :disabled="status=='view'" v-model="board.content"></input>
				<button class="btn btn-info" v-if="status=='view'" v-on:click.prevent="status='edit'" >수정</button>
				<button class="btn btn-success" v-else v-on:click.prevent="save" :disabled="!board.title||!board.writer||!board.content">저장</button>
				<button class="btn btn-danger" v-if="status!='new'" v-on:click.prevent="remove(board.boardNo)">삭제</button>
			</div>
		</modal>
	</div>
<script>
	Vue.component("modal", {
	    template: "#modal-template"
	  });

	let board = new Vue({
		el: '#board',
		data: {
			boardList : []
			,menu : {
				new : '작성'
				,edit : '수정'
				,view : '조회'
			}
			,board : {
				boardNo : ''
				,title : ''
				,writer : ''
				,content : ''
			}
			,showModal : false
			,status : ''
		},
		mounted : function(){
			this.load()
		} ,
		methods:{
			load : function(){
				$.ajax({
					url : '/restapi/board'
				}).done(function(data){
					board.boardList = data.payload;
				})
			},
			remove : function(boardNo){
				this.showModal=false;
				$.ajax({
					url : '/restapi/board'
					,method : 'post'
					,data : {
						boardNo : boardNo
						,_method : 'delete'
					}
				}).done(function(data){
					board.load();
				})
			},
			retrieve : function(boardNo){
				this.showModal=true;
				this.status='view';
				let that = this;
				$.ajax({
					url : '/restapi/board/'+boardNo
				}).done(function(data){
					that.board = data.payload;
				})
			},
			create : function(){
				this.board = {};
				this.showModal=true;
				this.status='new';
			},
			save : function(){
				let that = this;
				$.ajax({
					url : '/restapi/board'
					,data : {
						boardNo : that.status=='new' ? '' : that.board.boardNo
						,title : that.board.title
						,writer : that.board.writer
						,content : that.board.content
					}
					,method : 'POST'
				}).done(function(data){
					that.showModal = false;
					board.load();
				})
			},
		}
	})
</script>
</body>
</html>
```

​		

### Modal 템플릿 부분

```html
	 <script type="text/x-template" id="modal-template">
      <transition name="modal">
        <div class="modal-mask">
          <div class="modal-wrapper">
            <div class="modal-container">
              <div class="modal-header">
                <slot name="header">
                  default header
                </slot>
              </div>
              <div class="modal-body">
                <slot name="body">
                  default body
                </slot>
              </div>
              <div class="modal-footer">
                <slot name="footer">
                  <button class="modal-default-button" @click="$emit('close')">
                    close
                  </button>
                </slot>
              </div>
            </div>
          </div>
        </div>
      </transition>
    </script>
```

> 아래에서 모달에 사용할 템플릿을 미리 정의하는 부분입니다. 크게 신경 쓰지 않아도 됩니다.

​	

## HTML body

```html
<div id="board">
		<h2>Vue.js Board</h2>
		<table class="table">
			<thead>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>내용</th>
			</thead>
			<tbody>
				<tr v-if="!boardList">
					<td>게시글이 없습니다.</td>
				</tr>
				<tr v-for="b in boardList">
					<td v-text="b.boardNo"></td>
					<td class="title" v-on:click="retrieve(b.boardNo)" v-text="b.title ? b.title : 'untitled'"></td>
					<td v-text="b.writer"></td>
					<td v-text="b.content"></td>
				</tr>
			</tbody>
		</table>
		<button class="btn btn-success" id="show-modal" @click="create()">작성</button>
		<modal v-if="showModal" @close="showModal = false">
			<h3 slot="header" v-text="'게시글 '+menu[status]"></h3>
			<div slot="body">
				<input hidden v-model="board.boardNo"></input>
				제목 <input :disabled="status=='view'" v-model="board.title"></input><br/>
				작성자 <input :disabled="status=='view'" v-model="board.writer"></input><br/>
				내용 <input :disabled="status=='view'" v-model="board.content"></input>
				<button class="btn btn-info" v-if="status=='view'" v-on:click.prevent="status='edit'" >수정</button>
				<button class="btn btn-success" v-else v-on:click.prevent="save" :disabled="!board.title||!board.writer||!board.content">저장</button>
				<button class="btn btn-danger" v-if="status!='new'" v-on:click.prevent="remove(board.boardNo)">삭제</button>
			</div>
		</modal>
	</div>
```

언뜻 보면 복잡해 보일 수도 있는데 막상 30 줄 밖에 안되고 자세히 보면 아주 간단합니다.

크게 테이블 부분과 modal 부분 이렇게 두 부분으로 나뉘는데요.

​	

일단 사전 정보가 필요하기 때문에  Vue 코드의 data 부분을 살짝 먼저 보면

```javascript
		data: {
			boardList : []
			,menu : {
				new : '작성'
				,edit : '수정'
				,view : '조회'
			}
			,board : {
				boardNo : ''
				,title : ''
				,writer : ''
				,content : ''
			}
			,showModal : false
			,status : ''
		},
```

- boardList를 선언해서 게시글 목록을 받아 올 배열을 미리 선언 해 둡니다. 

- menu 객체는 status에 따라 (게시글 작성중인지, 조회중인지 수정중인지) 모달창에 상태를 한글로 표현 하기 위한 변수입니다.

- board는 조회/작성/수정에서 모두 필요해 데이터 바인딩을 위한 객체입니다.

- showModal은 모달을 보여줄지 말지를 컨트롤 하기 위한 변수이며

- status 는 현 상태가 작성중인지 조회중인지 수정중인지에 대한 정보를 저장해 화면을 동적으로 렌더링 하기 위한 변수 입니다.

  ​	

다시 body로 돌아와서.

```html
				<tr v-for="b in boardList">
					<td v-text="b.boardNo"></td>
					<td class="title" v-on:click="retrieve(b.boardNo)" v-text="b.title ? b.title : 'untitled'"></td>
					<td v-text="b.writer"></td>
					<td v-text="b.content"></td>
				</tr>
```

이전 글과 크게 달라진 점은 없지만,  일단  board 객체가 추가되며 이름이 겹쳐 iterator 순회할 때 이름을 board 에서 b 로 변경 하였습니다. 또한 제목이 없을 경우 아무것도 없으면 클릭하기가 불편해서 untitle 를 보여주도록 했습니다.

​	

### Modal

```html
		<modal v-if="showModal" @close="showModal = false">
			<h3 slot="header" v-text="'게시글 '+menu[status]"></h3>
			<div slot="body">
				<input hidden v-model="board.boardNo"></input>
				제목 <input :disabled="status=='view'" v-model="board.title"></input><br/>
				작성자 <input :disabled="status=='view'" v-model="board.writer"></input><br/>
				내용 <input :disabled="status=='view'" v-model="board.content"></input>
				<button class="btn btn-info" v-if="status=='view'" v-on:click.prevent="status='edit'" >수정</button>
				<button class="btn btn-success" v-else v-on:click.prevent="save" :disabled="!board.title||!board.writer||!board.content">저장</button>
				<button class="btn btn-danger" v-if="status!='new'" v-on:click.prevent="remove(board.boardNo)">삭제</button>
			</div>
		</modal>
```

​	

모달 부분은 게시글 작성/조회/수정 시 모두 한 화면을 돌려가며 사용 하기 때문에 조건문이 꽤 걸렸습니다.

제일 위에 header 에서 h3의 v-text를 **"'게시글 '+menu[status]"** 로 걸었기 때문에, 

```javascript
			,menu : {
				new : '작성'
				,edit : '수정'
				,view : '조회'
			}
```

위의 선언에 따라, new 일 경우 "게시글 작성", edit의 경우 "게시글 수정", view의 경우 "게시글 조회" 처럼 간단하게 상황에 따라 다른 텍스트를 보여 주도록 했습니다.

​		

또한 조회의 경우에도 input 태그를 사용하다 보니 **:disabled="status=='view'"** 를 통해 조회 할 때는 텍스트를 수정 하지 못하도록 해 두었습니다.

​	

```javascript
<button class="btn btn-success" v-else v-on:click.prevent="save" :disabled="!board.title||!board.writer||!board.content">저장</button>
```

저장 버튼은 위와 같이 해 두었기 때문에 "게시글 조회"가 아닐 경우에만 보여지며, 또한 제목, 작성자, 내용 중 어느 하나라도 입력하지 않으면 버튼이 비활성화 되도록 해 두었습니다.

​	

## script

```javascript
<script>
	Vue.component("modal", {
	    template: "#modal-template"
	  });

	let board = new Vue({
		el: '#board',
		data: {
			boardList : []
			,menu : {
				new : '작성'
				,edit : '수정'
				,view : '조회'
			}
			,board : {
				boardNo : ''
				,title : ''
				,writer : ''
				,content : ''
			}
			,showModal : false
			,status : ''
		},
		mounted : function(){
			this.load()
		} ,
		methods:{
			load : function(){
				$.ajax({
					url : '/restapi/board'
				}).done(function(data){
					board.boardList = data.payload;
				})
			},
			remove : function(boardNo){
				this.showModal=false;
				$.ajax({
					url : '/restapi/board'
					,method : 'post'
					,data : {
						boardNo : boardNo
						,_method : 'delete'
					}
				}).done(function(data){
					board.load();
				})
			},
			retrieve : function(boardNo){
				this.showModal=true;
				this.status='view';
				let that = this;
				$.ajax({
					url : '/restapi/board/'+boardNo
				}).done(function(data){
					that.board = data.payload;
				})
			},
			create : function(){
				this.board = {};
				this.showModal=true;
				this.status='new';
			},
			save : function(){
				let that = this;
				$.ajax({
					url : '/restapi/board'
					,data : {
						boardNo : that.status=='new' ? '' : that.board.boardNo
						,title : that.board.title
						,writer : that.board.writer
						,content : that.board.content
					}
					,method : 'POST'
				}).done(function(data){
					that.showModal = false;
					board.load();
				})
			},
		}
	})
</script>
```

마지막으로 Vue.js 코드 입니다.

약간은 복잡 해 보일 수 있는데요, JPA를 사용하다 보니 save function 에서 boardNo를 비워 보낼지, 아니면 채워 보낼지만 간단하게 변경 하면 상황에 따라 수정,삭제가 되기 때문에 어렵지 않게 함수의 재활용이 가능 했습니다.

​	

대부분은 이전 글에 있었으니 간단하게 조회 함수만 짚고 넘어가면..

```javascript
			retrieve : function(boardNo){
				this.showModal=true;
				this.status='view';
				let that = this;
				$.ajax({
					url : '/restapi/board/'+boardNo
				}).done(function(data){
					that.board = data.payload;
				})
			},
```

게시글 조회시에는 showModal 을 true로 해서 모달을 보여 주며 status 를 view 로 변경 합니다.

후에 restapi 에서 특정 게시글을 조회하는 요청을 보내 간단하게 Vue의 board 객체만 변경 해 주면, 이미 데이터가 바인딩 되어 있기 때문에 화면을 간단하게 렌더링 해서 바로 보여줍니다.

​		

이렇게 시간이 조금 날 때 마다 게시판을 수정 해 완성 해 보았는데요.

특별한 기능이 있는 게시판은 아니지만 Vue.js 를 이용해 게시판을 만들어 보니 확실히 어렵지 않게 싱글페이지 게시판을 만들 수 있었고 그 편리함 때문에 즐겁게 코딩 할 수 있었습니다.

​	

Vue.js 의 기본적인 사용 방법에 익숙 해 지면 조금 더 복잡한 기능들을 하나 둘 씩 더 사용 해 보도록 하겠습니다.

이것으로 Vue.js로 게시판 만들기 시리즈를 마치겠습니다. 수고하셨습니다.
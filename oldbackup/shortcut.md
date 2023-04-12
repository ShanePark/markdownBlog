# Javascript) 웹사이트 혹은 웹 어플리케이션에 단축키 추가하기

최종프로젝트를 진행하며, 막바지에 추가했던 '단축키' 기능입니다.

생각보다 어렵지는 않은데 발표할때 반응은 꽤나 좋았던 걸로 기억합니다.

실제로 싱글페이지로 웹 어플리케이션을 설계 하면서, 그 난이도 상승에 비해 얻은 설계상 이점이 크지 않다고 느꼈지만

반대로 싱글페이지가 아니었으면 추가하지 못했을 기능 중 하나 입니다. ( 뭐 억지로 할라면 했겠지만 싱글페이지가 아니라면 그 의미가 떨어지는 기능입니다)



<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/oldbackup/shortcut.assets/img-20230412213645205.webp width=590 height=782 alt=1>



 

 

1. 아이디어와 디자인

단축키 아이디어는 Google Docs와 Figma 에서 얻었습니다.  요즘 왠만한 어플리케이션들은 정말 전부 웹에서 자유자재로 실행할 수 있게끔 세상이 흘러가고 있다고 생각되는데요, 우연히 단축키 기능을 찾게 되었는데 이게 정말 웹 어플리케이션을 사용하고 있다는 사실을 잊게 할 만큼 편의성을 높여 주었습니다. 그 후에 왠만한 웹기반 어플리케이션들에서 ctrl + '/' 혹은 Command+'/' 키를 눌러 보았는데, 신기하게도 지원하는 앱이 상당히 많았습니다.



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/oldbackup/shortcut.assets/img-20230412213645179.webp)참고했다고 생각했는데, 다시 띄워놓고 보니 거의 그대로 배꼈네요



그 디자인은 Spotify App 을 최대한 참고 했습니다. 최종프로젝트 진행하는 내내 하루 10시간 이상 헤드셋과 에어팟을 번갈아가며 끼고 계속 음악을 들었는데요, 스포티파이는 정말 세계 1등 답게 괜찮은 음악 스트리밍 서비스 입니다.

눈으로 보고 참고할 디자인이 있으니, css는 제가 잘 못하는 분야긴 해도 모달 만들어서 font-size 걸고, 모서리 둥글게 하고 opacity 거는 것 정도는 어렵지 않게 할 수 있었습니다. Bootstrap modal을 거의 그대로 사용 했습니다.

xml

```xml
<!-- 단축키 모달 -->
<div class="modal fade" id="shortcutModal" tabindex="-1" role="dialog" aria-labelledby="shortcutModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
      	<div class="shorthead">
	        <span class="modal-title" id="shortcutModalLabel">Keyboard Shortcuts</span>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
	        </button>
      	</div>
        <p>Press <span class="shortcut">Ctrl</span><span class="shortcut">/</span> to toggle this modal.</p>
      </div>
      <div class="modal-body">
	      <h3>Menu</h3>
	      <div>
	      	<span>Code</span>
	      	<button>1</button>
	      	<button>Shift</button>
	      	<button>Ctrl</button>
	      </div>
	      <div>
	      	<span>Milestone</span>
	      	<button>M</button>
	      	<button>Ctrl</button>
	      </div>
	      <div>
	      	<span>Issue</span>
	      	<button>U</button>
	      	<button>Ctrl</button>
	      </div>
	      <div>
	      	<span>Gantt</span>
	      	<button>G</button>
	      	<button>Ctrl</button>
	      </div>
	      <div>
	      	<span>Calendar</span>
	      	<button>L</button>
	      	<button>Ctrl</button>
	      </div>
	      <div>
	      	<span>Kanban</span>
	      	<button>K</button>
	      	<button>Ctrl</button>
	      </div>
	      <div>
	      	<span>News</span>
	      	<button>N</button>
	      	<button>Shift</button>
	      	<button>Ctrl</button>
	      </div>
	      <div>
	      	<span>Wiki</span>
	      	<button>P</button>
	      	<button>Ctrl</button>
	      </div>
	      <div>
	      	<span>Analytics</span>
	      	<button>A</button>
	      	<button>Shift</button>
	      	<button>Ctrl</button>
	      </div>
<!-- 	      <h3>Code</h3> -->
<!-- 	      <div> -->
<!-- 	      	<span>Controller</span> -->
<!-- 	      	<button>5</button> -->
<!-- 	      	<button>Shift</button> -->
<!-- 	      	<button>Ctrl</button> -->
<!-- 	      </div> -->
<!-- 	      <div> -->
<!-- 	      	<span>Business Logic Layer</span> -->
<!-- 	      	<button>6</button> -->
<!-- 	      	<button>Shift</button> -->
<!-- 	      	<button>Ctrl</button> -->
<!-- 	      </div> -->
<!-- 	      <div> -->
<!-- 	      	<span>Persistene Layer</span> -->
<!-- 	      	<button>7</button> -->
<!-- 	      	<button>Shift</button> -->
<!-- 	      	<button>Ctrl</button> -->
<!-- 	      </div> -->
      </div>
    </div>
  </div>
</div>
```

 

 

2. 구현

사실 어플리케이션에 단축키 기능을 넣겠다고 한건 수행계획서 작성 때부터 생각했던 건데요, 그때는 일단 이벤트 리스너를 등록 해놓고 구현을 하면 어렵지 않게 할 수 있겠다 생각 했었는데 그래도 막상 구현할 때 되면 더 효율적인 방법을 생각해 낼 수 있지 않을까 싶기도 했었습니다.

현실은 발표 3~4일을 앞두고 급하게 넣다 보니 그때 생각했던 리스너 이벤트 바인딩을 이용해 그대로 구현하게 되었습니다. 사실 지금 생각 해도 딱히 더 좋은 아이디어가 떠오르지는 않네요. 아마 비슷하게 구현하지 않을까 싶습니다. 단축키들을 위한 라이브러리들도 존재하는 걸로 알고 있습니다.

 

xml

```xml
// keyboard 단축키 바인딩.
	$(document).bind('keypress', function(e) {
		// 단축키 목록 보는 단축키 : ctrl + '/'
		let ctrl = e.ctrlKey;
		let shift = e.shiftKey;
		let key = e.which;
		if(ctrl && shift){
			switch(key){
				case 1 : movePageHistory('analytics'); break;
				case 14 : movePageHistory('news'); break;
				case 49 : movePageHistory('code'); break;
				case 51 : movePageHistory('milestone'); break;
				case 52 : movePageHistory('issue'); break;
				case 53 : movePageHistory('ganttchart'); break;
				case 30 : movePageHistory('calendar'); break;
				case 55 : movePageHistory('kanbanboard'); break;
				case 56 : movePageHistory('news'); break;
				case 57 : movePageHistory('wiki'); break;
				case 48 : movePageHistory('analytics'); break;
			}
			
		}else if(ctrl) {
			switch(key){
				case 7 : movePageHistory('ganttchart'); break;
				case 11 : movePageHistory('kanbanboard'); break;
				case 12 : movePageHistory('calendar'); break;
				case 13 : movePageHistory('milestone'); break;
				case 16 : movePageHistory('wiki'); break;
				case 21 : movePageHistory('issue'); break;
				case 47 :
					$('#shortcutModal').modal('toggle');
					break;
			}
		}
	});
```

 

코드는 이렇게 구현했습니다. e.ctrlKey 와 e.shiftKey로 각각의 키가 눌려있는 상태인지 체크 할 수 있구요. 각각의 key에 해당하는 번호는 console.log(e.which)로 이것저것 찍어보며 찾아 냈습니다. 같은 키의 경우에도 해당 키만 눌렀는지, ctrl키가 눌려 있는지 shift키가 눌려있는지 모든 경우에 따라 각각 다르니 찍어보는게 좋습니다.  저는 jQuery로 구현했지만, 조금만 수정하면 어렵지 않게 Vanilla JS로 바꾸실 수 있습니다.

 

3. 문제

단축키를 위해 사용할 수 있는 키조합이 상당히 제한됩니다. 기본적으로 브라우저 상에서 작동하다 보니 브라우저에서 사용하는 단축키들을 바인딩 해 버리면, 제가 바인딩 시키는 단축키가 우선이 되기는 하는데요. 그러면 유저 입장에서 굉장히 불편합니다. 

실제 배포되어 있는 상당수의 사이트들에서도 발생하고 있는 문제인데요, 예를 들어 제가 자주 사용하는 커맨드키 + 숫자 ( 탭간 이동) 을 해당 웹사이트에서 바인딩 해 두었을 경우에는 저의 의도와는 관계 없이 해당 이벤트가 작동되게 됩니다. 그래서 최대한 브라우저에서 사용하는 단축키를 피해서 하려다 보니 사용할 수 있는 조합이 정말 소수 였습니다. 저는 mac을 사용하니 크게 상관 없지만 ( mac은 대부분의 키 바인딩이 command 키로 되기 떄문에 ctrl 키를 좀 더 자유롭게 사용 할 수 있습니다) 윈도우를 사용하는 유저가 절대 다수기 때문에 많이 신경을 써야 했습니다.

그래서 최대한 Ctrl 키로 맵핑을 하되, 한계가 있는 경우에는 Ctrl + Shift  키를 함께 입력하도록 해서 해결 했습니다.

사실 제가 고른 단축키임에도 불구하고 외우기가 어렵다보니 Ctrl + Shift + 1 ~ 7 ( Ctrl + shift + 2 는 왜인지 바인딩이 안되었습니다) 로 단축키를 추가로 바인딩 해 사용 했습니다. 그래서 발표에서는 마치 단축키를 다 외워서 빠르게 빠르게 이동한 것 처럼 보였겠지만 사실은 단축키가 추가로 더 있었다는 사실..

 

이상입니다. 위의 내용을 바탕으로 단축키를 보여줄 modal을 만들고, 그 단축키 목록 modal을 보여줄 키 맵핑을 하고,

keypress 이벤트에 각각의 단축키에 조합에 맞는 이벤트를 분기시켜 준다면 어렵지 않게 단축키 기능을 만드실 수 있습니다.

혹시 궁금한게 있으면 댓글 남겨주시면 금방 답변 해드리겠습니다.
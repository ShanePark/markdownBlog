# 티스토리 이미지 클릭시 원본 사이즈 모달 구현하기	

티스토리는 기본적으로 lightbox를 지원합니다.	

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/tistory-image-modal.assets/image-20210826233718764.webp width=400 height=400 alt=1>

> 그런데 이게 지원하는 정도가 아니라 아에 해당 css와 js 파일을 뺄 수 없을 정도로 못 박아 두었습니다. 뭐 사용하기에만 편하면 된다 생각하는데, 문제는 제가 티스토리를 순수 markdown으로 작성하고, 첨부하는 이미지들도 Github에 올리고 링크를 연결하는 방식으로 올리다 보니, Tistory의 기본 lightbox가 작동하지 않았습니다.

그래서 저는 티스토리 블로그에서 이미지를 클릭 할 때 이미지를 크게 볼 수 있게끔 스스로 구현해야 했는데, 쉽게 될 줄 알았는데 꽤나 헤맸습니다. 처음에는 어차피 강제적으로 들어 가 있으니 lightbox를 사용 해 보려고 했는데 생각처럼 잘 되지 않았습니다. 그 후에는 클릭시 에 정확히 이미지 크기의 새 창을 띄우도록 해 보았는데, 클릭시 닫히지도 않고 뭔가 깔끔하지 않았습니다. 새창이라 css를 따로 입힐 수도 없었습니다.

​	

Modal을 그냥 제가 만들어야 겠다 라고 생각했습니다.

​		

이미 아시는 분들은 알겠지만, 티스토리의 "스킨 편집" 기능을 이용하면 됩니다.

​	

스킨편집에 들어가서

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/tistory-image-modal.assets/image-20210826234322609.webp width=300 height=300 alt=1>	

​	

html 편집을 클릭하면 됩니다.

![image-20210826234347019](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/tistory-image-modal.assets/image-20210826234347019.webp)

​		

여기에서 HTML / CSS 등을 편집 할 수 있습니다.

![image-20210826234412845](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/tistory-image-modal.assets/image-20210826234412845.webp)

​	

### html

```html
<div class="modal">
	<div class="modalBox">
	</div>
</div>
```

특별한 것 없습니다. .modal과 그 안에 들어갈 .modalBox를 어딘가에 만들어 주었습니다.

​		

### css

```css
/* image modal */
.modal {
	display: none;
	z-index: 500;
	width: 100%;
	height: 100%;
	position: fixed;
	top: 0;
	left: 0;
	background-color: rgba(0, 0, 0, 0.8);
}
.modalBox {
	position: relative;
	text-align: center;
	top : 50%;
	left : 50%;
	position : sticky;
}
p img:hover{
	cursor: -webkit-zoom-in;
}
/* image modal end */
```

.modal  부분은

> display:none 으로 보이지 않게끔 합니다. z-index를 500을 줘서 맨 앞에 위치하게 하고, width와 height를 100%로 해서 화면을 가득 채웁니다. background-color 는 검정색 이면서, transparency(투명도)를 0.8을 줘서 굉장히 어둡게 처리했습니다. 0.5 정도로 하면 꽤 밝을 수는 있는데 제가 다크모드로 하다보니 이정도는 되어야 티가 나더라고요. dark 모드가 아닐 때에도 괜찮게 표현되는 것 같아 값을 상황에 따라 다르게 두지는 않았습니다.

.modalBox 부분은

> 이미지가 들어갈 부분입니다. 중앙으로 위치하도록 하기 위해 top, left를 50%로 두고 position을 sticky로 했습니다. 

또한 img:hover 를 zoom-in으로 설정해서 이미지에 마우스를 올릴 때 돋보기 아이콘이 나오도록 했습니다.

​	

### Javascript

```javascript
$(function(){
// 	이미지 클릭시 해당 이미지 모달
	$("p img").click(function(){
		let img = new Image();
		img.src = $(this).attr("src")
		$('.modalBox').html(img);
		$(".modal").show();
	});
// 모달 클릭할때 이미지 닫음
	$(".modal").click(function (e) {
    $(".modal").toggle();
  });
});
```

​	

마지막으로 자바스크립트 입니다. 이미지 클릭 할 경우에 해당 이미지 링크를 토대로 새로운 이미지 객체를 만들어 냅니다. 그리고 그 이미지를 modalBox에 집어 넣은 후에 modal을 띄우는 형식으로 구현 해 보았습니다.

또한 이미지를 띄운 상태에서는 보통 어디를 클릭해도 닫히는게 UX 적으로 편하다고 생각이 되어서 어디든 클릭하면 모달이 닫히도록 구현해 두었습니다.

​	

### 그렇게 완성한 모달입니다. 

> 군더더기 없이 깔끔하게 이미지만 나오도록 구현 했습니다. 개인적으로 닫기 버튼이 따로 있는 것 보다 그냥 이미지만 있는게 깔끔하고 보기 좋다고 생각되었습니다. 어디를 클릭해도 잘 닫히기만 하면 요즘 UX 추세상 헤맬것도 없다고 판단했습니다.

![gif](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/tistory-image-modal.assets/gif.webp)

​	

이상입니다.
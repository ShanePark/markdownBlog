# Javascript로 페이지 리다이렉트 하는 방법. location.href vs location.replace



javascript를 사용하며 다른 페이지로 이동 하는 방법은 몇 가지가 있습니다.

일단 추천하지는 않지만 jQuery를 사용한다면

```javascript
$(location).attr('href', 'https://shanepark.tistory.com');
```

같은 방법을 쓸 수 있겠죠?

​	

하지만 보통 가장 많이 썼던 방법은 

```javascript
window.location.href = 'https://shanepark.tistory.com';
```

였을 겁니다.



대다수의 상황에서 window.location.href 는 별 문제 없이 작동하지만, 해당 리다이렉트 방법을 사용하면 안되는 경우도 있을 수 있습니다. 아마 이 포스팅을 읽고 있는 분들도 많이들 겪어 보셨을 거라고 생각합니다.

그 상황을 한번 임의로 만들어 보겠습니다.



![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/redirect.assets/image-20210820225344230.png)

일단 바탕화면에 1.html, 2.html, 3.html 파일을 만듭니다. 1.html 에서 버튼을 클릭하면 2.html 파일로 이동하는데, 2.html 파일은 단순하게 3.html 파일로 리다이렉트 하는 역할을 하게끔 해 보겠습니다.

​	

### 1.html

![image-20210820230545572](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/redirect.assets/image-20210820230545572.png)

정말 단순하게 링크를 클릭하면 2.html 파일로 이동하는 페이지를 만들었습니다.

```html
<!DOCTYPE html>
<meta charset="UTF-8">
<html>
    <h3>첫 페이지 입니다.</h3>
    <body>
        <a href="2.html">이동 하기</a>
    </body>
</html>
```

​	

### 2.html

```html
<!DOCTYPE html>
<meta charset="UTF-8">
<html>
    2.html 파일입니다. 여기는 노출되면 안됩니다.
    <script>
        setTimeout(function(){ window.location.href="3.html" }, 100);
    </script>
</html>
```

이번 파일은 노출되지 않고 3.html 파일로 이동하는 역할만 하게끔 하려고 합니다.



### 3.html

```html
<!DOCTYPE html>
<meta charset="UTF-8">
<html>
    <body>
        최종 목적지 3.html 파일입니다.
    </body>
</html>
```



그렇게 최종 도착은 3.html 로 합니다. 그럼 1.html파일을 브라우저에서 열어보겠습니다.

​	

### 처음 페이지

![image-20210820230746895](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/redirect.assets/image-20210820230746895.png)

의도한 처음 페이지 입니다. 한번 이동하기 버튼을 클릭 해 보겠습니다.

​		

![image-20210820230813520](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/redirect.assets/image-20210820230813520.png)

리다이렉트가 정상적으로 이루어져 3.html 파일로 왔습니다. 첫 페이지에서 3.html 파일로 이동을 했는데요, 이제 뒤로가기 버튼을 한번 눌러보겠습니다.

![image-20210820230920674](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/redirect.assets/image-20210820230920674.png)

뒤로가기를 누르면...

​	

![image-20210820230932957](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/redirect.assets/image-20210820230932957.png)

노출되면 안되는 2.html 파일이 열립니다. 원래 대로라면 2.html의 스크립트가 실행되며 3.html 로 또 다시 이동하고.. 그렇게 무한 같은 행동만 반복하는 경우가 있었을 수도 있겠지만 다행히도 그런 참사는 일어나지 않았습니다. 워낙 자주 일어나던 문제라서 브라우저 차원에서 방지하지 않았나 생각됩니다.

​	

## 그럼 이런 경우의 Redirect는 어떻게 해야 할까요 ? 



### window.location.replace 를 이용하면 해결 할 수 있습니다.

위의 2.html 코드를 조금만 수정해보겠습니다.

```html
<!DOCTYPE html>
<meta charset="UTF-8">
<html>
    2.html 파일입니다. 여기는 노출되면 안됩니다.
    <script>
        setTimeout(function(){ window.location.replace('3.html')}, 100);
    </script>
</html>
```



### window.location.href 대신 replace를 썼을 뿐인데 어떻게 달라졌나 확인 을 해보겠습니다.

​	

![page](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/redirect.assets/page.gif)

이 경우에는 뒤로가기와 앞으로가기를 눌러도 2.html 파일이 노출되지 않습니다.

그래서 의도에 따라 "클릭" 등으로 이동 할때는 window.location.href 가 좋을 수 있지만 페이지를 넘겨 버리는 역할에서는 window.location.replace가 필요합니다.



사용방법의 차이에서 눈치챘을 수도 있지만, window.location.href="" 는 속성을 변경하는 것 이지만 window.location.replace() 는 함수를 호출 합니다.

​	

### 둘중 어떤걸 이용할 지 고민된다면, Browser history에 쌓을지 말지를 고민해보시면 되겠습니다.
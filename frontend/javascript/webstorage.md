# 이제는 Cookie 대신 Web Storage 가 대세

​		

## HTML Web Storage

> 참고자료 : https://developer.mozilla.org/en-US/docs/Web/API/Web_Storage_API

웹 스토리지를 이용하면 웹 어플리케이션은 데이터를 사용자의 브라우저에 내부적으로 저장 할 수 있습니다. HTML5 이전에는 어플리케이션의 데이터가 Cookie 에 저장되어야 했는데요, 그로 인해 매번의 서버에 대한 요청마다 포함되어야 했었습니다. 웹스토리지는 더 보안상 안전하며 훨씬 큰 데이터를 저장 할 수 있습니다. 심지어 웹사이트의 퍼포먼스에도 전혀 영향을 주지 않습니다.

​	

![](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/webstorage.assets/image-20210928211338759.png)

Cookie는 브라우저마다 최대 갯수와 최대 사이즈가 미묘하게 다르기는 하지만, 일단 최대 4096 bytes의 데이터를 저장 할 수 있습니다. 숫자가 커 보이지만 겨우 4kb 입니다. 의미 있는 문장을 저장하기에도 부족할 뿐 더러 직렬화 해서 뭔가의 객체나 데이터를 저장할 엄두도 내기 힘든 크기 입니다.

​		

![image-20210928211847703](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/webstorage.assets/image-20210928211847703.png)

반면에 Web Storage는 어떨까요? 무려 5MB의 크기를 제공합니다. 단순 비교로도 Cookie에 비해 1280배가 큽니다. 5MB라면 어지간한 이미지도 Base64 로 인코딩해서 문자로 저장 가능 할 정도 입니다.

​		

​	

### 그래서 Web Storage가 뭔데?

간단하게 사용자의 로컬 브라우저에 key-value 쌍의 형태로 데이터를 저장하는 일종의 데이터베이스라고 생각하면 됩니다. 대표적인 WebStorage의 Cookie와의 차별점은 

1. 절대적으로 큰 용량. 웹 스토리지 (5,242,880 Bytes) : 쿠키 (4,096 Bytes)
2. 어떤 경우에도 서버로 데이터가 전달되지 않습니다.



​	

### 호환성이 별로 아니야 ?

![image-20210928212804959](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/webstorage.assets/image-20210928212804959.png)

![image-20210928212901102](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/webstorage.assets/image-20210928212901102.png)

localStorage와 sessionStorage 모두 Internet Explorer 에서도 지원 해 주는 기능 입니다. 

우리가 알고 있는 익스플로러에서도 됩니다. 정말로요.

​		

### Incognito mode 에서는? 

>  Google Chrome 브라우저의 스크릿 모드와 같은 익명 모드에서는 괜찮을까요?

![image-20210928213226503](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/webstorage.assets/image-20210928213226503.png)

대부분의 최신 브라우저에서는 익명 브라우징 기능을 제공 하고 있습니다. Cookie건 history건 어떤 데이터도 남기지 않는데요, 같은 이유로 Web Storage를 사용할 때 근본적으로 어폐가 있습니다. 하지만 이로인해서 여러 브라우저들이 다양한 시나리오를 실험 하고 있습니다. 대부분의 브라우저들이 채택하고 있는 방법으로는 Web Storage API가 완전 잘 작동하는 것 처럼 보이게 하지만, 브라우저가 닫힐 때 데이터를 모두 지워 버립니다. 비공개 모드로 접속 했을 때 사파리의 경우에는 모든 storage를 0 bytes로 할당 해 버려서 데이터를 쓰지 못하게 해버린다네요. 결론적으로 익명 브라우징의 경우에는 브라우저마자 조금씩 접근 방법이 다르다보니 고려해야 할 상황이 제법 생기겠습니다.

​	

### 그래서 어디에 저장될까?

![image-20210928214358502](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/frontend/javascript/webstorage.assets/image-20210928214358502.png)

브라우저별로 실제 저장되는 장소는 조금씩 다릅니다. 대부분 유저의 profile 폴더에 저장되는데요. FireFox, Chrome는 C 라이브러리에 포함된 SQLite라는 관계형 데이터베이스 시스템으로 데이터를 저장하니 위에서 일종의 **데이터베이스**라고 말했던것도 완전 근거가 없진 않았습니다.

​		

### localStorage 와 sessionStorage

둘의 차이 또한 명확합니다. localStorage의 경우에는 시간제한이 없고 브라우저가 꺼져도 지워지지 않습니다. 값을 지워주려면 직접 지워줘야 하는데요, 

```javascript
localStorage.removeItem("keyName");
```

이런식으로 삭제 할 수 있습니다.

sessionStorage는 세션이 종료되면 삭제되는 저장소 입니다. 해당 sessionStorage는 일반적으로 우리가 사용하던 세션과는 다르게 브라우저 단위가 아닌 하나의 탭 단위로 저장됩니다. 세션을 사용 할 때 부담되었던게 계속해서 서버에 부하를 준다는 점 이었는데요, 쿠키를 쓰자니 보안상 안되겠고 세션을 쓰자니 서버 요청이 부담스러웠점이 다들 많으셨을텐데 그 중간의 위치에서 우리에게 새로운 선택지가 생겼습니다. 브라우저가 종료 되는 시점에서 굳이 데이터들을 남겨두지 않아도 되는 경우에도 찝찝하게 남겨두거나 혹은 일일히 삭제 해 줄 필요 없이 sessionStorage를 사용하면 됩니다. localStorage도 매력적인데 sessionStorage는 사용하기에 따라 정말 다양하게 활용 될 가능성이 있습니다.

물론 세션를 완벽하게 대체 하지는 못하겠지만, sessionStorage라는 훌륭한 선택지가 추가됨으로써 많음 부담을 덜 수 있습니다.

​		

공통된  webStorage의 장점으로는 javascript 객체를 저장 할 수 있다는 점 입니다.

```javascript
// Object 저장하기
localStorage.setItem('key', {name: 'value'});
alert(typeof localStorage.getItem('key')); // 스트링 값을 반환합니다.

```

​	

Integer도 저장 하고요

```javascript
// integer 저장하기
localStorage.setItem('key', 1);
alert(typeof localStorage.getItem('key')); // 스트링 값을 반환합니다.

```

​	

뭐니뭐니해도 JSON으로 직렬화 해서 저장하는게 넣고 꺼내기가 좋습니다.

```javascript
// JSON 형태로 저장하기
localStorage.setItem('key', JSON.stringify({name: 'value'}));
alert(JSON.parse(localStorage.getItem('key')).name); // 객체 그대로 꺼내오기

```

​	

## 그래서 어떻게 사용하나요

이미 위에서의 예제 코드들에서 눈치 채셨겠지만 사용이 정말 간단합니다.	

```javascript
// 저장
localStorage.setItem("name", "shane");

```

이렇게만 하면 저장 작업이 끝납니다.

​	

다시 꺼내올때는

```javascript
let name = localStorage.getItem("name");
```

이렇게하면 하면 저장된 String 값을 받아 올 수 있습니다.

​	

```javascript
document.getElementById("result").innerHTML = localStorage.getItem("name");
```

이런식으로 특정 엘리먼트에 데이터를 넣을 수도 있습니다.

세션스토리지는 간단하게 sessionStorage. 으로 시작하게끔 바꿔주기만 하면 됩니다.

​	

## 실제 사용 예시

​	

저는 블로그에 다크모드를 적용 하며 localStorage를 사용 했습니다. 

원래는 사용자의 다크모드 설정값을 읽어와서 다크모드를 보여줄지 아니면 라이트모드를 보여줄지를 단순하게 설정 해왔었는데요. 제가 우분투를 쓰기 시작하면서 브라우저가 리눅스의 기본 다크모드 설정은 읽어오지 못한다는 것을 알게 되었고, 그래서 다크모드를 사용자가 선택할 수 있게끔 했습니다. 

다크모드 / 라이트모드로 전환시 해당 설정에 대한 기록을 localStorage에 저장 해 두고, 제 블로그에 방문 할 때 사용자의 localStorage에서 darkMode 설정에 대한 localStorage 값을 불러 오고, 그게 없을 때에는 기본적으로 사용자의 OS에서 설정한 다크모드 설정 값을 불러오도록 구현해 두었습니다. 

​	

```javascript
let isDarkMode = localStorage.getItem("isDarkMode");
if(isDarkMode == null){
  isDarkMode = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
}else{
  isDarkMode = (isDarkMode=='true');
}			

```

위의 코드에서 보이는 것 처럼  isDarkMode 라는 저장값을 불러오고, 데이터가 없을 (null) 경우 에는 사용자의 prefers-color-scheme에서 읽어오도록 해 두었습니다.

​	

그러고 darkMode 라는 body에 dark라는 클래스를 붙이는 것으로 다크모드를 설정 했습니다.

```javascript
if(isDarkMode){
  $('body').addClass('dark');
}

```

​	

jQuery로 작성 했지만.. 토글 버튼을 클릭 했을 경우에는 localStorage.setItem에 boolean 값을 그대로 저장합니다. 물론 받아올 때는 string으로 받아오게 되지만, 굳이 "true"를 쓰지 않고 편하게 저장 할 수 있는 것도 장점입니다.

```javascript
$('#darkBtn').on('click', function(){
  isDarkMode = !isDarkMode
  if(isDarkMode){
    setDark();	
    localStorage.setItem("isDarkMode", true);
  }else{
    setNonDark();
    localStorage.setItem("isDarkMode", false);
  }
})		
```

​	

지금까지 WebStorage에 대해 알아보았습니다.

그 장점이 너무나도 명확함에도 호환성 문제도 전혀 없다보니 꼭 한번씩 사용 해 보시고, 쿠키와 세션사용의 많은 부분을 대체 해 보는게 좋겠습니다.



​	


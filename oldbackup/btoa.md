# **btoa 혹은 atob 사용해 BASE64 <-> UTF-8 인코딩 할때 한글 깨짐 해결**

## Intro

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/oldbackup/btoa.assets/img-20230412214329743.webp width=750 height=430 alt=1>



Github에서 저장소의 readme 파일을 불러와 화면에 렌더링 해 주는 기능을 구현 하고 있는 중, 한글이 깨져버리는 현상이 발생했습니다.



<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/oldbackup/btoa.assets/img-20230412214329769.webp width=750 height=290 alt=2>



위에서 보이듯, github 에서는 readme 파일을 base64 로 인코딩 해서 보내주는데요,

java

```java
let decoded = atob(res.content);Copy
```

이렇게 해서 출력 했더니 한글이 깨지는 현상이 발생 했습니다.

 

https://github.com/dankogai/js-base64

 

dankogai/js-base64

Base64 implementation for JavaScript. Contribute to dankogai/js-base64 development by creating an account on GitHub.

github.com

 

자체 인코더/디코더로는 부족한 듯 해서 Github에서 Base64 인코딩 해주는 js 라이브러리를 찾았습니다.

java

```java
<script src="https://cdn.jsdelivr.net/npm/js-base64@3.6.1/base64.min.js"></script>Copy
```

위의 라이브러리를 import 한 뒤에

java

```java
let decoded = Base64.decode(res.content);Copy
```

 Base64.decode를 이용해 디코딩 해 보았습니다.

 



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/oldbackup/btoa.assets/img-20230412214329693.webp)



이후 실행 해 보니

 



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/oldbackup/btoa.assets/img-20230412214329757.webp)



한글이 깨지지 않고 잘 나옵니다. 이제 마크다운만 렌더링 해서 다시 출력해주면 끝입니다.

이상입니다.
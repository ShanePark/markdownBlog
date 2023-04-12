# STS 로 Spring Boot 프로젝트 만들기. 3) Thymeleaf 를 이용한 웹 페이지 만들기

> 참고서적 : [길벗] 스프링부트 프로그래밍 입문 - 쇼다 츠야노

1. pom.xml에 Thymeleaf 추가하기

<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img.webp width=677 height=825 alt=1>



pom.xml의 Dependencies 탭을 열고 'Add' 버튼을 눌러 추가 하겠습니다.



<img src=https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649276.webp width=518 height=525 alt=2>



간단하게 Group Id와 Artifact Id 만 입력 하고 OK 버튼을 누른 뒤, 저장 합니다.

Mac의 경우는 Command + S, Windows 라면 Ctrl+S 커맨드를 입력 해 저장을 한다면, 빌드가 되는걸 우측 하단에서 확인 할 수 있습니다.



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649290.webp)



Dependency Hierarchy 에서 위와 같이 필요한 dependency 들이 들어와 있는 것을 확인 할 수 있습니다. 따로 버전도 입력하지 않아서 정말 편합니다.



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649301.webp)



pom.xml 에도 위와 같이 입력 된 것을 확인 할 수 있습니다. 사실 여기에서 바로 에디터로 입력해서 저장하는게 가장 간단하긴 하겠네요.

 



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649281.webp)



"/" url을 mapping 할 예정이라서 아까 만든 FirstController의 @RequestMapping은 주석 처리 해 두겠습니다.

 

2. 컨트롤러 만들기



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649480.webp)



 

이번에는 ThymeLeafTest 라는 컨트롤러를 만들어 보았습니다.

html 데이터로 응답해야 하므로, 더이상 RestController가 아닌 @Controller 어노테이션을 붙였습니다.

그러고는 index라는 간단한 String을 반환하게끔만 코드를 작성 했습니다. Spring Framework 에서 논리적 view name을 리턴했을 때가 생각나는 구조입니다.

 

3. 템플릿 파일 만들기

 

템플릿 파일은 resources 폴더에 templates 폴더를 만들고, 그 안에 보관 합니다. Spring Framework에서는 WEB-INF 안에 view 라는 폴더를 만들어 보관 했었는데 비슷 한 느낌입니다.

 



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649256.webp)



이미 폴더는 기본으로 만들어 져 있어서 따로 만들 필요가 없어 편합니다. 



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649295.webp)



어째서인지 모르지만 저는 new를 눌렀는데 html 파일을 만드는 wizard가 없습니다. 

구글을 통해 검색 해보니 다음과 같은 답변을 얻을 수 있었습니다.

https://github.com/spring-projects/sts4/issues/622

 

Issue with new file wizards in Spring Tool Suite 4.10.0 · Issue #622 · spring-projects/sts4

Describe the bug In STS 4.10.0: When I try to add a new HTML, CSS or JSP file to my Spring Boot project, I can't see wizards for these file types. They do not appear in the wizard list. (These ...

github.com



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649320.webp)STS4 부터는 지원을 안하니 new file을 생성하거나 web developer tools 를 설치하라고 합니다.



 

어쩔 수 없지요.



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649312.webp)



이렇게 Marketplace 에서 설치하셔도 무방합니다.

 



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649331.webp)

![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649318.webp)



그래서 저는 create File 로 index.html 파일을 만들었지만, html 파일을 만들 수 있는 분은 그대로 하시면 됩니다.

xml

```xml
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
</head>
<body>
	<h1>Hello world</h1>
</body>
</html>
```

html 파일은 간단하게 이렇게만 작성 했습니다.

그러고 서버를 켜서 ThymeLeafTest 컨트롤러로 매핑된 주소를 입력 하면,



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649418.webp)



작성 해 둔 템플릿의 내용이 표시되는 것을 확인 할 수 있습니다.

 

4. 템플릿에 데이터 전달하기

이번에는 컨트롤러에서 템플릿으로 데이터를 전달 해 보도록 하겠습니다.

 

ThymeLeafTest2 라는 이름의 새로운 컨트롤러를 만들었습니다.

xml

```xml
package com.shane.boot.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class ThymeLeafTest2 {
	@RequestMapping("/{number}")
	public String index(@PathVariable int number, Model model) {
		model.addAttribute("pow", number * number);
		return "index";
	}
}
```

코드는 위와 같이, number로 들어온 pathVariable의 int 값을 제곱 해서 pow 라는 이름으로 model 을 활용한 addAttribute 하는 방식입니다.

 

이번엔 index.html 파일을 조금 수정해 보겠습니다.

xml

```xml
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
</head>
<body>
	<h1>Hello world</h1>
	<p>pow is <span th:text="${pow}"></span></p>
</body>
</html>
```

 

이후 서버를 실행 해서 해당 url 로 요청을 보내면



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649354.webp)



의도한 대로, url에 들어간 pathVariable 값의 제곱을 보여주는 템플릿 화면이 출력 되는 것을 확인 할 수 있습니다.

 

Model 클래스는 스프링 프레임워크를 배울때, 선생님께서 Spring에서 데이터 전달을 할 때는 Model을 얼마나 잘 쓰는지가 중요하다고 강조하셨던 게 기억 납니다.

**Model** 클래스는 데이터만 관리하며 뷰에 관련된 정보는 가지고 있지 않습니다. 따라서 Model을 반환값 으로 사용 할 수는 없지만,,,

**ModelAndView** 클래스는 뷰 관련 정보도 가지고 있기 때문에 ModelAndView를 그대로 반환 값 으로 사용할 수 있습니다. 또한 이를 통해 설정한 템플릿 도 사용 할 수 있습니다.

이번에는 앞선 예제를 ModelAndView로 변경해보도록 하겠습니다.

 



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649330.webp)기존의 파일을 변경한다면 상관 없지만, 새로운 컨트롤러를 생성한다면 기존의 컨트롤러의 RequestMapping은 주석을 꼭 걸어주세요.



xml

```xml
package com.shane.boot.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class ThymeLeafTest3 {
	@RequestMapping("/{number}")
	public ModelAndView index(@PathVariable int number
			, ModelAndView mav) {
		mav.addObject("pow", number * number);
		mav.setViewName("powTemplate2");
		return mav;
	}
}
```

새로운 컨트롤러 입니다. Model을 쓸 때와 크게 변한것은 없지만,

1.Return 타입이 modelAndView 로 변했습니다.

2. modelAddAttribute를 쓰지 않고 addObject를 사용합니다.
3. viewName은 setViewName을 통해 합니다. 
4. 반환은 ModelAndView 클래스 그대로 합니다.

xml

```xml
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
</head>
<body>
	<h1>Hello Thymeleaf</h1>
	<p>pow is <span th:text="${pow}"></span></p>
</body>
</html>
```

앞서 만든 템플릿을 재활용 해도 상관 없지만, 뭔가 변한걸 보고 싶어서 powTemplate3.html 파일로 만들고, Hello world를 Hello Thymeleaf로 변경했습니다.

 

이제 서버를 켜서 확인 해 보면,



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649580.webp)



ModelAndView를 활용해도 같은 결과를 얻는 것을 확인 할 수 있습니다.

Model을 쓰든 ModelAndView를 쓰든 개인 취향 차이 라고 생각합니다. 저는 ModelAndView가 좀 더 가독성이 좋다고 생각되어서 ModelAndView 쪽의 손을 들어 주고 싶습니다. Spring에 대해 모른다면 return으로 뷰 네임을 하는게 어색 할 수 있다고 생각됩니다.

 

이번에는 html의 form을 사용 해 보도록 하겠습니다.

xml

```xml
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
</head>
<body>
	<h1>Hello Thymeleaf</h1>
	<p th:text="${msg}">loading..</p>
	<form method="post" action="/">
		<input type="text" name="text" th:value="${value}"/>
		<input type="submit" value="submit">
	</form>
</body>
</html>
```

 

다음과 같이 msg를 보여준 뒤에, form 태그를 통해 text를 입력 받도록 html 파일을 작성 했습니다.

 

xml

```xml
package com.shane.boot.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class FormTest {
	
	@GetMapping("/")
	public ModelAndView index(ModelAndView mav) {
		mav.addObject("msg", "please type your name");
		mav.setViewName("formTemplate");
		return mav;
	}
	
	@PostMapping("/")
	public ModelAndView form(
			@RequestParam("text")String text
			,ModelAndView mav) {
		mav.addObject("msg", String.format("welcome %s", text));
		mav.addObject("value", text);
		mav.setViewName("formTemplate");
		return mav;
	}
	
	
}
```

이번 컨트롤러는 Method를 2개 가지고 있습니다.

같은 주소이지만 각각 Get 방식과 Post 방식일 때 따로 요청을 처리합니다.

 

Get 요청에서는 이름을 입력하라는 msg 데이터를 넘기고, Post 요청에서는 입력받은 text 에게 환영 메시지를 띄웁니다. 둘 모두 template은 같은 파일을 공유합니다.

 

서버를 실행 합니다

 



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649399.webp)



이름을 입력하라며 input 이 한개 있습니다.

shane을 입력해 submit을 했더니

 



![img](https://raw.githubusercontent.com/Shane-Park/markdownBlog/master/backend/spring/sts-project3.assets/img-20230412212649342.webp)



보이는 것 처럼 welcome shane 메시지를 보여주는 것을 확인 할 수 있습니다.

 

 

이상입니다.

 

https://github.com/Shane-Park/springBootStudy

 

Shane-Park/springBootStudy

this repository was made for 2 days sprint of Spring boot for naver coding test - Shane-Park/springBootStudy

github.com

해당 포스팅의 모든 코드는 위 URL에서 확인 하실 수 있습니다.

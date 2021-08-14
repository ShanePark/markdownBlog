# Spring 에서 페블 템플릿 Pebble template 사용하기 2 ) Spring boot 프로젝트에서 사용하기

​	

본 글은 https://pebbletemplates.io/wiki/guide/spring-boot-integration/ 를 토대로 작성되었습니다.

​	

## 일단 스프링 부트 프로젝트를 생성 합니다.

![image-20210814110046712](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814110046712.png)

> Spring Starter를 이용하면 쉽게 생성 할 수 있습니다

​	

![image-20210814110327294](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814110327294.png)

> Maven 프로젝트로 생성 했지만 Gradle로 해도 무관합니다. 적당한 Location에 적당한 이름으로 프로젝트를 생성합니다.

​	

![image-20210814110440604](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814110440604.png)

> 간단하게 Sprinig Web만 추가해서 생성하겠습니다.

​	

Finish 를 눌러 프로젝트를 생성 합니다.



## Dependency를 추가해 줍니다.

### Maven

```xml
<dependency>
	<groupId>io.pebbletemplates</groupId>
	<artifactId>pebble-spring-boot-starter</artifactId>
	<version>3.1.5</version>
</dependency>
```

### Gradle

```
compile "io.pebbletemplates:pebble-spring-boot-starter:3.1.5"
```



### 이렇게 dependency 에 추가하는 것 만으로 충분하다고 설명되어 있습니다. 

![image-20210814111833538](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814111833538.png)

### 자동 설정에 포함된 내용들은

- Loader가 클래스 패스의 /templates/ 폴더 안에 있는 .pebble 템플릿 파일들을 pick 함
- 이전의 loader로 구성된 Pebble 엔진
- 스프링 확장 프로그램
- UTF-8 인코딩으로 된 text/html 파일을 출력할 뷰 리졸버

> spring-boot-starter에 의존하기 때문에 dependency에 추가하거나 Spring MVC를 적당하게 설정 해 주어야 합니다.



![image-20210814114238572](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814114238572.png)

​	

### application.properties 에 정의 할 수 있는 스프링 부트 외부 설정에 관한 내용입니다. 모두 pebble. 으로 시작합니다.

- pebble.prefix : MVC 뷰 네임의 prefix를 설정합니다. 기본 값은 /templates/ 입니다.
- pebble.suffix : 마찬가지로 mvc view name 뒤에 붙을 suffix를 설정합니다. 기본값은 .pebble 입니다.
- pebble.cache : PebbleEngine의 cache를 사용하거나 비활성화 합니다. 기본 값은 true 입니다.
- pebble.contentType : ViewResolver에 설정될 기본 콘텐츠 타입을 설정합니다. 기본은 text/html 입니다.
- pebble.encoding : ViewResolver에 설정될 text Encoding을 정의합니다. 기본은 UTF-8 입니다.
- pebble.exposeRequestAttribute : ViewResolver를 위해 template에 model이 들어가기 전에 모든 request 속성들이 추가 되어야 하는지에 대한 설정입니다. 기본값은 false 입니다.
- pebble.exposeSessionAttributes : 마찬가지로 모든 session 속성들이 미리 추가되어야 하는지에 대한 설정이며 기본값은 false 입니다.
- pebble.defaultLocale : 기본 Locale 설정이며 Default 는 null 입니다.
- pebble.strictVariables : PebbleEngine 안의 변수들을 엄격하게 체킹할 것인지에 대한 설정이며 기본은 false 입니다.
- pebble.greedyMatchMethod : Pebbl Engine 안에서 java 메서드들을 찾을때 greedy matching 모드를 사용할지에 대한 설정이며 기본 값은 false 입니다.



## 이제 이전 글에서 했던 것과 똑같이 html 파일들과 controller를 추가해 보겠습니다.

![image-20210814121906389](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814121906389.png)



### PebbleController.java

```java
package com.shane;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

@RestController
public class PebbleController {
	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody()
	public String home(ModelAndView mav) throws IOException {
		PebbleEngine engine = new PebbleEngine.Builder().build();
		PebbleTemplate compiledTemplate = engine.getTemplate("templates/home.html");

		Map<String, Object> context = new HashMap<>();
		context.put("name", "Shane");

		Writer writer = new StringWriter();
		compiledTemplate.evaluate(writer, context);

		String output = writer.toString();
		return output;
	}
}
```

​	

### base.html

```html
<html>
<head>
<title>{% block title %}My Website{% endblock %}</title>
</head>
<body>
	<div id="content">{% block content %}{% endblock %}</div>
	<div id="footer">{% block footer %} Copyright 2021 {% endblock %}
	</div>
</body>
</html>
```



### home.html

```html
{% extends "./base.html" %}

{% block title %} Home {% endblock %}

{% block content %}
    <h1> Home </h1>
    <p> Welcome to my home page. My name is {{ name }}.</p>
{% endblock %}
```



서버를 실행하면 이전 글과 마찬가지로 잘 렌더링 해 줍니다.

![image-20210814122024959](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814122024959.png)

## 이번엔 Pebble의 ViewResolver를 이용 해 보겠습니다.

아직 특별히 설정을 바꾸진 않겠지만, html 파일이 변경 될때마다 새로 고침을 하기는 귀찮으니 cache 설정을 변경하도록 하겠습니다.



applcation.yml 에 아래 내용을 추가합니다.

```yaml
pebble:
  cache : false
```



.properties 파일을 사용 한다면 아래와 같겠네요

```properties
pebble.cache = false
```



### 일단 새로운 컨트롤러를 하나 만들어줍니다.

```java
package com.shane;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

@Controller
public class PebbleModelController {
	
	@GetMapping(value = "/model")
	public String home(Model model) throws IOException {
		model.addAttribute("name", "Shane");
		return "springhome";
	}
	
}
```

위에서 썼던 코드와는 자동 설정된 PebbleViewResolver를 사용하기 떄문에 코드가 훨씬 간결합니다.



### 궁금해서 열어보니 PebbleViewResolver는 아래와 같이 작성 되어 있습니다.

![image-20210814142123818](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814142123818.png)



이제 템플릿을 만들어보겠습니다. 설정을 따로 바꾸지 않았기 떄문에 .pebble 확장자로 만들어 줘야 합니다.



### base.pebble

```html
<html>
<head>
<title>{% block title %}My Pebble base{% endblock %}</title>
</head>
<body>
	<div id="content">{% block content %}{% endblock %}</div>
	<div id="footer">{% block footer %} Copyright 2021 {% endblock %}
	</div>
</body>
</html>
```



사실 base.html 파일과 내용이 같은데요, 확장자를 .pebble로 변경해 줬습니다.



### springhome.pebble

```html
{% extends "./base" %}

{% block title %} Home {% endblock %}

{% block content %}
    <h1> Spring Pebble Home </h1>
    <p> Welcome to my home page. My name is {{ name }}.</p>
{% endblock %}
```

이번엔 springhome.pebble 파일입니다.

extneds 에 ./base 라고 써있지만, default 설정에 의해 자동으로 ./base.pebble 파일을 찾습니다.



이제 서버를 실행해서 localhost:8080/model로 접속 해 보겠습니다.



![image-20210814142424292](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814142424292.png)

> 서버를 실행하고

![image-20210814142436363](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814142436363.png)

의도대로 잘 출력되어서 나오는 것이 확인됩니다 ! 특히나 Pebble Template의 특징인 상속이 재밌습니다.



## 이번에는 .pebble 대신 .html을 사용하기 위해 설정을 변경해보도록 하겠습니다.



### application.yml에 아래 suffix 내용을 추가해 줍니다.

![image-20210814142851741](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814142851741.png)

### application.properties 라면

```properties
pebble.suffix = .html
```

내용이 한줄 더 추가 되어야 겠네요.



그러면 이제 .pebble 생생했던 파일들은 삭제하고, base.html과 home.html을 사용할 수 있습니다.



대신 home.html 에서 extends "./base.html" 로 되어있던 거에서 .html 내용을 제거해줘야합니다. 자동으로 붙기 떄문에.

### base.html

```html
<html>
<head>
<title>{% block title %}My Website{% endblock %}</title>
</head>
<body>
	<div id="content">{% block content %}{% endblock %}</div>
	<div id="footer">{% block footer %} Copyright 2021 {% endblock %}
	</div>
</body>
</html>
```



### home.html

```html
{% extends "./base" %}

{% block title %} Home {% endblock %}

{% block content %}
    <h1> Home </h1>
    <p> Welcome to my home page. My name is {{ name }}.</p>
{% endblock %}
```



### PebbleModelController.java

```java
package com.shane;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

@Controller
public class PebbleModelController {
	
	@GetMapping(value = "/model")
	public String home(Model model) {
		model.addAttribute("name", "Shane");
		return "home";
	}
	
}
```

이제 이렇게 하면 /templates/ Pebble View Resolver가 폴더에서 home.html 파일을 찾습니다. 그러고 home.html 파일은 동일 폴더 내의 base.html 파일을 찾아 상속 하고 스스로의 내용을 붙여서 렌더링 해 줍니다.

​	

![image-20210814143418732](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814143418732.png)



문제없이 잘 출력되는 것이 확인됩니다.



## 몇가지 Pebble Template의 기능들을 테스트 해 보겠습니다.



### 테스트를 위해 PebbleModelController에 GetMapping을 하나 추가하겠습니다.

```java
	@GetMapping(value = "/test")
	public String test(Model model) {
		model.addAttribute("name", "Shane");
		return "test";
	}
```



### text.html

```html
{% extends "./base" %}

{% block title %} Home {% endblock %}

{% block content %}
    <h1> Home </h1>
    <p> Welcome to my home page. My name is {{ name }}.</p>
    {{ response.contentType }} 
{% endblock %}
```



response 데이터를 활용 할 수 있습니다.

![image-20210814144119448](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814144119448.png)



## 반복문을 사용해보겠습니다

​	

```java
	@GetMapping(value = "/test")
	public String test(Model model) {
		model.addAttribute("name", "Shane");
		model.addAttribute("arr", new int[] {1,3,5});
		return "test";
	}
```

arr 이라는 이름으로 배열을 model에 담았는데요.



### text.html

```html
{% extends "./base" %}

{% block title %} Home {% endblock %}

{% block content %}
    <h1> Home </h1>
    <p> Welcome to my home page. My name is {{ name }}.</p>
    {% for data in arr %}
    	<p>{{ data }}</p>
    {% endfor %}
{% endblock %}
```

위와 같은 문법으로 반복문을 사용 할 수 있습니다.

결과를 확인해보면

![image-20210814144739223](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814144739223.png)

배열에 담은 값들이 순차적으로 잘 출력 되었습니다.



## 조건문도 사용해보겠습니다.

### text.html

```html
{% extends "./base" %}

{% block title %} Home {% endblock %}

{% block content %}
    <h1> Home </h1>
    <p> Welcome to my home page. My name is {{ name }}.</p>
    {% if arr != null %}
	    {% for data in arr %}
	    	<p>{{ data }}</p>
	    {% endfor %}
	{% else %}
		데이터가 없습니다.
	{% endif %}
{% endblock %}
```

arr이 있으면 위에서 처럼 데이터를 출력해주고, 없으면 데이터가 없다는 내용을 출력해줍니다.



![image-20210814151334250](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814151334250.png)

테스트를 위해 arr을 추가해주는 내용을 주석 처리 했습니다.

![image-20210814151734095](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814151734095.png)

> if 문에 의해 데이터가 없을때는 해당 하는 화면을 출력 해 주는것이 확인 됩니다.



![image-20210814151854676](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814151854676.png)

> empty를 체크해주는 기능이 있기 때문에 간편하게 String의 null 체크나 empty 체크를 할 수도 있습니다. String 뿐만 아니라 Collection 이나  Map의 empty 체크도 가능합니다.

​	

![image-20210814151958582](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814151958582.png)

> iterable 을 체크할 수도 있습니다.

​	

이번에는  arr2 라는 이름으로 몇가지 과일을 등록하고

```java
		model.addAttribute("arr2", new String[] {"apple", "banana"});
```



contains를 활용 해 보겠습니다.

```html
{% extends "./base" %}

{% block title %} Home {% endblock %}

{% block content %}
    <h1> Home </h1>
    <p> Welcome to my home page. My name is {{ name }}.</p>
    {% if arr != null %}
	    {% for data in arr %}
	    	<p>{{ data }}</p>
	    {% endfor %}
	    {% if arr2 contains "apple" %}
	    	apple을 포함합니다.
	    {% else %}
	    	apple을 포함하지 않습니다.
	    {% endif %}
	{% else %}
		데이터가 없습니다.
	{% endif %}
{% endblock %}
```



![image-20210814152450004](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814152450004.png)

contains 도 잘 작동합니다.



![image-20210814152521541](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814152521541.png)

> 설명을 보면 contains에 배열 데이터도 넣을 수 있게 되어 있습니다.



## 마지막으로 verbatim에 대해 알아보겠습니다.

![image-20210814152741896](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814152741896.png)

> verbatin에 들어간 내용은 Pebble이 parsing 하지 않고 있는 그대로 보내줍니다. 
>
> 이게 정말 필요한 이유가,  Vue.js 등에서도 {{ }} 형태를 사용하기 떄문입니다. Pebble이 서버사이드에서 {{ }}를 모두 번역해버리면 클라이언트 사이드 쪽에서 해당 괄호를 렌더링 해야 하는데 이미 의도하지 않은 방향으로 변경되기 떄문입니다.

```html
{% extends "./base" %}

{% block title %} Home {% endblock %}

{% block content %}
    <h1> Home </h1>
    <p> Welcome to my home page. My name is {{ name }}.</p>
    {% if arr != null %}
	    {% for data in arr %}
	    	<p>{{ data }}</p>
	    {% endfor %}
	    {% if arr2 contains "apple" %}
	    	apple을 포함합니다.
	    {% else %}
	    	apple을 포함하지 않습니다.
	    {% endif %}
	{% else %}
		데이터가 없습니다.
	{% endif %}
	<br>
	{% verbatim %}
	    {% if arr != null %}
		    {% for data in arr %}
		    	<p>{{ data }}</p>
		    {% endfor %}
		    {% if arr2 contains "apple" %}
		    	apple을 포함합니다.
		    {% else %}
		    	apple을 포함하지 않습니다.
		    {% endif %}
		{% else %}
			데이터가 없습니다.
		{% endif %}
	{% endverbatim %}
{% endblock %}
```

vebatim 블럭 안에 위에서 사용한 코드를 똑같이 넣어보겠습니다.



![image-20210814153137834](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebbleSpring.assets/image-20210814153137834.png)

그러면 해당 블럭 안의 내용들은 있는 그대로 넘어갑니다. HTML의 <pre> 태그 같은 느낌이네요.



서버사이드 템플릿 엔진으로 매력적인  Pebble Template 이었습니다.

해당 프로젝트의 코드들은 https://github.com/Shane-Park/markdownBlog/tree/master/projects/pebblespring 에서 확인하실 수 있습니다.
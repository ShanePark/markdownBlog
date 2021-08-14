# Spring 에서 페블 템플릿 Pebble template 사용하기 1 ) 기본 사용

​	

## Pebble Template이란 ?

![image-20210812224842647](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebble.assets/image-20210812224842647.png)

> Pebble은 PHP의 유명 템플릿 엔진인 Twig 에서 영감을 받았으며 Python Jinja의 Template Engine 문법과 유사한 Java 템플릿 엔진 입니다. template을 상속 하는 기능과 읽기 쉬운 문법이 특징인 서버사이드 템플릿 엔진 입니다.

##  

### 첫 글에서는 간단하게 https://pebbletemplates.io 의 Basic Usage를 하나씩 따라해보겠습니다.

​	

1. ### dependency 를 추가합니다

![image-20210812230724671](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebble.assets/image-20210812230724671.png)

> Spring Boot 에서는 pebble-spring-boot-starter 를 추가해서 사용하지만, 일단 Basic Usage에 쓰인 대로 따라가보겠습니다.

- Maven

``` xml
<dependency>
	<groupId>io.pebbletemplates</groupId>
	<artifactId>pebble</artifactId>
	<version>3.1.5</version>
</dependency>
```

- Gradle

```
compile "io.pebbletemplates:pebble:3.1.5"
```

​	

2. ### WEB-INF 폴더에 base.html 파일을 만들으라고 합니다.

   ​	

   ![image-20210812231324182](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebble.assets/image-20210812231324182.png)

   

   저는 프로젝트 패키지 구조를 아래와 같이 생성 했습니다.

   ![image-20210812231428853](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebble.assets/image-20210812231428853.png)

   

   > 저는 SpringBoot로 프로젝트를 생성해서 WEB-INF 폴더가 따로 있지는 않아 resources/templates 폴더에 생성했습니다.

   

   base.html

   ```html
   <html>
   <head>
   	<title>{% block title %}My Website{% endblock %}</title>
   </head>
   <body>
   	<div id="content">
   		{% block content %}{% endblock %}
   	</div>
   	<div id="footer">
   		{% block footer %}
   			Copyright 2021
   		{% endblock %}
   	</div>
   </body>
   </html>
   ```

   

### 3. base.html 을 상속하는 home.html 파일을 생성합니다.

​	

![image-20210812231731732](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebble.assets/image-20210812231731732.png)

```html
{% extends "./base.html" %}

{% block title %} Home {% endblock %}

{% block content %}
	<h1> Home </h1>
	<p> Welcome to my home page. My name is {{ name }}.</p>
{% endblock %}
```

​	

### 4. template을 컴파일 해 렌더링 해봅니다.

![image-20210812231952548](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebble.assets/image-20210812231952548.png)

PebbleController.java 를 생성해 렌더링한 html을 바로 확인 할 수 있도록 해보겠습니다.

```java
package com.example.demo;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

@Controller
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



### 5. 결과물은 아래와 같습니다.

![image-20210812232057794](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebble.assets/image-20210812232057794.png)



정말 이렇게 되었는지 확인 해 보도록 하겠습니다.



일단 서버를 실행 하고 

![image-20210812232124276](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebble.assets/image-20210812232124276.png)



실행 한 서버로 접속 해 봅니다. 저는 application.properties에

```
server.port = 8081
```

를 추가해서 port를 8081 로 변경했기 때문에 localhost:8081 로 접속하지만, 설정하지 않았다면 기본적으로 8080 포트로 설정 되어 있습니다.

![image-20210812232154433](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebble.assets/image-20210812232154433.png)

잘 렌더링 되어서 보여주는 것을 확인 할 수 있습니다.



우클릭을 해서 페이지 소스보기를 해보면

![image-20210812232353917](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebble.assets/image-20210812232353917.png)

![image-20210812232424466](https://github.com/Shane-Park/markdownBlog/raw/master/pebble/pebble.assets/image-20210812232424466.png)



깔끔하게 의도한 대로 렌더링 되어있는 것을 확인 할 수 있습니다.



다음번에는 Spring Boot에 PebbleViewResolver를 선언하고 PebbleEngine이나 Loader도 커스터마이징 해 보도록 하겠습니다.

그 다음 글에서는 Pebble 문법에 대해 알아 보겠습니다.




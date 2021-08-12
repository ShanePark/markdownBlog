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

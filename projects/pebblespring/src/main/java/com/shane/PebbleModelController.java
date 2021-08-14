package com.shane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PebbleModelController {
	
	@GetMapping(value = "/model")
	public String home(Model model) {
		model.addAttribute("name", "Shane");
		return "home";
	}
	
	@GetMapping(value = "/test")
	public String test(Model model) {
		model.addAttribute("name", "Shane");
		model.addAttribute("arr", new int[] {1,3,5});
		model.addAttribute("arr2", new String[] {"apple", "banana"});
		return "test";
	}
	
}

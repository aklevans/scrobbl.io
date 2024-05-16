package com.example.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MappingController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
}

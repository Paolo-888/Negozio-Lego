package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//localhost:8051/homepage
@Controller
@RequestMapping("/homepage")
class HomePageController {

	@GetMapping
	public String getPage() {
		
		return "homepage";
	}
}

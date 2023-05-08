package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("acquistocompletato")
public class AcquistoCompletatoController {

	@GetMapping
	public String getPage(HttpSession session) {
		
		if (session.getAttribute("cliente") == null) 
			return "redirect:/logincliente";
		
		return "acquistocompletato";
	}
}

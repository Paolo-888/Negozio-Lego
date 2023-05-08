package it.corso.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Catalogo;

import jakarta.servlet.http.HttpSession;

//localhost:8051/carrello
@Controller
@RequestMapping("/carrello")
public class CarelloController {


	
	@SuppressWarnings("unchecked")
	@GetMapping
	public String getPage(HttpSession session, Model model) {
		
		
		List<Catalogo> carrello = session.getAttribute("carrello") == null ? new ArrayList<>() : (List<Catalogo>) session.getAttribute("carrello");
		double totale = carrello.stream().mapToDouble(p->p.getPrezzo()).reduce(0, (p1,p2)->p1+p2);
		model.addAttribute("totale", totale);
		model.addAttribute("carrello", carrello);
		
		return "carrello";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/elimina")
	public String eliminaArticolo(@RequestParam("id") int id, HttpSession session) {
		
		List<Catalogo> carrello = (List<Catalogo>) session.getAttribute("carrello");
		
		for (Catalogo c : carrello) {
			if (c.getId()== id) {
				carrello.remove(c);
				break;
			}
		}
		session.setAttribute("carrello", carrello);
		
		return "redirect:/carrello";
	}
}

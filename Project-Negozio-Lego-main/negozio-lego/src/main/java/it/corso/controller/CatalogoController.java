package it.corso.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Catalogo;
import it.corso.service.CatalogoService;
import jakarta.servlet.http.HttpSession;


//localhost:8051/catalogo
@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

	@Autowired
	private CatalogoService catalogoService;
	
	
	@GetMapping
	public String getPage(@RequestParam(name ="id", required=false) Integer id, Model model) {
		
		List<Catalogo> prodotto = catalogoService.getProdotti();
		if (id !=null ) {
			prodotto = prodotto.stream().filter(p->p.getCategoria().getId()==id).collect(Collectors.toList());
		}
		model.addAttribute("catalogo", prodotto); 
		
		return "catalogo";
	}
	
	@GetMapping("/aggiungi")
	public String aggiungi(@RequestParam("id") int id, HttpSession session) {
		
		catalogoService.aggiungiAlCarrello(id, session);
		
		return "redirect:/catalogo";
	}
}

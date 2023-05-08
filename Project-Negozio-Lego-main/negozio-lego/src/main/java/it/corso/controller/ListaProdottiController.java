package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.model.Catalogo;
import it.corso.model.Categoria;
import it.corso.service.CatalogoService;
import it.corso.service.CategoriaService;

@Controller
@RequestMapping("/listaprodotti")
public class ListaProdottiController {

	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired 
	private CategoriaService categoriaService;
	
	@GetMapping
	public String getPage(Model model) {
		
		List<Catalogo> catalogo =catalogoService.getProdotti();
//		Catalogo prodotto = new Catalogo();
		List<Categoria> categoria = categoriaService.getCategoria();
		model.addAttribute("catalogo", catalogo);
//		model.addAttribute("prodotto", prodotto);
		model.addAttribute("categoria", categoria);
		
		return "listaprodotti";
	}
}

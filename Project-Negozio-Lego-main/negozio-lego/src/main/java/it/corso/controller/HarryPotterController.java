package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Catalogo;
import it.corso.model.Categoria;
import it.corso.service.CatalogoService;
import it.corso.service.CategoriaService;

//localhost:8051/harrypotter
@Controller
@RequestMapping("/harrypotter")
public class HarryPotterController {

	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public String getPage(/*@RequestParam(name ="id", required=false) int id,*/ Model model) {
		
		List<Catalogo> prodotto = catalogoService.getProdotti();
		model.addAttribute("catalogo", prodotto); 
		
		return "harrypotter";
	}
	
	@GetMapping("/filtro")
	public String filtroCatalogo(@RequestParam(name ="id", required=false) int id, Model model) {
		
		List<Categoria> categoria = categoriaService.getCategoria();
		model.addAttribute("categoria", categoria);
		model.addAttribute("id", id);
		
		return "harrypotter";
	}
	

}

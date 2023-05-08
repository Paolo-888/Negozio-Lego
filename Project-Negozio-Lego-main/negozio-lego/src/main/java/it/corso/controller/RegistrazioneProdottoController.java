package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.corso.model.Catalogo;
import it.corso.model.Categoria;
import it.corso.service.CatalogoService;
import it.corso.service.CategoriaService;
import jakarta.servlet.http.HttpSession;

//localhost:8051/gestioneprodotto
@Controller
@RequestMapping("/gestioneprodotto")
public class RegistrazioneProdottoController {

	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired 
	private CategoriaService categoriaService;
	
	@GetMapping
	public String getPage(HttpSession session, Model model) {
		
		if(session.getAttribute("admin") == null)
			return "redirect:/loginareariservata";
		
		List<Catalogo> catalogo =catalogoService.getProdotti();
		Catalogo prodotto = new Catalogo();
		List<Categoria> categorie = categoriaService.getCategoria();
		Categoria categoria = new Categoria();	
	    model.addAttribute("catalogo", catalogo);
		model.addAttribute("prodotto", prodotto);
		model.addAttribute("categoria", categoria);
		model.addAttribute("categorie", categorie);
		
		return "gestioneprodotto";
	}
	
	@PostMapping("salvaprodotto")
	public String registraProdotto (
			@RequestParam("nome") String nome,
            @RequestParam("descrizione") String descrizione,
            @RequestParam("prezzo") double prezzo,
            @RequestParam(name ="immagine", required = false) MultipartFile immagine,
            @RequestParam(name ="categoria", required = false) int idCategoria
            ) 
	{
		catalogoService.registraProdotto(nome, descrizione, immagine, prezzo, idCategoria);
        return	"redirect:/gestioneprodotto";
	}

	@GetMapping("/cancellaprodotto")
	public String cancellaProdotto(@RequestParam("id") int id)
	{
		Catalogo catalogo = catalogoService.getProdottoById(id);
		catalogoService.cancellaProdotto(catalogo);
		return "redirect:/gestioneprodotto";
	}
			

}

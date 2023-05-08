package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.service.AmministratoreService;
import jakarta.servlet.http.HttpSession;

//localhost:8051/loginareariservata
@Controller
@RequestMapping("/loginareariservata")
public class LoginAreaRiservataController {

	@Autowired
	private AmministratoreService amministratoreService;
	
	@GetMapping
	private String getPage (
			@RequestParam(name= "le", required = false) String logError,
			Model model,
			HttpSession session) {
		if (session.getAttribute("admin") !=null) //se l'ogetto utente è diverso da null (cioè se esiste)} allora quindi quando clicchi va li e cambia subito pagina andando a redirect:/reserved 
			return "redirect:/sceltamodifica";
		
		
		model.addAttribute("logError", logError != null); //true oppure false
		return "loginareariservata";	
	}
	
	@PostMapping
	public String gestioneLogin( //vado a controllare se i miei dati sono preseni se corrispondo mi fa l'accesso altrimenti mi da errore
			@RequestParam ("username") String username,
			@RequestParam ("password") String password,
			HttpSession session)
			 {
		if (!amministratoreService.controlloLogin(username, password, session))
			return "redirect:/loginareariservata?le"; // in questo modo se da errore mi indicare di reinseire i dati segnandomi i campi sbagliati
		
		return "redirect:/sceltamodifica";
	}
}

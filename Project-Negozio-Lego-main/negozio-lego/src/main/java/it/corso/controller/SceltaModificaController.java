package it.corso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.model.Amministratore;
import jakarta.servlet.http.HttpSession;

//localhost:8051/sceltamodifica
@Controller
@RequestMapping("/sceltamodifica")
public class SceltaModificaController {

	@GetMapping
	public String getPage(HttpSession session, Model model) { //qui permette di farmi diventare il Reserved accesibile solo tramite login e non tramite login almeno che non hai fatto l'accesso
		if(session.getAttribute("admin") == null)
			return "redirect:/loginareariservata";
		Amministratore admin = (Amministratore) session.getAttribute("admin"); //qui abbiamo fatto il casting di Utente sul session per salvare l'oggetto //"utente" ci riferiamo della chiave di riferimento
		model.addAttribute("admin", admin); //lo utlizzeremo nel html per fare il saluto personalizzato in base il suo mome utente con user.ParametroDesiderato
		return "sceltamodifica";
	}
}

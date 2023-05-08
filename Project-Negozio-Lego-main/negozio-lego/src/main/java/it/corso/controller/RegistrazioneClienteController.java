package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.service.ClienteService;
import jakarta.servlet.http.HttpSession;

//localhost:8051/registrazionecliente
@Controller
@RequestMapping("registrazionecliente")
public class RegistrazioneClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public String getPage(HttpSession session) {
		
		
		return "registrazionecliente";
	}
	
	@PostMapping("/salvacliente")
	public String registraCliente(
			@RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome,
			@RequestParam("indirizzo") String indirizzo,
			@RequestParam("cap") String cap,
			@RequestParam("localita") String localita,
			@RequestParam("provincia") String provincia,
			@RequestParam("telefono_cellulare") String telefono_cellulare,
			@RequestParam("email") String email,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session
			) {
		
		clienteService.registraCliente(nome, cognome, indirizzo, cap, localita, provincia, telefono_cellulare,email, username, password);
		return "redirect:/logincliente"; //da mettere "redirect:/logincliente"
	}	
}

package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Cliente;
import it.corso.model.Ordine;
import it.corso.service.ClienteService;
import it.corso.service.OrdineService;

//localhost:8051/amministrazione
@Controller
@RequestMapping("/amministrazione")
public class AmministrazioneController {
	 
	 @Autowired
	 private ClienteService clienteService;
	 
	 @Autowired
	 private OrdineService ordineService;
	 
	 @GetMapping
	 public String getPgae(Model model) {
		 
		 List<Cliente> clienti  = clienteService.getClienti();
		 List<Ordine> ordini = ordineService.geOrdine();
		 model.addAttribute("ordini", ordini);
		 model.addAttribute("clienti", clienti);
		 
		 return "amministrazione";
	 }
	 
		@GetMapping("/cancellacliente")
		public String cancellaCliente(@RequestParam("id") int id)
		{
			Cliente clienti = clienteService.getClienteById(id);
			clienteService.cancellaCliente(clienti);
			
			return "redirect:/amministrazione";
			
		}
		
		@GetMapping("/cancellaordine")
		public String cancellaOrdine(@RequestParam("id") int id)
		{
			Ordine ordini = ordineService.getOrdineById(id);
			ordineService.cancellaOrdine(ordini);
			
			return "redirect:/amministrazione";
			
		}
}













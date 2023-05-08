package it.corso.service;

import java.util.List;

import it.corso.model.Cliente;
import jakarta.servlet.http.HttpSession;

public interface ClienteService {

	void registraCliente(String nome, String cognome, String indirizzo, String cap, String localita, String provincia, String telefono_cellulare, String email, String username, String password);
	Cliente getClienteById(int id);
	List<Cliente> getClienti();
	void cancellaCliente(Cliente cliente);
	
	boolean controlloLogin(String username, String password, HttpSession session);
	boolean controlloCreazioneAccount(String username, HttpSession session);
}

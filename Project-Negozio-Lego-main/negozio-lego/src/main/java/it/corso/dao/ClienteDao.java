package it.corso.dao;

import org.springframework.data.repository.CrudRepository;


import it.corso.model.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, Integer>{

	Cliente findByProfiloUsernameAndProfiloPassword(String username, String password);// controllo login
	
	Cliente findByProfiloUsername (String username);  //controllo username se già in uso per la registrazione
}

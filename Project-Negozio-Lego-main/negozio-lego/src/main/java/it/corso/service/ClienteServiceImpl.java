package it.corso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.ClienteDao;
import it.corso.model.Cliente;
import it.corso.model.Profilo;
import jakarta.servlet.http.HttpSession;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteDao clienteDao;
	

	
	@Override
	public void registraCliente(String nome, String cognome, String indirizzo, String cap, String localita, String provincia, String telefono_cellulare, String email, String username, String password) {
		
		//Si possono migliore con controlli per l'inserimento
		Profilo profilo = new Profilo();
		profilo.setUsername(username);
		profilo.setPassword(password);
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCognome(cognome);
		cliente.setIndirizzo(indirizzo);
		cliente.setCap(cap);
		cliente.setLocalita(localita);
		cliente.setProvincia(provincia);
		cliente.setTelefono_cellulare(telefono_cellulare);
		cliente.setEmail(email);
		cliente.setProfilo(profilo);
		
		clienteDao.save(cliente);

	}

	@Override
	public Cliente getClienteById(int id) {
		
		return clienteDao.findById(id).get();
	}

	@Override
	public List<Cliente> getClienti() {

		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public void cancellaCliente(Cliente cliente) {
		clienteDao.delete(cliente);

	}
	
	@Override
	public boolean controlloLogin(String username, String password, HttpSession session) {
		
		Cliente cliente = clienteDao.findByProfiloUsernameAndProfiloPassword(username, password);
		
		//controllo login 
		if(cliente != null) {
			session.setAttribute("cliente", cliente);
			return true;
		}
		
		return false;
		
	}
	
	@Override
    public boolean controlloCreazioneAccount(String username, HttpSession session) {

        Cliente cliente = clienteDao.findByProfiloUsername(username);

        //controllo se username già esistente
        if(cliente == null) {
        	clienteDao.save(cliente);
            session.setAttribute("cliente", cliente);
            return true;
        }

        return false;
    }

}

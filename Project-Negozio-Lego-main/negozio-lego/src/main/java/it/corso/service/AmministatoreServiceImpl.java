package it.corso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.AmministratoreDao;
import it.corso.model.Amministratore;

import jakarta.servlet.http.HttpSession;

@Service
public class AmministatoreServiceImpl implements AmministratoreService {

	@Autowired
	private AmministratoreDao amministratoreDao;
	
	@Override
	public boolean controlloLogin(String username, String password, HttpSession session) {
		
		Amministratore admin = amministratoreDao.findByUsernameAndPassword(username, password);

		//controllo login 
		if(admin != null)
		{
			session.setAttribute("admin", admin); 
			return true; 
		}
		
		return false;
	}	
}

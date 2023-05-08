package it.corso.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import it.corso.model.Catalogo;
import jakarta.servlet.http.HttpSession;


public interface CatalogoService {

	void registraProdotto (String nome, String descrzione, MultipartFile immagine, double prezzo,int idCategoria);
	Catalogo getProdottoById(int id);
	List<Catalogo> getProdotti();
	void cancellaProdotto(Catalogo catalogo);
	
	void aggiungiAlCarrello(int id, HttpSession session);
}

package it.corso.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.corso.dao.CatalogoDao;
import it.corso.model.Catalogo;
import it.corso.model.Categoria;
import jakarta.servlet.http.HttpSession;

@Service
public class CatalogoServiceImpl implements CatalogoService {

	//inserire autowired
	@Autowired
	private CatalogoDao catalogoDao;
	
	@Autowired
	private CategoriaService categoriaService;
	
	
	@Override
	public void registraProdotto(String nome, String descrzione, MultipartFile immagine, double prezzo, int idCategoria) {
		
		Catalogo catalogo = new Catalogo();
		catalogo.setNome(nome);
		catalogo.setDescrizione(descrzione);
		if (immagine != null &&  !immagine.isEmpty()) {  //se l'immagine non è presente ed è valido come immagine allora:
				String tipo = immagine.getContentType(); //creiamo un variabile per fare in modo che viene caricato qualsiasi tipo di file 
				try {
				catalogo.setImmagine("data:" + tipo + ";base64," + Base64.getEncoder().encodeToString(immagine.getBytes())); //è una sorta d'intestazione 
				} catch (IOException e)
				{
					e.printStackTrace();
				}
		}	
		catalogo.setPrezzo(prezzo);
		Categoria categoria = categoriaService.getCategoriaById(idCategoria);
		catalogo.setCategoria(categoria);
		
		catalogoDao.save(catalogo);
	}

	@Override
	public Catalogo getProdottoById(int id) {
		
		return catalogoDao.findById(id).get();
	}

	@Override
	public List<Catalogo> getProdotti() {

		return (List<Catalogo>) catalogoDao.findAll();
	}

	@Override
	public void cancellaProdotto(Catalogo catalogo) {	
		catalogoDao.delete(catalogo);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void aggiungiAlCarrello(int id, HttpSession session) {
		
		Catalogo catalogo = getProdottoById(id);
		List<Catalogo> carrello = session.getAttribute("carrello") == null ? new ArrayList<>() : (List<Catalogo>) session.getAttribute("carrello");
		
		carrello.add(catalogo);
		session.setAttribute("carrello", carrello);
	}

	
}

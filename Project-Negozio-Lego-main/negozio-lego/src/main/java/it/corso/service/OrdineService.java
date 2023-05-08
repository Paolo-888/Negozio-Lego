package it.corso.service;

import java.util.List;

import it.corso.model.Catalogo;
import it.corso.model.Ordine;

public interface OrdineService {

	void registraOrdine(List<Catalogo> carrello,double totale, int idCliente);
	Ordine getOrdineById(int id);
	List<Ordine> geOrdine();
	void cancellaOrdine(Ordine ordine);
	
}

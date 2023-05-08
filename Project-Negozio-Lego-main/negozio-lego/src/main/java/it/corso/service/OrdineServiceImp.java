package it.corso.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.OrdineDao;
import it.corso.model.Catalogo;
import it.corso.model.Cliente;
import it.corso.model.Ordine;

@Service
public class OrdineServiceImp implements OrdineService {

	@Autowired
	private OrdineDao ordineDao;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CatalogoService catalogoService;
	
	@Override
	public void registraOrdine(List<Catalogo> carrello,double totale, int idCliente) {

		Ordine ordine = new Ordine();
		ordine.setData(LocalDate.now()); 
		
		Cliente cliente = clienteService.getClienteById(idCliente);
		ordine.setCliente(cliente);
		
		ordine.setImporto(totale);
		
		ordine.getCatalogo().clear();
		
		for (Catalogo c: carrello) {
			Catalogo catalogo = catalogoService.getProdottoById(c.getId());
			ordine.getCatalogo().add(catalogo);
		}
		ordineDao.save(ordine);	
	}

	@Override
	public Ordine getOrdineById(int id) {
	
		return ordineDao.findById(id).get();
	}

	@Override
	public List<Ordine> geOrdine() {
		return (List<Ordine>) ordineDao.findAll();
	}

	@Override
	public void cancellaOrdine(Ordine ordine) {
		
		//rimozione dell'ordine da cancellare dalla lista ordini dal cliente collegato (gli altri oridini restano)
		ordine.getCliente().getOrdini().remove(ordine);
		
		//pulizia lista prodotti dell'ordine da cancellare
		ordine.getCatalogo().clear();
		
		//cancellazione ordine 
		ordineDao.delete(ordine);

	}

}

package it.corso.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Catalogo;

public interface CatalogoDao extends CrudRepository<Catalogo, Integer> {

	List<Catalogo> findByCategoriaDescrizione(String descrizione);
}

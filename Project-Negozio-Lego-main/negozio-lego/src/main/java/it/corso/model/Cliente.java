package it.corso.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clienti")
public class Cliente {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nome")
	private String nome;
	@Column(name ="cognome")
	private String cognome;
	@Column(name ="indirizzo")
	private String indirizzo;
	@Column(name ="cap")
	private String cap;
	@Column(name ="localita")
	private String localita;
	@Column(name ="provincia")
	private String provincia;
	@Column(name ="telefono_cellulare")
	private String telefono_cellulare;
	@Column(name ="email")
	private String email;
	
	//relazione Tra Cliente e Profilo
	@OneToOne(cascade = CascadeType.ALL) 
	@JoinColumn(name = "id_profilo", referencedColumnName = "id") 
	private Profilo profilo;
	
	@OneToMany
	(
		mappedBy = "cliente", 
		cascade = CascadeType.REFRESH,
		fetch = FetchType.EAGER,
		orphanRemoval = true 
	)
	private List<Ordine> ordini = new ArrayList<> ();	
		
	//getter e setter 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getLocalita() {
		return localita;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getTelefono_cellulare() {
		return telefono_cellulare;
	}
	public void setTelefono_cellulare(String telefono_cellulare) {
		this.telefono_cellulare = telefono_cellulare;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Profilo getProfilo() {
		return profilo;
	}
	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}
	public List<Ordine> getOrdini() {
		return ordini;
	}
	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}
	
}

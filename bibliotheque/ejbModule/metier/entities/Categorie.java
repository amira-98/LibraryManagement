package metier.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity implementation class for Entity: Categorie
 *
 */
@Entity

public class Categorie implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_categorie;
	private String libelle;

	private static final long serialVersionUID = 1L;
@JsonIgnore
	@OneToMany (mappedBy="categorie",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Collection<Oeuvre> oeuv_cat;
	public Categorie() {
		super();
		this.oeuv_cat= Collections.emptySet();	
	
	}
	public Categorie(int id_categorie, String libelle) {
		super();
		this.id_categorie = id_categorie;
		this.libelle = libelle;
	}
	public int getId_categorie() {
		return id_categorie;
	}
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Collection<Oeuvre> getOeuv_cat() {
		return oeuv_cat;
	}
	public void setOeuv_cat(Collection<Oeuvre> oeuv_cat) {
		this.oeuv_cat = oeuv_cat;
	}

   
}

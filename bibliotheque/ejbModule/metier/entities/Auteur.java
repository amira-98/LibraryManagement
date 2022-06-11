package metier.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity implementation class for Entity: Auteur
 *
 */
@Entity
public class Auteur implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_auteur;
	private String nom;
	private String prenom;
	
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@OneToMany (mappedBy="auteur",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Collection<Oeuvre> auteur;	
	

	public Auteur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}



	public int getId_auteur() {
		return id_auteur;
	}



	public void setId_auteur(int id_auteur) {
		this.id_auteur = id_auteur;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}




	public Collection<Oeuvre> getAuteur() {
		return auteur;
	}



	public void setAuteur(Collection<Oeuvre> auteur) {
		this.auteur = auteur;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Auteur() {
		super();
	}
   
}

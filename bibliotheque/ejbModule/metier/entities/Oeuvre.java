package metier.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import metier.entities.Livre;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: Oeuvre
 *
 */
@Entity

public class Oeuvre implements Serializable {
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_oeuvre;
	private String titre;
	private int nb_dvd;
	public Oeuvre(String titre, int nb_dvd, int nb_sup_papier, Categorie categorie, Auteur auteur) {
		super();
		this.titre = titre;
		this.nb_dvd = nb_dvd;
		this.nb_sup_papier = nb_sup_papier;
	

	}

	public Oeuvre(String titre, int nb_dvd, int nb_sup_papier) {
		super();
		this.titre = titre;
		this.nb_dvd = nb_dvd;
		this.nb_sup_papier = nb_sup_papier;
	}

	private int nb_sup_papier;
	

	public Oeuvre(int id_oeuvre, String titre, int nb_dvd, int nb_sup_papier) {
		super();
		this.id_oeuvre = id_oeuvre;
		this.titre = titre;
		this.nb_dvd = nb_dvd;
		this.nb_sup_papier = nb_sup_papier;
	}

	public Oeuvre(int id_oeuvre, String titre, int nb_dvd, int nb_sup_papier, Categorie categorie, Auteur auteur) {
		super();
		this.id_oeuvre = id_oeuvre;
		this.titre = titre;
		this.nb_dvd = nb_dvd;
		this.nb_sup_papier = nb_sup_papier;
		
	}
	
	@ManyToOne
	@JoinColumn(name="oeuvr_cat")
	private Categorie categorie;
	
	@ManyToOne
	@JoinColumn(name="id_aut")
	private Auteur auteur;

	public Collection<Livre> getL_livre() {
		return l_livre;
	}

	public void setL_livre(Collection<Livre> l_livre) {
		this.l_livre = l_livre;
	}
	public int getNbLivreDispo(){
		int sum = 0;
		for(Livre l : l_livre){
			sum+=l.getQte();
		}
		return sum;
	}

	@JsonIgnore
	@OneToMany( mappedBy ="oeuvre",fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	private Collection <Livre> l_livre;
	public int getId_oeuvre() {
		return id_oeuvre;
	}

	public void setId_oeuvre(int id_oeuvre) {
		this.id_oeuvre = id_oeuvre;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNb_dvd() {
		return nb_dvd;
	}

	public void setNb_dvd(int nb_dvd) {
		this.nb_dvd = nb_dvd;
	}

	public int getNb_sup_papier() {
		return nb_sup_papier;
	}

	public void setNb_sup_papier(int nb_sup_papier) {
		this.nb_sup_papier = nb_sup_papier;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

	public Oeuvre() {
		super();
	}
   
}

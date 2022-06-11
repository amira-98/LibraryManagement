package metier.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



/**
 * Entity implementation class for Entity: Adherent
 *
 */

@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Adherent implements Serializable {

	
	public Adherent(String nom, String prenom) {
		
		super();
		this.nom = nom;
		this.prenom = prenom;
		
		
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	public Adherent(int id, String nom, String prenom, String adresse, String email, int tel, int nb_emprunt_encours,
			boolean blacklist) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.tel = tel;
		this.nb_emprunt_encours = nb_emprunt_encours;
		this.blacklist = blacklist;
	}

	private String nom;
	private String prenom;
	private String adresse;
	private String email; 
	private int tel;
	private int nb_emprunt_encours;
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="adherent")
	private Compte compte;
	@JsonIgnore
	@OneToMany( mappedBy ="adherent",fetch = FetchType.EAGER)
	private Collection <Emprunt> l_empr;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public int getNb_emprunt_encours() {
		return nb_emprunt_encours;
	}

	public void setNb_emprunt_encours(int nb_emprunt_encours) {
		this.nb_emprunt_encours = nb_emprunt_encours;
	}

	public boolean isBlacklist() {
		return blacklist;
	}

	public void setBlacklist(boolean blacklist) {
		this.blacklist = blacklist;
	}

	private boolean blacklist;
	private boolean etu;
	public boolean isEtu() {
		return etu;
	}

	public void setEtu(boolean etu) {
		this.etu = etu;
	}

	public Adherent() {
		super();
	}
	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public int getAnnee_inscrip() {
		return annee_inscrip;
	}

	public void setAnnee_inscrip(int annee_inscrip) {
		this.annee_inscrip = annee_inscrip;
	}

	private String filiere;
	private int annee_inscrip;
	private String grade;
	private String departement;
	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Collection<Emprunt> getL_empr() {
		return l_empr;
	}

	public void setL_empr(Collection<Emprunt> l_empr) {
		this.l_empr = l_empr;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}
}

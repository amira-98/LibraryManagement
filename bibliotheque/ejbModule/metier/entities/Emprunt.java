package metier.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: Eumprunt
 *
 */
@Entity

public class Emprunt implements Serializable {
    @EmbeddedId
    private Pk_emprunt id;
	
	
	public Date getDate_retour_effective() {
		return date_retour_effective;
	}
	public void setDate_retour_effective(Date date_retour_effective) {
		this.date_retour_effective = date_retour_effective;
	}
	public Date getDate_retour_theorique() {
		return date_retour_theorique;
	}
	public void setDate_retour_theorique(Date date_retour_theorique) {
		this.date_retour_theorique = date_retour_theorique;
	}
	public int getNb_avertissement() {
		return nb_avertissement;
	}
	public void setNb_avertissement(int nb_avertissement) {
		this.nb_avertissement = nb_avertissement;
	}
	public String getType_support() {
		return type_support;
	}
	public void setType_support(String type_support) {
		this.type_support = type_support;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Temporal(TemporalType.DATE)

	private Date date_retour_effective;
	public Adherent getAdherent() {
		return adherent;
	}
	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}
	public Livre getLivre() {
		return livre;
	}
	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	@Temporal(TemporalType.DATE)

	private Date date_retour_theorique;
	private int nb_avertissement;
	private String type_support;
	public Pk_emprunt getId() {
		return id;
	}
	public void setId(Pk_emprunt id) {
		this.id = id;
	}
	private static final long serialVersionUID = 1L;
@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	
	@JoinColumn(name="id_adherent",updatable=false,insertable=false)
	private Adherent adherent;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	
	@JoinColumn(name="id_livre",updatable=false,insertable=false)
	private Livre livre;
	public Emprunt() {
		super();
	}
   
}

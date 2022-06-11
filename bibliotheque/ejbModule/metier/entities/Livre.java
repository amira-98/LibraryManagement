package metier.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entity implementation class for Entity: Livre
 *
 */
@Entity

public class Livre implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_livre;
	public Livre(int id_livre, Date date_edition, int etat, double prix, int qte) {
		super();
		this.id_livre = id_livre;
		this.date_edition = date_edition;
		this.etat = etat;
	    this.qte = qte;
	}
	@Temporal(TemporalType.DATE)
	private Date date_edition;
	private int etat;
	
	public int getId_livre() {
		return id_livre;
	}
	public void setId_livre(int id_livre) {
		this.id_livre = id_livre;
	}
	public Date getDate_edition() {
		return date_edition;
	}
	public void setDate_edition(Date date_edition) {
		this.date_edition = date_edition;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public Collection<Emprunt> getL_empr() {
		return l_empr;
	}
	public void setL_empr(Collection<Emprunt> l_empr) {
		this.l_empr = l_empr;
	}
	public Oeuvre getOeuvre() {
		return oeuvre;
	}
	public void setOeuvre(Oeuvre oeuvre) {
		this.oeuvre = oeuvre;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private int qte;
	@JsonIgnore
	@OneToMany( mappedBy ="livre")
	private Collection <Emprunt> l_empr;

	
	private static final long serialVersionUID = 1L;

	public Livre() {
		super();
	}
	@ManyToOne
	@JoinColumn(name="Id_oeuvre")
	private Oeuvre oeuvre;

}

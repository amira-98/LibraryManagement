package metier.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
@Embeddable

public class Pk_emprunt implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_adherent;
	private int id_livre;
private Date date_emprunt;
	@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((date_emprunt == null) ? 0 : date_emprunt.hashCode());
	result = prime * result + id_adherent;
	result = prime * result + id_livre;
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Pk_emprunt other = (Pk_emprunt) obj;
	if (date_emprunt == null) {
		if (other.date_emprunt != null)
			return false;
	} else if (!date_emprunt.equals(other.date_emprunt))
		return false;
	if (id_adherent != other.id_adherent)
		return false;
	if (id_livre != other.id_livre)
		return false;
	return true;
}
	public Date getDate_emprunt() {
	return date_emprunt;
}
public void setDate_emprunt(Date date_emprunt) {
	this.date_emprunt = date_emprunt;
}
	public int getId_adherent() {
		return id_adherent;
	}
	public void setId_adherent(int id_adherent) {
		this.id_adherent = id_adherent;
	}
	public int getId_livre() {
		return id_livre;
	}
	public void setId_livre(int id_livre) {
		this.id_livre = id_livre;
	}
}

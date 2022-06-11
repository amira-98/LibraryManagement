package metier.sessions.service;

import javax.ejb.Local;

import metier.entities.*;

@Local
public interface EmpruntLocal {

	Emprunt ajouterEmprunt(Emprunt empr);
	
}

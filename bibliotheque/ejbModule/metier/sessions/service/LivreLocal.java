package metier.sessions.service;

import javax.ejb.Local;

import metier.entities.*;

@Local
public interface LivreLocal {

	Livre update(Livre l);
	Livre findById(int id);
	
}

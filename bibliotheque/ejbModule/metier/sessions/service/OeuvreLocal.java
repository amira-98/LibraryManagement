package metier.sessions.service;

import java.util.List;

import javax.ejb.Local;

import metier.entities.*;

@Local
public interface OeuvreLocal {

	Oeuvre save(Oeuvre oeuvre);
	Oeuvre update(Oeuvre oeuvre);
	void delete(int id);
	List<Oeuvre> findAll();
	Oeuvre findOne(int id);
	Oeuvre test();
	
}

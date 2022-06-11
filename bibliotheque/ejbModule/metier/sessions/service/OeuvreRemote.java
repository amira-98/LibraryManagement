package metier.sessions.service;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.*;

@Remote
public interface OeuvreRemote {

	Oeuvre save(Oeuvre oeuvre);
	Oeuvre update(Oeuvre oeuvre);
	void delete(int id);
	List<Oeuvre> findAll();
	Oeuvre findOne(int id);
	Oeuvre test();
	
}

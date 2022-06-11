package metier.sessions.service;

import java.util.List;

import javax.ejb.Local;

import metier.entities.*;


@Local
public interface AdherentLocal {

	Adherent save(Adherent adherant);
	Adherent update(Adherent adherant);
	void delete(int id);
	List<Adherent> getList();
	Adherent search(String value);
	Adherent findByLoginAndPassword(String login,String password);
	Adherent findById(int id);
}

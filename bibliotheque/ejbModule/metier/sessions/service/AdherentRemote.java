package metier.sessions.service;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.*;

@Remote
public interface AdherentRemote {

	Adherent save(Adherent adherant);
	Adherent update(Adherent adherant);
	void delete(int id);
	List<Adherent> getList();
	Adherent search(String value);
}

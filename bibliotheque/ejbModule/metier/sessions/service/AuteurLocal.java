package metier.sessions.service;

import java.util.List;

import javax.ejb.Local;

import metier.entities.*;

@Local
public interface AuteurLocal {

	public List<Auteur> getList(); 
	
}

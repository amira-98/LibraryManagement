package metier.sessions.service;

import java.util.List;

import javax.ejb.Remote;

import metier.entities.*;

@Remote
public interface AuteurRemote {
	 List<Auteur> getList(); 
}

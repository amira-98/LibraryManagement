package metier.sessions.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import metier.entities.*;
import metier.sessions.service.*;

@Stateless(name = "AuteurService")
public class AuteurService implements AuteurRemote, AuteurLocal {

	@PersistenceContext(name = "bibliotheque")
	private EntityManager entityManager;

	@Override
	public List<Auteur> getList() {
		
		return entityManager.createQuery("select a from Auteur a").getResultList();
	}

}

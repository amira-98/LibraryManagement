package metier.sessions.service.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import metier.entities.*;
import metier.sessions.service.*;

@Stateless(name="LivrService")
public class LivreService implements LivreLocal{
	
	@PersistenceContext(name = "bibliotheque")
	private EntityManager entityManager;

	@Override
	public Livre update(Livre l) {
		entityManager.merge(l);
		return l;
	}

	@Override
	public Livre findById(int id) {
		return entityManager.find(Livre.class, id);
	}

}

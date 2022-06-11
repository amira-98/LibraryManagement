package metier.sessions.service.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import metier.entities.*;
import metier.sessions.service.*;
@Stateless(name="EmpruntLocal")
public class EmpruntService implements EmpruntLocal{

	@PersistenceContext(name = "bibliotheque")
	private EntityManager em;
	
	@Override
	public Emprunt ajouterEmprunt(Emprunt empr) {
		Livre l=em.find(Livre.class, empr.getId().getId_livre());
		l.setQte((l.getQte())-1);
		em.persist(empr);
		
		return empr;
	}

}

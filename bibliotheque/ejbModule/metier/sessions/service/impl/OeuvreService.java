package metier.sessions.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import metier.entities.*;
import metier.sessions.service.*;

@Stateless(name = "OeuvreService")
public class OeuvreService implements OeuvreRemote, OeuvreLocal {

	@PersistenceContext(name = "bibliotheque")
	private EntityManager entityManager;

	@Override
	public Oeuvre save(Oeuvre oeuvre) {
		entityManager.persist(oeuvre);
		return oeuvre;
	}

	@Override
	public Oeuvre update(Oeuvre oeuvre) {
		entityManager.merge(oeuvre);
		return oeuvre;
	}

	@Override
	public void delete(int id) {
		Oeuvre oeuvre = entityManager.find(Oeuvre.class, id);
		if (oeuvre != null)
			entityManager.remove(oeuvre);

	}

	@Override
	public List<Oeuvre> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select o from Oeuvre o").getResultList();
	}
	@Override
	public Oeuvre findOne(int id){
		Oeuvre oeuvre = entityManager.find(Oeuvre.class, id);
		return oeuvre;
	}

	@Override
	public Oeuvre test() {
		Oeuvre oeuvre = new Oeuvre();
		oeuvre.setId_oeuvre(5);
		oeuvre.setTitre("Titre Test");
		return oeuvre;
	}

}

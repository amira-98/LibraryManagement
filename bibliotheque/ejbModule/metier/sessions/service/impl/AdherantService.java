package metier.sessions.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.*;
import metier.sessions.service.*;


@Stateless(name = "AdherentService")
public class AdherantService implements AdherentLocal, AdherentRemote {

	@PersistenceContext(name = "bibliotheque")
	private EntityManager em;

	@Override
	public Adherent save(Adherent adherant) {
		em.persist(adherant);
		return adherant;
	}

	@Override
	public Adherent update(Adherent adherant) {
		em.merge(adherant);
		return adherant;
	}

	@Override
	public void delete(int id) {
		Adherent ad = em.find(Adherent.class, id);
		if (ad != null) {
			em.remove(ad);
		}

	}

	@Override
	public List<Adherent> getList() {
		// TODO Auto-generated method stub
		return em.createQuery("select a from Adherent a").getResultList();
	}

	@Override
	public Adherent search(String value) {
		try{
			Query query = em.createQuery("select a from Adherent a where a.email=:vl or a.tel=:vl");
			query.setParameter("vl", value);

			return (Adherent) query.getSingleResult();
		}catch(Exception e){
			return null;
		}
		
	}
	
	@Override
	public Adherent findByLoginAndPassword(String login,String password){
		try{
			Query query = em.createQuery("select a from Adherent a where a.compte.login=:lg and a.compte.mot_de_passe=:ps");
			query.setParameter("lg", login);
			query.setParameter("ps", password);
			return (Adherent) query.getSingleResult();	
		}catch(Exception e){
			return null;
		}
		
	}

	@Override
	public Adherent findById(int id) {
		return em.find(Adherent.class, id);
	}

}

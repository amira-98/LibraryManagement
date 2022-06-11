package metier.sessions.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import metier.entities.*;
import metier.sessions.service.*;

@Stateless(name="CategServ")
public class CategorieService implements CategorieLocal,CategorieRemote{

	@PersistenceContext(name = "bibliotheque")
	private EntityManager entityManager;
	
	@Override
	public List<Categorie> getList() {
		return entityManager.createQuery("select c from Categorie c").getResultList();
	}

}

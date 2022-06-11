package metier.sessions.service;

import java.util.List;

import javax.ejb.Local;

import metier.entities.*;

@Local
public interface CategorieLocal {

	List<Categorie> getList();
	
}

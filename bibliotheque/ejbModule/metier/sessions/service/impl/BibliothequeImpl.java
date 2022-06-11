package metier.sessions.service.impl;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.*;
import metier.sessions.service.BibliothequeLocale;



@Stateless(name="BIBLIOTHEQUE")
public class BibliothequeImpl implements BibliothequeLocale{
@PersistenceContext(name="bibliotheque")
private EntityManager em;
@Override
public List<Compte> consulterCompte(String login,String mot_de_passe)
{ Query req=(Query) em.createQuery("select c from Compte c where c.login like :na and c.mot_de_passe like :nb");

req.setParameter("na",login);
req.setParameter("nb",mot_de_passe );
return req.getResultList();
	}

@Override
public void addEmprunt(Emprunt e) {
	em.persist(e);
					
}




@Override
public Adherent consulterAdherent(int numa) {
	Adherent adherent=em.find(Adherent.class, numa);
	if(adherent==null) throw new RuntimeException("Compte "
			+numa+"n'existe pas");
	
	return adherent;}

@Override
public List<Oeuvre> consulterOeuvresParMotC(String na) {
	 Query req=(Query) em.createQuery("select a from Oeuvre a where a.titre like :na");
		
	 req.setParameter("na","%"+na+"%" );
	 return req.getResultList();}
@Override
public Auteur consulterAuteur(int idauteur) { 
	Auteur auteur=em.find(Auteur.class, idauteur);
	if(auteur==null) throw new RuntimeException("Compte "
			+idauteur+"n'existe pas");
	
	return auteur;
	
	 }

											
@Override
public List<Auteur> consulterAuteurs() {
	Query req=(Query) em.createQuery("select a from Auteur a");
return req.getResultList();	}
@Override
public Categorie consulterCategorie(int idcategorie) {
	Categorie categorie=em.find(Categorie.class, idcategorie);
	if(categorie==null) throw new RuntimeException("Compte "
			+idcategorie+"n'existe pas");
	
	return categorie;}
@Override
public List<Categorie> consulterCategories() {
		Query req=(Query) em.createQuery("select c from Categorie c ");
		return req.getResultList();	
		}
	
	
	@Override
		public List<Emprunt> consulterEmpruntparadherent(int id_adherent) {
		Query req=em.createQuery("select e from Emprunt e where e.adherent.id =:x ");
		req.setParameter("x", id_adherent);
			return req.getResultList();	}
	
@Override
public Livre consulterLivre(int idlivre) {
	Livre livre=em.find(Livre.class, idlivre);
	if(livre==null) throw new RuntimeException("Compte "
			+idlivre+"n'existe pas");
	
	return livre;}
@Override
public List<Livre> consulterLivres() {
	Query req=(Query) em.createQuery("select l from Livre l");
	return req.getResultList();		}@Override
	public List<Livre> consulterLivresParOeuvre(int id_Oeuvre) {

		Query req=em.createQuery("select l from Livre l where l.oeuvre.id_oeuvre=:x ");
		req.setParameter("x", id_Oeuvre);
			return req.getResultList();
				}@Override
	public Oeuvre consulterOeuvre(int idoeuvre) {
					Oeuvre oeuvre=em.find(Oeuvre.class, idoeuvre);
					if(oeuvre==null) throw new RuntimeException("Compte "
							+idoeuvre+"n'existe pas");
					
					return oeuvre;
}
@Override
	public List<Oeuvre> consulterOeuvreParAuteur(int id_auteur) {
	Query req=em.createQuery("select o from Oeuvre o where o.auteur.id_auteur=:x ");
	req.setParameter("x", id_auteur);
		return req.getResultList();
	}
public  List<Livre> cousulterlivreparidoeuvre(int id_oeuvre) {
	Query req=em.createQuery("select l from Livre l where l.oeuvre=:x ");
	req.setParameter("x", id_oeuvre);
	return (List<Livre>)req.getResultList();
}
@Override
public List<Oeuvre> consulterOeuvreParAutCat(int id_auteur,int id_categorie) {
Query req=em.createQuery("select o from Oeuvre o where o.auteur.id_auteur=:x and o.categorie.id_categorie=:y");
req.setParameter("x", id_auteur);
req.setParameter("y", id_categorie);

	return req.getResultList();
}
@Override
public List<Oeuvre> consulterOeuvreParCatmc(int id_categorie,String mc) 
{
Query req=em.createQuery("select o from Oeuvre o where  o.categorie.id_categorie=:y and o.titre like :z");
req.setParameter("y", id_categorie);
req.setParameter("z", "%"+mc+"%");
	return req.getResultList();
}
@Override
public List<Oeuvre> consulterOeuvreParAutmc(int id_auteur,String mc) {
Query req=em.createQuery("select o from Oeuvre o where o.auteur.id_auteur=:x and o.titre like :z");
req.setParameter("x", id_auteur);
req.setParameter("z", "%"+mc+"%");
	return req.getResultList();
}
@Override
public List<Oeuvre> consulterOeuvreParAutCatmc(int id_auteur,int id_categorie,String mc) {
Query req=em.createQuery("select o from Oeuvre o where o.auteur.id_auteur=:x and o.categorie.id_categorie=:y and o.titre like :z");
req.setParameter("x", id_auteur);
req.setParameter("y", id_categorie);
req.setParameter("z", "%"+mc+"%");
	return req.getResultList();
}
@Override
	public List<Oeuvre> consulterOeuvreParCategorie(int id_Categorie) {
	Query req=em.createQuery("select o from Oeuvre o where o.categorie.id_categorie=:x ");
	req.setParameter("x", id_Categorie);
		return req.getResultList();
	}
@Override
	public List<Oeuvre> consulterOeuvres() {
	Query req=(Query) em.createQuery("select o from Oeuvre o");
return req.getResultList();	
	}
@Override
	public List<Oeuvre> consulterOeuvrespartitre(String titre) {
	Query req=em.createQuery("select o from Oeuvre o where o.titre like :x ");
	req.setParameter("x",titre);
		return req.getResultList();
	}


	
@Override
	public Emprunt counsulterEmprunt(Pk_emprunt emp) {
	
	Emprunt empr=em.find(Emprunt.class, emp);
	if(empr==null) throw new RuntimeException("Compte "
			+emp+"n'existe pas");
	
	return empr;
	}
@Override
public void AjouterEmprunt( int idLivre, int idAdherent,Date date_emprunt) {
	Pk_emprunt pk=new Pk_emprunt();
	pk.setId_livre(idLivre);
pk.setId_adherent(idAdherent);
pk.setDate_emprunt(date_emprunt);

Emprunt e=new Emprunt();
Calendar c = Calendar.getInstance();
c.setTime(date_emprunt);
c.add(Calendar.MONTH, 1);
e.setDate_retour_theorique(c.getTime());
e.setId(pk);
em.persist(e);

}


}

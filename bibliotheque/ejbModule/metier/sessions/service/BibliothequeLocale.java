package metier.sessions.service;


import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import metier.entities.*;




@Local
public interface BibliothequeLocale {

	//consultation d'une entité précise
	public List<Oeuvre> consulterOeuvreParAutmc(int id_auteur,String mc) ;
	public List<Oeuvre> consulterOeuvreParCatmc(int id_categorie,String mc) ;
	public List<Oeuvre> consulterOeuvreParAutCat(int id_auteur,int id_categorie) ;
	public List<Oeuvre> consulterOeuvreParAutCatmc(int id_auteur,int id_categorie,String mc);
	public List<Livre> cousulterlivreparidoeuvre(int id_oeuvre);
	public Oeuvre consulterOeuvre(int idoeuvre);
	public Livre consulterLivre(int idlivre);
	public Auteur consulterAuteur(int idauteur);
	public Categorie consulterCategorie(int idcategorie);
public List<Compte> consulterCompte(String login,String mot_de_passe);
	public Emprunt counsulterEmprunt(Pk_emprunt emp);
	public void addEmprunt(Emprunt e );
	//afficher la liste des occurences d'une entité
	public Adherent consulterAdherent(int numa);
	public List<Livre> consulterLivres();
	public List<Oeuvre> consulterOeuvres();
	public List<Auteur> consulterAuteurs();
	public List<Categorie> consulterCategories();

	//filtrer une liste par un critére
	
	public List<Oeuvre> consulterOeuvresParMotC(String mc);
	public List<Oeuvre> consulterOeuvrespartitre(String titre);
	public List<Oeuvre> consulterOeuvreParAuteur(int id_auteur);
	public List<Oeuvre> consulterOeuvreParCategorie(int id_Categorie);
	public List<Livre> consulterLivresParOeuvre(int id_Oeuvre);

	public List<Emprunt> consulterEmpruntparadherent(int id_adherent);
	public void AjouterEmprunt(int idLivre,int idAdherent,Date date_emprunt);
	
}

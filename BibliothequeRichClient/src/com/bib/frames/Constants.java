package com.bib.frames;

import metier.sessions.service.AdherentRemote;
import metier.sessions.service.AuteurRemote;
import metier.sessions.service.CategorieRemote;
import metier.sessions.service.OeuvreRemote;

public class Constants {

	public static final String APP_NAME = "bib";
	public static final String MODULE_NAME = "bibliotheque";
	public static final String DISTINCT_NAME = "";
	public static final String OEUVRE_SERVICE_NAME="OeuvreService";
	public static final String CATEGORIE_SERVICE_NAME="CategServ";
	public static final String AUTEUR_SERVICE_NAME="AuteurService";
	public static final String ADHERENT_SERVICE_NAME="AdherentService";
	public static final String OEUVRE_CLASS = OeuvreRemote.class.getName();
	public static final String AUTEUR_CLASS=AuteurRemote.class.getName();
	public static final String CATEGORIE_CLASS=CategorieRemote.class.getName();
	public static final String ADHERENT_CLASS=AdherentRemote.class.getName();
	
}

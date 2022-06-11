package com.bib.frames;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.bib.desktop.factory.MyContext;
import metier.entities.*;

import metier.sessions.service.AuteurRemote;
import metier.sessions.service.CategorieRemote;
import metier.sessions.service.OeuvreRemote;

public class RechercherOeuvre extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Context context;
	private OeuvreRemote oeuvreRemote;
	private CategorieRemote categorieRemote;
	private AuteurRemote auteurRemote;
	
	private JLabel idLabel = new JLabel("Identifiant");
	private JTextField idField = new JTextField();
	private JButton search = new JButton("Rechercher");
	
	private JTextField titleField = new JTextField();
	private JLabel titleLabel = new JLabel("Title : ");
	private JTextField nbDVDField = new JTextField();
	private JLabel nbDVDLabel = new JLabel("Nb DVD : ");
	private JTextField nbSupPapierField = new JTextField();
	private JLabel nbSupPapierLabel = new JLabel("nb Superieur de Papier : ");
	private JLabel categorieLabel = new JLabel("Categorie : ");
	private JComboBox<String> categorieCombo = new JComboBox<String>();
	private JLabel auteurLabel = new JLabel("Auteur : ");
	private JComboBox<String> auteurCombo = new JComboBox<String>();
	private JButton modifier = new JButton("Modifier");
	private JButton delete = new JButton("Supprimer");
	
	private JTextField editionField = new JTextField();
	private JLabel editionLabel = new JLabel("Date Edition : ");
	private JLabel nbLivreLabel = new JLabel("nombre : ");
	private JTextField nbLivreField = new JTextField();
	private JButton ajouterEdition = new JButton("Ajouter Edition");
	private JLabel nbLivre = new JLabel("0 editions ajoutées");
	
	private Collection<Livre> livres = new ArrayList<>();

	private void init() throws NamingException {
		context = MyContext.getInstance();

		oeuvreRemote = (OeuvreRemote) context.lookup("ejb:" + Constants.APP_NAME + "/" + Constants.MODULE_NAME + "/"
				+ Constants.DISTINCT_NAME + "/" + Constants.OEUVRE_SERVICE_NAME + "!" + Constants.OEUVRE_CLASS);

		categorieRemote = (CategorieRemote) context
				.lookup("ejb:" + Constants.APP_NAME + "/" + Constants.MODULE_NAME + "/" + Constants.DISTINCT_NAME + "/"
						+ Constants.CATEGORIE_SERVICE_NAME + "!" + Constants.CATEGORIE_CLASS);

		auteurRemote = (AuteurRemote) context.lookup("ejb:" + Constants.APP_NAME + "/" + Constants.MODULE_NAME + "/"
				+ Constants.DISTINCT_NAME + "/" + Constants.AUTEUR_SERVICE_NAME + "!" + Constants.AUTEUR_CLASS);
	}
	
	public RechercherOeuvre(){
		try {
			init();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(RechercherOeuvre.this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		CommonPartsLoader.init(this);
		CommonPartsLoader.loadMenu(this);
		
		setLayout(null);
		
		idLabel.setBounds(50, 20, 180, 30);
		add(idLabel);
		idField.setBounds(280, 20, 150, 30);
		add(idField);
		search.setBounds(480, 20, 150, 30);
		add(search);
		search.addActionListener((event)->{
			int id = Integer.parseInt(idField.getText());
			Oeuvre o = oeuvreRemote.findOne(id);
			if(o==null){
				JOptionPane.showMessageDialog(RechercherOeuvre.this, "Identifiant invalide !","Erreur",JOptionPane.ERROR_MESSAGE);
				modifier.setEnabled(false);
				return;
			}
			modifier.setEnabled(true);
			delete.setEnabled(true);
			titleField.setText(o.getTitre());
			nbDVDField.setText(String.valueOf(o.getNb_dvd()));
			nbSupPapierField.setText(String.valueOf(o.getNb_sup_papier()));
			auteurCombo.setSelectedItem(o.getAuteur().getId_auteur()+"-"+o.getAuteur().getNom()+" "+o.getAuteur().getPrenom());
			categorieCombo.setSelectedItem(o.getCategorie().getId_categorie()+"-"+o.getCategorie().getLibelle());
			this.livres = o.getL_livre();
			nbLivre.setText(livres.size() + " editions ajoutées");
			
			
			
		});

		titleLabel.setBounds(50, 100, 180, 30);
		add(titleLabel);
		titleField.setBounds(280, 100, 150, 30);
		add(titleField);

		nbDVDLabel.setBounds(50, 150, 120, 30);
		add(nbDVDLabel);
		nbDVDField.setBounds(280, 150, 150, 30);
		add(nbDVDField);

		nbSupPapierLabel.setBounds(50, 200, 180, 30);
		add(nbSupPapierLabel);
		nbSupPapierField.setBounds(280, 200, 100, 30);
		add(nbSupPapierField);

		auteurLabel.setBounds(50, 250, 120, 30);
		add(auteurLabel);
		auteurCombo.setBounds(280, 250, 150, 30);
		add(auteurCombo);

		categorieLabel.setBounds(50, 300, 120, 30);
		add(categorieLabel);
		categorieCombo.setBounds(280, 300, 150, 30);
		add(categorieCombo);
		
		loadAuteurs();
		loadCategories();
		
		nbLivreLabel.setBounds(20,370,120,30);
		add(nbLivreLabel);
		nbLivreField.setBounds(100, 370, 60, 30);
		add(nbLivreField);
		
		editionLabel.setBounds(230, 370, 120, 30);
		add(editionLabel);
		editionField.setBounds(420, 370, 120, 30);
		add(editionField);
		
		ajouterEdition.setBounds(550, 370, 180, 30);
		add(ajouterEdition);
		ajouterEdition.addActionListener((event) -> {
			if (!nbLivreField.getText().matches("[0-9]*")||nbLivreField.getText().equals("")) {
				JOptionPane.showMessageDialog(RechercherOeuvre.this, "Veuillez entrer un nombre de livres valide !","Erreur",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (editionField.getText().equals("")) {
				JOptionPane.showMessageDialog(RechercherOeuvre.this, "Veuillez entrer une date valide !","Erreur",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			for(Livre l : livres){
				if(l.getDate_edition().equals(this.parseDate(editionField.getText()))){
					JOptionPane.showMessageDialog(RechercherOeuvre.this, "Edition Existe deja !","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			Livre l = new Livre();
			l.setEtat(1);
			l.setDate_edition(this.parseDate(editionField.getText()));
			l.setQte(Integer.parseInt(nbLivreField.getText()));
			livres.add(l);
			nbLivre.setText(livres.size()+" editions ajoutées");
			
		});
		
		nbLivre.setBounds(30,440,300,30);
		add(nbLivre);

		
		modifier.setBounds(50, 520, 120, 30);
		add(modifier);
		modifier.setEnabled(false);
		modifier.addActionListener((event)->{
			if(!nbDVDField.getText().matches("[0-9]*")||nbDVDField.getText().equals("")){
				JOptionPane.showMessageDialog(RechercherOeuvre.this, "Veuillez entrer un nombre de DVD valide !");
				return;
			}
			if(!nbSupPapierField.getText().matches("[0-9]*")||nbSupPapierField.getText().equals("")){
				JOptionPane.showMessageDialog(RechercherOeuvre.this, "Veuillez entrer un nombre de papier valide !");
				return;
			}
			
			modifierOeuvre();
			JOptionPane.showMessageDialog(RechercherOeuvre.this, "Oeuvre bien modifiée","Success",JOptionPane.INFORMATION_MESSAGE);
		});
		
		delete.setBounds(250, 520, 120, 30);
		add(delete);
		delete.setEnabled(false);
		delete.addActionListener((event)->{
			int res = JOptionPane.showConfirmDialog(RechercherOeuvre.this, "Etes Vous sÃ»re de vouloir modifier cet oeuvre ?","Verification",
					JOptionPane.OK_CANCEL_OPTION);
			if(res==0){
				oeuvreRemote.delete(Integer.parseInt(idField.getText()));
				modifier.setEnabled(false);
				delete.setEnabled(false);
			}
		});
	}
	
	private void modifierOeuvre(){
		Oeuvre oeuvre = new Oeuvre();
		oeuvre.setId_oeuvre(Integer.parseInt(idField.getText()));
		oeuvre.setTitre(titleField.getText());
		oeuvre.setNb_dvd(Integer.parseInt(nbDVDField.getText()));
		oeuvre.setNb_sup_papier(Integer.parseInt(nbSupPapierField.getText()));
		StringTokenizer stz1 = new StringTokenizer(auteurCombo.getSelectedItem().toString(),"-");
		Auteur auteur = new Auteur();
		auteur.setId_auteur(Integer.parseInt(stz1.nextToken()));
		oeuvre.setAuteur(auteur);
		Categorie cat = new Categorie();
		StringTokenizer stz2 = new StringTokenizer(categorieCombo.getSelectedItem().toString(),"-");
		cat.setId_categorie(Integer.parseInt(stz2.nextToken()));
		oeuvre.setCategorie(cat);
		oeuvre.setL_livre(this.livres);
		oeuvreRemote.update(oeuvre);
	}
	
	private void loadAuteurs() {
		List<Auteur> auteurs = auteurRemote.getList();
		for(Auteur a:auteurs){
			auteurCombo.addItem(a.getId_auteur()+"-"+a.getNom()+" " + a.getPrenom());
			
		}
	}
	
	private void loadCategories(){
		List<Categorie> categories = categorieRemote.getList();
		for(Categorie c : categories){
			categorieCombo.addItem(c.getId_categorie()+"-"+c.getLibelle());
		}
	}	
	
	private  Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }

}

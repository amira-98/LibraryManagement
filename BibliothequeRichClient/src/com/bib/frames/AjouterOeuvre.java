package com.bib.frames;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class AjouterOeuvre extends JFrame {

	private static final long serialVersionUID = 1L;
	private Context context;
	private OeuvreRemote oeuvreRemote;
	private CategorieRemote categorieRemote;
	private AuteurRemote auteurRemote;

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
	private JButton ajouter = new JButton("Enregister");

	private JTextField editionField = new JTextField();
	private JLabel editionLabel = new JLabel("Date Edition : ");
	private JLabel nbLivreLabel = new JLabel("nombre : ");
	private JTextField nbLivreField = new JTextField();
	private JButton ajouterEdition = new JButton("Ajouter Edition");
	
	private JLabel nbLivre = new JLabel("0 editions ajoutées");

	private List<Livre> livres = new ArrayList<>();

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

	public AjouterOeuvre() {
		try {
			init();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(AjouterOeuvre.this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		CommonPartsLoader.init(this);
		CommonPartsLoader.loadMenu(this);

		setLayout(null);

		titleLabel.setBounds(50, 50, 180, 30);
		add(titleLabel);
		titleField.setBounds(280, 50, 150, 30);
		add(titleField);

		nbDVDLabel.setBounds(50, 100, 120, 30);
		add(nbDVDLabel);
		nbDVDField.setBounds(280, 100, 150, 30);
		add(nbDVDField);

		nbSupPapierLabel.setBounds(50, 150, 180, 30);
		add(nbSupPapierLabel);
		nbSupPapierField.setBounds(280, 150, 100, 30);
		add(nbSupPapierField);

		auteurLabel.setBounds(50, 200, 120, 30);
		add(auteurLabel);
		auteurCombo.setBounds(280, 200, 150, 30);
		add(auteurCombo);

		categorieLabel.setBounds(50, 250, 120, 30);
		add(categorieLabel);
		categorieCombo.setBounds(280, 250, 150, 30);
		add(categorieCombo);

		loadAuteurs();
		loadCategories();
		
		nbLivreLabel.setBounds(20,340,120,30);
		add(nbLivreLabel);
		nbLivreField.setBounds(100, 340, 60, 30);
		add(nbLivreField);
		
		editionLabel.setBounds(230, 340, 120, 30);
		add(editionLabel);
		editionField.setBounds(420, 340, 120, 30);
		add(editionField);
		
		ajouterEdition.setBounds(550, 340, 180, 30);
		add(ajouterEdition);
		ajouterEdition.addActionListener((event) -> {
			if (!nbLivreField.getText().matches("[0-9]*")||nbLivreField.getText().equals("")) {
				JOptionPane.showMessageDialog(AjouterOeuvre.this, "Veuillez entrer un nombre de livres valide !","Erreur",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (editionField.getText().equals("")) {
				JOptionPane.showMessageDialog(AjouterOeuvre.this, "Veuillez entrer une date valide !","Erreur",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			for(Livre l : livres){
				if(l.getDate_edition().equals(this.parseDate(editionField.getText()))){
					JOptionPane.showMessageDialog(AjouterOeuvre.this, "Edition Existe deja !","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			Livre l = new Livre();
			l.setEtat(1);
			l.setDate_edition(this.parseDate(editionField.getText()));
			l.setQte(Integer.parseInt(nbLivreField.getText()));
			livres.add(l);
			nbLivre.setText(livres.size()+" editions ajouté");
			
		});
		
		nbLivre.setBounds(30,380,300,30);
		add(nbLivre);

		ajouter.setBounds(50, 500, 120, 30);
		add(ajouter);
		ajouter.addActionListener((event) -> {
			if (!nbDVDField.getText().matches("[0-9]*")||nbDVDField.getText().equals("")) {
				JOptionPane.showMessageDialog(AjouterOeuvre.this, "Veuillez entrer un nombre de DVD valide !");
				return;
			}
			if (!nbSupPapierField.getText().matches("[0-9]*")||nbSupPapierField.getText().equals("")) {
				JOptionPane.showMessageDialog(AjouterOeuvre.this, "Veuillez entrer un nombre de papier valide !");
				return;
			}

			ajouter();
			JOptionPane.showMessageDialog(AjouterOeuvre.this, "Oeuvre bien ajoutée", "Success",
					JOptionPane.INFORMATION_MESSAGE);
		});

	}

	private void ajouter() {
		Oeuvre oeuvre = new Oeuvre();
		oeuvre.setTitre(titleField.getText());
		oeuvre.setNb_dvd(Integer.parseInt(nbDVDField.getText()));
		oeuvre.setNb_sup_papier(Integer.parseInt(nbSupPapierField.getText()));
		StringTokenizer stz1 = new StringTokenizer(auteurCombo.getSelectedItem().toString(), "-");
		Auteur auteur = new Auteur();
		auteur.setId_auteur(Integer.parseInt(stz1.nextToken()));
		oeuvre.setAuteur(auteur);
		Categorie cat = new Categorie();
		StringTokenizer stz2 = new StringTokenizer(categorieCombo.getSelectedItem().toString(), "-");
		cat.setId_categorie(Integer.parseInt(stz2.nextToken()));
		oeuvre.setCategorie(cat);
		oeuvre.setL_livre(new ArrayList<>());
		for(Livre l : livres){
			l.setOeuvre(oeuvre);
			oeuvre.getL_livre().add(l);
		}
		oeuvreRemote.save(oeuvre);
		
	}

	private void loadAuteurs() {
		List<Auteur> auteurs = auteurRemote.getList();
		for (Auteur a : auteurs) {
			auteurCombo.addItem(a.getId_auteur() + "-" + a.getNom() + " " + a.getPrenom());

		}
	}

	private void loadCategories() {
		List<Categorie> categories = categorieRemote.getList();
		for (Categorie c : categories) {
			categorieCombo.addItem(c.getId_categorie() + "-" + c.getLibelle());
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

package com.bib.frames;

import java.util.Calendar;

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

import metier.sessions.service.AdherentRemote;

public class AjouterAdherant extends JFrame {

	private static final long serialVersionUID = 1L;
	private Context context;
	private AdherentRemote adherentRemote;

	private JTextField nomField = new JTextField();
	private JLabel nomLabel = new JLabel("Nom : ");
	private JTextField prenomField = new JTextField();
	private JLabel prenomLabel = new JLabel("Prenom : ");
	private JTextField emailField = new JTextField();
	private JLabel emailLabel = new JLabel("Email : ");
	private JTextField telField = new JTextField();
	private JLabel telLabel = new JLabel("Telephone");
	private JTextField adressField = new JTextField();
	private JLabel adressLabel = new JLabel("Adresse");
	private JTextField filiereField = new JTextField();
	private JLabel filiereLabel  = new JLabel("Filiere");
	private JTextField departField = new JTextField();
	private JLabel deprtLabel = new JLabel("Department");
	private JTextField gradeField = new JTextField();
	private JLabel gradeLabel = new JLabel("Grade");
	private JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Etudiant","Enseignant"});
	private JLabel typeLabel = new JLabel("Type");
	private JButton ajouter = new JButton("Enregistrer");

	public AjouterAdherant() {
		try {
			init();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(AjouterAdherant.this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		//initialiser Ajouter Adherent
		CommonPartsLoader.init(this);
		CommonPartsLoader.loadMenu(this);

		setLayout(null);

		nomLabel.setBounds(50, 50, 180, 30);
		add(nomLabel);
		nomField.setBounds(280, 50, 190, 30);
		add(nomField);

		prenomLabel.setBounds(50, 100, 120, 30);
		add(prenomLabel);
		prenomField.setBounds(280, 100, 190, 30);
		add(prenomField);

		emailLabel.setBounds(50, 150, 180, 30);
		add(emailLabel);
		emailField.setBounds(280, 150, 190, 30);
		add(emailField);

		adressLabel.setBounds(50, 200, 120, 30);
		add(adressLabel);
		adressField.setBounds(280, 200, 190, 30);
		add(adressField);

		telLabel.setBounds(50, 250, 120, 30);
		add(telLabel);
		telField.setBounds(280, 250, 190, 30);
		add(telField);
		
		typeLabel.setBounds(50, 300, 150, 30);
		add(typeLabel);
		typeCombo.setBounds(280,300,190,30);
		add(typeCombo);
		
		deprtLabel.setBounds(50,350,150,30);
		add(deprtLabel);
		departField.setBounds(280, 350, 190, 30);
		add(departField);
		
		filiereLabel.setBounds(50, 400, 150, 30);
		add(filiereLabel);
		filiereField.setBounds(280,400,190,30);
		add(filiereField);
		
		gradeLabel.setBounds(50, 450, 150, 30);
		add(gradeLabel);
		gradeField.setBounds(280,450,190,30);
		add(gradeField);
		
		

		ajouter.setBounds(50, 500, 130, 30);
		add(ajouter);
		ajouter.addActionListener((event) -> {
			if(this.validateFields()){
				Adherent ad = new Adherent();
				ad.setNom(nomField.getText());
				ad.setPrenom(prenomField.getText());
				ad.setEmail(emailField.getText());
				ad.setAdresse(adressField.getText());
				ad.setTel(Integer.parseInt(telField.getText()));
				ad.setDepartement(departField.getText());
				ad.setEtu(typeCombo.getSelectedItem().toString().equals("Etudiant"));
				ad.setFiliere(filiereField.getText());
				ad.setGrade(gradeField.getText());
				ad.setAnnee_inscrip(Calendar.getInstance().get(Calendar.YEAR));

				Compte c = new Compte();
				c.setAdherent(ad);
				c.setLogin(ad.getEmail());
				c.setMot_de_passe(String.valueOf(ad.getTel()));
				ad.setCompte(c);

				adherentRemote.save(ad);
				JOptionPane.showMessageDialog(AjouterAdherant.this, "Adherent bien enregistré", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
			

		});

	}

	private void init() throws NamingException {
		context = MyContext.getInstance();

		adherentRemote = (AdherentRemote) context.lookup("ejb:" + Constants.APP_NAME + "/" + Constants.MODULE_NAME + "/"
				+ Constants.DISTINCT_NAME + "/" + Constants.ADHERENT_SERVICE_NAME + "!" + Constants.ADHERENT_CLASS);

	}
	
	private boolean validateFields(){
		if(nomField.getText().length()<2 || !nomField.getText().matches("[a-zA-Z ]*")){
			JOptionPane.showMessageDialog(AjouterAdherant.this, "Veuillez entrer un nom valide !", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(prenomField.getText().length()<2 || !prenomField.getText().matches("[a-zA-Z ]*")){
			JOptionPane.showMessageDialog(AjouterAdherant.this, "Veuillez entrer un nom valide !", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(telField.getText().length()!=8 || !telField.getText().matches("[0-9]*")){
			JOptionPane.showMessageDialog(AjouterAdherant.this, "Veuillez entrer un numero telephone valide !", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}

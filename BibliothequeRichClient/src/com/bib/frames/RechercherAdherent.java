package com.bib.frames;

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

public class RechercherAdherent extends JFrame{

	private static final long serialVersionUID = 1L;
	private Context context;
	private AdherentRemote adherentRemote;
	private Adherent adherent;
	private JTextField searchField = new JTextField();
	private JLabel searchLabel = new JLabel("Recherche : ");
	private JButton search = new JButton("Rechercher");
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
	private JButton enregistrer = new JButton("Enregistrer");

	public RechercherAdherent(){
		try {
			init();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(RechercherAdherent.this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		CommonPartsLoader.init(this);
		CommonPartsLoader.loadMenu(this);

		setLayout(null);
		
		searchLabel.setBounds(50, 20, 150, 30);
		searchField.setBounds(250, 20, 180, 30);
		search.setBounds(450, 20, 120, 30);
		add(searchLabel);
		add(searchField);
		add(search);
		
		nomLabel.setBounds(50, 100, 180, 30);
		add(nomLabel);
		nomField.setBounds(280, 100, 190, 30);
		add(nomField);

		prenomLabel.setBounds(50, 150, 120, 30);
		add(prenomLabel);
		prenomField.setBounds(280, 150, 190, 30);
		add(prenomField);

		emailLabel.setBounds(50, 200, 180, 30);
		add(emailLabel);
		emailField.setBounds(280, 200, 190, 30);
		add(emailField);

		adressLabel.setBounds(50, 250, 120, 30);
		add(adressLabel);
		adressField.setBounds(280, 250, 190, 30);
		add(adressField);

		telLabel.setBounds(50, 300, 120, 30);
		add(telLabel);
		telField.setBounds(280, 300, 190, 30);
		add(telField);
		
		typeLabel.setBounds(50, 350, 150, 30);
		add(typeLabel);
		typeCombo.setBounds(280,350,190,30);
		add(typeCombo);
		
		deprtLabel.setBounds(50,400,150,30);
		add(deprtLabel);
		departField.setBounds(280, 400, 190, 30);
		add(departField);
		
		filiereLabel.setBounds(50, 450, 150, 30);
		add(filiereLabel);
		filiereField.setBounds(280,450,190,30);
		add(filiereField);
		
		gradeLabel.setBounds(50, 500, 150, 30);
		add(gradeLabel);
		gradeField.setBounds(280,500,190,30);
		add(gradeField);

		enregistrer.setBounds(50, 550, 130, 30);
		add(enregistrer);
		enregistrer.setEnabled(false);
		
		search.addActionListener((event)->{
			adherent = adherentRemote.search(searchField.getText());
			if(adherent != null){
				enregistrer.setEnabled(true);
				nomField.setText(adherent.getNom());
				prenomField.setText(adherent.getPrenom());
				emailField.setText(adherent.getEmail());
				adressField.setText(adherent.getAdresse());
				telField.setText(String.valueOf(adherent.getTel()));
				departField.setText(adherent.getDepartement());
				gradeField.setText(adherent.getGrade());
				filiereField.setText(adherent.getFiliere());
				typeCombo.setSelectedItem(adherent.isEtu()?"Etudiant":"Enseignant");
			}else{
				enregistrer.setEnabled(false);
				nomField.setText("");
				prenomField.setText("");
				emailField.setText("");
				adressField.setText("");
				telField.setText("");
				departField.setText("");
				gradeField.setText("");
				filiereField.setText("");
				JOptionPane.showMessageDialog(RechercherAdherent.this, "Aucun Adherent touvé !","",JOptionPane.ERROR_MESSAGE);
			}
		});
		
		enregistrer.addActionListener((event)->{
			if(this.validateFields()){
				adherent.setNom(nomField.getText());
				adherent.setPrenom(prenomField.getText());
				adherent.setEmail(emailField.getText());
				adherent.setAdresse(adressField.getText());
				adherent.setTel(Integer.parseInt(telField.getText()));
				adherent.setDepartement(departField.getText());
				adherent.setEtu(typeCombo.getSelectedItem().toString().equals("Etudiant"));
				adherent.setFiliere(filiereField.getText());
				adherent.setGrade(gradeField.getText());
				adherentRemote.update(adherent);
				JOptionPane.showMessageDialog(RechercherAdherent.this, "Adherent bien enregistré", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		search.setToolTipText("Rechercher Par Email ou par Numero de telephone");
		searchField.setToolTipText("Rechercher Par Email ou par Numero de telephone");
	}
	

	private void init() throws NamingException {
		context = MyContext.getInstance();

		adherentRemote = (AdherentRemote) context.lookup("ejb:" + Constants.APP_NAME + "/" + Constants.MODULE_NAME + "/"
				+ Constants.DISTINCT_NAME + "/" + Constants.ADHERENT_SERVICE_NAME + "!" + Constants.ADHERENT_CLASS);

	}
	
	private boolean validateFields(){
		if(nomField.getText().length()<2 || !nomField.getText().matches("[a-zA-Z ]*")){
			JOptionPane.showMessageDialog(RechercherAdherent.this, "Veuillez entrer un nom valide !", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if(prenomField.getText().length()<2 || !prenomField.getText().matches("[a-zA-Z ]*")){
			JOptionPane.showMessageDialog(RechercherAdherent.this, "Veuillez entrer un nom valide !", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(telField.getText().length()!=8 || !telField.getText().matches("[0-9]*")){
			JOptionPane.showMessageDialog(RechercherAdherent.this, "Veuillez entrer un numero telephone valide !", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}

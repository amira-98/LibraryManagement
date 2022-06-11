package com.bib.frames;

import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CommonPartsLoader {
	//methode initialiser : nouvelle fenetre
	public static void init(JFrame frame){
		frame.setTitle("Bibliotheque");
		frame.setSize(800,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	}

	public static void loadMenu(JFrame frame) {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuOeuvre = new JMenu("Gestion Ouevre");
		JMenu menuAdherent = new JMenu("Gestion Adherent");
		JMenuItem ajoutOeuvre = new JMenuItem("Ajouter Oeuvre");
		JMenuItem listOeuvre = new JMenuItem("List des oeuvres");
		JMenuItem modifierOeuvre = new JMenuItem("Rechercher Oeuvre");
		JMenuItem ajouterAdherent = new JMenuItem("Ajouter Adherent");
		JMenuItem rechercherAdherent = new JMenuItem("Rechercher Adherent");
		JMenuItem listAdherent = new JMenuItem("Liste Adherents");
		
		ajoutOeuvre.addActionListener((event)->{
			java.awt.EventQueue.invokeLater(()->{
				new AjouterOeuvre();
				frame.dispose();
			});
		});
		
		modifierOeuvre.addActionListener((event)->{
			java.awt.EventQueue.invokeLater(()->{
				new RechercherOeuvre();
				frame.dispose();
			});
		});
		listOeuvre.addActionListener((event)->{
			java.awt.EventQueue.invokeLater(()->{
				new ListOeuvre();
				frame.dispose();
			});
		});
		
		ajouterAdherent.addActionListener((event)->{
			java.awt.EventQueue.invokeLater(()->{
				new AjouterAdherant();
				frame.dispose();
			});
		});
		
		listAdherent.addActionListener((event)->{
			java.awt.EventQueue.invokeLater(()->{
				new ListAdherent();
				frame.dispose();
			});
		});
		
		rechercherAdherent.addActionListener((event)->{
			java.awt.EventQueue.invokeLater(()->{
				new RechercherAdherent();
				frame.dispose();
			});
		});

		menuOeuvre.add(ajoutOeuvre);
		menuOeuvre.add(modifierOeuvre);
		menuOeuvre.add(listOeuvre);
		
		menuAdherent.add(ajouterAdherent);
		menuAdherent.add(rechercherAdherent);
		menuAdherent.add(listAdherent);

		menuBar.add(menuOeuvre);
		menuBar.add(menuAdherent);
		
		frame.setJMenuBar(menuBar);
	}
	
	

}

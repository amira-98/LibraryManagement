package com.bib.frames;

import java.awt.BorderLayout;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.bib.desktop.factory.MyContext;
import metier.entities.*;

import metier.sessions.service.OeuvreRemote;

public class ListOeuvre extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private Context context;
	private OeuvreRemote oeuvreRemote;
	
	
	public ListOeuvre(){
		try {
			init();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(ListOeuvre.this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		CommonPartsLoader.init(this);
		CommonPartsLoader.loadMenu(this);
		
		setLayout(new BorderLayout());
		fillTable();
		add(table.getTableHeader(),BorderLayout.NORTH);
		add(table,BorderLayout.CENTER);
		
	}
	
	private void init() throws NamingException {
		context = MyContext.getInstance();

		oeuvreRemote = (OeuvreRemote) context.lookup("ejb:" + Constants.APP_NAME + "/" + Constants.MODULE_NAME + "/"
				+ Constants.DISTINCT_NAME + "/" + Constants.OEUVRE_SERVICE_NAME + "!" + Constants.OEUVRE_CLASS);

		
	}
	
	private void fillTable(){
		String[] headers = {"Identifiant","Titre","Auteur","Nb Sup Pages","Nb DVD"};
		
		List<Oeuvre> list = oeuvreRemote.findAll();
		Object[][] content = new Object[list.size()][5];
		
		for(int i=0;i<list.size();i++){
			content[i][0] = list.get(i).getId_oeuvre();
			content[i][1] = list.get(i).getTitre();
			content[i][2] = list.get(i).getAuteur().getNom()+" "+list.get(i).getAuteur().getPrenom();
			content[i][3] = list.get(i).getNb_sup_papier();
			content[i][4] = list.get(i).getNb_dvd();
			
		}
		
		table = new JTable(content,headers);
		
	}

}

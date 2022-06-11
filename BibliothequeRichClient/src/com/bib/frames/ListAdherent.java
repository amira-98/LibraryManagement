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

import metier.sessions.service.AdherentRemote;

public class ListAdherent extends JFrame{

	private static final long serialVersionUID = 1L;
	private Context context;
	private AdherentRemote adherentRemote;
	
	private JTable table;
	
	public ListAdherent(){
		try {
			init();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(ListAdherent.this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
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

		adherentRemote = (AdherentRemote) context.lookup("ejb:" + Constants.APP_NAME + "/" + Constants.MODULE_NAME + "/"
				+ Constants.DISTINCT_NAME + "/" + Constants.ADHERENT_SERVICE_NAME + "!" + Constants.ADHERENT_CLASS);

	}
	
	private void fillTable(){
		
		List<Adherent> list = adherentRemote.getList();
		
		String[] headers = {"Nom","Prenom","Email","Telephone","Adresse"};
		Object[][] content = new Object[list.size()][5];
		
		for(int i=0;i<list.size();i++){
			content[i][0] = list.get(i).getNom();
			content[i][1] = list.get(i).getPrenom();
			content[i][2] = list.get(i).getEmail();
			content[i][3] = list.get(i).getTel();
			content[i][4] = list.get(i).getAdresse();
		}
		
		table = new JTable(content,headers);
		
		
	}

}












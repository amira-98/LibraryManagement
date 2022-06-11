package com.bib.frames;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MainApp extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel loginLabel = new JLabel("Nom utilsiateur");
	private JLabel passLabel = new JLabel("Mot de passe");
	private JTextField login = new JTextField();
	private JTextField pass = new JTextField();
	private JButton conn = new JButton("Connecter");

	public MainApp() {
		super("Bibliotheque");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		
		setLayout(null);
		
		loginLabel.setBounds(80,50,150,30);
		add(loginLabel);
		login.setBounds(250,50,150,30);
		add(login);
		
		passLabel.setBounds(80, 100, 120, 30);
		add(passLabel);
		pass.setBounds(250, 100, 150, 30);
		add(pass);
		
		conn.setBounds(180, 160, 120, 30);
		add(conn);
		
		conn.addActionListener((e)->{
			String log = login.getText();
			String pas = pass.getText();
			
			if("admin".equals(log)&&"admin".equals(pas)){
				
				java.awt.EventQueue.invokeLater(() -> {
					JFrame frame = new JFrame();
					dispose();
					CommonPartsLoader.init(frame);
					CommonPartsLoader.loadMenu(frame);
					
				});
				
				
			}else{
				JOptionPane.showMessageDialog(MainApp.this, "Login ou mot de passe invalide","Erreur",JOptionPane.ERROR_MESSAGE);
			}
		});
		
		
		
		
		

	}

	public static void main(String[] args) {
		
		java.awt.EventQueue.invokeLater(() -> {
			new MainApp();
		});

	}

}

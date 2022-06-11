package com.bib.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.entities.*;

import metier.sessions.service.*;

@WebServlet("/user/rechercher")
public class Rechercher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private OeuvreLocal oeuvreLocal;
	@EJB
	private AdherentLocal adLocal;
	@EJB
	private AuteurLocal auteurLocal;
	@EJB
	private CategorieLocal categLocal;
	public Rechercher() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String titre = request.getParameter("titre");
		String aut = request.getParameter("auteur");
		String categorie = request.getParameter("categ");

		int auteur = (aut.length() > 0 && aut.matches("[0-9]*")) ? Integer.parseInt(aut) : 0;
		int categ = (categorie.length() > 0 && categorie.matches("[0-9]*")) ? Integer.parseInt(categorie) : 0;

		List<Oeuvre> list = oeuvreLocal.findAll();
		List<Oeuvre> result = new ArrayList<>();

		int numberOf3s = 0;
		if (categ > 0 || auteur > 0 || titre.length() > 0) {
			for (Oeuvre ov : list) {
				int score = 0;
				if (categ > 0 && ov.getCategorie().getId_categorie() == categ)
					score++;
				if (auteur > 0 && ov.getAuteur().getId_auteur() == auteur)
					score++;
				if (titre.length()>0 && (titre.toLowerCase().contains(ov.getTitre()) || ov.getTitre().contains(titre)))
					score++;

				switch (score) {
				case 1:
					result.add(0, ov);
					break;
				case 2:
					result.add(result.size() - (numberOf3s), ov);
					break;
				case 3:
					result.add(ov);
					numberOf3s++;
					break;
					
				}
			}
		} else {
			result.addAll(list);
		}
		request.setAttribute("ovrs", result);
		
		boolean canUserBorrowBooks = true;
		HttpSession session = request.getSession(true);
		int adherentId = (Integer) session.getAttribute("user");
		
		Adherent ad = adLocal.findById(adherentId);
		if (ad.isBlacklist())
			canUserBorrowBooks = false;
		else {
			int nbBorrowedBooks = 0;
			for(Emprunt emp : ad.getL_empr()){
				
				if(emp.getDate_retour_effective()==null)
					nbBorrowedBooks++;
				
			}
			
			// Recupere les livres dans les cookies
			
			
			if(ad.isEtu() && nbBorrowedBooks>=3)
				canUserBorrowBooks=false;
			
			else if(!ad.isEtu() && nbBorrowedBooks>=5)
				canUserBorrowBooks=false;
			
		}

		request.setAttribute("borrow", canUserBorrowBooks);
		request.setAttribute("categs", categLocal.getList());
		request.setAttribute("auteurs", auteurLocal.getList());
		this.getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);

	}

}

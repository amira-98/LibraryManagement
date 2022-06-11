package com.bib.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.entities.*;

import metier.sessions.service.*;

@WebServlet("/user/")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private OeuvreLocal oeuvre;
	@EJB
	private AdherentLocal adLocal;
	@EJB
	private AuteurLocal auteurLocal;
	@EJB
	private CategorieLocal categLocal;

	public HomePage() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("#### User requested home page");
		request.setAttribute("ovrs", oeuvre.findAll());
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

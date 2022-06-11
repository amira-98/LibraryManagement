package com.bib.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.entities.*;

import metier.sessions.service.*;


@WebServlet("/user/panier")
public class AfficherPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
    private LivreLocal livrLocal;   
	@EJB
	private AdherentLocal adhrLocal;
	@EJB
	private EmpruntLocal emprLocal;
    
    public AfficherPanier() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		
		request.setAttribute("livres", this.getBooksInCookies(cookies));
		getServletContext().getRequestDispatcher("/WEB-INF/AfficherPanier.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("user entered afficher panier");
		
		Cookie[] cookies = request.getCookies();
		
		HttpSession session = request.getSession();
		Integer id = (Integer)session.getAttribute("user");
		Adherent adr = adhrLocal.findById(id);
		
		for(Cookie cookie: cookies){
			try{
				System.out.println("Cookie nme : " + cookie.getName());
				Livre l = livrLocal.findById(Integer.parseInt(cookie.getName()));
				Emprunt empr = new Emprunt();
				Pk_emprunt pk = new Pk_emprunt();
				pk.setId_adherent(adr.getId());
				pk.setId_livre(l.getId_livre());
				pk.setDate_emprunt(new Date());
				empr.setId(pk);
				//empr.setAdhe_empr(adr);
				//empr.setLivre_empr(l);
				Calendar cal = Calendar.getInstance(); 
				cal.add(Calendar.MONTH, 1);
				empr.setDate_retour_theorique(cal.getTime());
				emprLocal.ajouterEmprunt(empr);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
		response.sendRedirect(request.getContextPath()+"/user/");
	}
	
	private List<Livre> getBooksInCookies(Cookie[] cookies){
		List<Livre> list = new ArrayList<>();
		for(Cookie cookie: cookies){
			try{
				
				list.add(livrLocal.findById(Integer.parseInt(cookie.getName())));
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		return list;
	}

}

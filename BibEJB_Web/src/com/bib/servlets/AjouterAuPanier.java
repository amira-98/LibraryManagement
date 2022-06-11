package com.bib.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.entities.*;

import metier.sessions.service.*;


@WebServlet("/user/add-to-panel")
public class AjouterAuPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private LivreLocal livrLoc;
       
   
    public AjouterAuPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("user requesting to add to cookies");
		
		int id = Integer.parseInt(request.getParameter("id"));
		Cookie[] cookies = request.getCookies();
		/*
		 * search id book already exists in cookies
		 */
		for(Cookie c : cookies){
			try{
				if(Integer.parseInt(c.getName()) == id)
					return;
			}catch(Exception e){
				System.out.println("error");
				
			}
			
			
		}
		Livre l = livrLoc.findById(id);
		Cookie c = new Cookie(String.valueOf(id),l.getOeuvre().getTitre());
		c.setMaxAge(60*60*24);
		response.addCookie(c);
		response.setContentType("text/plain");
		response.getWriter().write("OK");
	}

}

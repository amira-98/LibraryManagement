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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@EJB
	private AdherentLocal adherentLocal;
   
    public LoginServlet() {
        super();
        
    }
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		Adherent ad = adherentLocal.findByLoginAndPassword(login, pass);
		if(ad == null){
			request.setAttribute("error", true);
			getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
		}else{
			HttpSession session = request.getSession(true);
			session.setAttribute("user", ad.getId());
			response.sendRedirect(request.getContextPath()+"/user/");
		}
	}

}

package com.bib.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user/delete-from-panier")
public class DeleteFromPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    
    public DeleteFromPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println(id);
		Cookie[] cookies = request.getCookies();
		response.setContentType("text/plain");
		for(Cookie cookie : cookies){
			if(cookie.getName().equals(id)){
				//faire mourir le cookie
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				response.getWriter().write("OK");
				return;
			}
		}
		
		response.getWriter().write("ERROR");
	}

}

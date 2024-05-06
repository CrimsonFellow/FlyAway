package com.flyaway.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logoutServlet", "/logout"})
public class LogoutServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the current session without creating a new one if it doesn't exist

        if (session != null) {
            session.invalidate(); // Invalidate the session, effectively logging out the user
        }

        // Redirect the user back to the index page after logout
        response.sendRedirect("index.jsp");
    }
}

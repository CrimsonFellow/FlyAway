package com.flyaway.controller;

import com.flyaway.service.UserService;
import com.flyaway.util.HibernateUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/userLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService userService;

    public UserLoginServlet() {
        this.userService = new UserService(HibernateUtil.getSessionFactory());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get username and password from the request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the user is authenticated
        boolean isAuthenticated = userService.authenticateUser(username, password);

        if (isAuthenticated) {
            // If authentication is successful, retrieve user ID and store it in the session
            int userId = userService.getUserIdByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            
            // Redirect the user to the user dashboard
            response.sendRedirect(request.getContextPath() +"/userDashboard.jsp");
        } else {
            // If authentication fails, redirect the user back to the index page with an error message
            response.sendRedirect("index.jsp?error=1");
        }
    }
}


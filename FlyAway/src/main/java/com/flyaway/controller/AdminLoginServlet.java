package com.flyaway.controller;

import com.flyaway.service.AdminService;
import com.flyaway.util.HibernateUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/adminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AdminService adminService;

    public AdminLoginServlet() {
        this.adminService = new AdminService(HibernateUtil.getSessionFactory());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get username and password from the request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the admin is authenticated
        boolean isAuthenticated = adminService.authenticateAdmin(username, password);

        if (isAuthenticated) {
            // If authentication is successful, store admin ID in session
            int adminId = adminService.getAdminIdByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("adminId", adminId);

            // Redirect to the admin dashboard
            response.sendRedirect(request.getContextPath() + "/adminDashboard.jsp");
        } else {
            // If authentication fails, redirect the admin back to the login page with an error message
            response.sendRedirect("index.jsp?error=1");
        }
    }
}



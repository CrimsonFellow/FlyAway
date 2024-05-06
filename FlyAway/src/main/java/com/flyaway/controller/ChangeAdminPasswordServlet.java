package com.flyaway.controller;

import com.flyaway.service.AdminService;
import com.flyaway.util.HibernateUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/changeAdminPasswordServlet")
public class ChangeAdminPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final AdminService adminService;

    public ChangeAdminPasswordServlet() {
        this.adminService = new AdminService(HibernateUtil.getSessionFactory());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve admin ID from session
        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("adminId");

        // Get the new password from the request parameters
        String newPassword = request.getParameter("newPassword");

        // Update the admin's password
        boolean passwordChanged = adminService.changePassword(adminId, newPassword);

        if (passwordChanged) {
            // Password change successful
            // Redirect to a success page or display a success message
            response.sendRedirect(request.getContextPath() + "/adminDashboard.jsp");
        } else {
            // Password change failed
            // Redirect to a failure page or display an error message
            response.sendRedirect(request.getContextPath() + "/adminDashboardjsp");
        }
    }
}

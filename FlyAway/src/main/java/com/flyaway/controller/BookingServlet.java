package com.flyaway.controller;

import com.flyaway.service.BookingService;
import com.flyaway.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        super.init();
        bookingService = new BookingService(HibernateUtil.getSessionFactory());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This method can be used to display bookings, if needed
        response.sendRedirect(request.getContextPath() + "/adminDashboard.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            deleteBooking(request, response);
        } else {
            // Handle invalid or unsupported actions here
            // Optionally, you can redirect or display an error message
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve booking ID from request parameters
        int bookingId = Integer.parseInt(request.getParameter("id"));

        // Delete the booking from the database
        bookingService.deleteBooking(bookingId);

        // Redirect back to the booking page
        response.sendRedirect(request.getContextPath() + "/booking");
    }

}


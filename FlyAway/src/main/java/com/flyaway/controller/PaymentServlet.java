package com.flyaway.controller;

import com.flyaway.model.Flight;
import com.flyaway.service.UserService;
import com.flyaway.service.FlightService;
import com.flyaway.util.HibernateUtil;
import com.flyaway.model.Booking; // Import Booking model
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final FlightService flightService;
    public PaymentServlet() {
        this.flightService = new FlightService(HibernateUtil.getSessionFactory());
        new UserService(HibernateUtil.getSessionFactory());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get flight ID, number of passengers, and username from request parameters
        String flightId = request.getParameter("flightId");
        String numPersons = request.getParameter("numPersons");
        
        //Retrieve UserId
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        
        // Retrieve the flight based on flight ID
        Flight flight = flightService.getFlightById(Integer.parseInt(flightId));

        if (flight != null) {
            // Calculate total price using BigDecimal
            BigDecimal ticketPrice = flight.getTicketPrice();
            BigDecimal totalPrice = ticketPrice.multiply(BigDecimal.valueOf(Integer.parseInt(numPersons)));

            // Forward the flight ID, number of passengers, total price, and userId to payment page
            request.setAttribute("flightId", flightId);
            request.setAttribute("numPersons", numPersons);
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("userId", userId);
            request.getRequestDispatcher("paymentPage.jsp").forward(request, response);
        } else {
            // Handle the case where flight is not found
            request.setAttribute("message", "Flight not found");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve flight ID, number of passengers, username, and name from request parameters
        String flightId = request.getParameter("flightId");
        String numPersons = request.getParameter("numPersons");
        String name = request.getParameter("name");


        // Retrieve the flight based on flight ID
        Flight flight = flightService.getFlightById(Integer.parseInt(flightId));
        
        //Retrieve UserId
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        if (flight != null) {
            // Calculate total price using BigDecimal
            BigDecimal ticketPrice = flight.getTicketPrice();
            BigDecimal totalPrice = ticketPrice.multiply(BigDecimal.valueOf(Integer.parseInt(numPersons)));

            // Create a booking for the user
            boolean bookingCreated = createBooking(userId, Integer.parseInt(flightId), totalPrice);

            if (bookingCreated) {
                // Set request attributes for receipt
                request.setAttribute("sourceAirport", flight.getDepartureAirport().getCity());
                request.setAttribute("destinationAirport", flight.getDestinationAirport().getCity());
                request.setAttribute("travelDate", flight.getDepartureDatetime());
                request.setAttribute("numPassengers", numPersons);
                request.setAttribute("totalPrice", totalPrice);
                request.setAttribute("name", name);
                request.getRequestDispatcher("receipt.jsp").forward(request, response);
            } else {
                // Handle the case where booking creation fails
                request.setAttribute("message", "Booking creation failed");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }
        } else {
            // Handle the case where flight is not found
            request.setAttribute("message", "Flight not found");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    private boolean createBooking(int userId, int flightId, BigDecimal totalPrice) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Create a new Booking object
            Booking booking = new Booking();
            booking.setUserId(userId);
            booking.setFlightId(flightId);
            booking.setBookingDate(new Date()); // Assuming the current date is the booking date
            booking.setTotalPrice(totalPrice);
            booking.setStatus("CONFIRMED"); // Assuming the initial status is "Pending"

            // Save the Booking object to the database
            session.save(booking);

            transaction.commit();
            return true; // Return true if booking creation is successful
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false if an error occurs during booking creation
        }
    }
}



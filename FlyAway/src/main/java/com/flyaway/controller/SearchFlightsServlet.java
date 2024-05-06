package com.flyaway.controller;

import com.flyaway.model.Flight;
import com.flyaway.service.FlightService;
import com.flyaway.util.HibernateUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchFlightsServlet")
public class SearchFlightsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final FlightService flightService;

    public SearchFlightsServlet() {
        this.flightService = new FlightService(HibernateUtil.getSessionFactory());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user input from request parameters
        String dateOfTravel = request.getParameter("dateOfTravel");
        String sourceStr = request.getParameter("source");
        String destinationStr = request.getParameter("destination");
        int sourceId = Integer.parseInt(sourceStr); // Convert source string to integer
        int destinationId = Integer.parseInt(destinationStr); // Convert destination string to integer
        int numPersons = Integer.parseInt(request.getParameter("numPersons"));

        // Perform flight search based on user input
        List<Flight> flights = flightService.searchFlights(dateOfTravel, sourceId, destinationId);

        if (flights == null) {
            // Handle the case where the flight search service returns null
            // For example, display an error message to the user
            request.setAttribute("message", "An error occurred while searching for flights.");
            request.getRequestDispatcher("userDashboard.jsp").forward(request, response);
        } else if (flights.isEmpty()) {
            // No flights found, display message to the user
            request.setAttribute("message", "No flights available for the specified criteria.");
            request.getRequestDispatcher("userDashboard.jsp").forward(request, response);
        } else {
            // Flights found, forward user to searchResults.jsp to display the results
            request.setAttribute("flights", flights);
            request.setAttribute("numPersons", numPersons);
            request.getRequestDispatcher("searchResults.jsp").forward(request, response);
        }
    }
}



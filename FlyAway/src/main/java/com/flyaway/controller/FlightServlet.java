package com.flyaway.controller;

import com.flyaway.model.Airline;
import com.flyaway.model.Airport;
import com.flyaway.model.Flight;
import com.flyaway.service.AirlineService;
import com.flyaway.service.AirportService;
import com.flyaway.service.FlightService;
import com.flyaway.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/flight")
public class FlightServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private FlightService flightService;

    @Override
    public void init() throws ServletException {
        super.init();
        flightService = new FlightService(HibernateUtil.getSessionFactory());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Flight> flights = flightService.getAllFlights();
        request.setAttribute("flights", flights);
        request.getRequestDispatcher("/adminDashboard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addFlight(request, response);
                    break;
                case "edit":
                    editFlight(request, response);
                    break;
                case "delete":
                    deleteFlight(request, response);
                    break;
                default:
                    // Handle invalid action
                    break;
            }
        }
    }

    private void addFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String airlineId = request.getParameter("airlineId");
        String departureAirportId = request.getParameter("departureAirportId");
        String destinationAirportId = request.getParameter("destinationAirportId");
        String departureDatetimeStr = request.getParameter("departureDatetime");
        String arrivalDatetimeStr = request.getParameter("arrivalDatetime");
        String ticketPriceStr = request.getParameter("ticketPrice");

        // Convert ticket price to BigDecimal
        BigDecimal ticketPrice = new BigDecimal(ticketPriceStr);

        // Retrieve Airline object by ID
        AirlineService airlineService = new AirlineService(HibernateUtil.getSessionFactory());
        Airline airline = airlineService.getAirlineById(Integer.parseInt(airlineId));

        // Retrieve departure and destination airports by ID
        AirportService airportService = new AirportService(HibernateUtil.getSessionFactory());
        Airport departureAirport = airportService.getAirportById(Integer.parseInt(departureAirportId));
        Airport destinationAirport = airportService.getAirportById(Integer.parseInt(destinationAirportId));

        // Parse departure and arrival datetime strings to Date objects
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date departureDatetime = null;
        Date arrivalDatetime = null;
        try {
            departureDatetime = dateFormat.parse(departureDatetimeStr);
            arrivalDatetime = dateFormat.parse(arrivalDatetimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parsing error
        }

        // Create the Flight object
        Flight flight = new Flight();
        flight.setAirline(airline);
        flight.setDepartureAirport(departureAirport);
        flight.setDestinationAirport(destinationAirport);
        flight.setDepartureDatetime(departureDatetime);
        flight.setArrivalDatetime(arrivalDatetime);
        flight.setTicketPrice(ticketPrice);

        // Add the flight using the FlightService
        flightService.addFlight(flight);

        // Redirect back to the flight management page
        response.sendRedirect(request.getContextPath() + "/flight");
    }



    private void editFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flightId = request.getParameter("id");
        String airlineId = request.getParameter("airlineId");
        String departureAirportId = request.getParameter("departureAirportId");
        String destinationAirportId = request.getParameter("destinationAirportId");
        String departureDatetimeStr = request.getParameter("departureDatetime");
        String arrivalDatetimeStr = request.getParameter("arrivalDatetime");
        String ticketPriceStr = request.getParameter("ticketPrice");

        Flight flight = flightService.getFlightById(Integer.parseInt(flightId));
        if (flight != null) {
            // Retrieve Airline object by ID
            AirlineService airlineService = new AirlineService(HibernateUtil.getSessionFactory());
            Airline airline = airlineService.getAirlineById(Integer.parseInt(airlineId));

            // Retrieve departure and destination airports by ID
            AirportService airportService = new AirportService(HibernateUtil.getSessionFactory());
            Airport departureAirport = airportService.getAirportById(Integer.parseInt(departureAirportId));
            Airport destinationAirport = airportService.getAirportById(Integer.parseInt(destinationAirportId));

            // Convert ticket price to BigDecimal
            BigDecimal ticketPrice = new BigDecimal(ticketPriceStr);

            // Parse departure and arrival datetime strings to Date objects
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date departureDatetime = null;
            Date arrivalDatetime = null;
            try {
                departureDatetime = dateFormat.parse(departureDatetimeStr);
                arrivalDatetime = dateFormat.parse(arrivalDatetimeStr);
            } catch (ParseException e) {
                e.printStackTrace();
                // Handle parsing error
            }

            // Update flight properties
            flight.setAirline(airline);
            flight.setDepartureAirport(departureAirport);
            flight.setDestinationAirport(destinationAirport);
            flight.setDepartureDatetime(departureDatetime);
            flight.setArrivalDatetime(arrivalDatetime);
            flight.setTicketPrice(ticketPrice);

            // Save the updated flight
            flightService.updateFlight(flight);
        }
        response.sendRedirect(request.getContextPath() + "/flight");
    }



    private void deleteFlight(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String flightId = request.getParameter("id");
        flightService.deleteFlight(Integer.parseInt(flightId));
        response.sendRedirect(request.getContextPath() + "/flight");
    }
}


package com.flyaway.controller;

import com.flyaway.model.Airline;
import com.flyaway.service.AirlineService;
import com.flyaway.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/airline")
public class AirlineServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AirlineService airlineService;

    @Override
    public void init() throws ServletException {
        super.init();
        airlineService = new AirlineService(HibernateUtil.getSessionFactory());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Airline> airlines = airlineService.getAllAirlines();
        request.setAttribute("airlines", airlines);
        request.getRequestDispatcher("/adminDashboard.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addAirline(request, response);
                    break;
                case "edit":
                    editAirline(request, response);
                    break;
                case "delete":
                    deleteAirline(request, response);
                    break;
                default:
                    // Handle invalid action
                    break;
            }
        }
    }

    private void addAirline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve airline data from request parameters
        String airlineName = request.getParameter("name");
        // Create new Airline object
        Airline airline = new Airline();
        airline.setName(airlineName);
        // Add airline to database
        airlineService.addAirline(airline);
        // Redirect back to the admin dashboard
        response.sendRedirect(request.getContextPath() + "/airline");
    }

    private void editAirline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve airline data from request parameters
        int airlineId = Integer.parseInt(request.getParameter("id"));
        String airlineName = request.getParameter("name");
        // Retrieve existing airline from database
        Airline airline = airlineService.getAirlineById(airlineId);
        if (airline != null) {
            // Update airline name
            airline.setName(airlineName);
            // Update airline in database
            airlineService.updateAirline(airline);
        }
        // Redirect back to the admin dashboard
        response.sendRedirect(request.getContextPath() + "/airline");
    }

    private void deleteAirline(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve airline ID from request parameters
        int airlineId = Integer.parseInt(request.getParameter("id"));
        // Delete airline from database
        airlineService.deleteAirline(airlineId);
        // Redirect back to the admin dashboard
        response.sendRedirect(request.getContextPath() + "/airline");
    }
}

package com.flyaway.controller;

import com.flyaway.model.Airport;
import com.flyaway.service.AirportService;
import com.flyaway.util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/airport")
public class AirportServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AirportService airportService;

    @Override
    public void init() throws ServletException {
        super.init();
        airportService = new AirportService(HibernateUtil.getSessionFactory());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Airport> airports = airportService.getAllAirports();
        request.setAttribute("airports", airports);
        request.getRequestDispatcher("/adminDashboard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addAirport(request, response);
                    break;
                case "edit":
                    editAirport(request, response);
                    break;
                case "delete":
                    deleteAirport(request, response);
                    break;
                default:
                    // Handle invalid action
                    break;
            }
        }
    }

    private void addAirport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        Airport airport = new Airport();
        airport.setCode(code);
        airport.setName(name);
        airport.setCity(city);
        airport.setCountry(country);

        airportService.addAirport(airport);
        response.sendRedirect(request.getContextPath() + "/airport");
    }

    private void editAirport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int airportId = Integer.parseInt(request.getParameter("id"));
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        Airport airport = airportService.getAirportById(airportId);
        if (airport != null) {
            airport.setCode(code);
            airport.setName(name);
            airport.setCity(city);
            airport.setCountry(country);

            airportService.updateAirport(airport);
        }
        response.sendRedirect(request.getContextPath() + "/airport");
    }

    private void deleteAirport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int airportId = Integer.parseInt(request.getParameter("id"));
        airportService.deleteAirport(airportId);
        response.sendRedirect(request.getContextPath() + "/airport");
    }
}


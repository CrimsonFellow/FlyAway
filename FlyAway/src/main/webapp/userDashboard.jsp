<%@ page import="java.util.List" %>
<%@ page import="com.flyaway.model.Airport" %>
<%@ page import="com.flyaway.service.AirportService" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="com.flyaway.util.HibernateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // Create AirportService instance
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    AirportService airportService = new AirportService(sessionFactory);

    // Retrieve list of airports
    List<Airport> airports = airportService.getAllAirports();

    // Get the values of input parameters passed to the servlet
    String dateOfTravel = request.getParameter("dateOfTravel");
    String source = request.getParameter("source");
    String destination = request.getParameter("destination");
    String numPersons = request.getParameter("numPersons");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #8a2be2;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            text-align: center;
            color: #fff;
            width: 400px;
        }
        h1, h2 {
            margin-top: 0;
        }
        form {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            text-align: left;
            color: #fff;
        }
        select,
        input[type="date"],
        input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: none;
            outline: none;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #fff;
            color: #8a2be2;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #ddd;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to FlyAway - User Dashboard</h1>
        
        <!-- Search Form -->
        <h2>Search for Flights</h2>
        <form action="searchFlightsServlet" method="post">
            <label for="dateOfTravel">Date of Travel:</label>
            <input type="date" id="dateOfTravel" name="dateOfTravel" required><br><br>

            <label for="source">Source:</label>
            <select id="source" name="source" required>
                <% for (Airport airport : airports) { %>
                    <option value="<%= airport.getAirportId() %>"><%= airport.getCity() %></option>
                <% } %>
            </select><br><br>

            <label for="destination">Destination:</label>
            <select id="destination" name="destination" required>
                <% for (Airport airport : airports) { %>
                    <option value="<%= airport.getAirportId() %>"><%= airport.getCity() %></option>
                <% } %>
            </select><br><br>

            <label for="numPersons">Number of Persons:</label>
            <input type="number" id="numPersons" name="numPersons" min="1" value="1" required><br><br>

            <input type="submit" value="Search">
        </form>
        
        <!-- Logout Button -->
        <p><a href="logoutServlet">Logout</a></p>
        
        <!-- Display Search Results -->
        <!-- Code for displaying search results here -->
    </div>
</body>
</html>




<%@ page import="java.util.List" %>
<%@ page import="com.flyaway.model.Airline" %>
<%@ page import="com.flyaway.model.Airport" %>
<%@ page import="com.flyaway.model.Flight" %>
<%@ page import="com.flyaway.model.Booking" %>
<%@ page import="com.flyaway.service.AirlineService" %>
<%@ page import="com.flyaway.service.AirportService" %>
<%@ page import="com.flyaway.service.FlightService" %>
<%@ page import="com.flyaway.service.BookingService" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="com.flyaway.util.HibernateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.text.SimpleDateFormat" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        /* Add the CSS styles here */
        div.overflow-table {
            overflow-x: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
        }
    </style>
    
</head>
<body>
    <h1>Welcome to FlyAway - Admin Dashboard</h1>
 
 <!-- Airline CRUD -->  
<h2>Airlines</h2>
<div id="addAirlineForm" style="display: none;">
    <form method="post" action="airline">
        <input type="hidden" name="action" value="add">
        <label for="airlineName">Airline Name:</label>
        <input type="text" id="airlineName" name="name">
        <button type="submit">Add Airline</button>
        <button type="button" onclick="cancelAdd('addAirlineForm')">Cancel</button>
    </form>
</div>
<button type="button" onclick="showForm('addAirlineForm')">Add Airline</button>

<div style="overflow-x: auto;">
    <table border="1" style="width: 100%;">
        <thead>
            <tr>
                <th>Airline ID</th>
                <th>Airline Name</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Populate airlines table
                AirlineService airlineService = new AirlineService(HibernateUtil.getSessionFactory());
                List<Airline> airlines = airlineService.getAllAirlines();
                for (Airline airline : airlines) {
            %>
            <tr>
                <td><%= airline.getAirlineId() %></td>
                <td><%= airlineService.getAirlineName(airline.getAirlineId()) %></td>
                <td>
                    <div style="display: flex;">
                        <button class="edit-btn" onclick="showForm('editForm<%= airline.getAirlineId() %>')">Edit</button>
                        <form class="edit-form" id="editForm<%= airline.getAirlineId() %>" method="post" action="airline" style="display: none;">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="id" value="<%= airline.getAirlineId() %>">
                            <input type="text" name="name" value="<%= airline.getName() %>">
                            <button type="submit">Update</button>
                        </form>
                        <form method="post" action="airline">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= airline.getAirlineId() %>">
                            <button type="submit">Delete</button>
                        </form>
                    </div>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>


<!-- Airports CRUD -->
<h2>Airports</h2>
<div id="addAirportForm" style="display: none;">
    <form method="post" action="airport">
        <input type="hidden" name="action" value="add">
        <label for="code">Code:</label>
        <input type="text" id="code" name="code"><br>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br>
        <label for="city">City:</label>
        <input type="text" id="city" name="city"><br>
        <label for="country">Country:</label>
        <input type="text" id="country" name="country"><br>
        <button type="submit">Add Airport</button>
        <button type="button" onclick="cancelAdd('addAirportForm')">Cancel</button>
    </form>
</div>
<button type="button" onclick="showForm('addAirportForm')">Add Airport</button>

<div style="overflow-x: auto;">
    <table border="1" style="width: 100%;">
        <thead>
            <tr>
                <th>Airport ID</th>
                <th>Code</th>
                <th>Name</th>
                <th>City</th>
                <th>Country</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Populate airports table
                AirportService airportService = new AirportService(HibernateUtil.getSessionFactory());
                List<Airport> airports = airportService.getAllAirports();
                for (Airport airport : airports) {
            %>
            <tr>
                <td><%= airport.getAirportId() %></td>
                <td><%= airport.getCode() %></td>
                <td><%= airport.getName() %></td>
                <td><%= airport.getCity() %></td>
                <td><%= airport.getCountry() %></td>
                <td>
                    <div style="display: flex;">
                        <button class="edit-btn" onclick="showForm('editForm<%= airport.getAirportId() %>')">Edit</button>
                        <form class="edit-form" id="editForm<%= airport.getAirportId() %>" method="post" action="airport" style="display: none;">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="id" value="<%= airport.getAirportId() %>">
                            <input type="text" name="code" value="<%= airport.getCode() %>"><br>
                            <input type="text" name="name" value="<%= airport.getName() %>"><br>
                            <input type="text" name="city" value="<%= airport.getCity() %>"><br>
                            <input type="text" name="country" value="<%= airport.getCountry() %>"><br>
                            <button type="submit">Update</button>
                        </form>
                        <form method="post" action="airport">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= airport.getAirportId() %>">
                            <button type="submit">Delete</button>
                        </form>
                    </div>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>



<!-- Flights CRUD -->
<h2>Flights</h2>
<div id="addFlightForm" style="display: none;">
    <form method="post" action="flight">
        <input type="hidden" name="action" value="add">
        <label for="airlineId">Airline:</label>
        <select id="airlineId" name="airlineId">
            <% for (Airline airline : airlines) { %>
                <option value="<%= airline.getAirlineId() %>"><%= airline.getName() %></option>
            <% } %>
        </select><br>
        <label for="departureAirportId">Departure Airport:</label>
        <select id="departureAirportId" name="departureAirportId">
            <% for (Airport airport : airports) { %>
                <option value="<%= airport.getAirportId() %>"><%= airport.getName() %> - <%= airport.getCity() %></option>
            <% } %>
        </select><br>
        <label for="destinationAirportId">Destination Airport:</label>
        <select id="destinationAirportId" name="destinationAirportId">
            <% for (Airport airport : airports) { %>
                <option value="<%= airport.getAirportId() %>"><%= airport.getName() %> - <%= airport.getCity() %></option>
            <% } %>
        </select><br>
        <label for="departureDatetime">Departure Date:</label>
        <input type="text" id="departureDatetime" name="departureDatetime" placeholder="YYYY-MM-DD"><br>
        <label for="arrivalDatetime">Arrival Date:</label>
        <input type="text" id="arrivalDatetime" name="arrivalDatetime" placeholder="YYYY-MM-DD"><br>
        <label for="ticketPrice">Ticket Price:</label>
        <input type="text" id="ticketPrice" name="ticketPrice"><br>
        <button type="submit">Add Flight</button>
        <button type="button" onclick="cancelAdd('addFlightForm')">Cancel</button>
    </form>
</div>

<button type="button" onclick="showForm('addFlightForm')">Add Flight</button>

<div style="overflow-x: auto;">
    <table border="1" style="width: 100%;">
        <thead>
            <tr>
                <th>Flight ID</th>
                <th>Airline</th>
                <th>Departure Airport</th>
                <th>Destination Airport</th>
                <th>Departure Date</th>
                <th>Arrival Date</th>
                <th>Ticket Price</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Populate flights table
                FlightService flightService = new FlightService(HibernateUtil.getSessionFactory());
                List<Flight> flights = flightService.getAllFlights();
                for (Flight flight : flights) {
            %>
            <tr>
                <td><%= flight.getFlightId() %></td>
                <td>${flight.airline.name}</td>
                <td><%= flight.getDepartureAirport().getCity() %></td>
                <td><%= flight.getDestinationAirport().getCity() %></td>
                <td><%= new SimpleDateFormat("yyyy-MM-dd").format(flight.getDepartureDatetime()) %></td>
                <td><%= new SimpleDateFormat("yyyy-MM-dd").format(flight.getArrivalDatetime()) %></td>
                <td><%= flight.getTicketPrice() %></td>
                <td>
                    <div style="display: flex;">
                        <button class="edit-btn" onclick="showForm('editForm<%= flight.getFlightId() %>')">Edit</button>
                        <form class="edit-form" id="editForm<%= flight.getFlightId() %>" method="post" action="flight" style="display: none;">
						    <input type="hidden" name="action" value="edit">
						    <input type="hidden" name="id" value="<%= flight.getFlightId() %>">
						    <label for="airlineId">Airline:</label>
						    <select id="airlineId" name="airlineId">
						        <% for (Airline airline : airlines) { %>
						            <option value="<%= airline.getAirlineId() %>" <% if (airline.getAirlineId() == flight.getAirline().getAirlineId()) { %> selected <% } %>><%= airline.getName() %></option>
						        <% } %>
						    </select><br>
						    <label for="departureAirportId">Departure Airport:</label>
						    <select id="departureAirportId" name="departureAirportId">
						        <% for (Airport airport : airports) { %>
						            <option value="<%= airport.getAirportId() %>" <% if (airport.getAirportId() == flight.getDepartureAirport().getAirportId()) { %> selected <% } %>><%= airport.getName() %> - <%= airport.getCity() %></option>
						        <% } %>
						    </select><br>
						    <label for="destinationAirportId">Destination Airport:</label>
						    <select id="destinationAirportId" name="destinationAirportId">
						        <% for (Airport airport : airports) { %>
						            <option value="<%= airport.getAirportId() %>" <% if (airport.getAirportId() == flight.getDestinationAirport().getAirportId()) { %> selected <% } %>><%= airport.getName() %> - <%= airport.getCity() %></option>
						        <% } %>
						    </select><br>
						    <label for="departureDatetime">Departure Date:</label>
						    <input type="text" id="departureDatetime" name="departureDatetime" value="<%= flight.getDepartureDatetime() %>"><br>
						    <label for="arrivalDatetime">Arrival Date:</label>
						    <input type="text" id="arrivalDatetime" name="arrivalDatetime" value="<%= flight.getArrivalDatetime() %>"><br>
						    <label for="ticketPrice">Ticket Price:</label>
						    <input type="text" id="ticketPrice" name="ticketPrice" value="<%= flight.getTicketPrice() %>"><br>
						    <button type="submit">Update</button>
						</form>

                        <form method="post" action="flight">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= flight.getFlightId() %>">
                            <button type="submit">Delete</button>
                        </form>
                    </div>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>



<!-- Bookings CRUD -->
<h2>Bookings</h2>

<div style="overflow-x: auto;">
    <table border="1" style="width: 100%;">
        <thead>
            <tr>
                <th>Booking ID</th>
                <th>User ID</th>
                <th>Flight ID</th>
                <th>Booking Date</th>
                <th>Total Price</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Populate bookings table
                BookingService bookingService = new BookingService(HibernateUtil.getSessionFactory());
                List<Booking> bookings = bookingService.getAllBookings();
                for (Booking booking : bookings) {
            %>
            <tr>
                <td><%= booking.getBookingId() %></td>
                <td><%= booking.getUserId() %></td>
                <td><%= booking.getFlightId() %></td>
                <td><%= booking.getBookingDate() %></td>
                <td><%= booking.getTotalPrice() %></td>
                <td><%= booking.getStatus() %></td>
                <td>
                    
                    <form method="post" action="booking">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%= booking.getBookingId() %>">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

    
    <script>
        function showForm(formId) {
            var form = document.getElementById(formId);
            if (form.style.display === "none") {
                form.style.display = "block";
            } else {
                form.style.display = "none";
            }
        }

        function cancelAdd(formId) {
            var form = document.getElementById(formId);
            form.style.display = "none";
        }
    </script>

    <!-- Logout -->
    <p><a href="logoutServlet">Logout</a></p>
    <!-- Admin Change Password -->
	<p><a href="changePassword.jsp">Change Password</a></p>

    
</body>
</html>


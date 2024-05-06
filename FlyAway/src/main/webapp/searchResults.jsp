<%@ page import="java.util.List" %>
<%@ page import="com.flyaway.model.Flight" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Results</title>
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
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            width: 100%;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #8a2be2;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            border-radius: 10px;
            overflow: hidden;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #8a2be2;
            color: #fff;
        }
        tr:hover {
            background-color: #f2f2f2;
        }
        .action-link {
            color: #8a2be2;
            text-decoration: none;
            transition: color 0.3s;
        }
        .action-link:hover {
            color: #6a1a9a;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Search Results</h1>

        <c:if test="${not empty flights}">
            <table>
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
                    <c:forEach items="${flights}" var="flight">
                        <tr>
                            <td>${flight.flightId}</td>
                            <td>${flight.airline.name}</td>
                            <td>${flight.departureAirport.city}</td>
                            <td>${flight.destinationAirport.city}</td>
                            <td><fmt:formatDate value="${flight.departureDatetime}" pattern="yyyy-MM-dd" /></td>
                            <td><fmt:formatDate value="${flight.arrivalDatetime}" pattern="yyyy-MM-dd" /></td>
                            <td>${flight.ticketPrice}</td>
                            <td>
                                <a class="action-link" href="PaymentServlet?flightId=${flight.flightId}&numPersons=${numPersons}">Choose Flight</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <p>Choose a flight for ${numPersons} passengers</p>
        </c:if>
    </div>
</body>
</html>



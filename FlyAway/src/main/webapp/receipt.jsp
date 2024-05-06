<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Flight Receipt</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
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
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        h1 {
            color: #8a2be2;
            margin-bottom: 20px;
        }
        p {
            margin-bottom: 10px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #8a2be2;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #6a1a9a;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Flight Receipt</h1>
        <p>From: ${sourceAirport}</p>
        <p>To: ${destinationAirport}</p>
        <p>Date of Travel: <fmt:formatDate value="${travelDate}" pattern="yyyy-MM-dd" /></p>
        <p>Number of Passengers: ${numPassengers}</p>
        <p>Name: ${name}</p>
        <p>Total Price: ${totalPrice}</p>

        <!-- Button to go back to user dashboard -->
        <form action="userDashboard.jsp"> <!-- Adjust the action attribute with the appropriate URL -->
            <input type="submit" value="Back to User Dashboard">
        </form>
    </div>
</body>
</html>



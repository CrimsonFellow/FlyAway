<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.flyaway.model.Flight" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Page</title>
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
            max-width: 400px;
            width: 100%;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #8a2be2;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: 1px solid #ddd;
            outline: none;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #8a2be2;
            color: #fff;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #6a1a9a;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Payment Page</h1>

        <c:if test="${not empty flightId}">
            <p>Flight ID: ${flightId}</p>
            <p>Number of Passengers: ${numPersons}</p>
            <p>Total Price: ${totalPrice}</p>
            <p>User ID: ${userId}</p> <!-- Displaying the userId -->

            <!-- Payment Form -->
            <form action="PaymentServlet" method="post">
                <input type="hidden" name="flightId" value="${flightId}" />
                <input type="hidden" name="numPersons" value="${numPersons}" />
                <input type="hidden" name="totalPrice" value="${totalPrice}" />
                <input type="hidden" name="userId" value="${userId}" /> <!-- Adding userId as a hidden input -->

                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required /><br><br>

                <label for="creditCard">Credit Card Number:</label>
                <input type="text" id="creditCard" name="creditCard" required /><br><br>

                <input type="submit" value="Confirm Payment" />
            </form>
        </c:if>
    </div>
</body>
</html>





<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FlyAway - Airline Booking Portal</title>
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
            width: 300px;
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
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: none;
            outline: none;
        }
        button {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #fff;
            color: #8a2be2;
            cursor: pointer;
        }
        button:hover {
            background-color: #ddd;
        }
        .admin-login {
            margin-top: 20px;
            color: #333;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to FlyAway - Airline Booking Portal</h1>
        
        <!-- User Login Form -->
        <h2>User Login</h2>
        <form action="userLoginServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>
            <button type="submit">Login</button>
        </form>
        
        <!-- Admin Login Form -->
        <div class="admin-login">
            <h2>Admin Login</h2>
            <form action="adminLoginServlet" method="post">
                <label for="admin-username">Username:</label>
                <input type="text" id="admin-username" name="username" required><br>
                <label for="admin-password">Password:</label>
                <input type="password" id="admin-password" name="password" required><br>
                <button type="submit">Login</button>
            </form>
        </div>
    </div>
</body>
</html>


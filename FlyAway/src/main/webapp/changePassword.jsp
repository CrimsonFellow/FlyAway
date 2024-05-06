<%@ page import="com.flyaway.service.AdminService" %>
<%@ page import="com.flyaway.util.HibernateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // Check if admin ID is already present in the session
    Integer adminId = (Integer) session.getAttribute("adminId");
    
    // If admin ID is not present, retrieve it using the AdminService
    if (adminId == null) {
        AdminService adminService = new AdminService(HibernateUtil.getSessionFactory());
        String adminUsername = request.getParameter("adminUsername"); // Assuming you're passing the admin username from the login page
        adminId = adminService.getAdminIdByUsername(adminUsername);
        // Store the admin ID in the session
        session.setAttribute("adminId", adminId);
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
</head>
<body>
    <h1>Change Password</h1>
    <form method="post" action="changeAdminPasswordServlet">
        <input type="hidden" name="adminId" value="<%= adminId %>">
        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required><br>
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required><br>
        <input type="submit" value="Change Password">
    </form>
</body>
</html>

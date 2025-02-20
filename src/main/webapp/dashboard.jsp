<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dashboard - Book Social Media</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 1200px; margin: 0 auto; }
        .header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .welcome { font-size: 1.2em; }
        .logout { color: #fff; background: #dc3545; padding: 8px 16px; text-decoration: none; border-radius: 4px; }
        .section { margin-top: 30px; }
        .section-title { font-size: 1.5em; margin-bottom: 15px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div class="welcome">
                Welcome, ${user.username}!
            </div>
            <a href="logout" class="logout">Logout</a>
        </div>

        <div class="section">
            <div class="section-title">Upload Documents</div>
            <!-- File upload form will go here -->
        </div>

        <div class="section">
            <div class="section-title">Shared Resources</div>
            <!-- Shared resources list will go here -->
        </div>

        <div class="section">
            <div class="section-title">Study Materials</div>
            <!-- Study materials list will go here -->
        </div>
    </div>
</body>
</html>

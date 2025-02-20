<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login - Book Social Media</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 400px; margin: 0 auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input[type="text"], input[type="password"] {
            width: 100%; padding: 8px; box-sizing: border-box;
        }
        .error { color: red; margin-bottom: 10px; }
        .success { color: green; margin-bottom: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Login</h1>
        
        <% if (request.getParameter("error") != null) { %>
            <div class="error">
                Invalid username or password. Please try again.
            </div>
        <% } %>
        
        <% if (request.getParameter("registration") != null) { %>
            <div class="success">
                Registration successful! Please login.
            </div>
        <% } %>

        <form action="login" method="post">

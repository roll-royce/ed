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
        
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input[type="text"], textarea, input[type="file"] {
            width: 100%; padding: 8px; box-sizing: border-box;
        }
        textarea { height: 100px; }
        
        .resource-table { width: 100%; border-collapse: collapse; }
        .resource-table th, .resource-table td {
            padding: 8px; border: 1px solid #ddd; text-align: left;
        }
        .resource-table th { background-color: #f5f5f5; }
        .download-link { color: #007bff; text-decoration: none; }
        
        .materials-list { margin-top: 20px; }
        .material-item {
            border: 1px solid #ddd; padding: 15px; margin-bottom: 15px;
            border-radius: 4px;
        }
        .material-meta {
            margin-top: 10px; color: #666; font-size: 0.9em;
        }
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
            <form action="document" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" id="title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="description"></textarea>
                </div>
                <div class="form-group">
                    <label for="file">Select file:</label>
                    <input type="file" id="file" name="file" required>
                </div>
                <button type="submit">Upload</button>
            </form>
        </div>

        <div class="section">
            <div class="section-title">Shared Resources</div>
            <table class="resource-table">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Uploaded By</th>
                        <th>Date</th>
                        <th>Download</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="doc" items="${documents}">
                        <tr>
                            <td>${doc.title}</td>
                            <td>${doc.description}</td>
                            <td>${doc.uploaderId}</td>
                            <td>${doc.uploadedAt}</td>
                            <td>
                                <a href="download?file=${doc.filePath}" class="download-link">Download</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="section">
            <div class="section-title">Study Materials</div>
            <div class="materials-list">
                <c:forEach var="post" items="${posts}">
                    <div class="material-item">
                        <h3>${post.title}</h3>
                        <p>${post.content}</p>
                        <div class="material-meta">
                            <span>Posted by: ${post.authorId}</span>
                            <span>Date: ${post.createdAt}</span>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>
</html>

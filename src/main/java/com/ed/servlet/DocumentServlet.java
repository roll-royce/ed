package com.ed.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ed.model.Document;
import com.ed.service.DocumentService;

@WebServlet("/document")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class DocumentServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DocumentServlet.class);
    private DocumentService documentService = new DocumentService();
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Get application path
            String appPath = request.getServletContext().getRealPath("");
            String uploadPath = appPath + File.separator + UPLOAD_DIR;

            // Create upload directory if it doesn't exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Process file upload
            Part filePart = request.getPart("file");
            String fileName = getFileName(filePart);
            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);

            // Create document object
            Document document = new Document();
            document.setTitle(request.getParameter("title"));
            document.setDescription(request.getParameter("description"));
            document.setFilePath(fileName);
            document.setUploaderId((int) session.getAttribute("userId"));

            // Save document
            if (documentService.uploadDocument(document)) {
                response.sendRedirect("dashboard.jsp?upload=success");
            } else {
                response.sendRedirect("dashboard.jsp?upload=failed");
            }
        } catch (IOException | ServletException e) {
            logger.error("Error processing file upload", e);
            response.sendRedirect("dashboard.jsp?upload=error");
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}

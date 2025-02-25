package com.ed.service;

import java.util.List;

import com.ed.dao.DocumentDAO;
import com.ed.model.Document;

public class DocumentService {
    private final DocumentDAO documentDAO = new DocumentDAO();


    public boolean uploadDocument(Document document) {
        // Validate input
        if (document.getTitle() == null || document.getTitle().trim().isEmpty() ||
            document.getFilePath() == null || document.getFilePath().trim().isEmpty() ||
            document.getUploaderId() <= 0) {
            return false;
        }

        // Create new document
        return documentDAO.createDocument(document);
    }

    public List<Document> getAllDocuments() {
        return documentDAO.getAllDocuments();
    }

    public List<Document> getUserDocuments(int userId) {
        return documentDAO.getDocumentsByUploader(userId);
    }

    public boolean deleteDocument(int documentId) {
        return documentDAO.deleteDocument(documentId);
    }
}

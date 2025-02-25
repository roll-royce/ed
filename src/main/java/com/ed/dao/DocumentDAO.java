package com.ed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ed.model.Document;
import com.ed.util.DatabaseUtil;

public class DocumentDAO {
    private static final Logger logger = LoggerFactory.getLogger(DocumentDAO.class);

    public boolean createDocument(Document document) {
        String sql = "INSERT INTO documents (title, description, file_path, uploader_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, document.getTitle());
            pstmt.setString(2, document.getDescription());
            pstmt.setString(3, document.getFilePath());
            pstmt.setInt(4, document.getUploaderId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error creating document", e);
            return false;
        }
    }

    public List<Document> getAllDocuments() {
        List<Document> documents = new ArrayList<>();
        String sql = "SELECT * FROM documents ORDER BY uploaded_at DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Document doc = new Document();
                doc.setId(rs.getInt("id"));
                doc.setTitle(rs.getString("title"));
                doc.setDescription(rs.getString("description"));
                doc.setFilePath(rs.getString("file_path"));
                doc.setUploaderId(rs.getInt("uploader_id"));
                doc.setUploadedAt(rs.getTimestamp("uploaded_at"));
                documents.add(doc);
            }
        } catch (SQLException e) {
            logger.error("Error getting all documents", e);
        }
        return documents;
    }

    public List<Document> getDocumentsByUploader(int uploaderId) {
        List<Document> documents = new ArrayList<>();
        String sql = "SELECT * FROM documents WHERE uploader_id = ? ORDER BY uploaded_at DESC";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, uploaderId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Document doc = new Document();
                doc.setId(rs.getInt("id"));
                doc.setTitle(rs.getString("title"));
                doc.setDescription(rs.getString("description"));
                doc.setFilePath(rs.getString("file_path"));
                doc.setUploaderId(rs.getInt("uploader_id"));
                doc.setUploadedAt(rs.getTimestamp("uploaded_at"));
                documents.add(doc);
            }
        } catch (SQLException e) {
            logger.error("Error getting documents by uploader", e);
        }
        return documents;
    }

    public boolean deleteDocument(int documentId) {
        String sql = "DELETE FROM documents WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, documentId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error deleting document", e);
            return false;
        }
    }
}

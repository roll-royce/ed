package com.ed.service;

import com.ed.dao.UserDAO;
import com.ed.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
        // Validate input
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() ||
            user.getPassword() == null || user.getPassword().trim().isEmpty() ||
            user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return false;
        }

        // Check if username or email already exists
        if (userDAO.usernameExists(user.getUsername())) {
            return false;
        }
        if (userDAO.emailExists(user.getEmail())) {
            return false;
        }

        // Create new user
        return userDAO.createUser(user);
    }

    public User authenticateUser(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}

package com.gmdb.service;

import com.gmdb.dao.UserDAO;
import com.gmdb.entity.User;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public void registerUser(User user) {
        userDAO.registerUser(user);
    }

    public User login(String email, String password) {
        return userDAO.login(email, password);
    }
}

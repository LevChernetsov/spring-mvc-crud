package com.example.service;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.findAll();
    }


    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userDao.findById(id);
    }


    @Transactional
    public void saveUser(User user) {
        userDao.save(user);
    }


    @Transactional
    public void updateUser(User user) {
        userDao.update(user);
    }


    @Transactional
    public void deleteUser(Long id) {
        userDao.delete(id);
    }


    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
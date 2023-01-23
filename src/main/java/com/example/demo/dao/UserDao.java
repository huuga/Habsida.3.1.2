package com.example.demo.dao;

import com.example.demo.entity.User;

import java.util.List;

public interface UserDao {
   public List<User> getUsersList();
   public void addUser(User user);
   public void removeUser(Long id);
   public User findUserById(Long id);
   public void updateUser(User user);
}

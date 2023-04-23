package com.project.XX.service;

import java.util.List;

import com.project.XX.entity.User;

public interface IUserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User addUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

}

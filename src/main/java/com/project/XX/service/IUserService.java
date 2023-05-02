package com.project.XX.service;

import java.util.List;

import com.project.XX.entity.User;
import com.project.XX.util.Role;

public interface IUserService {

    List<User> getAllUsers();

    User getUserById(Long id);
    
    User getUserByRole(Role role);

    User addUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

}

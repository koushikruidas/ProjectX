package com.project.XX.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.XX.entity.User;
import com.project.XX.model.UserDto;
import com.project.XX.service.IUserService;
import com.project.XX.util.Role;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private ModelMapper mapper;

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @GetMapping("role/{role}")
    public User getUserByRole(@PathVariable Role role) {
        return userService.getUserByRole(role);
    }

    @PostMapping("")
    public User addUser(@RequestBody UserDto userDto) {
    	User user = mapper.map(userDto, User.class);
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
    	User user = mapper.map(userDto, User.class);
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}


package com.project.XX.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.XX.entity.User;
import com.project.XX.exception.UserNotFoundException;
import com.project.XX.repo.UserRepository;
import com.project.XX.util.Role;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User not found with id : " + id);
        }
    }
    
    @Override
    public User getUserByRole(Role role) {
        Optional<User> user = userRepository.findByRole(role);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException("User not found with id : " + role);
        }
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found with id : " + id);
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User not found with id : " + id);
        }
    }
    
    public Set<User> getUserByIdAndRole(List<Long> ids, Role role){
    	Set<User> userList = new HashSet<User>();
    	for(int i = 0; i < ids.size(); i++) {
    		Optional<User> user = userRepository.findByIdAndRole(ids.get(i), role);
    		if(user.isPresent()) {
    			userList.add(user.get());
    		}
    	}
    	return userList;
    }

}


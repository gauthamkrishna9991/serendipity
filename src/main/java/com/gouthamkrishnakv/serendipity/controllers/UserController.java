package com.gouthamkrishnakv.serendipity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gouthamkrishnakv.serendipity.mappers.UserMapper;
import com.gouthamkrishnakv.serendipity.models.User;

@RestController
@RequestMapping("/api/user")
public class UserController {
    // Autowire the User Mapper Created
    @Autowired
    UserMapper userMapper;
    
    // A test route
    @GetMapping("/ping")
    public String ping() {
        return "Pong!";
    }

    // CREATE

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") Long id) {
        return this.userMapper.findUserById(id);
    }
    
    @GetMapping("")
    public List<User> getUsers() {
        return this.userMapper.findAllUsers();
    }
}

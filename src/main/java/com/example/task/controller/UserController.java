package com.example.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.task.model.User;
import com.example.task.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/find/{id}")
    public User getUserById(@PathVariable int id){
        return userService.findUserById(id);
    }

    @GetMapping("/getCreatorId")
    public int getUserById(){
        return userService.getCreatorId();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/update/{id}")
    public void updateUser(@RequestBody User user, @PathVariable int id){
       userService.updateUser(user, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }
}

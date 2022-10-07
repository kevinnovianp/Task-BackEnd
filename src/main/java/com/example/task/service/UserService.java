package com.example.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.task.model.User;
import com.example.task.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private int currUserId = 1;
    private int isLogin = 0;

    public User addUser(User user){
        user.setId(this.currUserId);
        this.currUserId++;
        return userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user, int id){
        User currUser = userRepository.findById(id).orElse(null);
        currUser.setUsername(user.getUsername());
        currUser.setPassword(user.getPassword());
        return userRepository.save(currUser);
    }
    
    public User findUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(int id){
        User currUser = userRepository.findById(id).orElse(null);
        userRepository.delete(currUser);
    }

    public int getIdLogin(){
        return this.isLogin;
    }

    public void setIdLogin(int id){
        this.isLogin = id;
    }
}

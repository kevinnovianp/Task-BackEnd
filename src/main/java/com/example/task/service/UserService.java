package com.example.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.task.config.SecurityConfig;
import com.example.task.model.CustomUserDetail;
import com.example.task.model.User;
import com.example.task.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private int creatorId = 0;
    private int currUserId = 3;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User addUser(User user){
        this.currUserId++;
        user.setId(this.currUserId);
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
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

    public int getCreatorId(){
        return this.creatorId;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found!");
        }
        this.creatorId = user.getId();
        return new CustomUserDetail(user);
        // return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}

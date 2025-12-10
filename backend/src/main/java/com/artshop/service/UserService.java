package com.artshop.service;

import com.artshop.model.User;
import com.artshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        user.setPassword(hashPassword(user.getPassword()));
        user.setCreatedAt(System.currentTimeMillis());
        user.setActive(true);
        return userRepository.save(user);
    }
    
    public User loginUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }
        User user = userOpt.get();
        if (!verifyPassword(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        user.setLastLogin(System.currentTimeMillis());
        userRepository.save(user);
        return user;
    }
    
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
    
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    private boolean verifyPassword(String rawPassword, String hashedPassword) {
        String hashedInput = hashPassword(rawPassword);
        return hashedInput.equals(hashedPassword);
    }
}

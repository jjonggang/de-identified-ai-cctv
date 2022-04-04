package com.example.aicctvbackend.service.user;

import com.example.aicctvbackend.domain.user.User;
import com.example.aicctvbackend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User create(final User user){
        if(user == null || user.getEmail()==null){
            throw new RuntimeException("Invalid arguments");
        }
        final String email = user.getEmail();
        if(userRepository.existsByEmail(email)){
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }

    public User getByCredentials(final String email, final String password){
        return userRepository.findByEmailAndPassword(email, password);
    }
}
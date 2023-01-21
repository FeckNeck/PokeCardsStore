package com.feckneck.pokeCardsserver.auth;

import com.feckneck.pokeCardsserver.user.User;
import com.feckneck.pokeCardsserver.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {


    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user) {
        Optional<User> email = userRepository.findUserByEmail(user.getEmail());
        if(email.isPresent()){
            throw new IllegalStateException("email not valid");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}

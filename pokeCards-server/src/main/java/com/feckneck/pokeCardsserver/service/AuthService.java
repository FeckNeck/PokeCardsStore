package com.feckneck.pokeCardsserver.service;

import com.feckneck.pokeCardsserver.repository.UserRepository;
import com.feckneck.pokeCardsserver.handler.BadRequestException;
import com.feckneck.pokeCardsserver.model.Role;
import com.feckneck.pokeCardsserver.model.User;
import com.feckneck.pokeCardsserver.validator.CreateUserValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService{
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> addUser(User user) {
        CreateUserValidator validator = new CreateUserValidator();
        Optional<String> error = validator.validate(user, userRepository);
        if (error.isPresent()) {
            throw new BadRequestException(error.get());
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        userRepository.save(user);
        return new ResponseEntity<>("User successfully created !", HttpStatus.CREATED);
    }
}

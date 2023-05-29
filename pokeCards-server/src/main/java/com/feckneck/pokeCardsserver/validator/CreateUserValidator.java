package com.feckneck.pokeCardsserver.validator;

import com.feckneck.pokeCardsserver.model.User;
import com.feckneck.pokeCardsserver.repository.UserRepository;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class CreateUserValidator {

    public Optional<String> validate(User user, UserRepository userRepository) {
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
        String confirmPassword = user.getConfirmPassword();

        if (email == null || email.isEmpty()) {
            return Optional.of("${messages.email.empty}");
        }
        if (username == null || username.isEmpty()) {
            return Optional.of("messages.username.empty");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            return Optional.of("messages.email.unique");
        }
        if (userRepository.findByUsername(username).isPresent()) {
            return Optional.of("messages.username.unique");
        }
        if (password == null || password.isEmpty()) {
            return Optional.of("messages.password.empty");
        }
        if (password.length() < 8) {
            return Optional.of("messages.password.min");
        }
        if (!password.equals(confirmPassword)) {
            return Optional.of("messages.password_confirmation.confirmed");
        }

        return Optional.empty();
    }
}

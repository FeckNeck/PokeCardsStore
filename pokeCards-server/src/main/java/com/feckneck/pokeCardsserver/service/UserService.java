package com.feckneck.pokeCardsserver.service;
import com.feckneck.pokeCardsserver.model.User;
import com.feckneck.pokeCardsserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }
    public void UpdateUser(User user){
        userRepository.save(user);
    }
}

package com.feckneck.pokeCardsserver.controller;


import com.feckneck.pokeCardsserver.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value="/getUsers")
    @PreAuthorize("hasAuthority('Admin')")
    public String getUsers(){
        return "getUsers";
    }

    @GetMapping(value = "/")
    @PreAuthorize("hasAnyAuthority('SCOPE_USER')")
    public Map<String, Object> xd(Authentication authentication){
        return Map.of(
                "authorities",authentication.getAuthorities(),
                "credentials",authentication.getCredentials()
        );
    }

    @GetMapping(value = "/saveData")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public Map<String, Object> saveData(String data){
        return Map.of("data",data);
    }


}

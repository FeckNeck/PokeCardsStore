package com.feckneck.pokeCardsserver.auth;


import com.feckneck.pokeCardsserver.user.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private JwtEncoder jwtEncoder;
    private AuthenticationManager authenticationManager;
    private AuthService authService;
    public AuthController(JwtEncoder jwtEncoder, AuthenticationManager authenticationManager, AuthService authService) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user){
        System.out.println(user);
        authService.addUser(user);
    }

    @PostMapping("/login")
    public Map<String,String> jwtToken(@RequestBody User user){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        Map<String,String> token = new HashMap<>();
        Instant instant = Instant.now();

        String scope = authentication.getAuthorities()
                .stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(instant)
                .expiresAt(instant.plus(1, ChronoUnit.MINUTES))
                .claim("scope",scope)
                .build();

        JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(instant)
                .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                .build();

        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        String refreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
        token.put("accessToken",accessToken);
        token.put("refreshToken",refreshToken);

        return token;
    }
}

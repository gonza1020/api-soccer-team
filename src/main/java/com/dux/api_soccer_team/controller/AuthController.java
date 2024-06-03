package com.dux.api_soccer_team.controller;

import com.dux.api_soccer_team.dto.JwtAuthenticationResponse;
import com.dux.api_soccer_team.dto.User;
import com.dux.api_soccer_team.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            // Authenticate the user using the provided credentials
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            boolean passwordMatches = new BCryptPasswordEncoder().matches(user.getPassword(), userDetails.getPassword());
            if (!passwordMatches) {
                throw new BadCredentialsException("Usuario o contraseña incorrectos"); // Handle incorrect password
            }
            // Generate and return JWT token
            String token = jwtTokenProvider.createToken(userDetails);
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
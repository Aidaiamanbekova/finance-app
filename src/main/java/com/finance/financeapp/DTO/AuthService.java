package com.finance.financeapp.DTO;

import com.finance.financeapp.entities.User;
import com.finance.financeapp.entities.Role;
import com.finance.financeapp.security.JwtUtil;
import com.finance.financeapp.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authManager, UserRepository userRepo, PasswordEncoder encoder, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    public String register(AuthRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setEmail(request.getUsername() + "@example.com"); // simplified
        user.setRole(Role.USER);
        userRepo.save(user);
        return "User registered";
    }

    public String login(AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        return jwtUtil.generateToken(request.getUsername());
    }
}

package org.onlinecinema.authservice.service;

import lombok.RequiredArgsConstructor;
import org.onlinecinema.authservice.dto.AuthResponse;
import org.onlinecinema.authservice.dto.LoginRequest;
import org.onlinecinema.authservice.dto.RegisterRequest;
import org.onlinecinema.authservice.entities.User;
import org.onlinecinema.authservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Le nom d'utilisateur est déjà utilisé");
        }
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("L'email est déjà utilisé");
        }
        
        User user = new User(
            request.getUsername(),
            request.getEmail(),
            passwordEncoder.encode(request.getPassword())
        );
        
        user = userRepository.save(user);
        
        String token = jwtService.generateToken(user.getUsername(), user.getEmail(), user.getRoles());
        
        return new AuthResponse(token, user.getUsername(), user.getEmail(), user.getRoles());
    }
    
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsernameOrEmail())
            .orElse(userRepository.findByEmail(request.getUsernameOrEmail())
                .orElseThrow(() -> new RuntimeException("Nom d'utilisateur ou mot de passe incorrect")));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Nom d'utilisateur ou mot de passe incorrect");
        }
        
        String token = jwtService.generateToken(user.getUsername(), user.getEmail(), user.getRoles());
        
        return new AuthResponse(token, user.getUsername(), user.getEmail(), user.getRoles());
    }
    
    public Boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}

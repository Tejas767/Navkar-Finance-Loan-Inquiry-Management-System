package com.inquiry.loan.controller;

import com.inquiry.loan.entity.UserEntity;
import com.inquiry.loan.entity.Role;
import com.inquiry.loan.repository.UserRepository;
import com.inquiry.loan.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus; // 💡 Added import
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List; // 💡 Added import
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> body) {
        String username = body.get("username");
        String password = body.get("password");
        // role changed to USER 
        String roleStr = body.getOrDefault("role", "USER"); 
        
        Role role = roleStr.equalsIgnoreCase("ADMIN") ? Role.ROLE_ADMIN : Role.ROLE_USER;

        if (userRepo.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error","User exists"));
        }

        //  Enforce only one ROLE_ADMIN
        if (role == Role.ROLE_ADMIN) {
            List<UserEntity> existingAdmins = userRepo.findAllByRole(Role.ROLE_ADMIN);
            if (!existingAdmins.isEmpty()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                       .body(Map.of("error", "An administrator already exists. Cannot register new admin."));
            }
        }
        
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepo.save(user);
        return ResponseEntity.ok(Map.of("message","User created"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body) {
        String username = body.get("username");
        String password = body.get("password");
        UserEntity u = userRepo.findByUsername(username).orElse(null);
        if (u == null) return ResponseEntity.status(401).body(Map.of("error","Invalid credentials"));
        if (!passwordEncoder.matches(password, u.getPassword())) return ResponseEntity.status(401).body(Map.of("error","Invalid credentials"));
        String token = jwtUtil.generateToken(u.getUsername(), u.getRole().name());
        return ResponseEntity.ok(Map.of("token", token, "role", u.getRole().name()));
    }
    
    
    
    
    @GetMapping("/me")
    public ResponseEntity<Map<String,String>> me() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(Map.of("username", username));
    }
}
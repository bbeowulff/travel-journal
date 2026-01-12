package com.example.traveljournal.Service;

import com.example.traveljournal.Dto.LoginRequest;
import com.example.traveljournal.Dto.LoginResponse;
import com.example.traveljournal.Entity.User;
import com.example.traveljournal.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest req) {
        User u = userRepository.findByEmailIgnoreCase(req.email())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Invalid credentials"
                ));

        if (!u.getPassword().equals(req.password())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials");
        }

        return new LoginResponse(u.getId(), u.getEmail(), "Login successful");
    }
}

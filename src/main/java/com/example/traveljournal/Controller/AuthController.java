package com.example.traveljournal.Controller;

import com.example.traveljournal.Dto.LoginRequest;
import com.example.traveljournal.Dto.LoginResponse;
import com.example.traveljournal.Service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel-journal")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}



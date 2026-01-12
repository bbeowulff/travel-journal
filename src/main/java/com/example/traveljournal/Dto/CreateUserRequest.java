package com.example.traveljournal.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank @Size(max = 120) String name,
        @NotBlank @Size(max = 120) String surname,
        @NotBlank @Email @Size(max = 200) String email,
        @NotBlank @Size(min = 16, max = 128) String password
) {}


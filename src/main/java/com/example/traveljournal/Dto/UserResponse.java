package com.example.traveljournal.Dto;

public record UserResponse(
        Long id,
        String name,
        String surname,
        String email
) {}


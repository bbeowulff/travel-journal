package com.example.traveljournal.Dto;

public record LoginResponse(
        Long userId,
        String email,
        String message
) {}


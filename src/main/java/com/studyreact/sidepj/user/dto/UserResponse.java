package com.studyreact.sidepj.user.dto;

public record UserResponse(
        String name,
        String email,
        String password
) {
}
package com.studyreact.sidepj.user.dto;

public record LoginRequest(
        String email,
        String password
) {
}
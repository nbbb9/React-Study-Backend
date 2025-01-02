package com.studyreact.sidepj.user.dto;

public record UserRequest(
        String name,
        String email,
        String password
) {
}
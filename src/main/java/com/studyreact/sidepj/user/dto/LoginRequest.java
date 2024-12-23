package com.studyreact.sidepj.user.dto;

import com.studyreact.sidepj.user.User;

public record LoginRequest(
        String email,
        String password
) {
    public static User toUser(LoginRequest request){
        return new User(
                request.email(),
                request.password()
        );
    }
}
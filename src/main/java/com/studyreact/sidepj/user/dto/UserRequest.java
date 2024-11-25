package com.studyreact.sidepj.user.dto;

import com.studyreact.sidepj.user.User;

public record UserRequest(
        String name,
        String email,
        String password
) {
    public static User toUser(UserRequest request){
        return new User(
                request.name(),
                request.email(),
                request.password()
        );
    }
}

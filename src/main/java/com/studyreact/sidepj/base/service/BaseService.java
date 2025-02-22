package com.studyreact.sidepj.base.service;

import com.studyreact.sidepj.user.User;
import com.studyreact.sidepj.user.UserRepository;
import configs.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
public class BaseService {

    private final JwtUtil jwtUtil;
    protected final UserRepository userRepository;

    protected User getUserByToken(String token) {
        String userEmail = jwtUtil.getEmailFromToken(token);
        return userRepository.findByEmail((userEmail))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "인증 실패, 다시 로그인해주세요."));
    }

}
package com.studyreact.sidepj.user;

import com.studyreact.sidepj.user.dto.LoginRequest;
import com.studyreact.sidepj.user.dto.UserRequest;
import configs.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * 회원가입
     *
     * @param request UserRequest
     * @return void
     * */
    @Transactional
    public void signup(UserRequest request) {
        String encryptedPassword = passwordEncoder.encode(request.password());//비밀번호 암호화
        User user = new User(request.name(), request.email(), encryptedPassword);
        userRepository.save(user);
    }

    /**
     * 로그인
     * @param request LoginRequest
     * @return void
     * */
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())//이메일 기반으로 사용자 찾기
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "틀린 이메일 또는 비밀번호 입니다."));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "틀린 이메일 또는 비밀번호 입니다.");
        }
        return jwtUtil.generateToken(user.getEmail());
    }

}
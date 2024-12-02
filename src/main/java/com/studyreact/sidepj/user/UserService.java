package com.studyreact.sidepj.user;

import com.studyreact.sidepj.user.dto.LoginRequest;
import com.studyreact.sidepj.user.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    public final UserRepository userRepository;

    public void signup(UserRequest request){
        User user = UserRequest.toUser(request);
        userRepository.save(user);
    }

    public void login(LoginRequest request){
        log.info("컨트롤러 - 로그인 입력값 : email={}, password={}", request.email(), request.password());
        User user = LoginRequest.toUser(request);
        log.info("서비스 - 변환된 User 객체 : email={}, password={}", user.getEmail(), user.getPassword());

        userRepository.findByEmailAndPassword(user.email, user.password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));
    }

}

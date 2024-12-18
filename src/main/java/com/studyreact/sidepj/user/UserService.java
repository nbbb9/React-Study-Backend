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

    /**
     * 회원가입
     *
     * @param request UserRequest
     * @return void
     * */
    @Transactional
    public void signup(UserRequest request){
        User user = UserRequest.toUser(request);
        userRepository.save(user);
    }

    /**
     * 로그인
     * @param request LoginRequest
     * @return void
     * */
    public void login(LoginRequest request){
        User user = LoginRequest.toUser(request);
        userRepository.findByEmailAndPassword(user.email, user.password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));
    }

}
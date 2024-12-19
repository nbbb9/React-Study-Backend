package com.studyreact.sidepj.user;

import com.studyreact.sidepj.user.dto.LoginRequest;
import com.studyreact.sidepj.user.dto.UserRequest;
import configs.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @Transactional
//    public void signup(UserRequest request){
//        User user = UserRequest.toUser(request);
//        userRepository.save(user);
//    }
    @Transactional
    public void signup(UserRequest request) {
        String encryptedPassword = passwordEncoder.encode(request.password());
        User user = new User(request.name(), request.email(), encryptedPassword);
        userRepository.save(user);
    }


    /**
     * 로그인
     * @param request LoginRequest
     * @return void
     * */
//    public void login(LoginRequest request){
//        User user = LoginRequest.toUser(request);
//        userRepository.findByEmailAndPassword(user.email, user.password)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));
//    }
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

}
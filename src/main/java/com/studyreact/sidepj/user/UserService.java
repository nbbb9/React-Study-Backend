package com.studyreact.sidepj.user;

import com.studyreact.sidepj.user.dto.LoginRequest;
import com.studyreact.sidepj.user.dto.IsExistUserRequest;
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
public class UserService {

    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * 회원가입
     * @param request UserRequest
     * @return void
     * */
    @Transactional
    public void signup(UserRequest request) {
        String encryptedPassword = passwordEncoder.encode(request.password());//비밀번호 암호화
        User user = new User();
        user.save(request, encryptedPassword);
        userRepository.save(user);
    }

    /**
     * 로그인
     * @param request LoginRequest
     * @return void
     * */
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())//이메일 기반으로 사용자 찾기
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not exist User"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not matched password");
        }
        return jwtUtil.generateToken(user.getEmail());
    }

    /**
     * 이름으로 이메일 찾기
     * @param name
     * @return String
     */
    public String findMyEmail(String name){
        return userRepository.findEmailByName(name);
    }

    /**
     * 이름과 이메일로 사용자 존재 여부 판단
     * @param request IsExistUserRequest
     * @return "exist" or "notExist"
     */
    public String isExistUser(IsExistUserRequest request) {
        boolean userExists = userRepository.existsByNameAndEmail(request.name(), request.email());
        return userExists ? "exist" : "notExist";
    }

    /**
     * 사용자 삭제처리
     * @param
     * @return void
     */
    @Transactional
    public void deleteUser(String userId){
        User user  = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not exist user"));
        user.delete();
    }

}
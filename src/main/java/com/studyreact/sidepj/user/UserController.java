package com.studyreact.sidepj.user;

import com.studyreact.sidepj.user.dto.LoginRequest;
import com.studyreact.sidepj.user.dto.UserRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/reactstudy")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @PostMapping("/signup")//회원가입
    public ResponseEntity<?> signUp(@RequestBody UserRequest request , HttpServletResponse response){
        userService.signup(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")//로그인
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response){
        log.info("로그인 입력값 : email={}, password={}", request.email(), request.password());
        userService.login(request);
        return ResponseEntity.ok().build();
    }

}

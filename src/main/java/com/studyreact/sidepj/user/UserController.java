package com.studyreact.sidepj.user;

import com.studyreact.sidepj.user.dto.IsExistUserRequest;
import com.studyreact.sidepj.user.dto.LoginRequest;
import com.studyreact.sidepj.user.dto.UserRequest;
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
    public ResponseEntity<Void> signUp(@RequestBody UserRequest request){
        userService.signup(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")//로그인
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));//토큰 리턴
    }

    @GetMapping("/findMyEmail")//이름으로 이메일 찾기
    public ResponseEntity<String> findMyEmail(@RequestParam String name){
        return ResponseEntity.ok(userService.findMyEmail(name));
    }

    @GetMapping("/existUser")//사용자 존재여부 판단
    public ResponseEntity<String> isExistUser(@ModelAttribute IsExistUserRequest request) {
        return ResponseEntity.ok(userService.isExistUser(request));
    }

//    @PutMapping("/modPassword")//이름과 이메일로 비밀번호 변경
//    public ResponseEntity<Void> modPassword(@ModelAttribute ModPasswordRequest request){
//        return ResponseEntity.ok(userService.modPassword(request));
//    }

}
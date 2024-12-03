package com.studyreact.sidepj.post;

import com.studyreact.sidepj.user.dto.LoginRequest;
import com.studyreact.sidepj.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/reactstudy")
@RequiredArgsConstructor
public class PostController {

    public final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam(required = false) MultipartFile image,
            @AuthenticationPrincipal LoginRequest user) {

        postService.createPost(title, description, image, user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
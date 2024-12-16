package com.studyreact.sidepj.post;

import com.studyreact.sidepj.post.dto.PostRequest;
import com.studyreact.sidepj.post.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/reactstudy")
@RequiredArgsConstructor
public class PostController {

    public final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @PostMapping("/addpost")
    public ResponseEntity<?> createPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        String imageUrl = null;

        if (image != null && !image.isEmpty()) {
            // 이미지 업로드 로직 (예: S3, 로컬 디렉토리 등)
            imageUrl = postService.uploadImage(image); // 이미지 업로드 후 URL 반환
        }

        PostRequest request = new PostRequest(title, content, imageUrl);
        postService.createPost(request);
        return ResponseEntity.ok().build();
    }



}
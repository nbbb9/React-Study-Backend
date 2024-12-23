package com.studyreact.sidepj.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyreact.sidepj.post.dto.PostRequest;
import com.studyreact.sidepj.post.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/reactstudy")
@RequiredArgsConstructor
public class PostController {

    public final PostService postService;

    @GetMapping//전체 게시글 조회
    public ResponseEntity<List<PostResponse>> getPosts() {
        return ResponseEntity.ok(postService.getPosts());
    }

//    @PostMapping("/addpost")//게시글 저장
//    public ResponseEntity<?> createPost(@RequestPart("file") PostRequest request,
//                                        //@RequestHeader("Authorization") String token >> 나중에 기능 구현
//                                        @RequestPart(value = "image", required = false) MultipartFile image){
//        log.info("request : " + request);
//        log.info("image : " + image);
//        postService.createPost(request, image);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/addpost")
    public ResponseEntity<?> createPost (@ModelAttribute PostRequest request)throws IOException {
        log.info("제발!!!!! : " + request);
        Posts createdPost = postService.createPost(request);
        return ResponseEntity.ok().build();
    }

}
package com.studyreact.sidepj.post;

import com.studyreact.sidepj.post.dto.PostRequest;
import com.studyreact.sidepj.post.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addpost")//게시글 저장
    public ResponseEntity<Void> createPost (@RequestBody PostRequest request)throws IOException {
        Posts createdPost = postService.createPost(request);
        return ResponseEntity.ok().build();
    }

}
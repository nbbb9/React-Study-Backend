package com.studyreact.sidepj.post;

import com.studyreact.sidepj.post.dto.PostRequest;
import com.studyreact.sidepj.post.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    public final PostRepository postRepository;

    public List<PostResponse> getPosts() {
        return postRepository.findAll().stream()
                .map(PostResponse::toResponse)
                .toList();
    }

    public void createPost(PostRequest request){
        Posts posts = PostRequest.toPosts(request);
        postRepository.save(posts);
    }

    public String uploadImage(MultipartFile image) {
        try {
            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path imagePath = Paths.get("/tmp/uploads/" + fileName);// 로컬 디렉토리에 저장
            if (!Files.exists(imagePath)) {
                Files.createDirectories(imagePath); // 디렉토리가 없으면 생성
            }
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + fileName; // 저장된 파일 경로 반환
        } catch (IOException e) {
            throw new RuntimeException("이미지 업로드 실패", e);
        }
    }
}

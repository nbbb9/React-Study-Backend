package com.studyreact.sidepj.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    public final PostRepository postRepository;

    public void createPost(String title, String description, MultipartFile image, String username) {
        String imageUrl = null;

        if (image != null && !image.isEmpty()) {
            imageUrl = uploadImageToStorage(image); // 이미지 업로드 후 URL 반환
        }

        Posts post = new Posts(title, description, imageUrl, username);
        postRepository.save(post);
    }

    private String uploadImageToStorage(MultipartFile image) {
        // 이미지 파일 업로드 로직
        return "https://example.com/images/" + image.getOriginalFilename();
    }

}

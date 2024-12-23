package com.studyreact.sidepj.post.dto;

import com.studyreact.sidepj.post.Posts;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public record PostRequest(
        String title,
        String content,
        MultipartFile image
) {
    public Posts toEntity(String image)throws IOException {
        return Posts.builder()
                .title(title)
                .content(content)
                .imageUrl(image)
                .build();
    }
}
package com.studyreact.sidepj.post.dto;

import com.studyreact.sidepj.post.Posts;
import com.studyreact.sidepj.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public record PostRequest(
        String id,
        String title,
        String content,
        String userId
) {

    public PostRequest from(Posts post, User user){
        return new PostRequest(
                post.getId().toString(),
                post.getTitle(),
                post.getContent(),
                user.getId().toString()
        );
    }
}
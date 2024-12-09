package com.studyreact.sidepj.post.dto;

import com.studyreact.sidepj.post.Posts;

public record PostResponse(
        String title,
        String content,
        String imageUrl
) {
    public static PostResponse toResponse(Posts posts){
        return new PostResponse (
                posts.getTitle(),
                posts.getContent(),
                posts.getImageUrl()
        );
    }
}
package com.studyreact.sidepj.post.dto;

import com.studyreact.sidepj.post.Posts;

public record PostRequest(
        String title,
        String content,
        String imageUrl
) {
    public static Posts toPosts(PostRequest request){
        return new Posts(
                request.title(),
                request.content(),
                request.imageUrl()
        );
    }
}

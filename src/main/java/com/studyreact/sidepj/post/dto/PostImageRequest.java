package com.studyreact.sidepj.post.dto;

import com.studyreact.sidepj.post.Posts;

public record PostImageRequest(
        String imageurl
) {
    public static Posts toPosts(PostImageRequest request){
        return new Posts(
                request.imageurl()
        );
    }
}

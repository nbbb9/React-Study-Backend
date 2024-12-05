package com.studyreact.sidepj.post.dto;

import com.studyreact.sidepj.post.Posts;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public record PostResponse(
        String title,
        String content
) {
    public static PostResponse toResponse(Posts posts){
        return new PostResponse (
                posts.getTitle(),
                posts.getContent()
        );
    }
}


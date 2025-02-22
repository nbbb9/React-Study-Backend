package com.studyreact.sidepj.post;

import com.studyreact.sidepj.base.entities.BaseEntity;
import com.studyreact.sidepj.post.dto.PostRequest;
import com.studyreact.sidepj.user.User;
import io.jsonwebtoken.lang.Assert;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table(name = "posts")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Posts extends BaseEntity{

    @Column(name = "title", nullable = false)
    @Comment(value = "게시글 제목")
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    @Comment(value = "게시글 내용")
    private String content;

    @Column(name = "image_url")
    @Comment(value = "이미지 URL")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    @Comment(value = "사용자Id")
    private String userId;

    @Column(name = "is_delete", nullable = false, columnDefinition = "boolean default false")
    private boolean isDelete;

    @Builder
    public Posts(String title, String content, String imageUrl) {
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(content, "내용은 필수입니다.");
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public void save(PostRequest request, String imageUrl, User user){
        duplicationField(request);
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public void update(PostRequest request, String imageUrl){
        duplicationField(request);
        this.imageUrl = imageUrl;
    }

    public void delete(){
        this.isDelete = true;
    }

    private void duplicationField(PostRequest request){
        this.title  = request.title();
        this.content = request.content();
    }

}
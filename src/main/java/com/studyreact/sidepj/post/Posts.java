package com.studyreact.sidepj.post;

import com.studyreact.sidepj.base.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
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

    @Column(nullable = false)
    @Comment(value = "게시글 제목")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Comment(value = "게시글 내용")
    private String content;

    @Column
    @Comment(value = "이미지 URL")
    private String imageUrl;

    @Column
    @Comment(value = "사용자 이름")
    private String username;

    public Posts(String title, String content){
        this.title = title;
        this.content = content;
    }
    public Posts(String imageUrl){
        this.imageUrl = imageUrl;
    }

}
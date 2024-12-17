package com.studyreact.sidepj.user;

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

@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class User extends BaseEntity {

    @Column(nullable = false)
    @Comment("이름")
    String name;

    @Column(nullable = false)
    @Comment("이메일")
    String email;

    @Column(nullable = false)
    @Comment("비밀번호")
    String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
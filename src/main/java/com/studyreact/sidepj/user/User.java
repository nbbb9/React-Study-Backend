package com.studyreact.sidepj.user;

import com.studyreact.sidepj.base.entities.BaseEntity;
import com.studyreact.sidepj.user.dto.UserRequest;
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
    @Comment(value = "이름")
    String name;

    @Column(nullable = false)
    @Comment(value = "이메일")
    String email;

    @Column(nullable = false)
    @Comment(value = "비밀번호")
    String password;

    @Column(name = "is_delete", nullable = false, columnDefinition = "boolean default false")
    private boolean isDelete;

    public void save(UserRequest request, String encryptedPassword){
        duplicationField(request);
        this.password = encryptedPassword;
    }

    public void update(UserRequest request, String encryptedPassword){
        duplicationField(request);
        this.password = encryptedPassword;
    }

    public void delete(){
        this.isDelete = true;
    }

    private void duplicationField(UserRequest request){
        this.name = request.name();
        this.email = request.email();
    }

}
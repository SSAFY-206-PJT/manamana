package com.webtoon.manamana.entity.user;

import com.webtoon.manamana.config.entity.BaseTimeEntity;
import com.webtoon.manamana.entity.webtoon.Comment;
import com.webtoon.manamana.user.dto.request.UserUpdateRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private int age;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "refresh_token")
    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private LoginProvider loginProvider;

    //유저가 쓴 댓글을 조회하는 요구사항이 있기 때문에 필요.
    @OneToMany(mappedBy = "user")
    private List<Comment> comment = new ArrayList<>();

    //유저 정보 수정
    public void updateUser(UserUpdateRequestDTO userUpdateRequestDTO){

        if(userUpdateRequestDTO.getNickname() != null){
            this.nickname = userUpdateRequestDTO.getNickname();
        }
        if(userUpdateRequestDTO.getUserImage() != null){
            this.imagePath = userUpdateRequestDTO.getUserImage();
        }
    }

    //유저 정보 삭제
    public void removeUser(){
        this.isDeleted = true;
    }

}

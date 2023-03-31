package com.manamana.crawling.entity.user;

import com.manamana.crawling.config.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


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
//    @OneToMany(mappedBy = "user")
//    private List<Comment> comment = new ArrayList<>();

    //유저 정보 수정
//    public void updateUser(UserUpdateRequestDTO userUpdateRequestDTO){
//
//        if(userUpdateRequestDTO.getNickname() != null){
//            this.nickname = userUpdateRequestDTO.getNickname();
//        }
//        if(userUpdateRequestDTO.getUserImage() != null){
//            this.imagePath = userUpdateRequestDTO.getUserImage();
//        }
//    }
    @Builder
    public User(long id, String email, String nickname, String imagePath, String gender, int age, boolean isDeleted, String refreshToken, LoginProvider loginProvider) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.imagePath = imagePath;
        this.gender = gender;
        this.age = age;
        this.isDeleted = isDeleted;
        this.refreshToken = refreshToken;
        this.loginProvider = loginProvider;
    }



    //유저 정보 삭제
    public void removeUser(){
        this.isDeleted = true;
    }

}

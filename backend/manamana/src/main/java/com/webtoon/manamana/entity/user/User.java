package com.webtoon.manamana.entity.user;

//import com.webtoon.manamana.auth.oauth2.dto.OAuth2UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webtoon.manamana.auth.oauth2.dto.OAuth2UserInfo;
import com.webtoon.manamana.config.entity.BaseTimeEntity;
import com.webtoon.manamana.entity.webtoon.Comment;
import com.webtoon.manamana.user.dto.request.UserUpdateRequestDTO;
import lombok.Builder;
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
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> comment = new ArrayList<>();

    @Builder
    public User(String email, String nickname, String imagePath, String gender, int age, boolean isDeleted, LoginProvider loginProvider) {
        this.email = email;
        this.nickname = nickname;
        this.imagePath = imagePath;
        this.gender = gender;
        this.age = age;
        this.isDeleted = isDeleted;
        this.loginProvider = loginProvider;
    }

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

    //유저 재가입
    public void reSignUp(){
        this.isDeleted = false;
    }

    //TODO : age의 경우, 테이블은 int인데 카카오에서 제공하는 값은 연령대로 1~9 형태임, 따라서 앞의 숫자만 따서 저장.
    //Oauth 로그인시에 정보가 다르면 업데이트 함 - 프로필 사진은 업데이트 안함
    public void loginInfoUpdate(OAuth2UserInfo oAuth2UserInfo){
        this.nickname = oAuth2UserInfo.getName();
        this.email = oAuth2UserInfo.getEmail();
        this.gender = oAuth2UserInfo.getGender();
        this.age = Integer.parseInt(oAuth2UserInfo.getAge().split("~")[0]);
    }

    //유저 생성
    public static User createUser(OAuth2UserInfo oAuth2UserInfo, LoginProvider loginProvider){
        return User.builder()
                .email(oAuth2UserInfo.getEmail())
                .nickname(oAuth2UserInfo.getName())
                .imagePath(oAuth2UserInfo.getProfileImage())
                .age(Integer.parseInt(oAuth2UserInfo.getAge().split("~")[0]))
                .gender(oAuth2UserInfo.getGender())
                .isDeleted(false)
                .loginProvider(loginProvider).build();
    }

    //리프레시 토큰 업데이트
    public void updateRefresh(String refreshToken){
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", isDeleted=" + isDeleted +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}

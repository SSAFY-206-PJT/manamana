package com.webtoon.manamana.user.dto.response;

import com.webtoon.manamana.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/*유저 조회 DTO*/
@Getter
@Setter
public class UserResponseDTO {

    private long id;
    private String email;
    private String nickname;
    private String imagePath;
    private String gender;
    private int age;
    private long likeCount; //관심 웹툰 수
    private long scoreCount; //평가한 웹툰 수

    @Builder
    public UserResponseDTO(long id, String email, String nickname, String imagePath, String gender, int age, long likeCount, long scoreCount) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.imagePath = imagePath;
        this.gender = gender;
        this.age = age;
        this.likeCount = likeCount;
        this.scoreCount = scoreCount;
    }

    public static UserResponseDTO createDTO(User user,Long likeCount, Long scoreCount){

        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .imagePath(user.getImagePath())
                .gender(user.getGender())
                .age(user.getAge())
                .likeCount(likeCount)
                .scoreCount(scoreCount).build();
    }
}

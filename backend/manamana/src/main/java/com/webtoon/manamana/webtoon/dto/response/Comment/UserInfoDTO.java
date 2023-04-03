package com.webtoon.manamana.webtoon.dto.response.comment;

import com.webtoon.manamana.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {

    private long id;
    private String nickname;
    private String imagePath;


    @Builder
    public UserInfoDTO(long id, String nickname, String imagePath) {
        this.id = id;
        this.nickname = nickname;
        this.imagePath = imagePath;
    }

    public static UserInfoDTO createDTO(User user){
        return UserInfoDTO.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .imagePath(user.getImagePath()).build();

    }
}

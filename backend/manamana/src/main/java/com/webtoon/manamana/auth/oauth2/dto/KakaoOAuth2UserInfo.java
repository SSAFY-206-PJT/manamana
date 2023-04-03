package com.webtoon.manamana.auth.oauth2.dto;

/*카카오 계정 정보 받아오는 DTO*/

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    private Long id;

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super((Map<String, Object>) attributes.get("kakao_account"));
        this.id = (Long) attributes.get("id");
    }

    //id
    @Override
    public String getId() {
        return this.id.toString();
    }

    //이메일
    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    //닉네임
    @Override
    public String getName() {
        return (String) ((Map<String, Object>) attributes.get("profile")).get("nickname");
    }

    //이미지
    @Override
    public String getProfileImage() {
        return (String) ((Map<String, Object>) attributes.get("profile")).get("thumbnail_image_url");
    }

    //성별
    @Override
    public String getGender() {
        return (String) attributes.get("gender");
    }

    //연령대
    @Override
    public String getAge() {
        return (String) attributes.get("age_range");
    }
}

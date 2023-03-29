package com.webtoon.manamana.auth.DTO;

import com.webtoon.manamana.auth.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.webtoon.manamana.auth.oauth2.dto.KakaoOAuth2UserInfo;
import com.webtoon.manamana.auth.oauth2.dto.OAuth2UserInfo;
import com.webtoon.manamana.entity.user.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {


    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) throws OAuth2AuthenticationProcessingException {
        if (registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
            return new KakaoOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Unsupported Login Type : " + registrationId);
        }
    }

}

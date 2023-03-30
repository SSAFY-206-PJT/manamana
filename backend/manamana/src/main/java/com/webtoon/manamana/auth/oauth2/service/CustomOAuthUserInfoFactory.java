package com.webtoon.manamana.auth.oauth2.service;

import com.webtoon.manamana.auth.oauth2.dto.KakaoOAuth2UserInfo;
import com.webtoon.manamana.auth.oauth2.dto.OAuth2UserInfo;
import com.webtoon.manamana.auth.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.webtoon.manamana.entity.user.AuthProvider;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomOAuthUserInfoFactory {

    //받아온 데이터를 각 소셜에 맞게 객체와 매핑 시켜줌.
    public OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) throws OAuth2AuthenticationProcessingException {
        if(registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())){
            return new KakaoOAuth2UserInfo(attributes);
        }
        else{
            throw new OAuth2AuthenticationProcessingException("Unsupported Login Type : " + registrationId);
        }

    }
}

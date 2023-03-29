package com.webtoon.manamana.auth.oauth2.service;

import com.webtoon.manamana.auth.oauth2.dto.OAuth2UserInfo;
import com.webtoon.manamana.auth.oauth2.dto.UserPrincipal;
import com.webtoon.manamana.auth.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.webtoon.manamana.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final CustomOAuthUserInfoFactory customOAuthUserInfoFactory;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //userRequest가 Oauth로부터 받아온 정보가 담겨있음 -> access token같은.
        //super.loadUser(userRequest)이 구문을 사용해서 access token으로 실제로 사용하는 속성값들을 가져옴
        OAuth2User oAuth2User = super.loadUser(userRequest); // OAuth 제공자로 부터 정보를 받아오는 객체
//        System.out.println(oAuth2User.getAttributes().toString());
//        System.out.println(userRequest.getClientRegistration());
//        System.out.println(userRequest.getAccessToken();
//        System.out.println(userRequest.getClientRegistration());
//        System.out.println(userRequest.getAdditionalParameters());


        try{
            return processOAuthUser(userRequest, oAuth2User);
        }catch (Exception e){
            throw new InternalAuthenticationServiceException(e.getMessage(),e.getCause());
        }
    }

    /**
     * 사용자 정보 추출 후 시큐리티가 처리할 UserPrincipal 객체를 제공
     */
    private OAuth2User processOAuthUser(OAuth2UserRequest userRequest, OAuth2User oAuth2User) throws OAuth2AuthenticationProcessingException {
        //만들어둔 팩토리에 데이터를 전달해서 어떤 provider인지 판단.
        OAuth2UserInfo oAuth2UserInfo = customOAuthUserInfoFactory
                .getOAuth2UserInfo(userRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        System.out.println(oAuth2UserInfo.toString());

//        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
//            throw new OAuth2AuthenticationProcessingException("OAuth2 공급자(구글, 네이버 등)에서 이메일을 찾을 수 없습니다.");
//        }
//
//        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
//        User user;
//
//        if (userOptional.isPresent()) {
//            user = userOptional.get();
//
//            if (!user.getProvider().equals(AuthProvider.valueOf(userRequest.getClientRegistration().getRegistrationId()))) {
//                throw new OAuth2AuthenticationProcessingException(user.getProvider() + "계정을 사용하기 위해서 로그인이 필요합니다.");
//            }
//            user = updateExistingUser(user, oAuth2UserInfo);
//
//        } else {
//            user = registerNewUser(userRequest, oAuth2UserInfo);
//        }
//        return UserPrincipal.create(user);
        return null;
    }

    //DB에 없을때, 등록

    //DB에 있을때는 수정

}

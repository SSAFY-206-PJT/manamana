package com.webtoon.manamana.auth.oauth2.service;

import com.webtoon.manamana.auth.DTO.UserPrincipal;
import com.webtoon.manamana.auth.oauth2.dto.OAuth2UserInfo;
import com.webtoon.manamana.auth.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.webtoon.manamana.auth.oauth2.repository.LoginProviderRepository;
import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import com.webtoon.manamana.entity.user.AuthProvider;
import com.webtoon.manamana.entity.user.LoginProvider;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.user.repository.user.UserRepository;
import com.webtoon.manamana.user.repository.user.UserRepositorySupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final CustomOAuthUserInfoFactory customOAuthUserInfoFactory;
    private final LoginProviderRepository loginProviderRepository;

    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest); // OAuth 제공자로 부터 정보를 받아오는 객체

        try{
            System.out.println("test123151251");
            return processOAuthUser(userRequest, oAuth2User);
        }catch (Exception e){
            throw new InternalAuthenticationServiceException(e.getMessage(),e.getCause());
        }
    }

    /**
     * 사용자 정보 추출 후 시큐리티가 처리할 UserPrincipal 객체를 제공
     */
    @Transactional
    public OAuth2User processOAuthUser(OAuth2UserRequest userRequest, OAuth2User oAuth2User) throws OAuth2AuthenticationProcessingException {
        //만들어둔 팩토리에 데이터를 전달해서 어떤 provider인지 판단.
        OAuth2UserInfo oAuth2UserInfo = customOAuthUserInfoFactory
                .getOAuth2UserInfo(userRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());

        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("OAuth2 공급자(구글, 네이버 등)에서 이메일을 찾을 수 없습니다.");
        }

        //로그인 제공자 조회
        LoginProvider loginProvider = loginProviderRepository.findLoginProviderByName(AuthProvider.valueOf(userRequest.getClientRegistration().getRegistrationId().toLowerCase()))
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_PROVIDER));


        System.out.println(loginProvider.getName());
        //이메일과 로그인 제공자로 유저 조회
        Optional<User> userOptional = userRepositorySupport.findUserByEmailAndLoginProvider(oAuth2UserInfo.getEmail(), loginProvider);

        User user = null;
        if(userOptional.isPresent()){
            user = userOptional.get();

            if (!user.getLoginProvider().getName().equals(AuthProvider.valueOf(userRequest.getClientRegistration().getRegistrationId().toLowerCase()))) {
                throw new OAuth2AuthenticationProcessingException(user.getLoginProvider().getName() + "계정을 사용하기 위해서 로그인이 필요합니다.");
            }
            updateExistingUser(user, oAuth2UserInfo);

        }
        else{
            user = registerNewUser(oAuth2UserInfo, loginProvider);
        }


        //가져온 정보를 시큐리티가 처리하는 UserPrincipal 객체로 변경해서 넘겨줌.
        return UserPrincipal.create(user);
    }

    //DB에 없을때, 등록
    public User registerNewUser(OAuth2UserInfo oAuth2UserInfo, LoginProvider loginProvider){

        return userRepository.save(User.createUser(oAuth2UserInfo, loginProvider));
    }

    //DB에 있을때는 수정
    public void updateExistingUser(User user, OAuth2UserInfo oAuth2UserInfo){

        //탈퇴한 아이디면 복구먼저 함.
        if(user.isDeleted()) user.reSignUp();

        user.loginInfoUpdate(oAuth2UserInfo);
    }

}

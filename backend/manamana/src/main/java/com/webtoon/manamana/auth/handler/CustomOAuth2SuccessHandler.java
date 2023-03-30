package com.webtoon.manamana.auth.handler;

import com.webtoon.manamana.auth.DTO.UserPrincipal;
import com.webtoon.manamana.auth.exception.BadRequestException;
import com.webtoon.manamana.auth.util.CookieUtils;
import com.webtoon.manamana.auth.util.HttpCookieOAuth2AuthorizationRequestRepository;
import com.webtoon.manamana.auth.util.TokenProvider;
import com.webtoon.manamana.config.AppProperty;
import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.user.repository.user.UserRepository;
import com.webtoon.manamana.user.repository.user.UserRepositorySupport;
import com.webtoon.manamana.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.webtoon.manamana.auth.util.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@RequiredArgsConstructor
@Component
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider; //토큰 제공자 - 토큰을 생성함.
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final AppProperty appProperty;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //로그인에 성공하면 리프레시 토큰을 발급받아서 저장함.
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String refreshToken = tokenProvider.createRefreshToken(); //리프레시 토큰

        System.out.println(userPrincipal.getId());
        saveRefreshToken(userPrincipal,refreshToken); //리프레시 토큰 저장 - 로그인을 했으면 리프레시 토큰을 새로 발급해줌.

        //리프레시 토큰 쿠키에 담기.
        CookieUtils.addCookie(response,"refresh-token",refreshToken,Integer.parseInt(appProperty.getRefreshTokenExpirationTime()));

        String targetUrl = determineTargetUrl(request, response, authentication);
        if (response.isCommitted()) {
            logger.debug("응답이 이미 커밋되었습니다. " + targetUrl + "로 리다이렉션 할 수 없습니다.");
            return;
        }

        clearAuthenticationAttributes(request, response);
        response.sendRedirect(appProperty.getRedirect_page());
//        getRedirectStrategy().sendRedirect(request,response,appProperty.getRedirect_page()); //리다이렉션.
    }

    protected String determineTargetUrl(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);


        if (redirectUri.isPresent()) {
            throw new BadRequestException("승인되지 않은 리디렉션 URI가 있어 인증을 진행할 수 없습니다.");
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        System.out.println("target : " + targetUrl);

        String token = tokenProvider.creatToken(authentication); //access 토큰 생성.

        CookieUtils.addCookie(response,"access-token", token,Integer.parseInt(appProperty.getTokenExpirationTime()));

//        return UriComponentsBuilder.fromUriString(targetUrl)
//                .queryParam("token", token)
//                .build().toString();

        return targetUrl;
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request,
                                                 HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);

        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    //없으면 저장, 있으면 수정 - 앞서서 유저정보를 저장하고 왔기 때문에 리프레시 필드는 null이므로 항상 업데이트임

    public void saveRefreshToken(UserPrincipal userPrincipal, String refreshToken){
        User user = userRepository.findByIdAndIsDeletedFalse(userPrincipal.getId())
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_USER));

        System.out.println(user.toString());
        user.updateRefresh(refreshToken);
    }
}

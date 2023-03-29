//package com.webtoon.manamana.auth.handler;
//
//import com.webtoon.manamana.auth.exception.BadRequestException;
//import com.webtoon.manamana.auth.util.CookieUtils;
//import com.webtoon.manamana.auth.util.HttpCookieOAuth2AuthorizationRequestRepository;
//import com.webtoon.manamana.auth.util.TokenProvider;
//import com.webtoon.manamana.config.AppProperty;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Optional;
//
//import static com.webtoon.manamana.auth.util.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
//
//@RequiredArgsConstructor
//@Component
//public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    private final TokenProvider tokenProvider; //토큰 제공자 - 토큰을 생성함.
//    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
//    private final AppProperty appProperty;
//
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//        String targetUrl = determineTargetUrl(request, response, authentication);
//        if (response.isCommitted()) {
//            logger.debug("응답이 이미 커밋되었습니다. " + targetUrl + "로 리다이렉션 할 수 없습니다.");
//            return;
//        }
//        clearAuthenticationAttributes(request, response);
//        getRedirectStrategy().sendRedirect(request,response,targetUrl); //리다이렉션.
//    }
//
//    protected String determineTargetUrl(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication) {
//        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
//                .map(Cookie::getValue);
//
//        if (redirectUri.isPresent()) {
//            throw new BadRequestException("승인되지 않은 리디렉션 URI가 있어 인증을 진행할 수 없습니다.");
//        }
//
//        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
//
//        String token = tokenProvider.creatToken(authentication);
//
//        return UriComponentsBuilder.fromUriString(targetUrl)
//                .queryParam("token", token)
//                .build().toString();
//    }
//
//    protected void clearAuthenticationAttributes(HttpServletRequest request,
//                                                 HttpServletResponse response) {
//        super.clearAuthenticationAttributes(request);
//
//        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
//    }
//}

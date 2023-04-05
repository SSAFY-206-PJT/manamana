package com.webtoon.manamana.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppProperty {

    @Value("${app.auth.token-secret}")
    private String tokenSecret;

    @Value("${app.auth.token-expiration-time}")
    private String tokenExpirationTime;

//    @Value("#{'${app.oauth2.authorized-redirect-uris}'}")
//    private List<String> authorizedRedirectUris;

    @Value("${app.oauth2.authorized-redirect-uris}")
    private String authorizedRedirectUris;

    @Value("${app.auth.refresh-token-secret}")
    private String refreshTokenSecret;
    @Value("${app.auth.refresh-token-expiration-time}")
    private String refreshTokenExpirationTime;

    @Value("${app.auth.redirect-page}")
    private String redirect_page;



}

package com.webtoon.manamana.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Component
public class AppProperty {

    @Value("${app.auth.token-secret}")
    private String toeknSecret;

    @Value("${app.auth.token-expiration-time}")
    private String tokenExpirationTime;

//    @Value("#{'${app.oauth2.authorized-redirect-uris}'}")
//    private List<String> authorizedRedirectUris;

    @Value("${app.oauth2.authorized-redirect-uris}")
    private String authorizedRedirectUris;

}

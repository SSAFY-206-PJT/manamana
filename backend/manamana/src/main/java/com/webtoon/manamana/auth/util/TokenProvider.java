package com.webtoon.manamana.auth.util;

import com.webtoon.manamana.auth.DTO.UserPrincipal;
import com.webtoon.manamana.auth.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.webtoon.manamana.config.AppProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/*jwt를 발급하고 인증하는 클래스*/
@RequiredArgsConstructor
@Component
public class TokenProvider {


    private final AppProperty appProperty;

    public String creatToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperty.getTokenExpirationTime());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperty.getToeknSecret())
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperty.getToeknSecret())
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String token) throws OAuth2AuthenticationProcessingException {
        try {
            Jwts.parser().setSigningKey(appProperty.getToeknSecret()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) { // 유효하지 않은 JWT
            throw new OAuth2AuthenticationProcessingException("not valid jwt");
        } catch (io.jsonwebtoken.ExpiredJwtException e) { // 만료된 JWT
            throw new OAuth2AuthenticationProcessingException("expired jwt");
        } catch (io.jsonwebtoken.UnsupportedJwtException e) { // 지원하지 않는 JWT
            throw new OAuth2AuthenticationProcessingException("unsupported jwt");
        } catch (IllegalArgumentException e) { // 빈값
            throw new OAuth2AuthenticationProcessingException("empty jwt");
        }
    }
}

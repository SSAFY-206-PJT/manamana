package com.webtoon.manamana.auth.util;

import com.webtoon.manamana.auth.dto.UserPrincipal;
import com.webtoon.manamana.config.AppProperty;
import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.user.repository.user.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static com.webtoon.manamana.config.response.exception.CustomExceptionStatus.*;
import static com.webtoon.manamana.config.response.exception.CustomExceptionStatus.REFRESH_TOKEN_RENEWAL;

/*jwt를 발급하고 인증하는 클래스*/
@RequiredArgsConstructor
@Component
public class TokenProvider {

    private static final Long ACCESS_TOKEN_VALIDATE_TIME = 1000L * 60 * 60 *24; // 1hour
    public static final Long REFRESH_TOKEN_VALIDATE_TIME = 1000L * 60 * 60 * 24 * 7; // 1week

    private final AppProperty appProperty;
    private final UserRepository userRepository;

    public String creatToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_TOKEN_VALIDATE_TIME);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperty.getTokenSecret())
                .compact();
    }

    public String creatToken(UserPrincipal userPrincipal) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ACCESS_TOKEN_VALIDATE_TIME);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperty.getTokenSecret())
                .compact();
    }

    //리프레시 토큰
    public String createRefreshToken(){

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + REFRESH_TOKEN_VALIDATE_TIME);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, appProperty.getRefreshTokenSecret())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .compact();

    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperty.getTokenSecret())
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(appProperty.getTokenSecret()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) { // 유효하지 않은 JWT
            throw new CustomException(TOKEN_INVALID);
//            throw new MalformedJwtException("not valid jwt");
        } catch (ExpiredJwtException e) { // 만료된 JWT
            throw new CustomException(REFRESH_TOKEN_RENEWAL);
//            throw new ExpiredJwtException(null,null,"expired");
        } catch (UnsupportedJwtException e) { // 지원하지 않는 JWT
            throw new CustomException(TOKEN_UNSUPPORTED);
//            throw new UnsupportedJwtException("unsupported jwt");
        } catch (IllegalArgumentException e) { // 빈값
            throw new CustomException(TOKEN_NOT_FOUND);
//            throw new IllegalArgumentException("empty jwt");
        }
//            Jwts.parser().setSigningKey(appProperty.getTokenSecret()).parseClaimsJws(token);

    }

    public boolean validateRefreshToken(String refreshToken) {

        //검증
        validateToken(refreshToken);

            //DB를 조회해서 비교.
        Optional<User> refreshTokenOptional = userRepository.findByIdAndIsDeletedFalse(getUserIdFromToken(refreshToken));

        return refreshTokenOptional.isPresent() && refreshToken.equals(refreshTokenOptional.get().getRefreshToken());

    }

}

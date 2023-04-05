package com.webtoon.manamana.auth.service;

import com.webtoon.manamana.auth.DTO.UserPrincipal;

import com.webtoon.manamana.auth.util.TokenProvider;
import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import com.webtoon.manamana.entity.user.User;
import com.webtoon.manamana.user.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.webtoon.manamana.config.response.exception.CustomExceptionStatus.NOT_INVALID_REFRESH_TOKEN;
import static com.webtoon.manamana.config.response.exception.CustomExceptionStatus.REFRESH_TOKEN_RENEWAL;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{


    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    public String reAccessToken(String oldAccessToken, String refreshToken) {

        //이전의 엑세스 토큰에서 id값 꺼내기
        Long userId = tokenProvider.getUserIdFromToken(oldAccessToken);

        //id값으로 유저 정보 조회
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUNT_USER));

        //리프레시 토큰 검증
        try {
            if(!tokenProvider.validateRefreshToken(refreshToken)) throw new CustomException(NOT_INVALID_REFRESH_TOKEN);

        } catch (Exception e) {
            throw new CustomException(REFRESH_TOKEN_RENEWAL);
        }


        //엑세스 토큰 생성해서 리턴.
        return tokenProvider.creatToken(UserPrincipal.create(user));
    }
}

package com.webtoon.manamana.auth.controller;

import com.webtoon.manamana.auth.dto.TokenResponseDTO;
import com.webtoon.manamana.auth.service.AuthService;
import com.webtoon.manamana.auth.util.CookieUtils;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final ResponseService responseService;
    private final AuthService authService;

    //만료되면 재발급.
    @GetMapping("/reissuance")
    public DataResponse<TokenResponseDTO> reAuth(
            @RequestHeader("Authorization")String accessToken,
            HttpServletRequest request){

        //토큰이 형식에 맞지 않거나 데이터가 없으면.
        if (!StringUtils.hasText(accessToken) || !accessToken.startsWith("Bearer ")) {
            throw new CustomException(CustomExceptionStatus.TOKEN_ILLEGAL);
        }

        //이전 엑세스 토큰 추출 -> id값 꺼내기 위해서.
        String oldAccessToken = accessToken.substring(7);

        //쿠키에서 리프레시 토큰 꺼내기.
        Cookie cookie = CookieUtils.getCookie(request, "refresh-token")
                .orElseThrow(() -> new CustomException(CustomExceptionStatus.NOT_FOUND_REFRESH_TOKEN));

        String refreshToken = cookie.getValue();


        //서비스에서 리프레시 토큰확인하고 엑세스 토큰 재발급해서 리턴.
        String newAccessToken = authService.reAccessToken(oldAccessToken, refreshToken);

        TokenResponseDTO tokenResponseDTO = TokenResponseDTO.createDTO(newAccessToken);


        return responseService.getDataResponse(tokenResponseDTO, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

}

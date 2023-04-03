package com.webtoon.manamana.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*인가 - 권한이 없음.*/
@Slf4j
@RequiredArgsConstructor
@Component
public class TokenAccessDeniedHandler implements AccessDeniedHandler {

    private final ResponseService responseService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        log.error("Access error : ", accessDeniedException.getMessage());

        CommonResponse exceptionResponse = responseService.getExceptionResponse(CustomExceptionStatus.HAVE_NO_AUTHORITY);
        String errorObject = objectMapper.writeValueAsString(exceptionResponse);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(403);

        response.sendError(HttpServletResponse.SC_FORBIDDEN, errorObject);
    }
}

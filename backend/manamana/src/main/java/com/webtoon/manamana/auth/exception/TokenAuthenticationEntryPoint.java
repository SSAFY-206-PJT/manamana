package com.webtoon.manamana.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtoon.manamana.auth.TokenAuthenticationFilter;
import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.webtoon.manamana.auth.TokenAuthenticationFilter.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ResponseService responseService;

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Authentication error");

        String exception = (String) request.getAttribute(TOKEN_EXCEPTION_KEY);

        CommonResponse exceptionResponse;

        if(exception == TOKEN_INVALID){
            exceptionResponse = responseService.getExceptionResponse(CustomExceptionStatus.TOKEN_INVALID);
        }
        else if(exception == TOKEN_EXPIRE){
            exceptionResponse = responseService.getExceptionResponse(CustomExceptionStatus.TOKEN_EXPIRE);
        }
        else if(exception == TOKEN_UNSUPPORTED){
            exceptionResponse = responseService.getExceptionResponse(CustomExceptionStatus.TOKEN_UNSUPPORTED);
        }
        else{
            exceptionResponse = responseService.getExceptionResponse(CustomExceptionStatus.TOKEN_ILLEGAL);
        }



        String error = objectMapper.writeValueAsString(exceptionResponse);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(401);

//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,error);

        response.getWriter().write(error);
    }
}

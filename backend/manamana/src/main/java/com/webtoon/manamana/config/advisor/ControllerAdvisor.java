package com.webtoon.manamana.config.advisor;

import com.webtoon.manamana.config.response.CommonResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Getter
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerAdvisor {

    //에러나면 ResponseService에서 에러난 것을 던져주기 위해
    private final ResponseService responseService;


    @ExceptionHandler
    //커스텀 예외 처리 - 커스텀 예외를 먼저 처리하도록 둠.
    public CommonResponse exceptionHandler(CustomException e){

        CustomExceptionStatus status = e.getCustomExceptionStatus();

        e.printStackTrace();
        log.warn("[" +" CustomException - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + "]" +" : " + status.getMessage());

        return responseService.getExceptionResponse(status);

    }


    //커스텀 예외로 확인되지 않은 모든 런타임 예외
    @ExceptionHandler
    public CommonResponse exceptionHandler(RuntimeException e){

        e.printStackTrace();

        log.error("[" +" CustomException - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + "]" +" : " + e.getMessage());

        CommonResponse response = new CommonResponse();
        response.setSuccess(false);
        response.setCode(400);
        response.setMessage(e.getMessage());

        return response;
    }

}

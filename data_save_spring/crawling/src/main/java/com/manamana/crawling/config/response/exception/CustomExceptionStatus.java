package com.manamana.crawling.config.response.exception;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum CustomExceptionStatus {

    /*common error*/
    REQUEST_ERROR(false, 400, "요청을 확인해주세요."),
    //EMPTY_JWT(false, 401, "JWT를 입력해주세요."),
    INVALID_JWT(false, 401, "유효하지 않은 JWT입니다."),
    //INVALID_USER_JWT(false,403,"권한이 없는 유저의 접근입니다."),
    //NOT_AUTHENTICATED_ACCOUNT(false, 403, "로그인이 필요합니다."),

    REQUEST_QUERY_ERROR(false, 400, "잘못된 쿼리 요청입니다."),

    ;
    private final boolean isSuccess;

    private final int code;

    private final String message;
}

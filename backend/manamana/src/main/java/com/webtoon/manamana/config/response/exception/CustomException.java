package com.webtoon.manamana.config.response.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomException extends RuntimeException{

    CustomExceptionStatus customExceptionStatus;
}

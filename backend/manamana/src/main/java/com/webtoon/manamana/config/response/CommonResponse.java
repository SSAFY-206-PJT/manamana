package com.webtoon.manamana.config.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonResponse {

    public boolean isSuccess;

    public int code;

    public String message;
}

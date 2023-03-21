package com.webtoon.manamana.config.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonResponse {

    @JsonProperty(value = "isSuccess")
    public boolean isSuccess;

    public int code;

    public String message;
}

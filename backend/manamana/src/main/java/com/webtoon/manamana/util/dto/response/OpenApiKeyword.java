package com.webtoon.manamana.util.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenApiKeyword {

    private String startDate;
    private String endDate;
    private String timeUnit;
    private List<Object> result;

    @Override
    public String toString() {
        return "OpenApiKeyword{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", timeUnit='" + timeUnit + '\'' +
                ", result=" + result +
                '}';
    }
}

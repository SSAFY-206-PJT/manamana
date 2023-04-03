package com.webtoon.manamana.user.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 선호 장르 선택
 * */
@Getter
@Setter
public class IdIntMultiSelectRequestDTO {

    private List<Integer> id;
}

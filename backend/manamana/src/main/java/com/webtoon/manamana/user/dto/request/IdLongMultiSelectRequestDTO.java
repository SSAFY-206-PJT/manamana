package com.webtoon.manamana.user.dto.request;

/*웹툰 삭제시*/

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * 관심 웹툰 삭제, 선호 웹툰 선택 전부 사용
 */
@Getter
@Setter
public class IdLongMultiSelectRequestDTO {

    private List<Long> id;

}

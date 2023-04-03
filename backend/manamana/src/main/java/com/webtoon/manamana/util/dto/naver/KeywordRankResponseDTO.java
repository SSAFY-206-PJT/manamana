package com.webtoon.manamana.util.dto.naver;


import com.webtoon.manamana.util.dto.response.Top10ResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class KeywordRankResponseDTO {

    private String message;
    private int statusCode;

    private int returnCode;

    private String date;
    private String datetime;
    private String range;
    private List<RankDTO> ranks;


    public List<Top10ResponseDTO> createTop10DTO(){

        List<Top10ResponseDTO> top10ResponseDTOS = ranks.stream()
                .map(Top10ResponseDTO::createDTO)
                .collect(Collectors.toList());

        return top10ResponseDTOS;

    }


}

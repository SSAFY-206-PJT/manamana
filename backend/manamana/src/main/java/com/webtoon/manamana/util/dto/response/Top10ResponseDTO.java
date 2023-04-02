package com.webtoon.manamana.util.dto.response;


import com.webtoon.manamana.util.dto.naver.RankDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Top10ResponseDTO {

    private int rank;
    private String keyword;


    @Builder
    public Top10ResponseDTO(int rank, String keyword) {
        this.rank = rank;
        this.keyword = keyword;
    }

    public static Top10ResponseDTO createDTO(RankDTO rankDTO){

        return Top10ResponseDTO.builder()
                .rank(rankDTO.getRank())
                .keyword(rankDTO.getKeyword()).build();

    }
}

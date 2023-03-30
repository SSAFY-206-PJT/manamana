package com.webtoon.manamana.auth.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDTO {

    private String accessToken;


    @Builder
    public TokenResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public static TokenResponseDTO createDTO(String accessToken){

        return TokenResponseDTO.builder()
                .accessToken(accessToken)
                .build();
    }
}

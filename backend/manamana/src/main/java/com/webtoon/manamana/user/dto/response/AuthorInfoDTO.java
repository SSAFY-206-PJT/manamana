package com.webtoon.manamana.user.dto.response;

import com.webtoon.manamana.entity.webtoon.Author;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/*작가 정보*/
@Getter
@Setter
public class AuthorInfoDTO {

    private int id;
    private String name;

    @Builder
    public AuthorInfoDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AuthorInfoDTO createDTO(Author author){

        return AuthorInfoDTO.builder()
                .id(author.getId())
                .name(author.getName()).build();
    }
}

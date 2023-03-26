package com.webtoon.manamana.webtoon.dto.response.common;

import com.webtoon.manamana.entity.webtoon.Webtoon;
import com.webtoon.manamana.webtoon.dto.response.AuthorDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class WebtoonListDTO {

    private long id;
    private String name;
    private String status;
    private String imagePath;
    private List<AuthorDTO> authors;

    @Builder
    public WebtoonListDTO(long id, String name, String status, String imagePath, List<AuthorDTO> authors) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.imagePath = imagePath;
        this.authors = authors;
    }

    public static WebtoonListDTO createDTO(Webtoon webtoon, Map<Integer,String> statusMap){


        List<AuthorDTO> authorDTOS = webtoon.getAuthors().stream()
                .map(AuthorDTO::createDTO)
                .collect(Collectors.toList());


        return WebtoonListDTO.builder()
                .id(webtoon.getId())
                .name(webtoon.getName())
                .status(statusMap.get(webtoon.getStatusId()))
                .imagePath(webtoon.getImagePath())
                .authors(authorDTOS).build();
    }
}

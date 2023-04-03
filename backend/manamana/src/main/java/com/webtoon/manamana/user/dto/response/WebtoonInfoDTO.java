package com.webtoon.manamana.user.dto.response;

import com.webtoon.manamana.entity.user.UserWebtoon;
import com.webtoon.manamana.util.dto.response.StatusResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


/*관심 등록 한, 웹툰 정보*/
@Getter
@Setter
public class WebtoonInfoDTO {

    private long id;
    private String name;

    private List<AuthorInfoDTO> authors;

    private String imagePath;
    private int status;

    private List<Integer> days;

    @Builder
    public WebtoonInfoDTO(long id, String name, List<AuthorInfoDTO> authors, String imagePath, int status, List<Integer> days) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.imagePath = imagePath;
        this.status = status;
        this.days = days;
    }

    public static WebtoonInfoDTO createDTO(UserWebtoon userWebtoon){

        List<AuthorInfoDTO> authorInfoDTOS = userWebtoon.getWebtoon().getAuthors().stream()
                .map(AuthorInfoDTO::createDTO)
                .collect(Collectors.toList());


        List<Integer> days = userWebtoon.getWebtoon().getWebtoonDays().stream()
                .map(webtoonDay -> {
                    return webtoonDay.getCodeId();
                })
                .collect(Collectors.toList());

        return WebtoonInfoDTO.builder()
                .id(userWebtoon.getWebtoon().getId())
                .name(userWebtoon.getWebtoon().getName())
                .authors(authorInfoDTOS)
                .imagePath(userWebtoon.getWebtoon().getImagePath())
                .status(userWebtoon.getWebtoon().getStatusId())
                .days(days).build();
    }
}

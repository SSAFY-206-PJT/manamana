package com.webtoon.manamana.user.dto.response;

//선택했던 장르조회 DTO


import com.webtoon.manamana.entity.user.PreferGenre;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GenreResponseDTO {

    private List<Integer> id;

    @Builder
    public GenreResponseDTO(List<Integer> id) {
        this.id = id;
    }

    public static GenreResponseDTO createDTO(List<PreferGenre> preferGenres){

        //스트림을 이용해서 장르 id 값만 가져옴.
        List<Integer> collect = preferGenres.stream()
                .map(PreferGenre::getGenreId)
                .collect(Collectors.toList());


        return GenreResponseDTO.builder()
                .id(collect).build();

    }
}

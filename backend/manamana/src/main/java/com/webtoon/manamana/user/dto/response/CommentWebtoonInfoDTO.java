package com.webtoon.manamana.user.dto.response;


import com.webtoon.manamana.entity.webtoon.Webtoon;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/*댓글 조회시에 보여줄 웹툰 정보 DTO*/
@Getter
@Setter
public class CommentWebtoonInfoDTO {

    private long id;
    private String name;
    private String imagePath;


    @Builder
    public CommentWebtoonInfoDTO(long id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }

    //Entity -> DTO
    public static CommentWebtoonInfoDTO createDTO(Webtoon webtoon){

        return CommentWebtoonInfoDTO.builder()
                .id(webtoon.getId())
                .name(webtoon.getName())
                .imagePath(webtoon.getImagePath()).build();

    }
}

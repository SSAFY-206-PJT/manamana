package com.webtoon.manamana.user.dto.response;

import com.webtoon.manamana.entity.user.WebtoonNotification;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebtoonNotificationDTO {

    private long id;
    private long webtoonId;
    private String image;
    private String name;
    private int episode;
    @Builder
    public WebtoonNotificationDTO(long id, long webtoonId, String image, String name, int episode) {
        this.id = id;
        this.webtoonId = webtoonId;
        this.image = image;
        this.name = name;
        this.episode = episode;
    }




    public static WebtoonNotificationDTO createDTO(WebtoonNotification webtoonNotification){

        return WebtoonNotificationDTO.builder()
                .id(webtoonNotification.getId())
                .webtoonId(webtoonNotification.getWebtoon().getId())
                .image(webtoonNotification.getWebtoon().getImagePath())
                .name(webtoonNotification.getWebtoon().getName())
                .episode(webtoonNotification.getEpisode()).build();
    }
}

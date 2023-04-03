package com.webtoon.manamana.webtoon.dto.response.common;

import com.webtoon.manamana.entity.webtoon.WebtoonProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebtoonProviderDTO {

    private String name;
    private String url;
    private String provider_image;


    @Builder
    public WebtoonProviderDTO(String name, String url, String provider_image) {
        this.name = name;
        this.url = url;
        this.provider_image = provider_image;
    }

    public static WebtoonProviderDTO createDTO(WebtoonProvider webtoonProvider, String webtoonUrl){

        return WebtoonProviderDTO.builder()
                .name(webtoonProvider.getName())
                .url(webtoonProvider.getUrl() + webtoonUrl)
                .provider_image(webtoonProvider.getImage()).build();
    }
}
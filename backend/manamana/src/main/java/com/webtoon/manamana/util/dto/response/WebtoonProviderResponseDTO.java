package com.webtoon.manamana.util.dto.response;

import com.webtoon.manamana.entity.webtoon.WebtoonProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebtoonProviderResponseDTO {

    private int id;
    private String name;
    private String providerUrl;
    private String providerImage;

    @Builder
    public WebtoonProviderResponseDTO(int id, String name, String providerUrl, String providerImage) {
        this.id = id;
        this.name = name;
        this.providerUrl = providerUrl;
        this.providerImage = providerImage;
    }

    public static WebtoonProviderResponseDTO createDTO(WebtoonProvider webtoonProvider){
        return WebtoonProviderResponseDTO.builder()
                .id(webtoonProvider.getId())
                .name(webtoonProvider.getName())
                .providerUrl(webtoonProvider.getUrl())
                .providerImage(webtoonProvider.getImage()).build();
    }
}

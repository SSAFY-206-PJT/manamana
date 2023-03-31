package com.manamana.crawling.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class WebtoonDataArrayDTO {

    private int provider_id;

    private List<WebtoonDataDTO> data;
}

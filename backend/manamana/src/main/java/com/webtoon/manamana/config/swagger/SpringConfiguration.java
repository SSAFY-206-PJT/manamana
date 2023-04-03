package com.webtoon.manamana.config.swagger;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    @Bean
    public Komoran komoran(){
        return new Komoran(DEFAULT_MODEL.FULL);
    }
}

package com.webtoon.manamana.config;

import com.querydsl.core.annotations.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600; //1시간

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        SortHandlerMethodArgumentResolver sortArgumentResolver = new SortHandlerMethodArgumentResolver();
        sortArgumentResolver.setSortParameter("sortBy");
        sortArgumentResolver.setPropertyDelimiter("-");

        PageableHandlerMethodArgumentResolver pageableArgumentResolver = new PageableHandlerMethodArgumentResolver(sortArgumentResolver);
        pageableArgumentResolver.setOneIndexedParameters(true);
        pageableArgumentResolver.setMaxPageSize(500);
        pageableArgumentResolver.setFallbackPageable(PageRequest.of(0,12));
        argumentResolvers.add(pageableArgumentResolver);

    }

//    // TODO : 추후에 지워야됨, 또한 시큐리티 사용시 바꿔야됨.
//    /*CORS 처리*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedOriginPatterns("*")//외부에서 들어오는 모든 url 허용
                .allowedMethods("GET","POST","PATCH","DELETE") //허용되는 메서드
                .allowedHeaders("*")//허용되는 헤더 지원
                .allowCredentials(true) //자격증명 허용
                .maxAge(MAX_AGE_SECS);
    }
}

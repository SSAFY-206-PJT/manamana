package com.webtoon.manamana.webtoon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webtoon.manamana.config.response.CustomSuccessStatus;
import com.webtoon.manamana.config.response.DataResponse;
import com.webtoon.manamana.config.response.ResponseService;
import com.webtoon.manamana.webtoon.dto.response.AuthorDTO;
import com.webtoon.manamana.webtoon.dto.response.WebtoonListDTO;
import com.webtoon.manamana.webtoon.util.WebtoonListFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/webtoons")
public class WebtoonController {

    private final ResponseService responseService;


    //전체 조회.
    @GetMapping
    public DataResponse<List<WebtoonListDTO>> webtoonList(
            @RequestParam HashMap<String,String> filter
    ){
        //쿼리 스트링을 map으로 받아서 객체에 매핑함.
        ObjectMapper objectMapper = new ObjectMapper();
        WebtoonListFilter searchFilter = objectMapper.convertValue(filter,WebtoonListFilter.class);



        //TODO : 더미데이터로 리턴하기 때문에 실제 로직으로 바꿔야 됨.

        List<WebtoonListDTO> returnArray = new ArrayList<>();
        List<AuthorDTO> authArray1 = new ArrayList<>();
        AuthorDTO authorDTO1 = AuthorDTO.builder()
                .id(1)
                .name("시니")
                .build();
        AuthorDTO authorDTO2 = AuthorDTO.builder()
                .id(2)
                .name("광운")
                .build();
        authArray1.add(authorDTO1);
        authArray1.add(authorDTO2);

        WebtoonListDTO webtoonListDTO = WebtoonListDTO.builder()
                .id(1)
                .name("1초")
                .status("연재중")
                .imagePath("https://image-comic.pstatic.net/webtoon/725586/thumbnail/thumbnail_IMAG21_17f81846-d1a9-43fd-83a4-f9e966b6b977.jpg")
                .authors(authArray1)
                .build();

        List<AuthorDTO> authArray2 = new ArrayList<>();
        AuthorDTO authorDTO3 = AuthorDTO.builder()
                .id(3)
                .name("박태준만화회사")
                .build();
        AuthorDTO authorDTO4 = AuthorDTO.builder()
                .id(4)
                .name("정종택")
                .build();
        authArray2.add(authorDTO3);
        authArray2.add(authorDTO4);

        WebtoonListDTO webtoonListDTO2 = WebtoonListDTO.builder()
                .id(2)
                .name("김부장")
                .status("연재중")
                .imagePath("https://image-comic.pstatic.net/webtoon/783053/thumbnail/thumbnail_IMAG21_d7732f14-26da-4e35-8762-660cc87b53f1.jpg")
                .authors(authArray2)
                .build();

        returnArray.add(webtoonListDTO);
        returnArray.add(webtoonListDTO2);
        return responseService.getDataResponse(returnArray, CustomSuccessStatus.RESPONSE_SUCCESS);
    }

    @GetMapping("/{webtoon-id}")
    public DataResponse<WebtoonListDTO>> webtoonList(
            @RequestParam HashMap<String,String> filter
    )

}

package com.manamana.crawling.service;

import com.manamana.crawling.dto.ResponseRecommendDataDTO;
import com.manamana.crawling.dto.UserWebtoonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecommendService {
    public ResponseRecommendDataDTO dummyRecommendData() {
        List<UserWebtoonDTO> userWebtoonDTOList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                UserWebtoonDTO userWebtoonDTO = UserWebtoonDTO.createDTO(i, j, (int) (Math.random() * 100));
                userWebtoonDTOList.add(userWebtoonDTO);
            }
        }
        return ResponseRecommendDataDTO.createDTO(userWebtoonDTOList);
    }
}

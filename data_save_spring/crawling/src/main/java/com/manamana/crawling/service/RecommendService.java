package com.manamana.crawling.service;

import com.manamana.crawling.dto.ResponseRecommendDataDTO;
import com.manamana.crawling.dto.UserWebtoonDTO;
import com.manamana.crawling.entity.user.UserWebtoon;
import com.manamana.crawling.entity.webtoon.Webtoon;
import com.manamana.crawling.repository.UserRepository;
import com.manamana.crawling.repository.UserWebtoonRepository;
import com.manamana.crawling.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecommendService {
    private final UserWebtoonRepository userWebtoonRepository;
    private final UserRepository userRepository;
    private final WebtoonRepository webtoonRepository;

    public List<UserWebtoonDTO> recommendData() {
        List<UserWebtoonDTO> userWebtoonDTOList = new ArrayList<>();
        List<UserWebtoon> userWebtoonAll = userWebtoonRepository.findAll();
        userWebtoonAll.stream()
                .forEach(userWebtoon -> {
                    UserWebtoonDTO userWebtoonDTO = UserWebtoonDTO.createDTO(userWebtoon.getUser().getId(), userWebtoon.getWebtoon().getId(), userWebtoon.getScore());
                    userWebtoonDTOList.add(userWebtoonDTO);
                });
        return userWebtoonDTOList;
    }

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

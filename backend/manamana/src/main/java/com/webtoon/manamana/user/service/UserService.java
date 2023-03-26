package com.webtoon.manamana.user.service;

import com.webtoon.manamana.user.dto.request.UserUpdateRequestDTO;
import com.webtoon.manamana.user.dto.response.UserCommentResponseDTO;
import com.webtoon.manamana.user.dto.response.UserResponseDTO;
import com.webtoon.manamana.user.dto.response.WebtoonInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    /*회원 정보 조회*/
    UserResponseDTO getUser(Long userId);

    /*회원 정보 수정*/
    void updateUser(long userId, UserUpdateRequestDTO userUpdateRequestDTO, MultipartFile file);

    /*회원 탈퇴*/
    void removeUser(long userId);

    /*작성한 댓글 조회*/
    List<UserCommentResponseDTO> getUserCommentAll(long userId);

    /*관심 웹툰 조회*/
    List<WebtoonInfoDTO> getUserWebtoonAll(long userId, Integer dayId);

    /*관심 웹툰 삭제*/
    void deleteUserWebtoon(long userId,List<Long> webtoonIds);

    /*선호 장르 선택*/
    void selectLikeGenre(long userId,List<Integer> genreIds);

    /*선호 웹툰 선택*/
    void selectLikeWebtoon(long userId,List<Long> webtoonIds);


}

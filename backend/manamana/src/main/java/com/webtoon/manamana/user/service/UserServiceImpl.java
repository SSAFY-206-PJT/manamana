package com.webtoon.manamana.user.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.webtoon.manamana.config.response.exception.CustomException;
import com.webtoon.manamana.config.response.exception.CustomExceptionStatus;
import com.webtoon.manamana.entity.user.*;
import com.webtoon.manamana.entity.webtoon.Comment;
import com.webtoon.manamana.entity.webtoon.Webtoon;
import com.webtoon.manamana.entity.webtoon.WebtoonGenre;
import com.webtoon.manamana.entity.webtoon.codetable.Genre;
import com.webtoon.manamana.user.dto.request.UserUpdateRequestDTO;
import com.webtoon.manamana.user.dto.response.GenreResponseDTO;
import com.webtoon.manamana.user.dto.response.UserCommentResponseDTO;
import com.webtoon.manamana.user.dto.response.UserResponseDTO;
import com.webtoon.manamana.user.dto.response.WebtoonInfoDTO;
import com.webtoon.manamana.user.repository.user.*;
import com.webtoon.manamana.util.repository.GenreCodeRepository;
import com.webtoon.manamana.webtoon.repository.comment.CommentRepository;
import com.webtoon.manamana.webtoon.repository.comment.CommentRepositorySupport;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonGenreRepository;
import com.webtoon.manamana.webtoon.repository.webtoon.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.webtoon.manamana.config.aws.AwsDirectoryName.PROFILE_IMAGE;
import static com.webtoon.manamana.config.response.exception.CustomExceptionStatus.*;
import static com.webtoon.manamana.config.response.exception.CustomExceptionStatus.NOT_FOUNT_USER;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;
    private final UserWebtoonRepository userWebtoonRepository;
    private final UserWebtoonRepositorySupport userWebtoonRepositorySupport;
    private final CommentRepository commentRepository;
    private final CommentRepositorySupport commentRepositorySupport;
    private final UserGenreRepository userGenreRepository;
    private final GenreCodeRepository genreCodeRepository;
    private final WebtoonRepository webtoonRepository;
    private final WebtoonGenreRepository webtoonGenreRepository;
    private final PreferGenreRepository preferGenreRepository;
    private final PreferGenreRepositorySupport preferGenreRepositorySupport;

    //aws 업로드
    private final AmazonS3Client amazonS3Client;

    //s3 버킷명
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    //TODO : jwt로 받은 유저ID와 pathvariable로 받은 유저ID가 같은지 처리하는 로직 필요. - 별도의 메서드로 만들어서 공통처리하도록.



    /*회원 정보 조회*/
    @Transactional(readOnly = true)
    @Override
    public UserResponseDTO getUser(Long userId) {

        //유저 조회
        User user = userCheck(userId);

        //좋아요 수 조회
        Long userWebtoonLikeCount = userWebtoonRepositorySupport.findUserWebtoonLikeCount(user);

        //평가한 웹툰 수 조회
        Long userWebtoonScoreCount = userWebtoonRepositorySupport.findUserWebtoonScoreCount(user);


        return UserResponseDTO.createDTO(user,userWebtoonLikeCount,userWebtoonScoreCount);
    }

    /*회원 정보 수정*/
    // TODO : 이미지 파일을 받아서 S3 저장하는 로직 필요.
    @Transactional
    @Override
    public void updateUser(long userId, UserUpdateRequestDTO userUpdateRequestDTO, MultipartFile file) {

        //유저 조회
        User user = userCheck(userId);

        String updateFilePath = "";

        //TODO : 파일 업로드 처리 로직 필요.
        if(file != null){
            updateFilePath = saveFile(userId, file);

            //DTO에 파일 경로 저장
            userUpdateRequestDTO.setUserImage(updateFilePath);
        }


        //유저 업데이트
        user.updateUser(userUpdateRequestDTO);


    }


    /*회원 탈퇴*/
    @Transactional
    @Override
    public void removeUser(long userId) {
        //유저 조회
        User user = userCheck(userId);

        //유저 삭제
        user.removeUser();
    }

    /*작성한 댓글 조회*/
    @Transactional(readOnly = true)
    @Override
    public List<UserCommentResponseDTO> getUserCommentAll(long userId) {

        //유저 조회
        User user = userCheck(userId);
        
        //댓글 조회
        List<Comment> commentList = commentRepositorySupport.findByUserCommentAll(user);

        //response DTO로 변환.
        List<UserCommentResponseDTO> userCommentResponseDTOS = commentList.stream()
                .map(UserCommentResponseDTO::createDTO)
                .collect(Collectors.toList());

        return userCommentResponseDTOS;
    }

    /*관심 웹툰 조회 - 요일 별 조회(0이면 전체 조회.).*/
    @Transactional(readOnly = true)
    @Override
    public List<WebtoonInfoDTO> getUserWebtoonAll(long userId, Integer dayId) {
        //유저 조회
        User user = userCheck(userId);

        //관심 웹툰 조회
        List<UserWebtoon> userWebtoons = userWebtoonRepositorySupport.findUserWebtoonLikeAll(user, dayId);

        //response DTO 변환
        List<WebtoonInfoDTO> webtoonInfoDTOS = userWebtoons.stream()
                .map(WebtoonInfoDTO::createDTO)
                .collect(Collectors.toList());

        return webtoonInfoDTOS;
    }

    /*관심 웹툰 삭제*/
    @Transactional
    @Override
    public void deleteUserWebtoon(long userId,List<Long> webtoonIds) {

        //유저 조회
        User user = userCheck(userId);

        //관심 웹툰 조회
        webtoonIds.forEach(id -> {
            UserWebtoon userWebtoon = userWebtoonRepository.findByUserAndIsDeletedFalseAndIsLikedTrue(user)
                    .orElseThrow(() -> new CustomException(NOT_FOUND_USER_WEBTOON));

            userWebtoon.removeUserWebtoon();
        });

    }

    /*선호 장르 선택*/
    @Transactional
    @Override
    public void selectLikeGenre(long userId,List<Integer> genreIds) {
        //유저 조회
        User user = userCheck(userId);

        genreIds.forEach(id -> {

            //선택된 장르들을 하나씩 조회.
            Genre genre = genreCodeRepository.findById(id)
                    .orElseThrow(() -> new CustomException(NOT_FOUNT_GENRE));


            //해당 장르를 유저가 전에 선택했는지 확인.
            userGenreRepository.findById(UserGenreId.createUserGenreId(user.getId(), genre.getId()))
                    .ifPresentOrElse(
                            //장르가 있으면 +10
                            userGenre -> userGenre.updateUserGenre(),
                            //입력한적 없으면 생성
                            () ->{
                                UserGenre userGenre = UserGenre.createUserGenre(user, genre);
                                userGenreRepository.save(userGenre);
                            } );
        });
    }

    /*선호했던 장르 조회*/
    @Override
    public GenreResponseDTO findSelectLikeGenre(long userId) {

        //유저 조회
        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_USER));

        //유저가 선택한 장르 조회
        List<PreferGenre> selectGenre = preferGenreRepositorySupport.findSelectGenre(user);
        System.out.println(selectGenre);

        //DTO 변환.
        GenreResponseDTO genreResponseDTO = GenreResponseDTO.createDTO(selectGenre);


        return genreResponseDTO;
    }


    /*선호 웹툰 선택*/
    @Transactional
    @Override
    public void selectLikeWebtoon(long userId,List<Long> webtoonIds) {

        //유저 조회
        User user = userCheck(userId);

        webtoonIds.forEach(id -> {
            //해당 웹툰 조회
            Webtoon webtoon = webtoonRepository.findByIdAndIsDeletedFalse(id)
                    .orElseThrow(() -> new CustomException(NOT_FOUNT_WEBTOON));

            List<WebtoonGenre> webtoonGenres = webtoonGenreRepository.findByWebtoonId(webtoon.getId());


            webtoonGenres.forEach(webtoonGenre ->
                userGenreRepository.findById(UserGenreId.createUserGenreId(user.getId(), webtoonGenre.getGenre().getId()))
                        .ifPresentOrElse(
                                //장르가 있으면 +5
                                userGenre -> userGenre.updateUserWebtoonGenre(),
                                //입력한적 없으면 2점으로 생성
                                () -> {
                                    UserGenre userGenre = UserGenre.createUserWebtoonGenre(user, webtoonGenre.getGenre());
                                    userGenreRepository.save(userGenre);
                                })
            );
        });
    }

    /*유틸 메서드*/
    //유저 조회하는 메서드
    private User userCheck(long userId) {

        User user = userRepository.findByIdAndIsDeletedFalse(userId)
                .orElseThrow(() -> new CustomException(NOT_FOUNT_USER));
        return user;
    }

    /*S3 파일 저장.*/
    private String saveFile(long userId, MultipartFile file) {
        String storageFileUrl;

        //저장에 필요한 메타데이터
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        //유저가 업로드한 파일의 이름
        String originFileName = file.getOriginalFilename();

        //확장자 추출
        int index = originFileName.lastIndexOf(".");
        String ext = originFileName.substring(index+1);

        //저장할 이름
        String storeFileName = UUID.randomUUID().toString() + "." + ext;

        //파일 저장위치
        String key = PROFILE_IMAGE + userId + "/" + storeFileName;

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, key, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        }
        catch(IOException e){

            throw new CustomException(FILE_SAVE_FAIL);
        }

        storageFileUrl = amazonS3Client.getUrl(bucket,key).toString();

        return storageFileUrl;
    }
}

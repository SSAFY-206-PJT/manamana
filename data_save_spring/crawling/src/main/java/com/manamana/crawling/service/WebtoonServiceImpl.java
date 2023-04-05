package com.manamana.crawling.service;

import com.manamana.crawling.dto.WebtoonUpdateDTO;
import com.manamana.crawling.entity.webtoon.*;
import com.manamana.crawling.entity.webtoon.codetable.DayCode;
import com.manamana.crawling.entity.webtoon.codetable.Genre;
import com.manamana.crawling.repository.*;
import com.manamana.crawling.dto.WebtoonDataDTO;
import com.manamana.crawling.dto.WebtoonDataArrayDTO;
import com.manamana.crawling.repository.WebtoonProviderRepository;
import com.manamana.crawling.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class WebtoonServiceImpl implements WebtoonService {

    private final WebtoonRepository webtoonRepository;
    private final WebtoonProviderRepository webtoonProviderRepository;
    private final GenreRepository genreRepository;
    private final WebtoonGenreRepository webtoonGenreRepository;
    private final DayCodeRepository dayCodeRepository;
    private final WebtoonDayRepository webtoonDayRepository;
    private final AuthorRepository authorRepository;
    private final WebtoonAdditionRepository webtoonAdditionRepository;

    public WebClient webClient() {
        return WebClient.create("http://localhost:8080");
    }


    // 웹툰 데이터 리스트 처리
    public void webtoonsData(WebtoonDataArrayDTO webtoonDataArrayDTO) {
        int providerId = webtoonDataArrayDTO.getProvider_id();
        List<WebtoonDataDTO> webtoonData = webtoonDataArrayDTO.getData();

        // 업데이트 리스트를 저장
         List<Long> ids = new ArrayList<>();

        webtoonData.forEach(w -> {
            Webtoon webtoon = saveWebtoon(providerId, w);
            ids.add(webtoon.getId());
            saveGenre(webtoon, w);
            saveDay(webtoon, w);
            saveAuthor(webtoon, w);
            saveAddition(webtoon);
        });

        WebtoonUpdateDTO webtoonUpdateDTO = WebtoonUpdateDTO.createDTO(ids);

        String block = webClient().post()
                .uri("/webtoons/alarm")
                .bodyValue(webtoonUpdateDTO)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    // 웹툰 저장
    private Webtoon saveWebtoon(int provider, WebtoonDataDTO webtoonDataDTO) {
        // 웹툰 데이터 정제
        String grade = webtoonDataDTO.getGrade().trim();
        int gradeId = 1;
        if (grade.contains("성인")) {
            gradeId = 2;
        }

        String status = webtoonDataDTO.getStatus().trim();
        int statusId = 1;
        if (status.contains("휴재")) {
            statusId = 3;
        } else if (status.contains("완결")) {
            statusId = 2;
        }

        String webtoonId = Integer.toString(webtoonDataDTO.getWebtoon_id()).trim();
        String startDateStirng = "20" + webtoonDataDTO.getStart_date().trim();
        LocalDate startDate = LocalDate.parse(startDateStirng.replace(".", "-"), DateTimeFormatter.ISO_DATE);
        WebtoonProvider webtoonProvider = webtoonProviderRepository.findById(provider).orElseThrow();

        Optional<Webtoon> findedWebtoon = webtoonRepository.findByWebtoonIdAndProviderId(webtoonId, webtoonProvider);



        if (!findedWebtoon.isEmpty()) {
            Webtoon updatedWebtoon = findedWebtoon.get();
            updatedWebtoon.updateName(webtoonDataDTO.getName().trim());
            updatedWebtoon.updateImagePath(webtoonDataDTO.getImage().trim());
            updatedWebtoon.updatePlot(webtoonDataDTO.getPlot().trim());
            updatedWebtoon.updateGradeId(gradeId);
            updatedWebtoon.updateStatusId(statusId);
            updatedWebtoon.updateWebtoonUrl(webtoonDataDTO.getWebtoon_url().trim());
            updatedWebtoon.updateStartDate(startDate);
            updatedWebtoon.updateTotalEp(webtoonDataDTO.getTotal_ep());
            updatedWebtoon.updateColorHsl(webtoonDataDTO.getColorHsl().trim());
            return updatedWebtoon;
        }
        Optional<Webtoon> webtoonByName = webtoonRepository.findByName(webtoonDataDTO.getName());
        if (webtoonByName.isEmpty()) {
            Webtoon webtoon = Webtoon.builder()
                    .name(webtoonDataDTO.getName().trim())
                    .imagePath(webtoonDataDTO.getImage())
                    .plot(webtoonDataDTO.getPlot().trim())
                    .gradeId(gradeId)
                    .statusId(statusId)
                    .webtoonUrl(webtoonDataDTO.getWebtoon_url().trim())
                    .webtoonId(webtoonId)
                    .startDate(startDate)
                    .totalEp(webtoonDataDTO.getTotal_ep())
                    .colorHsl(webtoonDataDTO.getColorHsl().trim())
                    .isDeleted(false)
                    .providerId(webtoonProvider)
                    .build();
            return webtoonRepository.save(webtoon);
        }
        return webtoonByName.orElseThrow();


    }

    // 장르 저장
    private void saveGenre(Webtoon webtoon, WebtoonDataDTO webtoonDataDTO) {
        List<WebtoonGenre> webtoonGenres = new ArrayList<>();
        List<Integer> genreIds = new ArrayList<>();
        webtoonDataDTO.getGenre_arr().forEach(genreName -> {

            Optional<Genre> genre = genreRepository.findByName(genreName);
            if (!genre.isEmpty()) {
                genreIds.add(genre.get().getId());
            } else {
                genreRepository.findAll().forEach(g -> {
                    if (genreName.contains(g.getName())) {
                        genreIds.add(g.getId());
                    }
                });
                }
            });

            genreIds.forEach(genreId -> {
                if (genreId != 0) {
                    Genre genre = genreRepository.findById(genreId).orElseThrow();

                    WebtoonGenreId webtoonGenreId = WebtoonGenreId.builder()
                            .webtoonId(webtoon.getId())
                            .genreId(genreId)
                            .build();

                    WebtoonGenre webtoonGenre = WebtoonGenre.builder()
                            .id(webtoonGenreId)
                            .genre(genre)
                            .webtoon(webtoon)
                            .build();
                    webtoonGenres.add(webtoonGenre);
                }
            });

        List<WebtoonGenre> webtoonGenreList = webtoonGenres
                .stream()
                .distinct()
                .collect(Collectors.toList());
        webtoonGenreRepository.saveAll(webtoonGenreList);
    }

    // 요일 저장
    private void saveDay(Webtoon webtoon, WebtoonDataDTO webtoonDataDTO) {
        // 입력받은 요일객체를 저장할 리스트 (요일 객체)
        List<WebtoonDay> webtoonDays = new ArrayList<>();

        // 이미 저장되어 있는 요일을 저장할 리스트 (요일 아이디)
        List<Integer> webtoonDayList = webtoonDayRepository.findByWebtoon(webtoon).stream()
                .map(d -> d.getCodeId())
                .collect(Collectors.toList());

        // 입력받은 요일에 맞는 요일 아이디를 저장할 리스트 (요일 아이디)
        List<Integer> dayIdArr = webtoonDataDTO.getDay_arr().stream()
                .map(d -> {
                    Optional<DayCode> dayCode = dayCodeRepository.findByDay(d);
                    if (dayCode.isEmpty()) {
                        return 8;
                    }
                    return dayCode.get().getId();
                })
                .distinct()
                .collect(Collectors.toList());

        // 삭제할 요일 리스트 (요일 아이디)
        List<Integer> deletedList = webtoonDayList.stream()
                .filter(d -> !dayIdArr.contains(d))
                .collect(Collectors.toList());
        // 저장할 요일 리스트 (요일 아이디)
        List<Integer> saveList = dayIdArr.stream()
                .filter(d -> !webtoonDayList.contains(d))
                .collect(Collectors.toList());

        // 저장할 요일을 요일 객체로 만들기
        saveList.forEach(dayId -> {

            WebtoonDay webtoonDay = WebtoonDay.builder()
                    .codeId(dayId)
                    .webtoon(webtoon)
                    .build();
            webtoonDays.add(webtoonDay);
        });
        // 저장
        webtoonDayRepository.saveAll(webtoonDays);
        // 삭제할 요일을 db아이디로 변환
        List<WebtoonDay> deleteDays = deletedList.stream()
                .map(d -> {
                    System.out.println("HERE :" + d);
                    Optional<WebtoonDay> byCodeIdAndWebtoon = webtoonDayRepository.findByCodeIdAndWebtoon(d, webtoon);
                    return byCodeIdAndWebtoon.get();
                })
                .collect(Collectors.toList());
        System.out.println("FINE");
        // 삭제
        webtoonDayRepository.deleteAllInBatch(deleteDays);
    }

    // 작가 저장
    private void saveAuthor(Webtoon webtoon, WebtoonDataDTO webtoonDataDTO) {
        List<Author> authors = new ArrayList<>();
        List<String> webtoonAuthorList = authorRepository.findByWebtoon(webtoon).stream()
                .map(a -> a.getName())
                .collect(Collectors.toList());
        List<String> authorsArr = webtoonDataDTO.getAuthors_arr();

        //리스트에 있는 값은 DB에서 지우기
        List<String> deletedList = webtoonAuthorList.stream()
                .filter(a -> !authorsArr.contains(a))
                .collect(Collectors.toList());

        List<String> saveList = authorsArr.stream()
                .filter(a -> !webtoonAuthorList.contains(a))
                .collect(Collectors.toList());

        saveList.forEach(authorName -> {

             Author author = Author.builder()
                     .name(authorName)
                     .webtoon(webtoon)
                     .build();
             authors.add(author);
        });

        authorRepository.saveAll(authors);

        List<Author> deleteAuthors = new ArrayList<>();
        deletedList.stream()
                .forEach(a -> {
                    List<Author> byNameAndWebtoon = authorRepository.findByNameAndWebtoon(a, webtoon);
                    System.out.println(byNameAndWebtoon);
                    byNameAndWebtoon.forEach(q -> deleteAuthors.add(q));
                });

        authorRepository.deleteAllInBatch(deleteAuthors);

    }

    private void saveAddition(Webtoon webtoon) {

        Optional<WebtoonAddition> additionByWebtoon = webtoonAdditionRepository.findByWebtoon(webtoon);

        if (additionByWebtoon.isEmpty()) {
            WebtoonAddition webtoonAddition = WebtoonAddition.builder()
                    .view(0)
                    .totalScore(0)
                    .scoreCount(0)
                    .webtoon(webtoon)
                    .build();
            webtoonAdditionRepository.save(webtoonAddition);
        }
    }
}

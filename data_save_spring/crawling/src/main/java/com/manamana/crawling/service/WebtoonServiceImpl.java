package com.manamana.crawling.service;

import com.manamana.crawling.entity.webtoon.*;
import com.manamana.crawling.entity.webtoon.codetable.Genre;
import com.manamana.crawling.repository.*;
import com.manamana.crawling.dto.WebtoonDataDTO;
import com.manamana.crawling.dto.WebtoonDataArrayDTO;
import com.manamana.crawling.repository.WebtoonProviderRepository;
import com.manamana.crawling.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 웹툰 데이터 리스트 처리
    public void webtoonsData(WebtoonDataArrayDTO webtoonDataArrayDTO) {
        int providerId = webtoonDataArrayDTO.getProvider_id();
        List<WebtoonDataDTO> webtoonData = webtoonDataArrayDTO.getData();
        webtoonData.forEach(w -> {
            Webtoon webtoon = saveWebtoon(providerId, w);
            saveGenre(webtoon, w);
            saveDay(webtoon, w);
            saveAuthor(webtoon, w);
        });
    }


    // 웹툰 저장
    private Webtoon saveWebtoon(int provider, WebtoonDataDTO webtoonDataDTO) {
        // 웹툰 데이터 정제
        String grade = webtoonDataDTO.getGrade();
        int gradeId = 0;
        if (grade.equals("성인")) {
            gradeId = 1;
        }

        String status = webtoonDataDTO.getStatus();
        int statusId = 0;
        if (status.equals("휴재")) {
            statusId = 2;
        } else if (status.equals("완결")) {
            statusId = 1;
        }

        String webtoonId = Integer.toString(webtoonDataDTO.getWebtoon_id());
        String startDateStirng = "20" + webtoonDataDTO.getStart_date();
        LocalDate startDate = LocalDate.parse(startDateStirng.replace(".", "-"), DateTimeFormatter.ISO_DATE);
        WebtoonProvider webtoonProvider = webtoonProviderRepository.findById(provider).orElseThrow();

        Optional<Webtoon> findedWebtoon = webtoonRepository.findByWebtoonIdAndProviderId(webtoonId, webtoonProvider);
        if (!findedWebtoon.isEmpty()) {
            Webtoon updatedWebtoon = findedWebtoon.get();
            updatedWebtoon.updateName(webtoonDataDTO.getName());
            updatedWebtoon.updateImagePath(webtoonDataDTO.getImage());
            updatedWebtoon.updatePlot(webtoonDataDTO.getPlot());
            updatedWebtoon.updateGradeId(gradeId);
            updatedWebtoon.updateSerialId(statusId);
            updatedWebtoon.updateWebtoonUrl(webtoonDataDTO.getWebtoon_url());
            updatedWebtoon.updateStartDate(startDate);
            updatedWebtoon.updateTotalEp(webtoonDataDTO.getTotal_ep());
            updatedWebtoon.updateColorHsl(webtoonDataDTO.getColorHsl());
            return updatedWebtoon;
        }

        Webtoon webtoon = Webtoon.builder()
                .name(webtoonDataDTO.getName())
                .imagePath(webtoonDataDTO.getImage())
                .plot(webtoonDataDTO.getPlot())
                .gradeId(gradeId)
                .serialId(statusId)
                .webtoonUrl(webtoonDataDTO.getWebtoon_url())
                .webtoonId(webtoonId)
                .startDate(startDate)
                .totalEp(webtoonDataDTO.getTotal_ep())
                .colorHsl(webtoonDataDTO.getColorHsl())
                .isDeleted(false)
                .providerId(webtoonProvider)
                .build();
        return webtoonRepository.save(webtoon);
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

        List<WebtoonDay> webtoonDays = new ArrayList<>();

        webtoonDataDTO.getDay_arr().forEach(day -> {

            int dayId = dayCodeRepository.findByDay(day).orElseThrow().getId();

            WebtoonDay webtoonDay = WebtoonDay.builder()
                    .codeId(dayId)
                    .webtoon(webtoon)
                    .build();
            webtoonDays.add(webtoonDay);
        });

        webtoonDayRepository.saveAll(webtoonDays);
    }

    // 작가 저장
    private void saveAuthor(Webtoon webtoon, WebtoonDataDTO webtoonDataDTO) {
        List<Author> authors = new ArrayList<>();
        List<Author> webtoonAuthorList = authorRepository.findByWebtoon(webtoon);
        webtoonDataDTO.getAuthors_arr().forEach(authorName -> {

             Optional<Author> findedAuthor = authorRepository.findByNameAndWebtoon(authorName, webtoon);
             if (!findedAuthor.isEmpty()) {
                 Author author = Author.builder()
                         .name(authorName)
                         .webtoon(webtoon)
                         .build();
                 authors.add(author);
             }
             


        });
        //TODO 여기 할 순서 리스트에 있는 값은 DB에서 지우기
        List<Author> deletedList = webtoonAuthorList.stream()
                .filter(a -> !authors.contains(a))
                .collect(Collectors.toList());
        //TODO 중복은 저장 안되게        
        authorRepository.saveAll(authors);
    }
}

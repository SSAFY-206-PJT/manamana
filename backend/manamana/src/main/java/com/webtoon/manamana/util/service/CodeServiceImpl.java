package com.webtoon.manamana.util.service;

import com.webtoon.manamana.entity.webtoon.WebtoonProvider;
import com.webtoon.manamana.entity.webtoon.codetable.*;
import com.webtoon.manamana.util.dto.response.*;
import com.webtoon.manamana.util.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService{

    private final DayCodeRepository dayCodeRepository;
    private final GenreCodeRepository genreCodeRepository;
    private final GradeCodeRepository gradeCodeRepository;
    private final SortCodeRepository sortCodeRepository;
    private final StatusCodeRepository statusCodeRepository;

    private final WebtoonProviderRepository webtoonProviderRepository;

    @Override
    public List<DayResponseDTO> getDays() {

        List<DayCode> dayCodes = dayCodeRepository.findAll();

        List<DayResponseDTO> dayResponseDTOS = dayCodes.stream()
                .map(DayResponseDTO::createDTO)
                .collect(Collectors.toList());

        return dayResponseDTOS;
    }

    @Override
    public List<GenreResponseDTO> getGenres() {

        List<Genre> genres = genreCodeRepository.findAll();

        List<GenreResponseDTO> genreResponseDTOS = genres.stream()
                .map(GenreResponseDTO::createDTO)
                .collect(Collectors.toList());

        return genreResponseDTOS;
    }

    @Override
    public List<GradeResponseDTO> getGrades() {

        List<Grade> grades = gradeCodeRepository.findAll();

        List<GradeResponseDTO> gradeResponseDTOS = grades.stream()
                .map(GradeResponseDTO::createDTO)
                .collect(Collectors.toList());

        return gradeResponseDTOS;
    }

    @Override
    public List<SortResponseDTO> getSorts() {

        List<Sort> sorts = sortCodeRepository.findAll();

        List<SortResponseDTO> sortResponseDTOS = sorts.stream()
                .map(SortResponseDTO::createDTO)
                .collect(Collectors.toList());

        return sortResponseDTOS;
    }

    @Override
    public List<StatusResponseDTO> getStatuses() {

        List<SerialStatus> statuses = statusCodeRepository.findAll();

        List<StatusResponseDTO> statusResponseDTOS = statuses.stream()
                .map(StatusResponseDTO::createDTO)
                .collect(Collectors.toList());

        return statusResponseDTOS;
    }

    @Override
    public List<WebtoonProviderResponseDTO> providers() {

        List<WebtoonProvider> webtoonProviders = webtoonProviderRepository.findAll();

        List<WebtoonProviderResponseDTO> webtoonProviderResponseDTOS = webtoonProviders.stream()
                .map(WebtoonProviderResponseDTO::createDTO)
                .collect(Collectors.toList());

        return webtoonProviderResponseDTOS;
    }
}

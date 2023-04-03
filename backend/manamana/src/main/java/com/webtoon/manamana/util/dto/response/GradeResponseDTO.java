package com.webtoon.manamana.util.dto.response;

import com.webtoon.manamana.entity.webtoon.codetable.Grade;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeResponseDTO {

    private int id;
    private String grade;

    @Builder
    public GradeResponseDTO(int id, String grade) {
        this.id = id;
        this.grade = grade;
    }

    public static GradeResponseDTO createDTO(Grade grade){

        return GradeResponseDTO.builder()
                .id(grade.getId())
                .grade(grade.getGrade()).build();
    }
}

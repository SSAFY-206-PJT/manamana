package com.webtoon.manamana.entity.webtoon.codetable;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "grade")
    private String grade;

}

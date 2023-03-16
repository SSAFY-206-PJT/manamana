package com.webtoon.manamana.entity.webtoon.codetable;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "day_codes")
public class DayCode {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "day")
    private String day;

}

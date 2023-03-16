package com.webtoon.manamana.entity.webtoon.codetable;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "sorts")
public class Sort {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}

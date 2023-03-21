package com.manamana.crawling.entity.webtoon.codetable;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "genres")
public class Genre {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

}

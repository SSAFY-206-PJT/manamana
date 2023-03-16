package com.webtoon.manamana.entity.webtoon;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.IDN;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "webtoon_providers")
public class WebtoonProvider {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;
}

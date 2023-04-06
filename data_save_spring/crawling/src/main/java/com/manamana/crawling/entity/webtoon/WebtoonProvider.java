package com.manamana.crawling.entity.webtoon;

import lombok.AllArgsConstructor;
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

    @Column(name = "provider_url")
    private String url;

    @Column(name = "provider_image")
    private String image;

}

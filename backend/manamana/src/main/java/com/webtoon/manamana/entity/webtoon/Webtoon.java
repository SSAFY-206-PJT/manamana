package com.webtoon.manamana.entity.webtoon;


import com.webtoon.manamana.config.entity.BaseTimeEntity;
import com.webtoon.manamana.entity.user.UserWebtoon;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "webtoons")
public class Webtoon extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "plot")
    private String plot;
    @Column(name = "grade_id")
    private int gradeId;
    @Column(name = "status_id")
    private int statusId;
    @Column(name = "webtoon_url")
    private String webtoonUrl;

    @Column(name = "webtoon_id")
    private String webtoonId;

    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "total_ep")
    private int totalEp;

    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "color_hsl")
    private String colorHsl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private WebtoonProvider providerId;

    @OneToMany(mappedBy = "webtoon")
    private Set<Comment> comment = new HashSet<>();


    @OneToMany(mappedBy = "webtoon")
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy= "webtoon")
    private Set<WebtoonDay> webtoonDays = new HashSet<>();

    @OneToMany(mappedBy = "webtoon")
    private Set<WebtoonGenre> webtoonGenres = new HashSet<>();

    @OneToOne(mappedBy = "webtoon", fetch = FetchType.LAZY)
    private WebtoonAddition webtoonAddition;


    @OneToMany(mappedBy = "webtoon")
    private Set<UserWebtoon> userWebtoons = new HashSet<>();

}

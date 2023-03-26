package com.webtoon.manamana.entity.webtoon;


import com.webtoon.manamana.config.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private List<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "webtoon")
    private List<Author> authors = new ArrayList<>();

    @OneToMany(mappedBy= "webtoon")
    private List<WebtoonDay> webtoonDays = new ArrayList<>();




}

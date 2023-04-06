package com.manamana.crawling.entity.webtoon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manamana.crawling.config.entity.BaseTimeEntity;
import com.manamana.crawling.entity.user.UserWebtoon;
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
//@Builder
@Table(name = "webtoons")
public class Webtoon extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonIgnore
    @OneToMany(mappedBy = "webtoon")
    private Set<Author> authors = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy= "webtoon")
    private Set<WebtoonDay> webtoonDays = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "webtoon")
    private Set<WebtoonGenre> webtoonGenres = new HashSet<>();

    @OneToOne(mappedBy = "webtoon", fetch = FetchType.LAZY)
    private WebtoonAddition webtoonAddition;

    @JsonIgnore
    @OneToMany(mappedBy = "webtoon")
    private Set<UserWebtoon> userWebtoons = new HashSet<>();


    @Builder
    public Webtoon(long id, String name, String imagePath, String plot, int gradeId, int statusId, String webtoonUrl, String webtoonId, LocalDate startDate, int totalEp, String colorHsl, boolean isDeleted, WebtoonProvider providerId) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.plot = plot;
        this.gradeId = gradeId;
        this.statusId = statusId;
        this.webtoonUrl = webtoonUrl;
        this.webtoonId = webtoonId;
        this.startDate = startDate;
        this.totalEp = totalEp;
        this.colorHsl = colorHsl;
        this.isDeleted = isDeleted;
        this.providerId = providerId;
    }


    public void updateName(String name) {
        this.name = name;
    }

    public void updateImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void updatePlot(String plot) {
        this.plot = plot;
    }

    public void updateGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public void updateStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void updateWebtoonUrl(String webtoonUrl) {
        this.webtoonUrl = webtoonUrl;
    }

    public void updateStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void updateTotalEp(int totalEp) {
        this.totalEp = totalEp;
    }

    public void updateColorHsl(String colorHsl) {
        this.colorHsl = colorHsl;
    }



}

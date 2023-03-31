package com.webtoon.manamana.entity.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
@Table(name = "prefer_genres")
public class PreferGenre {


    @Column(name = "id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "genre_id")
    private int genreId;

    @Column(name = "is_canceled")
    private boolean isCanceled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public PreferGenre(long id, int genreId, boolean isCanceled, User user) {
        this.id = id;
        this.genreId = genreId;
        this.isCanceled = isCanceled;
        this.user = user;
    }

    public static PreferGenre createPreferGenre(User user, int genreId){

        return PreferGenre.builder()
                .genreId(genreId)
                .isCanceled(false)
                .user(user).build();

    }


    public void updatePreferGenre(boolean isCanceled){
        this.isCanceled = isCanceled;
    }
}



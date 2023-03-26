package com.webtoon.manamana.entity.user;

import com.webtoon.manamana.config.entity.BaseTimeEntity;
import com.webtoon.manamana.entity.webtoon.codetable.Genre;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "users_and_genres")
public class UserGenre extends BaseTimeEntity {

    @EmbeddedId
    private UserGenreId userGenreId;

    @Column(name = "weight")
    private int weight;

    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @MapsId("genreId")
    @JoinColumn(name = "genre_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Genre genre;


    @Builder
    public UserGenre(UserGenreId userGenreId, int weight, User user, Genre genre) {
        this.userGenreId = userGenreId;
        this.weight = weight;
        this.user = user;
        this.genre = genre;
    }

    public static UserGenre createUserGenre(User user, Genre genre){

        return UserGenre.builder()
                .userGenreId(UserGenreId.createUserGenreId(user.getId(), genre.getId()))
                .weight(50)
                .user(user)
                .genre(genre).build();
    }

    public void updateUserGenre(){
        this.weight += 10;
    }

    public static UserGenre createUserWebtoonGenre(User user, Genre genre){

        return UserGenre.builder()
                .userGenreId(UserGenreId.createUserGenreId(user.getId(), genre.getId()))
                .weight(5)
                .user(user)
                .genre(genre).build();
    }

    public void updateUserWebtoonGenre(){
        this.weight += 2;
    }


}

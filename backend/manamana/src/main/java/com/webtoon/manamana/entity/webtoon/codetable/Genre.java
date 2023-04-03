package com.webtoon.manamana.entity.webtoon.codetable;


import com.webtoon.manamana.entity.user.UserGenre;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


}

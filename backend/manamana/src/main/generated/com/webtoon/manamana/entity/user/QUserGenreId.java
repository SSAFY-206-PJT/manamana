package com.webtoon.manamana.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserGenreId is a Querydsl query type for UserGenreId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserGenreId extends BeanPath<UserGenreId> {

    private static final long serialVersionUID = 661059720L;

    public static final QUserGenreId userGenreId = new QUserGenreId("userGenreId");

    public final NumberPath<Integer> genreId = createNumber("genreId", Integer.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserGenreId(String variable) {
        super(UserGenreId.class, forVariable(variable));
    }

    public QUserGenreId(Path<? extends UserGenreId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserGenreId(PathMetadata metadata) {
        super(UserGenreId.class, metadata);
    }

}


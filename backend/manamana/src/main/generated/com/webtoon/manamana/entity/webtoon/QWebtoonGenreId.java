package com.webtoon.manamana.entity.webtoon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWebtoonGenreId is a Querydsl query type for WebtoonGenreId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QWebtoonGenreId extends BeanPath<WebtoonGenreId> {

    private static final long serialVersionUID = 2032727784L;

    public static final QWebtoonGenreId webtoonGenreId = new QWebtoonGenreId("webtoonGenreId");

    public final NumberPath<Integer> genreId = createNumber("genreId", Integer.class);

    public final NumberPath<Long> webtoonId = createNumber("webtoonId", Long.class);

    public QWebtoonGenreId(String variable) {
        super(WebtoonGenreId.class, forVariable(variable));
    }

    public QWebtoonGenreId(Path<? extends WebtoonGenreId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWebtoonGenreId(PathMetadata metadata) {
        super(WebtoonGenreId.class, metadata);
    }

}


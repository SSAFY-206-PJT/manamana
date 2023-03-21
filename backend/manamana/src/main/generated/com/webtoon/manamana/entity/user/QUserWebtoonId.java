package com.webtoon.manamana.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserWebtoonId is a Querydsl query type for UserWebtoonId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserWebtoonId extends BeanPath<UserWebtoonId> {

    private static final long serialVersionUID = -1223741261L;

    public static final QUserWebtoonId userWebtoonId = new QUserWebtoonId("userWebtoonId");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Long> webtoonId = createNumber("webtoonId", Long.class);

    public QUserWebtoonId(String variable) {
        super(UserWebtoonId.class, forVariable(variable));
    }

    public QUserWebtoonId(Path<? extends UserWebtoonId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserWebtoonId(PathMetadata metadata) {
        super(UserWebtoonId.class, metadata);
    }

}


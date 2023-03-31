package com.webtoon.manamana.entity.webtoon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWebtoonDay is a Querydsl query type for WebtoonDay
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWebtoonDay extends EntityPathBase<WebtoonDay> {

    private static final long serialVersionUID = 619757958L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWebtoonDay webtoonDay = new QWebtoonDay("webtoonDay");

    public final NumberPath<Integer> codeId = createNumber("codeId", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QWebtoon webtoon;

    public QWebtoonDay(String variable) {
        this(WebtoonDay.class, forVariable(variable), INITS);
    }

    public QWebtoonDay(Path<? extends WebtoonDay> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWebtoonDay(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWebtoonDay(PathMetadata metadata, PathInits inits) {
        this(WebtoonDay.class, metadata, inits);
    }

    public QWebtoonDay(Class<? extends WebtoonDay> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.webtoon = inits.isInitialized("webtoon") ? new QWebtoon(forProperty("webtoon"), inits.get("webtoon")) : null;
    }

}


package com.webtoon.manamana.entity.webtoon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWebtoonAddition is a Querydsl query type for WebtoonAddition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWebtoonAddition extends EntityPathBase<WebtoonAddition> {

    private static final long serialVersionUID = -163537070L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWebtoonAddition webtoonAddition = new QWebtoonAddition("webtoonAddition");

    public final com.webtoon.manamana.config.entity.QBaseTimeEntity _super = new com.webtoon.manamana.config.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> scoreCount = createNumber("scoreCount", Long.class);

    public final NumberPath<Long> totalScore = createNumber("totalScore", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public final NumberPath<Long> view = createNumber("view", Long.class);

    public final QWebtoon webtoon;

    public QWebtoonAddition(String variable) {
        this(WebtoonAddition.class, forVariable(variable), INITS);
    }

    public QWebtoonAddition(Path<? extends WebtoonAddition> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWebtoonAddition(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWebtoonAddition(PathMetadata metadata, PathInits inits) {
        this(WebtoonAddition.class, metadata, inits);
    }

    public QWebtoonAddition(Class<? extends WebtoonAddition> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.webtoon = inits.isInitialized("webtoon") ? new QWebtoon(forProperty("webtoon"), inits.get("webtoon")) : null;
    }

}


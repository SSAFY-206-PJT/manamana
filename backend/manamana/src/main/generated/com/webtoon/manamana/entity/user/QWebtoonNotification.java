package com.webtoon.manamana.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWebtoonNotification is a Querydsl query type for WebtoonNotification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWebtoonNotification extends EntityPathBase<WebtoonNotification> {

    private static final long serialVersionUID = 1761609422L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWebtoonNotification webtoonNotification = new QWebtoonNotification("webtoonNotification");

    public final NumberPath<Integer> episode = createNumber("episode", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isChecked = createBoolean("isChecked");

    public final QUser user;

    public final com.webtoon.manamana.entity.webtoon.QWebtoon webtoon;

    public QWebtoonNotification(String variable) {
        this(WebtoonNotification.class, forVariable(variable), INITS);
    }

    public QWebtoonNotification(Path<? extends WebtoonNotification> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWebtoonNotification(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWebtoonNotification(PathMetadata metadata, PathInits inits) {
        this(WebtoonNotification.class, metadata, inits);
    }

    public QWebtoonNotification(Class<? extends WebtoonNotification> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
        this.webtoon = inits.isInitialized("webtoon") ? new com.webtoon.manamana.entity.webtoon.QWebtoon(forProperty("webtoon"), inits.get("webtoon")) : null;
    }

}


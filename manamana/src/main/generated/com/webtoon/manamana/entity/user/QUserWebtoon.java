package com.webtoon.manamana.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserWebtoon is a Querydsl query type for UserWebtoon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserWebtoon extends EntityPathBase<UserWebtoon> {

    private static final long serialVersionUID = 1965204856L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserWebtoon userWebtoon = new QUserWebtoon("userWebtoon");

    public final com.webtoon.manamana.config.entity.QBaseTimeEntity _super = new com.webtoon.manamana.config.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final QUserWebtoonId id;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final BooleanPath isLiked = createBoolean("isLiked");

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public final QUser user;

    public final com.webtoon.manamana.entity.webtoon.QWebtoon webtoon;

    public QUserWebtoon(String variable) {
        this(UserWebtoon.class, forVariable(variable), INITS);
    }

    public QUserWebtoon(Path<? extends UserWebtoon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserWebtoon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserWebtoon(PathMetadata metadata, PathInits inits) {
        this(UserWebtoon.class, metadata, inits);
    }

    public QUserWebtoon(Class<? extends UserWebtoon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QUserWebtoonId(forProperty("id")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
        this.webtoon = inits.isInitialized("webtoon") ? new com.webtoon.manamana.entity.webtoon.QWebtoon(forProperty("webtoon"), inits.get("webtoon")) : null;
    }

}


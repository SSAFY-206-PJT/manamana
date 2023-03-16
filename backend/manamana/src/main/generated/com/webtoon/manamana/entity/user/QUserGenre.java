package com.webtoon.manamana.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserGenre is a Querydsl query type for UserGenre
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserGenre extends EntityPathBase<UserGenre> {

    private static final long serialVersionUID = -687579507L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserGenre userGenre = new QUserGenre("userGenre");

    public final com.webtoon.manamana.config.entity.QBaseTimeEntity _super = new com.webtoon.manamana.config.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final com.webtoon.manamana.entity.webtoon.codetable.QGenre genre;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public final QUser user;

    public final QUserGenreId userGenreId;

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QUserGenre(String variable) {
        this(UserGenre.class, forVariable(variable), INITS);
    }

    public QUserGenre(Path<? extends UserGenre> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserGenre(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserGenre(PathMetadata metadata, PathInits inits) {
        this(UserGenre.class, metadata, inits);
    }

    public QUserGenre(Class<? extends UserGenre> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.genre = inits.isInitialized("genre") ? new com.webtoon.manamana.entity.webtoon.codetable.QGenre(forProperty("genre")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
        this.userGenreId = inits.isInitialized("userGenreId") ? new QUserGenreId(forProperty("userGenreId")) : null;
    }

}


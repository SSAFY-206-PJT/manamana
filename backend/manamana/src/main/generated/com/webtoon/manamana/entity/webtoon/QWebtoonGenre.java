package com.webtoon.manamana.entity.webtoon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWebtoonGenre is a Querydsl query type for WebtoonGenre
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWebtoonGenre extends EntityPathBase<WebtoonGenre> {

    private static final long serialVersionUID = -1410173715L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWebtoonGenre webtoonGenre = new QWebtoonGenre("webtoonGenre");

    public final com.webtoon.manamana.entity.webtoon.codetable.QGenre genre;

    public final QWebtoonGenreId id;

    public final QWebtoon webtoon;

    public QWebtoonGenre(String variable) {
        this(WebtoonGenre.class, forVariable(variable), INITS);
    }

    public QWebtoonGenre(Path<? extends WebtoonGenre> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWebtoonGenre(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWebtoonGenre(PathMetadata metadata, PathInits inits) {
        this(WebtoonGenre.class, metadata, inits);
    }

    public QWebtoonGenre(Class<? extends WebtoonGenre> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.genre = inits.isInitialized("genre") ? new com.webtoon.manamana.entity.webtoon.codetable.QGenre(forProperty("genre")) : null;
        this.id = inits.isInitialized("id") ? new QWebtoonGenreId(forProperty("id")) : null;
        this.webtoon = inits.isInitialized("webtoon") ? new QWebtoon(forProperty("webtoon"), inits.get("webtoon")) : null;
    }

}


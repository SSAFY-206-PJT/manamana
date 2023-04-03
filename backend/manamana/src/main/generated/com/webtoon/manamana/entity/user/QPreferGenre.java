package com.webtoon.manamana.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPreferGenre is a Querydsl query type for PreferGenre
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPreferGenre extends EntityPathBase<PreferGenre> {

    private static final long serialVersionUID = -1290368472L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPreferGenre preferGenre = new QPreferGenre("preferGenre");

    public final NumberPath<Integer> genreId = createNumber("genreId", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isCanceled = createBoolean("isCanceled");

    public final QUser user;

    public QPreferGenre(String variable) {
        this(PreferGenre.class, forVariable(variable), INITS);
    }

    public QPreferGenre(Path<? extends PreferGenre> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPreferGenre(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPreferGenre(PathMetadata metadata, PathInits inits) {
        this(PreferGenre.class, metadata, inits);
    }

    public QPreferGenre(Class<? extends PreferGenre> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}


package com.webtoon.manamana.entity.webtoon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWebtoon is a Querydsl query type for Webtoon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWebtoon extends EntityPathBase<Webtoon> {

    private static final long serialVersionUID = -1400302026L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWebtoon webtoon = new QWebtoon("webtoon");

    public final com.webtoon.manamana.config.entity.QBaseTimeEntity _super = new com.webtoon.manamana.config.entity.QBaseTimeEntity(this);

    public final ListPath<Author, QAuthor> authors = this.<Author, QAuthor>createList("authors", Author.class, QAuthor.class, PathInits.DIRECT2);

    public final StringPath colorHsl = createString("colorHsl");

    public final ListPath<Comment, QComment> comment = this.<Comment, QComment>createList("comment", Comment.class, QComment.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final NumberPath<Integer> gradeId = createNumber("gradeId", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imagePath = createString("imagePath");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath name = createString("name");

    public final StringPath plot = createString("plot");

    public final QWebtoonProvider providerId;

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final NumberPath<Integer> statusId = createNumber("statusId", Integer.class);

    public final NumberPath<Integer> totalEp = createNumber("totalEp", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public final ListPath<com.webtoon.manamana.entity.user.UserWebtoon, com.webtoon.manamana.entity.user.QUserWebtoon> userWebtoons = this.<com.webtoon.manamana.entity.user.UserWebtoon, com.webtoon.manamana.entity.user.QUserWebtoon>createList("userWebtoons", com.webtoon.manamana.entity.user.UserWebtoon.class, com.webtoon.manamana.entity.user.QUserWebtoon.class, PathInits.DIRECT2);

    public final QWebtoonAddition webtoonAddition;

    public final ListPath<WebtoonDay, QWebtoonDay> webtoonDays = this.<WebtoonDay, QWebtoonDay>createList("webtoonDays", WebtoonDay.class, QWebtoonDay.class, PathInits.DIRECT2);

    public final ListPath<WebtoonGenre, QWebtoonGenre> webtoonGenres = this.<WebtoonGenre, QWebtoonGenre>createList("webtoonGenres", WebtoonGenre.class, QWebtoonGenre.class, PathInits.DIRECT2);

    public final StringPath webtoonId = createString("webtoonId");

    public final StringPath webtoonUrl = createString("webtoonUrl");

    public QWebtoon(String variable) {
        this(Webtoon.class, forVariable(variable), INITS);
    }

    public QWebtoon(Path<? extends Webtoon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWebtoon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWebtoon(PathMetadata metadata, PathInits inits) {
        this(Webtoon.class, metadata, inits);
    }

    public QWebtoon(Class<? extends Webtoon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.providerId = inits.isInitialized("providerId") ? new QWebtoonProvider(forProperty("providerId")) : null;
        this.webtoonAddition = inits.isInitialized("webtoonAddition") ? new QWebtoonAddition(forProperty("webtoonAddition"), inits.get("webtoonAddition")) : null;
    }

}


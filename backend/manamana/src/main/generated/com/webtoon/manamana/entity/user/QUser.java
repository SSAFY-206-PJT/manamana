package com.webtoon.manamana.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1326688406L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final com.webtoon.manamana.config.entity.QBaseTimeEntity _super = new com.webtoon.manamana.config.entity.QBaseTimeEntity(this);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final ListPath<com.webtoon.manamana.entity.webtoon.Comment, com.webtoon.manamana.entity.webtoon.QComment> comment = this.<com.webtoon.manamana.entity.webtoon.Comment, com.webtoon.manamana.entity.webtoon.QComment>createList("comment", com.webtoon.manamana.entity.webtoon.Comment.class, com.webtoon.manamana.entity.webtoon.QComment.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createTime = _super.createTime;

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imagePath = createString("imagePath");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final QLoginProvider loginProvider;

    public final StringPath nickname = createString("nickname");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateTime = _super.updateTime;

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.loginProvider = inits.isInitialized("loginProvider") ? new QLoginProvider(forProperty("loginProvider")) : null;
    }

}


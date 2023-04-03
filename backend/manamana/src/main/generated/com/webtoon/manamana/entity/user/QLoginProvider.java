package com.webtoon.manamana.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLoginProvider is a Querydsl query type for LoginProvider
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLoginProvider extends EntityPathBase<LoginProvider> {

    private static final long serialVersionUID = -607496177L;

    public static final QLoginProvider loginProvider = new QLoginProvider("loginProvider");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final EnumPath<AuthProvider> name = createEnum("name", AuthProvider.class);

    public QLoginProvider(String variable) {
        super(LoginProvider.class, forVariable(variable));
    }

    public QLoginProvider(Path<? extends LoginProvider> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLoginProvider(PathMetadata metadata) {
        super(LoginProvider.class, metadata);
    }

}


package com.webtoon.manamana.entity.webtoon;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWebtoonProvider is a Querydsl query type for WebtoonProvider
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWebtoonProvider extends EntityPathBase<WebtoonProvider> {

    private static final long serialVersionUID = 75557447L;

    public static final QWebtoonProvider webtoonProvider = new QWebtoonProvider("webtoonProvider");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath url = createString("url");

    public QWebtoonProvider(String variable) {
        super(WebtoonProvider.class, forVariable(variable));
    }

    public QWebtoonProvider(Path<? extends WebtoonProvider> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWebtoonProvider(PathMetadata metadata) {
        super(WebtoonProvider.class, metadata);
    }

}


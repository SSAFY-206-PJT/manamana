package com.webtoon.manamana.entity.webtoon.codetable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSort is a Querydsl query type for Sort
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSort extends EntityPathBase<Sort> {

    private static final long serialVersionUID = 595544355L;

    public static final QSort sort = new QSort("sort");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QSort(String variable) {
        super(Sort.class, forVariable(variable));
    }

    public QSort(Path<? extends Sort> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSort(PathMetadata metadata) {
        super(Sort.class, metadata);
    }

}


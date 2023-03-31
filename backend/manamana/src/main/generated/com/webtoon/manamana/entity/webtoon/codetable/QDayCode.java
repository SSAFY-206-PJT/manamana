package com.webtoon.manamana.entity.webtoon.codetable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDayCode is a Querydsl query type for DayCode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDayCode extends EntityPathBase<DayCode> {

    private static final long serialVersionUID = -1471366652L;

    public static final QDayCode dayCode = new QDayCode("dayCode");

    public final StringPath day = createString("day");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QDayCode(String variable) {
        super(DayCode.class, forVariable(variable));
    }

    public QDayCode(Path<? extends DayCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDayCode(PathMetadata metadata) {
        super(DayCode.class, metadata);
    }

}


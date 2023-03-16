package com.webtoon.manamana.entity.webtoon.codetable;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSerialStatus is a Querydsl query type for SerialStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSerialStatus extends EntityPathBase<SerialStatus> {

    private static final long serialVersionUID = 256340363L;

    public static final QSerialStatus serialStatus = new QSerialStatus("serialStatus");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath status = createString("status");

    public QSerialStatus(String variable) {
        super(SerialStatus.class, forVariable(variable));
    }

    public QSerialStatus(Path<? extends SerialStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSerialStatus(PathMetadata metadata) {
        super(SerialStatus.class, metadata);
    }

}


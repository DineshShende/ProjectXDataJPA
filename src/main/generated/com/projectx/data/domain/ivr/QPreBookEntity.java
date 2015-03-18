package com.projectx.data.domain.ivr;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPreBookEntity is a Querydsl query type for PreBookEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPreBookEntity extends EntityPathBase<PreBookEntity> {

    private static final long serialVersionUID = 918167742L;

    public static final QPreBookEntity preBookEntity = new QPreBookEntity("preBookEntity");

    public final NumberPath<Long> freightRequestByCustomerId = createNumber("freightRequestByCustomerId", Long.class);

    public final NumberPath<Long> freightRequestByvendorId = createNumber("freightRequestByvendorId", Long.class);

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final NumberPath<Long> preBookId = createNumber("preBookId", Long.class);

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QPreBookEntity(String variable) {
        super(PreBookEntity.class, forVariable(variable));
    }

    public QPreBookEntity(Path<? extends PreBookEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPreBookEntity(PathMetadata<?> metadata) {
        super(PreBookEntity.class, metadata);
    }

}


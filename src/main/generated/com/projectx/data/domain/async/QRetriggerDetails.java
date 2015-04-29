package com.projectx.data.domain.async;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QRetriggerDetails is a Querydsl query type for RetriggerDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRetriggerDetails extends EntityPathBase<RetriggerDetails> {

    private static final long serialVersionUID = 365222839L;

    public static final QRetriggerDetails retriggerDetails = new QRetriggerDetails("retriggerDetails");

    public final StringPath data = createString("data");

    public final NumberPath<Long> retriggerId = createNumber("retriggerId", Long.class);

    public final StringPath service = createString("service");

    public QRetriggerDetails(String variable) {
        super(RetriggerDetails.class, forVariable(variable));
    }

    public QRetriggerDetails(Path<? extends RetriggerDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRetriggerDetails(PathMetadata<?> metadata) {
        super(RetriggerDetails.class, metadata);
    }

}


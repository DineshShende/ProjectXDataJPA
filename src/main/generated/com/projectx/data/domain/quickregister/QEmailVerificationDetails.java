package com.projectx.data.domain.quickregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEmailVerificationDetails is a Querydsl query type for EmailVerificationDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEmailVerificationDetails extends EntityPathBase<EmailVerificationDetails> {

    private static final long serialVersionUID = 1263846193L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmailVerificationDetails emailVerificationDetails = new QEmailVerificationDetails("emailVerificationDetails");

    public final StringPath email = createString("email");

    public final StringPath emailHash = createString("emailHash");

    public final DateTimePath<java.util.Date> emailHashSentTime = createDateTime("emailHashSentTime", java.util.Date.class);

    public final NumberPath<Integer> insertedBy = createNumber("insertedBy", Integer.class);

    public final NumberPath<Long> insertedById = createNumber("insertedById", Long.class);

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final QEmailVerificationKey key;

    public final NumberPath<Integer> resendCount = createNumber("resendCount", Integer.class);

    public final NumberPath<Integer> updatedBy = createNumber("updatedBy", Integer.class);

    public final NumberPath<Long> updatedById = createNumber("updatedById", Long.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QEmailVerificationDetails(String variable) {
        this(EmailVerificationDetails.class, forVariable(variable), INITS);
    }

    public QEmailVerificationDetails(Path<? extends EmailVerificationDetails> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmailVerificationDetails(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QEmailVerificationDetails(PathMetadata<?> metadata, PathInits inits) {
        this(EmailVerificationDetails.class, metadata, inits);
    }

    public QEmailVerificationDetails(Class<? extends EmailVerificationDetails> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.key = inits.isInitialized("key") ? new QEmailVerificationKey(forProperty("key")) : null;
    }

}


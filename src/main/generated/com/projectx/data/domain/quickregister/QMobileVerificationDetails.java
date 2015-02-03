package com.projectx.data.domain.quickregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMobileVerificationDetails is a Querydsl query type for MobileVerificationDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMobileVerificationDetails extends EntityPathBase<MobileVerificationDetails> {

    private static final long serialVersionUID = 1900696479L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMobileVerificationDetails mobileVerificationDetails = new QMobileVerificationDetails("mobileVerificationDetails");

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final QMobileVerificationKey key;

    public final NumberPath<Long> mobile = createNumber("mobile", Long.class);

    public final NumberPath<Integer> mobilePin = createNumber("mobilePin", Integer.class);

    public final NumberPath<Integer> mobileVerificationAttempts = createNumber("mobileVerificationAttempts", Integer.class);

    public final NumberPath<Integer> resendCount = createNumber("resendCount", Integer.class);

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QMobileVerificationDetails(String variable) {
        this(MobileVerificationDetails.class, forVariable(variable), INITS);
    }

    public QMobileVerificationDetails(Path<? extends MobileVerificationDetails> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMobileVerificationDetails(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMobileVerificationDetails(PathMetadata<?> metadata, PathInits inits) {
        this(MobileVerificationDetails.class, metadata, inits);
    }

    public QMobileVerificationDetails(Class<? extends MobileVerificationDetails> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.key = inits.isInitialized("key") ? new QMobileVerificationKey(forProperty("key")) : null;
    }

}


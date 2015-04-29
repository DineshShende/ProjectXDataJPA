package com.projectx.data.domain.quickregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAuthenticationDetails is a Querydsl query type for AuthenticationDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAuthenticationDetails extends EntityPathBase<AuthenticationDetails> {

    private static final long serialVersionUID = 55329188L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuthenticationDetails authenticationDetails = new QAuthenticationDetails("authenticationDetails");

    public final StringPath email = createString("email");

    public final StringPath emailPassword = createString("emailPassword");

    public final NumberPath<Integer> insertedBy = createNumber("insertedBy", Integer.class);

    public final NumberPath<Long> insertedById = createNumber("insertedById", Long.class);

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final QAuthenticationDetailsKey key;

    public final NumberPath<Integer> lastUnsucessfullAttempts = createNumber("lastUnsucessfullAttempts", Integer.class);

    public final NumberPath<Long> mobile = createNumber("mobile", Long.class);

    public final StringPath password = createString("password");

    public final StringPath passwordType = createString("passwordType");

    public final NumberPath<Integer> resendCount = createNumber("resendCount", Integer.class);

    public final NumberPath<Integer> updatedBy = createNumber("updatedBy", Integer.class);

    public final NumberPath<Long> updatedById = createNumber("updatedById", Long.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QAuthenticationDetails(String variable) {
        this(AuthenticationDetails.class, forVariable(variable), INITS);
    }

    public QAuthenticationDetails(Path<? extends AuthenticationDetails> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAuthenticationDetails(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAuthenticationDetails(PathMetadata<?> metadata, PathInits inits) {
        this(AuthenticationDetails.class, metadata, inits);
    }

    public QAuthenticationDetails(Class<? extends AuthenticationDetails> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.key = inits.isInitialized("key") ? new QAuthenticationDetailsKey(forProperty("key")) : null;
    }

}


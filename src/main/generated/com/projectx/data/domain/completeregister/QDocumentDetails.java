package com.projectx.data.domain.completeregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDocumentDetails is a Querydsl query type for DocumentDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDocumentDetails extends EntityPathBase<DocumentDetails> {

    private static final long serialVersionUID = -1303330947L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDocumentDetails documentDetails = new QDocumentDetails("documentDetails");

    public final StringPath contentType = createString("contentType");

    public final ArrayPath<byte[], Byte> document = createArray("document", byte[].class);

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final QDocumentKey key;

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final StringPath verificationRemark = createString("verificationRemark");

    public final NumberPath<Integer> verificationStatus = createNumber("verificationStatus", Integer.class);

    public QDocumentDetails(String variable) {
        this(DocumentDetails.class, forVariable(variable), INITS);
    }

    public QDocumentDetails(Path<? extends DocumentDetails> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDocumentDetails(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDocumentDetails(PathMetadata<?> metadata, PathInits inits) {
        this(DocumentDetails.class, metadata, inits);
    }

    public QDocumentDetails(Class<? extends DocumentDetails> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.key = inits.isInitialized("key") ? new QDocumentKey(forProperty("key")) : null;
    }

}


package com.projectx.data.domain.completeregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDocumentKey is a Querydsl query type for DocumentKey
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QDocumentKey extends BeanPath<DocumentKey> {

    private static final long serialVersionUID = -1899020006L;

    public static final QDocumentKey documentKey = new QDocumentKey("documentKey");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final NumberPath<Integer> customerType = createNumber("customerType", Integer.class);

    public final StringPath documentName = createString("documentName");

    public QDocumentKey(String variable) {
        super(DocumentKey.class, forVariable(variable));
    }

    public QDocumentKey(Path<? extends DocumentKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDocumentKey(PathMetadata<?> metadata) {
        super(DocumentKey.class, metadata);
    }

}


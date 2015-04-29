package com.projectx.data.domain.quickregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEmailVerificationKey is a Querydsl query type for EmailVerificationKey
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QEmailVerificationKey extends BeanPath<EmailVerificationKey> {

    private static final long serialVersionUID = 1227964110L;

    public static final QEmailVerificationKey emailVerificationKey = new QEmailVerificationKey("emailVerificationKey");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final NumberPath<Integer> customerType = createNumber("customerType", Integer.class);

    public final NumberPath<Integer> emailType = createNumber("emailType", Integer.class);

    public QEmailVerificationKey(String variable) {
        super(EmailVerificationKey.class, forVariable(variable));
    }

    public QEmailVerificationKey(Path<? extends EmailVerificationKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailVerificationKey(PathMetadata<?> metadata) {
        super(EmailVerificationKey.class, metadata);
    }

}


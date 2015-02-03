package com.projectx.data.domain.quickregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAuthenticationDetailsKey is a Querydsl query type for AuthenticationDetailsKey
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QAuthenticationDetailsKey extends BeanPath<AuthenticationDetailsKey> {

    private static final long serialVersionUID = -955526629L;

    public static final QAuthenticationDetailsKey authenticationDetailsKey = new QAuthenticationDetailsKey("authenticationDetailsKey");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final NumberPath<Integer> customerType = createNumber("customerType", Integer.class);

    public QAuthenticationDetailsKey(String variable) {
        super(AuthenticationDetailsKey.class, forVariable(variable));
    }

    public QAuthenticationDetailsKey(Path<? extends AuthenticationDetailsKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthenticationDetailsKey(PathMetadata<?> metadata) {
        super(AuthenticationDetailsKey.class, metadata);
    }

}


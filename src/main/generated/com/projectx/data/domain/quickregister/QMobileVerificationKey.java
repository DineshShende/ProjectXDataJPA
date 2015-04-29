package com.projectx.data.domain.quickregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMobileVerificationKey is a Querydsl query type for MobileVerificationKey
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QMobileVerificationKey extends BeanPath<MobileVerificationKey> {

    private static final long serialVersionUID = 2104243772L;

    public static final QMobileVerificationKey mobileVerificationKey = new QMobileVerificationKey("mobileVerificationKey");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final NumberPath<Integer> customerType = createNumber("customerType", Integer.class);

    public final NumberPath<Integer> mobileType = createNumber("mobileType", Integer.class);

    public QMobileVerificationKey(String variable) {
        super(MobileVerificationKey.class, forVariable(variable));
    }

    public QMobileVerificationKey(Path<? extends MobileVerificationKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMobileVerificationKey(PathMetadata<?> metadata) {
        super(MobileVerificationKey.class, metadata);
    }

}


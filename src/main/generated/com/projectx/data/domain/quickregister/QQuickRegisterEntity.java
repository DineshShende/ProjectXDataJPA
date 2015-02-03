package com.projectx.data.domain.quickregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QQuickRegisterEntity is a Querydsl query type for QuickRegisterEntity
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuickRegisterEntity extends EntityPathBase<QuickRegisterEntity> {

    private static final long serialVersionUID = -176517331L;

    public static final QQuickRegisterEntity quickRegisterEntity = new QQuickRegisterEntity("quickRegisterEntity");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final NumberPath<Integer> customerType = createNumber("customerType", Integer.class);

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final BooleanPath isEmailVerified = createBoolean("isEmailVerified");

    public final BooleanPath isMobileVerified = createBoolean("isMobileVerified");

    public final StringPath lastName = createString("lastName");

    public final NumberPath<Long> mobile = createNumber("mobile", Long.class);

    public final NumberPath<Integer> pincode = createNumber("pincode", Integer.class);

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QQuickRegisterEntity(String variable) {
        super(QuickRegisterEntity.class, forVariable(variable));
    }

    public QQuickRegisterEntity(Path<? extends QuickRegisterEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuickRegisterEntity(PathMetadata<?> metadata) {
        super(QuickRegisterEntity.class, metadata);
    }

}


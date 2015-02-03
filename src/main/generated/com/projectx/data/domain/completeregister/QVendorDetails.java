package com.projectx.data.domain.completeregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVendorDetails is a Querydsl query type for VendorDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVendorDetails extends EntityPathBase<VendorDetails> {

    private static final long serialVersionUID = 1729498160L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVendorDetails vendorDetails = new QVendorDetails("vendorDetails");

    public final DateTimePath<java.util.Date> dateOfBirth = createDateTime("dateOfBirth", java.util.Date.class);

    public final StringPath email = createString("email");

    public final QAddress firmAddress;

    public final StringPath firstName = createString("firstName");

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final BooleanPath isEmailVerified = createBoolean("isEmailVerified");

    public final BooleanPath isMobileVerified = createBoolean("isMobileVerified");

    public final StringPath laguage = createString("laguage");

    public final StringPath lastName = createString("lastName");

    public final NumberPath<Long> mobile = createNumber("mobile", Long.class);

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final NumberPath<Long> vendorId = createNumber("vendorId", Long.class);

    public QVendorDetails(String variable) {
        this(VendorDetails.class, forVariable(variable), INITS);
    }

    public QVendorDetails(Path<? extends VendorDetails> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVendorDetails(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVendorDetails(PathMetadata<?> metadata, PathInits inits) {
        this(VendorDetails.class, metadata, inits);
    }

    public QVendorDetails(Class<? extends VendorDetails> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.firmAddress = inits.isInitialized("firmAddress") ? new QAddress(forProperty("firmAddress")) : null;
    }

}


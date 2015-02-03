package com.projectx.data.domain.completeregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDriverDetails is a Querydsl query type for DriverDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDriverDetails extends EntityPathBase<DriverDetails> {

    private static final long serialVersionUID = 2146114896L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDriverDetails driverDetails = new QDriverDetails("driverDetails");

    public final StringPath bloodGroup = createString("bloodGroup");

    public final DateTimePath<java.util.Date> dateOfBirth = createDateTime("dateOfBirth", java.util.Date.class);

    public final NumberPath<Long> driverId = createNumber("driverId", Long.class);

    public final DateTimePath<java.util.Date> drivingSince = createDateTime("drivingSince", java.util.Date.class);

    public final DateTimePath<java.util.Date> employedSince = createDateTime("employedSince", java.util.Date.class);

    public final StringPath firstName = createString("firstName");

    public final QAddress homeAddress;

    public final NumberPath<Long> homeContactNumber = createNumber("homeContactNumber", Long.class);

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final BooleanPath isDealFinalizationPermissionGiven = createBoolean("isDealFinalizationPermissionGiven");

    public final BooleanPath isFreightRequestPermissionGiven = createBoolean("isFreightRequestPermissionGiven");

    public final BooleanPath isMobileVerified = createBoolean("isMobileVerified");

    public final StringPath language = createString("language");

    public final StringPath lastName = createString("lastName");

    public final StringPath licenceNumber = createString("licenceNumber");

    public final StringPath middleName = createString("middleName");

    public final NumberPath<Long> mobile = createNumber("mobile", Long.class);

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final NumberPath<Long> vendorId = createNumber("vendorId", Long.class);

    public QDriverDetails(String variable) {
        this(DriverDetails.class, forVariable(variable), INITS);
    }

    public QDriverDetails(Path<? extends DriverDetails> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDriverDetails(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDriverDetails(PathMetadata<?> metadata, PathInits inits) {
        this(DriverDetails.class, metadata, inits);
    }

    public QDriverDetails(Class<? extends DriverDetails> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.homeAddress = inits.isInitialized("homeAddress") ? new QAddress(forProperty("homeAddress")) : null;
    }

}


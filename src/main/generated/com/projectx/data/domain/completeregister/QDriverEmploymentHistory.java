package com.projectx.data.domain.completeregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDriverEmploymentHistory is a Querydsl query type for DriverEmploymentHistory
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDriverEmploymentHistory extends EntityPathBase<DriverEmploymentHistory> {

    private static final long serialVersionUID = 819136662L;

    public static final QDriverEmploymentHistory driverEmploymentHistory = new QDriverEmploymentHistory("driverEmploymentHistory");

    public final NumberPath<Long> addressId = createNumber("addressId", Long.class);

    public final StringPath addressLine = createString("addressLine");

    public final StringPath bloodGroup = createString("bloodGroup");

    public final StringPath city = createString("city");

    public final DateTimePath<java.util.Date> dateOfBirth = createDateTime("dateOfBirth", java.util.Date.class);

    public final StringPath district = createString("district");

    public final NumberPath<Long> driverId = createNumber("driverId", Long.class);

    public final DateTimePath<java.util.Date> drivingSince = createDateTime("drivingSince", java.util.Date.class);

    public final DateTimePath<java.util.Date> employedSince = createDateTime("employedSince", java.util.Date.class);

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> homeContactNumber = createNumber("homeContactNumber", Long.class);

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final BooleanPath isDealFinalizationPermissionGiven = createBoolean("isDealFinalizationPermissionGiven");

    public final BooleanPath isFreightRequestPermissionGiven = createBoolean("isFreightRequestPermissionGiven");

    public final StringPath language = createString("language");

    public final StringPath lastName = createString("lastName");

    public final StringPath licenceNumber = createString("licenceNumber");

    public final StringPath middleName = createString("middleName");

    public final NumberPath<Long> mobile = createNumber("mobile", Long.class);

    public final NumberPath<Integer> pincode = createNumber("pincode", Integer.class);

    public final NumberPath<Long> recordId = createNumber("recordId", Long.class);

    public final StringPath state = createString("state");

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final NumberPath<Long> vendorId = createNumber("vendorId", Long.class);

    public QDriverEmploymentHistory(String variable) {
        super(DriverEmploymentHistory.class, forVariable(variable));
    }

    public QDriverEmploymentHistory(Path<? extends DriverEmploymentHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDriverEmploymentHistory(PathMetadata<?> metadata) {
        super(DriverEmploymentHistory.class, metadata);
    }

}


package com.projectx.data.domain.request;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QFreightRequestByVendor is a Querydsl query type for FreightRequestByVendor
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFreightRequestByVendor extends EntityPathBase<FreightRequestByVendor> {

    private static final long serialVersionUID = 671582418L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFreightRequestByVendor freightRequestByVendor = new QFreightRequestByVendor("freightRequestByVendor");

    public final DateTimePath<java.util.Date> availableDate = createDateTime("availableDate", java.util.Date.class);

    public final StringPath availableTime = createString("availableTime");

    public final NumberPath<Integer> destination = createNumber("destination", Integer.class);

    public final NumberPath<Long> driverId = createNumber("driverId", Long.class);

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final NumberPath<Integer> pickupRangeInKm = createNumber("pickupRangeInKm", Integer.class);

    public final NumberPath<Long> requestId = createNumber("requestId", Long.class);

    public final NumberPath<Integer> source = createNumber("source", Integer.class);

    public final StringPath status = createString("status");

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final com.projectx.data.domain.completeregister.QVehicleDetailsDTO vehicleDetailsId;

    public final NumberPath<Long> vendorId = createNumber("vendorId", Long.class);

    public QFreightRequestByVendor(String variable) {
        this(FreightRequestByVendor.class, forVariable(variable), INITS);
    }

    public QFreightRequestByVendor(Path<? extends FreightRequestByVendor> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFreightRequestByVendor(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFreightRequestByVendor(PathMetadata<?> metadata, PathInits inits) {
        this(FreightRequestByVendor.class, metadata, inits);
    }

    public QFreightRequestByVendor(Class<? extends FreightRequestByVendor> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.vehicleDetailsId = inits.isInitialized("vehicleDetailsId") ? new com.projectx.data.domain.completeregister.QVehicleDetailsDTO(forProperty("vehicleDetailsId"), inits.get("vehicleDetailsId")) : null;
    }

}


package com.projectx.data.domain.request;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTestRequest is a Querydsl query type for TestRequest
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTestRequest extends EntityPathBase<TestRequest> {

    private static final long serialVersionUID = 278801814L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTestRequest testRequest = new QTestRequest("testRequest");

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

    public QTestRequest(String variable) {
        this(TestRequest.class, forVariable(variable), INITS);
    }

    public QTestRequest(Path<? extends TestRequest> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTestRequest(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTestRequest(PathMetadata<?> metadata, PathInits inits) {
        this(TestRequest.class, metadata, inits);
    }

    public QTestRequest(Class<? extends TestRequest> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.vehicleDetailsId = inits.isInitialized("vehicleDetailsId") ? new com.projectx.data.domain.completeregister.QVehicleDetailsDTO(forProperty("vehicleDetailsId"), inits.get("vehicleDetailsId")) : null;
    }

}


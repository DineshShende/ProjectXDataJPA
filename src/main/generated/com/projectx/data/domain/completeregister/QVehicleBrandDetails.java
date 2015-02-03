package com.projectx.data.domain.completeregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVehicleBrandDetails is a Querydsl query type for VehicleBrandDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVehicleBrandDetails extends EntityPathBase<VehicleBrandDetails> {

    private static final long serialVersionUID = 564992477L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVehicleBrandDetails vehicleBrandDetails = new QVehicleBrandDetails("vehicleBrandDetails");

    public final StringPath modelNumber = createString("modelNumber");

    public final NumberPath<Long> vehicleBrandId = createNumber("vehicleBrandId", Long.class);

    public final StringPath vehicleBrandName = createString("vehicleBrandName");

    public final QVehicleTypeDetails vehicleTypeId;

    public QVehicleBrandDetails(String variable) {
        this(VehicleBrandDetails.class, forVariable(variable), INITS);
    }

    public QVehicleBrandDetails(Path<? extends VehicleBrandDetails> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVehicleBrandDetails(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVehicleBrandDetails(PathMetadata<?> metadata, PathInits inits) {
        this(VehicleBrandDetails.class, metadata, inits);
    }

    public QVehicleBrandDetails(Class<? extends VehicleBrandDetails> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.vehicleTypeId = inits.isInitialized("vehicleTypeId") ? new QVehicleTypeDetails(forProperty("vehicleTypeId")) : null;
    }

}


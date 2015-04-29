package com.projectx.data.domain.completeregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QVehicleTypeDetails is a Querydsl query type for VehicleTypeDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVehicleTypeDetails extends EntityPathBase<VehicleTypeDetails> {

    private static final long serialVersionUID = 1215760454L;

    public static final QVehicleTypeDetails vehicleTypeDetails = new QVehicleTypeDetails("vehicleTypeDetails");

    public final NumberPath<Long> vehicleTypeId = createNumber("vehicleTypeId", Long.class);

    public final StringPath vehicleTypeName = createString("vehicleTypeName");

    public QVehicleTypeDetails(String variable) {
        super(VehicleTypeDetails.class, forVariable(variable));
    }

    public QVehicleTypeDetails(Path<? extends VehicleTypeDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVehicleTypeDetails(PathMetadata<?> metadata) {
        super(VehicleTypeDetails.class, metadata);
    }

}


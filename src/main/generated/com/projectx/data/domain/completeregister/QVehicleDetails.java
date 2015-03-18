package com.projectx.data.domain.completeregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVehicleDetails is a Querydsl query type for VehicleDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVehicleDetails extends EntityPathBase<VehicleDetails> {

    private static final long serialVersionUID = -1028279328L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVehicleDetails vehicleDetails = new QVehicleDetails("vehicleDetails");

    public final StringPath chassisNumber = createString("chassisNumber");

    public final ListPath<String, StringPath> commodityList = this.<String, StringPath>createList("commodityList", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final StringPath insuranceCompany = createString("insuranceCompany");

    public final StringPath insuranceNumber = createString("insuranceNumber");

    public final BooleanPath insuranceStatus = createBoolean("insuranceStatus");

    public final BooleanPath isBodyTypeFlexible = createBoolean("isBodyTypeFlexible");

    public final NumberPath<Integer> length = createNumber("length", Integer.class);

    public final NumberPath<Integer> loadCapacityInTons = createNumber("loadCapacityInTons", Integer.class);

    public final NumberPath<Integer> numberOfWheels = createNumber("numberOfWheels", Integer.class);

    public final StringPath ownerFirstName = createString("ownerFirstName");

    public final StringPath ownerLastName = createString("ownerLastName");

    public final StringPath ownerMiddleName = createString("ownerMiddleName");

    public final StringPath permitType = createString("permitType");

    public final StringPath registrationNumber = createString("registrationNumber");

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final StringPath vehicleBodyType = createString("vehicleBodyType");

    public final QVehicleBrandDetails vehicleBrandId;

    public final NumberPath<Long> vehicleId = createNumber("vehicleId", Long.class);

    public final QVehicleTypeDetails vehicleTypeId;

    public final NumberPath<Long> vendorId = createNumber("vendorId", Long.class);

    public final NumberPath<Integer> width = createNumber("width", Integer.class);

    public QVehicleDetails(String variable) {
        this(VehicleDetails.class, forVariable(variable), INITS);
    }

    public QVehicleDetails(Path<? extends VehicleDetails> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVehicleDetails(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVehicleDetails(PathMetadata<?> metadata, PathInits inits) {
        this(VehicleDetails.class, metadata, inits);
    }

    public QVehicleDetails(Class<? extends VehicleDetails> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.vehicleBrandId = inits.isInitialized("vehicleBrandId") ? new QVehicleBrandDetails(forProperty("vehicleBrandId"), inits.get("vehicleBrandId")) : null;
        this.vehicleTypeId = inits.isInitialized("vehicleTypeId") ? new QVehicleTypeDetails(forProperty("vehicleTypeId")) : null;
    }

}


package com.projectx.data.domain.request;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFreightRequestByCustomer is a Querydsl query type for FreightRequestByCustomer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFreightRequestByCustomer extends EntityPathBase<FreightRequestByCustomer> {

    private static final long serialVersionUID = -429938456L;

    public static final QFreightRequestByCustomer freightRequestByCustomer = new QFreightRequestByCustomer("freightRequestByCustomer");

    public final NumberPath<Long> allocatedFor = createNumber("allocatedFor", Long.class);

    public final StringPath allocationStatus = createString("allocationStatus");

    public final StringPath bodyType = createString("bodyType");

    public final NumberPath<Integer> capacity = createNumber("capacity", Integer.class);

    public final StringPath commodity = createString("commodity");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final NumberPath<Integer> destination = createNumber("destination", Integer.class);

    public final NumberPath<Integer> grossWeight = createNumber("grossWeight", Integer.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Integer> insertedBy = createNumber("insertedBy", Integer.class);

    public final NumberPath<Long> insertedById = createNumber("insertedById", Long.class);

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final BooleanPath isFullTruckLoad = createBoolean("isFullTruckLoad");

    public final BooleanPath isLessThanTruckLoad = createBoolean("isLessThanTruckLoad");

    public final NumberPath<Integer> length = createNumber("length", Integer.class);

    public final StringPath model = createString("model");

    public final NumberPath<Integer> noOfVehicles = createNumber("noOfVehicles", Integer.class);

    public final DateTimePath<java.util.Date> pickupDate = createDateTime("pickupDate", java.util.Date.class);

    public final StringPath pickupTime = createString("pickupTime");

    public final NumberPath<Long> requestId = createNumber("requestId", Long.class);

    public final NumberPath<Integer> source = createNumber("source", Integer.class);

    public final NumberPath<Integer> updatedBy = createNumber("updatedBy", Integer.class);

    public final NumberPath<Long> updatedById = createNumber("updatedById", Long.class);

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public final StringPath vehicleBrand = createString("vehicleBrand");

    public final NumberPath<Integer> width = createNumber("width", Integer.class);

    public QFreightRequestByCustomer(String variable) {
        super(FreightRequestByCustomer.class, forVariable(variable));
    }

    public QFreightRequestByCustomer(Path<? extends FreightRequestByCustomer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFreightRequestByCustomer(PathMetadata<?> metadata) {
        super(FreightRequestByCustomer.class, metadata);
    }

}


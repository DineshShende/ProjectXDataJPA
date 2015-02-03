package com.projectx.data.domain.completeregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = 62667978L;

    public static final QAddress address = new QAddress("address");

    public final NumberPath<Long> addressId = createNumber("addressId", Long.class);

    public final StringPath addressLine = createString("addressLine");

    public final StringPath city = createString("city");

    public final NumberPath<Integer> customerType = createNumber("customerType", Integer.class);

    public final StringPath district = createString("district");

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final NumberPath<Integer> pincode = createNumber("pincode", Integer.class);

    public final StringPath state = createString("state");

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QAddress(String variable) {
        super(Address.class, forVariable(variable));
    }

    public QAddress(Path<? extends Address> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddress(PathMetadata<?> metadata) {
        super(Address.class, metadata);
    }

}


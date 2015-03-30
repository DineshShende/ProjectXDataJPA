package com.projectx.data.domain.handshake;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDealDetails is a Querydsl query type for DealDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDealDetails extends EntityPathBase<DealDetails> {

    private static final long serialVersionUID = 1453136791L;

    public static final QDealDetails dealDetails = new QDealDetails("dealDetails");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final NumberPath<Long> dealId = createNumber("dealId", Long.class);

    public final StringPath deductionMode = createString("deductionMode");

    public final NumberPath<Long> freightRequestByCustomerId = createNumber("freightRequestByCustomerId", Long.class);

    public final NumberPath<Long> freightRequestByVendorId = createNumber("freightRequestByVendorId", Long.class);

    public final StringPath insertedBy = createString("insertedBy");

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QDealDetails(String variable) {
        super(DealDetails.class, forVariable(variable));
    }

    public QDealDetails(Path<? extends DealDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDealDetails(PathMetadata<?> metadata) {
        super(DealDetails.class, metadata);
    }

}


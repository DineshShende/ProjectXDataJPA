package com.projectx.data.domain.completeregister;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCustomerDetails is a Querydsl query type for CustomerDetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCustomerDetails extends EntityPathBase<CustomerDetails> {

    private static final long serialVersionUID = 1242660698L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerDetails customerDetails = new QCustomerDetails("customerDetails");

    public final StringPath businessDomain = createString("businessDomain");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final DateTimePath<java.util.Date> dateOfBirth = createDateTime("dateOfBirth", java.util.Date.class);

    public final StringPath email = createString("email");

    public final QAddress firmAddressId;

    public final StringPath firstName = createString("firstName");

    public final QAddress homeAddressId;

    public final DateTimePath<java.util.Date> insertTime = createDateTime("insertTime", java.util.Date.class);

    public final BooleanPath isEmailVerified = createBoolean("isEmailVerified");

    public final BooleanPath isMobileVerified = createBoolean("isMobileVerified");

    public final BooleanPath isSecondaryMobileVerified = createBoolean("isSecondaryMobileVerified");

    public final StringPath language = createString("language");

    public final StringPath lastName = createString("lastName");

    public final StringPath middleName = createString("middleName");

    public final NumberPath<Long> mobile = createNumber("mobile", Long.class);

    public final StringPath nameOfFirm = createString("nameOfFirm");

    public final NumberPath<Long> phoneNumber = createNumber("phoneNumber", Long.class);

    public final StringPath secondaryEmail = createString("secondaryEmail");

    public final NumberPath<Long> secondaryMobile = createNumber("secondaryMobile", Long.class);

    public final StringPath updatedBy = createString("updatedBy");

    public final DateTimePath<java.util.Date> updateTime = createDateTime("updateTime", java.util.Date.class);

    public QCustomerDetails(String variable) {
        this(CustomerDetails.class, forVariable(variable), INITS);
    }

    public QCustomerDetails(Path<? extends CustomerDetails> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerDetails(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QCustomerDetails(PathMetadata<?> metadata, PathInits inits) {
        this(CustomerDetails.class, metadata, inits);
    }

    public QCustomerDetails(Class<? extends CustomerDetails> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.firmAddressId = inits.isInitialized("firmAddressId") ? new QAddress(forProperty("firmAddressId")) : null;
        this.homeAddressId = inits.isInitialized("homeAddressId") ? new QAddress(forProperty("homeAddressId")) : null;
    }

}


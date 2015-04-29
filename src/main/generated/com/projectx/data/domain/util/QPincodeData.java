package com.projectx.data.domain.util;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPincodeData is a Querydsl query type for PincodeData
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPincodeData extends EntityPathBase<PincodeData> {

    private static final long serialVersionUID = 1966997960L;

    public static final QPincodeData pincodeData = new QPincodeData("pincodeData");

    public final StringPath district = createString("district");

    public final StringPath officeName = createString("officeName");

    public final NumberPath<Integer> pincode = createNumber("pincode", Integer.class);

    public final StringPath state = createString("state");

    public final StringPath Taluk = createString("Taluk");

    public QPincodeData(String variable) {
        super(PincodeData.class, forVariable(variable));
    }

    public QPincodeData(Path<? extends PincodeData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPincodeData(PathMetadata<?> metadata) {
        super(PincodeData.class, metadata);
    }

}


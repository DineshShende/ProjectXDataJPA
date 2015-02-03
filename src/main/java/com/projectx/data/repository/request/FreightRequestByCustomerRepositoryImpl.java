package com.projectx.data.repository.request;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Join;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.projectx.data.domain.completeregister.QVehicleBrandDetails;
import com.projectx.data.domain.completeregister.QVehicleDetailsDTO;
import com.projectx.data.domain.completeregister.VehicleDetailsDTO;
import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.data.domain.request.QFreightRequestByCustomer;
import com.projectx.data.domain.request.QFreightRequestByVendor;

public class FreightRequestByCustomerRepositoryImpl extends
		QueryDslRepositorySupport implements
		FreightRequestByCustomerCustomRepository {
	
	

	public FreightRequestByCustomerRepositoryImpl() {
		super(FreightRequestByCustomer.class);

	}

	EntityManager entityManager=getEntityManager();
	

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return super.getEntityManager();
	}




	@Override
	public List<FreightRequestByCustomer> getMatchingCustomerRequest(FreightRequestByVendor freightRequestByVendor) {

		QFreightRequestByCustomer qFreightRequestByCustomer=QFreightRequestByCustomer.freightRequestByCustomer;
		
		QFreightRequestByVendor qFreightRequestByVendor=QFreightRequestByVendor.freightRequestByVendor;
		
		QVehicleDetailsDTO qVehicleDetailsDTO=QVehicleDetailsDTO.vehicleDetailsDTO;
		
		QVehicleBrandDetails qVehicleBrandDetails=QVehicleBrandDetails.vehicleBrandDetails;
		
		Predicate sourcePredicate=qFreightRequestByCustomer.source.eq(freightRequestByVendor.getSource());
		
		
		
		VehicleDetailsDTO count=		
		from(qVehicleDetailsDTO).list(qVehicleDetailsDTO).get(0);		
		
		
		System.out.println(count);
		
		///list.add(new FreightRequestByCustomer());
		
		return null;
	}

}

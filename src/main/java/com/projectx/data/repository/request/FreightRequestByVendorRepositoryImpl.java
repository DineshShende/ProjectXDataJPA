package com.projectx.data.repository.request;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.Visitor;
import com.mysema.query.types.expr.BooleanExpression;
import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.data.domain.request.QFreightRequestByVendor;

public class FreightRequestByVendorRepositoryImpl extends QueryDslRepositorySupport
		implements FreightRequestByVendorCustomRepository {

	public FreightRequestByVendorRepositoryImpl() {
		super(FreightRequestByVendor.class);

	}

	@Override
	public List<FreightRequestByVendor> getMatchingVendorRequest(
			FreightRequestByCustomer freightRequestByCustomer) {

		QFreightRequestByVendor qTestRequest=QFreightRequestByVendor.freightRequestByVendor;
		
		Predicate sourcePredicate=qTestRequest.source.eq(freightRequestByCustomer.getSource());
		
		Predicate destinationPredicate=qTestRequest.destination.eq(freightRequestByCustomer.getDestination());
		
		Predicate availableDatePredicate=qTestRequest.availableDate.loe(freightRequestByCustomer.getPickupDate());
				
				//before(freightRequestByCustomer.getPickupDate()).or(qTestRequest.availableDate.eq(freightRequestByCustomer.getPickupDate()));
		
		
		Predicate bodyType=qTestRequest.vehicleDetailsId.isBodyTypeFlexible.eq(true).
				or(qTestRequest.vehicleDetailsId.vehicleBodyType.eq(freightRequestByCustomer.getBodyType()));

		
		JPQLQuery jpaQuery=from(qTestRequest).where(sourcePredicate)
				  .where(destinationPredicate)
				  .where(availableDatePredicate)
				  .where(bodyType);

		
		
		if(freightRequestByCustomer.getIsFullTruckLoad())
		{	
			Predicate fullTruckLoadPredicate=qTestRequest.vehicleDetailsId.loadCapacityInTons.goe(freightRequestByCustomer.getCapacity());
		
			jpaQuery=jpaQuery.where(fullTruckLoadPredicate);
		}	

		
		if(freightRequestByCustomer.getIsLessThanTruckLoad())
		{
		
		
			Predicate lessThanTruckLoadPredicate=qTestRequest.vehicleDetailsId.loadCapacityInTons.goe(freightRequestByCustomer.getGrossWeight())
					.and(qTestRequest.vehicleDetailsId.length.goe(freightRequestByCustomer.getLength()))
					.and(qTestRequest.vehicleDetailsId.width.goe(freightRequestByCustomer.getWidth()))
					.and(qTestRequest.vehicleDetailsId.height.goe(freightRequestByCustomer.getHeight()));
			
			jpaQuery=jpaQuery.where(lessThanTruckLoadPredicate);
		}
				
		
		if(freightRequestByCustomer.getVehicleBrand()!=null && !freightRequestByCustomer.getVehicleBrand().equals(""))
		{
			Predicate vehicleBrandPredicate=qTestRequest.vehicleDetailsId.vehicleBrandId.vehicleBrandName.eq(freightRequestByCustomer.getVehicleBrand());
			
			jpaQuery=jpaQuery.where(vehicleBrandPredicate);
		}
		
		
		if(freightRequestByCustomer.getModel()!=null && !freightRequestByCustomer.getModel().equals(""))
		{
			Predicate modelPredicate=qTestRequest.vehicleDetailsId.vehicleBrandId.modelNumber.eq(freightRequestByCustomer.getModel());
			
			jpaQuery=jpaQuery.where(modelPredicate);
		}
		
		//TODO pickupTime
		
		
		
		return jpaQuery.list(qTestRequest);
	}

	

}

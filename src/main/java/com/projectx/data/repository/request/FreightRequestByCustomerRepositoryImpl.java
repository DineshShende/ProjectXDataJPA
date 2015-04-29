package com.projectx.data.repository.request;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.Predicate;
import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.data.domain.request.QFreightRequestByCustomer;

public class FreightRequestByCustomerRepositoryImpl extends
		QueryDslRepositorySupport implements
		FreightRequestByCustomerCustomRepository {
	
	

	public FreightRequestByCustomerRepositoryImpl() {
		super(FreightRequestByCustomer.class);

	}

	@Override
	public List<FreightRequestByCustomer> getMatchingCustomerRequest(FreightRequestByVendor freightRequestByVendor,String allocationStatus) {

		QFreightRequestByCustomer qFreightRequestByCustomer=QFreightRequestByCustomer.freightRequestByCustomer;
		
		Predicate sourcePredicate=qFreightRequestByCustomer.source.eq(freightRequestByVendor.getSource());
		
		Predicate destinationPredicate=qFreightRequestByCustomer.destination.eq(freightRequestByVendor.getDestination());
		
		//TODO need discussion
		Predicate availableDatePredicate=qFreightRequestByCustomer.pickupDate.goe(freightRequestByVendor.getAvailableDate());
		
		//Predicate startDatePredicate=qFreightRequestByCustomer.insertTime.before(freightRequestByVendor.getAvailableDate());
		
		Predicate allocationStatusPredicate=qFreightRequestByCustomer.allocationStatus.eq(allocationStatus);
		
		Predicate commodityPredicate=qFreightRequestByCustomer.commodity.isNull().or(qFreightRequestByCustomer.commodity.isNotNull().and(qFreightRequestByCustomer.commodity.isNotEmpty())
				.and(qFreightRequestByCustomer.commodity.in(freightRequestByVendor.getVehicleDetailsId().getCommodityList())));
				
				
		
		
		JPQLQuery jpqlQuery=from(qFreightRequestByCustomer)
							.where(sourcePredicate)
							.where(destinationPredicate)
							.where(availableDatePredicate)
							.where(allocationStatusPredicate)
							.where(commodityPredicate);
		
		
		
		Predicate fullTruckLoadPredicate=qFreightRequestByCustomer.isFullTruckLoad.eq(true)
							.and(qFreightRequestByCustomer.capacity.loe(freightRequestByVendor.getVehicleDetailsId().getLoadCapacityInTons()));
		
		
		Predicate lessThanTruckLoadPredicateORfullTruckLoadPredicate
							=qFreightRequestByCustomer.isLessThanTruckLoad.eq(true)
								.and(qFreightRequestByCustomer.grossWeight.loe(freightRequestByVendor.getVehicleDetailsId().getLoadCapacityInTons()))
								.and(qFreightRequestByCustomer.length.loe(freightRequestByVendor.getVehicleDetailsId().getLength()))
								.and(qFreightRequestByCustomer.width.loe(freightRequestByVendor.getVehicleDetailsId().getWidth()))
								.and(qFreightRequestByCustomer.height.loe(freightRequestByVendor.getVehicleDetailsId().getHeight()))
								.or(fullTruckLoadPredicate);
		
		
		jpqlQuery=jpqlQuery.where(lessThanTruckLoadPredicateORfullTruckLoadPredicate);
		
		
		
		if(!freightRequestByVendor.getVehicleDetailsId().getIsBodyTypeFlexible())
		{
			Predicate bodyTypePredicate=qFreightRequestByCustomer.bodyType.isNull().or(qFreightRequestByCustomer.bodyType.eq(freightRequestByVendor.getVehicleDetailsId().getVehicleBodyType()));
		
			jpqlQuery=jpqlQuery.where(bodyTypePredicate);
		}	
		
		
		Predicate vehicleBrandPredicate=qFreightRequestByCustomer.vehicleBrand.notLike("")
										.and(qFreightRequestByCustomer.vehicleBrand.eq(freightRequestByVendor.getVehicleDetailsId().getVehicleBrandId().getVehicleBrandName()))
										.or(qFreightRequestByCustomer.vehicleBrand.isNull());
		
		jpqlQuery=jpqlQuery.where(vehicleBrandPredicate);
		
		
		Predicate modelNumberPredicate=qFreightRequestByCustomer.model.notLike("")
				.and(qFreightRequestByCustomer.model.eq(freightRequestByVendor.getVehicleDetailsId().getVehicleBrandId().getModelNumber()))
				.or(qFreightRequestByCustomer.model.isNull());
		
		jpqlQuery=jpqlQuery.where(modelNumberPredicate);
		
		//TODO availableTime
		
		return jpqlQuery.list(qFreightRequestByCustomer);
	}

}

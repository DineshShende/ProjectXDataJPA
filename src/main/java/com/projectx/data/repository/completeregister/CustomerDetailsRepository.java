package com.projectx.data.repository.completeregister;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.CustomerDetails;

@Repository
public interface CustomerDetailsRepository extends CrudRepository<CustomerDetails, Long> {

	@Override
	CustomerDetails save(CustomerDetails customerDetails);
	
	//@Query(value="update customerdetails set ismobileverified=:ismobileVerified where customerid=:customerId",nativeQuery=true)
	//Integer updateIsMobileVerified(@Param("customerId")Long customerId,@Param("isMobileVerified")Boolean isMobileVerified);
	
}

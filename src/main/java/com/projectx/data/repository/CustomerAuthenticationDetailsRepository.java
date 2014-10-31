package com.projectx.data.repository;



import javax.transaction.Transactional;

import com.projectx.data.domain.CustomerAuthenticationDetails;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
@Profile(value={"Prod","Test"})
public interface CustomerAuthenticationDetailsRepository extends
		CrudRepository<CustomerAuthenticationDetails, Long> {

	@Override
	CustomerAuthenticationDetails save(CustomerAuthenticationDetails authenticationDetails);
	
	@Transactional
	@Modifying
	@Query(value="update customer_authentication_details set PASSWORD=:password,PASSWORDTYPE=:passwordType where CUSTOMERID=:customerId",nativeQuery = true)
	Integer updatePasswordAndPasswordType(@Param("customerId")Long customerId,@Param("password")String password,@Param("passwordType")String passwordType);
	
	@Query(value="select * from customer_authentication_details where (EMAIL=:email or MOBILE=:mobile) and PASSWORD=:password",nativeQuery = true)
	CustomerAuthenticationDetails loginVerification(@Param("email") String email,@Param("mobile") Long mobile,@Param("password") String password);
	
	@Transactional
	@Modifying
	@Query(value="truncate table customer_authentication_details",nativeQuery = true)
	void clearTestData();

	
}

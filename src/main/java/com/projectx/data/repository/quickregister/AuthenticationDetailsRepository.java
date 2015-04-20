package com.projectx.data.repository.quickregister;



import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import com.projectx.data.domain.quickregister.AuthenticationDetails;
import com.projectx.data.domain.quickregister.AuthenticationDetailsKey;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
@Profile(value={"Test","Prod","Dev"})
public interface AuthenticationDetailsRepository extends
		CrudRepository<AuthenticationDetails, AuthenticationDetailsKey> {

	@Override
	AuthenticationDetails save(AuthenticationDetails authenticationDetails);
	
	@Override
	public AuthenticationDetails findOne(AuthenticationDetailsKey id);
	
	Optional<AuthenticationDetails> findByEmail(String email);
	
	Optional<AuthenticationDetails> findByMobile(Long mobile);
	
	@Transactional
	@Modifying

	@Query(value="update authenticationdetails set PASSWORD=:password,EMAILPASSWORD=:emailPassword,PASSWORDTYPE=:passwordType,"
			+ "RESENDCOUNT=:resendCount,LASTUNSUCESSFULLATTEMPTS=:lastUnsucessfullAttempts,"
			+ "UPDATETIME=:updateDate ,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType" ,nativeQuery = true)
	Integer updatePasswordEmailPasswordAndPasswordTypeAndCounts(@Param("customerId")Long customerId,@Param("customerType")Integer customerType,@Param("password")String password,
			@Param("emailPassword")String emailPassword,@Param("passwordType")String passwordType,@Param("resendCount")Integer resendCount,@Param("lastUnsucessfullAttempts")Integer lastUnsucessfullAttempts,
			@Param("updateDate") Date updateDate,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);
	
		
	@Transactional
	@Modifying
	@Query(value="update authenticationdetails set RESENDCOUNT=RESENDCOUNT+1,"
			+ "UPDATETIME=:updateDate ,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType",nativeQuery=true)
	Integer incrementResendCount(@Param("customerId") Long customerId,@Param("customerType")Integer customerType,
			@Param("updateDate") Date updateDate,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);

	
	@Transactional
	@Modifying
	@Query(value="update authenticationdetails set LASTUNSUCESSFULLATTEMPTS=LASTUNSUCESSFULLATTEMPTS+1,"
			+ "UPDATETIME=:updateDate ,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById where CUSTOMERID=:customerId and CUSTOMERTYPE=:customerType",nativeQuery=true)
	Integer incrementLastUnsucessfullAttempts(@Param("customerId") Long customerId,@Param("customerType")Integer customerType,
			@Param("updateDate") Date updateDate,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);


	@Transactional
	@Modifying
	@Query(value="update authenticationdetails set EMAIL=:email,"
			+ "UPDATETIME=:updateDate ,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById where CUSTOMERID=:customerId and CUSTOMERTYPE=:entityType",nativeQuery=true)
	Integer updateEmail(@Param("customerId") Long customerId,@Param("entityType") Integer entityType,@Param("email")String email,
			@Param("updateDate") Date updateDate,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);

	@Transactional
	@Modifying
	@Query(value="update authenticationdetails set MOBILE=:mobile,"
			+ "UPDATETIME=:updateDate ,UPDATEDBY=:updatedBy,UPDATEDBYID=:updatedById where CUSTOMERID=:customerId and CUSTOMERTYPE=:entityType",nativeQuery=true)
	Integer updateMobile(@Param("customerId") Long customerId,@Param("entityType") Integer entityType,@Param("mobile")Long mobile,
			@Param("updateDate") Date updateDate,@Param("updatedBy") Integer updatedBy,@Param("updatedById") Long updatedById);


}

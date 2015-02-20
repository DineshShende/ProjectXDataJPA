package com.projectx.data.repository.quickregister;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.quickregister.QuickRegisterEntity;

@Repository
@Profile(value = { "Test", "Prod" })

public interface QuickRegisterRepository extends
		CrudRepository<QuickRegisterEntity,Long > {
	
		 @Override
		 QuickRegisterEntity save(QuickRegisterEntity  customer);
		 
		 @Override
		 List<QuickRegisterEntity> findAll();
	
		 Optional<QuickRegisterEntity> findByCustomerId(Long customerId);
		
		 Optional<QuickRegisterEntity> findByEmail(String email);
		 
		 Optional<QuickRegisterEntity> findByMobile(Long mobile);
		 

	 
		@Transactional
		 @Modifying
		 @Query(value="update quickregisterentity set ISMOBILEVERIFIED=:status,UPDATETIME=:updateTime,"
		 		+ "UPDATEDBY=:updatedBy where CUSTOMERID=:customerId",nativeQuery = true)
		 Integer updateMobileVerificationStatus(@Param("customerId")Long customerId, @Param("status")Boolean status,
				 @Param("updateTime")Date updateTime,@Param("updatedBy")String updatedBy);
		 
		@Transactional
		 @Modifying
		 @Query(value="update quickregisterentity set ISEMAILVERIFIED=:status,UPDATETIME=:updateTime,"
		 		+ "UPDATEDBY=:updatedBy where CUSTOMERID=:customerId",nativeQuery = true)
		 Integer updateEmailVerificationStatus(@Param("customerId")Long customerId,@Param("status")Boolean status,
				 @Param("updateTime")Date updateTime,@Param("updatedBy")String updatedBy);

	
		
}

package com.projectx.data.repository;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.CustomerQuickRegisterEntity;

@Repository
@Profile(value = { "Test", "Prod" })
public interface CustomerQuickRegisterRepository extends
		CrudRepository<CustomerQuickRegisterEntity,Long > {
	
		 CustomerQuickRegisterEntity findByEmail(String email);
		
		 CustomerQuickRegisterEntity findByMobile(Long mobile);
		 
		 @Override
		 List<CustomerQuickRegisterEntity> findAll();
		 
		 @Query(value="select status from customer_quick_register_entity where email=:email",nativeQuery = true)
		 String fetchStatusByEmail(@Param("email") String email);
		
		 
		 @Query(value="select status from customer_quick_register_entity where mobile=:mobile",nativeQuery = true)
		 String fetchStatusByMobile(@Param(value="mobile") Long mobile);
		 
		
		@Modifying
		@Query(value="update customer_quick_register_entity set status=:status where email=:email",nativeQuery = true)
		 Integer updateStatusByEmail(@Param("email") String email, @Param("status") String status);
		
		@Modifying
		@Query(value="update customer_quick_register_entity set status=:status where mobile=:mobile",nativeQuery = true)
		 Integer updateStatusByMobile(@Param("mobile") Long mobile, @Param("status") String status);
		
		

		 int countByEmail(String email);
		 
		 int countByMobile(Long mobile);
		
		// Long deleteByEmail(String email);
		 
		// Long deleteByMobile(Long mobile);
			
			
}

package com.projectx.data.repository;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
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
		 
		 int countByEmail(String email);
		 
		 int countByMobile(Long mobile);
		 
		 Long deleteByEmail(String email);
		 
		 Long deleteByMobile(Long mobile);
	

}

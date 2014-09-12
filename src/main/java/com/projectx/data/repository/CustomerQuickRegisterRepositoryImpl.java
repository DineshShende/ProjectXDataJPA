package com.projectx.data.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.CustomerQuickRegisterEntity;
import com.projectx.data.domain.CustomerQuickRegisterKey;

@Repository
@Profile(value = { "Test", "Prod" })
public interface CustomerQuickRegisterRepositoryImpl extends
		CrudRepository<CustomerQuickRegisterEntity,CustomerQuickRegisterKey > {

	
}

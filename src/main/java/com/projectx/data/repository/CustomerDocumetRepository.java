package com.projectx.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.projectx.data.domain.CustomerAuthenticationDetails;
import com.projectx.data.domain.CustomerDocumet;

public interface CustomerDocumetRepository extends
							CrudRepository<CustomerDocumet, Long>{

	
	
}

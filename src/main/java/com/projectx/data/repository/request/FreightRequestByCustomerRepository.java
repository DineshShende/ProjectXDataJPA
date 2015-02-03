package com.projectx.data.repository.request;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projectx.data.domain.request.FreightRequestByCustomer;

@Repository
public interface FreightRequestByCustomerRepository extends
		CrudRepository<FreightRequestByCustomer, Long>,FreightRequestByCustomerCustomRepository {
	
	List<FreightRequestByCustomer> findByCustomerId(Long customerId);

}

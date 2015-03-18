package com.projectx.data.repository.ivr;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.projectx.data.domain.ivr.PreBookEntity;

public interface PreBookRepository extends CrudRepository<PreBookEntity,Long > {
	
	PreBookEntity findByFreightRequestByCustomerId(Long freightRequestByCustomerId);
	
	@Transactional
	@Modifying
	@Query(value="delete from prebook where freightrequestbycustomerid=:freightRequestByCustomerId",nativeQuery=true)
	Integer deleteByfreightRequestByCustomerId(@Param("freightRequestByCustomerId") Long freightRequestByCustomerId);

}

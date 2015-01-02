package com.projectx.data.repository.completeregister;

import org.springframework.data.repository.CrudRepository;

import com.projectx.data.domain.completeregister.DocumentDetails;
import com.projectx.data.domain.completeregister.DocumentKey;
import com.projectx.data.domain.quickregister.AuthenticationDetails;

public interface DocumetDetailsRepository extends
							CrudRepository<DocumentDetails, DocumentKey>{

	
	
	
}

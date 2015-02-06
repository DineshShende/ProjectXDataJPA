package com.projectx.data.service.request;

import org.springframework.stereotype.Service;

import com.projectx.data.domain.request.TestRequest;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

@Service
public interface FreightRequestByVendorService {
	
	TestRequest toFreightRequestByVendor(FreightRequestByVendorDTO freightRequestByVendorDTO);
	
	FreightRequestByVendorDTO toFreightRequestByVendorDTO(TestRequest freightRequestByVendor);

}

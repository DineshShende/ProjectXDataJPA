package com.projectx.data.service.request;

import org.springframework.stereotype.Service;

import com.projectx.data.domain.request.FreightRequestByVendor;
import com.projectx.rest.domain.request.FreightRequestByVendorDTO;

@Service
public interface FreightRequestByVendorService {
	
	FreightRequestByVendor toFreightRequestByVendor(FreightRequestByVendorDTO freightRequestByVendorDTO);
	
	FreightRequestByVendorDTO toFreightRequestByVendorDTO(FreightRequestByVendor freightRequestByVendor);

}

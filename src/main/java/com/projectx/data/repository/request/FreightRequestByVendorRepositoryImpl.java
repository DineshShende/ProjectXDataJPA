package com.projectx.data.repository.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.projectx.data.domain.request.FreightRequestByCustomer;
import com.projectx.data.domain.request.FreightRequestByVendor;

public class FreightRequestByVendorRepositoryImpl extends
QueryDslRepositorySupport implements
FreightRequestByVendorCustomRepository{

	public FreightRequestByVendorRepositoryImpl() {
		super(FreightRequestByVendor.class);

	}

	@Override
	public List<FreightRequestByVendor> getMatchingVendorRequest(
			FreightRequestByCustomer freightRequestByCustomer) {
		
		List<FreightRequestByVendor> list=new ArrayList<FreightRequestByVendor>();
		list.add(new FreightRequestByVendor());
		
		return list;
	}

}

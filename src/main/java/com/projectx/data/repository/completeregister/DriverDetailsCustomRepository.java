package com.projectx.data.repository.completeregister;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.DriverDetails;
import com.projectx.data.domain.quickregister.EmailVerificationDetails;
import com.projectx.data.domain.quickregister.EmailVerificationKey;
import com.projectx.data.domain.quickregister.MobileVerificationDetails;
import com.projectx.data.domain.quickregister.MobileVerificationKey;
import com.projectx.data.repository.quickregister.MobileVerificationDetailsRepository;


@Component
public class DriverDetailsCustomRepository {

	@Autowired
	DriverDetailsRepository driverDetailsRepository;
	
	@Autowired
	MobileVerificationDetailsRepository mobileVerificationDetailsRepository;
	
	private Integer ENTITY_TYPE_DRIVER=3;
	private Integer ENTITY_TYPE_PRIMARY=1;
	private Integer ZERO_COUNT=0;
	
	private String UPDATED_BY_CUST_ONLINE="CUST_ONLINE";
	
	@Transactional(rollbackOn=DataIntegrityViolationException.class)
	public DriverDetails save (DriverDetails driverDetails)
	{
		DriverDetails savedEntity=driverDetailsRepository.save(driverDetails);
		
		if(driverDetails.getMobile()!=null)
		{	
			MobileVerificationDetails mobileVerificationDetails=
					new MobileVerificationDetails(new MobileVerificationKey(savedEntity.getDriverId(), ENTITY_TYPE_DRIVER, ENTITY_TYPE_PRIMARY),
							driverDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), UPDATED_BY_CUST_ONLINE);
			
			try
			{
			mobileVerificationDetailsRepository.save(mobileVerificationDetails);
			}
			catch(DataIntegrityViolationException e)
			{
				driverDetailsRepository.delete(savedEntity.getDriverId());
				
				return null;
			}
		
		}
		return savedEntity;
	}
	
	@Transactional
	public DriverDetails update(DriverDetails driverDetails)
	{
		DriverDetails oldEntity=driverDetailsRepository.findOne(driverDetails.getDriverId());
		
		if(oldEntity!=null)
		{
			if(driverDetails.getMobile()!=null && !driverDetails.getMobile().equals(oldEntity.getMobile()))
			{
				MobileVerificationDetails verificationDetails=
						mobileVerificationDetailsRepository.findOne(new MobileVerificationKey(driverDetails.getDriverId(),
								ENTITY_TYPE_DRIVER, ENTITY_TYPE_PRIMARY));
				
				if(verificationDetails==null)
				{
					MobileVerificationDetails mobileVerificationDetails=
							new MobileVerificationDetails(new MobileVerificationKey(driverDetails.getDriverId(), ENTITY_TYPE_DRIVER,
									ENTITY_TYPE_PRIMARY), driverDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), UPDATED_BY_CUST_ONLINE);
				
					mobileVerificationDetailsRepository.save(mobileVerificationDetails);
				}
				else
				{
					mobileVerificationDetailsRepository.updateMobile(driverDetails.getDriverId(), ENTITY_TYPE_DRIVER,
							ENTITY_TYPE_PRIMARY, driverDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT);
					
				}
				
				
			}
			
			driverDetails.setMobile(oldEntity.getMobile());
			
			return driverDetailsRepository.save(driverDetails);
		}
		else
		{
			DriverDetails savedEntity=driverDetailsRepository.save(driverDetails);
			
			if(driverDetails.getMobile()!=null)
			{	
				MobileVerificationDetails mobileVerificationDetails=
						new MobileVerificationDetails(new MobileVerificationKey(savedEntity.getDriverId(), ENTITY_TYPE_DRIVER, ENTITY_TYPE_PRIMARY),
								driverDetails.getMobile(), null, ZERO_COUNT, ZERO_COUNT, new Date(), new Date(), UPDATED_BY_CUST_ONLINE);
				
				mobileVerificationDetailsRepository.save(mobileVerificationDetails);
			
			}
			return savedEntity;
			
		}
		
		
	}
	
	public Integer updateMobileAndMobileVerificationStatus(Long driverId,Long mobile,Boolean isMobileVerified)
	{
		return driverDetailsRepository.updateMobileAndMobileVerificationStaus(driverId, mobile, isMobileVerified);
	}
	
	public List<DriverDetails> getDriverListByVendorId(Long vendorId)
	{
		return driverDetailsRepository.getDriverListByVendorId(vendorId);
	}
	
	public void deleteByKey(Long driverId)
	{
		driverDetailsRepository.delete(driverId);
	}
	
	public Integer count()
	{
		return (int)driverDetailsRepository.count();
	}
	
	public void deleteAll()
	{
		driverDetailsRepository.deleteAll();
	}
	
	public DriverDetails findOne(Long driverId)
	{
		return driverDetailsRepository.findOne(driverId);
	}
	
	
}



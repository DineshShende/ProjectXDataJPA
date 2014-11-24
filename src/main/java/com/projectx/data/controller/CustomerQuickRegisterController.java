package com.projectx.data.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.CustomerAuthenticationDetails;
import com.projectx.data.domain.CustomerDocumet;
import com.projectx.data.domain.CustomerEmailVerificationDetails;
import com.projectx.data.domain.CustomerMobileVerificationDetails;
import com.projectx.data.domain.CustomerQuickRegisterEntity;
import com.projectx.data.repository.CustomerAuthenticationDetailsRepository;
import com.projectx.data.repository.CustomerDocumetRepository;
import com.projectx.data.repository.CustomerEmailVerificationDetailsRepository;
import com.projectx.data.repository.CustomerMobileVerificationDetailsRepository;
import com.projectx.data.repository.CustomerQuickRegisterRepository;
import com.projectx.rest.domain.CustomerIdDTO;
import com.projectx.rest.domain.CustomerIdEmailDTO;
import com.projectx.rest.domain.CustomerIdMobileDTO;
import com.projectx.rest.domain.CustomerQuickRegisterDTO;
import com.projectx.rest.domain.GetByEmailDTO;
import com.projectx.rest.domain.GetByMobileDTO;
import com.projectx.rest.domain.LoginVerificationDTO;
import com.projectx.rest.domain.UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO;
import com.projectx.rest.domain.UpdateEmailHashDTO;
import com.projectx.rest.domain.UpdateEmailMobileVerificationStatus;
import com.projectx.rest.domain.UpdateEmailPassword;
import com.projectx.rest.domain.UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO;
import com.projectx.rest.domain.UpdatePasswordAndPasswordTypeDTO;

import static com.projectx.data.fixtures.CustomerQuickRegisterEntityDataFixture.*;

@RestController
@RequestMapping(value="/customer/quickregister")
public class CustomerQuickRegisterController {
	
	@Autowired
	CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
	@Autowired
	CustomerAuthenticationDetailsRepository customerAuthenticationDetailsRepository;  
	
	@Autowired
	CustomerEmailVerificationDetailsRepository customerEmailVerificationDetailsRepository;
	
	@Autowired
	CustomerMobileVerificationDetailsRepository customerMobileVerificationDetailsRepository; 
	
	@Autowired
	CustomerDocumetRepository customerDocumentRepository;
	
	
	private final Integer ZERO_COUNT=0;
	
	@RequestMapping(method=RequestMethod.POST)
	public CustomerQuickRegisterEntity saveNewCustomer(@Valid @RequestBody  CustomerQuickRegisterEntity customerEntity,BindingResult result)
	{
		if(result.hasErrors())
		{
			return new CustomerQuickRegisterEntity();
		}
		return customerQuickRegisterRepository.save(customerEntity);
				
	}
	
	@RequestMapping(value="/responseEntity",method=RequestMethod.POST)
	public ResponseEntity<CustomerQuickRegisterEntity> saveNewCustomerWithResponseEntity(@Valid @RequestBody  CustomerQuickRegisterEntity customerEntity,BindingResult result)
	{
		CustomerQuickRegisterEntity newEntity;
		
		if(result.hasErrors())
		{
			newEntity=new CustomerQuickRegisterEntity();
			return new ResponseEntity<CustomerQuickRegisterEntity>(newEntity, HttpStatus.CONFLICT);
		}
		else
		{
			newEntity=customerQuickRegisterRepository.save(customerEntity);
			return new ResponseEntity<CustomerQuickRegisterEntity>(newEntity, HttpStatus.CREATED);
		}
		
				
	}
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public List<CustomerQuickRegisterEntity> getAllCustomer()
	{
		return customerQuickRegisterRepository.findAll(); 
	}
	
	@RequestMapping(value="/getEntityByCustomerId",method=RequestMethod.POST)
	public CustomerQuickRegisterEntity getCustomerByCustomerId(@RequestBody CustomerIdDTO customerDTO)
	{
		
		Optional<CustomerQuickRegisterEntity> fetchedEntity= customerQuickRegisterRepository.findByCustomerId(customerDTO.getCustomerId());
		
		if(!fetchedEntity.isPresent())
			return new CustomerQuickRegisterEntity();
		
		return fetchedEntity.get();
	}
	
	@RequestMapping(value="/getCustomerQuickRegisterEntityByEmail",method=RequestMethod.POST)
	public CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByEmail(@RequestBody GetByEmailDTO getByEmailDTO)
	{
		Optional<CustomerQuickRegisterEntity> fetchedEntity=customerQuickRegisterRepository.findByEmail(getByEmailDTO.getEmail());
		
		if(!fetchedEntity.isPresent())
			return new CustomerQuickRegisterEntity();
		
		return fetchedEntity.get();
	}
	
	
	@RequestMapping(value="/getCustomerQuickRegisterEntityByMobile",method=RequestMethod.POST)
	public CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByMobile(@RequestBody GetByMobileDTO getByMobileDTO)
	{
		Optional<CustomerQuickRegisterEntity> fetchedEntity=customerQuickRegisterRepository.findByMobile(getByMobileDTO.getMobile());
		
		if(!fetchedEntity.isPresent())
			return new CustomerQuickRegisterEntity();
		
		return fetchedEntity.get();
	}
	
	
	@RequestMapping(value="/updateMobileVerificationStatus",method=RequestMethod.POST)
	public Integer updateMobileVerificationStatus(@RequestBody UpdateEmailMobileVerificationStatus updateStatus) throws InterruptedException
	{
		Integer result=customerQuickRegisterRepository.updateMobileVerificationStatus(updateStatus.getCustomerId(), updateStatus.getStatus(),
													updateStatus.getUpdateTime(), updateStatus.getUpdatedBy());
		return result;
	}
	
	
	@RequestMapping(value="/updateEmailVerificationStatus",method=RequestMethod.POST)
	public Integer updateEmailVerificationStatus(@RequestBody UpdateEmailMobileVerificationStatus updateStatus) throws InterruptedException
	{
		Integer result=customerQuickRegisterRepository.updateEmailVerificationStatus(updateStatus.getCustomerId(), updateStatus.getStatus(),
													updateStatus.getUpdateTime(), updateStatus.getUpdatedBy());
		return result;
	}
	
	//--------------Authentication Details----------
	

	@RequestMapping(value="/customerAuthentication/saveLoginDetails",method=RequestMethod.POST)
	public CustomerAuthenticationDetails saveLoginDetails(@RequestBody CustomerAuthenticationDetails customerAuthenticationDetails)
	{
		return customerAuthenticationDetailsRepository.save(customerAuthenticationDetails);
	}
	
	@RequestMapping(value="/customerAuthentication/getLoginDetailsByCustomerId",method=RequestMethod.POST)
	public CustomerAuthenticationDetails getLoginDetailsByCustomerId(@RequestBody CustomerIdDTO customerIdDTO)
	{
		CustomerAuthenticationDetails fetchedEntity=customerAuthenticationDetailsRepository.findByCustomerId(customerIdDTO.getCustomerId());
		
		if(fetchedEntity==null)
			return new CustomerAuthenticationDetails();
		
		return fetchedEntity;
	}
	
	
	@RequestMapping(value="/customerAuthentication/getLoginDetailsByEmail",method=RequestMethod.POST)
	public CustomerAuthenticationDetails getLoginDetailsByEmail(@RequestBody GetByEmailDTO emailDTO)
	{
		CustomerAuthenticationDetails fetchedEntity=customerAuthenticationDetailsRepository.findByEmail(emailDTO.getEmail());
		
		if(fetchedEntity==null)
			return new CustomerAuthenticationDetails();
		
		return fetchedEntity;
	}
	
	@RequestMapping(value="/customerAuthentication/getLoginDetailsByMobile",method=RequestMethod.POST)
	public CustomerAuthenticationDetails getLoginDetailsByMobile(@RequestBody GetByMobileDTO mobileDTO)
	{
		CustomerAuthenticationDetails fetchedEntity=customerAuthenticationDetailsRepository.findByMobile(mobileDTO.getMobile());
		
		if(fetchedEntity==null)
			return new CustomerAuthenticationDetails();
		
		return fetchedEntity;
	}
	
	
	@RequestMapping(value="/customerAuthentication/updatePasswordAndPasswordTypeAndCounts",method=RequestMethod.POST)
	public Integer updatePasswordAndPasswordTypeAndCounts(@RequestBody UpdatePasswordAndPasswordTypeDTO passwordAndPasswordTypeDTO)
	{
		Integer updateStatus=customerAuthenticationDetailsRepository
				.updatePasswordAndPasswordTypeAndCounts(passwordAndPasswordTypeDTO.getCustomerId(), passwordAndPasswordTypeDTO.getPassword(),
						passwordAndPasswordTypeDTO.getPasswordType(), ZERO_COUNT, ZERO_COUNT);
		
		
		return updateStatus;
	}
	
	@RequestMapping(value="/customerAuthentication/updateEmailPasswordAndPasswordTypeAndCounts",method=RequestMethod.POST)
	public Integer updateEmailPasswordAndPasswordTypeAndCounts(@RequestBody UpdateEmailPassword emailPassword)
	{
		Integer updateStatus=customerAuthenticationDetailsRepository
				.updateEmailPasswordAndPasswordTypeAndCounts(emailPassword.getCustomerId(),emailPassword.getEmailPassword(),
						"Default", ZERO_COUNT, ZERO_COUNT);
		
		
		return updateStatus;
	}
	
	@RequestMapping(value="/customerAuthentication/incrementResendCount",method=RequestMethod.POST)
	public Integer incrementResendCount(@RequestBody  CustomerIdDTO customerIdDTO)
	{
		Integer updateStatus=customerAuthenticationDetailsRepository
				.incrementResendCount(customerIdDTO.getCustomerId());
		
		
		return updateStatus;
	}
	
	@RequestMapping(value="/customerAuthentication/incrementLastUnsucessfullAttempts",method=RequestMethod.POST)
	public Integer incrementLastUnsucessfullAttempts(@RequestBody  CustomerIdDTO customerIdDTO)
	{
		Integer updateStatus=customerAuthenticationDetailsRepository
				.incrementLastUnsucessfullAttempts(customerIdDTO.getCustomerId());
		
		
		return updateStatus;
	}
	
	@RequestMapping(value="/customerAuthentication/loginDetailsCount" )
	public Integer loginDetailsCount()
	{
		return new Integer((int) customerAuthenticationDetailsRepository.count());
	}
	
	//------------------------EmailVerification
	
	@RequestMapping(value="/emailVerification/saveEmailVerificationDetails",method=RequestMethod.POST)
	public CustomerEmailVerificationDetails saveEmailVerificationEntity(@RequestBody CustomerEmailVerificationDetails emailVerificationDetails)
	{
		CustomerEmailVerificationDetails savedEmailVerificationDetails=customerEmailVerificationDetailsRepository.save(emailVerificationDetails);
		
		return savedEmailVerificationDetails;
		
	}
	
	@RequestMapping(value="/emailVerification/getEmailVerificationDetailsByCustomerIdAndEmail",method=RequestMethod.POST)
	public CustomerEmailVerificationDetails getEmailVerificationDetailsByCustomerIdAndEmail(@RequestBody CustomerIdEmailDTO customerIdEmailDTO)
	{
		CustomerEmailVerificationDetails fetchedEmailVerificationDetails=customerEmailVerificationDetailsRepository
				.findByCustomerIdAndEmail(customerIdEmailDTO.getCustomerId(), customerIdEmailDTO.getEmail());
		
		if(fetchedEmailVerificationDetails==null)
			fetchedEmailVerificationDetails=new CustomerEmailVerificationDetails();
		
		return fetchedEmailVerificationDetails;
		
	}
	
	@RequestMapping(value="/emailVerification/resetEmailHashAndEmailHashSentTime",method=RequestMethod.POST)
	public Integer resetEmailHashAndEmailHashSentTime(@RequestBody UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO dto)
	{
		Integer updateStatus=customerEmailVerificationDetailsRepository
				.resetEmailHashAndEmailHashSentTime(dto.getCustomerId(), dto.getEmail(), dto.getEmailHash(), dto.getEmailHashSentTime(),
						dto.getResendCount());
		
		return updateStatus;
		
	}
	
	@RequestMapping(value="/emailVerification/incrementResendCountByCustomerIdAndEmail",method=RequestMethod.POST)
	public Integer incrementResendCountByCustomerIdAndEmail(@RequestBody CustomerIdEmailDTO customerIdEmailDTO)
	{
		Integer updateStatus=customerEmailVerificationDetailsRepository
				.incrementResendCountByCustomerIdAndEmail(customerIdEmailDTO.getCustomerId(), customerIdEmailDTO.getEmail());
		
		return updateStatus;
		
	}
	
	@RequestMapping(value="/emailVerification/getCount")
	public Integer getEmailVerificationCount()
	{
		
		return new Integer((int) customerEmailVerificationDetailsRepository
				.count());
		
	}
	
	//--------------------------Mobile Verification
	
	@RequestMapping(value="/mobileVerification/saveMobileVerificationDetails",method=RequestMethod.POST)
	public CustomerMobileVerificationDetails saveMobileVerificationEntity(@RequestBody CustomerMobileVerificationDetails mobileVerificationDetails)
	{
		CustomerMobileVerificationDetails savedmobileVerificationDetails=customerMobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		return savedmobileVerificationDetails;
		
	}
	
	@RequestMapping(value="/mobileVerification/getMobileVerificationDetailsByCustomerIdAndMobile",method=RequestMethod.POST)
	public CustomerMobileVerificationDetails getMobileVerificationDetailsByCustomerIdAndMobile(@RequestBody CustomerIdMobileDTO customerIdMobileDTO)
	{
		CustomerMobileVerificationDetails fetchedMobileVerificationDetails=customerMobileVerificationDetailsRepository
				.findByCustomerIdAndMobile(customerIdMobileDTO.getCustomerId(), customerIdMobileDTO.getMobile());
		
		if(fetchedMobileVerificationDetails==null)
			return new CustomerMobileVerificationDetails();
		
		return fetchedMobileVerificationDetails;
		
	}
	
	@RequestMapping(value="/mobileVerification/updateMobilePinAndMobileVerificationAttemptsAndResendCount",method=RequestMethod.POST)
	public Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount
			(@RequestBody UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO dto)
	{
		Integer updateStatus=customerMobileVerificationDetailsRepository
				.updateMobilePinAndMobileVerificationAttemptsAndResendCount(dto.getCustomerId(), dto.getMobile(),
						dto.getMobilePin(),dto.getMobileVerificationAttempts(),dto.getResendCount());
		
		return updateStatus;
		
	}
	
	@RequestMapping(value="/mobileVerification/incrementMobileVerificationAttempts",method=RequestMethod.POST)
	public Integer incrementMobileVerificationAttempts
			(@RequestBody CustomerIdMobileDTO mobileDTO)
	{
		Integer updateStatus=customerMobileVerificationDetailsRepository
				.incrementMobileVerificationAttempts(mobileDTO.getCustomerId(),mobileDTO.getMobile());
		
		return updateStatus;
		
	}
	
	@RequestMapping(value="/mobileVerification/incrementResendCount",method=RequestMethod.POST)
	public Integer incrementResendCount
			(@RequestBody CustomerIdMobileDTO mobileDTO)
	{
		Integer updateStatus=customerMobileVerificationDetailsRepository
				.incrementResendCount(mobileDTO.getCustomerId(), mobileDTO.getMobile());
		
		return updateStatus;
		
	}
		
	@RequestMapping(value="/mobileVerification/getCount")
	public Integer mobileVerificationCount()
	{
		return new Integer((int) customerMobileVerificationDetailsRepository
				.count());
	}
	
	
	
	@RequestMapping(value="/saveCustomerDocument",method=RequestMethod.POST)
	public CustomerDocumet saveCustomerDocument(@RequestBody CustomerDocumet customerDocumet)
	{
		return customerDocumentRepository.save(customerDocumet);
	}
	
	@RequestMapping(value="/getCustomerDocumentById",method=RequestMethod.POST)
	public CustomerDocumet getCustomerDocumentById(@RequestBody CustomerIdDTO customerIdDTO)
	{
		return customerDocumentRepository.findOne(customerIdDTO.getCustomerId());
	}
	
	@RequestMapping(value="/getAllCustomerDocument")
	public CustomerDocumet getAllCustomerDocument()
	{
		return customerDocumentRepository.findAll().iterator().next();
	}
	//***********************Highly Dangerous***************************************/
	@RequestMapping(value="/clearForTesting")
	public Boolean clearTableForTesting()
	{
		 customerQuickRegisterRepository.clearTestData();
		 
		 return true;
	}
	
	@RequestMapping(value="/clearLoginDetailsForTesting")
	public Boolean clearLoginDetailsForTesting()
	{
		customerAuthenticationDetailsRepository.clearTestData();
		
		return true;
	}
	
	@RequestMapping(value="/clearEmailVerificationForTesting")
	public Boolean clearEmailVerificationForTesting()
	{
		customerEmailVerificationDetailsRepository.clearTestData();
		
		return true;
	}
	
	@RequestMapping(value="/clearMobileVerificationForTesting")
	public Boolean clearMobileVerificationForTesting()
	{
		customerMobileVerificationDetailsRepository.clearTestData();
		
		return true;
	}
	
	
	//***********************Highly Dangerous***************************************/
	
	@RequestMapping(value="/test")
	public CustomerMobileVerificationDetails test()
	{
		return new CustomerMobileVerificationDetails(CUST_ID, CUST_MOBILE, "PRIMARY", CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT);
	}
	@RequestMapping(value="/customer")
	public CustomerMobileVerificationDetails show()
	{
		return new CustomerMobileVerificationDetails(CUST_ID, CUST_MOBILE, "PRIMARY", CUST_MOBILEPIN, ZERO_COUNT, ZERO_COUNT);
	}
}

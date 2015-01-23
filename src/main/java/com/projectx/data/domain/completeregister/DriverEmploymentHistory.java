package com.projectx.data.domain.completeregister;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.data.util.serializer.JsonDateDeSerializer;
import com.projectx.data.util.serializer.JsonDateSerializer;

@Entity
@Table(name="DRIVEREMPLOYMENTHISTORY")
public class DriverEmploymentHistory {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="RECORDID")
	private Long recordId;
	
	@Column(name="DRIVERID")
	private Long driverId;
	
	@Column(name="FIRSTNAME")
	private String firstName;
	
	@Column(name="MIDDLENAME")
	private String middleName;
	
	@Column(name="LASTNAME")
	private String lastName;
	
	@Column(name="DATEOFBIRTH")
	private Date dateOfBirth;
	
	@Column(name="BLOODGROUP")
	private String bloodGroup;
	/*
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="HOMEADDRESS")
	private Address homeAddress;
	*/
	
	@Column(name="ADDRESSID")
	private Long addressId;
	
	@Column(name="ADDRESSLINE")
	private String addressLine; 
	
	@Column(name="CITY")
	private String city; 
	
	@Column(name="DISTRICT")
	private String district; 
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="PINCODE")
	private Integer pincode; 
	
	
	
	@Column(name="MOBILE")
	private Long mobile;
	
	@Column(name="HOMECONTACTNUMBER")
	private Long homeContactNumber;
	
	@Column(name="LICENCENUMBER")
	private String licenceNumber;
	
	@Column(name="DRIVINGSINCE")
	private Date drivingSince;
	
	@Column(name="EMPLOYEDSINCE")
	private Date employedSince;
	
	@Column(name="ISFREIGHTREQUESTPERMISSIONGIVEN")
	private Boolean isFreightRequestPermissionGiven;
	
	@Column(name="ISDEALFINALIZATIONPERMISSIONGIVEN")
	private Boolean isDealFinalizationPermissionGiven;

	@Column(name="LANGUAGE")
	private String language;
	
	@Column(name="VENDERID")
	private Long vendorId;
	
	@Column(name="INSERTTIME")
	private Date insertTime;
	
	@Column(name="UPDATETIME")
	private Date updateTime;
	
	@Column(name="UPDATEDBY")
	private String updatedBy;
	public DriverEmploymentHistory() {

	}




/*
	public DriverEmploymentHistory(Long recordId, Long driverId,
			String firstName, String middleName, String lastName,
			Date dateOfBirth, String bloodGroup, Address homeAddress,
			Long mobile, Long homeContactNumber, String licenceNumber,
			Date drivingSince, Date employedSince,
			Boolean isFreightRequestPermissionGiven,
			Boolean isDealFinalizationPermissionGiven, String language,
			Long vendorId, Date insertTime, Date updateTime, String updatedBy) {
		super();
		this.recordId = recordId;
		this.driverId = driverId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.bloodGroup = bloodGroup;
		this.homeAddress = homeAddress;
		this.mobile = mobile;
		this.homeContactNumber = homeContactNumber;
		this.licenceNumber = licenceNumber;
		this.drivingSince = drivingSince;
		this.employedSince = employedSince;
		this.isFreightRequestPermissionGiven = isFreightRequestPermissionGiven;
		this.isDealFinalizationPermissionGiven = isDealFinalizationPermissionGiven;
		this.language = language;
		this.vendorId = vendorId;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
	}

*/
	
	



	public Long getRecordId() {
		return recordId;
	}







	public DriverEmploymentHistory(Long recordId, Long driverId, String firstName,
		String middleName, String lastName, Date dateOfBirth,
		String bloodGroup, Long addressId, String addressLine, String city,
		String district, String state, Integer pincode, Long mobile,
		Long homeContactNumber, String licenceNumber, Date drivingSince,
		Date employedSince, Boolean isFreightRequestPermissionGiven,
		Boolean isDealFinalizationPermissionGiven, String language,
		Long vendorId, Date insertTime, Date updateTime, String updatedBy) {
	super();
	this.recordId = recordId;
	this.driverId = driverId;
	this.firstName = firstName;
	this.middleName = middleName;
	this.lastName = lastName;
	this.dateOfBirth = dateOfBirth;
	this.bloodGroup = bloodGroup;
	this.addressId = addressId;
	this.addressLine = addressLine;
	this.city = city;
	this.district = district;
	this.state = state;
	this.pincode = pincode;
	this.mobile = mobile;
	this.homeContactNumber = homeContactNumber;
	this.licenceNumber = licenceNumber;
	this.drivingSince = drivingSince;
	this.employedSince = employedSince;
	this.isFreightRequestPermissionGiven = isFreightRequestPermissionGiven;
	this.isDealFinalizationPermissionGiven = isDealFinalizationPermissionGiven;
	this.language = language;
	this.vendorId = vendorId;
	this.insertTime = insertTime;
	this.updateTime = updateTime;
	this.updatedBy = updatedBy;
}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public String getDistrict() {
		return district;
	}




	public void setDistrict(String district) {
		this.district = district;
	}




	public String getState() {
		return state;
	}




	public void setState(String state) {
		this.state = state;
	}




	public Integer getPincode() {
		return pincode;
	}




	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}




	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	/*
	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	*/
	
	

	public Long getMobile() {
		return mobile;
	}

	public String getAddressLine() {
		return addressLine;
	}




	public Long getAddressId() {
		return addressId;
	}




	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}




	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}




	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Long getHomeContactNumber() {
		return homeContactNumber;
	}

	public void setHomeContactNumber(Long homeContactNumber) {
		this.homeContactNumber = homeContactNumber;
	}


	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDrivingSince() {
		return drivingSince;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setDrivingSince(Date drivingSince) {
		this.drivingSince = drivingSince;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getEmployedSince() {
		return employedSince;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setEmployedSince(Date employedSince) {
		this.employedSince = employedSince;
	}


	public Boolean getIsFreightRequestPermissionGiven() {
		return isFreightRequestPermissionGiven;
	}

	public void setIsFreightRequestPermissionGiven(
			Boolean isFreightRequestPermissionGiven) {
		this.isFreightRequestPermissionGiven = isFreightRequestPermissionGiven;
	}

	public Boolean getIsDealFinalizationPermissionGiven() {
		return isDealFinalizationPermissionGiven;
	}

	public void setIsDealFinalizationPermissionGiven(
			Boolean isDealFinalizationPermissionGiven) {
		this.isDealFinalizationPermissionGiven = isDealFinalizationPermissionGiven;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getInsertTime() {
		return insertTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}




	@Override
	public String toString() {
		return "DriverEmploymentHistory [recordId=" + recordId + ", driverId="
				+ driverId + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", bloodGroup=" + bloodGroup + ", addressId="
				+ addressId + ", addressLine=" + addressLine + ", city=" + city
				+ ", district=" + district + ", state=" + state + ", pincode="
				+ pincode + ", mobile=" + mobile + ", homeContactNumber="
				+ homeContactNumber + ", licenceNumber=" + licenceNumber
				+ ", drivingSince=" + drivingSince + ", employedSince="
				+ employedSince + ", isFreightRequestPermissionGiven="
				+ isFreightRequestPermissionGiven
				+ ", isDealFinalizationPermissionGiven="
				+ isDealFinalizationPermissionGiven + ", language=" + language
				+ ", vendorId=" + vendorId + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", updatedBy=" + updatedBy
				+ "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((addressId == null) ? 0 : addressId.hashCode());
		result = prime * result
				+ ((addressLine == null) ? 0 : addressLine.hashCode());
		result = prime * result
				+ ((bloodGroup == null) ? 0 : bloodGroup.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result
				+ ((district == null) ? 0 : district.hashCode());
		result = prime * result
				+ ((driverId == null) ? 0 : driverId.hashCode());
		result = prime * result
				+ ((drivingSince == null) ? 0 : drivingSince.hashCode());
		result = prime * result
				+ ((employedSince == null) ? 0 : employedSince.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime
				* result
				+ ((homeContactNumber == null) ? 0 : homeContactNumber
						.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime
				* result
				+ ((isDealFinalizationPermissionGiven == null) ? 0
						: isDealFinalizationPermissionGiven.hashCode());
		result = prime
				* result
				+ ((isFreightRequestPermissionGiven == null) ? 0
						: isFreightRequestPermissionGiven.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((licenceNumber == null) ? 0 : licenceNumber.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
		result = prime * result
				+ ((recordId == null) ? 0 : recordId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((vendorId == null) ? 0 : vendorId.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DriverEmploymentHistory other = (DriverEmploymentHistory) obj;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
		if (addressLine == null) {
			if (other.addressLine != null)
				return false;
		} else if (!addressLine.equals(other.addressLine))
			return false;
		if (bloodGroup == null) {
			if (other.bloodGroup != null)
				return false;
		} else if (!bloodGroup.equals(other.bloodGroup))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (driverId == null) {
			if (other.driverId != null)
				return false;
		} else if (!driverId.equals(other.driverId))
			return false;
		if (drivingSince == null) {
			if (other.drivingSince != null)
				return false;
		} else if (!drivingSince.equals(other.drivingSince))
			return false;
		if (employedSince == null) {
			if (other.employedSince != null)
				return false;
		} else if (!employedSince.equals(other.employedSince))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (homeContactNumber == null) {
			if (other.homeContactNumber != null)
				return false;
		} else if (!homeContactNumber.equals(other.homeContactNumber))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
		if (isDealFinalizationPermissionGiven == null) {
			if (other.isDealFinalizationPermissionGiven != null)
				return false;
		} else if (!isDealFinalizationPermissionGiven
				.equals(other.isDealFinalizationPermissionGiven))
			return false;
		if (isFreightRequestPermissionGiven == null) {
			if (other.isFreightRequestPermissionGiven != null)
				return false;
		} else if (!isFreightRequestPermissionGiven
				.equals(other.isFreightRequestPermissionGiven))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (licenceNumber == null) {
			if (other.licenceNumber != null)
				return false;
		} else if (!licenceNumber.equals(other.licenceNumber))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (pincode == null) {
			if (other.pincode != null)
				return false;
		} else if (!pincode.equals(other.pincode))
			return false;
		if (recordId == null) {
			if (other.recordId != null)
				return false;
		} else if (!recordId.equals(other.recordId))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		return true;
	}



		
	

}
/*
DROP TRIGGER IF EXISTS driverdetails_log_update;

DELIMITER $$
CREATE TRIGGER driverdetails_log_update
AFTER UPDATE  ON driverdetails
FOR EACH ROW
BEGIN

	declare districtN text;
	declare cityN text;
	declare addressidNew bigint;	
	declare pincodeN bigint;
	declare addresslineN text;
	declare stateN text;
	
	
	DECLARE addres_cursor CURSOR FOR SELECT addressid,addressline,city,district,state,pincode FROM address where addressid=NEW.homeaddress;

	
	OPEN addres_cursor;
	FETCH addres_cursor INTO addressidNew,addresslineN,cityN,districtN,stateN,pincodeN;


	insert into driveremploymenthistory (driverId ,bloodgroup, dateofbirth, drivingsince,
			employedsince, firstname,addressid,addressline,pincode, city,district,state, homecontactnumber, inserttime, 
			isdealfinalizationpermissiongiven, isfreightrequestpermissiongiven, 
			language, lastname, licencenumber, middlename, mobile, updatetime, 
			updatedby, venderid) 
		values (NEW.driverid,NEW.bloodgroup, NEW.dateofbirth, NEW.drivingsince, NEW.employedsince,
			NEW.firstname,addressidNew,addresslineN,pincodeN,cityN,districtN,stateN,NEW.homecontactnumber, NEW.inserttime,
			NEW.isdealfinalizationpermissiongiven, NEW.isfreightrequestpermissiongiven, 
			NEW.language, NEW.lastname, NEW.licencenumber, NEW.middlename, NEW.mobile, 
			NEW.updatetime, NEW.updatedby, NEW.venderid);


END;
*/
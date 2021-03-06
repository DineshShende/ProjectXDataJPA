package com.projectx.data.domain.completeregister;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.data.util.serializer.JsonDateDeSerializer;
import com.projectx.data.util.serializer.JsonDateSerializer;

@Entity
@Table(name="VEHICLEOWNERHISTORY")
public class VehicleOwnerHistory {

	@Id @Column(name="RECORDID")
	private Long recordId;
	
	@Column(name="VEHICLEID")
	private Long vehicleId;
	
	@Column(name="OWNERFIRSTNAME")
	private String ownerFirstName;
	
	@Column(name="OWNERMIDDLENAME")
	private String ownerMiddleName;
	
	@Column(name="OWNERLASTNAME")
	private String ownerLastName;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="VEHICLETYPEID")
	private VehicleTypeDetails vehicleTypeId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="VEHICLEBRANDID")
	private VehicleBrandDetails vehicleBrandId;
	
	@Column(name="VEHICLEBODYTYPE")
	private String vehicleBodyType;
	
	@Column(name="ISBODYTYPEFLEXIBLE")
	private Boolean isBodyTypeFlexible;
	
	@Column(name="REGISTRATIONNUMBER")
	private String registrationNumber;
	
	@Column(name="CHASSISNUMBER")
	private String chassisNumber;
	
	@Column(name="LOADCAPCITYINTONS")
	private Integer loadCapacityInTons;
	
	@Column(name="NUMBEROFWHEELS")
	private Integer numberOfWheels;
	
	@Column(name="PERMITTYPE")
	private String permitType;
	
	@Column(name="INSURANCESTATUS")
	private Boolean insuranceStatus;
	
	@Column(name="INSURANCENUMBER")
	private String insuranceNumber;
	
	@Column(name="INSURANCECOMPANY")
	private String insuranceCompany;
	
	@Column(name="VENDORID")
	private Long vendorId;
	
	@Column(name="INSERTTIME")
	private Date insertTime;
	
	@Column(name="UPDATETIME")
	private Date updateTime;
	
	@Column(name="UPDATEDBY")
	private String updatedBy;
	
	public VehicleOwnerHistory() {

	}


	public VehicleOwnerHistory(Long recordId, Long vehicleId,
			String ownerFirstName, String ownerMiddleName,
			String ownerLastName, VehicleTypeDetails vehicleTypeId,
			VehicleBrandDetails vehicleBrandId, String vehicleBodyType,
			Boolean isBodyTypeFlexible, String registrationNumber,
			String chassisNumber, Integer loadCapacityInTons,
			Integer numberOfWheels, String permitType, Boolean insuranceStatus,
			String insuranceNumber, String insuranceCompany, Long vendorId,
			Date insertTime, Date updateTime, String updatedBy) {
		super();
		this.recordId = recordId;
		this.vehicleId = vehicleId;
		this.ownerFirstName = ownerFirstName;
		this.ownerMiddleName = ownerMiddleName;
		this.ownerLastName = ownerLastName;
		this.vehicleTypeId = vehicleTypeId;
		this.vehicleBrandId = vehicleBrandId;
		this.vehicleBodyType = vehicleBodyType;
		this.isBodyTypeFlexible = isBodyTypeFlexible;
		this.registrationNumber = registrationNumber;
		this.chassisNumber = chassisNumber;
		this.loadCapacityInTons = loadCapacityInTons;
		this.numberOfWheels = numberOfWheels;
		this.permitType = permitType;
		this.insuranceStatus = insuranceStatus;
		this.insuranceNumber = insuranceNumber;
		this.insuranceCompany = insuranceCompany;
		this.vendorId = vendorId;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
	}


	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}


	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	
	
	public Long getRecordId() {
		return recordId;
	}


	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}


	public String getOwnerFirstName() {
		return ownerFirstName;
	}


	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}


	public String getOwnerMiddleName() {
		return ownerMiddleName;
	}


	public void setOwnerMiddleName(String ownerMiddleName) {
		this.ownerMiddleName = ownerMiddleName;
	}


	public String getOwnerLastName() {
		return ownerLastName;
	}


	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}


	public VehicleTypeDetails getVehicleTypeId() {
		return vehicleTypeId;
	}


	public void setVehicleTypeId(VehicleTypeDetails vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}


	public VehicleBrandDetails getVehicleBrandId() {
		return vehicleBrandId;
	}


	public void setVehicleBrandId(VehicleBrandDetails vehicleBrandId) {
		this.vehicleBrandId = vehicleBrandId;
	}


	public String getVehicleBodyType() {
		return vehicleBodyType;
	}


	public void setVehicleBodyType(String vehicleBodyType) {
		this.vehicleBodyType = vehicleBodyType;
	}


	public Boolean getIsBodyTypeFlexible() {
		return isBodyTypeFlexible;
	}


	public void setIsBodyTypeFlexible(Boolean isBodyTypeFlexible) {
		this.isBodyTypeFlexible = isBodyTypeFlexible;
	}


	public String getRegistrationNumber() {
		return registrationNumber;
	}


	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	public String getChassisNumber() {
		return chassisNumber;
	}


	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}


	public Integer getLoadCapacityInTons() {
		return loadCapacityInTons;
	}


	public void setLoadCapacityInTons(Integer loadCapacityInTons) {
		this.loadCapacityInTons = loadCapacityInTons;
	}


	public Integer getNumberOfWheels() {
		return numberOfWheels;
	}


	public void setNumberOfWheels(Integer numberOfWheels) {
		this.numberOfWheels = numberOfWheels;
	}


	public String getPermitType() {
		return permitType;
	}


	public void setPermitType(String permitType) {
		this.permitType = permitType;
	}


	public Boolean getInsuranceStatus() {
		return insuranceStatus;
	}


	public void setInsuranceStatus(Boolean insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}


	public String getInsuranceNumber() {
		return insuranceNumber;
	}


	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}


	public String getInsuranceCompany() {
		return insuranceCompany;
	}


	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
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

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "VehicleOwnerHistory [recordId=" + recordId + ", vehicleId="
				+ vehicleId + ", ownerFirstName=" + ownerFirstName
				+ ", ownerMiddleName=" + ownerMiddleName + ", ownerLastName="
				+ ownerLastName + ", vehicleTypeId=" + vehicleTypeId
				+ ", vehicleBrandId=" + vehicleBrandId + ", vehicleBodyType="
				+ vehicleBodyType + ", isBodyTypeFlexible="
				+ isBodyTypeFlexible + ", registrationNumber="
				+ registrationNumber + ", chassisNumber=" + chassisNumber
				+ ", loadCapacityInTons=" + loadCapacityInTons
				+ ", numberOfWheels=" + numberOfWheels + ", permitType="
				+ permitType + ", insuranceStatus=" + insuranceStatus
				+ ", insuranceNumber=" + insuranceNumber
				+ ", insuranceCompany=" + insuranceCompany + ", vendorId="
				+ vendorId + ", insertTime=" + insertTime + ", updateTime="
				+ updateTime + ", updatedBy=" + updatedBy + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chassisNumber == null) ? 0 : chassisNumber.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime
				* result
				+ ((insuranceCompany == null) ? 0 : insuranceCompany.hashCode());
		result = prime * result
				+ ((insuranceNumber == null) ? 0 : insuranceNumber.hashCode());
		result = prime * result
				+ ((insuranceStatus == null) ? 0 : insuranceStatus.hashCode());
		result = prime
				* result
				+ ((isBodyTypeFlexible == null) ? 0 : isBodyTypeFlexible
						.hashCode());
		result = prime
				* result
				+ ((loadCapacityInTons == null) ? 0 : loadCapacityInTons
						.hashCode());
		result = prime * result
				+ ((numberOfWheels == null) ? 0 : numberOfWheels.hashCode());
		result = prime * result
				+ ((ownerFirstName == null) ? 0 : ownerFirstName.hashCode());
		result = prime * result
				+ ((ownerLastName == null) ? 0 : ownerLastName.hashCode());
		result = prime * result
				+ ((ownerMiddleName == null) ? 0 : ownerMiddleName.hashCode());
		result = prime * result
				+ ((permitType == null) ? 0 : permitType.hashCode());
		result = prime * result
				+ ((recordId == null) ? 0 : recordId.hashCode());
		result = prime
				* result
				+ ((registrationNumber == null) ? 0 : registrationNumber
						.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((vehicleBodyType == null) ? 0 : vehicleBodyType.hashCode());
		result = prime * result
				+ ((vehicleBrandId == null) ? 0 : vehicleBrandId.hashCode());
		result = prime * result
				+ ((vehicleId == null) ? 0 : vehicleId.hashCode());
		result = prime * result
				+ ((vehicleTypeId == null) ? 0 : vehicleTypeId.hashCode());
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
		VehicleOwnerHistory other = (VehicleOwnerHistory) obj;
		if (chassisNumber == null) {
			if (other.chassisNumber != null)
				return false;
		} else if (!chassisNumber.equals(other.chassisNumber))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (Math.abs(insertTime.getTime()-other.insertTime.getTime())>1000000)
			return false;
		if (insuranceCompany == null) {
			if (other.insuranceCompany != null)
				return false;
		} else if (!insuranceCompany.equals(other.insuranceCompany))
			return false;
		if (insuranceNumber == null) {
			if (other.insuranceNumber != null)
				return false;
		} else if (!insuranceNumber.equals(other.insuranceNumber))
			return false;
		if (insuranceStatus == null) {
			if (other.insuranceStatus != null)
				return false;
		} else if (!insuranceStatus.equals(other.insuranceStatus))
			return false;
		if (isBodyTypeFlexible == null) {
			if (other.isBodyTypeFlexible != null)
				return false;
		} else if (!isBodyTypeFlexible.equals(other.isBodyTypeFlexible))
			return false;
		if (loadCapacityInTons == null) {
			if (other.loadCapacityInTons != null)
				return false;
		} else if (!loadCapacityInTons.equals(other.loadCapacityInTons))
			return false;
		if (numberOfWheels == null) {
			if (other.numberOfWheels != null)
				return false;
		} else if (!numberOfWheels.equals(other.numberOfWheels))
			return false;
		if (ownerFirstName == null) {
			if (other.ownerFirstName != null)
				return false;
		} else if (!ownerFirstName.equals(other.ownerFirstName))
			return false;
		if (ownerLastName == null) {
			if (other.ownerLastName != null)
				return false;
		} else if (!ownerLastName.equals(other.ownerLastName))
			return false;
		if (ownerMiddleName == null) {
			if (other.ownerMiddleName != null)
				return false;
		} else if (!ownerMiddleName.equals(other.ownerMiddleName))
			return false;
		if (permitType == null) {
			if (other.permitType != null)
				return false;
		} else if (!permitType.equals(other.permitType))
			return false;
		if (recordId == null) {
			if (other.recordId != null)
				return false;
		} else if (!recordId.equals(other.recordId))
			return false;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (Math.abs(updateTime.getTime()-other.updateTime.getTime())>1000000)
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (vehicleBodyType == null) {
			if (other.vehicleBodyType != null)
				return false;
		} else if (!vehicleBodyType.equals(other.vehicleBodyType))
			return false;
		if (vehicleBrandId == null) {
			if (other.vehicleBrandId != null)
				return false;
		} else if (!vehicleBrandId.equals(other.vehicleBrandId))
			return false;
		if (vehicleId == null) {
			if (other.vehicleId != null)
				return false;
		} else if (!vehicleId.equals(other.vehicleId))
			return false;
		if (vehicleTypeId == null) {
			if (other.vehicleTypeId != null)
				return false;
		} else if (!vehicleTypeId.equals(other.vehicleTypeId))
			return false;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		return true;
	}

		
	
	
}

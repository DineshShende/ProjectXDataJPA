package com.projectx.data.domain.completeregister;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;




import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.data.util.annotation.VehicleDetailsValid;
import com.projectx.data.util.serializer.JsonDateDeSerializer;
import com.projectx.data.util.serializer.JsonDateSerializer;

@VehicleDetailsValid
@Entity
@Table(name="VEHICLEDETAILS")
public class VehicleDetails {

	@Id @GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="VEHICLEID")
	private Long vehicleId;
	
	@NotNull
	@Column(name="OWNERFIRSTNAME")
	private String ownerFirstName;
	
	@Column(name="OWNERMIDDLENAME")
	private String ownerMiddleName;
	
	@NotNull
	@Column(name="OWNERLASTNAME")
	private String ownerLastName;
	
	/*
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="VEHICLETYPEID")
	private VehicleTypeDetails vehicleTypeId;
	*/
	
	@OneToOne
	(cascade={CascadeType.ALL})
	//@Cascade(value={CascadeType.ALL})
	@JoinColumn(name="VEHICLEBRANDID")
	private VehicleBrandDetails vehicleBrandId;
	
	@NotNull
	@Column(name="VEHICLEBODYTYPE")
	private String vehicleBodyType;
	
	@NotNull
	@Column(name="ISBODYTYPEFLEXIBLE")
	private Boolean isBodyTypeFlexible;
	
	@NotNull
	@Column(name="REGISTRATIONNUMBER",unique=true)
	private String registrationNumber;
	
	@NotNull
	@Column(name="CHASSISNUMBER",unique=true)
	private String chassisNumber;
	
	@NotNull
	@Column(name="LOADCAPCITYINTONS")
	private Integer loadCapacityInTons;
	
	@NotNull
	@Column(name="LENGTH")
	private Integer length;
	
	@NotNull
	@Column(name="WIDTH")
	private Integer width;
	
	@NotNull
	@Column(name="HEIGHT")
	private Integer height;
	
	@NotNull
	@Column(name="NUMBEROFWHEELS")
	private Integer numberOfWheels;
	
	@NotNull
	@Column(name="PERMITTYPE")
	private String permitType;
	
	@Column(name="INSURANCESTATUS")
	private Boolean insuranceStatus;
	
	@Column(name="INSURANCENUMBER")
	private String insuranceNumber;
	
	@Column(name="INSURANCECOMPANY")
	private String insuranceCompany;
	
	@NotNull
	@Column(name="VENDORID")
	private Long vendorId;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@JoinTable(name="COMMODITYLIST",joinColumns=@JoinColumn(name="VEHICLEID"))
	private List<String> commodityList;
	
	
	@NotNull(message="InsertTime can't be NULL")
	@Column(name="INSERTTIME")
	private Date insertTime;
	
	@NotNull(message="UpdateTime can't be NULL")
	@Column(name="UPDATETIME")
	private Date updateTime;
	
	@NotNull(message="UpdatedBy can't be NULL")
	@Column(name="UPDATEDBY")
	private Integer updatedBy;
	
	@NotNull(message="InsertedBy can't be NULL")
	@Column(name="INSERTEDBY")
	private Integer insertedBy;
	
	@NotNull(message="UpdatedById can't be NULL")
	@Column(name="UPDATEDBYID")
	private Long updatedById;
	
	@NotNull(message="InsertedById can't be NULL")
	@Column(name="INSERTEDBYID")
	private Long insertedById;


	public VehicleDetails() {

	}


	public VehicleDetails(Long vehicleId, String ownerFirstName,
			String ownerMiddleName, String ownerLastName,
			
			VehicleBrandDetails vehicleBrandId, String vehicleBodyType,
			Boolean isBodyTypeFlexible, String registrationNumber,
			String chassisNumber, Integer loadCapacityInTons, Integer length,
			Integer width, Integer height, Integer numberOfWheels,
			String permitType, Boolean insuranceStatus, String insuranceNumber,
			String insuranceCompany, Long vendorId, List<String> commodityList,
			Date insertTime, Date updateTime, Integer updatedBy,
			Integer insertedBy,Long updatedById,Long insertedById) {
		super();
		this.vehicleId = vehicleId;
		this.ownerFirstName = ownerFirstName;
		this.ownerMiddleName = ownerMiddleName;
		this.ownerLastName = ownerLastName;
		this.vehicleBrandId = vehicleBrandId;
		this.vehicleBodyType = vehicleBodyType;
		this.isBodyTypeFlexible = isBodyTypeFlexible;
		this.registrationNumber = registrationNumber;
		this.chassisNumber = chassisNumber;
		this.loadCapacityInTons = loadCapacityInTons;
		this.length = length;
		this.width = width;
		this.height = height;
		this.numberOfWheels = numberOfWheels;
		this.permitType = permitType;
		this.insuranceStatus = insuranceStatus;
		this.insuranceNumber = insuranceNumber;
		this.insuranceCompany = insuranceCompany;
		this.vendorId = vendorId;
		this.commodityList = commodityList;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy=insertedBy;
		this.updatedById=updatedById;
		this.insertedById=insertedById;
	}







	public Long getVehicleId() {
		return vehicleId;
	}



	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
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


	

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getNumberOfWheels() {
		return numberOfWheels;
	}



	public void setNumberOfWheels(Integer numberOfVehicle) {
		this.numberOfWheels = numberOfVehicle;
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



	public Long getVendorId() {
		return vendorId;
	}



	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	
	

	public List<String> getCommodityList() {
		return commodityList;
	}


	public void setCommodityList(List<String> commodityList) {
		this.commodityList = commodityList;
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


	public Integer getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Integer getInsertedBy() {
		return insertedBy;
	}


	public void setInsertedBy(Integer insertedBy) {
		this.insertedBy = insertedBy;
	}


	public Long getUpdatedById() {
		return updatedById;
	}


	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}


	public Long getInsertedById() {
		return insertedById;
	}


	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}


	@Override
	public String toString() {
		return "VehicleDetails [vehicleId=" + vehicleId + ", ownerFirstName="
				+ ownerFirstName + ", ownerMiddleName=" + ownerMiddleName
				+ ", ownerLastName=" + ownerLastName + ", vehicleBrandId="
				+ vehicleBrandId + ", vehicleBodyType=" + vehicleBodyType
				+ ", isBodyTypeFlexible=" + isBodyTypeFlexible
				+ ", registrationNumber=" + registrationNumber
				+ ", chassisNumber=" + chassisNumber + ", loadCapacityInTons="
				+ loadCapacityInTons + ", length=" + length + ", width="
				+ width + ", height=" + height + ", numberOfWheels="
				+ numberOfWheels + ", permitType=" + permitType
				+ ", insuranceStatus=" + insuranceStatus + ", insuranceNumber="
				+ insuranceNumber + ", insuranceCompany=" + insuranceCompany
				+ ", vendorId=" + vendorId + ", commodityList=" + commodityList
				+ ", insertTime=" + insertTime + ", updateTime=" + updateTime
				+ ", updatedBy=" + updatedBy + ", insertedBy=" + insertedBy
				+ ", updatedById=" + updatedById + ", insertedById="
				+ insertedById + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chassisNumber == null) ? 0 : chassisNumber.hashCode());
		result = prime * result
				+ ((commodityList == null) ? 0 : commodityList.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((insertedBy == null) ? 0 : insertedBy.hashCode());
		result = prime * result
				+ ((insertedById == null) ? 0 : insertedById.hashCode());
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
		result = prime * result + ((length == null) ? 0 : length.hashCode());
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
		result = prime
				* result
				+ ((registrationNumber == null) ? 0 : registrationNumber
						.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
		result = prime * result
				+ ((vehicleBodyType == null) ? 0 : vehicleBodyType.hashCode());
		result = prime * result
				+ ((vehicleBrandId == null) ? 0 : vehicleBrandId.hashCode());
		result = prime * result
				+ ((vehicleId == null) ? 0 : vehicleId.hashCode());
		result = prime * result
				+ ((vendorId == null) ? 0 : vendorId.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
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
		VehicleDetails other = (VehicleDetails) obj;
		if (chassisNumber == null) {
			if (other.chassisNumber != null)
				return false;
		} else if (!chassisNumber.equals(other.chassisNumber))
			return false;
		if (commodityList == null) {
			if (other.commodityList != null)
				return false;
		} else if (!commodityList.equals(other.commodityList))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} 
		if (insertedBy == null) {
			if (other.insertedBy != null)
				return false;
		} else if (!insertedBy.equals(other.insertedBy))
			return false;
		if (insertedById == null) {
			if (other.insertedById != null)
				return false;
		} else if (!insertedById.equals(other.insertedById))
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
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
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
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} 
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedById == null) {
			if (other.updatedById != null)
				return false;
		} else if (!updatedById.equals(other.updatedById))
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
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}


	
	
	
}

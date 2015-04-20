package com.projectx.data.domain.request;

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.data.domain.completeregister.VehicleDetails;
import com.projectx.data.util.serializer.JsonDateDeSerializer;
import com.projectx.data.util.serializer.JsonDateSerializer;
@Entity
@Table(name="FREIGHTREQUESTBYVENDOR")
public class FreightRequestByVendor {

	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REQUESTID")
	private Long requestId;
	
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="VEHICLEDETAILSID")
	private VehicleDetails vehicleDetailsId;
	

	@NotNull
	@Column(name="SOURCE")
	private Integer source;
	
	@NotNull
	@Column(name="DESTINATION")
	private Integer destination;
	
	@Column(name="DRIVERID")
	private Long driverId;
	
	@NotNull
	@Column(name="AVAILABLEDATE")
	private Date availableDate;
	
	@Column(name="AVAILABLETIME")
	private String availableTime;
	
	@Column(name="PICKUPRANGEINKM")
	private Integer pickupRangeInKm;

	@NotNull
	@Column(name="VENDORID")
	private Long vendorId;

	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="RESERVEDBY")
	private Long reservedBy;
	
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

	public FreightRequestByVendor() {

	}

	
	
	
	public FreightRequestByVendor(Long requestId,
			VehicleDetails vehicleDetailsId, Integer source,
			Integer destination, Long driverId, Date availableDate,
			String availableTime, Integer pickupRangeInKm, Long vendorId,
			String status, Long reservedBy, Date insertTime, Date updateTime,Integer updatedBy,
			Integer insertedBy,Long updatedById,Long insertedById) {
		super();
		this.requestId = requestId;
		this.vehicleDetailsId = vehicleDetailsId;
		this.source = source;
		this.destination = destination;
		this.driverId = driverId;
		this.availableDate = availableDate;
		this.availableTime = availableTime;
		this.pickupRangeInKm = pickupRangeInKm;
		this.vendorId = vendorId;
		this.status = status;
		this.reservedBy = reservedBy;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy=insertedBy;
		this.updatedById=updatedById;
		this.insertedById=insertedById;
	}




	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public VehicleDetails getVehicleDetailsId() {
		return vehicleDetailsId;
	}

	public void setVehicleDetailsId(VehicleDetails vehicleDetailsId) {
		this.vehicleDetailsId = vehicleDetailsId;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getDestination() {
		return destination;
	}

	public void setDestination(Integer destination) {
		this.destination = destination;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getAvailableDate() {
		return availableDate;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	public String getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(String availableTime) {
		this.availableTime = availableTime;
	}

	public Integer getPickupRangeInKm() {
		return pickupRangeInKm;
	}

	public void setPickupRangeInKm(Integer pickupRangeInKm) {
		this.pickupRangeInKm = pickupRangeInKm;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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




	public Long getReservedBy() {
		return reservedBy;
	}




	public void setReservedBy(Long reservedBy) {
		this.reservedBy = reservedBy;
	}




	@Override
	public String toString() {
		return "FreightRequestByVendor [requestId=" + requestId
				+ ", vehicleDetailsId=" + vehicleDetailsId + ", source="
				+ source + ", destination=" + destination + ", driverId="
				+ driverId + ", availableDate=" + availableDate
				+ ", availableTime=" + availableTime + ", pickupRangeInKm="
				+ pickupRangeInKm + ", vendorId=" + vendorId + ", status="
				+ status + ", reservedBy=" + reservedBy + ", insertTime="
				+ insertTime + ", updateTime=" + updateTime + ", updatedBy="
				+ updatedBy + ", insertedBy=" + insertedBy + ", updatedById="
				+ updatedById + ", insertedById=" + insertedById + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((availableDate == null) ? 0 : availableDate.hashCode());
		result = prime * result
				+ ((availableTime == null) ? 0 : availableTime.hashCode());
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result
				+ ((driverId == null) ? 0 : driverId.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((insertedBy == null) ? 0 : insertedBy.hashCode());
		result = prime * result
				+ ((insertedById == null) ? 0 : insertedById.hashCode());
		result = prime * result
				+ ((pickupRangeInKm == null) ? 0 : pickupRangeInKm.hashCode());
		result = prime * result
				+ ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result
				+ ((reservedBy == null) ? 0 : reservedBy.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
		result = prime
				* result
				+ ((vehicleDetailsId == null) ? 0 : vehicleDetailsId.hashCode());
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
		FreightRequestByVendor other = (FreightRequestByVendor) obj;
		if (availableDate == null) {
			if (other.availableDate != null)
				return false;
		}
		if (availableTime == null) {
			if (other.availableTime != null)
				return false;
		} else if (!availableTime.equals(other.availableTime))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (driverId == null) {
			if (other.driverId != null)
				return false;
		} else if (!driverId.equals(other.driverId))
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
		if (pickupRangeInKm == null) {
			if (other.pickupRangeInKm != null)
				return false;
		} else if (!pickupRangeInKm.equals(other.pickupRangeInKm))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (reservedBy == null) {
			if (other.reservedBy != null)
				return false;
		} else if (!reservedBy.equals(other.reservedBy))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
		if (vehicleDetailsId == null) {
			if (other.vehicleDetailsId != null)
				return false;
		} else if (!vehicleDetailsId.equals(other.vehicleDetailsId))
			return false;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		return true;
	}

	
	
}

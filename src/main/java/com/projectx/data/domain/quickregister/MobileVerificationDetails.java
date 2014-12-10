package com.projectx.data.domain.quickregister;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="mobileverificationdetails")
public class MobileVerificationDetails <ID extends Serializable> implements Persistable<ID>{

	private static final long serialVersionUID = 1L;


	@EmbeddedId
	private MobileVerificationKey key;
	

	@Column(name="MOBILETYPE")
	private String mobileType;
	
	@Column(name="MOBILEPIN")
	private Integer mobilePin;
	
	@Column(name="MOBILEVERIFICATIONATTEMPTS")
	private Integer mobileVerificationAttempts;
	
	@Column(name="RESENDCOUNT")
	private Integer resendCount;
	
	@Column(name="INSERTIME")
	private Date insertTime;
	
	@Column(name="UPDATETIME")
	private Date updateTime;
	
	@Column(name="UPDATEDBY")
	private String updatedBy;

	public MobileVerificationDetails() {

	}

	

	public MobileVerificationDetails(MobileVerificationKey key,
			String mobileType, Integer mobilePin,
			Integer mobileVerificationAttempts, Integer resendCount,
			Date insertTime, Date updateTime, String updatedBy) {
		super();
		this.key = key;
		this.mobileType = mobileType;
		this.mobilePin = mobilePin;
		this.mobileVerificationAttempts = mobileVerificationAttempts;
		this.resendCount = resendCount;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
	}



	public MobileVerificationKey getKey() {
		return key;
	}

	public void setKey(MobileVerificationKey key) {
		this.key = key;
	}

	public String getMobileType() {
		return mobileType;
	}

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	public Integer getMobilePin() {
		return mobilePin;
	}

	public void setMobilePin(Integer mobilePin) {
		this.mobilePin = mobilePin;
	}

	public Integer getMobileVerificationAttempts() {
		return mobileVerificationAttempts;
	}

	public void setMobileVerificationAttempts(Integer mobileVerificationAttempts) {
		this.mobileVerificationAttempts = mobileVerificationAttempts;
	}

	public Integer getResendCount() {
		return resendCount;
	}

	public void setResendCount(Integer resendCount) {
		this.resendCount = resendCount;
	}

	
	
	public Date getInsertTime() {
		return insertTime;
	}



	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}



	public Date getUpdateTime() {
		return updateTime;
	}



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
		return "MobileVerificationDetails [key=" + key + ", mobileType="
				+ mobileType + ", mobilePin=" + mobilePin
				+ ", mobileVerificationAttempts=" + mobileVerificationAttempts
				+ ", resendCount=" + resendCount + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", updatedBy=" + updatedBy
				+ "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result
				+ ((mobilePin == null) ? 0 : mobilePin.hashCode());
		result = prime * result
				+ ((mobileType == null) ? 0 : mobileType.hashCode());
		result = prime
				* result
				+ ((mobileVerificationAttempts == null) ? 0
						: mobileVerificationAttempts.hashCode());
		result = prime * result
				+ ((resendCount == null) ? 0 : resendCount.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
		MobileVerificationDetails other = (MobileVerificationDetails) obj;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (insertTime.getTime()-other.insertTime.getTime()>1000000)//(!insertTime.equals(other.insertTime))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (mobilePin == null) {
			if (other.mobilePin != null)
				return false;
		} else if (!mobilePin.equals(other.mobilePin))
			return false;
		if (mobileType == null) {
			if (other.mobileType != null)
				return false;
		} else if (!mobileType.equals(other.mobileType))
			return false;
		if (mobileVerificationAttempts == null) {
			if (other.mobileVerificationAttempts != null)
				return false;
		} else if (!mobileVerificationAttempts
				.equals(other.mobileVerificationAttempts))
			return false;
		if (resendCount == null) {
			if (other.resendCount != null)
				return false;
		} else if (!resendCount.equals(other.resendCount))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (Math.abs(updateTime.getTime()-other.updateTime.getTime())>100000)//updateTime.equals(other.updateTime)
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}



	@JsonIgnore
	@Override
	public ID getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@JsonIgnore
	private @Transient boolean isNew = true;

	  @Override
	  @JsonIgnore
	  public boolean isNew() {
	    return isNew;
	  }

	  @PostPersist
	  @PostLoad
	  void markNotNew() {
	    this.isNew = false;
	  }
	  
		/*
		@PrePersist
		  void initIdentifier() {

		    if (key == null) {
		      this.key = new MobileVerificationKey();
		    }
		  }
		*/
		
		
}

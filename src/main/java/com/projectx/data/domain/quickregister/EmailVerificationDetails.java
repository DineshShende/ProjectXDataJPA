package com.projectx.data.domain.quickregister;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="EMAILVERIFICATIONDETAILS")
public class EmailVerificationDetails <ID extends Serializable> implements Persistable<ID> {

	@EmbeddedId
	private EmailVerificationKey key;
	
	@Column(name="EMAILTYPE")
	private Integer emailType;
	
	@Column(name="EMAILHASH")
	private String emailHash;
	
	@Column(name="EMAILHASHSENTTIME")
	private Date emailHashSentTime;
	
	@Column(name="RESENDCOUNT")
	private Integer resendCount;
	
	@Column(name="INSERTIME")
	private Date insertTime;
	
	@Column(name="UPDATETIME")
	private Date updateTime;
	
	@Column(name="UPDATEDBY")
	private String updatedBy;
	


	public EmailVerificationDetails() {

	}



	public EmailVerificationDetails(EmailVerificationKey key, Integer emailType,
			String emailHash, Date emailHashSentTime, Integer resendCount,
			Date insertTime, Date updateTime, String updatedBy) {
		this.key = key;
		this.emailType = emailType;
		this.emailHash = emailHash;
		this.emailHashSentTime = emailHashSentTime;
		this.resendCount = resendCount;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
	}



	public EmailVerificationKey getKey() {
		return key;
	}



	public void setKey(EmailVerificationKey key) {
		this.key = key;
	}



	public Integer getEmailType() {
		return emailType;
	}



	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}



	public String getEmailHash() {
		return emailHash;
	}



	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}



	public Date getEmailHashSentTime() {
		return emailHashSentTime;
	}



	public void setEmailHashSentTime(Date emailHashSentTime) {
		this.emailHashSentTime = emailHashSentTime;
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
		return "EmailVerificationDetails [key=" + key + ", emailType="
				+ emailType + ", emailHash=" + emailHash
				+ ", emailHashSentTime=" + emailHashSentTime + ", resendCount="
				+ resendCount + ", insertTime=" + insertTime + ", updateTime="
				+ updateTime + ", updatedBy=" + updatedBy + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emailHash == null) ? 0 : emailHash.hashCode());
		result = prime
				* result
				+ ((emailHashSentTime == null) ? 0 : emailHashSentTime
						.hashCode());
		result = prime * result
				+ ((emailType == null) ? 0 : emailType.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		EmailVerificationDetails other = (EmailVerificationDetails) obj;
		if (emailHash == null) {
			if (other.emailHash != null)
				return false;
		} else if (!emailHash.equals(other.emailHash))
			return false;
		if (emailHashSentTime == null) {
			if (other.emailHashSentTime != null)
				return false;
		} else if (!emailHashSentTime.equals(other.emailHashSentTime))
			return false;
		if (emailType == null) {
			if (other.emailType != null)
				return false;
		} else if (!emailType.equals(other.emailType))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (resendCount == null) {
			if (other.resendCount != null)
				return false;
		} else if (!resendCount.equals(other.resendCount))
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
		return true;
	}

	@Override
	@JsonIgnore
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
	
	
}

package com.projectx.data.domain.quickregister;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.data.util.serializer.JsonDateDeSerializer;
import com.projectx.data.util.serializer.JsonDateSerializer;

@Entity
@Table(name="EMAILVERIFICATIONDETAILS")
public class EmailVerificationDetails  {

	@EmbeddedId
	private EmailVerificationKey key;
	
	@NotNull
	@Column(name="EMAIL",unique=true)
	private String email;
	
	@Column(name="EMAILHASH")
	private String emailHash;
	
	@Column(name="EMAILHASHSENTTIME")
	private Date emailHashSentTime;
	
	@NotNull
	@Column(name="RESENDCOUNT")
	private Integer resendCount;
	
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

	


	public EmailVerificationDetails() {

	}

	public EmailVerificationDetails(EmailVerificationKey key, String email,
			String emailHash, Date emailHashSentTime, Integer resendCount,
			Date insertTime, Date updateTime, Integer updatedBy,
			Integer insertedBy,Long updatedById,Long insertedById) {
		super();
		this.key = key;
		this.email = email;
		this.emailHash = emailHash;
		this.emailHashSentTime = emailHashSentTime;
		this.resendCount = resendCount;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
		this.insertedBy=insertedBy;
		this.updatedById=updatedById;
		this.insertedById=insertedById;
	}


	public EmailVerificationKey getKey() {
		return key;
	}



	public void setKey(EmailVerificationKey key) {
		this.key = key;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailHash() {
		return emailHash;
	}



	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}


	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getEmailHashSentTime() {
		return emailHashSentTime;
	}


	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setEmailHashSentTime(Date emailHashSentTime) {
		this.emailHashSentTime = emailHashSentTime;
	}



	public Integer getResendCount() {
		return resendCount;
	}



	public void setResendCount(Integer resendCount) {
		this.resendCount = resendCount;
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
		return "EmailVerificationDetails [key=" + key + ", email=" + email
				+ ", emailHash=" + emailHash + ", emailHashSentTime="
				+ emailHashSentTime + ", resendCount=" + resendCount
				+ ", insertTime=" + insertTime + ", updateTime=" + updateTime
				+ ", updatedBy=" + updatedBy + ", insertedBy=" + insertedBy
				+ ", updatedById=" + updatedById + ", insertedById="
				+ insertedById + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((emailHash == null) ? 0 : emailHash.hashCode());
		result = prime
				* result
				+ ((emailHashSentTime == null) ? 0 : emailHashSentTime
						.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((insertedBy == null) ? 0 : insertedBy.hashCode());
		result = prime * result
				+ ((insertedById == null) ? 0 : insertedById.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result
				+ ((resendCount == null) ? 0 : resendCount.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
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
		return true;
	}


	
	
	/*
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
	*/
	
}

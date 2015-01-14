package com.projectx.data.domain.quickregister;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.data.util.serializer.*;

@Entity
@Table(name="AUTHENTICATIONDETAILS")
public class AuthenticationDetails  {

	@EmbeddedId
	private AuthenticationDetailsKey key;
	
	@Column(name="EMAIL",unique=true)
	private String email;
	
	@Column(name="MOBILE",unique=true)
	private Long mobile;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="PASSWORDTYPE")
	private String passwordType;
	
	@Column(name="EMAILPASSWORD")
	private String emailPassword;
	
	@Column(name="RESENDCOUNT")
	private Integer resendCount;
	
	@Column(name="LASTUNSUCESSFULLATTEMPTS")
	private Integer lastUnsucessfullAttempts;

	
	@Column(name="INSERTTIME")
	private Date insertTime;
	
	
	@Column(name="UPDATETIME")
	private Date UpdateTime;
	
	
	@Column(name="UPDATEDBY")
	private String updatedBy;


	public AuthenticationDetails() {

	}


	public AuthenticationDetails(AuthenticationDetailsKey key, String email,
			Long mobile, String password, String passwordType,
			String emailPassword, Integer resendCount,
			Integer lastUnsucessfullAttempts, Date insertTime, Date updateTime,
			String updatedBy) {
		super();
		this.key = key;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.passwordType = passwordType;
		this.emailPassword = emailPassword;
		this.resendCount = resendCount;
		this.lastUnsucessfullAttempts = lastUnsucessfullAttempts;
		this.insertTime = insertTime;
		UpdateTime = updateTime;
		this.updatedBy = updatedBy;
	}


	public AuthenticationDetailsKey getKey() {
		return key;
	}


	public void setKey(AuthenticationDetailsKey key) {
		this.key = key;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getMobile() {
		return mobile;
	}


	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordType() {
		return passwordType;
	}


	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}


	public String getEmailPassword() {
		return emailPassword;
	}


	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}


	public Integer getResendCount() {
		return resendCount;
	}


	public void setResendCount(Integer resendCount) {
		this.resendCount = resendCount;
	}


	public Integer getLastUnsucessfullAttempts() {
		return lastUnsucessfullAttempts;
	}


	public void setLastUnsucessfullAttempts(Integer lastUnsucessfullAttempts) {
		this.lastUnsucessfullAttempts = lastUnsucessfullAttempts;
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
		return UpdateTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	@Override
	public String toString() {
		return "AuthenticationDetails [key=" + key + ", email=" + email
				+ ", mobile=" + mobile + ", password=" + password
				+ ", passwordType=" + passwordType + ", emailPassword="
				+ emailPassword + ", resendCount=" + resendCount
				+ ", lastUnsucessfullAttempts=" + lastUnsucessfullAttempts
				+ ", insertTime=" + insertTime + ", UpdateTime=" + UpdateTime
				+ ", updatedBy=" + updatedBy + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((UpdateTime == null) ? 0 : UpdateTime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((emailPassword == null) ? 0 : emailPassword.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime
				* result
				+ ((lastUnsucessfullAttempts == null) ? 0
						: lastUnsucessfullAttempts.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((passwordType == null) ? 0 : passwordType.hashCode());
		result = prime * result
				+ ((resendCount == null) ? 0 : resendCount.hashCode());
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
		AuthenticationDetails other = (AuthenticationDetails) obj;
		if (UpdateTime == null) {
			if (other.UpdateTime != null)
				return false;
		} else if (!UpdateTime.equals(other.UpdateTime))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailPassword == null) {
			if (other.emailPassword != null)
				return false;
		} else if (!emailPassword.equals(other.emailPassword))
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
		if (lastUnsucessfullAttempts == null) {
			if (other.lastUnsucessfullAttempts != null)
				return false;
		} else if (!lastUnsucessfullAttempts
				.equals(other.lastUnsucessfullAttempts))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordType == null) {
			if (other.passwordType != null)
				return false;
		} else if (!passwordType.equals(other.passwordType))
			return false;
		if (resendCount == null) {
			if (other.resendCount != null)
				return false;
		} else if (!resendCount.equals(other.resendCount))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}


				
}

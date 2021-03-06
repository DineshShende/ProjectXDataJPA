package com.projectx.rest.domain.quickregister;

import javax.validation.constraints.NotNull;

public class UpdatePasswordEmailPasswordAndPasswordTypeDTO {

	private Long customerId;
	
	private Integer customerType;
	
	
	private String password;
	
	
	private String emailPassword;
	
	@NotNull
	private String passwordType;
	
	@NotNull
	private Integer updatedBy;
	
	@NotNull
	private Long updatedById;
	
	
	public UpdatePasswordEmailPasswordAndPasswordTypeDTO() {
		
	}

	public UpdatePasswordEmailPasswordAndPasswordTypeDTO(Long customerId,
			Integer customerType, String password, String emailPassword,
			String passwordType, Integer updatedBy,Long updatedById) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.password = password;
		this.emailPassword = emailPassword;
		this.passwordType = passwordType;
		this.updatedBy = updatedBy;
		this.updatedById=updatedById;
	}





	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	
	public Integer getCustomerType() {
		return customerType;
	}



	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	

	public Long getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}

	@Override
	public String toString() {
		return "UpdatePasswordEmailPasswordAndPasswordTypeDTO [customerId="
				+ customerId + ", customerType=" + customerType + ", password="
				+ password + ", emailPassword=" + emailPassword
				+ ", passwordType=" + passwordType + ", updatedBy=" + updatedBy
				+ ", updatedById=" + updatedById + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result
				+ ((emailPassword == null) ? 0 : emailPassword.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((passwordType == null) ? 0 : passwordType.hashCode());
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
		UpdatePasswordEmailPasswordAndPasswordTypeDTO other = (UpdatePasswordEmailPasswordAndPasswordTypeDTO) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerType == null) {
			if (other.customerType != null)
				return false;
		} else if (!customerType.equals(other.customerType))
			return false;
		if (emailPassword == null) {
			if (other.emailPassword != null)
				return false;
		} else if (!emailPassword.equals(other.emailPassword))
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

			
}

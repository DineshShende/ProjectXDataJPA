package com.projectx.data.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerQuickRegisterKey implements Serializable{
	
	private String emaill;
	
	
	private Long mobile;
	
	public CustomerQuickRegisterKey()
	{}

	public String getEmaill() {
		return emaill;
	}

	public void setEmaill(String emaill) {
		this.emaill = emaill;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public CustomerQuickRegisterKey(String emaill, Long mobile) {
		this.emaill = emaill;
		this.mobile = mobile;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emaill == null) ? 0 : emaill.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
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
		CustomerQuickRegisterKey other = (CustomerQuickRegisterKey) obj;
		if (emaill == null) {
			if (other.emaill != null)
				return false;
		} else if (!emaill.equals(other.emaill))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}
	
	

}

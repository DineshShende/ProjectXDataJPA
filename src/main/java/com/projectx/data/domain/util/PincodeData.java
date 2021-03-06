package com.projectx.data.domain.util;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PincodeData")
public class PincodeData {

	@Id
	private Integer pincode;
	
	private String officeName;
	
	private String Taluk;
	
	private String district;
	
	private String state;
	
	private String latitude;
	
	private String longitude;

	public PincodeData() {
		super();
	}

	

	public PincodeData(Integer pincode, String officeName, String taluk,
			String district, String state, String latitude, String longitude) {
		super();
		this.pincode = pincode;
		this.officeName = officeName;
		Taluk = taluk;
		this.district = district;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
	}



	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getTaluk() {
		return Taluk;
	}

	public void setTaluk(String taluk) {
		Taluk = taluk;
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
	
	

	public String getLatitude() {
		return latitude;
	}



	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}



	public String getLongitude() {
		return longitude;
	}



	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}



	@Override
	public String toString() {
		return "PincodeData [pincode=" + pincode + ", officeName=" + officeName
				+ ", Taluk=" + Taluk + ", district=" + district + ", state="
				+ state + ", latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Taluk == null) ? 0 : Taluk.hashCode());
		result = prime * result
				+ ((district == null) ? 0 : district.hashCode());
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result
				+ ((officeName == null) ? 0 : officeName.hashCode());
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		PincodeData other = (PincodeData) obj;
		if (Taluk == null) {
			if (other.Taluk != null)
				return false;
		} else if (!Taluk.equals(other.Taluk))
			return false;
		if (district == null) {
			if (other.district != null)
				return false;
		} else if (!district.equals(other.district))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (officeName == null) {
			if (other.officeName != null)
				return false;
		} else if (!officeName.equals(other.officeName))
			return false;
		if (pincode == null) {
			if (other.pincode != null)
				return false;
		} else if (!pincode.equals(other.pincode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}


	
	
	
	
}

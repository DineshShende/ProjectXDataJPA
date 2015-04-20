package com.projectx.rest.domain.quickregister;

public class ResponseDTO {
	
	private String errorMessage;

	public ResponseDTO() {
		super();
	}

	
	
	public ResponseDTO(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}





	public String getErrorMessage() {
		return errorMessage;
	}



	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

	@Override
	public String toString() {
		return "ResponseDTO [errorMessage=" + errorMessage + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((errorMessage == null) ? 0 : errorMessage.hashCode());
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
		ResponseDTO other = (ResponseDTO) obj;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		return true;
	}


	
	

}

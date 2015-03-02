package com.projectx.data.exception.request;

public class VehicleDetailsNotFoundException extends RuntimeException {

	public VehicleDetailsNotFoundException(Throwable cause) {
        super(cause);
    }

    public VehicleDetailsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehicleDetailsNotFoundException(String message) {
        super(message);
    }

    public VehicleDetailsNotFoundException() {
    }

	
}

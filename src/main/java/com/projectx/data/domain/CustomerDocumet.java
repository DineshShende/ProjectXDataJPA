package com.projectx.data.domain;

import java.io.IOException;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class CustomerDocumet {

	@Id
	private Long customerId;
	
	@Lob
	private byte[] image;

	public CustomerDocumet() {

	}

	public CustomerDocumet(Long customerId, byte[] image) {
		super();
		this.customerId = customerId;
		this.image = image;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "CustomerDocumet [customerId=" + customerId + ", image="
				+ Arrays.toString(image) + "]";
	}

	
	
	/*
	private static byte[] writtingImage(String fileLocation) {
	      System.out.println("file lication is"+fileLocation);
	     IOManager manager=new IOManager();
	        try {
	           return manager.getBytesFromFile(fileLocation);
	            
	        } catch (IOException e) {
	        }
	        return null;
	    }
	*/
}

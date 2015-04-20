package com.projectx.data.domain.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TestChild {
	
	
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private Long childId;
	
	private String childName;

	public TestChild() {
		super();
	}

	
	
	public TestChild(String childName) {
		super();
		this.childName = childName;
	}


/*
	public TestChild(Long childId, String childName) {
		super();
		this.childId = childId;
		this.childName = childName;
	}

*/	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	

}

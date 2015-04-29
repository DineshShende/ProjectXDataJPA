package com.projectx.data.domain.test;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//@Entity
public class TestParent {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long parentId;
	
	private String parentName;
	
	@OneToOne(cascade=CascadeType.ALL)
	private TestChild testChild;

	
	
	public TestParent() {
		super();
	}
	
	

	public TestParent(Long parentId, String parentName, TestChild testChild) {
		super();
		this.parentId = parentId;
		this.parentName = parentName;
		this.testChild = testChild;
	}



	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public TestChild getTestChild() {
		return testChild;
	}

	public void setTestChild(TestChild testChild) {
		this.testChild = testChild;
	}

	
	
	
}

package com.projectx.data.domain.test;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//@Embeddable
public class TestChildId implements Serializable{

	@OneToOne
	@JoinColumn(name="TESTPARENTID")
	private TestParent testParentId;
	
	@Column(name="TESTTYPE")
	private Integer testType;
	
	@Column(name="ENTITYTYPE")
	private Integer entityType;

	
	
	public TestChildId() {
		super();
	}



	public TestChildId(TestParent testParentId, Integer testType,
			Integer entityType) {
		super();
		this.testParentId = testParentId;
		this.testType = testType;
		this.entityType = entityType;
	}



	public TestParent getTestParentId() {
		return testParentId;
	}



	public void setTestParentId(TestParent testParentId) {
		this.testParentId = testParentId;
	}



	public Integer getTestType() {
		return testType;
	}



	public void setTestType(Integer testType) {
		this.testType = testType;
	}



	public Integer getEntityType() {
		return entityType;
	}



	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}



	@Override
	public String toString() {
		return "TestChildId [testParentId=" + testParentId + ", testType="
				+ testType + ", entityType=" + entityType + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entityType == null) ? 0 : entityType.hashCode());
		result = prime * result
				+ ((testParentId == null) ? 0 : testParentId.hashCode());
		result = prime * result
				+ ((testType == null) ? 0 : testType.hashCode());
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
		TestChildId other = (TestChildId) obj;
		if (entityType == null) {
			if (other.entityType != null)
				return false;
		} else if (!entityType.equals(other.entityType))
			return false;
		if (testParentId == null) {
			if (other.testParentId != null)
				return false;
		} else if (!testParentId.equals(other.testParentId))
			return false;
		if (testType == null) {
			if (other.testType != null)
				return false;
		} else if (!testType.equals(other.testType))
			return false;
		return true;
	}
	
	
	
}

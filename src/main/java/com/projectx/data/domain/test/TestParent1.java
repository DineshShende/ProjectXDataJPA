package com.projectx.data.domain.test;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//@Entity
public class TestParent1 implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	String name;
	
	@OneToOne(mappedBy="id",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private TestChild1 testChild1;

	
	
	public TestParent1() {
		super();
	}



	public TestParent1(String name, TestChild1 testChild1) {
		super();
		this.id = id;
		this.name = name;
		this.testChild1 = testChild1;
	}

	


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public TestChild1 getTestChild1() {
		return testChild1;
	}



	public void setTestChild1(TestChild1 testChild1) {
		this.testChild1 = testChild1;
	}

	

}

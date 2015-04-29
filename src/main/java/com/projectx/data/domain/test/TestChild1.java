package com.projectx.data.domain.test;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//@Entity
public class TestChild1  implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long childId;
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn
	private TestParent1 id;
	
	private String childName;

	
	
	public TestChild1() {
		super();
	}



	public TestChild1(TestParent1 id, String childName) {
		super();
		this.id = id;
		this.childName = childName;
	}



	public TestParent1 getId() {
		return id;
	}



	public void setId(TestParent1 id) {
		this.id = id;
	}



	public String getChildName() {
		return childName;
	}



	public void setChildName(String childName) {
		this.childName = childName;
	}



}

package com.projectx.data.repository;

import java.util.List;

import com.projectx.data.domain.Email;


public interface EmailRepository {

	public Email findByEmail(String lastName);
	
	public Email save(Email email);
	
	public Email findOne (String email);

	public List<Email> findAll();
	
	public boolean exists(String email);
}
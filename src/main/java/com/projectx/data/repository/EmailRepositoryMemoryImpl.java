package com.projectx.data.repository;

import java.util.List;

import org.springframework.context.annotation.Profile;

import com.projectx.data.domain.Email;

@Profile("Test12")
public class EmailRepositoryMemoryImpl implements EmailRepository{

	
	@Override
	public Email findByEmail(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Email save(Email email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Email findOne(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Email> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}

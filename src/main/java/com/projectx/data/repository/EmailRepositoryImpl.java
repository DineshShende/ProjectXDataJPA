package com.projectx.data.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import com.projectx.data.domain.Email;


@Profile(value={"Test","Prod"})
public interface EmailRepositoryImpl extends CrudRepository<Email, String>, EmailRepository {
}

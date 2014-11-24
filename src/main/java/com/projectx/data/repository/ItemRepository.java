package com.projectx.data.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.projectx.data.domain.Item;

public interface ItemRepository extends CrudRepository<Item, UUID> {

}

package com.projectx.data.repository.quickregister;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.projectx.data.domain.completeregister.Item;

public interface ItemRepository extends CrudRepository<Item, UUID> {

}

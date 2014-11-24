package com.projectx.data.repository;

import static org.junit.Assert.*;

import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.config.Application;
import com.projectx.data.domain.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@Transactional
public class ItemRepositoryTest {

	@Autowired
	ItemRepository itemRepository;
	
	@Test
	public void environmetTest() {
		

	}
	
	@Test
	
	public void addItem()
	{
		UUID itemId=UUID.randomUUID();
		
		System.out.println(itemId.getLeastSignificantBits());
		System.out.println(itemId.getMostSignificantBits());
		System.out.println(itemId);
		
		
		Item item=new Item(itemId, "First");
		
		System.out.println(itemId.toString());
		
		itemRepository.save(item);
		
		assertEquals(1, itemRepository.count());

		System.out.println(itemRepository.findAll());
		
		System.out.println(itemRepository.findOne(itemId));
		
				
	}

}

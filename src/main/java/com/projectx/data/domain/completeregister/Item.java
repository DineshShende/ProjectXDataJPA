package com.projectx.data.domain.completeregister;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {

	@Id 
	@Column(name="ITEMID",columnDefinition="BINARY(16)")
	private UUID itemId;
	
	@Column(name="ITEMNAME")
	private String itemName;

	public Item() {

	}

	public Item(UUID itemId, String itemName) {
		this.itemId = itemId;
		this.itemName = itemName;
	}

	public UUID getItemId() {
		return itemId;
	}

	public void setItemId(UUID itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + "]";
	}

		
	
}

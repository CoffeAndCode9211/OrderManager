package com.omnicuris.cme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnicuris.cme.entity.Item;
import com.omnicuris.cme.service.ItemService;
import com.omnicuris.cme.utils.Status;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping
	public List<Item> getAllActiveItems() {
		return itemService.findItemByStatus(Status.ACTIVE);
	}

}

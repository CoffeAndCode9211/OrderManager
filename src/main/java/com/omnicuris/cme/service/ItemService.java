package com.omnicuris.cme.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.omnicuris.cme.dao.ItemRepository;
import com.omnicuris.cme.entity.Item;
import com.omnicuris.cme.utils.ResponseStatus;
import com.omnicuris.cme.utils.Status;
import com.omnicuris.cme.utils.Utils;

@Service
public class ItemService {

	private ItemRepository itemRepository;

	public ItemService(ItemRepository orderRepository) {
		this.itemRepository = orderRepository;
	}

	public List<Item> findItemByStatus(Status status) {
		return itemRepository.findItemByStatus(status);
	}

	public ResponseStatus saveItem(Item item) {
		ResponseStatus status = new ResponseStatus();
		try {
			if (itemRepository.save(item) != null) {
				status.setMsg(Utils.SUCCESS_MSG);
				status.setStatus(true);
			} else {
				status.setMsg(Utils.ERROR_MSG);
				status.setStatus(false);
			}
		} catch (Exception e) {
			status.setMsg(e.getMessage());
			status.setStatus(false);
		}
		return status;
	}

}

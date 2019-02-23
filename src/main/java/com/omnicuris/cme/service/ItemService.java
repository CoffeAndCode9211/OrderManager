package com.omnicuris.cme.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnicuris.cme.dao.ItemRepository;
import com.omnicuris.cme.entity.Item;
import com.omnicuris.cme.utils.ResponseStatus;
import com.omnicuris.cme.utils.Status;
import com.omnicuris.cme.utils.Utils;

@Service
@Transactional
public class ItemService {

	private static final Logger LOG = LoggerFactory.getLogger(ItemService.class);

	private ItemRepository itemRepository;

	public ItemService(ItemRepository orderRepository) {
		this.itemRepository = orderRepository;
	}

	public List<Item> findItemByStatus(Status status) {
		return itemRepository.findItemByStatus(status);
	}

	public Item findItemById(long id) {
		Optional<Item> itemOpt = itemRepository.findById(id);
		if (itemOpt.isPresent()) {
			return itemOpt.get();
		}
		return null;
	}

	public ResponseStatus saveItem(Item item) {
		ResponseStatus status = new ResponseStatus();
		try {
			if(item.getStatus() == null){
				item.setStatus(Status.ACTIVE);
			}
			if (itemRepository.save(item) != null) {
				status.setMsg(Utils.SAVE_MSG);
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
	
	public ResponseStatus updateItem(Item item) {
		ResponseStatus status = new ResponseStatus();
		try {
			if(item.getStatus() == null){
				item.setStatus(Status.ACTIVE);
			}
			if (itemRepository.save(item) != null) {
				status.setMsg(Utils.UPDATE_MSG);
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

	public ResponseStatus deleteItem(long itemId) {
		ResponseStatus status = new ResponseStatus("Error", false);
		try {
			Optional<Item> itemOpt = itemRepository.findById(itemId);
			if (itemOpt.isPresent()) {
				Item item = itemOpt.get();
				item.setStatus(Status.INACTIVE);
				if (itemRepository.save(item) != null) {
					status.setMsg(Utils.DELETE_MSG);
					status.setStatus(true);
				}
			}else{
				status.setMsg("Item not found");
				status.setStatus(false);
			}
		} catch (Exception e) {
			LOG.error("Error:", e);
			status.setMsg(e.getMessage());
			status.setStatus(false);
		}
		return status;
	}

}

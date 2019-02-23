package com.omnicuris.cme.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnicuris.cme.entity.Item;
import com.omnicuris.cme.entity.Order;
import com.omnicuris.cme.service.ItemService;
import com.omnicuris.cme.service.OrderService;
import com.omnicuris.cme.utils.ResponseStatus;
import com.omnicuris.cme.utils.Status;

@RestController
@RequestMapping(value = "/item", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private OrderService orderService;

	/**
	 * Get All Active items
	 * @return List<Item>
	 */
	@GetMapping
	public List<Item> getAllActiveItems() {
		return itemService.findItemByStatus(Status.ACTIVE);
	}

	/**
	 * Save an Item 
	 * @param item
	 * @return Return HTTP status with msg
	 */
	@PostMapping
	public ResponseEntity<Object> saveItem(@Valid @RequestBody Item item) {
		ResponseStatus status = itemService.saveItem(item);
		if (status != null && status.isStatus()) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Update an Item 
	 * @param item
	 * @return Return HTTP status with msg
	 */
	@PutMapping
	public ResponseEntity<Object> updateItem(@Valid @RequestBody Item item) {
		ResponseStatus status = itemService.updateItem(item);
		if (status != null && status.isStatus()) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Delete an Item if there is no active Order for this Item
	 * @param itemId
	 * @return Return HTTP status with msg
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteitem(@PathVariable("id") long itemId) {
		// check for any active Order for this Item before deleting
		List<Order> lstOrder = orderService.findOrderByItemId(itemId);
		if (lstOrder != null && !lstOrder.isEmpty()) {
			ResponseStatus status = new ResponseStatus();
			status.setMsg("Active order exist for this Item. Delete is not allowed.");
			return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
		}

		ResponseStatus status = itemService.deleteItem(itemId);
		if (status != null && status.isStatus()) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}

}

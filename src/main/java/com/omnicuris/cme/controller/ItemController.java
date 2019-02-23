package com.omnicuris.cme.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnicuris.cme.entity.Item;
import com.omnicuris.cme.service.ItemService;
import com.omnicuris.cme.utils.ResponseStatus;
import com.omnicuris.cme.utils.Status;

@RestController
@RequestMapping(value = "/item", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping
	public List<Item> getAllActiveItems() {
		return itemService.findItemByStatus(Status.ACTIVE);
	}

	@PostMapping
	public ResponseEntity<Object> saveItem(@Valid @RequestBody Item item) {
		ResponseStatus status = itemService.saveItem(item);
		if (status != null && status.isStatus()) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public ResponseEntity<Object> updateItem(@Valid @RequestBody Item item) {
		ResponseStatus status = itemService.updateItem(item);
		if (status != null && status.isStatus()) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteitem(@PathVariable("id") long id) {
		ResponseStatus status = itemService.deleteItem(id);
		if (status != null && status.isStatus()) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}


}

package com.omnicuris.cme.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnicuris.cme.entity.Order;
import com.omnicuris.cme.service.OrderService;
import com.omnicuris.cme.utils.ResponseStatus;
import com.omnicuris.cme.utils.Status;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<Order> getAllActiveOrders() {
		return orderService.findOrderByStatus(Status.ACTIVE);
	}

	@PostMapping
	public ResponseEntity<Object> saveOrder(@Valid @RequestBody Order order) {
		ResponseStatus status = orderService.saveOrder(order);
		if (status != null && status.isStatus()) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable("id") long id) {
		ResponseStatus status = orderService.deleteOrder(id);
		if (status != null && status.isStatus()) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}

}

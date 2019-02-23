package com.omnicuris.cme.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omnicuris.cme.entity.Order;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

	/*
	*//**
	 * HTTP Rest API for searching Bank Detail from Elastic Server
	 * @return List<Bank> max 10 records
	 *//*
	@RequestMapping
	public List<Order> getAllBanks() {
		return bankService.getAllBanks();
	}

	*//**
	 * HTTP Rest API for searching Bank Detail on selected field and text provided
	 * @param key
	 * @param text
	 * @return List<Bank> max 10 records
	 *//*
	@RequestMapping("/search")
	public List<Order> getBandDetailByField(@RequestParam("key") BankEnum key, @RequestParam("text") String text) {
		return bankService.getBandDetailByFieldAndText(key, text);
	}
*/
}

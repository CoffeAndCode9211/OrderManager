package com.omnicuris.cme.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnicuris.cme.dao.OrderRepository;
import com.omnicuris.cme.entity.Order;
import com.omnicuris.cme.utils.ResponseStatus;
import com.omnicuris.cme.utils.Utils;

@Service
@Transactional
public class OrderService {

	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> query(String status) {
		return orderRepository.findOrderByStatus(status);
	}

	public ResponseStatus saveOrder(Order order) {
		ResponseStatus status = new ResponseStatus();
		try {
			if (orderRepository.save(order) != null) {
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

package com.omnicuris.cme.dao;

import java.util.List;

import com.omnicuris.cme.entity.Order;

public interface OrderRepositoryCustom  {

	List<Order> findOrderByStatus(String status);
	
}

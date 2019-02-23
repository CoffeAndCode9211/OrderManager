package com.omnicuris.cme.dao;

import java.util.List;

import com.omnicuris.cme.entity.Order;
import com.omnicuris.cme.utils.Status;

public interface OrderRepositoryCustom  {

	List<Order> findOrderByStatus(Status status);
	
	List<Order> findOrderByItemId(long itemId);
	
}

package com.omnicuris.cme.dao;

import java.util.List;

import com.omnicuris.cme.entity.Order;
import com.omnicuris.cme.utils.Status;

public interface OrderRepositoryCustom  {

	/**
	 * Get order by status
	 * @param status
	 * @return List<Order>
	 */
	List<Order> findOrderByStatus(Status status);
	
	/**
	 * Get Order by ItemId
	 * @param status
	 * @return List<Order>
	 */
	List<Order> findOrderByItemId(long itemId);
	
}

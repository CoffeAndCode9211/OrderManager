package com.omnicuris.cme.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omnicuris.cme.dao.OrderRepository;
import com.omnicuris.cme.entity.Item;
import com.omnicuris.cme.entity.Order;
import com.omnicuris.cme.utils.ResponseStatus;
import com.omnicuris.cme.utils.Status;
import com.omnicuris.cme.utils.Utils;

@Service
@Transactional
public class OrderService {

	private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Autowired
	private ItemService itemService;

	public List<Order> findOrderByStatus(Status status) {
		return orderRepository.findOrderByStatus(status);
	}

	public Order findOrderById(long id) {
		Optional<Order> OrderOpt = orderRepository.findById(id);
		if (OrderOpt.isPresent()) {
			return OrderOpt.get();
		}
		return null;
	}

	public List<Order> findOrderByItemId(long itemId) {
		List<Order> lstOfActvOrder = orderRepository.findOrderByItemId(itemId);
		return lstOfActvOrder;
	}

	/**
	 * Save an Order and update item accordingly
	 * @param order
	 * @return Status with msg
	 */
	public ResponseStatus saveOrder(Order order) {
		ResponseStatus status = new ResponseStatus();
		try {

			Item item = itemService.findItemById(order.getItemId().getId());
			int qtyleft = item.getQty();
			int qtyOrdered = order.getQty();
			if (qtyleft > 0 && qtyleft >= qtyOrdered) {
				double totPrice = Utils.round(qtyOrdered * item.getPrice(), 2);
				order.setTotalAmt(totPrice);

				item.setQty((qtyleft - qtyOrdered));
			} else if (qtyleft <= 0) {
				status.setMsg("Ordered item is Out of Stock");
				status.setStatus(false);
				return status;
			} else {
				status.setMsg("We have " + qtyleft + " pieces available in stock.");
				status.setStatus(false);
				return status;
			}

			if (order.getStatus() == null) {
				order.setStatus(Status.ACTIVE);
			}
			if (orderRepository.save(order) != null) {

				itemService.saveItem(item);

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

	/**
	 * Delete order and Update Item accordingly
	 * @param orderId
	 * @return Status with msg
	 */
	public ResponseStatus deleteOrder(long orderId) {
		ResponseStatus status = new ResponseStatus("Error", false);
		try {
			Optional<Order> orderOpt = orderRepository.findById(orderId);
			if (orderOpt.isPresent()) {
				Order order = orderOpt.get();
				if (order.getStatus().equals(Status.INACTIVE)) {
					status.setMsg("Item not found");
					status.setStatus(false);
					return status;
				}
				LOG.info("Orderr status:" + order.getStatus());
				Item item = itemService.findItemById(order.getItemId().getId());
				int qtyleft = item.getQty();
				int qtyOrdered = order.getQty();
				int newItemQty = qtyleft + qtyOrdered;

				order.setStatus(Status.INACTIVE);
				if (orderRepository.save(order) != null) {

					item.setQty(newItemQty);
					itemService.updateItem(item);

					status.setMsg(Utils.DELETE_MSG);
					status.setStatus(true);
				}
			} else {
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

package com.omnicuris.cme.dao;

import java.util.List;

import com.omnicuris.cme.entity.Item;
import com.omnicuris.cme.utils.Status;

public interface ItemRepositoryCustom {

	/**
	 * Get Item by Status
	 * @param status
	 * @return List<Item>
	 */
	List<Item> findItemByStatus(Status status);

}

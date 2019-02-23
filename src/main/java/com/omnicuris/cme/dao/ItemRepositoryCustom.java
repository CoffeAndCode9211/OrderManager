package com.omnicuris.cme.dao;

import java.util.List;

import com.omnicuris.cme.entity.Item;
import com.omnicuris.cme.utils.Status;

public interface ItemRepositoryCustom  {

	List<Item> findItemByStatus(Status status);
	
}

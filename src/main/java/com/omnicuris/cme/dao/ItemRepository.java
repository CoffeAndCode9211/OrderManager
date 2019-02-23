package com.omnicuris.cme.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.omnicuris.cme.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom, JpaSpecificationExecutor<Item> {

}

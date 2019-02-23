package com.omnicuris.cme.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.omnicuris.cme.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom, JpaSpecificationExecutor<Order> {

}

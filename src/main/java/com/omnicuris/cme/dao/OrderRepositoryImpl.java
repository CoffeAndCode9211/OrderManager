package com.omnicuris.cme.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.omnicuris.cme.entity.Item;
import com.omnicuris.cme.entity.Order;
import com.omnicuris.cme.utils.Status;

@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {

	private EntityManager em;

    public OrderRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    
	@Override
	public List<Order> findOrderByStatus(Status status) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

        Root<Order> order = cq.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

        if (status != null) {
            predicates.add(cb.equal(order.get("status"), status));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Order> query = em.createQuery(cq);
        return query.getResultList();
	}
	
	@Override
	public List<Order> findOrderByItemId(long itemId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

        Root<Order> order = cq.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

        if (itemId != 0) {
        	Item item = new Item();
        	item.setId(itemId);
            predicates.add(cb.equal(order.get("itemId"), item));
            predicates.add(cb.equal(order.get("status"), Status.ACTIVE));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Order> query = em.createQuery(cq);
        return query.getResultList();
	}

}

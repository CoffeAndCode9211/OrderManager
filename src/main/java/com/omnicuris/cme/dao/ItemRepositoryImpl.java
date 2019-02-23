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
import com.omnicuris.cme.utils.Status;

@Repository
public class ItemRepositoryImpl implements ItemRepositoryCustom {

	private EntityManager em;

	public ItemRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Item> findItemByStatus(Status status) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Item> cq = cb.createQuery(Item.class);

		Root<Item> item = cq.from(Item.class);
		List<Predicate> predicates = new ArrayList<>();

		if (status != null) {
			predicates.add(cb.equal(item.get("status"), status));
		}
		cq.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Item> query = em.createQuery(cq);
		return query.getResultList();
	}

}

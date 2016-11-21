package org.onbrains.onwork.inf.dataaccess.utils;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

	@Produces
	@PersistenceContext(unitName = "OnWorkPU")
	private EntityManager em;

}
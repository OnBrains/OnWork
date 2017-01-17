package org.onbrains.onwork.inf.dataaccess.utils;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.onbrains.onwork.inf.modelbase.Identifiable;

/**
 * Created on 12.12.2016 16:32.
 *
 * @author Oleg Naumov
 */
@Stateless
public class BusinessObjectRepository<BO extends Identifiable> implements Serializable {

	private static final long serialVersionUID = 1388185703241824468L;

	@Inject
	protected EntityManager em;

	public void create(BO instance) {
		em.persist(instance);
	}

	public BO merge(BO instance) {
		return em.merge(instance);
	}

	public void remove(BO instance) {
		em.remove(instance);
	}

}
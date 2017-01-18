package org.onbrains.onwork.env.day;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.onbrains.onwork.env.day.model.AbstractDayType;
import org.onbrains.onwork.util.Callback;

/**
 * Абстрактная реализация контроллера для справочника "Типов дней"
 *
 * @author Oleg Naumov
 */
public abstract class AbstractDayTypeDirectoryViewController<T extends AbstractDayType> implements Serializable {

	private static final long serialVersionUID = -1830218683559045123L;

	@Inject
	private EntityManager em;

	protected List<T> types;

	public abstract List<T> getTypes();

	public Callback<T> getCallback() {
		return type -> types.add(type);
	}

	@Transactional
	public void remove(T type) {
		types.remove(type);
		em.remove(em.merge(type));
	}

}
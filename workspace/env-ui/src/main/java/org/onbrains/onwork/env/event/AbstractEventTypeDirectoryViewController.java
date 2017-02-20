package org.onbrains.onwork.env.event;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.onbrains.onwork.env.event.model.AbstractEventType;
import org.onbrains.onwork.util.Callback;

/**
 * Абстрактная реализация контроллера для справочника "Типов событий".
 *
 * @author Oleg Naumov
 */
public abstract class AbstractEventTypeDirectoryViewController<T extends AbstractEventType> implements Serializable {

	private static final long serialVersionUID = -4814420804507131623L;

	@Inject
	protected EntityManager em;

	@Inject
	protected EventTypeRepository etr;

	protected List<T> types;

	public abstract List<T> getTypes();

	public Callback<T> getCallback() {
		return (type -> types.add(type));
	}

	@Transactional
	public void remove(T type) {
		types.remove(type);
		try {
			em.remove(em.merge(type));
		} catch (ConcurrentModificationException ex) {
			type.setActive(false);
			em.merge(type);
		}
	}

}
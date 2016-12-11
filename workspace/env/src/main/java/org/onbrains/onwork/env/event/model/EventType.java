package org.onbrains.onwork.env.event.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 * Тип события. Обычные события, которые могут происходить в течении рабочего дня. Они не влияют на отработанное время.
 *
 * @author Oleg Naumov
 */
@Entity
@Access(AccessType.FIELD)
@NamedQuery(name = EventType.FIND_ALL, query = "from EventType")
public class EventType extends AbstractEventType {

	private static final long serialVersionUID = -1895845062215492533L;

	public static final String FIND_ALL = "EventType.findAll";

	protected EventType() {
	}

	public EventType(Long id) {
		super(id);
	}

	@Override
	public boolean influenceOnWorkTime() {
		return false;
	}

}
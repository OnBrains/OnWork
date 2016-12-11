package org.onbrains.onwork.env.event.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 * Событие, время которого идёт в зачёт отработанного времени.
 *
 * @author Oleg Naumov
 */
@Entity
@Access(AccessType.FIELD)
@NamedQuery(name = WorkEventType.FIND_ALL, query = "from WorkEventType")
public class WorkEventType extends AbstractEventType {

	private static final long serialVersionUID = -469601351116218806L;

	public static final String FIND_ALL = "WorkEventType.findAll";

	protected WorkEventType() {
	}

	public WorkEventType(Long id) {
		super(id);
	}

	@Override
	public boolean influenceOnWorkTime() {
		return true;
	}

}
package org.onbrains.onwork.env.event.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 * События с фиксированным временем. В независимости от того, как долго длилось событие, только фиксированное время
 * пойдёт в зачёт отработанного. Примеры такого события: "Обед".
 *
 * @author Oleg Naumov
 */
@Entity
@Access(AccessType.FIELD)
@NamedQuery(name = FixedTimeEventType.FIND_ALL, query = "from FixedTimeEventType")
public class FixedTimeEventType extends AbstractEventType {

	private static final long serialVersionUID = -719580023780371814L;

	public static final String FIND_ALL = "FixedTimeEventType.findAll";

	@Column(name = "fixed_time")
	private Long fixedTime;

	protected FixedTimeEventType() {
	}

	public FixedTimeEventType(Long id) {
		super(id);
	}

	@Override
	public boolean influenceOnWorkTime() {
		return true;
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public Long getFixedTime() {
		return fixedTime;
	}

	public void setFixedTime(Long fixedTime) {
		this.fixedTime = fixedTime;
	}

}
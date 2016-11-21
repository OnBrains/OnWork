package org.onbrains.onwork.env.workday.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.onbrains.onwork.env.day.model.DayType;

/**
 * Created on 13.11.2016 16:11.
 *
 * @author Oleg Naumov
 */
@Entity
@Access(AccessType.FIELD)
public class WorkDayType extends DayType {

	private static final long serialVersionUID = 4101561696210252825L;

	protected WorkDayType() {
	}

	public WorkDayType(Long id) {
		super(id);
	}

}
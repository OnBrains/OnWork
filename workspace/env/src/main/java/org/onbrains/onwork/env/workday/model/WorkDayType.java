package org.onbrains.onwork.env.workday.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.onbrains.onwork.env.day.model.AbstractDayType;

/**
 * Created on 13.11.2016 16:11.
 *
 * @author Oleg Naumov
 */
@Entity
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = WorkDayType.FIND_ALL, query = "from WorkDayType") })
public class WorkDayType extends AbstractDayType {

	private static final long serialVersionUID = 4101561696210252825L;

	public static final String FIND_ALL = "WorkDayType.findAll";

	protected WorkDayType() {
	}

	public WorkDayType(Long id) {
		super(id);
	}

}
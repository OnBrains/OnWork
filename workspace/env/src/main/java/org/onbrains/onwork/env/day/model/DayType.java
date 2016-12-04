package org.onbrains.onwork.env.day.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Access(AccessType.FIELD)
@NamedQueries({ @NamedQuery(name = DayType.FIND_ALL, query = "from DayType") })
public class DayType extends AbstractDayType {

	private static final long serialVersionUID = -4536998624025275478L;

	public static final String FIND_ALL = "DayType.findAll";

	public static final long WORK_DAY = 1;
	public static final long HOLIDAY = 2;
	public static final long FESTIVAL = 3;
	public static final long SHORT_DAY = 4;

	protected DayType() {
	}

	public DayType(Long id) {
		super(id);
		setSys(false);
	}

}
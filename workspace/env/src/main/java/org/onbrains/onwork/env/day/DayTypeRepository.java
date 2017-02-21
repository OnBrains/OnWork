package org.onbrains.onwork.env.day;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.onbrains.onwork.env.day.model.AbstractDayType;
import org.onbrains.onwork.env.day.model.DayType;
import org.onbrains.onwork.env.sequence.IdSequenceService;

@Stateless
public class DayTypeRepository implements Serializable {

	private static final long serialVersionUID = 5839738923333989315L;

	@Inject
	private IdSequenceService iss;

	@Inject
	private EntityManager em;

	public DayType create(@NotNull String name, float factor, String icon, String iconColor, String description) {
		DayType dayType = new DayType(iss.nextValue(DayType.class));

		dayType.setName(name);
		dayType.setFactor(factor);
		dayType.setIcon(icon);
		dayType.setIconColor(iconColor);
		dayType.setDescription(description);

		em.persist(dayType);
		return dayType;
	}

	public List<AbstractDayType> findAll() {
		return em.createNamedQuery(AbstractDayType.FIND_ALL, AbstractDayType.class).getResultList();
	}

	public List<DayType> findAllCalendarTypes() {
		return em.createNamedQuery(DayType.FIND_ALL_CALENDAR_TYPES, DayType.class).getResultList();
	}

}
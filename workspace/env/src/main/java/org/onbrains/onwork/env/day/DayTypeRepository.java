package org.onbrains.onwork.env.day;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.onbrains.onwork.env.day.model.DayType;
import org.onbrains.onwork.env.sequence.IdSequenceService;

/**
 * Created on 24.11.2016 19:59.
 *
 * @author Oleg Naumov
 */
@Stateless
public class DayTypeRepository implements Serializable {

	private static final long serialVersionUID = 5839738923333989315L;

	@Inject
	private IdSequenceService iss;

	@Inject
	private EntityManager em;

	public DayType create(@NotNull String name, float factor, String color, String icon, String description) {
		DayType dayType = new DayType(iss.nextValue(DayType.class));

		dayType.setName(name);
		dayType.setFactor(factor);
		dayType.setColor(color);
		dayType.setIcon(icon);
		dayType.setDescription(description);

		em.persist(dayType);
		return dayType;
	}

	public List<DayType> findAll() {
		return em.createNamedQuery(DayType.FIND_ALL, DayType.class).getResultList();
	}

}
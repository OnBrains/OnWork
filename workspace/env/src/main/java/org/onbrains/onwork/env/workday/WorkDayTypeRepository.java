package org.onbrains.onwork.env.workday;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.onbrains.onwork.env.sequence.IdSequenceService;
import org.onbrains.onwork.env.workday.model.WorkDayType;

/**
 * Created on 24.11.2016 19:48.
 *
 * @author Oleg Naumov
 */
@Stateless
public class WorkDayTypeRepository implements Serializable {

	private static final long serialVersionUID = 4079194095310216641L;

	@Inject
	private IdSequenceService iss;

	@Inject
	private EntityManager em;

	public WorkDayType create(@NotNull String name, float factor, String color, String icon, String description) {
		WorkDayType workDayType = new WorkDayType(iss.nextValue(WorkDayType.class));

		workDayType.setName(name);
		workDayType.setFactor(factor);
		workDayType.setColor(color);
		workDayType.setIcon(icon);
		workDayType.setDescription(description);

		em.persist(workDayType);
		return workDayType;
	}

}
package org.onbrains.onwork.env.workday;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.onbrains.onwork.env.sequence.IdSequenceService;
import org.onbrains.onwork.env.workday.model.WorkDayType;
import org.onbrains.onwork.inf.dataaccess.utils.BusinessObjectRepository;

/**
 * Created on 24.11.2016 19:48.
 *
 * @author Oleg Naumov
 */
@Stateless
public class WorkDayTypeRepository extends BusinessObjectRepository<WorkDayType> {

	private static final long serialVersionUID = 4079194095310216641L;

	@Inject
	private IdSequenceService iss;

	public WorkDayType create(@NotNull String name, Float factor, String icon, String iconColor, String description) {
		WorkDayType workDayType = new WorkDayType(iss.nextValue(WorkDayType.class));

		workDayType.setName(name);
		workDayType.setFactor(factor);
		workDayType.setIcon(icon);
		workDayType.setIconColor(iconColor);
		workDayType.setDescription(description);

		em.persist(workDayType);
		return workDayType;
	}

	public List<WorkDayType> findAll() {
		return em.createNamedQuery(WorkDayType.FIND_ALL, WorkDayType.class).getResultList();
	}

}
package org.onbrains.onwork.env.workday;

import static org.onbrains.onwork.env.workday.model.WorkDayState.NO_WORK;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;

import org.onbrains.onwork.env.day.DayRepository;
import org.onbrains.onwork.env.day.model.Day;
import org.onbrains.onwork.env.employee.model.Employee;
import org.onbrains.onwork.env.sequence.IdSequenceService;
import org.onbrains.onwork.env.workday.model.WorkDay;
import org.onbrains.onwork.env.workday.model.WorkDayType;

/**
 * Created on 13.11.2016 20:09.
 *
 * @author Oleg Naumov
 */
@Stateless
public class WorkDayRepository implements Serializable {

	private static final long serialVersionUID = 3535783851569041034L;

	private static final String FIND_WORK_DAYS_OF_MONTH = "WorkDayRepository.findWorkDaysOf";
	private static final String FIND_WORK_DAY = "WorkDayRepository.findWorkDay";

	@Inject
	private EntityManager em;

	@Inject
	private IdSequenceService idSequence;

	@Inject
	private DayRepository dr;

	public void createWorkDays(@NotNull LocalDate month, @NotNull Employee employee) {
		List<Day> daysOfMonth = dr.findDaysOf(month);
		daysOfMonth.forEach(day -> create(day, employee));
	}

	public WorkDay findWorkDay(@NotNull LocalDate date, @NotNull Employee employee) {
		try {
			return em.createNamedQuery(FIND_WORK_DAY, WorkDay.class).setParameter("employee", employee)
					.setParameter("date", date).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public List<WorkDay> findWorkDaysOf(@NotNull LocalDate month, @NotNull Employee employee) {
		return em.createNamedQuery(FIND_WORK_DAYS_OF_MONTH, WorkDay.class).setParameter("employee", employee)
				.setParameter("month", month).getResultList();
	}

	// *****************************************************************************************************************
	// Private methods
	// *****************************************************************************************************************

	private WorkDay create(@NotNull Day day, @NotNull Employee employee) {
		WorkDay workDay = new WorkDay(idSequence.nextValue(WorkDay.class));

		workDay.setEmployee(employee);
		workDay.setDay(day);
		workDay.setType((WorkDayType) day.getType());
		workDay.setState(NO_WORK);

		em.persist(workDay);
		return workDay;
	}

}
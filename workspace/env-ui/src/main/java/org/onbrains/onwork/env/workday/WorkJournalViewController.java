package org.onbrains.onwork.env.workday;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.onbrains.onwork.env.employee.EmployeeRepository;
import org.onbrains.onwork.env.workday.model.WorkDay;

@Named(value = "workJournalVC")
@ViewScoped
public class WorkJournalViewController implements Serializable {

	private static final long serialVersionUID = 3794565650424269642L;

	@Inject
	private EmployeeRepository er;

	@Inject
	private WorkDayRepository wdr;

	private YearMonth selectedMonth;

	private List<WorkDay> workDays;

	@PostConstruct
	protected void postConstruct() {
		selectedMonth = YearMonth.now();
	}

	public List<WorkDay> getWorkDays() {
		if (workDays == null || workDays.isEmpty())
			workDays = wdr.findWorkDaysOf(selectedMonth, er.find());
		return workDays;
	}

	public void nextMonth() {
		selectedMonth = selectedMonth.plusMonths(1);
		workDays = null;
	}

	public void previousMonth() {
		selectedMonth = selectedMonth.minusMonths(1);
		workDays = null;
	}

	public Runnable getChangedCallback() {
		return () -> workDays = null;
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public YearMonth getSelectedMonth() {
		return selectedMonth;
	}

}
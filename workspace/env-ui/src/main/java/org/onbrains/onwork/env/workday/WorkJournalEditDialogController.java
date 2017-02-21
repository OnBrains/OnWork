package org.onbrains.onwork.env.workday;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.onbrains.onwork.env.day.DayTypeRepository;
import org.onbrains.onwork.env.day.model.AbstractDayType;
import org.onbrains.onwork.env.employee.EmployeeRepository;
import org.onbrains.onwork.env.workday.model.WorkDay;
import org.onbrains.onwork.util.Notification;
import org.onbrains.onwork.util.OmnifacesUtils;

@Named(value = "workJournalEditDC")
@ViewScoped
public class WorkJournalEditDialogController implements Serializable {

	private static final long serialVersionUID = 1693678551435322143L;

	@Inject
	private EntityManager em;

	@Inject
	private EmployeeRepository er;

	@Inject
	private WorkDayRepository wdr;

	@Inject
	private DayTypeRepository dtr;

	private WorkDay editableWorkDay;
	private Runnable changedCallback;

	private Boolean interval = false;

	private LocalDate newFromDate;
	private LocalDate newToDate;
	private String newDescription;
	private AbstractDayType newType;

	private List<AbstractDayType> types;
	private List<WorkDay> workDays = new ArrayList<>();

	@Transactional
	public void submit(String dlgId) {
		if (editableWorkDay == null)
			workDays.forEach(workDay -> em.merge(workDay));
		else
			em.merge(editableWorkDay);

		changedCallback.run();
		cancel();
		OmnifacesUtils.closeDlg(dlgId);
	}

	public void change() {
		if (!interval)
			changeSingleWorkDay(newFromDate);
		else
			changeSeveralWorkDays();

		clear();
		Collections.sort(workDays, (d1, d2) -> d1.getDay().getValue().compareTo(d2.getDay().getValue()));
	}

	public void toggleIntervalMode() {
		interval = !interval;
	}

	public void cancel() {
		clear();
		workDays.clear();
	}

	public List<AbstractDayType> getTypes() {
		if (types == null)
			types = dtr.findAll();
		return types;
	}

	public void setEditableWorkDay(WorkDay editableWorkDay) {
		this.editableWorkDay = editableWorkDay;
		// this.newFromDate = editableWorkDay.getDay().getValue();
		// this.newType = editableWorkDay.getType();
		// this.newDescription = editableWorkDay.getDescription();
	}

	// *****************************************************************************************************************
	// Private methods
	// *****************************************************************************************************************

	private void changeSingleWorkDay(LocalDate date) {
		WorkDay workDay = wdr.find(date, er.find());
		if (workDay == null)
			return;

		if (!workDays.contains(workDay)) {
			setNewValues(workDay);
			workDays.add(workDay);
		}
	}

	private void changeSeveralWorkDays() {
		if (!checkInterval()) {
			Notification.addMessage("work_journal_edit_form:date_error", "Начальная дата больше конечной");
			return;
		}

		LocalDate tempDate = newFromDate;
		do {
			changeSingleWorkDay(tempDate);
			tempDate = tempDate.plusDays(1);
		} while (tempDate.isBefore(newToDate) || tempDate.isEqual(newToDate));
	}

	private void setNewValues(WorkDay day) {
		if (newType != null)
			day.setType(newType);
		day.setDescription(newDescription);
	}

	private boolean checkInterval() {
		return newFromDate.isBefore(newToDate);
	}

	@SuppressWarnings("Duplicates")
	private void clear() {
		editableWorkDay = null;
		interval = false;
		newFromDate = null;
		newToDate = null;
		newDescription = null;
		newType = null;
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public WorkDay getEditableWorkDay() {
		return editableWorkDay;
	}

	public void setChangedCallback(Runnable changedCallback) {
		this.changedCallback = changedCallback;
	}

	public Boolean getInterval() {
		return interval;
	}

	public void setInterval(Boolean interval) {
		this.interval = interval;
	}

	public LocalDate getNewFromDate() {
		return newFromDate;
	}

	public void setNewFromDate(LocalDate newFromDate) {
		this.newFromDate = newFromDate;
	}

	public LocalDate getNewToDate() {
		return newToDate;
	}

	public void setNewToDate(LocalDate newToDate) {
		this.newToDate = newToDate;
	}

	public String getNewDescription() {
		return newDescription;
	}

	public void setNewDescription(String newDescription) {
		this.newDescription = newDescription;
	}

	public AbstractDayType getNewType() {
		return newType;
	}

	public void setNewType(AbstractDayType newType) {
		this.newType = newType;
	}

	public List<WorkDay> getWorkDays() {
		return workDays;
	}

}
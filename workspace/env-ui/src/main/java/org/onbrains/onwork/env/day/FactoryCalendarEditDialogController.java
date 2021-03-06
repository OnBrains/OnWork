package org.onbrains.onwork.env.day;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.onbrains.onwork.env.day.model.Day;
import org.onbrains.onwork.env.day.model.DayType;
import org.onbrains.onwork.util.Notification;
import org.onbrains.onwork.util.OmnifacesUtils;

@Named(value = "factoryCalendarEditDC")
@ViewScoped
public class FactoryCalendarEditDialogController implements Serializable {

	private static final long serialVersionUID = -5449565836973567500L;

	@PersistenceContext
	private EntityManager em;

	@Inject
	private DayRepository dr;

	@Inject
	private DayTypeRepository dtr;

	private Runnable changedCallback;

	private Boolean interval = false;

	private LocalDate newFromDate;
	private LocalDate newToDate;
	private String newDescription;
	private DayType newType;

	private List<DayType> types;
	private List<Day> days = new ArrayList<>();

	@Transactional
	public void submit(String dlgId) {
		days.forEach(day -> em.merge(day));
		changedCallback.run();

		cancel();
		OmnifacesUtils.closeDlg(dlgId);
	}

	public void change() {
		if (!interval)
			changeSingleDay();
		else
			changeSeveralDays();

		clear();
		Collections.sort(days, (d1, d2) -> d1.getValue().compareTo(d2.getValue()));
	}

	public void toggleIntervalMode() {
		interval = !interval;
	}

	public void cancel() {
		clear();
		days.clear();
	}

	public List<DayType> getTypes() {
		if (types == null)
			types = dtr.findAllCalendarTypes();
		return types;
	}

	// *****************************************************************************************************************
	// Private methods
	// *****************************************************************************************************************

	// TODO: change work day that dependent from this day and they types are equals
	private void changeSingleDay() {
		Day day = dr.find(newFromDate);
		if (day == null)
			return;

		if (!days.contains(day)) {
			setNewValues(day);
			days.add(day);
		}
	}

	// TODO: call changeSingleDay because now can add several equals days
	private void changeSeveralDays() {
		if (!checkInterval()) {
			Notification.addMessage("factory_calendar_edit_form:date_error", "Начальная дата больше конечной");
			return;
		}

		LocalDate tempDate = newFromDate;
		do {
			Day day = dr.find(tempDate);
			setNewValues(day);
			days.add(day);
			tempDate = tempDate.plusDays(1);
		} while (tempDate.isBefore(newToDate) || tempDate.isEqual(newToDate));
	}

	private void setNewValues(Day day) {
		if (newType != null)
			day.setType(newType);
		day.setDescription(newDescription);
	}

	private boolean checkInterval() {
		return newFromDate.isBefore(newToDate);
	}

	@SuppressWarnings("Duplicates")
	private void clear() {
		interval = false;
		newFromDate = null;
		newToDate = null;
		newDescription = null;
		newType = null;
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public Runnable getChangedCallback() {
		return changedCallback;
	}

	public void setChangedCallback(Runnable changedCallback) {
		this.changedCallback = changedCallback;
	}

	public Boolean getInterval() {
		return interval;
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

	public DayType getNewType() {
		return newType;
	}

	public void setNewType(DayType newType) {
		this.newType = newType;
	}

	public List<Day> getDays() {
		return days;
	}

}
package org.onbrains.onwork.env.day;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.onbrains.onwork.env.day.model.Day;
import org.onbrains.onwork.env.day.model.DayType;
import org.onbrains.onwork.util.Notification;

@Named(value = "factoryCalendarEditDC")
@ViewScoped
public class FactoryCalendarEditDialogController implements Serializable {

	private static final long serialVersionUID = -5449565836973567500L;

	@PersistenceContext
	private EntityManager em;

	@Inject
	private DayRepository dr;

	private Boolean interval = false;

	private LocalDate newFromDate;
	private LocalDate newToDate;
	private String newDescription;
	private DayType newType;

	private Set<Day> days = new HashSet<>();

	@Transactional
	public void submit() {
		days.forEach(day -> em.merge(day));
	}

	public void change() {
		if (!interval)
			changeSingleDay();
		else
			changeSeveralDays();
	}

	public void toggleIntervalMode() {
		interval = !interval;
	}

	public void cancel() {
		interval = false;
		newFromDate = null;
		newToDate = null;
		newDescription = null;
		newType = null;
	}

	// *****************************************************************************************************************
	// Private methods
	// *****************************************************************************************************************

	private void changeSingleDay() {
		Day day = dr.find(newFromDate);
		setNewValues(day);
		days.add(day);
	}

	private void changeSeveralDays() {
		if (!checkInterval()) {
			Notification.addMessage("", "Начальная дата больше конечной");
			return;
		}

		LocalDate tempDate = newToDate;
		do {
			Day day = dr.find(tempDate);
			setNewValues(day);
			days.add(day);
			tempDate = tempDate.plusDays(1);
		} while (tempDate.equals(newToDate));
	}

	private void setNewValues(Day day) {
		day.setType(newType);
		day.setDescription(newDescription);
	}

	private boolean checkInterval() {
		return newFromDate.isAfter(newToDate);
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

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

	public Set<Day> getDays() {
		return days;
	}

	public void setDays(Set<Day> days) {
		this.days = days;
	}

}
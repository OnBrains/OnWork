package org.onbrains.onwork.env.day;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.onbrains.onwork.env.day.model.Day;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 * Created on 13.11.2016 20:49.
 *
 * @author Oleg Naumov
 */
@Named(value = "dayDirectoryVM")
@ViewScoped
public class DayDirectoryViewModel implements Serializable {

	private static final long serialVersionUID = 8985101951746767877L;

	@Inject
	private DayRepository dr;

	private LocalDate selectedMonth;

	private List<Day> days;
	private Boolean yearHaveDays;

	@PostConstruct
	protected void postConstruct() {
		selectedMonth = LocalDate.now();
	}

	public List<Day> getDays() {
		if (days == null)
			days = dr.findDaysOf(selectedMonth);
		return days;
	}

	// TODO: создавать дни для конкретного года
	public void createDaysForYear() {
		dr.createDays(LocalDate.now().getYear());
	}

	// TODO: проверять дни для конкретного года
	public boolean isYearHaveDays() {
		if (yearHaveDays == null)
			yearHaveDays = dr.countDaysOf(LocalDate.now()) != 0;
		return yearHaveDays;
	}

	public void nextMonth() {
		selectedMonth = selectedMonth.plusMonths(1);
		days = null;
	}

	public void previousMonth() {
		selectedMonth = selectedMonth.minusMonths(1);
		days = null;
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public LocalDate getSelectedMonth() {
		return selectedMonth;
	}

	private List<Day> selectedDays;

	public List<Day> getSelectedDays() {
		return selectedDays;
	}

	public void setSelectedDays(List<Day> selectedDays) {
		this.selectedDays = selectedDays;
	}
}
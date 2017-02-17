package org.onbrains.onwork.env.day;

import static org.onbrains.onwork.env.day.model.DayType.HOLIDAY;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.onbrains.onwork.env.day.model.Day;

@Named(value = "dayDirectoryVM")
@ViewScoped
public class DayDirectoryViewModel implements Serializable {

	private static final long serialVersionUID = 8985101951746767877L;

	@Inject
	private DayRepository dr;

	private LocalDate selectedMonth;

	private List<Day> days;

	@PostConstruct
	protected void postConstruct() {
		selectedMonth = LocalDate.now();
	}

	public List<Day> getDays() {
		if (days == null)
			days = dr.findDaysOf(selectedMonth);
		return days;
	}

	public void nextMonth() {
		selectedMonth = selectedMonth.plusMonths(1);
		days = null;
	}

	public void previousMonth() {
		selectedMonth = selectedMonth.minusMonths(1);
		days = null;
	}

	public String getStyleClassForRow(Day day) {
		return day != null && !Objects.equals(HOLIDAY, day.getType().getId()) ? "" : "holiday";
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public LocalDate getSelectedMonth() {
		return selectedMonth;
	}

}
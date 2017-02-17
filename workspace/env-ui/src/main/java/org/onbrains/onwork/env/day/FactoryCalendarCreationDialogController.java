package org.onbrains.onwork.env.day;

import java.io.Serializable;
import java.time.Year;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.onbrains.onwork.util.Notification;

@Named(value = "factoryCalendarCreationDC")
@RequestScoped
public class FactoryCalendarCreationDialogController implements Serializable {

	private static final long serialVersionUID = -1424744912281091443L;

	@Inject
	private DayRepository dr;

	private Year newYear;

	public void create() {
		if (isYearHaveDays()) {
			Notification.addMessage("factory_calendar_creation_form:year:year",
					"Для данного года уже заведён производственный календарь");
			return;
		}
		dr.createDays(newYear);
	}

	public void cancel() {
		newYear = null;
	}

	public boolean isYearHaveDays() {
		return dr.countDaysOf(newYear) != 0;
	}

	// *****************************************************************************************************************
	// Private methods
	// *****************************************************************************************************************

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public Year getNewYear() {
		return newYear;
	}

	public void setNewYear(Year newYear) {
		this.newYear = newYear;
	}

}
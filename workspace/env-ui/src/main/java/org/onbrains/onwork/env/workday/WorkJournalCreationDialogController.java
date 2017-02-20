package org.onbrains.onwork.env.workday;

import java.io.Serializable;
import java.time.YearMonth;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.onbrains.onwork.env.employee.EmployeeRepository;
import org.onbrains.onwork.util.Notification;
import org.onbrains.onwork.util.OmnifacesUtils;

@Named(value = "workJournalCreationDC")
@RequestScoped
public class WorkJournalCreationDialogController implements Serializable {

	private static final long serialVersionUID = 5061391523621909626L;

	@Inject
	private EmployeeRepository er;

	@Inject
	private WorkDayRepository wdr;

	private YearMonth newMonth;

	public void create(String dlgId) {
		if (isMonthHasJournal()) {
			Notification.addMessage("work_journal_creation_form:month:month",
					"Для данного месяца уже заведён рабочий журнал");
			return;
		}
		wdr.createWorkDays(newMonth, er.find());

		cancel();
		OmnifacesUtils.closeDlg(dlgId);
	}

	public void cancel() {
		newMonth = null;
	}

	// *****************************************************************************************************************
	// Private methods
	// *****************************************************************************************************************

	private boolean isMonthHasJournal() {
		return wdr.countWorkDaysOf(newMonth, er.find()) != 0;
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public YearMonth getNewMonth() {
		return newMonth;
	}

	public void setNewMonth(YearMonth newMonth) {
		this.newMonth = newMonth;
	}

}
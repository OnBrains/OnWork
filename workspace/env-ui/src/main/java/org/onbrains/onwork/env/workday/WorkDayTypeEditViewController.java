package org.onbrains.onwork.env.workday;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.onbrains.onwork.env.day.AbstractDayTypeEditDialogController;
import org.onbrains.onwork.env.workday.model.WorkDayType;

/**
 * Created on 24.11.2016 20:28.
 *
 * @author Oleg Naumov
 */
@Named(value = "workDayTypeEditDC")
@RequestScoped
public class WorkDayTypeEditViewController extends AbstractDayTypeEditDialogController<WorkDayType> {

	private static final long serialVersionUID = -5103080761051783088L;

	@Inject
	private WorkDayTypeRepository wdtr;

	@Override
	protected void create() {
		WorkDayType workDayType = wdtr.create(getName(), getFactor(), getColor(), getIcon(), getDescription());
		getCallback().execute(workDayType);
	}

}
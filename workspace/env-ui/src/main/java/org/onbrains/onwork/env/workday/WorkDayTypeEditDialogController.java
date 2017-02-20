package org.onbrains.onwork.env.workday;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.onbrains.onwork.env.day.AbstractDayTypeEditDialogController;
import org.onbrains.onwork.env.workday.model.WorkDayType;

@Named(value = "workDayTypeEditDC")
@ViewScoped
public class WorkDayTypeEditDialogController extends AbstractDayTypeEditDialogController<WorkDayType> {

	private static final long serialVersionUID = -5103080761051783088L;

	@Inject
	private WorkDayTypeRepository wdtr;

	@Override
	protected void create() {
		WorkDayType workDayType = wdtr.create(getName(), getFactor(), getIcon(), getIconColor(), getDescription());
		getCallback().execute(workDayType);
	}

	@Override
	protected void update() {
		super.update();
		em.merge(getEditableObject());
	}

}
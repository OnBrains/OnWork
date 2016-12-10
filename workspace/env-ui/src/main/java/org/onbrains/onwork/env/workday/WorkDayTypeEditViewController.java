package org.onbrains.onwork.env.workday;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.onbrains.onwork.env.day.AbstractDayTypeEditDialogController;
import org.onbrains.onwork.env.workday.model.WorkDayType;

/**
 * Created on 24.11.2016 20:28.
 *
 * @author Oleg Naumov
 */
@Named(value = "workDayTypeEditDC")
@ViewScoped
@Transactional
public class WorkDayTypeEditViewController extends AbstractDayTypeEditDialogController<WorkDayType> {

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
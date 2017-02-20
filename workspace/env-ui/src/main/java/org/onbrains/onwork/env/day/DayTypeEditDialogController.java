package org.onbrains.onwork.env.day;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.onbrains.onwork.env.day.model.DayType;

@Named(value = "dayTypeEditDC")
@ViewScoped
public class DayTypeEditDialogController extends AbstractDayTypeEditDialogController<DayType> {

	private static final long serialVersionUID = -1980386256232164089L;

	@Inject
	private DayTypeRepository dtr;

	@Override
	protected void create() {
		DayType dayType = dtr.create(getName(), getFactor(), getIcon(), getIconColor(), getDescription());
		getCallback().execute(dayType);
	}

	@Override
	protected void update() {
		super.update();
		em.merge(getEditableObject());
	}

}
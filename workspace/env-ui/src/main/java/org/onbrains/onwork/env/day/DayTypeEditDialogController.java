package org.onbrains.onwork.env.day;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.onbrains.onwork.env.day.model.DayType;

/**
 * Created on 27.11.2016 20:40.
 *
 * @author Oleg Naumov
 */
@Named(value = "dayTypeEditDC")
@ViewScoped
public class DayTypeEditDialogController extends AbstractDayTypeEditDialogController<DayType> {

	private static final long serialVersionUID = -1980386256232164089L;

	@Inject
	private DayTypeRepository dtr;

	@Override
	protected void create() {
		dtr.create(getName(), getFactor(), getColor(), getIcon(), getDescription());
	}

}
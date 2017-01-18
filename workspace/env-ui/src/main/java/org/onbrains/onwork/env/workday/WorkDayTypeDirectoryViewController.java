package org.onbrains.onwork.env.workday;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.onbrains.onwork.env.day.AbstractDayTypeDirectoryViewController;
import org.onbrains.onwork.env.workday.model.WorkDayType;

/**
 * Реализация контроллера справочника "Типов дней" для рабочих дней.
 *
 * @author Oleg Naumov
 */
@Named(value = "workDayTypeDirectoryVC")
@ViewScoped
public class WorkDayTypeDirectoryViewController extends AbstractDayTypeDirectoryViewController<WorkDayType> {

	private static final long serialVersionUID = -8945765102305242366L;

	@Inject
	private WorkDayTypeRepository wdtr;

	@Override
	public List<WorkDayType> getTypes() {
		if (types == null) {
			types = wdtr.findAll();
		}
		return types;
	}

}
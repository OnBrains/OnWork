package org.onbrains.onwork.env.day;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.onbrains.onwork.env.day.model.DayType;

/**
 * Реализация контроллера справочника "Типов дней" для календарных дней.
 *
 * @author Oleg Naumov
 */
@Named(value = "dayTypeDirectoryVC")
@ViewScoped
public class DayTypeDirectoryViewController extends AbstractDayTypeDirectoryViewController<DayType> {

	private static final long serialVersionUID = -8945765102305242366L;

	@Inject
	private DayTypeRepository dtr;

	@Override
	public List<DayType> getTypes() {
		if (types == null) {
			types = dtr.findAll();
		}
		return types;
	}

}
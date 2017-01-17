package org.onbrains.onwork.env.event;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.onbrains.onwork.env.event.model.FixedTimeEventType;

/**
 * Реализация контроллера для справочника "Типов событий с фиксированным временем".
 *
 * @author Oleg Naumov
 */
@Named(value = "fixedTimeEventTypeDirectoryVC")
@ViewScoped
public class FixedTimeEventTypeDirectoryViewController
		extends AbstractEventTypeDirectoryViewController<FixedTimeEventType> {

	private static final long serialVersionUID = 5673618964985055515L;

	@Override
	public List<FixedTimeEventType> getTypes() {
		if (types == null)
			types = etr.findAllFixedTimeEventTypes();
		return types;
	}

}
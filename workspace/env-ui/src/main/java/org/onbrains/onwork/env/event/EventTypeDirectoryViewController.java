package org.onbrains.onwork.env.event;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.onbrains.onwork.env.event.model.EventType;

/**
 * Реализация контроллера для справочника "Простых типов событий".
 *
 * @author Oleg Naumov
 */
@Named(value = "eventTypeDirectoryVC")
@ViewScoped
public class EventTypeDirectoryViewController extends AbstractEventTypeDirectoryViewController<EventType> {

	private static final long serialVersionUID = 4074900443685495786L;

	@Override
	public List<EventType> getTypes() {
		if (types == null)
			types = etr.findAllEventTypes();
		return types;
	}

}
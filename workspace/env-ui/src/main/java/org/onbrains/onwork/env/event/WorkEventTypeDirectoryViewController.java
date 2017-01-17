package org.onbrains.onwork.env.event;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.onbrains.onwork.env.event.model.WorkEventType;

/**
 * Реализация контроллера для справочника "Рабочих типов событий".
 *
 * @author Oleg Naumov
 */
@Named(value = "workEventTypeDirectoryVC")
@ViewScoped
public class WorkEventTypeDirectoryViewController extends AbstractEventTypeDirectoryViewController<WorkEventType> {

	private static final long serialVersionUID = 4928651823567879705L;

	@Override
	public List<WorkEventType> getTypes() {
		if (types == null) {
			types = etr.findAllWorkEventTypes();
		}
		return types;
	}

}
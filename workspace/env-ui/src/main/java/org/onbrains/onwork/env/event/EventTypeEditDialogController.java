package org.onbrains.onwork.env.event;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.onbrains.onwork.env.event.model.EventType;

/**
 * Реализация диалога создания/редактирования "Простых типов событий".
 *
 * @author Oleg Naumov
 */
@Named(value = "eventTypeEditDC")
@ViewScoped
@Transactional
public class EventTypeEditDialogController extends AbstractEventTypeEditDialogController<EventType> {

	private static final long serialVersionUID = -3914842784146140910L;

	@Override
	protected void create() {
		EventType instance = etr.createEventType(getName(), getIcon(), getIconColor(), getDescription());
		getCallback().execute(instance);
	}

	@Override
	protected void update() {
		super.update();
		em.merge(getEditableObject());
	}

}
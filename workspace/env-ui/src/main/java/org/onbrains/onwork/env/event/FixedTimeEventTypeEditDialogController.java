package org.onbrains.onwork.env.event;

import java.time.LocalTime;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.onbrains.onwork.env.event.model.FixedTimeEventType;

/**
 * Реализация диалога создания/редактирования "Типов событий с фиксированным временем".
 *
 * @author Oleg Naumov
 */
@Named(value = "fixedTimeEventTypeEditDC")
@ViewScoped
public class FixedTimeEventTypeEditDialogController extends AbstractEventTypeEditDialogController<FixedTimeEventType> {

	private static final long serialVersionUID = -7089744567493995290L;

	private LocalTime fixedTime;

	@Override
	protected void create() {
		FixedTimeEventType instance = etr.createFixedTimeEventType(getName(), getFixedTime(), getIcon(), getIconColor(),
				getDescription());
		getCallback().execute(instance);
	}

	@Override
	protected void update() {
		super.update();
		getEditableObject().setFixedTime(fixedTime);
		em.merge(getEditableObject());
	}

	@Override
	public void cancel() {
		super.cancel();
		fixedTime = null;
	}

	@Override
	public void setEditableObject(FixedTimeEventType editableObject) {
		super.setEditableObject(editableObject);
		fixedTime = editableObject.getFixedTime();
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public LocalTime getFixedTime() {
		return fixedTime;
	}

	public void setFixedTime(LocalTime fixedTime) {
		this.fixedTime = fixedTime;
	}

}
package org.onbrains.onwork.env.event;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.onbrains.onwork.env.event.model.WorkEventType;

/**
 * Реализация диалога создания/редактирования "Рабочих типов событий".
 *
 * @author Oleg Naumov
 */
@Named(value = "workEventTypeEditDC")
@ViewScoped
@Transactional
public class WorkEventTypeEditDialogController extends AbstractEventTypeEditDialogController<WorkEventType> {

	private static final long serialVersionUID = -4178573980171222364L;

	@Override
	protected void create() {
		WorkEventType instance = etr.createWorkEventType(getName(), getIcon(), getIconColor(), getDescription());
		getCallback().execute(instance);
	}

	@Override
	protected void update() {
		super.update();
		em.merge(getEditableObject());
	}

}
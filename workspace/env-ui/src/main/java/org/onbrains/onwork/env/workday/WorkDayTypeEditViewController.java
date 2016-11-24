package org.onbrains.onwork.env.workday;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.onbrains.onwork.env.workday.model.WorkDayType;
import org.onbrains.onwork.util.Callback;
import org.onbrains.onwork.util.EditDialogController;

/**
 * Created on 24.11.2016 20:28.
 *
 * @author Oleg Naumov
 */
@Named(value = "workDayTypeEditDC")
@RequestScoped
public class WorkDayTypeEditViewController implements EditDialogController<WorkDayType>, Serializable {

	private static final long serialVersionUID = -5103080761051783088L;

	@Inject
	private WorkDayTypeRepository wdtr;

	@Inject
	private EntityManager em;

	private WorkDayType editableType;
	private Callback<WorkDayType> callback;

	private String name;
	private Float factor;
	private String color;
	private String icon;
	private String description;

	@Override
	public void submit() {
		if (!isEditMode()) {
			WorkDayType type = wdtr.create(name, factor, color, icon, description);
			callback.execute(type);
		} else {
			update();
			em.merge(editableType);
		}
	}

	@Override
	public void cancel() {
		editableType = null;
		callback = null;
		name = null;
		factor = null;
		color = null;
		icon = null;
		description = null;
	}

	@Override
	public void setEditableObject(WorkDayType editableObject) {
		editableType = editableObject;
		name = editableObject.getName();
		factor = editableObject.getFactor();
		color = editableObject.getColor();
		icon = editableObject.getIcon();
		description = editableObject.getDescription();
	}

	// *****************************************************************************************************************
	// Private methods
	// *****************************************************************************************************************

	private void update() {
		editableType.setName(name);
		editableType.setFactor(factor);
		editableType.setColor(color);
		editableType.setIcon(icon);
		editableType.setDescription(description);
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	@Override
	public WorkDayType getEditableObject() {
		return editableType;
	}

	@Override
	public Callback<WorkDayType> getCallback() {
		return callback;
	}

	@Override
	public void setCallback(Callback<WorkDayType> callback) {
		this.callback = callback;
	}

	// *****************************************************************************************************************
	// Inner classes
	// *****************************************************************************************************************

}
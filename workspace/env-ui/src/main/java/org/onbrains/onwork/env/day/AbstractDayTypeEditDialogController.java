package org.onbrains.onwork.env.day;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.onbrains.onwork.env.day.model.AbstractDayType;
import org.onbrains.onwork.util.Callback;
import org.onbrains.onwork.util.EditDialogController;
import org.onbrains.onwork.util.OmnifacesUtils;

public abstract class AbstractDayTypeEditDialogController<T extends AbstractDayType>
		implements EditDialogController<T>, Serializable {

	private static final long serialVersionUID = -4501594762187392875L;

	@Inject
	protected EntityManager em;

	private T editableType;
	private Callback<T> callback;

	private String name;
	private Float factor;
	private String icon;
	private String iconColor;
	private String description;

	@Override
	@Transactional
	public void submit(String dlgId) {
		if (!isEditMode())
			create();
		else
			update();

		cancel();
		OmnifacesUtils.closeDlg(dlgId);
	}

	@Override
	public void cancel() {
		editableType = null;
		callback = null;
		name = null;
		factor = null;
		icon = null;
		iconColor = null;
		description = null;
	}

	@Override
	public void setEditableObject(T editableObject) {
		editableType = editableObject;
		name = editableObject.getName();
		factor = editableObject.getFactor();
		icon = editableObject.getIcon();
		iconColor = editableObject.getIconColor();
		description = editableObject.getDescription();
	}

	public boolean canEdit() {
		return editableType == null || !editableType.isSys();
	}

	// *****************************************************************************************************************
	// Private methods
	// *****************************************************************************************************************

	protected abstract void create();

	protected void update() {
		editableType.setName(name);
		editableType.setFactor(factor);
		editableType.setIcon(icon);
		editableType.setIconColor(iconColor);
		editableType.setDescription(description);
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	@Override
	public T getEditableObject() {
		return editableType;
	}

	@Override
	public Callback<T> getCallback() {
		return callback;
	}

	@Override
	public void setCallback(Callback<T> callback) {
		this.callback = callback;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getFactor() {
		return factor;
	}

	public void setFactor(Float factor) {
		this.factor = factor;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconColor() {
		return iconColor;
	}

	public void setIconColor(String iconColor) {
		this.iconColor = iconColor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
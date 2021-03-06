package org.onbrains.onwork.env.event;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.onbrains.onwork.env.event.model.AbstractEventType;
import org.onbrains.onwork.util.Callback;
import org.onbrains.onwork.util.EditDialogController;
import org.onbrains.onwork.util.OmnifacesUtils;

/**
 * Абстрактная реализация диалога создания/редактирования типов событий.
 *
 * @author Oleg Naumov
 */
public abstract class AbstractEventTypeEditDialogController<T extends AbstractEventType>
		implements EditDialogController<T>, Serializable {

	private static final long serialVersionUID = -1266260116973706767L;

	@Inject
	protected EntityManager em;

	@Inject
	protected EventTypeRepository etr;

	private T editableType;
	private Callback<T> callback;

	private String name;
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
		icon = null;
		iconColor = null;
		description = null;
	}

	@Override
	public void setEditableObject(T editableObject) {
		editableType = editableObject;

		name = editableObject.getName();
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
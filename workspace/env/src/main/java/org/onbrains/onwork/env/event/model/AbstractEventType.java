package org.onbrains.onwork.env.event.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.onbrains.onwork.inf.modelbase.BusinessDirectory;

/**
 * Абстрактный класс типов событий, вводит основные поля и методы для работы с типом события.
 *
 * @author Oleg Naumov
 */
@Entity
@Access(AccessType.FIELD)
@Table(schema = "system", name = "event_type", uniqueConstraints = @UniqueConstraint(name = "uc_event_type", columnNames = {
		"name" }))
public abstract class AbstractEventType extends BusinessDirectory {

	private static final long serialVersionUID = -4532803890054563286L;

	@Column(length = 128, nullable = false)
	private String name;

	@Column(length = 64)
	private String icon;

	@Column(name = "icon_color", length = 16)
	private String iconColor;

	@Column(length = 512)
	private String description;

	@Column(nullable = false)
	private Boolean active;

	@Column(nullable = false)
	private Boolean sys;

	protected AbstractEventType() {
	}

	public AbstractEventType(Long id) {
		super(id);
		sys = false;
	}

	@Override
	public String getObjectName() {
		return name;
	}

	public abstract boolean influenceOnWorkTime();

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	private void setSys(Boolean sys) {
		this.sys = sys;
	}

	@Override
	public boolean isSys() {
		return sys;
	}

}
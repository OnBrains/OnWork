package org.onbrains.onwork.env.day.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.onbrains.onwork.inf.modelbase.BusinessDirectory;

/**
 * Created on 27.11.2016 17:50.
 *
 * @author Oleg Naumov
 */
@Entity
@Access(AccessType.FIELD)
@Table(schema = "system", name = "day_type", uniqueConstraints = @UniqueConstraint(name = "uc_day_type", columnNames = {
		"name" }))
public abstract class AbstractDayType extends BusinessDirectory {

	private static final long serialVersionUID = -6364999546146433925L;

	@Column(length = 128, nullable = false)
	private String name;

	@Column(nullable = false)
	private Float factor;

	@Column(length = 16)
	private String color;

	@Column(length = 16)
	private String icon;

	@Column(length = 512)
	private String description;

	@Column(nullable = false)
	private Boolean sys;

	@Version
	private Long version;

	protected AbstractDayType() {
	}

	public AbstractDayType(Long id) {
		super(id);
	}

	@Override
	public String getObjectName() {
		return getName();
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getFactor() {
		return factor;
	}

	public void setFactor(float factor) {
		this.factor = factor;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean isSys() {
		return sys;
	}

	public void setSys(boolean sys) {
		this.sys = sys;
	}

}
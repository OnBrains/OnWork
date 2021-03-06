package org.onbrains.onwork.env.day.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.onbrains.onwork.inf.modelbase.BusinessDirectory;

@Entity
@Access(AccessType.FIELD)
@Table(schema = "system", name = "day_type", uniqueConstraints = @UniqueConstraint(name = "uc_day_type", columnNames = {
		"name" }))
@NamedQueries({ @NamedQuery(name = AbstractDayType.FIND_ALL, query = "select dt from AbstractDayType dt") })
public abstract class AbstractDayType extends BusinessDirectory {

	private static final long serialVersionUID = -6364999546146433925L;

	public static final String FIND_ALL = "findAll";

	@Column(length = 128, nullable = false)
	private String name;

	@Column(nullable = false)
	private Float factor;

	@Column(length = 64)
	private String icon;

	@Column(name = "icon_color", length = 16)
	private String iconColor;

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

	@Override
	public boolean isSys() {
		return sys;
	}

	public void setSys(boolean sys) {
		this.sys = sys;
	}

}
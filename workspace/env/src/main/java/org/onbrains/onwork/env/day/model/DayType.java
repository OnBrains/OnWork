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
@NamedQueries({ @NamedQuery(name = DayType.FIND_ALL, query = "from DayType") })
public class DayType extends BusinessDirectory {

	private static final long serialVersionUID = -4536998624025275478L;

	public static final String FIND_ALL = "findAll";

	public static final long WORK_DAY = 1;
	public static final long HOLIDAY = 2;
	public static final long SHORT_DAY = 3;

	@Column(length = 128, nullable = false)
	private String name;

	@Column(nullable = false)
	private float factor;

	@Column(length = 16)
	private String color;

	@Column(length = 16)
	private String icon;

	@Column(length = 512)
	private String description;

	@Column(nullable = false)
	private boolean sys;

	@Version
	private Long version;

	protected DayType() {
	}

	public DayType(Long id) {
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
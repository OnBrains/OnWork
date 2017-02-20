package org.onbrains.onwork.env.day.model;

import java.time.LocalDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import org.onbrains.onwork.inf.modelbase.BusinessObject;

/**
 * Created on 13.11.2016 16:03.
 *
 * @author Oleg Naumov
 */
@Entity
@Access(AccessType.FIELD)
@Table(schema = "system")
//@formatter:off
@NamedQueries({
		@NamedQuery(name = Day.FIND_DAYS_OF_MONTH, query = "select d from Day d where to_char(d.value, 'yyyy-MM') = :month order by d.value"),
		@NamedQuery(name = Day.COUNT_DAYS_OF_YEAR, query = "select count(*) from Day d where to_char(d.value, 'yyyy') =  :year"),
		@NamedQuery(name = Day.FIND_DAY, query = "select d from Day d where d.value = :date")
})
//@formatter:on
public class Day extends BusinessObject {

	private static final long serialVersionUID = 1633044954816711718L;

	public static final String FIND_DAYS_OF_MONTH = "findDaysOfMonth";
	public static final String FIND_DAY = "findDay";
	public static final String COUNT_DAYS_OF_YEAR = "countDaysOfYear";

	@Column(nullable = false, updatable = false)
	private LocalDate value;

	@ManyToOne(optional = false)
	private DayType type;

	@Column(length = 512)
	private String description;

	@Version
	private Long version;

	protected Day() {
	}

	public Day(Long id) {
		super(id);
	}

	@Override
	public String getObjectName() {
		return value.toString();
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public LocalDate getValue() {
		return value;
	}

	public void setValue(LocalDate value) {
		this.value = value;
	}

	public DayType getType() {
		return type;
	}

	public void setType(DayType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
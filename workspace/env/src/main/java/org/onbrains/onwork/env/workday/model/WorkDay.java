package org.onbrains.onwork.env.workday.model;

import java.time.LocalTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import org.onbrains.onwork.env.day.model.AbstractDayType;
import org.onbrains.onwork.env.day.model.Day;
import org.onbrains.onwork.env.employee.model.Employee;
import org.onbrains.onwork.inf.modelbase.BusinessObject;

/**
 * Created on 13.11.2016 16:13.
 *
 * @author Oleg Naumov
 */
@Entity
@Access(AccessType.FIELD)
@Table(schema = "system", name = "work_day")
//@formatter:off
@NamedQueries({
		@NamedQuery(name = WorkDay.COUNT_WORK_DAYS_OF_MONTH, query = "select count(*) from WorkDay wd where to_char(wd.day.value, 'yyyy-MM') = :month and wd.employee = :employee"),
		@NamedQuery(name = WorkDay.FIND_WORK_DAYS_OF_MONTH, query = "select wd from WorkDay wd where to_char(wd.day.value, 'yyyy-MM') = :month and wd.employee = :employee"),
		@NamedQuery(name = WorkDay.FIND_WORK_DAY, query = "select wd from WorkDay wd where wd.day.value = :date and wd.employee = :employee")
})
//@formatter:on
public class WorkDay extends BusinessObject {

	private static final long serialVersionUID = -6333728186740111484L;

	public static final String COUNT_WORK_DAYS_OF_MONTH = "countWorkDaysOfMonth";
	public static final String FIND_WORK_DAYS_OF_MONTH = "WorkDayRepository.findWorkDaysOf";
	public static final String FIND_WORK_DAY = "WorkDayRepository.find";

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Day day;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private AbstractDayType type;

	@Column(name = "coming_time")
	private LocalTime comingTime;

	@Column(name = "out_time")
	private LocalTime outTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 32)
	private WorkDayState state;

	@Column(length = 512)
	private String description;

	@Version
	private Long version;

	protected WorkDay() {
	}

	public WorkDay(Long id) {
		super(id);
	}

	public String getTypeDescription() {
		return day.getDescription() == null ? type.getObjectName() : day.getDescription();
	}

	@Override
	public String getObjectName() {
		return day.getObjectName();
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public AbstractDayType getType() {
		return type;
	}

	public void setType(AbstractDayType type) {
		this.type = type;
	}

	public LocalTime getComingTime() {
		return comingTime;
	}

	public void setComingTime(LocalTime comingTime) {
		this.comingTime = comingTime;
	}

	public LocalTime getOutTime() {
		return outTime;
	}

	public void setOutTime(LocalTime outTime) {
		this.outTime = outTime;
	}

	public WorkDayState getState() {
		return state;
	}

	public void setState(WorkDayState state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
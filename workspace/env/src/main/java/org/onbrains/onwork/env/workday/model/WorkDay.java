package org.onbrains.onwork.env.workday.model;

import java.time.LocalDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

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
public class WorkDay extends BusinessObject {

	private static final long serialVersionUID = -6333728186740111484L;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Day day;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private WorkDayType type;

	@Column(name = "coming_time")
	private LocalDateTime comingTime;

	@Column(name = "out_time")
	private LocalDateTime outTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 32)
	private WorkDayState state;

	@Version
	private Long version;

	protected WorkDay() {
	}

	public WorkDay(Long id) {
		super(id);
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

	public WorkDayType getType() {
		return type;
	}

	public void setType(WorkDayType type) {
		this.type = type;
	}

	public LocalDateTime getComingTime() {
		return comingTime;
	}

	public void setComingTime(LocalDateTime comingTime) {
		this.comingTime = comingTime;
	}

	public LocalDateTime getOutTime() {
		return outTime;
	}

	public void setOutTime(LocalDateTime outTime) {
		this.outTime = outTime;
	}

	public WorkDayState getState() {
		return state;
	}

	public void setState(WorkDayState state) {
		this.state = state;
	}

}
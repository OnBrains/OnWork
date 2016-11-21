package org.onbrains.onwork.env.workday.model;

import org.onbrains.onwork.inf.modelbase.Named;

/**
 * Created on 13.11.2016 16:57.
 *
 * @author Oleg Naumov
 */
public enum WorkDayState implements Named {

	//@formatter:off
	NO_WORK	("Не работал"),
	WORKING	("На работе"),
	WORKED	("Отработал");
	//@formatter:on

	private String name;

	private WorkDayState(String name) {
		this.name = name;
	}

	@Override
	public String getObjectName() {
		return name;
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public String getName() {
		return name;
	}

}
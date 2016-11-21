package org.onbrains.onwork.env.format;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.google.common.collect.ImmutableMap;

/**
 * Created on 19.11.2016 16:43.
 *
 * @author Oleg Naumov
 */
@Named(value = "dateFormat")
@ApplicationScoped
public class DateFormatUtils implements Serializable {

	private static final long serialVersionUID = -2594634495632142220L;

	//@formatter:off
	private static final Map<Long, String> MONTH_NAME_MAP = ImmutableMap.<Long, String>builder()
			.put(1L, "Январь")
			.put(2L, "Фавраль")
			.put(3L, "Март")
			.put(4L, "Апрель")
			.put(5L, "Май")
			.put(6L, "Июнь")
			.put(7L, "Июль")
			.put(8L, "Август")
			.put(9L, "Сентябрь")
			.put(10L, "Октябрь")
			.put(11L, "Ноябрь")
			.put(12L, "Декабрь")
		.build();
	//@formatter:on

	public static String toDDEE(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd EE.");
		return date != null ? formatter.format(date) : null;
	}

	public static String toMMMMMYYYY(LocalDate date) {
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
				.appendText(ChronoField.MONTH_OF_YEAR, MONTH_NAME_MAP).appendPattern(" yyyy").toFormatter();
		return date != null ? formatter.format(date) : null;
	}

}
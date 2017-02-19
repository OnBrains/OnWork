package org.onbrains.onwork.env.day;

import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;
import static org.onbrains.onwork.env.day.model.DayType.HOLIDAY;
import static org.onbrains.onwork.env.day.model.DayType.WORK_DAY;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.validation.constraints.NotNull;

import org.onbrains.onwork.env.day.model.Day;
import org.onbrains.onwork.env.day.model.DayType;
import org.onbrains.onwork.env.sequence.IdSequenceService;

import com.google.common.collect.Lists;

@Stateless
public class DayRepository implements Serializable {

	private static final long serialVersionUID = 7967349544017177175L;

	private static final int SATURDAY = 6;
	private static final int SUNDAY = 7;
	public static final List<Integer> HOLIDAY_DAYS = Lists.newArrayList(SATURDAY, SUNDAY);

	@Inject
	private EntityManager em;

	@Inject
	private IdSequenceService idSequence;

	@Inject
	private DayTypeRepository dtr;

	public void createDays(@NotNull Year year) {
		LocalDate dayOfYear = LocalDate.of(year.getValue(), 1, 1);
		LocalDate lastDayOfYear = dayOfYear.with(lastDayOfYear());

		DayType workDay = em.find(DayType.class, WORK_DAY);
		DayType holiday = em.find(DayType.class, HOLIDAY);

		DayType type;
		while (!dayOfYear.equals(lastDayOfYear)) {
			type = !HOLIDAY_DAYS.contains(dayOfYear.get(DAY_OF_WEEK)) ? workDay : holiday;
			create(dayOfYear, type);
			dayOfYear = dayOfYear.plusDays(1);
		}
		markSpecialDays(year);
	}

	public List<Day> findDaysOf(LocalDate month) {
		return em.createNamedQuery(Day.FIND_DAYS_OF_MONTH, Day.class).setParameter("month", month).getResultList();
	}

	public Day find(LocalDate date) {
		try {
			return em.createNamedQuery(Day.FIND_DAY, Day.class).setParameter("date", date).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public long countDaysOf(Year year) {
		return Long.valueOf(em.createNamedQuery(Day.COUNT_DAYS_OF_YEAR).setParameter("year", year.toString())
				.getSingleResult().toString());
	}

	// *****************************************************************************************************************
	// Private methods
	// *****************************************************************************************************************

	private Day create(@NotNull LocalDate date, @NotNull DayType type) {
		Day day = new Day(idSequence.nextValue(Day.class));

		day.setValue(date);
		day.setType(type);

		em.persist(day);
		return day;
	}

	private void markSpecialDays(Year year) {
		DayType festival = em.find(DayType.class, DayType.FESTIVAL);

		Arrays.stream(SpecialDay.values()).forEach(sd -> {
			Day day = find(LocalDate.of(year.getValue(), sd.getMonth(), sd.getDayOfMonth()));
			day.setType(festival);
			day.setDescription(sd.getDescription());
			em.merge(day);
		});
	}

	// *****************************************************************************************************************
	// Inner classes
	// *****************************************************************************************************************

	private enum SpecialDay {

		//@formatter:off
		FIRST_JAN 			(Month.JANUARY, 1, "День новогодних каникул"),
		SECOND_JAN 			(Month.JANUARY, 2, "День новогодних каникул"),
		THIRD_JAN 			(Month.JANUARY, 3, "День новогодних каникул"),
		FOURTH_JAN 			(Month.JANUARY, 4, "День новогодних каникул"),
		FIFTH_JAN 			(Month.JANUARY, 5, "День новогодних каникул"),
		SIXTH_JAN 			(Month.JANUARY, 6, "День новогодних каникул"),
		SEVENTH_JAN 		(Month.JANUARY, 7, "Православное Рождество"),
		EIGHTH_JAN 			(Month.JANUARY, 8, "День новогодних каникул"),
		TWENTY_THIRD_FEB 	(Month.FEBRUARY, 23, "День защитника Отечества"),
		EIGHTH_MAR 			(Month.MARCH, 8, "Международный женский день"),
		FIRST_MAY 			(Month.MAY, 1, "Праздник Весны и Труда"),
		NINTH_MAY 			(Month.MAY, 9, "День победы"),
		TWELFTH_JUN 		(Month.JUNE, 12, "День России"),
		FOURTH_NOV 			(Month.NOVEMBER, 4, "День народного единства");
		//@formatter:on

		private Month month;
		private int dayOfMonth;
		private String description;

		SpecialDay(Month month, int dayOfMonth, String description) {
			this.month = month;
			this.dayOfMonth = dayOfMonth;
			this.description = description;
		}

		public Month getMonth() {
			return month;
		}

		public int getDayOfMonth() {
			return dayOfMonth;
		}

		public String getDescription() {
			return description;
		}

	}

}
package org.onbrains.onwork.converter;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "MonthConverter")
public class MonthConverter implements Converter {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("LLL yyyy");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value != null && !value.isEmpty() ? YearMonth.parse(value, FORMATTER) : null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value != null ? ((YearMonth) value).format(FORMATTER) : null;
	}

}
package org.onbrains.onwork.converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "TimeConverter")
public class TimeConverter implements Converter {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH24:mm");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value != null && !value.isEmpty() ? LocalTime.parse(value, FORMATTER) : null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value != null ? ((LocalTime) value).format(FORMATTER) : null;
	}

}
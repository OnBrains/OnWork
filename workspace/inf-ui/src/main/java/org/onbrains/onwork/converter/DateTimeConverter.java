package org.onbrains.onwork.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "DateTimeConverter")
public class DateTimeConverter implements Converter {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH24:mm");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value != null && !value.isEmpty() ? LocalDateTime.parse(value, FORMATTER) : null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value != null ? ((LocalDateTime) value).format(FORMATTER) : null;
	}

}
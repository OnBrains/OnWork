package org.onbrains.onwork.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "DateConverter")
public class DateConverter implements Converter {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value != null && !value.isEmpty() ? LocalDate.parse(value, FORMATTER) : null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value != null ? ((LocalDate) value).format(FORMATTER) : null;
	}

}
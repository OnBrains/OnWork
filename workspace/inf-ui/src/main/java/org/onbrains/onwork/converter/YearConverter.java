package org.onbrains.onwork.converter;

import java.time.Year;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "YearConverter")
public class YearConverter implements Converter {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy");

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value != null && !value.isEmpty() ? Year.parse(value, FORMATTER) : null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value != null ? ((Year) value).format(FORMATTER) : null;
	}

}
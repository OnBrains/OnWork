package org.onbrains.onwork.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.onbrains.onwork.inf.modelbase.Identifiable;
import org.onbrains.onwork.util.EntityManagerUtils;

@FacesConverter(forClass = Identifiable.class)
public class IdentityConverter implements Converter, Serializable {

	private static final long serialVersionUID = -111112585584227958L;

	@Inject
	private EntityManagerUtils emu;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return emu.getAsObject(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

}
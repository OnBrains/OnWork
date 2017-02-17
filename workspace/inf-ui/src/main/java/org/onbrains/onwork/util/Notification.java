package org.onbrains.onwork.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Notification {

	public static void addMessage(String elementId, String message) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(elementId, new FacesMessage(message));
	}

	public static void addMessage(String elementId, String message, String description) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ctx.addMessage(elementId, new FacesMessage(message, description));
	}

}
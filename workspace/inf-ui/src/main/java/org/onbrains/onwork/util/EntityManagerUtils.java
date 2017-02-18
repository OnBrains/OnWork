package org.onbrains.onwork.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class EntityManagerUtils implements Serializable {

	private static final long serialVersionUID = 6963476283283930527L;

	private static final Pattern SERIALIZED_FORMAT = Pattern.compile("(.*)\\[id=([0-9])+\\]");

	@Inject
	private EntityManager em;

	public Object getAsObject(String value) {
		if (value == null || value.isEmpty())
			return null;

		Matcher matcher = SERIALIZED_FORMAT.matcher(value);
		if (!matcher.matches()) {
			throw new ConverterException();
		}

		Class<?> entityClass = null;
		Long id = null;
		try {
			entityClass = Class.forName(matcher.group(1));
			id = Long.parseLong(matcher.group(2));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return em.find(entityClass, id);
	}

}
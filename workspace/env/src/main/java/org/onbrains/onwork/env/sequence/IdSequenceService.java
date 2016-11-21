package org.onbrains.onwork.env.sequence;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.onbrains.onwork.inf.exception.SystemException;
import org.onbrains.onwork.inf.modelbase.Identifiable;
import org.onbrains.onwork.inf.modelbase.SequenceDefinition;

@Stateless
public class IdSequenceService implements Serializable {

	private static final long serialVersionUID = 1730768321569694455L;

	private static final String GET_NEXT_VALUE = "select nextval(:sequence_name)";
	private static final String GET_CURRENT_VALUE = "select currval(:sequence_name)";

	@Inject
	private EntityManager em;

	/**
	 * @param clazz
	 *            класс, для которого нужно получить <b>ID</b>.
	 * @return значение сгенерированного <b>ID</b>.
	 */
	public Long nextValue(Class<? extends Identifiable> clazz) {
		return Long.valueOf(em.createNativeQuery(GET_NEXT_VALUE).setParameter("sequence_name", getSequenceName(clazz))
				.getSingleResult().toString());
	}

	/**
	 * @param clazz
	 *            класс, для которого нужно узнать текущий(последний использованный) <b>ID</b>.
	 * @return значение текущего(последнего использованного) <b>ID</b>.
	 */
	public Long currentValue(Class<? extends Identifiable> clazz) {
		return Long.valueOf(em.createNativeQuery(GET_CURRENT_VALUE)
				.setParameter("sequence_name", getSequenceName(clazz)).getSingleResult().toString());
	}

	/**
	 * @param clazz
	 *            класс, для которого нужно определить генератор.
	 * @return Имя генератора.
	 */
	private String getSequenceName(Class<? extends Identifiable> clazz) {
		if (clazz.isAnnotationPresent(SequenceDefinition.class)) {
			SequenceDefinition sd = clazz.getAnnotation(SequenceDefinition.class);
			return sd.name();
		} else {
			throw new SystemException(
					String.format("Есть класс %s, у которого нет @SequenceDefinition", clazz.getCanonicalName()));
		}
	}

}
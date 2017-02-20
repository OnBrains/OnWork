package org.onbrains.onwork.env.event;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.onbrains.onwork.env.event.model.AbstractEventType;
import org.onbrains.onwork.env.event.model.EventType;
import org.onbrains.onwork.env.event.model.FixedTimeEventType;
import org.onbrains.onwork.env.event.model.WorkEventType;
import org.onbrains.onwork.env.sequence.IdSequenceService;

/**
 * Репозиторий для работы с типами событий:
 * <ul>
 * <li>{@link org.onbrains.onwork.env.event.model.EventType}</li>
 * <li>{@link org.onbrains.onwork.env.event.model.WorkEventType}</li>
 * <li>{@link org.onbrains.onwork.env.event.model.FixedTimeEventType}</li>
 * </ul>
 *
 * @author Oleg Naumov
 */
@Stateless
public class EventTypeRepository implements Serializable {

	private static final long serialVersionUID = -1821675891746124034L;

	@Inject
	private EntityManager em;

	@Inject
	private IdSequenceService iss;

	public EventType createEventType(@NotNull String name, String icon, String iconColor, String description) {
		EventType instance = new EventType(iss.nextValue(EventType.class));

		initDefaultProperty(instance, name, icon, iconColor, description);

		em.persist(instance);
		return instance;
	}

	public WorkEventType createWorkEventType(@NotNull String name, String icon, String iconColor, String description) {
		WorkEventType instance = new WorkEventType(iss.nextValue(WorkEventType.class));

		initDefaultProperty(instance, name, icon, iconColor, description);

		em.persist(instance);
		return instance;
	}

	public FixedTimeEventType createFixedTimeEventType(@NotNull String name, @NotNull LocalTime fixedTime, String icon,
			String iconColor, String description) {
		FixedTimeEventType instance = new FixedTimeEventType(iss.nextValue(FixedTimeEventType.class));

		initDefaultProperty(instance, name, icon, iconColor, description);
		instance.setFixedTime(fixedTime);

		em.persist(instance);
		return instance;
	}

	public List<EventType> findAllEventTypes() {
		return em.createNamedQuery(EventType.FIND_ALL, EventType.class).getResultList();
	}

	public List<WorkEventType> findAllWorkEventTypes() {
		return em.createNamedQuery(WorkEventType.FIND_ALL, WorkEventType.class).getResultList();
	}

	public List<FixedTimeEventType> findAllFixedTimeEventTypes() {
		return em.createNamedQuery(FixedTimeEventType.FIND_ALL, FixedTimeEventType.class).getResultList();
	}

	// *****************************************************************************************************************
	// Private methods
	// *****************************************************************************************************************

	private void initDefaultProperty(AbstractEventType instance, String name, String icon, String iconColor,
			String description) {
		instance.setName(name);
		instance.setIcon(icon);
		instance.setIconColor(iconColor);
		instance.setDescription(description);
		instance.setActive(true);
	}

}
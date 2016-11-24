package org.onbrains.onwork.env.workday;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.onbrains.onwork.env.day.DayTypeRepository;
import org.onbrains.onwork.env.day.model.DayType;
import org.onbrains.onwork.env.workday.model.WorkDayType;
import org.onbrains.onwork.util.Callback;

/**
 * Created on 24.11.2016 19:24.
 *
 * @author Oleg Naumov
 */
@Named(value = "workDayTypeDirectoryVC")
@ViewScoped
public class WorkDayTypeDirectoryViewController implements Serializable {

	private static final long serialVersionUID = -8945765102305242366L;

	@Inject
	private DayTypeRepository dtr;

	@Inject
	private EntityManager em;

	private List<DayType> types;
	private List<DayType> selectedTypes = new ArrayList<>();

	public List<DayType> getTypes() {
		if (types == null) {
			types = dtr.findAll();
		}
		return types;
	}

	public void toggleSelect(DayType type) {
		if (selectedTypes.contains(type))
			selectedTypes.remove(type);
		else
			selectedTypes.add(type);
	}

	public Callback<WorkDayType> getCallback() {
		return (type -> types.add(type));
	}

	@Transactional
	public void remove() {
		selectedTypes.forEach(type -> {
			types.remove(type);
			em.remove(em.merge(type));
		});
		selectedTypes.clear();
	}

}
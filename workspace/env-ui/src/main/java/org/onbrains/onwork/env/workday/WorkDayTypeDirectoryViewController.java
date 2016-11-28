package org.onbrains.onwork.env.workday;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
	private WorkDayTypeRepository wdtr;

	@Inject
	private EntityManager em;

	private List<WorkDayType> types;

	public List<WorkDayType> getTypes() {
		if (types == null) {
			types = wdtr.findAll();
		}
		return types;
	}

	public Callback<WorkDayType> getCallback() {
		return (type -> types.add(type));
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public void remove(WorkDayType type) {
		types.remove(type);
		em.remove(em.merge(type));
	}

}
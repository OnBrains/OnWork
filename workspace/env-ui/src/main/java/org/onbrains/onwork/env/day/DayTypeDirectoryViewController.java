package org.onbrains.onwork.env.day;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.onbrains.onwork.env.day.model.DayType;
import org.onbrains.onwork.util.Callback;

/**
 * Created on 27.11.2016 20:47.
 *
 * @author Oleg Naumov
 */
@Named(value = "dayTypeDirectoryVC")
@ViewScoped
public class DayTypeDirectoryViewController implements Serializable {

	private static final long serialVersionUID = -8945765102305242366L;

	@Inject
	private DayTypeRepository dtr;

	@Inject
	private EntityManager em;

	private List<DayType> types;

	public List<DayType> getTypes() {
		if (types == null) {
			types = dtr.findAll();
		}
		return types;
	}

	public Callback<DayType> getCallback() {
		return (type -> types.add(type));
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public void remove(DayType type) {
		types.remove(type);
		em.remove(em.merge(type));
	}

}
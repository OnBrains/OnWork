package org.onbrains.onwork.env.day.model;

import static org.onbrains.onwork.inf.dataaccess.utils.EntityManagerUtils.queryCachedEntities;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DiscriminatorOptions;
import org.onbrains.onwork.inf.modelbase.BusinessDirectory;

@Entity
@Access(AccessType.FIELD)
@Table(schema = "system", name = "day_type", uniqueConstraints = @UniqueConstraint(name = "uc_day_type", columnNames = { "name" }))
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = DayType.CACHE_REGION_NAME)
public class DayType extends BusinessDirectory {

	private static final long serialVersionUID = -4536998624025275478L;

	public static final String CACHE_REGION_NAME = "org.onbrains.onwork.evn-cache-region";

	public static final long WORK_DAY = 1;
	public static final long HOLIDAY = 2;
	public static final long SHORT_DAY = 3;

	public static DayType workDay(@NotNull EntityManager em) {
		return find(WORK_DAY, em);
	}

	public static DayType holiday(@NotNull EntityManager em) {
		return find(HOLIDAY, em);
	}

	public static DayType shorDay(@NotNull EntityManager em) {
		return find(SHORT_DAY, em);
	}

	public static DayType find(@NotNull long id, @NotNull EntityManager em) {
		return em.find(DayType.class, id);
	}

	public static List<DayType> values(@NotNull EntityManager em) {
		return queryCachedEntities(em, DayType.class, CACHE_REGION_NAME);
	}

	@Column(length = 128, nullable = false)
	private String name;

	@Column(nullable = false)
	private float factor;

	@Column(nullable = false)
	private boolean sys;

	@Version
	private Long version;

	protected DayType() {
	}

	public DayType(Long id) {
		super(id);
	}

	@Override
	public String getObjectName() {
		this.getId();
		return getName();
	}

	// *****************************************************************************************************************
	// Simple getters and setters
	// *****************************************************************************************************************

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getFactor() {
		return factor;
	}

	public void setFactor(float factor) {
		this.factor = factor;
	}

	@Override
	public boolean isSys() {
		return sys;
	}

	public void setSys(boolean sys) {
		this.sys = sys;
	}

}
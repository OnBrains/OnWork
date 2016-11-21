package org.onbrains.onwork.inf.modelbase;

import java.io.Serializable;

public class SuperClass implements Identifiable, Named, Serializable {

	private static final long serialVersionUID = 224675911426788837L;

	private Long id;

	protected SuperClass() {
	}

	public SuperClass(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		SuperClass superClass = (SuperClass) o;

		return id != null ? id.equals(superClass.id) : superClass.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getObjectName() {
		return this.toString();
	}

}
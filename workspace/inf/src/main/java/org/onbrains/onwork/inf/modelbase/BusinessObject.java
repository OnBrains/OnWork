package org.onbrains.onwork.inf.modelbase;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SequenceDefinition
public class BusinessObject extends SuperClass {

	private static final long serialVersionUID = -8023548170626797515L;

	protected BusinessObject() {
	}

	public BusinessObject(Long id) {
		super(id);
	}

	@Id
	@Override
	@Access(AccessType.PROPERTY)
	public Long getId() {
		return super.getId();
	}

}
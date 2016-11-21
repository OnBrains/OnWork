package org.onbrains.onwork.inf.modelbase;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
@SequenceDefinition(name = "system.gen_directory_object_id")
public class BusinessDirectory extends SuperClass {

	private static final long serialVersionUID = -2884191263077950366L;

	protected BusinessDirectory() {
	}

	public BusinessDirectory(Long id) {
		super(id);
	}

	@Id
	@Override
	@Access(AccessType.PROPERTY)
	public Long getId() {
		return super.getId();
	}

	@Transient
	public boolean isSys() {
		return false;
	}

}
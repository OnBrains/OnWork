package org.onbrains.onwork.env.employee;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.onbrains.onwork.env.employee.model.Employee;

@Stateless
public class EmployeeRepository implements Serializable {

	private static final long serialVersionUID = -804995853342549565L;

	@Inject
	private EntityManager em;

	public Employee find() {
		return em.find(Employee.class, 1L);
	}

}
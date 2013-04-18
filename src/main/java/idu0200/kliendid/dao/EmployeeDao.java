package idu0200.kliendid.dao;

import idu0200.kliendid.model.Employee;

import javax.persistence.EntityManager;

public class EmployeeDao extends DaoBase<Employee> {

    public EmployeeDao(EntityManager entityManager) {
        super(entityManager, Employee.class);
    }
}

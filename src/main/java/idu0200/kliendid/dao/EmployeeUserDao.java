package idu0200.kliendid.dao;

import idu0200.kliendid.model.Employee;
import idu0200.kliendid.model.EmployeeUser;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EmployeeUserDao extends DaoBase<EmployeeUser> {

    public EmployeeUserDao(EntityManager entityManager) {
        super(entityManager, EmployeeUser.class);
    }

    public Employee auth(String username, String password) throws Exception {
        Query q = em.createNamedQuery("EmployeeUser.auth");
        q.setParameter("username", username).setParameter("password", password).setMaxResults(1);

        List<EmployeeUser> users = (List<EmployeeUser>)q.getResultList(); // Collections.checkedList(q.getResultList(), EmployeeUser.class);
        if (users.size() == 0) {
            throw new Exception("No user found");
        }
        return users.get(0).getEmployee();
    }
}

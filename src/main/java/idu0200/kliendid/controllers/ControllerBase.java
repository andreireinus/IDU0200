package idu0200.kliendid.controllers;

import idu0200.kliendid.dao.EmployeeDao;
import idu0200.kliendid.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ControllerBase extends HttpServlet {
    private static EntityManagerFactory entityManagerFactory;

    protected EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("idu0200");
        }
        return entityManagerFactory.createEntityManager();
    }

    private HttpSession session;
    protected void ensureSession(HttpServletRequest request) {
        session = request.getSession(true);
    }
    protected HttpSession getSession() {
        return session;
    }

    protected long getUserId() {
        Object value = getSession().getAttribute("user_id");
        if (value == null) {
            return 0;
        }
        return (long) value;
    }

    protected void setUserId(long id) {
        getSession().setAttribute("user_id", id);
    }


    public Employee getCurrentUser() {
        if (!isAuthenticated()) {
            return null;
        }

        EntityManager em = getEntityManager();
        EmployeeDao db = new EmployeeDao(em);
        Employee employee = db.getById(getUserId());
        em.close();

        return employee;
    }

    protected boolean isAuthenticated() {
        return (getUserId() > 0);
    }
}

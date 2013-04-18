package idu0200.kliendid.controllers;

import idu0200.kliendid.dao.EmployeeUserDao;
import idu0200.kliendid.model.Employee;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthController extends ControllerBase {
    @Override
    public void processAction(String action, HttpServletRequest request, HttpServletResponse response) {
        super.processAction(action, request, response);
        if (action.equals("index")) {
            index(request, response);
        } else if (action.equals("login")) {
            login(request, response);
        } else if (action.equals("logout")) {
            logout(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        getSession().invalidate();
        redirectTo(request, response, "auth", "index");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = getEntityManager();
        EmployeeUserDao dao = new EmployeeUserDao(em);

        try {
            Employee employee = dao.auth(request.getParameter("username"), request.getParameter("password"));

            setAuthedUserId(employee.getId());

            redirectTo(request, response, "customer", "index");
        } catch (Exception e) {
            handleException(e, request, response);
        }

        em.close();
    }


    private void index(HttpServletRequest request, HttpServletResponse response) {
        includeJsp("auth/login.jsp", request, response);
    }
}

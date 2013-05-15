package idu0200.kliendid.controllers;

import idu0200.kliendid.common.AjaxErrorResponse;
import idu0200.kliendid.common.AjaxRequest;
import idu0200.kliendid.dao.EmployeeUserDao;
import idu0200.kliendid.model.Employee;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/user", name = "User")
public class UserController extends ControllerBase {
    public void isLoggedIn(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        writeResponse(response, isAuthenticated());
    }

    public void auth(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager()                         ;
        EmployeeUserDao dao = new EmployeeUserDao(em);

        try {
            Employee employee = dao.auth(String.valueOf(ajaxRequest.getParameter("username")), String.valueOf(ajaxRequest.getParameter("password")));
            setUserId(employee.getId());

            writeResponse(response, employee);
        } catch (Exception e) {
            writeResponse(response, new AjaxErrorResponse(e.getMessage()));
        }
    }

    public void logout(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        getSession().invalidate();
        writeResponse(response, true);
    }


}

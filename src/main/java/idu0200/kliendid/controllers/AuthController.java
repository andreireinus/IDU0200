package idu0200.kliendid.controllers;

import idu0200.kliendid.dao.EmployeeUserDao;
import idu0200.kliendid.model.Employee;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Auth", urlPatterns = "/auth")
public class AuthController extends XControllerBase {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("auth/login.html");
        view.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ensureSession(request);
        EntityManager em = getEntityManager();
        EmployeeUserDao dao = new EmployeeUserDao(em);

        try {
            Employee employee = dao.auth(request.getParameter("username"), request.getParameter("password"));
            setAuthedUserId(employee.getId());

            response.sendRedirect("r");
            return;
        } catch (Exception e) {
            doGet(request, response);
        }
    }
}

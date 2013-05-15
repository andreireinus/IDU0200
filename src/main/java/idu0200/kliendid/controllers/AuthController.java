package idu0200.kliendid.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Auth", urlPatterns = "/api/auth")
public class AuthController extends ControllerBase {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ensureSession(request);
//        EntityManager em = getEntityManager();
//        EmployeeUserDao dao = new EmployeeUserDao(em);
//
//        try {
//            Employee employee = dao.auth(request.getParameter("username"), request.getParameter("password"));
//            setAuthedUserId(employee.getId());
//
//            response.sendRedirect("r");
//            return;
//        } catch (Exception e) {
//            doGet(request, response);
//        }
    }
}

package idu0200.kliendid.controllers;

import idu0200.kliendid.dao.EmployeeDao;
import idu0200.kliendid.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class XControllerBase extends HttpServlet {
    private HttpSession session;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    protected boolean allowAnonymousAccess() {
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void processAction(String actionName, HttpServletRequest request, HttpServletResponse response) {
        ensureSession(request);
    }

    protected void includeJsp(String jspFile, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher view = request.getRequestDispatcher(jspFile);
            view.include(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("message", e.getMessage());
        includeJsp("layout/error.jsp", request, response);
    }

    protected void redirectTo(HttpServletRequest request, HttpServletResponse response, String controller, String action) {
        request.setAttribute("controller", controller);
        request.setAttribute("action", action);
        includeJsp("layout/redirect.jsp", request, response);
    }

    protected void redirectTo(HttpServletRequest request, HttpServletResponse response, String controller, String action, long id) {
        request.setAttribute("id", id);
        redirectTo(request, response, controller, action);
    }

    private void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) {

    }

    protected void ensureSession(HttpServletRequest request) {
        session = request.getSession(true);
    }

    protected HttpSession getSession() {
        if (session == null) {
        }
        return session;
    }

    protected long getAuthedUserId() {
        Object value = getSession().getAttribute("user_id");
        if (value == null) {
            return 0;
        }
        return (long) value;
    }

    protected void setAuthedUserId(long id) {
        getSession().setAttribute("user_id", id);
    }


    protected long getId(HttpServletRequest request) {
        if (request.getParameter("id") == null) {
            return 0;
        }
        return Long.parseLong(request.getParameter("id"));
    }



    protected Timestamp getNow() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }


}

package idu0200.kliendid.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Router", urlPatterns = "/r")
public class RouterController extends ControllerBase {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ensureSession(request);

        String controllerName = request.getParameter("c");
        if (controllerName == null) {
            controllerName = "auth";
        }

        String actionName = request.getParameter("a");
        if (actionName == null) {
            actionName = "index";
        }

        if (!isAuthenticated() && !controllerName.equals("auth")) {
            controllerName = "auth";
            actionName = "index";
        }

        ControllerBase controller = new ControllerBase();
        if (controllerName.equals("auth")) {
            controller = new AuthController();
        } else if (controllerName.equals("customer")) {
            controller = new CustomerController();
        } else if (controllerName.equals("groups")) {
            controller = new GroupsController();
        }

        request.setAttribute("controller", controllerName);

        includeJsp("layout/header.jsp", request, response);

        if (isAuthenticated()) {
            includeJsp("layout/navbar.jsp", request, response);
        }

        controller.processAction(actionName, request, response);
        includeJsp("layout/templates.jsp", request, response);
        includeJsp("layout/footer.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

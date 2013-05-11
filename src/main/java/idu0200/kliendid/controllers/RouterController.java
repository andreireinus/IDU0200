package idu0200.kliendid.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Router", urlPatterns = "/r")
public class RouterController extends XControllerBase {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ensureSession(request);

        if (!isAuthenticated()) {
            response.sendRedirect("auth");
            return;
        }

        RequestDispatcher view = request.getRequestDispatcher("layout.jsp");
        view.include(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

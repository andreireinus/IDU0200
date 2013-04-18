package idu0200.kliendid.controllers;

import idu0200.kliendid.common.ValidationResult;
import idu0200.kliendid.dao.CustomerDao;
import idu0200.kliendid.model.Customer;
import idu0200.kliendid.validators.CustomerValidator;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerController extends ControllerBase {
    @Override
    public void processAction(String actionName, HttpServletRequest request, HttpServletResponse response) {
        super.processAction(actionName, request, response);

        if (actionName.equals("index")) {
            index(request, response);
        } else if (actionName.equals("new")) {
            actionNew(request, response);
        } else if (actionName.equals("submitNew")) {
            submitNew(request, response);
        } else if (actionName.equals("view")) {
            includeJsp("customer/view.jsp", request, response);
        } else {
            System.out.println("Unknown action: " + actionName);
        }
    }

    private void submitNew(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = getEntityManager();

        CustomerValidator validator = new CustomerValidator();
        ValidationResult<Customer> result = validator.validate(request.getParameterMap(), new Customer());

        if (result.isValid()) {
            try {
                em.getTransaction().begin();

                // Do magic

                em.getTransaction().commit();
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                throw e;
            }
        }

        includeJsp("customer/form.jsp", request, response);
    }

    private void actionNew(HttpServletRequest request, HttpServletResponse response) {

        includeJsp("customer/form.jsp", request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = getEntityManager();
        CustomerDao db = new CustomerDao(em);
        request.setAttribute("customerList", db.getList());

        includeJsp("customer/index.jsp", request, response);
        em.close();
    }
}

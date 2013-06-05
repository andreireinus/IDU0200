package idu0200.kliendid.controllers;

import idu0200.kliendid.common.AjaxRequest;
import idu0200.kliendid.common.ValidationResult;
import idu0200.kliendid.dao.CustomerDao;
import idu0200.kliendid.model.Customer;
import idu0200.kliendid.validators.CustomerValidator;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/customer", name = "Customer")
public class CustomerController extends ControllerBase {
    public void list(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();

        CustomerDao db = new CustomerDao(em);

        writeResponse(response, db.getList());

        em.close();
    }

    public void getById(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();
        CustomerDao db = new CustomerDao(em);

        writeResponse(response, db.getById(ajaxRequest.getId()));
        em.close();
    }

    public void add(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer = new Customer();

        CustomerValidator validator = new CustomerValidator();
        ValidationResult<Customer> result = validator.validate(ajaxRequest.getValues(), customer);

        EntityManager em = getEntityManager();

        if (result.isValid()) {
            try {
                em.getTransaction().begin();
                em.persist(customer);
                em.getTransaction().commit();
                writeResponse(response, customer);
            } catch (Exception e) {
                handleException(e, em);
            }
        } else {
            writeResponse(response, result);
        }
        em.close();

    }

    public void update(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();
        CustomerDao db = new CustomerDao(em);

        Customer customer = db.getById(ajaxRequest.getId());

        CustomerValidator validator = new CustomerValidator();
        ValidationResult<Customer> result = validator.validate(ajaxRequest.getValues(), customer);

        if (result.isValid()) {
            try {
                em.getTransaction().begin();
                em.persist(customer);
                em.getTransaction().commit();

                writeResponse(response, customer);
            } catch (Exception e) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            }
        } else {
            writeResponse(response, result);
        }

        em.close();
    }

}
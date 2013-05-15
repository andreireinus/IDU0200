package idu0200.kliendid.controllers;

import idu0200.kliendid.common.AjaxRequest;
import idu0200.kliendid.common.ValidationResult;
import idu0200.kliendid.dao.CustomerAddressDao;
import idu0200.kliendid.dao.CustomerDao;
import idu0200.kliendid.model.AddressType;
import idu0200.kliendid.model.Customer;
import idu0200.kliendid.model.CustomerAddress;
import idu0200.kliendid.validators.CustomerAddressValidator;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/address", name = "Address")
public class AddressController extends ControllerBase {
    public void setPrimaryAddress(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            CustomerAddressDao dbAddress = new CustomerAddressDao(em);

            CustomerAddress address = dbAddress.getById(ajaxRequest.getId());
            address.getCustomer().setPrimaryAddress(address);

            em.getTransaction().commit();
            writeResponse(response, true);
        } catch (Exception e) {
            handleException(e, em);
        }

        em.close();
    }

    public void getById(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();

        CustomerAddressDao db = new CustomerAddressDao(em);
        writeResponse(response, db.getById(ajaxRequest.getId()));

        em.close();
    }

    public void add(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            CustomerDao dbCustomer = new CustomerDao(em);
            Long customerId = Long.parseLong((String) ajaxRequest.getParameter("customerId"));
            Customer customer = dbCustomer.getById(customerId);

            CustomerAddress address = new CustomerAddress();
            address.setCustomer(customer);
            address.setAddressType(AddressType.Additional);
            if (customer.getAddresses().size() == 0) {
                address.setAddressType(AddressType.Primary);
            }

            CustomerAddressValidator validator = new CustomerAddressValidator();
            ValidationResult<CustomerAddress> result = validator.validate(ajaxRequest.getValues(), address);

            if (result.isValid()) {
                em.persist(address);
                em.getTransaction().commit();
                writeResponse(response, address);
            } else {
                em.getTransaction().rollback();
                writeResponse(response, result);
            }
        } catch (Exception e) {
            handleException(e, em);
        }

        em.close();
    }

    public void update(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            CustomerAddressDao db = new CustomerAddressDao(em);
            CustomerAddress address = db.getById(ajaxRequest.getId());

            CustomerAddressValidator validator = new CustomerAddressValidator();
            ValidationResult<CustomerAddress> result = validator.validate(ajaxRequest.getValues(), address);

            if (result.isValid()) {
                em.persist(address);
                em.getTransaction().commit();
                writeResponse(response, address);
            } else {
                em.getTransaction().rollback();
                writeResponse(response, result);
            }
        } catch (Exception e) {
            handleException(e, em);
        }
        em.close();
    }
}

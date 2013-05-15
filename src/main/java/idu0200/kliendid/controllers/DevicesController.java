package idu0200.kliendid.controllers;

import idu0200.kliendid.common.AjaxRequest;
import idu0200.kliendid.common.ValidationResult;
import idu0200.kliendid.dao.CommunicationDeviceDao;
import idu0200.kliendid.dao.CommunicationDeviceTypeDao;
import idu0200.kliendid.dao.CustomerDao;
import idu0200.kliendid.model.CommunicationDevice;
import idu0200.kliendid.model.CommunicationDeviceType;
import idu0200.kliendid.model.Customer;
import idu0200.kliendid.validators.CommunicationDeviceValidator;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/devices", name = "Devices")
public class DevicesController extends ControllerBase {
    public void delete(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();

        CommunicationDeviceDao db = new CommunicationDeviceDao(em);
        em.remove(db.getById(ajaxRequest.getId()));
        writeResponse(response, true);

        em.close();
    }

    public void getById(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();

        CommunicationDeviceDao db = new CommunicationDeviceDao(em);
        writeResponse(response, db.getById(ajaxRequest.getId()));

        em.close();
    }

    public void getTypes(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();

        CommunicationDeviceTypeDao db = new CommunicationDeviceTypeDao(em);
        writeResponse(response, db.getList());

        em.close();
    }

    public void add(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();

            CustomerDao dbCustomer = new CustomerDao(em);
            Long customerId = Long.parseLong((String) ajaxRequest.getParameter("customerId"));
            Customer customer = dbCustomer.getById(customerId);

            CommunicationDeviceTypeDao dbType = new CommunicationDeviceTypeDao(em);
            CommunicationDeviceType type = dbType.getById((Long) ajaxRequest.getParameter("typeId"));


            CommunicationDevice device = new CommunicationDevice();
            device.setCreated(getCurrentTimestamp());
            device.setCustomer(customer);
            device.setType(type);

            CommunicationDeviceValidator validator = new CommunicationDeviceValidator();
            ValidationResult<CommunicationDevice> result = validator.validate(ajaxRequest.getValues(), device);

            if (result.isValid()) {
                em.persist(device);
                em.getTransaction().commit();
                writeResponse(response, device);
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

            CommunicationDeviceDao db = new CommunicationDeviceDao(em);
            CommunicationDeviceTypeDao dbType = new CommunicationDeviceTypeDao(em);
            CommunicationDeviceType type = dbType.getById((Long) ajaxRequest.getParameter("typeId"));


            CommunicationDevice device = db.getById(ajaxRequest.getId());
            device.setType(type);

            CommunicationDeviceValidator validator = new CommunicationDeviceValidator();
            ValidationResult<CommunicationDevice> result = validator.validate(ajaxRequest.getValues(), device);

            if (result.isValid()) {
                em.persist(device);
                em.getTransaction().commit();
                writeResponse(response, device);
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

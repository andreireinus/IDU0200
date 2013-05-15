package idu0200.kliendid.controllers;

import idu0200.kliendid.common.AjaxRequest;
import idu0200.kliendid.dao.CustomerDao;
import idu0200.kliendid.dao.GroupDao;
import idu0200.kliendid.dao.GroupDefinitionDao;
import idu0200.kliendid.model.Group;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/groups", name = "Groups")
public class GroupsController extends ControllerBase {
    public void list(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();

        GroupDefinitionDao db= new GroupDefinitionDao(em);
        writeResponse(response, db.getGroupsList());

        em.close();
    }

    public void add(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            GroupDefinitionDao db = new GroupDefinitionDao(em);
            CustomerDao dbCustomer = new CustomerDao(em);

            Group group = new Group();
            group.setCreated(getCurrentTimestamp());
            group.setCreatedBy(getCurrentUser());
            group.setDefinition(db.getById(ajaxRequest.getId()));
            group.setCustomer(dbCustomer.getById((Long)ajaxRequest.getParameter("customerId")));

            em.persist(group);

            em.getTransaction().commit();
            writeResponse(response, group);
        } catch (Exception e) {
            handleException(e, em);
        }
        em.close();
    }


    public void remove(AjaxRequest ajaxRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            GroupDao db = new GroupDao(em);

            em.remove(db.getById(ajaxRequest.getId()));

            em.getTransaction().commit();
            writeResponse(response, true);
        } catch (Exception e) {
            handleException(e, em);
        }
        em.close();
    }
}

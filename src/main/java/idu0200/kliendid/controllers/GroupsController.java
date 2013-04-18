package idu0200.kliendid.controllers;

import idu0200.kliendid.common.ValidationResult;
import idu0200.kliendid.dao.GroupDefinitionDao;
import idu0200.kliendid.model.GroupDefinition;
import idu0200.kliendid.validators.CustomerGroupDefinitionValidator;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GroupsController extends ControllerBase {
    @Override
    public void processAction(String actionName, HttpServletRequest request, HttpServletResponse response) {
        super.processAction(actionName, request, response);

        EntityManager em = getEntityManager();
        GroupDefinitionDao db = new GroupDefinitionDao(em);

        if (actionName.equals("index")) {
            request.setAttribute("groups", db.getGroupsList());

            includeJsp("groups/index.jsp", request, response);

        } else if (actionName.equals("new")) {
            request.setAttribute("item", new GroupDefinition());
            includeJsp("groups/form.jsp", request, response);
        } else if (actionName.equals("submit")) {
            CustomerGroupDefinitionValidator validator = new CustomerGroupDefinitionValidator();

            GroupDefinition item = new GroupDefinition();
            if (getId(request) > 0) {
                item = db.getById(getId(request));
            }

            ValidationResult<GroupDefinition> result = validator.validate(request.getParameterMap(), item);
            if (result.isValid()) {
                try {
                    em.getTransaction().begin();
                    em.persist(result.resultObject);
                    em.getTransaction().commit();

                    redirectTo(request, response, "groups", "view", result.resultObject.getId());
                } catch (Exception e) {
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    handleException(e, request, response);
                }
            }
        } else if (actionName.equals("view")) {
            request.setAttribute("item", db.getById(getId(request)));
            includeJsp("groups/form.jsp", request, response);
        }
        em.close();
    }

}

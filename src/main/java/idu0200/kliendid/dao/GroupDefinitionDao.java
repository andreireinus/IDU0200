package idu0200.kliendid.dao;

import idu0200.kliendid.model.GroupDefinition;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GroupDefinitionDao extends DaoBase<GroupDefinition> {

    public GroupDefinitionDao(EntityManager entityManager) {
        super(entityManager, GroupDefinition.class);
    }


    public List<GroupDefinition> getGroupsList() {
        Query q =  em.createNamedQuery("CustomerGroupDefinition.all");
        return (List<GroupDefinition>)q.getResultList();
    }
}

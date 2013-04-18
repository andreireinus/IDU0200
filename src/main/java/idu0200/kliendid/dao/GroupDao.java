package idu0200.kliendid.dao;

import idu0200.kliendid.model.Group;

import javax.persistence.EntityManager;

public class GroupDao extends DaoBase<Group> {

    public GroupDao(EntityManager entityManager) {
        super(entityManager, Group.class);
    }
}

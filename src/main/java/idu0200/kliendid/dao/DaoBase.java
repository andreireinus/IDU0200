package idu0200.kliendid.dao;

import javax.persistence.EntityManager;

public class DaoBase<T> {
    protected Class<T> entityClass;

    protected EntityManager em;

    public DaoBase(EntityManager entityManager, Class<T> entityClass) {
        em = entityManager;
        this.entityClass = entityClass;
    }
    public T getById(long id) {
        return em.find(entityClass, id);
    }
}

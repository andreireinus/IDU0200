package idu0200.kliendid.dao;

import idu0200.kliendid.model.CommunicationDeviceType;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CommunicationDeviceTypeDao extends DaoBase<CommunicationDeviceType> {
    public CommunicationDeviceTypeDao(EntityManager entityManager) {
        super(entityManager, CommunicationDeviceType.class);
    }

    public List<CommunicationDeviceType> getList() {
        Query q = em.createQuery("select t from CommunicationDeviceType t");

        return (List<CommunicationDeviceType>) q.getResultList();
    }
}

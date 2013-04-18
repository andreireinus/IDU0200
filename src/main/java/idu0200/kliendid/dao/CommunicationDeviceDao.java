package idu0200.kliendid.dao;

import idu0200.kliendid.model.CommunicationDevice;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CommunicationDeviceDao extends DaoBase<CommunicationDevice> {

    public CommunicationDeviceDao(EntityManager entityManager, Class<CommunicationDevice> entityClass) {
        super(entityManager, entityClass);
    }

    public List<CommunicationDevice> getListByCustomer(long customerId) {
        Query q = em.createQuery("select d from CommunicationDevice d where d.customer.id = :customerId order by d.orderBy")
                .setParameter("customerId", customerId);

        return (List<CommunicationDevice>)q.getResultList();
    }
}

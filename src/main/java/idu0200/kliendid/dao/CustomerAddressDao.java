package idu0200.kliendid.dao;

import idu0200.kliendid.model.CustomerAddress;

import javax.persistence.EntityManager;

public class CustomerAddressDao extends DaoBase<CustomerAddress> {
    public CustomerAddressDao(EntityManager entityManager) {
        super(entityManager, CustomerAddress.class);
    }
}

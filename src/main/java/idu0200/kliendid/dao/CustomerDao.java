package idu0200.kliendid.dao;

import idu0200.kliendid.common.SearchOptions;
import idu0200.kliendid.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CustomerDao extends DaoBase<Customer> {

    public CustomerDao(EntityManager entityManager) {
        super(entityManager, Customer.class);
    }

    public List<Customer> getList() {
        return getList(new SearchOptions());
    }

    public List<Customer> getList(SearchOptions options) {
        Query q = em.createNamedQuery("Customer.all");

        return (List<Customer>)q.getResultList();
    }

    public List<Customer> search(String search) {
        Query q = em.createNamedQuery("Customer.search").setParameter("s", "%" + search.toLowerCase() + "%");

        return (List<Customer>)q.getResultList();
    }
}

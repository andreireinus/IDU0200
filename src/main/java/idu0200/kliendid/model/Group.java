package idu0200.kliendid.model;

import javax.persistence.*;
import java.sql.Timestamp;

@javax.persistence.Table(name = "customer_group")
@Entity
public class Group {
    private long id;

    @Column(name = "customer_group", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer", referencedColumnName = "customer")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private GroupDefinition definition;

    @ManyToOne
    @JoinColumn(name = "c_group", referencedColumnName = "c_group")
    public GroupDefinition getDefinition() {
        return definition;
    }

    public void setDefinition(GroupDefinition cGroup) {
        this.definition = cGroup;
    }

    private Timestamp created;

    @Column(name = "created", nullable = true, insertable = true, updatable = true, length = 29, precision = 6)
    @Basic
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    private Employee createdBy;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "employee")
    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group that = (Group) o;

        if (id != that.id) return false;
        if (definition != null ? !definition.equals(that.definition) : that.definition != null) return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (definition != null ? definition.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        return result;
    }
}

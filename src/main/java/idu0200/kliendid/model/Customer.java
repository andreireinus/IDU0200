package idu0200.kliendid.model;

import flexjson.JSON;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.all", query = "select c from Customer c"),
        @NamedQuery(name = "Customer.search", query = "select distinct c " +
                "from Customer c " +
                "join c.addresses ca " +
                "join c.devices d " +
                "where " +
                "c.firstName like :s " +
                "or c.lastName like :s " +
                "or ca.address like :s " +
                "or ca.county like :s " +
                "or ca.house like :s " +
                "or ca.townCounty like :s " +
                "or ca.zip like :s " +
                "or d.valueText like :s")
})
public class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private String identityCode;
    private String note;
    private Date birthDate;
    private Timestamp created;
    private Timestamp updated;
    private Employee createdBy;
    private Employee updatedBy;
    private Set<Group> groups;
    private Set<CustomerAddress> addresses;
    private Set<CommunicationDevice> devices;

    @Column(name = "customer", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "identity_code", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    @Column(name = "note", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    String getNote() {
        return note;
    }

    void setNote(String note) {
        this.note = note;
    }

    @Column(name = "created", nullable = true, insertable = true, updatable = true, length = 29, precision = 6)
    @Basic
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Column(name = "updated", nullable = true, insertable = true, updatable = true, length = 29, precision = 6)
    @Basic
    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "employee")
    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    @ManyToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "employee")
    public Employee getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Employee updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "birth_date", nullable = true, insertable = true, updatable = true, length = 29, precision = 6)
    @Basic
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (birthDate != null ? !birthDate.equals(customer.birthDate) : customer.birthDate != null) return false;
        if (created != null ? !created.equals(customer.created) : customer.created != null) return false;
        if (createdBy != null ? !createdBy.equals(customer.createdBy) : customer.createdBy != null) return false;
        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
        if (identityCode != null ? !identityCode.equals(customer.identityCode) : customer.identityCode != null)
            return false;
        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null) return false;
        if (note != null ? !note.equals(customer.note) : customer.note != null) return false;
        if (updated != null ? !updated.equals(customer.updated) : customer.updated != null) return false;
        if (updatedBy != null ? !updatedBy.equals(customer.updatedBy) : customer.updatedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (identityCode != null ? identityCode.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (updated != null ? updated.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (updatedBy != null ? updatedBy.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

    @OneToMany
    @JoinColumn(name = "customer", referencedColumnName = "customer")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public boolean inGroup(GroupDefinition definition) {
        Group group = getGroup(definition);
        return (group != null);
    }

    public Group getGroup(GroupDefinition definition) {
        Iterator<Group> i = groups.iterator();
        while (i.hasNext()) {
            Group group = i.next();
            if (group.getDefinition().getId() == definition.getId()) {
                return group;
            }
        }
        return null;
    }

    @OneToMany
    @JoinColumn(name = "customer", referencedColumnName = "customer")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Set<CustomerAddress> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<CustomerAddress> addresses) {
        this.addresses = addresses;
    }

    @OneToMany
    @JoinColumn(name = "customer", referencedColumnName = "customer")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Set<CommunicationDevice> getDevices() {
        return this.devices;
    }

    public void setDevices(Set<CommunicationDevice> devices) {
        this.devices = devices;
    }

    @Transient
    @JSON(include = false)
    public CustomerAddress getPrimaryAddress() {
        for (CustomerAddress address : addresses) {
            if (address.getAddressType() == AddressType.Primary) {
                return address;
            }
        }
        return null;
    }

    public void setPrimaryAddress(CustomerAddress address) {
        for (CustomerAddress a : addresses) {
            if (a.equals(address)) {
                a.setAddressType(AddressType.Primary);
            } else {
                a.setAddressType(AddressType.Additional);
            }
        }
    }

}

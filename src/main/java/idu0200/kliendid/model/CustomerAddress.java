package idu0200.kliendid.model;

import javax.persistence.*;

@javax.persistence.Table(name = "cst_address")
@Entity
public class CustomerAddress {
    private long id;
    private Customer customer;
    private String zip;
    private String house;
    private String address;
    private String county;
    private String townCounty;
    private AddressType addressType;

    @Column(name = "cst_address", nullable = false, insertable = true, updatable = false, length = 30, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "customer")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "zip", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column(name = "house", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Column(name = "address", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "county", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Column(name = "town_county", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getTownCounty() {
        return townCounty;
    }

    public void setTownCounty(String townCounty) {
        this.townCounty = townCounty;
    }

    @Column(name = "address_type", nullable = true, insertable = true, updatable = true, length = 30, precision = 0)
    @Enumerated(value = EnumType.ORDINAL)
    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerAddress that = (CustomerAddress) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (addressType != null ? !addressType.equals(that.addressType) : that.addressType != null) return false;
        if (county != null ? !county.equals(that.county) : that.county != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (house != null ? !house.equals(that.house) : that.house != null) return false;
        if (townCounty != null ? !townCounty.equals(that.townCounty) : that.townCounty != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (house != null ? house.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (county != null ? county.hashCode() : 0);
        result = 31 * result + (townCounty != null ? townCounty.hashCode() : 0);
        result = 31 * result + (addressType != null ? addressType.hashCode() : 0);
        return result;
    }
}

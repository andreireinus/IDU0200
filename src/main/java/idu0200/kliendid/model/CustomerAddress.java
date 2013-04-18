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
    private String parish;
    private String townCounty;
    private AddressType addressType;
    private String phone;
    private String sms;
    private String mobile;
    private String email;
    private String note;
    private Long country;

    @Column(name = "cst_address", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    void setId(long id) {
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
    String getZip() {
        return zip;
    }

    void setZip(String zip) {
        this.zip = zip;
    }

    @Column(name = "house", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    String getHouse() {
        return house;
    }

    void setHouse(String house) {
        this.house = house;
    }

    @Column(name = "address", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "county", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    String getCounty() {
        return county;
    }

    void setCounty(String county) {
        this.county = county;
    }

    @Column(name = "parish", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    String getParish() {
        return parish;
    }

    void setParish(String parish) {
        this.parish = parish;
    }

    @Column(name = "town_county", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    String getTownCounty() {
        return townCounty;
    }

    void setTownCounty(String townCounty) {
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

    @Column(name = "phone", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    String getPhone() {
        return phone;
    }

    void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "sms", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    String getSms() {
        return sms;
    }

    void setSms(String sms) {
        this.sms = sms;
    }

    @Column(name = "mobile", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    String getMobile() {
        return mobile;
    }

    void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 30, precision = 0)
    @Basic
    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "note", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
    @Basic
    String getNote() {
        return note;
    }

    void setNote(String note) {
        this.note = note;
    }

    @Column(name = "country", nullable = true, insertable = true, updatable = true, length = 30, precision = 0)
    @Basic
    Long getCountry() {
        return country;
    }

    void setCountry(Long country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerAddress that = (CustomerAddress) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (addressType != null ? !addressType.equals(that.addressType) : that.addressType != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (county != null ? !county.equals(that.county) : that.county != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (house != null ? !house.equals(that.house) : that.house != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (parish != null ? !parish.equals(that.parish) : that.parish != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (sms != null ? !sms.equals(that.sms) : that.sms != null) return false;
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
        result = 31 * result + (parish != null ? parish.hashCode() : 0);
        result = 31 * result + (townCounty != null ? townCounty.hashCode() : 0);
        result = 31 * result + (addressType != null ? addressType.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (sms != null ? sms.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
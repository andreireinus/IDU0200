package idu0200.kliendid.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comm_device")
public class CommunicationDevice {

    private long id;

    @Id
    @Column(name = "comm_device", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    private CommunicationDeviceType communicationDeviceType;

    @ManyToOne
    @JoinColumn(name="comm_device_type", referencedColumnName = "comm_device_type", nullable = true)
    public CommunicationDeviceType getType() {
        return communicationDeviceType;
    }

    public void setType(CommunicationDeviceType type) {
        communicationDeviceType = type;
    }


    private Customer customer;

    @ManyToOne
    @JoinColumn(name="customer", referencedColumnName = "customer", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private String valueText;

    @Column(name = "value_text", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getValueText() {
        return valueText;
    }

    public void setValueText(String valueText) {
        this.valueText = valueText;
    }

    private Long orderBy;

    @Column(name = "orderb", nullable = true, insertable = true, updatable = true, length = 30, precision = 0)
    @Basic
    public Long getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Long orderb) {
        this.orderBy = orderb;
    }

    private Timestamp created;

    @Column(name = "created", nullable = true, insertable = true, updatable = false, length = 29, precision = 6)
    @Basic
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommunicationDevice that = (CommunicationDevice) o;

        if (id != that.id) return false;
        if (communicationDeviceType != null ? !communicationDeviceType.equals(that.communicationDeviceType) : that.communicationDeviceType != null)
            return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (customer != null ? !customer.equals(that.customer) : that.customer != null) return false;
        if (orderBy != null ? !orderBy.equals(that.orderBy) : that.orderBy != null) return false;
        if (valueText != null ? !valueText.equals(that.valueText) : that.valueText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (communicationDeviceType != null ? communicationDeviceType.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (valueText != null ? valueText.hashCode() : 0);
        result = 31 * result + (orderBy != null ? orderBy.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }
}

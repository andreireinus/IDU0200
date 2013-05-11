package idu0200.kliendid.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "comm_device_type")
@Entity
public class CommunicationDeviceType {
    private long id;

    @Column(name = "comm_device_type", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @Id
    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    private String name;

    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<CommunicationDevice> devices;

    @OneToMany(mappedBy = "type")
    public List<CommunicationDevice> getDevices() {
        return devices;
    }

    public void setDevices(List<CommunicationDevice> devices)  {
        this.devices = devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommunicationDeviceType that = (CommunicationDeviceType) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

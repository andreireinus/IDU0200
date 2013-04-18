package idu0200.kliendid.model;

import javax.persistence.*;

@Entity
@Table(name = "c_group")
@NamedQueries(
        @NamedQuery(name = "CustomerGroupDefinition.all", query = "select d from GroupDefinition d")
)
public class GroupDefinition {

    public GroupDefinition() {
        setName("");
    }


    private long id;

    @Column(name = "c_group", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupDefinition that = (GroupDefinition) o;

        return id == that.id && !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

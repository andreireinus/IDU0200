package idu0200.kliendid.model;

import javax.persistence.*;

@Entity
@Table(name = "emp_user")
@NamedQuery(name = "EmployeeUser.auth", query = "select e from EmployeeUser e where e.username = :username and e.password = :password")
public class EmployeeUser {
    private long id;

    @Column(name = "emp_user", nullable = false, insertable = true, updatable = true, length = 30, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "employee", referencedColumnName = "employee")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private String username;

    @Column(name = "username", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    @Basic
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String password;

    @Column(name = "passw", nullable = true, insertable = true, updatable = true, length = 300, precision = 0)
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String passw) {
        this.password = passw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeUser that = (EmployeeUser) o;

        return id == that.id && !(employee != null ? !employee.equals(that.employee) : that.employee != null) && !(password != null ? !password.equals(that.password) : that.password != null) && !(username != null ? !username.equals(that.username) : that.username != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}

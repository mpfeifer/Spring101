package mp.test.ebf.Employee;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

    private @Id String id;
    private String name;
    private String surname;
    private String email;
    private Float salary;
    private String company;

    public Employee() {
        this.setId(UUID.randomUUID().toString());
    }

    public Employee(String name, String surname, String email, Float salary, String company) {
        this.setId(UUID.randomUUID().toString());
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.salary = salary;
        this.company = company;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public Float getSalary() { return this.salary; }

    public String getCompany() { return this.company; }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) { this.email = email; }

    public void setSalary(Float salary) { this.salary = salary; }

    public void setCompany(String company) { this.company = company; }

    public void setFromOther(Employee o) {
        setName(o.getName());
        setSurname(o.getSurname());
        setEmail(o.getEmail());
        setSalary(o.getSalary());
        setCompany(o.getCompany());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id
                + ", name='" + this.name + '\''
                + ", surname='" + this.surname + '\''
                + ", email='" + this.email + '\''
                + ", salary='" + this.salary.toString() + '\''
                + ", company='" + this.company + '\''
                + '}';
    }
}
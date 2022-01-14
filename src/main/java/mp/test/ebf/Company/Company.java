package mp.test.ebf.Company;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Company {

    private @Id String id;
    private String name;


    public Company() {
        this.setId(UUID.randomUUID().toString());
    }

    public Company(String name) {
        this.setId(UUID.randomUUID().toString());
        this.name = name;

    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Company))
            return false;
        Company employee = (Company) o;
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
                + '}';
    }

    public void setFromOther(Company company) {
        setName(company.getName());
    }
}
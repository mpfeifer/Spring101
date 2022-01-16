package mp.test.ebf.Company;

import mp.test.ebf.Employee.Employee;
import mp.test.ebf.Employee.EmployeeRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class CompanyController {

    private final CompanyRepository repository;
    private final EmployeeRepository employees;

    CompanyController(CompanyRepository repository, EmployeeRepository employees) {
        this.repository = repository;
        this.employees = employees;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/companies")
    List<Company> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/companies")
    Company newEmployee(@RequestBody Company newCompany) {
        return repository.save(newCompany);
    }

    // Single item

    @GetMapping("/companies/{id}")
    EntityModel<Company> one(@PathVariable String id) {

        Company company = repository.findById(id) //
                .orElseThrow(() -> new CompanyNotFoundException(id));

        return EntityModel.of(company, //
                linkTo(methodOn(CompanyController.class).one(id)).withSelfRel(),
                linkTo(methodOn(CompanyController.class).all()).withRel("companies"));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @PutMapping("/companies/{id}")
    Company replaceCompany(@RequestBody Company newCompany, @PathVariable String id) {

        return repository.findById(id)
                .map(company -> {
                    company.setFromOther(newCompany);
                    return repository.save(company);
                })
                .orElseGet(() -> {
                    newCompany.setId(id);
                    return repository.save(newCompany);
                });
    }

    @DeleteMapping("/companies/{id}")
    void deleteEmployee(@PathVariable String id) {
        repository.deleteById(id);
    }

    @GetMapping("/companies/{id}/avgsalary")
    Double getAverageSalary(@PathVariable String id) {
        return employees.findAll()
                .stream()
                .filter(e -> e.getCompany().equals(id))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0d);
    }

}

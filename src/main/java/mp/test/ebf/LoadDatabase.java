package mp.test.ebf;

import mp.test.ebf.Company.Company;
import mp.test.ebf.Company.CompanyRepository;
import mp.test.ebf.Employee.Employee;
import mp.test.ebf.Employee.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Value("${testType:interactive}")
    private String testType;

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        return args -> {
            log.info("testType = " + testType);
            switch (testType) {
                case "performance":
                    break;
                case "interactive": {
                    Company evilEmpireCompany = new Company("Evil Empire");
                    String evilEmpireId = evilEmpireCompany.getId();
                    Employee employeeVader = new Employee("Darth", "Vader", "vader@evil-empire.com", 200000.0f, evilEmpireId);
                    Employee employeeTyranus = new Employee("Darth", "Tyranus", "tyranus@evil-empire.com", 400000.0f, evilEmpireId);
                    log.info("Preloading " + companyRepository.save(evilEmpireCompany));
                    log.info("Preloading " + employeeRepository.save(employeeVader));
                    log.info("Preloading " + employeeRepository.save(employeeTyranus));
                }
                break;
            }
        };
    }
}
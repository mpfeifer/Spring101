package mp.test.ebf.Employee;

class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(String id) {
        super("Could not find employee " + id);
    }
}
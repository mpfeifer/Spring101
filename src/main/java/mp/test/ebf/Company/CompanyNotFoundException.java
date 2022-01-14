package mp.test.ebf.Company;

class CompanyNotFoundException extends RuntimeException {

    CompanyNotFoundException(String id) {
        super("Could not find employee " + id);
    }
}
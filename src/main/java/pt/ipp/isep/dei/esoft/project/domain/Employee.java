package pt.ipp.isep.dei.esoft.project.domain;
import java.util.Objects;

public class Employee extends Person {
    private final String email;

    public Employee(String email, String name, String passportCardNumber, String taxNumber, String emailAddress, String role,
                    String phoneNumber, String city, String district, String state, String zipCode, String streetName) {
        super(name, passportCardNumber, taxNumber, emailAddress, phoneNumber, role, streetName, city, district, state, zipCode);
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return email.equals(employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */
//    public Employee clone() {
//        return new Employee(this.email);
//    }
    public boolean isAgent() {
        return this.getRole().equalsIgnoreCase("Agent");
    }
}

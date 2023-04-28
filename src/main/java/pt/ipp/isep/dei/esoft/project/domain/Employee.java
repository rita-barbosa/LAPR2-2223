package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Employee extends Person {
    private String agencyId;

    public Employee(String agencyId, String name, String passportCardNumber, String taxNumber, String emailAddress, String role,
                    String phoneNumber, String city, String district, String state, String zipCode, String streetName) {

        super(name, passportCardNumber, taxNumber, emailAddress, phoneNumber, role, streetName, city, district, state, zipCode);
        this.agencyId = agencyId;
    }

    public Employee(String agencyId, String name, String passportCardNumber, String taxNumber, String emailAddress, String role,
                    String phoneNumber, Location location) {

        super(name, passportCardNumber, taxNumber, emailAddress, phoneNumber, role, location);
        this.agencyId = agencyId;
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
        return this.getEmailAddress().equals(employee.getEmailAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmailAddress());
    }

    public boolean hasEmail(String email) {
        return this.getEmailAddress().equals(email);
    }

    public Employee clone() {
        return new Employee(agencyId, this.getName(), this.getPassportCardNumber(), this.getTaxNumber(), this.getEmailAddress(), this.getRole(),
                this.getPhoneNumber(), this.getLocation());
    }

    public boolean isAgent() {
        return this.getRole().equalsIgnoreCase("Agent");
    }


}

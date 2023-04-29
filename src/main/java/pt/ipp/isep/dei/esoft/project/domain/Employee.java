package pt.ipp.isep.dei.esoft.project.domain;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
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

    public Employee(String emailAddress, String roleId) {
        super(emailAddress, roleId);
    }

    public Employee(String emailAddress) {
        super(emailAddress);
    }

    public Employee(String agencyId, String name, String passportCardNumber, String taxNumber, Email emailAddress,
                    List<String> roles, String phoneNumber, Location location) {

        super(name, passportCardNumber, taxNumber, emailAddress, phoneNumber, roles, location);
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

    public Employee clone() {
        return new Employee(agencyId, this.getName(), this.getPassportCardNumber(), this.getTaxNumber(), this.getEmailAddress(), this.getRoles(),
                this.getPhoneNumber(), this.getLocation());
    }

    public boolean hasEmail(String email) {
        return this.getEmailAddress().getEmail().equals(email);
    }

    public boolean isAgent() {
        Boolean valid = false;
        for (String role : getRoles()) {
            if (role.equalsIgnoreCase("agent")) {
                valid = true;
            }
        }
        return valid;
    }


}

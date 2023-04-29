package pt.ipp.isep.dei.esoft.project.domain;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Objects;

public class Employee extends Person {
    private Integer agencyId;
    private static final Integer AGENCY_ID_BY_DEFAULT = 0;

    public Employee(Integer agencyId, String name, String passportCardNumber, String taxNumber, String emailAddress, String role,
                    String phoneNumber, String city, String district, String state, String zipCode, String streetName) {

        super(name, passportCardNumber, taxNumber, emailAddress, phoneNumber, role, streetName, city, district, state, zipCode);
        this.agencyId = agencyId;
    }

    public Employee(String emailAddress, String roleId) {
        super(emailAddress, roleId);
        this.agencyId = AGENCY_ID_BY_DEFAULT;
    }

    public Employee(String emailAddress) {
        super(emailAddress);
        this.agencyId = AGENCY_ID_BY_DEFAULT;
    }

    public Employee(Integer agencyId, String name, String passportCardNumber, String taxNumber, Email emailAddress,
                    List<String> roles, String phoneNumber, Location location) {

        super(name, passportCardNumber, taxNumber, emailAddress, phoneNumber, roles, location);
        this.agencyId = agencyId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return super.equals(o) && Objects.equals(agencyId, employee.agencyId);
    }




}

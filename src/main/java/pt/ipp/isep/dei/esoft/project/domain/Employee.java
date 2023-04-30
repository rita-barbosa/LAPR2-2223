package pt.ipp.isep.dei.esoft.project.domain;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;
import java.util.Objects;

/**
 * The type Employee.
 */
public class Employee extends Person {
    /**
     * The Agency id.
     */
    private Integer agencyId;
    /**
     * The constant AGENCY_ID_BY_DEFAULT.
     */
    private static final Integer AGENCY_ID_BY_DEFAULT = 0;

    /**
     * Instantiates a new Employee.
     *
     * @param agencyId           the agency id where Employee works at
     * @param name               the name of Employee
     * @param passportCardNumber the passport card number of Employee
     * @param taxNumber          the tax number of Employee
     * @param emailAddress       the email address of Employee
     * @param role               the role of Employee
     * @param phoneNumber        the phone number of Employee
     * @param city               the city of Employee's Location
     * @param district           the district of Employee's Location
     * @param state              the state of Employee's Location
     * @param zipCode            the zip code of Employee's Location
     * @param streetName         the street name of Employee's Location
     */
    public Employee(Integer agencyId, String name, String passportCardNumber, String taxNumber, String emailAddress, String role,
                    String phoneNumber, String city, String district, String state, String zipCode, String streetName) {

        super(name, passportCardNumber, taxNumber, emailAddress, phoneNumber, role, streetName, city, district, state, zipCode);
        this.agencyId = agencyId;
    }

    /**
     * Instantiates a new Employee.
     *
     * @param emailAddress the email address of Employee
     * @param roleId       the role id
     */
    public Employee(String emailAddress, String roleId) {
        super(emailAddress, roleId);
        this.agencyId = AGENCY_ID_BY_DEFAULT;
    }

    /**
     * Instantiates a new Employee.
     *
     * @param emailAddress the email address of Employee
     */
    public Employee(String emailAddress) {
        super(emailAddress);
        this.agencyId = AGENCY_ID_BY_DEFAULT;
    }

    /**
     * Instantiates a new Employee.
     *
     * @param agencyId           the agency id where Employee works at
     * @param name               the name of Employee
     * @param passportCardNumber the passport card number of Employee
     * @param taxNumber          the tax number of Employee
     * @param emailAddress       the email address of Employee
     * @param roles              the roles of Employee
     * @param phoneNumber        the phone number of Employee
     * @param location           the location of Employee
     */
    public Employee(Integer agencyId, String name, String passportCardNumber, String taxNumber, Email emailAddress,
                    List<String> roles, String phoneNumber, Location location) {

        super(name, passportCardNumber, taxNumber, emailAddress, phoneNumber, roles, location);
        this.agencyId = agencyId;
    }


    /**
     * Clone employee.
     *
     * @return the employee
     */
    public Employee clone() {
        return new Employee(agencyId, this.getName(), this.getPassportCardNumber(), this.getTaxNumber(), this.getEmailAddress(), this.getRoles(),
                this.getPhoneNumber(), this.getLocation());
    }

    /**
     * Has email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean hasEmail(String email) {
        return this.getEmailAddress().getEmail().equals(email);
    }

    /**
     * Checks if Employee has role "Agent".
     *
     * @return the confirmation
     */
    public boolean isAgent() {
        Boolean valid = false;
        for (String role : getRoles()) {
            if (role.equalsIgnoreCase("agent")) {
                valid = true;
            }
        }
        return valid;
    }


    /**
     * Equals boolean.
     *
     * @param o the o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return super.equals(o) && Objects.equals(agencyId, employee.agencyId);
    }

}

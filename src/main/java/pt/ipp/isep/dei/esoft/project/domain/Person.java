package pt.ipp.isep.dei.esoft.project.domain;

import javax.management.relation.Role;

public class Person {
    private String name;
    private String taxNumber;
    private String emailAddress;
    private String phoneNumber;
    private String passportCardNumber;
    private String role;
    private Location location;

    /**
     * Creates a Person object with the given parameters.
     *
     * @param name               The name of the person.
     * @param passportCardNumber The passport card number of the person.
     * @param taxNumber          The tax number of the person.
     * @param emailAddress       The email address of the person.
     * @param phoneNumber        The phone number of the person.
     * @param role               The role of the person.
     * @param city               The city of the person.
     * @param state              The state of the person.
     * @param zipCode            The zip code of the person.
     * @param streetName         The street name of the person.
     */
    public Person(String name, String passportCardNumber, String taxNumber, String emailAddress, String phoneNumber,
                  String role, String streetName, String city, String district, String state, String zipCode) {
        this.name = name;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.location = new Location(streetName, city, district, state, zipCode);
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Person(String name, String passportCardNumber, String taxNumber, String emailAddress, String phoneNumber,
                  String role, Location location) {
        this.name = name;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.location = location;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    /**
     * Validates the Employee object by checking if any of its required fields are empty.
     *
     * @param employee The Employee object to be validated.
     * @return True if all required fields are not empty, false otherwise.
     */
    public boolean validateEmployee(Employee employee) {
        if (employee.getName().isEmpty() ||
                employee.getTaxNumber().isEmpty() ||
                employee.getLocation().equals("")||
                employee.getEmailAddress().isEmpty() ||
                employee.getPhoneNumber().isEmpty() ||
                employee.getRole() == null) {
            return false;

            // CHECK LOCATION IN THIS METHOD
        }
        return true;
    }


    /**
     * Returns the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name the name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the passport card number of the person.
     *
     * @return the passport card number of the person
     */
    public String getPassportCardNumber() {
        return passportCardNumber;
    }

    /**
     * Sets the passport card number of the person.
     *
     * @param passportCardNumber the passport card number of the person
     */
    public void setPassportCardNumber(String passportCardNumber) {
        this.passportCardNumber = passportCardNumber;
    }

    /**
     * Returns the tax number of the person.
     *
     * @return the tax number of the person
     */
    public String getTaxNumber() {
        return taxNumber;
    }

    /**
     * Sets the tax number of the person.
     *
     * @param taxNumber the tax number of the person
     */
    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }


    /**
     * Returns the location of the person.
     * @return the location of the person.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the person.
     * @param location the location of the person
     */
    public void setLocation(Location location) {
        this.location = location;
    }


    /**
     * Returns the email address of the person.
     *
     * @return the email address of the person
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address of the person.
     *
     * @param emailAddress the email address of the person
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the phone number of the person.
     *
     * @return the phone number of the person
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the person.
     *
     * @param phoneNumber the phone number of the person
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the role of the person.
     *
     * @return the role of the person
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the person.
     *
     * @param role the role of the person
     */
    public void setRole(String role) {
        this.role = role;
    }

    public boolean hasEmail(String emailAddress) {
        return this.emailAddress.equals(emailAddress);
    }

    public Person clone() {
        return new Person(this.name, this.passportCardNumber, this.taxNumber, this.emailAddress, this.phoneNumber,
                this.role, this.location);
    }

}


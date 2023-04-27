package pt.ipp.isep.dei.esoft.project.domain;

import javax.management.relation.Role;

public class Person {
    private String name;
    private String taxNumber;
    private String city;
    private String state;
    private String zipCode;
    private String streetName;
    private String emailAddress;
    private String phoneNumber;
    private String passportCardNumber;
    private Role role;

    /**
     Creates a Person object with the given parameters.
     @param name The name of the person.
     @param passportCardNumber The passport card number of the person.
     @param taxNumber The tax number of the person.
     @param emailAddress The email address of the person.
     @param phoneNumber The phone number of the person.
     @param role The role of the person.
     @param city The city of the person.
     @param state The state of the person.
     @param zipCode The zip code of the person.
     @param streetName The street name of the person.
     */
    public Person(String name, String passportCardNumber, String taxNumber, String emailAddress,
                  String phoneNumber, Role role,
                  String city, String state, String zipCode, String streetName) {
        this.name = name;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.city = city;
        this.state = state;
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    /**
     Validates the Employee object by checking if any of its required fields are empty.
     @param employee The Employee object to be validated.
     @return True if all required fields are not empty, false otherwise.
     */
    public boolean validateEmployee(Employee employee) {
        if (employee.getName().isEmpty() ||
                employee.getTaxNumber().isEmpty() ||
                employee.getCity().isEmpty() ||
                employee.getEmailAddress().isEmpty() ||
                employee.getPhoneNumber().isEmpty() ||
                employee.getRole() == null) {
            return false;
        }
        return true;
    }


    /**
     Gets the state of the Person.
     @return The state of the Person.
     */
    public String getState() {
        return state;
    }

    /**
     Sets the state of the Person.
     @param state The state to be set.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     Gets the zip code of the Person.
     @return The zip code of the Person.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     Sets the zip code of the Person.
     @param zipCode The zip code to be set.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     Gets the street name of the Person.
     @return The street name of the Person.
     */
    public String getStreetName() {
        return streetName;
    }
    /**
     Sets the street name of the Person.
     @param streetName The street name to be set.
     */

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     Returns the name of the person.
     @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     Sets the name of the person.
     @param name the name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     Returns the passport card number of the person.
     @return the passport card number of the person
     */
    public String getPassportCardNumber() {
        return passportCardNumber;
    }

    /**
     Sets the passport card number of the person.
     @param passportCardNumber the passport card number of the person
     */
    public void setPassportCardNumber(String passportCardNumber) {
        this.passportCardNumber = passportCardNumber;
    }

    /**
     Returns the tax number of the person.
     @return the tax number of the person
     */
    public String getTaxNumber() {
        return taxNumber;
    }

    /**
     Sets the tax number of the person.
     @param taxNumber the tax number of the person
     */
    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    /**
     Returns the city of the person.
     @return the city of the person
     */
    public String getCity() {
        return city;
    }

    /**
     Sets the city of the person.
     @param city the city of the person
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     Returns the email address of the person.
     @return the email address of the person
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     Sets the email address of the person.
     @param emailAddress the email address of the person
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     Returns the phone number of the person.
     @return the phone number of the person
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     Sets the phone number of the person.
     @param phoneNumber the phone number of the person
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     Returns the role of the person.
     @return the role of the person
     */
    public Role getRole() {
        return role;
    }

    /**
     Sets the role of the person.
     @param role the role of the person
     */
    public void setRole(Role role) {
        this.role = role;
    }

}


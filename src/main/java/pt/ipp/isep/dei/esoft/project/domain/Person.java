package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Scanner;

public class Person {
    private String name;
    private String taxNumber;
    private String emailAddress;
    private String phoneNumber;
    private String passportCardNumber;
    private String role;
    private Location location;
    private final Integer PASSPORT_CARD_NUMBER_LENGTH = 9;
    private final String PASSPORT_FIRST_CHARACTER = "C";
    private final Integer TAX_NUMBER_LENGTH = 11;
    private final Integer NUMBER_OF_SEGMENTS_TAXES = 3;
    private final Integer FIRST_SEGMENT_TAX_NUMBER_LENGTH = 3;
    private final Integer SECOND_SEGMENT_TAX_NUMBER_LENGTH = 3;
    private final Integer THIRD_SEGMENT_TAX_NUMBER_LENGTH = 3;
    private final Integer THREE_DIGIT_SEGMENT_PHONE_NUMBER = 3;
    private final Integer FOUR_DIGIT_SEGMENT_PHONE_NUMBER = 4;


    private final Integer PHONE_NUMBER_LENGTH = 14;

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
        if (!(validatePerson(name, passportCardNumber, taxNumber, emailAddress, phoneNumber, role))) {
            System.out.println("Your Data is not correct. Please submit new data.");
            String[] newValues = getNewData(name, passportCardNumber, taxNumber, emailAddress, phoneNumber, role);
            name = newValues[0];
            passportCardNumber = newValues[1];
            taxNumber = newValues[2];
            emailAddress = newValues[3];
            phoneNumber = newValues[4];
            role = newValues[5];
        }
        this.name = name;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.location = new Location(streetName, city, district, state, zipCode);
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    private boolean validatePerson(String name, String passportCardNumber, String taxNumber, String emailAddress, String phoneNumber, String role) {
        if ((name.isBlank() || name.isEmpty()) && (passportCardNumber.isBlank() || passportCardNumber.isEmpty()) && (taxNumber.isBlank() || taxNumber.isEmpty()) &&
                (emailAddress.isBlank() || emailAddress.isEmpty()) && (phoneNumber.isBlank() || phoneNumber.isEmpty()) && (role.isBlank() || role.isEmpty())) {
            return false;
        }
        return validateTaxNumber(taxNumber) && validatePhoneNumber(phoneNumber) && validatePassportCardNumber(passportCardNumber);
    }

    private String[] getNewData(String name, String passportCardNumber, String taxNumber, String emailAddress, String phoneNumber, String role) {
        String[] newValues = {name, passportCardNumber, taxNumber, emailAddress, phoneNumber, role};
        Scanner input = new Scanner(System.in);
        while (name.isBlank()) {
            System.out.println("Invalid Name. Provide a new one.");
            name = input.nextLine();
        }
        while (!validatePassportCardNumber(passportCardNumber)) {
            System.out.println("Invalid Passport Card Number. Provide a new one.");
            passportCardNumber = input.nextLine();
        }
        while (!validateTaxNumber(taxNumber)) {
            System.out.println("Invalid Tax Number. Provide a new one.");
            taxNumber = input.nextLine();
        }
        while (emailAddress.isBlank()) {
            System.out.println("Invalid Email. Provide a new one.");
            emailAddress = input.nextLine();
        }
        while (!validatePhoneNumber(phoneNumber)) {
            System.out.println("Invalid Phone Number. Provide a new one.");
            phoneNumber = input.nextLine();
        }
        while (role.isBlank()) {
            System.out.println("Invalid Role. Provide a new one.");
            role = input.nextLine();
        }
        return newValues;
    }


    private boolean validatePassportCardNumber(String passportCardNumber) {
        if (passportCardNumber.length() == PASSPORT_CARD_NUMBER_LENGTH) {
            String[] passp = passportCardNumber.split("");
            if (passp[0].equals(PASSPORT_FIRST_CHARACTER)) {
                for (String digit : passp) {
                    try {
                        Integer.parseInt(digit);
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        //Phone Number:  NXX NXX-XXXX, where the Ns denote any of the digits 2–9, and the Xs denote any digit 0–9.
        //exemplo (907) 488-6419
        if (phoneNumber.length() == PHONE_NUMBER_LENGTH) {
            String[] phone = phoneNumber.split(" ");
            String firstSegment = phone[0];
            String[] phoneSecondPart = phone[1].split("-");
            String secondSegment = phoneSecondPart[0];
            String thirdSegment = phoneSecondPart[1];
            return getThreeDigitSegmentValidation(firstSegment) && getThreeDigitSegmentValidation(secondSegment) &&
                    getFourDigitSegmentValidation(thirdSegment);
        }
        return false;
    }

    private boolean getFourDigitSegmentValidation(String phoneSegment) {
        if (phoneSegment.length() == FOUR_DIGIT_SEGMENT_PHONE_NUMBER) {
            String[] segmentDigits = phoneSegment.split("");
            for (String digit : segmentDigits) {
                try {
                    Integer.parseInt(digit);
                } catch (NumberFormatException e) {
                    return false;
                }
                if (!(Integer.parseInt(digit) >= 0 && Integer.parseInt(digit) <= 9)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean getThreeDigitSegmentValidation(String phoneSegment) {
        if (phoneSegment.length() == THREE_DIGIT_SEGMENT_PHONE_NUMBER) {
            String[] segmentDigits = phoneSegment.split("");
            for (String digit : segmentDigits) {
                try {
                    Integer.parseInt(digit);
                } catch (NumberFormatException e) {
                    return false;
                }
                if (!(Integer.parseInt(segmentDigits[0]) >= 2 && Integer.parseInt(segmentDigits[0]) <= 9)) {
                    return false;
                } else if (!(Integer.parseInt(digit) >= 0 && Integer.parseInt(digit) <= 9)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean validateTaxNumber(String taxNumber) {
        if (taxNumber.length() == TAX_NUMBER_LENGTH) {
            String[] tax = taxNumber.split("-");
            return tax.length == NUMBER_OF_SEGMENTS_TAXES
                    && tax[0].length() == FIRST_SEGMENT_TAX_NUMBER_LENGTH
                    && tax[1].length() == SECOND_SEGMENT_TAX_NUMBER_LENGTH
                    && tax[2].length() == THIRD_SEGMENT_TAX_NUMBER_LENGTH;
        }
        return false;
    }

    public Person(String emailAddress) {
        this.emailAddress = emailAddress;
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

    public Person(String email, String roleId) {
        this.emailAddress = email;
        this.role = roleId;
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
                employee.getLocation().equals("") ||
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
     *
     * @return the location of the person.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the person.
     *
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


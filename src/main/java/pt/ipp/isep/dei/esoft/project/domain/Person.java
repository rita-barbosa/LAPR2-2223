package pt.ipp.isep.dei.esoft.project.domain;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person {
    private String name;
    private String taxNumber;
    private Email emailAddress;
    private String phoneNumber;
    private String passportCardNumber;
    private List<String> roles;
    private Location location;
    private final Integer PASSPORT_CARD_NUMBER_LENGTH = 9;
    private final char PASSPORT_FIRST_CHARACTER = 'C';
    private final Integer NUMBER_OF_SEGMENTS_TAXES = 3;
    private final Integer FIRST_SEGMENT_TAX_NUMBER_LENGTH = 3;
    private final Integer SECOND_SEGMENT_TAX_NUMBER_LENGTH = 2;
    private final Integer THIRD_SEGMENT_TAX_NUMBER_LENGTH = 4;
    private final Integer THREE_DIGIT_SEGMENT_PHONE_NUMBER = 3;
    private final Integer FOUR_DIGIT_SEGMENT_PHONE_NUMBER = 4;


    private final Integer PHONE_NUMBER_LENGTH = 12;

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
        Scanner input = new Scanner(System.in);
        this.roles = new ArrayList<>();

        this.roles.add(role);
        this.name = name;
        if (streetName != null) {
            this.location = new Location(streetName, city, district, state, zipCode);
        }
        this.emailAddress = new Email(emailAddress);
        while (!validatePassportCardNumber(passportCardNumber)) {
            System.out.println("Invalid Passport Card Number. Provide a new one.");
            passportCardNumber = input.nextLine();
        }
        this.passportCardNumber = passportCardNumber;
        while (!validateTaxNumber(taxNumber)) {
            System.out.println("Invalid Tax Number. Provide a new one.");
            taxNumber = input.nextLine();
        }
        this.taxNumber = taxNumber;
        while (!validatePhone(phoneNumber)) {
            System.out.println("Invalid Phone Number. Provide a new one.");
            phoneNumber = input.nextLine();
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructor of a new Person instance with a name, passport card number, tax number, email address, phone number, role and location.
     *
     * @param name - the name of the person.
     * @param passportCardNumber - the passport card number of the person.
     * @param taxNumber - the tax number of the person.
     * @param emailAddress - the email address of the person.
     * @param phoneNumber - the phone number of the person.
     * @param roles - the roles the person has in the system.
     * @param location - the location of the person address.
     */
    public Person(String name, String passportCardNumber, String taxNumber, Email emailAddress,
                  String phoneNumber, List<String> roles, Location location) {
        Scanner input = new Scanner(System.in);
        this.roles = roles;
        this.name = name;
        this.location = location;
        this.emailAddress = emailAddress;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.phoneNumber = phoneNumber;
    }


    public Person(String emailAddress, String role) {
        this.roles = new ArrayList<>();

        this.roles.add(role);
        this.emailAddress = new Email(emailAddress);
    }

    public Person(String emailAddress) {
        this.emailAddress = new Email(emailAddress);
    }

    private boolean validatePassportCardNumber(String passportCardNumber) {
        if (passportCardNumber.length() == PASSPORT_CARD_NUMBER_LENGTH && (passportCardNumber.charAt(0) == PASSPORT_FIRST_CHARACTER)) {
            String[] passp = passportCardNumber.split("");
            for (int i = 1; i < passp.length - 1; i++) {
                try {
                    Integer.parseInt(passp[i]);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean validatePhone(String phoneNumber) {

        if (phoneNumber.length() == PHONE_NUMBER_LENGTH) {
            String[] phone = phoneNumber.split(" ");
            String[] firstSegment = phone[0].split("");
            String[] secondSegment = phone[1].split("");
            String[] thirdSegment = phone[2].split("");
            if ((Integer.parseInt(firstSegment[0]) < 2) || (Integer.parseInt(secondSegment[0]) < 2)) {
                return false;
            }
            if (!validation(firstSegment) || firstSegment.length != THREE_DIGIT_SEGMENT_PHONE_NUMBER) {
                return false;
            }
            if (!validation(secondSegment) || secondSegment.length != THREE_DIGIT_SEGMENT_PHONE_NUMBER) {
                return false;
            }
            if (!validation(thirdSegment) || thirdSegment.length != FOUR_DIGIT_SEGMENT_PHONE_NUMBER) {
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean validation(String[] segmentDigits) {
        for (int i = 0; i < segmentDigits.length; i++) {
            try {
                Integer.parseInt(segmentDigits[i]);
            } catch (NumberFormatException e) {
                return false;
            }
            if (!(Integer.parseInt(segmentDigits[i]) >= 0 && Integer.parseInt(segmentDigits[i]) <= 9)) {
                return false;
            }
        }
        return true;
    }


    private boolean validateTaxNumber(String taxNumber) {
        String[] segments = taxNumber.split("-");
        if (taxNumber == null || taxNumber.isEmpty()) {
            return false;
        }
        if (segments.length != NUMBER_OF_SEGMENTS_TAXES) {
            return false;
        }
        if (segments[0].length() != FIRST_SEGMENT_TAX_NUMBER_LENGTH) {
            return false;
        }
        if (segments[1].length() != SECOND_SEGMENT_TAX_NUMBER_LENGTH) {
            return false;
        }
        if (segments[2].length() != THIRD_SEGMENT_TAX_NUMBER_LENGTH) {
            return false;
        }
        return true;
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
                employee.getEmailAddress() == null ||
                employee.getPhoneNumber().isEmpty() ||
                employee.getRoles() == null) {
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
     * Returns the passport card number of the person.
     *
     * @return the passport card number of the person
     */
    public String getPassportCardNumber() {
        return passportCardNumber;
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
     * Returns the location of the person.
     *
     * @return the location of the person.
     */
    public Location getLocation() {
        return location;
    }


    /**
     * Returns the email address of the person.
     *
     * @return the email address of the person
     */
    public Email getEmailAddress() {
        return this.emailAddress;
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
     * Returns the role of the person.
     *
     * @return the role of the person
     */
    public List<String> getRoles() {
        return roles;
    }


    public boolean hasEmail(String emailAddress) {
        return (this.emailAddress.equals(emailAddress));
    }

    public Person clone() {
        return new Person(this.name, this.passportCardNumber, this.taxNumber, this.emailAddress, this.phoneNumber,
                this.roles, this.location);
    }

}


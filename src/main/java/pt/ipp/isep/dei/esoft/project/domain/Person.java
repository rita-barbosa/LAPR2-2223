package pt.ipp.isep.dei.esoft.project.domain;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.*;

/**
 * The type Person.
 */
public class Person {
    /**
     * The Name.
     */
    private String name;
    /**
     * The Tax number.
     */
    private String taxNumber;


    /**
     * The Email address.
     */
    private Email emailAddress;
    /**
     * The Phone number.
     */
    private String phoneNumber;
    /**
     * The Passport card number.
     */
    private String passportCardNumber;
    /**
     * The Roles.
     */
    private List<String> roles = new ArrayList<>();
    /**
     * The Location.
     */
    private Location location;
    /**
     * The constant PASSPORT_CARD_NUMBER_LENGTH.
     */
    private static final Integer PASSPORT_CARD_NUMBER_LENGTH = 9;
    /**
     * The constant PASSPORT_FIRST_CHARACTER.
     */
    private static final char PASSPORT_FIRST_CHARACTER = 'C';
    /**
     * The constant NUMBER_OF_SEGMENTS_TAXES.
     */
    private static final Integer NUMBER_OF_SEGMENTS_TAXES = 3;
    /**
     * The constant FIRST_SEGMENT_TAX_NUMBER_LENGTH.
     */
    private static final Integer FIRST_SEGMENT_TAX_NUMBER_LENGTH = 3;
    /**
     * The constant SECOND_SEGMENT_TAX_NUMBER_LENGTH.
     */
    private static final Integer SECOND_SEGMENT_TAX_NUMBER_LENGTH = 2;
    /**
     * The constant THIRD_SEGMENT_TAX_NUMBER_LENGTH.
     */
    private static final Integer THIRD_SEGMENT_TAX_NUMBER_LENGTH = 4;
    /**
     * The constant THREE_DIGIT_SEGMENT_PHONE_NUMBER.
     */
    private static final Integer THREE_DIGIT_SEGMENT_PHONE_NUMBER = 3;
    /**
     * The constant FOUR_DIGIT_SEGMENT_PHONE_NUMBER.
     */
    private static final Integer FOUR_DIGIT_SEGMENT_PHONE_NUMBER = 4;
    /**
     * The constant PHONE_NUMBER_LENGTH.
     */
    private static final Integer PHONE_NUMBER_LENGTH = 12;

    /**
     * Creates a Person object with the given parameters.
     *
     * @param name               The name of the person.
     * @param passportCardNumber The passport card number of the person.
     * @param taxNumber          The tax number of the person.
     * @param emailAddress       The email address of the person.
     * @param phoneNumber        The phone number of the person.
     * @param role               The role of the person.
     * @param streetName         The street name of the person.
     * @param city               The city of the person.
     * @param district           the district
     * @param state              The state of the person.
     * @param zipCode            The zip code of the person.
     */
    public Person(String name, String passportCardNumber, String taxNumber, String emailAddress, String phoneNumber,
                  String role, String streetName, String city, String district, String state, String zipCode) {

        this.roles = new ArrayList<>();

        this.roles.add(role);
        this.name = name;
        if (streetName != null) {
            this.location = new Location(streetName, city, district, state, zipCode);
        }
        this.setEmailAddress(emailAddress);
        this.setPassportCardNumber(passportCardNumber);
        this.setTaxNumber(taxNumber);
        this.setPhoneNumber(phoneNumber);
    }

    /**
     * Constructor of a new Person instance with a name, passport card number, tax number, email address, phone number, role and location.
     *
     * @param name               - the name of the person.
     * @param passportCardNumber - the passport card number of the person.
     * @param taxNumber          - the tax number of the person.
     * @param emailAddress       - the email address of the person.
     * @param phoneNumber        - the phone number of the person.
     * @param roles              - the roles the person has in the system.
     * @param location           - the location of the person address.
     */
    public Person(String name, String passportCardNumber, String taxNumber, Email emailAddress,
                  String phoneNumber, List<String> roles, Location location) {
        this.roles = roles;
        this.name = name;
        this.location = location;
        this.emailAddress = emailAddress;
        this.setPassportCardNumber(passportCardNumber);
        this.setTaxNumber(taxNumber);
        this.setPhoneNumber(phoneNumber);
    }


    /**
     * Instantiates a new Person.
     *
     * @param emailAddress the email address
     * @param role         the role
     */
    public Person(String emailAddress, String role) {
        this.roles = new ArrayList<>();

        this.roles.add(role);
        this.setEmailAddress(emailAddress);
    }

    /**
     * Instantiates a new Person.
     *
     * @param emailAddress the email address
     */
    public Person(String emailAddress) {
        this.emailAddress = new Email(emailAddress);
    }

    public Person(String name, String passportCardNumber, String taxNumber, String emailAddress,
                  String phoneNumber, String role) {
        this.roles.add(role);
        this.name = name;
        this.setEmailAddress(emailAddress);
        this.setPassportCardNumber(passportCardNumber);
        this.setTaxNumber(taxNumber);
        this.setPhoneNumber(phoneNumber);
    }

    public Person(String name, String taxNumber, String phoneNumber, String email,String passportCardNumber) {
        this.name = name;
        this.setEmailAddress(email);
        this.setTaxNumber(taxNumber);
        this.setPhoneNumber(phoneNumber);
        this.setPassportCardNumber(passportCardNumber);
    }


    /**
     * This method checks if the passport card number is valid, has the appropriate format.
     *
     * @param passportCardNumber the passport card number to be validated.
     * @return {@code true} if the passport card number has the appropriate format;{@code false} otherwise;
     */
    private boolean validatePassportCardNumber(String passportCardNumber) {
        if (passportCardNumber.length() == PASSPORT_CARD_NUMBER_LENGTH) {
            if (Character.isDigit(passportCardNumber.charAt(0))) {
                return validatePassportCardNumberWithoutLetter(passportCardNumber);
            } else if (passportCardNumber.charAt(0) == PASSPORT_FIRST_CHARACTER) {
                return validatePassportCardNumberWithLetter(passportCardNumber);
            }
        }
        return false;
    }

    /**
     * This method validates the passport card number in case it starts with the letter 'C'.
     *
     * @param passportCardNumber the passport card number to be validated.
     * @return {@code true} if the passport card number has the appropriate format;{@code false} otherwise;
     */
    private boolean validatePassportCardNumberWithLetter(String passportCardNumber) {
        boolean valid = true;
        String numericPart = passportCardNumber.substring(1);
        if (numericPart.length() == 8) {
            for (char c : numericPart.toCharArray()) {
                if (!Character.isDigit(c)) {
                    valid = false;
                }
            }
        }
        return valid;
    }

    /**
     * This method validates the passport card number in case it doesn't start with the letter 'C'.
     *
     * @param passportCardNumber the passport card number to be validated.
     * @return {@code true} if the passport card number has the appropriate format;{@code false} otherwise;
     */
    private boolean validatePassportCardNumberWithoutLetter(String passportCardNumber) {
        for (char c : passportCardNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }


    /**
     * This method checks if the received phone number has the appropriate format.
     *
     * @param phoneNumber the phone number
     * @return {@code true} if the phone number is correct;{@code false} otherwise;
     */
    private boolean validatePhone(String phoneNumber) {

        if (phoneNumber.length() == PHONE_NUMBER_LENGTH) {
            String[] phone = phoneNumber.split("-");
            String[] firstSegment = phone[0].split("");
            String[] secondSegment = phone[1].split("");
            String[] thirdSegment = phone[2].split("");

            if ((Integer.parseInt(firstSegment[0]) < 2) || (Integer.parseInt(secondSegment[0]) < 2)) {
                return false;
            }
            if (!validationDigists(firstSegment) || firstSegment.length != THREE_DIGIT_SEGMENT_PHONE_NUMBER) {
                return false;
            }
            if (!validationDigists(secondSegment) || secondSegment.length != THREE_DIGIT_SEGMENT_PHONE_NUMBER) {
                return false;
            }
            return validationDigists(thirdSegment) && thirdSegment.length == FOUR_DIGIT_SEGMENT_PHONE_NUMBER;
        }
        return false;
    }

    /**
     * This method checks if the received String only has digits.
     *
     * @param segmentDigits the segment digits
     * @return {@code true} if they are all digits;{@code false} otherwise;
     */
    private boolean validationDigists(String[] segmentDigits) {
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

    /**
     * This method sets the tax number of an instance, after verifying if the tax number is correct.
     *
     * @param taxNumber - tax number.
     */
    public void setTaxNumber(String taxNumber) {
        Scanner input = new Scanner(System.in);
        while (!validateTaxNumber(taxNumber)) {
            System.out.println("Invalid Tax Number. Provide a new one.");
            taxNumber = input.nextLine();
        }
        this.taxNumber = taxNumber;
    }

    /**
     * This method sets the email of an instance, after verifying if the email address is correct.
     *
     * @param email - tax number.
     */
    public void setEmailAddress(String email) {
        boolean valid = false;
        Scanner input = new Scanner(System.in);
        while (!valid) {
            try {
                this.emailAddress = new Email(email);
                valid = true;
            } catch (IllegalArgumentException e) {
                email = input.nextLine();
            }
        }
    }

    /**
     * This method sets the phone number of an instance, after verifying if the phone number is correct.
     *
     * @param phoneNumber - phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        Scanner input = new Scanner(System.in);
        while (!validatePhone(phoneNumber)) {
            System.out.println("Invalid Phone Number. Provide a new one.");
            phoneNumber = input.nextLine();
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method sets the passport card number of an instance, after verifying if the passport card number is correct.
     *
     * @param passportCardNumber - passport card number
     */
    public void setPassportCardNumber(String passportCardNumber) {
        Scanner input = new Scanner(System.in);
        while (!validatePassportCardNumber(passportCardNumber)) {
            System.out.println("Invalid Passport Card Number. Provide a new one.");
            passportCardNumber = input.nextLine();
        }
        this.passportCardNumber = passportCardNumber;
    }
    /**
     * This method sets the Person roles.
     * @param roles - list of roles
     */
    public void setRole(List<String> roles) {
        if (!new HashSet<>(this.roles).containsAll(roles)) {
            roles.removeAll(this.roles);
            this.roles.addAll(roles);
        }
    }

    /**
     * This method sets the location of an instance.
     *
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }


    /**
     * Validates tax number format.
     *
     * @param taxNumber the tax number
     * @return {@code true} if tax number has the correct format;{@code false} otherwise;
     */
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


    /**
     * Checks if person has the email received.
     *
     * @param emailAddress the email address
     * @return {@code true} if the email is the same;{@code false} otherwise;
     */
    public boolean hasEmail(String emailAddress) {
        return (this.emailAddress.equals(new Email(emailAddress)));
    }

    /**
     * This method returns a copy of the Person.
     *
     * @return copy (clone) of the Person
     */
    public Person clone() {
        return new Person(this.name, this.passportCardNumber, this.taxNumber, this.emailAddress, this.phoneNumber,
                this.roles, this.location);
    }

    /**
     * This method checks if two instances are equal. Receiving an object that is to be compared.
     *
     * @param o - object to be compared
     * @return {@code true} if they are equal;{@code false} otherwise;
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(taxNumber, person.taxNumber)
                && Objects.equals(emailAddress, person.emailAddress) && Objects.equals(phoneNumber, person.phoneNumber)
                && Objects.equals(passportCardNumber, person.passportCardNumber) && Objects.equals(roles, person.roles)
                && Objects.equals(location, person.location);
    }
}


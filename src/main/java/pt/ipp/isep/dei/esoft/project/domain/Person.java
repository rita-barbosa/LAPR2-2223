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
    private List<String> roles;
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
        Scanner input = new Scanner(System.in);
        this.roles = roles;
        this.name = name;
        this.location = location;
        this.emailAddress = emailAddress;
        this.passportCardNumber = passportCardNumber;
        this.taxNumber = taxNumber;
        this.phoneNumber = phoneNumber;
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
        this.emailAddress = new Email(emailAddress);
    }

    /**
     * Instantiates a new Person.
     *
     * @param emailAddress the email address
     */
    public Person(String emailAddress) {
        this.emailAddress = new Email(emailAddress);
    }

    /**
     * Validate passport card number boolean.
     *
     * @param passportCardNumber the passport card number
     * @return the boolean
     */
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

    /**
     * Validate phone boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
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

    /**
     * Validation boolean.
     *
     * @param segmentDigits the segment digits
     * @return the boolean
     */
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


    /**
     * Validate tax number boolean.
     *
     * @param taxNumber the tax number
     * @return the boolean
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
     * Has email boolean.
     *
     * @param emailAddress the email address
     * @return the boolean
     */
    public boolean hasEmail(String emailAddress) {
        return (this.emailAddress.equals(new Email(emailAddress)));
    }

    /**
     * Clone person.
     *
     * @return the person
     */
    public Person clone() {
        return new Person(this.name, this.passportCardNumber, this.taxNumber, this.emailAddress, this.phoneNumber,
                this.roles, this.location);
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
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


    public void setRole(List<String> roles) {
        if (!new HashSet<>(this.roles).containsAll(roles)) {
            roles.removeAll(this.roles);
            this.roles.addAll(roles);
        }
    }
}


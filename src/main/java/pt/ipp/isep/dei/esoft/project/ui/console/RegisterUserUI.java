package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterUserController;

import java.util.Optional;
import java.util.Scanner;

public class RegisterUserUI implements Runnable {


    private final RegisterUserController controller;
    private String name;
    private String passportCardNumber;
    private String taxNumber;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private String streetName;
    private String city;
    private String district;
    private String state;
    private String zipCode;
    private final Integer PASSWORD_LENGHT = 7;

    public RegisterUserUI() {
        this.controller = new RegisterUserController();
    }

    private RegisterUserController getController() {
        return controller;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        name = requestName();
        passportCardNumber = requestPassportCardNumber();
        taxNumber = requestTaxNumber();
        emailAddress = requestEmailAddres();
        phoneNumber = requestPhoneNumber();
        password = requestPassword();
        requestLocation();
//        submitData();
    }

//    private void submitData() {
//        Optional<Boolean> success = getController().;
//    }

    private String requestPassword() {
        Scanner input = new Scanner(System.in);
        String answer = null;
        Boolean invalid = true;
        System.out.println("Password (Format: 7 alphanumeric characters, including 3 capital letters and 2 digits)\nor press enter to skip (Generated password will be sent to the introduced email):");
        while (invalid) {
            answer = input.nextLine().trim();
            if (answer.isEmpty()) {
                return null;
            } else if (answer.length() != PASSWORD_LENGHT) {
                System.out.println("ERROR: Password must have 7 alphanumeric characters.");
            } else {
                invalid = false;
            }
        }

        return answer;
    }

    private String requestPhoneNumber() {
        System.out.println("Phone Number (Format: NXX-NXX-XXXX):");
        return getStringAnswer();
    }

    private String requestEmailAddres() {
        System.out.println("Email Address (Format: name@domain.com):");
        return getStringAnswer();
    }


    private String requestTaxNumber() {
        System.out.println("Tax Number (Format: XXX-XX-XXXX):");
        return getStringAnswer();
    }

    private String requestPassportCardNumber() {
        System.out.println("Passport Card Number (Example: C98745634):");
        return getStringAnswer();
    }

    private String requestName() {
        System.out.println("Name:");
        return getStringAnswer();
    }

    private void requestLocation() {
        if (isInput()) {
            streetName = requestStreetName();
            state = requestState();
            city = requestCity();
            district = requestDistrict();
            zipCode = requestZipCode();
        }
    }


    private String requestState() {
        System.out.println("State:");
        return getStringAnswer();
    }

    private String requestCity() {
        System.out.println("City:");
        return getStringAnswer();
    }

    private String requestDistrict() {
        System.out.println("District:");
        return getStringAnswer();
    }

    private String requestZipCode() {
        Scanner input = new Scanner(System.in);
        String answer = null;
        System.out.println("Zipcode:");
        boolean invalid = true;
        do {
            try {
                answer = input.nextLine();
                Integer value = Integer.parseInt(answer);
                invalid = false;
            } catch (NumberFormatException e) {
                System.out.println("\nERROR: Value typed is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
            }
        } while (invalid);
        return answer;
    }

    private String requestStreetName() {
        System.out.println("StreetName:");
        return getStringAnswer();
    }

    private String getStringAnswer() {
        Scanner input = new Scanner(System.in);
        String answer;
        boolean invalid = true;
        do {
            answer = input.nextLine().trim();
            if (answer.isEmpty()) {
                System.out.println("ERROR: It is mandatory to introduce the required data.");
            } else {
                invalid = false;
            }

        } while (invalid);
        return answer;
    }

    private boolean isInput() {
        Scanner input = new Scanner(System.in);
        Boolean value = null;
        System.out.println("Do you want to introduce your address (Y/N)?");
        String answer;
        answer = input.nextLine();
        while (value == null) {
            if (answer.equalsIgnoreCase("y")) {
                value = true;
            } else if (answer.equalsIgnoreCase("n")) {
                value = false;
            } else {
                System.out.println("\nERROR: The input provided is not valid. Please try again.");
                answer = input.nextLine();
            }
        }
        return value;
    }
}

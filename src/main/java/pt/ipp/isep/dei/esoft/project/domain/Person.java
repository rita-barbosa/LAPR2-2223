package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Scanner;

public class Person {
    private String name;
    private int taxNumber;
    private String passportCardNumber;
    private int phoneNumber;

    public Person (String name, int taxNumber, String passportCardNumber, int phoneNumber) {
        if (validatePerson(name, passportCardNumber)) {
            this.name = name;
            this.taxNumber = taxNumber;
            this.passportCardNumber = passportCardNumber;
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Invalid data. Please submit new data");
        }
    }
    private boolean validatePerson(String name, String passportCardNumber) {
        if (name.isEmpty() || passportCardNumber.isEmpty() ){
            return false;
        } else if (name.isBlank() || passportCardNumber.isBlank()) {
            return false;
        }
        return true;
    }

    private void validateData (int taxNumber, int phoneNumber) {
        Scanner in = new Scanner(System.in);
        while (!in.hasNextInt()){
            System.out.println("Invalid data. Please submit new data");
            in.next();
        }
    }
}

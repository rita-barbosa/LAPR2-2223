package pt.ipp.isep.dei.esoft.project.ui.console;

import org.apache.commons.lang3.NotImplementedException;
import pt.ipp.isep.dei.esoft.project.application.controller.ListRequestsController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ListRequestsUI implements Runnable{

    private final ListRequestsController controller = new ListRequestsController();

    /**
     * The designation of the type of property being announced.
     */
    private String propertyTypeDesignation;

    /**
     * The designation of the commission type.
     */
    private String commissionTypeDesignation;

    /**
     * The email address of the owner of the property.
     */
    private String ownerEmail;

    private ListRequestsController getController(){
        return controller;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property announcement requests:\n");
        Optional<List<RequestDto>> listRequests = controller.getRequestsList();
        boolean continueLoop = true;
        Integer requestIdDto;
        do {
            for (RequestDto request : listRequests.get()) {
                System.out.println(request.toString());
            }
            boolean option = askOption();
            if (option){
                requestIdDto = askRequestId();
                Request request = controller.getRequestByIdDto(requestIdDto);
                System.out.println(request.toString());
                boolean optionForRequest = askOptionForRequest();
                if (optionForRequest) {
                    commissionTypeDesignation = displayAndSelectCommissionType();
                    if (requestConfirmation()) {
                        submitData();
                    }
                } else {
                    System.out.println("Writte a justification message:\n");
                    String message = input.nextLine();
                    ownerEmail = controller.getOwnerEmail();
                    controller.sendEmail(ownerEmail, message);
                }
            } else {
                continueLoop = false;
            }
        }while(continueLoop);


    }



    private Integer askRequestId(){
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        Integer value = null;
        do {
            try {
                value = input.nextInt();
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Value typed is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);
        return value;
    }

    private boolean askOption() {
        System.out.println("Do you want to select any request? \n1 - Yes \n2 - No");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        boolean invalid = true;

        do {
            try {
                while (option != 1 && option != 2) {
                    option = sc.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);

        return option == 1;
    }

    private boolean askOptionForRequest() {
        System.out.println("Do you want to accept or decline the request? \n1 - Accept \n2 - Decline");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        boolean invalid = true;

        do {
            try {
                while (option != 1 && option != 2) {
                    option = sc.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);

        return option == 1;
    }


//    private void successMessage(Boolean success, String acceptanceAnswer) {
//        if (success) {
//            System.out.println("The order was successfully " + acceptanceAnswer + "ed.\n");
//        } else {
//            System.out.println(" ERROR: The order wasn't successfully " + acceptanceAnswer + "ed.\n");
//        }
//    }

    private String displayAndSelectCommissionType() {
        List<CommissionType> commissionTypes = controller.getCommissionTypeList();
        boolean invalid = true;
        int listSize = commissionTypes.size();
        int answer = -1;
        Scanner input = new Scanner(System.in);

        do {
            try {
                while (answer < 1 || answer > listSize) {
                    displayCommissionTypeOptions(commissionTypes);
                    System.out.println("Select a type of commission:");
                    answer = input.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);
        return (commissionTypes.get(answer - 1).getDesignation());
    }

    private void displayCommissionTypeOptions(List<CommissionType> commissionTypes) {
        int i = 1;
        System.out.println();
        for (CommissionType commissionType : commissionTypes) {
            System.out.println(i + " - " + commissionType.getDesignation());
            i++;
        }
    }

    private boolean requestConfirmation() {
        Scanner input = new Scanner(System.in);
        Boolean value = null;

        System.out.println("\nDo you want to submit the information (Y/N)?");
        String answer = input.nextLine();
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

    private void submitData() {
        throw new NotImplementedException();
//
//        Optional<Announcement> announcement = getController().publishAnnouncement( commissionTypeDesignation, commissionValue, request, ownerEmail,);
//
//        if (announcement.isPresent()) {
//            System.out.println("\nAnnouncement published successfully.");
//        } else {
//            System.out.println("\nERROR: announcement was not published.");
//        }

    }


}

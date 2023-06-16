package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListRequestsController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.domain.dto.CommissionTypeDto;
import pt.ipp.isep.dei.esoft.project.domain.dto.RequestDto;

import java.util.*;

/**
 * The type List requests ui.
 */
public class ListRequestsUI implements Runnable {

    /**
     * The Controller.
     */
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
     * The commission value.
     */
    private Double commissionValue;
    /**
     * The Request index.
     */
    private Integer requestIndex;
    /**
     * The email address of the owner of the property.
     */
    private String ownerEmail;

    /**
     * Get controller list requests controller.
     *
     * @return the list requests controller
     */
    private ListRequestsController getController() {
        return controller;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nProperty requests to be published:\n");
        Optional<List<RequestDto>> listRequests = controller.getRequestsList();
        Optional<RequestDto> requestDto = Optional.empty();
        boolean continueLoop = true;
        Optional<Request> originalRequest = null;
        do {
            if (listRequests.isPresent() && listRequests.get().size() > 0) {
                displayList(listRequests.get());
                boolean option = askOption();
                if (option) {
                    if (listRequests.get().size() > 0) {
                        requestIndex = requestRequestIndex(listRequests.get());
                        requestDto = Optional.of(listRequests.get().get(requestIndex));
                    }
                    if (requestDto.isPresent()) {
                        originalRequest = controller.getRequestByDtoId(requestDto.get());
                    }
                    if (originalRequest.isPresent()) {
                        System.out.println(originalRequest.get());

                    boolean optionForRequest = askOptionForRequest();
                    if (optionForRequest) {
                        commissionTypeDesignation = displayAndSelectCommissionType();
                        commissionValue = requestCommissionValue();
                        if (requestConfirmation() && requestDto.isPresent()) {
                            submitData(originalRequest);
                            listRequests.get().remove(requestDto.get());
                        }
                    } else {
                        System.out.println("Write a justification message:");
                        String message = input.nextLine();
                        if (originalRequest.isPresent() && requestDto.isPresent()) {
                            controller.defineJustificationMessage(message, originalRequest.get());
                            controller.sendEmail(originalRequest.get());
                            originalRequest.get().setValidationStatus(true);
                            listRequests.get().remove(requestDto.get());
                        }
                    }
                } else {
                        System.out.println("ERROR!!!!");
                    }
                } else {
                    continueLoop = false;
                }
            } else {
                System.out.println("\n\nThere isn't any property request available! Returning to the Agent Menu!");
                continueLoop = false;
            }
        } while (continueLoop);
    }

    /**
     * Display list.
     *
     * @param list the RequestDTo list
     */
    private void displayList(List<RequestDto> list) {
        int i = 0;
        for (RequestDto request : list) {
            System.out.printf("#%d\n", i++);
            System.out.printf("%s\n", request.toString());
        }
        System.out.println();
    }

    /**
     * Request announcement index integer.
     *
     * @param list the list
     * @return the integer
     */
    private Integer requestRequestIndex(List<RequestDto> list) {
        System.out.println("Please select a request by its index number.");
        int idx;
        idx = getIntAnswer();
        while (idx > list.size() - 1 || idx < 0) {
            System.out.println("\nThe request index must be within the displayed list.");
            idx = getIntAnswer();
        }
        return idx;
    }

    /**
     * Gets int answer.
     *
     * @return the int answer
     */
    private Integer getIntAnswer() {
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

    /**
     * Ask option boolean.
     *
     * @return the boolean
     */
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

    /**
     * Ask option for request boolean.
     *
     * @return the boolean
     */
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

    /**
     * Display and select commission type string.
     *
     * @return the string
     */
    private String displayAndSelectCommissionType() {
        Optional<List<CommissionType>> commissionTypes = controller.getCommissionTypeList();
        Optional<List<CommissionTypeDto>> commissionTypeDtos = controller.getCommissionTypeListDto(commissionTypes.get());
        boolean invalid = true;
        int listSize = commissionTypeDtos.get().size();
        int answer = -1;
        Scanner input = new Scanner(System.in);

        do {
            try {
                while (answer < 1 || answer > listSize) {
                    displayCommissionTypeOptions(commissionTypeDtos.get());
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
        return (commissionTypes.get().get(answer - 1).getDesignation());
    }


    /**
     * Display commission type options.
     *
     * @param commissionTypes the commission types
     */
    private void displayCommissionTypeOptions(List<CommissionTypeDto> commissionTypes) {
        int i = 1;
        System.out.println();
        for (CommissionTypeDto commissionType : commissionTypes) {
            System.out.println(i + " - " + commissionType.getCommissionType());
            i++;
        }
    }
    /**
     * This method requests the commission value of the agent.
     *
     * @return commission value of the agent.
     */
    private Double requestCommissionValue() {
        System.out.println("Commission Value:");
        return getDoubleAnswer();
    }

    /**
     * This method obtains and verifies a Double user input.
     * If the user doesn't type in a Double, then an Input Mismatch Exception will be "catched".
     *
     * @return the Double user inputted answer.
     */
    private Double getDoubleAnswer() {
        Scanner input = new Scanner(System.in);
        boolean invalid = true;
        Double value = null;
        do {
            try {
                value = input.nextDouble();
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Value typed is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);

        return value;
    }

    /**
     * Request confirmation boolean.
     *
     * @return the boolean
     */
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

    /**
     * Submit data.
     *
     * @param request the request
     */
    private void submitData(Optional<Request> request) {
        Boolean success = getController().publishAnnouncement(commissionTypeDesignation, commissionValue, request);

        if (success) {
            System.out.println("\nAnnouncement published successfully.");
//            request.get().setValidationStatus(true);
        } else {
            System.out.println("\nERROR: Announcement was not published and sms notification wasn't send.");
        }
    }


}
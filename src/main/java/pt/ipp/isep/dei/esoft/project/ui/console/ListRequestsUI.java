package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListRequestsController;
import pt.ipp.isep.dei.esoft.project.domain.RequestDto;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ListRequestsUI implements Runnable{

    private final ListRequestsController controller = new ListRequestsController();

    private ListRequestsController getController(){
        return controller;
    }

    public void run() {
        System.out.println("Property announcement requests:\n");
        List<RequestDto> listRequests = controller.getRequestsList();
        displayRequestList();
        boolean continueLoop = true;
        do {
            boolean option = askOption();
            if (option){

            } else {
                continueLoop = false;
            }

        }while(continueLoop);




    }

    private void displayRequestList(){

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


}

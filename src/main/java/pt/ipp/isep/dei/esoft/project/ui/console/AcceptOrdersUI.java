package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AcceptOrdersController;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.domain.dto.OrderDto;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AcceptOrdersUI implements Runnable {
    enum Answers {
        REJECT,
        ACCEPT

    }

    private String acceptanceAnswer;
    private final AcceptOrdersController controller;

    public AcceptOrdersUI() {
        this.controller = new AcceptOrdersController();
    }

    private AcceptOrdersController getController() {
        return this.controller;
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        System.out.println("\nAccept Purchase Orders\n");

        Boolean success;


        Optional<List<AnnouncementDto>> listAnnouncements = controller.getAnnouncementsList();
        if (listAnnouncements.isPresent() && listAnnouncements.get().size() > 0) {
            for (AnnouncementDto a : listAnnouncements.get()) {
                System.out.println(a.toString());
                int idx = 1;
                if (!a.getListOrdersDto().isEmpty()) {
                    for (OrderDto o : a.getListOrdersDto()) {
                        acceptanceAnswer = selectAcceptanceAnswerOfOrder(o, idx);
                        idx++;
                        success = controller.defineOrderAcceptance(acceptanceAnswer, a.getAnnouncementId(), o.getId());
                        successMessage(success, acceptanceAnswer);
                        if (acceptanceAnswer.equals(Answers.ACCEPT.toString().toLowerCase())) {
                            break;
                        }
                    }
                } else {
                    System.out.println("WARNING: This property doesn't have purchase orders.");
                }
            }
        } else {
            System.out.println("WARNING: There aren't property available for purchase to be evaluated.");
        }
    }

    private void successMessage(Boolean success, String acceptanceAnswer) {
        try {
            if (success) {
                System.out.println("The order was successfully " + acceptanceAnswer + "ed.\n");
            } else {
                System.out.println(" ERROR: The order wasn't successfully " + acceptanceAnswer + "ed.\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("ERROR: ");
        }
    }

    private String selectAcceptanceAnswerOfOrder(OrderDto orderDto, int idx) {
        int answer = -1;
        boolean invalid = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.printf("\n[ORDER %s] Select an option to confirm your order acceptance:\n", idx);

                displayAcceptanceOptions();
                answer = input.nextInt();
                if (answer < 1 || answer > Answers.values().length) {
                    System.out.println("Invalid option. Please try again.");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid" + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);

        return (Answers.values()[answer - 1].toString().toLowerCase());
    }

    private void displayAcceptanceOptions() {
        int i = 1;
        System.out.println();
        for (Enum option : Answers.values()) {
            System.out.println(i + " - " + option.toString());
            i++;
        }
    }
}

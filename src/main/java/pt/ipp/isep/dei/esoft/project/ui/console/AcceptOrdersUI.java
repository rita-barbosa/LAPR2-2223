package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AcceptOrdersController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.domain.OrderDto;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
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
        System.out.println("\nAccept Purchase Orders");

        Boolean success;
        String acceptanceAnswer;
        List<AnnouncementDto> listAnnouncements = controller.getAnnouncementsList();

        for (AnnouncementDto a : listAnnouncements) {
            System.out.println(a.toString());
            System.out.println(a.getListOrdersDto().toString());
            for (OrderDto o : a.getListOrdersDto()) {
                acceptanceAnswer = selectAcceptanceAnswerOfOrder(a);
                success = controller.defineOrderAcceptance(acceptanceAnswer, a.getAnnouncementId(), o.getId());
                successMessage(success, acceptanceAnswer);
                if (acceptanceAnswer.equals(Answers.ACCEPT.toString().toLowerCase())) {
                    break;
                }
            }
        }
    }

    private void successMessage(Boolean success, String acceptanceAnswer) {
        if (success) {
            System.out.println("The order was successfully " + acceptanceAnswer);
        } else {
            System.out.println(" ERROR: The order wasn't successfully " + acceptanceAnswer);
        }
    }

    private String selectAcceptanceAnswerOfOrder(AnnouncementDto a) {
        int answer = -1;

        boolean invalid = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.println("Select an option to confirm your order acceptance:");
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

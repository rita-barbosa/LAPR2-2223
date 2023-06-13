package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsNetworkController;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.Serializable;
import java.util.*;

/**
 * The type List deals network ui.
 */
public class ListDealsNetworkUI implements Runnable, Serializable {

    private final ListDealsNetworkController controller = new ListDealsNetworkController();

    private String algorithm;

    private String sortingOrder;


    private ListDealsNetworkController getController() {
        return controller;
    }

    /**
     * Run.
     */
    public void run() {
        System.out.println("\n=============================================================\n");
        System.out.println("Deals in the Network");
        System.out.println("\n=============================================================");
        Optional<List<AnnouncementDto>> allDealsList = controller.toDto(controller.getAllDealsList());
        if (allDealsList.isPresent() && allDealsList.get().size() > 0) {
            displayList(allDealsList.get());
        }
        do {
            requestData();
            submitData();
        } while (askQuestion());
    }

    /**
     * Ask question boolean.
     *
     * @return the boolean
     */
    private Boolean askQuestion() {
        System.out.printf("Would you like to sort the deals in the network again?%n1. Yes%n2. No%n");
        boolean invalid = true;
        int answer = -1;
        Scanner input = new Scanner(System.in);
        do {
            try {
                answer = input.nextInt();
                if (answer != 1 && answer != 2) {
                    System.out.println("\nERROR: Option selected is invalid. (1 or 2)");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is not a number. (1 or 2)");
                input.nextLine();
            }
        } while (invalid);
        return answer == 1;
    }

    private void displayList(List<AnnouncementDto> allDealsList) {
        for (AnnouncementDto dto : allDealsList) {
                System.out.println(dto.toDealString());
            System.out.println("=============================================================");
        }
    }

    private void submitData() {
        System.out.println("\n=============================================================\n");
        System.out.println("Deals sorted by property area");
        System.out.println("\n=============================================================");
        Optional<List<AnnouncementDto>> sortedListDto = getController().getListSortedByAlgorithm(sortingOrder, algorithm);
        if (sortedListDto.isPresent() && sortedListDto.get().size() > 1) {
            displayList(sortedListDto.get());
        }
    }

    private void requestData() {
        algorithm = displayAndSelectAlgorithms();
        sortingOrder = displayAndSelectSortingOrder();
    }

    private String displayAndSelectSortingOrder() {
        List<String> sortingOrders = new ArrayList<>();
        sortingOrders.add("Ascending");
        sortingOrders.add("Descending");
        int answer = -1;
        boolean invalid = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                while (answer < 1 || answer > sortingOrders.size()) {
                    System.out.printf("Please select a sorting order:%n1. Ascending%n2. Descending%n");
                    answer = input.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);
        return (sortingOrders.get(answer - 1));
    }

    private String displayAndSelectAlgorithms() {
        List<String> algorithmsAvailable = new ArrayList<>();
        algorithmsAvailable.add("Merge Sort");
        algorithmsAvailable.add("Bubble Sort");
        int answer = -1;
        boolean invalid = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                while (answer < 1 || answer > algorithmsAvailable.size()) {
                    System.out.printf("Please select a sorting algorithm:%n1. Merge Sort%n2. Bubble Sort%n");
                    answer = input.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);
        return (algorithmsAvailable.get(answer - 1));
    }
}

package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ScheduleVisitController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * The type Schedule visit ui.
 */
public class ScheduleVisitUI {

    /**
     * The constant MAX_HOUR_IN_DAY.
     */
    private static final Integer MAX_HOUR_IN_DAY = 24;
    /**
     * The constant MIN_HOUR_IN_DAY.
     */
    private static final Integer MIN_HOUR_IN_DAY = 0;
    /**
     * The constant MAX_MONTH_IN_YEAR.
     */
    private static final Integer MAX_MONTH_IN_YEAR = 12;
    /**
     * The constant MIN_MONTH_IN_YEAR.
     */
    private static final Integer MIN_MONTH_IN_YEAR = 1;

    /**
     * The Visit day.
     */
    private Integer visitDay;

    /**
     * The Visit month.
     */
    private Integer visitMonth;

    /**
     * The Visit year.
     */
    private Integer visitYear;

    /**
     * The Start hour.
     */
    private Integer startHour;

    /**
     * The End hour.
     */
    private Integer endHour;

    /**
     * The Controller.
     */
    private final ScheduleVisitController controller = new ScheduleVisitController();

    /**
     * Gets controller.
     *
     * @return the controller
     */
    private ScheduleVisitController getController() {
        return controller;
    }

    /**
     * Run.
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        System.out.println("Schedule a Visit Request for an Announcement");

        List<AnnouncementDto> listToDisplay = controller.getAnnouncementListDto();
        String criteria;

        displayList(listToDisplay);

        do {
            while (askQuestion("select any criteria")) {
                criteria = displayAndSelectCriteria();
                listToDisplay = controller.getFilteredList(criteria);
                displayList(listToDisplay);
            }

            int listIndx = getIntAnswer();

            AnnouncementDto announcementDto = listToDisplay.get(listIndx);

            Announcement announcement = controller.toModel(announcementDto);

            requestData();

            displaysData();

            submitData(announcement);

        } while (askQuestion("submit another visit request"));
    }

    /**
     * Displays data.
     */
    private void displaysData() {
        System.out.printf("%n### Visit Data ###%nDate (Day/Month/Year): %d/%d/%d%nStarting hour: %d%nEnding hour: %d%n",
                visitDay, visitMonth, visitYear, startHour, endHour);
    }

    /**
     * Submit data.
     *
     * @param announcement the announcement
     */
    private void submitData(Announcement announcement) {
        Boolean confirmation = getController().scheduleVisit(announcement, startHour, endHour, visitDay, visitMonth, visitYear);
        if (confirmation) {
            System.out.println("\nVisit request successfully sent to agent!");
        } else {
            System.out.println("\nERROR! Visit request not sent to agent!");
        }
    }

    /**
     * Request data.
     */
    private void requestData() {
        visitDay = requestVisitDay();
        visitMonth = requestVisitMonth();
        visitYear = requestVisitYear();
        startHour = requestStartHour();
        endHour = requestEndHour(startHour);
    }

    /**
     * Request end hour integer.
     *
     * @param startHour the start hour
     * @return the integer
     */
    private Integer requestEndHour(Integer startHour) {
        System.out.println("When do you want to end your visit? (24-hour format)");
        int time = getIntAnswer();
        while (time > MAX_HOUR_IN_DAY && time < MIN_HOUR_IN_DAY && time < startHour) {
            System.out.println("Ending hour invalid!");
            System.out.println("Note that the time for the end of the visit cannot be earlier than the beginning of said visit.");
            System.out.printf("Starting hour: %d%n", startHour);
            System.out.println("Please choose an hour between 0 and 23.");
            time = getIntAnswer();
        }
        return time;
    }

    /**
     * Request start hour integer.
     *
     * @return the integer
     */
    private Integer requestStartHour() {
        System.out.println("When do you want to start your visit? (24 hours format)");
        int time = getIntAnswer();
        while (time > MAX_HOUR_IN_DAY && time < MIN_HOUR_IN_DAY) {
            System.out.println("Starting hour invalid! Please choose an hour between 0 and 23.");
            time = getIntAnswer();
        }
        return time;
    }

    /**
     * Request visit year integer.
     *
     * @return the integer
     */
    private Integer requestVisitYear() {
        System.out.println("In what year do you want to schedule your visit?");
        int year = getIntAnswer();
        LocalDate date = LocalDate.now();
        while (year < date.getYear()) {
            System.out.printf("Year invalid! Please choose a year from %d onwards.", date.getYear());
            year = getIntAnswer();
        }
        return year;
    }

    /**
     * Request visit month integer.
     *
     * @return the integer
     */
    private Integer requestVisitMonth() {
        System.out.println("In what month do you want to schedule your visit?");
        System.out.printf("1.January%n2.February%n3.March%n4.April%n" +
                "5.May%n6.June%n7.July%n8.August%n" +
                "9.September%n10.October%n11.November%n12.December%n");
        int month = getIntAnswer();
        while (month > MAX_MONTH_IN_YEAR && month < MIN_MONTH_IN_YEAR) {
            System.out.println("Starting hour invalid! Please choose an hour between 0 and 23.");
            month = getIntAnswer();
        }
        return month;
    }

    /**
     * Request visit day integer.
     *
     * @return the integer
     */
    private Integer requestVisitDay() {
        return null;
    }

    /**
     * Display list.
     *
     * @param listToDisplay the list to display
     */
    private void displayList(List<AnnouncementDto> listToDisplay) {
        int i = 1;
        for (AnnouncementDto a : listToDisplay) {
            System.out.printf("#%d%n", i++);
            System.out.println(a.getRequestAttributes());
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Display and select criteria string.
     *
     * @return the string
     */
    private String displayAndSelectCriteria() {
        List<String> criteriaList = controller.getCriteriaList();
        int listSize = criteriaList.size();
        int answer = -1;
        boolean invalid = true;
        Scanner input = new Scanner(System.in);
        do {
            try {
                while (answer < 1 || answer > listSize) {
                    displayCriteriaOptions(criteriaList);
                    System.out.println("Select a criteria:");
                    answer = input.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                input.nextLine();
            }
        } while (invalid);
        return (criteriaList.get(answer - 1));
    }

    /**
     * Display criteria options.
     *
     * @param criteriaList the criteria list
     */
    private void displayCriteriaOptions(List<String> criteriaList) {
        int i = 1;
        for (String criteria : criteriaList) {
            System.out.println(i + " - " + criteria);
            i++;
        }
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
     * Ask question boolean.
     *
     * @param sentence the sentence
     * @return the boolean
     */
    private boolean askQuestion(String sentence) {
        System.out.printf("Would you like to %s?%n1. Yes%n2. No%n", sentence);
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

}

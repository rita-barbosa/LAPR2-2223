package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListVisitsController;
import pt.ipp.isep.dei.esoft.project.domain.dto.VisitDto;

import java.time.LocalDate;
import java.util.*;

public class ListVisitsUI implements Runnable {

    private final ListVisitsController controller = new ListVisitsController();
    /**
     * The constant MAX_MONTH_IN_YEAR.
     */
    private static final Integer MAX_MONTH_IN_YEAR = 12;
    /**
     * The constant MIN_MONTH_IN_YEAR.
     */
    private static final Integer MIN_MONTH_IN_YEAR = 1;
    /**
     * The constant MIN_DAYS_IN_MONTH.
     */
    private static final Integer MIN_DAYS_IN_MONTH = 1;

    /**
     * The constant MAX_DAYS_IN_MONTH.
     */
    private static final Integer MAX_DAYS_IN_MONTH = 31;
    /**
     * The constant AVERAGE_DAYS_IN_MONTH.
     */
    private static final Integer AVERAGE_DAYS_IN_MONTH = 30;
    /**
     * The constant DAYS_IN_FEBRUARY.
     */
    private static final Integer DAYS_IN_FEBRUARY = 28;
    /**
     * The constant LEAP_YEARL_DAYS_IN_FEBRUARY.
     */
    private static final Integer LEAP_YEARL_DAYS_IN_FEBRUARY = 29;

    private LocalDate beginDate;

    private LocalDate endDate;
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

    private ListVisitsController getController(){
        return controller;
    }

    public void run(){
        beginDate = requestBeginDate();
        endDate = requestEndDate();
        Optional<List<VisitDto>> listVisitsDto = controller.getVisitRequestsList(beginDate, endDate);
        if (listVisitsDto.isPresent()){
            System.out.println("Booking requests:\n");
            displayList(listVisitsDto.get());
        }
    }

    private void displayList(List<VisitDto> visitDtoList){
        //CHECK THIS LATER

    }

    private LocalDate requestBeginDate(){
        System.out.println("Select a begin date:");
        visitYear = visitYear();
        visitMonth = visitMonth();
        visitDay = visitDay();
        return LocalDate.of(visitYear, visitMonth, visitDay);
    }

    private LocalDate requestEndDate(){
        System.out.println("Select a end date: (YYYY-MM-DD)");
        visitYear = visitYear();
        visitMonth = visitMonth();
        visitDay = visitDay();
        return LocalDate.of(visitYear, visitMonth, visitDay);
    }

    /**
     * Request visit year integer.
     *
     * @return the integer
     */
    private Integer visitYear() {
        System.out.println(" * In what year do you want to schedule your visit?");
        int year = getIntAnswer();
        LocalDate date = LocalDate.now();
        while (year < date.getYear()) {
            System.out.printf("Year invalid! Please choose a year from %d onwards.%n", date.getYear());
            year = getIntAnswer();
        }
        return year;
    }

    /**
     * Request visit month integer.
     *
     * @return the integer
     */
    private Integer visitMonth() {
        System.out.println(" * In what month do you want to schedule your visit?");
        System.out.printf("1.January%n2.February%n3.March%n4.April%n" +
                "5.May%n6.June%n7.July%n8.August%n" +
                "9.September%n10.October%n11.November%n12.December%n");
        int month = getIntAnswer();
        while (month > MAX_MONTH_IN_YEAR || month < MIN_MONTH_IN_YEAR) {
            System.out.println("Month invalid! Please choose an month between 1 and 12.");
            month = getIntAnswer();
        }
        return month;
    }

    /**
     * Request visit day integer.
     *
     * @return the integer
     */
    private Integer visitDay() {
        System.out.println(" * In what day do you want to schedule your visit?");
        int day = getIntAnswer();
        List<Integer> oddMonths = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
        List<Integer> evenMonths = Arrays.asList(4, 6, 9, 11);

        if (oddMonths.contains(visitMonth)) {
            while (day > MAX_DAYS_IN_MONTH || day < MIN_DAYS_IN_MONTH) {
                System.out.println("Day invalid! Please choose a day between 1 and 31.");
                day = getIntAnswer();
            }
        } else if (evenMonths.contains(visitMonth)) {
            while (day > AVERAGE_DAYS_IN_MONTH || day < MIN_DAYS_IN_MONTH) {
                System.out.println("Day invalid! Please choose a day between 1 and 30.");
                day = getIntAnswer();
            }
        } else {
            if (isItLeapYear(visitYear)) {
                while (day > LEAP_YEARL_DAYS_IN_FEBRUARY || day < MIN_DAYS_IN_MONTH) {
                    System.out.println("Day invalid! Please choose a day between 1 and 29.");
                    day = getIntAnswer();
                }
            } else {
                while (day > DAYS_IN_FEBRUARY || day < MIN_DAYS_IN_MONTH) {
                    System.out.println("Day invalid! Please choose a day between 1 and 28.");
                    day = getIntAnswer();
                }
            }
        }
        return day;
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
     * Returns true if the year passed as a parameter is a leap year. If the year
     * passed as a parameter is not a leap year, returns false.
     *
     * @param visitYear the year to validate.
     * @return true if the year passed as a parameter is a leap year, otherwise returns false.
     */
    private boolean isItLeapYear(Integer visitYear) {
        return visitYear % 4 == 0 && visitYear % 100 != 0 || visitYear % 400 == 0;
    }

}
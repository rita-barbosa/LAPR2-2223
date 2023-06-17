package pt.ipp.isep.dei.esoft.project.ui.console;

import javafx.util.Pair;
import pt.ipp.isep.dei.esoft.project.application.controller.ScheduleVisitController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.domain.dto.CriteriaDto;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.time.LocalDate;
import java.util.*;

/**
 * The type Schedule visit ui.
 */
public class ScheduleVisitUI implements Runnable {

    /**
     * The constant MAX_HOUR_IN_DAY.
     */
    private static final Integer MAX_HOUR_IN_DAY = 23;
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
     * The property type designation.
     */
    private String propertyTypeDesignation;

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
        System.out.println("\nSchedule a visit to a property");
        System.out.println("========================================================");
        Optional<List<AnnouncementDto>> nonDealsListDto;
        Optional<List<AnnouncementDto>> copycat;
        Optional<AnnouncementDto> announcementDto;
        Optional<Announcement> announcement;

        do {
            do {
                nonDealsListDto = controller.getAllNonDealAnnouncementsDto();
                if (nonDealsListDto.isPresent() && nonDealsListDto.get().size() > 0) {
                    boolean answer;
                    do {
                        displayList(nonDealsListDto.get());
                        answer = Utils.askDirectQuestion("Select any criteria:");
                        while (answer && nonDealsListDto.get().size() > 0) {
                            propertyTypeDesignation = "house";
                            copycat = filterList(nonDealsListDto.get());
                            if (copycat.isPresent()) {
                                nonDealsListDto = copycat;
                                if (nonDealsListDto.get().size() > 0) {
                                    displayList(nonDealsListDto.get());
                                }
                            }
                            if (nonDealsListDto.get().size() == 0) {
                                System.out.println("...\nNo announcements with chosen sequence of criteria!\nReturning to initial list.");
                                System.out.println("========================================================");
                                nonDealsListDto = controller.getAllNonDealAnnouncementsDto();
                                try {
                                    Thread.sleep(3500);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                displayList(nonDealsListDto.get());
                            } else {
                                answer = Utils.askDirectQuestion("Select any criteria:");
                            }
                        }
                    } while (answer);
                }
            } while (nonDealsListDto.get().size() == 0);
            nonDealsListDto.get();
            Integer announcementIndex = Utils.requestAnnouncementIndex(nonDealsListDto.get());
            announcementDto = Optional.of(nonDealsListDto.get().get(announcementIndex - 1));
            announcement = controller.toModel(announcementDto.get());
            requestData();
            displaysData();
            announcement.ifPresent(this::submitData);
        } while (Utils.askDirectQuestion("Submit another visit request?"));
    }

    /**
     * Gets filtered list.
     *
     * @return the filtered list
     */
    private Optional<List<AnnouncementDto>> filterList(List<AnnouncementDto> list) {
        Object criteria;
        switch (displayAndSelectCriteriaList(controller.getCriteriaList().get())) {
            case 1:
                criteria = displayAndSelectBusinessType();
                System.out.println("========================================================\nAnnouncements by type of business:");
                list = controller.getAnnouncementsByBusinessType((CriteriaDto) criteria, list);
                break;
            case 2:
                criteria = displayAndSelectPropertyType();
                propertyTypeDesignation = criteria.toString();
                System.out.println("========================================================\nAnnouncements by type of property:");
                list = controller.getAnnouncementsByPropertyType((CriteriaDto) criteria, list);
                break;
            case 3:
                criteria = selectNumberBedrooms();
                if (!propertyTypeDesignation.equalsIgnoreCase("Land")) {
                    System.out.println("========================================================\nAnnouncements by Number of Bedrooms:");
                    list = controller.getAnnouncementsByNumberBedrooms((Integer) criteria, list);
                } else {
                    list = new ArrayList<>();
                }
                break;
            case 4:
                criteria = displayAndSelectPrice();
                System.out.println("========================================================\nAnnouncements by Price:");
                list = controller.getAnnouncementsByPrice(criteria.toString(), list);
                break;
            case 5:
                criteria = displayAndSelectCity();
                System.out.println("========================================================\nAnnouncements by City:");
                list = controller.getAnnouncementsByCity(criteria.toString(), list);
                break;
            case 6:
                criteria = displayAndSelectState();
                System.out.println("========================================================\nAnnouncements by State:");
                list = controller.getAnnouncementsByState(criteria.toString(), list);
                break;
        }
        return Optional.of(list);
    }

    /**
     * Displays data.
     */
    private void displaysData() {
        System.out.printf("### Visit Data ###%nDate (Day/Month/Year): %d/%d/%d%nStarting hour: %d%nEnding hour: %d%n",
                visitDay, visitMonth, visitYear, startHour, endHour);
    }

    /**
     * Submit data.
     *
     * @param announcement the announcement
     */
    private void submitData(Announcement announcement) {
        try {
            Pair<Boolean, Integer> confirmation = getController().scheduleVisit(announcement, startHour, endHour, visitDay, visitMonth, visitYear);
            if (confirmation.getKey()) {
                System.out.println("\nVisit request successfully sent to agent!");
            } else {
                System.out.println("\nERROR! Visit request not sent to agent!");
                if (confirmation.getValue() == 1) {
                    System.out.println("CONFLICT! This visit is already scheduled.");
                } else if (confirmation.getValue() == 2) {
                    System.out.println("CONFLICT! The start hour of the visit request you want to submit conflicts with \na visit taking place at that time in the same property.");
                } else {
                    System.out.println("CONFLICT! The end hour of the visit request you want to submit conflicts with a \nvisit taking place at that time in the same property.");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\nERROR! Visit request not sent to agent!");
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    /**
     * Request data.
     */
    private void requestData() {
        visitYear = requestVisitYear();
        visitMonth = requestVisitMonth();
        visitDay = requestVisitDay();
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
        int time = Utils.readIntegerFromConsole("When do you want to end your visit? (24-hour format)");
        while (time > MAX_HOUR_IN_DAY || time < MIN_HOUR_IN_DAY || time < startHour) {
            System.out.println("Ending hour invalid!");
            System.out.print("Note that the time for the end of the visit cannot be earlier than the beginning of it.");
            time = Utils.readIntegerFromConsole("Please choose an hour between " + (startHour + 1) + " and 23.");
        }
        return time;
    }

    /**
     * Request start hour integer.
     *
     * @return the integer
     */
    private Integer requestStartHour() {
        int time = Utils.readIntegerFromConsole("When do you want to start your visit? (24-hours format)");
        while (time > MAX_HOUR_IN_DAY || time < MIN_HOUR_IN_DAY) {
            time = Utils.readIntegerFromConsole("Starting hour invalid! Please choose an hour between 0 and 23.");
        }
        return time;
    }

    /**
     * Request visit year integer.
     *
     * @return the integer
     */
    private Integer requestVisitYear() {
        int year = Utils.readIntegerFromConsole("In what year do you want to schedule your visit?");
        LocalDate date = LocalDate.now();
        while (year < date.getYear()) {
            year = Utils.readIntegerFromConsole("Year invalid! Please choose a year from " + date.getYear() + " onwards.");
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
        String monthsOption = "1.January\n2.February\n3.March\n4.April\n" +
                "5.May\n6.June\n7.July\n8.August\n" +
                "9.September\n10.October\n11.November\n12.December";
        int month = Utils.readIntegerFromConsole(monthsOption);
        while (month > MAX_MONTH_IN_YEAR || month < MIN_MONTH_IN_YEAR) {
            month = Utils.readIntegerFromConsole("Month invalid! Please choose an month between 1 and 12.");
        }
        return month;
    }

    /**
     * Request visit day integer.
     *
     * @return the integer
     */
    private Integer requestVisitDay() {
        int day = Utils.readIntegerFromConsole("In what day do you want to schedule your visit?");
        List<Integer> oddMonths = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
        List<Integer> evenMonths = Arrays.asList(4, 6, 9, 11);

        if (oddMonths.contains(visitMonth)) {
            while (day > MAX_DAYS_IN_MONTH || day < MIN_DAYS_IN_MONTH) {
                day = Utils.readIntegerFromConsole("Day invalid! Please choose a day between 1 and 31.");
            }
        } else if (evenMonths.contains(visitMonth)) {
            while (day > AVERAGE_DAYS_IN_MONTH || day < MIN_DAYS_IN_MONTH) {
                day = Utils.readIntegerFromConsole("Day invalid! Please choose a day between 1 and 30.");
            }
        } else {
            if (isItLeapYear(visitYear)) {
                while (day > LEAP_YEARL_DAYS_IN_FEBRUARY || day < MIN_DAYS_IN_MONTH) {
                    day = Utils.readIntegerFromConsole("Day invalid! Please choose a day between 1 and 29.");
                }
            } else {
                while (day > DAYS_IN_FEBRUARY || day < MIN_DAYS_IN_MONTH) {
                    day = Utils.readIntegerFromConsole("Day invalid! Please choose a day between 1 and 28.");
                }
            }
        }
        return day;
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

    /**
     * Display list.
     *
     * @param listToDisplay the list to display
     */
    private void displayList(List<AnnouncementDto> listToDisplay) {
        int i = 1;
        for (AnnouncementDto a : listToDisplay) {
            System.out.printf("#%d%n", i++);
            System.out.printf("%s", a.getRequestAttributes());
            System.out.println("========================================================");
        }
    }

    /**
     * This method display and aks to select criteria from the criteria list.
     *
     * @param criterias the list of criterias
     * @return the criteria chosen
     */
    private Integer displayAndSelectCriteriaList(List<CriteriaDto> criterias) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        System.out.println("Criteria available:");
        for (CriteriaDto criteria : criterias) {
            count++;
            System.out.printf("%d - %s\n", count, criteria);
        }
        int option = 0;
        boolean invalid = true;
        do {
            try {
                System.out.println("Which one do you want to choose?");
                while (option < 1 || option > 6) {
                    option = sc.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid!"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);
        return option;
    }

    /**
     * This method display and aks to select a type of business.
     *
     * @return the type of business chosen
     */
    private CriteriaDto displayAndSelectBusinessType() {
        Scanner sc = new Scanner(System.in);
        List<CriteriaDto> businessTypeDtoList = controller.getBusinessTypeList().get();
        displayBusinessTypeOptions(businessTypeDtoList);
        int option = 0;
        boolean invalid = true;
        do {
            try {
                while (option < 1 || option > businessTypeDtoList.size()) {
                    System.out.println("Select type of business:");
                    option = sc.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);
        return (businessTypeDtoList.get(option - 1));
    }

    /**
     * This method display the type of business options.
     *
     * @param businessTypes the business types
     */
    private void displayBusinessTypeOptions(List<CriteriaDto> businessTypes) {
        int count = 0;
        System.out.println("Type of business:");
        for (CriteriaDto businessType : businessTypes) {
            count++;
            System.out.printf("%d - %s\n", count, businessType);
        }
    }

    /**
     * This method display and aks to select a type of property.
     *
     * @return the type of property chosen
     */
    private CriteriaDto displayAndSelectPropertyType() {
        Scanner sc = new Scanner(System.in);
        List<CriteriaDto> propertyTypesDtoList = controller.getPropertyTypeList().get();
        displayPropertiesTypeOptions(propertyTypesDtoList);
        int option = 0;
        boolean invalid = true;
        do {
            try {
                while (option < 1 || option > propertyTypesDtoList.size()) {
                    System.out.println("Select type of property:");
                    option = sc.nextInt();
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);
        return (propertyTypesDtoList.get(option - 1));
    }

    /**
     * This method display the type of properties options.
     *
     * @param propertyTypes the property types
     */
    private void displayPropertiesTypeOptions(List<CriteriaDto> propertyTypes) {
        int count = 0;
        System.out.println("Type of properties:");
        for (CriteriaDto propertyType : propertyTypes) {
            count++;
            System.out.printf("%d - %s\n", count, propertyType);
        }
    }


    /**
     * This method display and aks to select number of bedrooms.
     *
     * @return the number of bedrooms chosen
     */
    private Integer selectNumberBedrooms() {
        if (propertyTypeDesignation.equalsIgnoreCase("Land")) {
            System.out.println("This filter is unavailable for Land announcements!");
            return null;
        }
        return Utils.readIntegerFromConsole("Property's number of bedrooms:");
    }

    /**
     * This method display and aks to select a type of sorting to price.
     *
     * @return the type of sorting select to price
     */
    private String displayAndSelectPrice() {
        return Utils.sortSelection("Price:");
    }

    /**
     * This method display and aks to select a type of sorting to city.
     *
     * @return the type of sorting select to city
     */
    private String displayAndSelectCity() {
        return Utils.sortSelection("City:");
    }

    /**
     * This method display and aks to select a type of sorting to state.
     *
     * @return the type of sorting select to state
     */
    private String displayAndSelectState() {
        return Utils.sortSelection("State:");
    }


}

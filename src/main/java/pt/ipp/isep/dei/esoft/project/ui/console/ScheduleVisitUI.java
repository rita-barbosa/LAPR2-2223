package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ScheduleVisitController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.time.LocalDate;
import java.util.*;

/**
 * The type Schedule visit ui.
 */
public class ScheduleVisitUI implements Runnable {

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
     * The Announcement index.
     */
    private Integer announcementIndex;

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
        Optional<List<Announcement>> listToDisplay = controller.getAllAnnouncementsList();
        Optional<List<Announcement>> copycat;
        Optional<List<AnnouncementDto>> listToDisplayDto = Optional.empty();
        Optional<AnnouncementDto> announcementDto;
        Optional<Announcement> announcement;

        do {
            do {
                if (listToDisplay.get().size() > 0) {
                    listToDisplayDto = controller.getAnnouncementListDto(listToDisplay.get());
                }
                if (listToDisplayDto.isPresent() && listToDisplayDto.get().size() > 0) {
                    boolean answer;
                    do {
                        displayList(listToDisplayDto.get());
                        answer = askQuestion("select any criteria");
                        copycat = Optional.of(new ArrayList<>(listToDisplay.get()));
                        while (answer && listToDisplayDto.get().size() > 0) {
                            copycat = filterList(copycat.get());
                            if (copycat.isPresent()) {
                                listToDisplayDto = controller.toDto(copycat.get());

                                if (listToDisplayDto.isPresent() && listToDisplayDto.get().size() > 0) {
                                    displayList(listToDisplayDto.get());
                                }
                            }
                            if (listToDisplayDto.get().size() == 0) {
                                System.out.println("...\nNo announcements with chosen sequence of criteria!\nReturning to initial list.");
                                System.out.println("========================================================");
                                // Reset listToDisplay and listToDisplayDto to the original list and its DTO
                                copycat = Optional.of(new ArrayList<>(listToDisplay.get()));
                                listToDisplayDto = controller.getAnnouncementListDto(copycat.get());
                                try {
                                    Thread.sleep(3500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                displayList(listToDisplayDto.get());  // Display the original list again
                            } else {
                                answer = askQuestion("select any criteria");
                            }
                        }
                    } while (answer);
                }
            } while (listToDisplayDto.get().size() == 0);
            listToDisplayDto.get();
            announcementIndex = requestAnnouncementIndex(listToDisplayDto.get());
            announcementDto = Optional.of(listToDisplayDto.get().get(announcementIndex - 1));
            announcement = controller.toModel(announcementDto.get());
            requestData();
            displaysData();
            announcement.ifPresent(this::submitData);
        } while (askQuestion("submit another visit request"));
    }


    /**
     * Request announcement index integer.
     *
     * @param list the list
     * @return the integer
     */
    private Integer requestAnnouncementIndex(List<AnnouncementDto> list) {
        System.out.println("Please select an announcement by its index number.");
        int idx;
        idx = getIntAnswer();
        while (idx > list.size() || idx < 1) {
            System.out.println("The announcement index must be within the displayed list.");
            idx = getIntAnswer();
        }
        return idx;
    }

    /**
     * Gets filtered list.
     *
     * @return the filtered list
     */
    private Optional<List<Announcement>> filterList(List<Announcement> list) {
        Object criteria;
        switch (displayAndSelectCriteriaList(controller.getCriteriaList())) {
            case 1:
                criteria = displayAndSelectBusinessType().toLowerCase();
                System.out.println("========================================================\nAnnouncements by type of business:");
                list = controller.getAnnouncementsByBusinessType(criteria.toString(), list);
                break;
            case 2:
                criteria = displayAndSelectPropertyType().toLowerCase();
                System.out.println("========================================================\nAnnouncements by type of property:\n");
                list = controller.getAnnouncementsByPropertyType(criteria.toString(), list);
                break;
            case 3:
                criteria = displayAndSelectNumberBedrooms();
                System.out.println("========================================================\nAnnouncements by Number of Bedrooms:");
                list = controller.getAnnouncementsByNumberBedrooms((Integer) criteria, list);
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
        Boolean confirmation = getController().scheduleVisit(announcement, startHour, endHour, visitDay, visitMonth, visitYear);
        if (confirmation) {
            System.out.println("\nVisit request successfully sent to agent!\n");
        } else {
            System.out.println("\nERROR! Visit request not sent to agent!\n");
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
        System.out.println("When do you want to end your visit? (24-hour format)");
        int time = getIntAnswer();
        while (time > MAX_HOUR_IN_DAY || time < MIN_HOUR_IN_DAY || time < startHour) {
            System.out.println("Ending hour invalid!");
            System.out.println("Note that the time for the end of the visit cannot be earlier than the beginning of it.");
            System.out.printf("Please choose an hour between 0 and 23, and later than %d.%n", startHour);
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
        System.out.println("When do you want to start your visit? (24-hours format)");
        int time = getIntAnswer();
        while (time > MAX_HOUR_IN_DAY || time < MIN_HOUR_IN_DAY) {
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
    private Integer requestVisitMonth() {
        System.out.println("In what month do you want to schedule your visit?");
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
    private Integer requestVisitDay() {
        System.out.println("In what day do you want to schedule your visit?");
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
        System.out.println();
    }

    /**
     * This method display and aks to select criteria from the criteria list.
     *
     * @param criterias the list of criterias
     * @return the criteria chosen
     */
    private Integer displayAndSelectCriteriaList(List<String> criterias) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        System.out.println("Criteria available:");
        for (String criteria : criterias) {
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
    private Boolean askQuestion(String sentence) {
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

    /**
     * This method display and aks to select a type of business.
     *
     * @return the type of business chosen
     */
    private String displayAndSelectBusinessType() {
        Scanner sc = new Scanner(System.in);
        List<BusinessType> businessTypes = controller.getBusinessTypeList();
        displayBusinessTypeOptions(businessTypes);
        int option = 0;
        boolean invalid = true;
        do {
            try {
                while (option < 1 || option > businessTypes.size()) {
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
        return (businessTypes.get(option - 1).getDesignation());
    }

    /**
     * This method display the type of business options.
     *
     * @param businessTypes the business types
     */
    private void displayBusinessTypeOptions(List<BusinessType> businessTypes) {
        int count = 0;
        System.out.println("Type of business:");
        for (BusinessType businessType : businessTypes) {
            count++;
            System.out.printf("%d - %s\n", count, businessType);
        }
    }

    /**
     * This method display and aks to select a type of property.
     *
     * @return the type of property chosen
     */
    private String displayAndSelectPropertyType() {
        Scanner sc = new Scanner(System.in);
        List<PropertyType> propertyTypes = controller.getPropertyTypeList();
        displayPropertiesTypeOptions(propertyTypes);
        int option = 0;
        boolean invalid = true;
        do {
            try {
                while (option < 1 || option > propertyTypes.size()) {
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
        return (propertyTypes.get(option - 1).getDesignation());
    }

    /**
     * This method display the type of properties options.
     *
     * @param propertyTypes the property types
     */
    private void displayPropertiesTypeOptions(List<PropertyType> propertyTypes) {
        int count = 0;
        System.out.println("Type of properties:");
        for (PropertyType propertyType : propertyTypes) {
            count++;
            System.out.printf("%d - %s\n", count, propertyType);
        }
    }


    /**
     * This method display and aks to select number of bedrooms.
     *
     * @return the number of bedrooms chosen
     */
    private Integer displayAndSelectNumberBedrooms() {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        boolean invalid = true;
        do {
            try {
                while (option < 0) {
                    System.out.println("Enter number of bedrooms:");
                    option = sc.nextInt();
                    if (option <= 0) {
                        System.out.println("ERROR: Number of bedrooms is invalid.");
                    }
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);
        return option;

    }

    /**
     * This method display and aks to select a type of sorting to price.
     *
     * @return the type of sorting select to price
     */
    private String displayAndSelectPrice() {
        System.out.println("Price:");
        return sortSelection();
    }

    /**
     * This method display and aks to select a type of sorting to city.
     *
     * @return the type of sorting select to city
     */
    private String displayAndSelectCity() {
        System.out.println("City:");
        return sortSelection();
    }

    /**
     * This method display and aks to select a type of sorting to state.
     *
     * @return the type of sorting select to state
     */
    private String displayAndSelectState() {
        System.out.println("State:");
        return sortSelection();
    }

    /**
     * This method display and aks to select a type of sorting.
     *
     * @return the type of sorting chosen
     */
    private String sortSelection() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Sort types available:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        int option = 0;
        boolean invalid = true;
        do {
            try {
                while (option < 1 || option > 2) {
                    option = sc.nextInt();
                    if (option == 1) {
                        return "Ascending";
                    } else if (option == 2) {
                        return "Descending";
                    }
                }
                invalid = false;
            } catch (InputMismatchException e) {
                System.out.println("\nERROR: Option selected is invalid"
                        + " (" + e.getClass().getSimpleName() + ")");
                sc.nextLine();
            }
        } while (invalid);
        return null;
    }
}

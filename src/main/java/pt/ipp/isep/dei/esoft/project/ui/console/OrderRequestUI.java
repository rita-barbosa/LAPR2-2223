//package pt.ipp.isep.dei.esoft.project.ui.console;
//
//import pt.ipp.isep.dei.esoft.project.application.controller.OrderRequestController;
//import pt.ipp.isep.dei.esoft.project.application.controller.OrderRequestResult;
//import pt.ipp.isep.dei.esoft.project.domain.Announcement;
//import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
//import pt.ipp.isep.dei.esoft.project.domain.BusinessType;
//import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
//import pt.isep.lei.esoft.auth.domain.model.Email;
//
//import java.util.InputMismatchException;
//import java.util.List;
//import java.util.Optional;
//import java.util.Scanner;
//
//public class OrderRequestUI implements Runnable{
//
//    /**
//     * The Order amount.
//     */
//    private Double orderAmount;
//    /**
//     * The email of the client that submitted the order.
//     */
//    private Email clientEmail;
//    /**
//     * The Announcement index.
//     */
//    private Integer announcementIndex;
//    /**
//     * The Controller.
//     */
//    private final OrderRequestController controller = new OrderRequestController();
//    /**
//     * Gets controller.
//     *
//     * @return the controller
//     */
//    private OrderRequestController getController() {
//        return controller;
//    }
//
//    @Override
//    public void run() {
//        System.out.println("\nCreate a order request for a property.");
//        System.out.println("========================================================");
//        Optional<List<Announcement>> listToDisplay = controller.getAllAnnouncementsList();
//        Optional<List<AnnouncementDto>> listToDisplayDto = Optional.empty();
//        Optional<AnnouncementDto> announcementDto = Optional.empty();
//        Optional<Announcement> announcement = Optional.empty();
//
//        if (listToDisplay.isPresent() && listToDisplay.get().size() > 0) {
//            listToDisplayDto = controller.getAnnouncementListDto(listToDisplay.get());
//        }
//        if (listToDisplayDto.isPresent() && listToDisplayDto.get().size() > 0) {
//            displayList(listToDisplayDto.get());
//            while (askQuestion("select any criteria")) {
//                listToDisplay = filterList();
//                if (listToDisplay.isPresent() && listToDisplay.get().size() > 0) {
//                    listToDisplayDto = controller.toDto(listToDisplay.get());
//                    if (listToDisplayDto.isPresent() && listToDisplayDto.get().size() > 0) {
//                        displayList(listToDisplayDto.get());
//                    }
//                }
//            }
//        }
//        if (listToDisplayDto.isPresent() && listToDisplayDto.get().size() > 0) {
//            announcementIndex = requestAnnouncementIndex(listToDisplayDto.get());
//            announcementDto = Optional.of(listToDisplayDto.get().get(announcementIndex - 1));
//        }
//        if (announcementDto.isPresent()) {
//            announcement = controller.toModel(announcementDto.get());
//        }
//        System.out.println("\nIntroduce the order amount.");
//        requestData(announcement.get());
//        displaysData();
//        announcement.ifPresent(this::submitData) ;
//
//    }
//
//    /**
//     * Request announcement index integer.
//     *
//     * @param list the list
//     * @return the integer
//     */
//    private Integer requestAnnouncementIndex(List<AnnouncementDto> list) {
//        System.out.println("Please select an announcement by its index number.");
//        int idx;
//        idx = getIntAnswer();
//        while (idx > list.size() || idx < 1) {
//            System.out.println("The announcement index must be within the displayed list.");
//            idx = getIntAnswer();
//        }
//        return idx;
//    }
//
//    /**
//     * Gets filtered list.
//     *
//     * @return the filtered list
//     */
//    private Optional<List<Announcement>> filterList() {
//        Optional<List<Announcement>> listToDisplay = controller.getAllAnnouncementsList();
//        Object criteria;
//        switch (displayAndSelectCriteriaList(controller.getCriteriaList())) {
//            case 1:
//                criteria = displayAndSelectBusinessType().toLowerCase();
//                System.out.println("========================================================\nAnnouncements by type of business:");
//                listToDisplay = Optional.of(controller.getAnnouncementsByBusinessType(criteria.toString()));
//                break;
//            case 2:
//                criteria = displayAndSelectPropertyType().toLowerCase();
//                System.out.println("========================================================\nAnnouncements by type of property:\n");
//                listToDisplay = Optional.of(controller.getAnnouncementsByPropertyType(criteria.toString()));
//                break;
//            case 3:
//                criteria = displayAndSelectNumberBedrooms();
//                System.out.println("========================================================\nAnnouncements by Number of Bedrooms:");
//                listToDisplay = Optional.of(controller.getAnnouncementsByNumberBedrooms((Integer) criteria));
//                break;
//            case 4:
//                criteria = displayAndSelectPrice();
//                System.out.println("========================================================\nAnnouncements by Price:");
//                listToDisplay = Optional.of(controller.getAnnouncementsByPrice(criteria.toString()));
//                break;
//            case 5:
//                criteria = displayAndSelectCity();
//                System.out.println("========================================================\nAnnouncements by City:");
//                listToDisplay = Optional.of(controller.getAnnouncementsByCity(criteria.toString()));
//                break;
//            case 6:
//                criteria = displayAndSelectState();
//                System.out.println("========================================================\nAnnouncements by State:");
//                listToDisplay = Optional.of(controller.getAnnouncementsByState(criteria.toString()));
//                break;
//        }
//        return listToDisplay;
//    }
//
//    /**
//     * Displays data.
//     */
//    private void displaysData() {
//        System.out.printf("### Order Request ###\nOrder amount %s\nClient email: %s\n",
//                orderAmount, clientEmail);
//    }
//
//    /**
//     * Submit data.
//     *
//     * @param announcement the announcement
//     */
//    private void submitData(Announcement announcement) {
//        OrderRequestResult confirmation = getController().orderRequest(announcement, orderAmount);
//        switch (confirmation){
//
//            case PENDING_ORDER:
//                System.out.println("You still have a pending order in this announcement, wait for a response.");
//                break;
//            case SAME_VALUE:
//                System.out.println("The order amount submitted has already been posted for this property. Please contact the agent that is responsible for this property.");
//                break;
//            case SUCCESS:
//                System.out.println("Your order was submitted with success. ");
//                break;
//        }
//    }
//
//    /**
//     * Request data.
//     */
//    private void requestData(Announcement announcement) {
//        orderAmount = requestOrderAmount(announcement);
//    }
//
//    private Double requestOrderAmount(Announcement announcement) {
//        System.out.println("");
//        double value = getDoubleAnswer();
//        while (value > announcement.getRequest().getBusiness().getPrice()) {
//            System.out.printf("They order amount can´t be higher then the order in the announcement");
//            value = getIntAnswer();
//        }
//        return value;
//    }
//
//    private double getDoubleAnswer() {
//        Scanner input = new Scanner(System.in);
//        boolean invalid = true;
//        Double value = null;
//        do {
//            try {
//                value = input.nextDouble();
//                invalid = false;
//            } catch (InputMismatchException e) {
//                System.out.println("\nERROR: Value typed is invalid"
//                        + " (" + e.getClass().getSimpleName() + ")");
//                input.nextLine();
//            }
//        } while (invalid);
//        return value;
//    }
//
//    /**
//     * Display list.
//     *
//     * @param listToDisplay the list to display
//     */
//    private void displayList(List<AnnouncementDto> listToDisplay) {
//        int i = 1;
//        for (AnnouncementDto a : listToDisplay) {
//            System.out.printf("#%d%n", i++);
//            System.out.printf("%s", a.getRequestAttributes());
//            System.out.println("========================================================");
//        }
//        System.out.println();
//    }
//
//    /**
//     * This method display and aks to select criteria from the criteria list.
//     *
//     * @param criterias the list of criterias
//     * @return the criteria chosen
//     */
//    private Integer displayAndSelectCriteriaList(List<String> criterias) {
//        Scanner sc = new Scanner(System.in);
//        int count = 0;
//        System.out.println("Criteria available:");
//        for (String criteria : criterias) {
//            count++;
//            System.out.printf("%d - %s\n", count, criteria);
//        }
//        int option = 0;
//        boolean invalid = true;
//        do {
//            try {
//                System.out.println("Which one do you want to choose?\n");
//                while (option < 1 || option > 6) {
//                    option = sc.nextInt();
//                }
//                invalid = false;
//            } catch (InputMismatchException e) {
//                System.out.println("\nERROR: Option selected is invalid!"
//                        + " (" + e.getClass().getSimpleName() + ")");
//                sc.nextLine();
//            }
//        } while (invalid);
//        return option;
//    }
//
//    /**
//     * Gets int answer.
//     *
//     * @return the int answer
//     */
//    private Integer getIntAnswer() {
//        Scanner input = new Scanner(System.in);
//        boolean invalid = true;
//        Integer value = null;
//        do {
//            try {
//                value = input.nextInt();
//                invalid = false;
//            } catch (InputMismatchException e) {
//                System.out.println("\nERROR: Value typed is invalid"
//                        + " (" + e.getClass().getSimpleName() + ")");
//                input.nextLine();
//            }
//        } while (invalid);
//        return value;
//    }
//
//    /**
//     * Ask question boolean.
//     *
//     * @param sentence the sentence
//     * @return the boolean
//     */
//    private Boolean askQuestion(String sentence) {
//        System.out.printf("Would you like to %s?%n1. Yes%n2. No%n", sentence);
//        boolean invalid = true;
//        int answer = -1;
//        Scanner input = new Scanner(System.in);
//        do {
//            try {
//                answer = input.nextInt();
//                if (answer != 1 && answer != 2) {
//                    System.out.println("\nERROR: Option selected is invalid. (1 or 2)");
//                } else {
//                    invalid = false;
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("\nERROR: Option selected is not a number. (1 or 2)");
//                input.nextLine();
//            }
//        } while (invalid);
//        return answer == 1;
//    }
//
//    private String displayAndSelectBusinessType() {
//        Scanner sc = new Scanner(System.in);
//        List<BusinessType> businessTypes = controller.getBusinessTypeList();
//        displayBusinessTypeOptions(businessTypes);
//        int option = 0;
//        boolean invalid = true;
//        do {
//            try {
//                while (option < 1 || option > businessTypes.size()) {
//                    System.out.println("Select type of business:");
//                    option = sc.nextInt();
//                }
//                invalid = false;
//            } catch (InputMismatchException e) {
//                System.out.println("\nERROR: Option selected is invalid"
//                        + " (" + e.getClass().getSimpleName() + ")");
//                sc.nextLine();
//            }
//        } while (invalid);
//        return (businessTypes.get(option - 1).getDesignation());
//    }
//
//    /**
//     * This method display the type of business options.
//     *
//     * @param businessTypes the business types
//     */
//    private void displayBusinessTypeOptions(List<BusinessType> businessTypes) {
//        int count = 0;
//        System.out.println("Type of business:");
//        for (BusinessType businessType : businessTypes) {
//            count++;
//            System.out.printf("%d - %s\n", count, businessType);
//        }
//    }
//
//    /**
//     * This method display and aks to select a type of property.
//     *
//     * @return the type of property chosen
//     */
//    private String displayAndSelectPropertyType() {
//        Scanner sc = new Scanner(System.in);
//        List<PropertyType> propertyTypes = controller.getPropertyTypeList();
//        displayPropertiesTypeOptions(propertyTypes);
//        int option = 0;
//        boolean invalid = true;
//        do {
//            try {
//                while (option < 1 || option > propertyTypes.size()) {
//                    System.out.println("Select type of property:");
//                    option = sc.nextInt();
//                }
//                invalid = false;
//            } catch (InputMismatchException e) {
//                System.out.println("\nERROR: Option selected is invalid"
//                        + " (" + e.getClass().getSimpleName() + ")");
//                sc.nextLine();
//            }
//        } while (invalid);
//        return (propertyTypes.get(option - 1).getDesignation());
//    }
//
//    /**
//     * This method display the type of properties options.
//     *
//     * @param propertyTypes the property types
//     */
//    private void displayPropertiesTypeOptions(List<PropertyType> propertyTypes) {
//        int count = 0;
//        System.out.println("Type of properties:");
//        for (PropertyType propertyType : propertyTypes) {
//            count++;
//            System.out.printf("%d - %s\n", count, propertyType);
//        }
//    }
//
//
//    /**
//     * This method display and aks to select number of bedrooms.
//     *
//     * @return the number of bedrooms chosen
//     */
//    private Integer displayAndSelectNumberBedrooms() {
//        Scanner sc = new Scanner(System.in);
//        int option = -1;
//        boolean invalid = true;
//        do {
//            try {
//                while (option < 0) {
//                    System.out.println("Enter number of bedrooms:");
//                    option = sc.nextInt();
//                    if (option <= 0) {
//                        System.out.println("ERROR: Number of bedrooms is invalid.");
//                    }
//                }
//                invalid = false;
//            } catch (InputMismatchException e) {
//                System.out.println("\nERROR: Option selected is invalid"
//                        + " (" + e.getClass().getSimpleName() + ")");
//                sc.nextLine();
//            }
//        } while (invalid);
//        return option;
//
//    }
//
//    /**
//     * This method display and aks to select a type of sorting to price.
//     *
//     * @return the type of sorting select to price
//     */
//    private String displayAndSelectPrice() {
//        System.out.println("Price:");
//        return sortSelection();
//    }
//
//    /**
//     * This method display and aks to select a type of sorting to city.
//     *
//     * @return the type of sorting select to city
//     */
//    private String displayAndSelectCity() {
//        System.out.println("City:");
//        return sortSelection();
//    }
//
//    /**
//     * This method display and aks to select a type of sorting to state.
//     *
//     * @return the type of sorting select to state
//     */
//    private String displayAndSelectState() {
//        System.out.println("State:");
//        return sortSelection();
//    }
//
//    /**
//     * This method display and aks to select a type of sorting.
//     *
//     * @return the type of sorting chosen
//     */
//    private String sortSelection() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Sort types available:");
//        System.out.println("1. Ascending");
//        System.out.println("2. Descending");
//        int option = 0;
//        boolean invalid = true;
//        do {
//            try {
//                while (option < 1 || option > 2) {
//                    option = sc.nextInt();
//                    if (option == 1) {
//                        return "Ascending";
//                    } else if (option == 2) {
//                        return "Descending";
//                    }
//                }
//                invalid = false;
//            } catch (InputMismatchException e) {
//                System.out.println("\nERROR: Option selected is invalid"
//                        + " (" + e.getClass().getSimpleName() + ")");
//                sc.nextLine();
//            }
//        } while (invalid);
//        return null;
//    }
//}
//

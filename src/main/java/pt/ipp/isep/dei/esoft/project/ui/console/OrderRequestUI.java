package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.OrderRequestController;
import pt.ipp.isep.dei.esoft.project.application.controller.OrderRequestResult;
import pt.ipp.isep.dei.esoft.project.domain.Announcement;
import pt.ipp.isep.dei.esoft.project.domain.dto.AnnouncementDto;
import pt.ipp.isep.dei.esoft.project.domain.BusinessType;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.dto.CriteriaDto;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class OrderRequestUI implements Runnable {

    /**
     * The Order amount.
     */
    private Double orderAmount;
    /**
     * The email of the client that submitted the order.
     */
    private Email clientEmail;
    /**
     * The Announcement index.
     */
    private Integer announcementIndex;
    /**
     * The property type designation.
     */
    private String propertyTypeDesignation;
    /**
     * The Controller.
     */
    private final OrderRequestController controller = new OrderRequestController();

    /**
     * Gets controller.
     *
     * @return the controller
     */
    private OrderRequestController getController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\nCreate a order request for a property.");
        System.out.println("========================================================");
        //Optional<List<Announcement>> listToDisplay = controller.getAllNonDealAnnouncementsDto();
        Optional<List<AnnouncementDto>> nonDealsListDto  = controller.getAllNonDealAnnouncementsDto();
        Optional<List<AnnouncementDto>> copycat;
        Optional<AnnouncementDto> announcementDto = Optional.empty();
        Optional<Announcement> announcement = Optional.empty();

        try {
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
            if (nonDealsListDto.isPresent() && nonDealsListDto.get().size() > 0) {
                announcementIndex = requestAnnouncementIndex(nonDealsListDto.get());
                announcementDto = Optional.of(nonDealsListDto.get().get(announcementIndex - 1));
            }
            if (announcementDto.isPresent()) {
                announcement = controller.toModel(announcementDto.get());
            }
            requestData(announcement.get());
            displaysData();
            announcement.ifPresent(this::submitData);
        } catch (Exception e) {
            System.out.println("ERROR: There are no property available for sale/rent");
        }

    }

    /**
     * Request announcement index integer.
     *
     * @param list the list
     * @return the integer
     */
    private Integer requestAnnouncementIndex(List<AnnouncementDto> list) {
        int idx;
        idx = Utils.readIntegerFromConsole("Please select an announcement by its index number.");
        while (idx > list.size() || idx < 1) {
            idx = Utils.readIntegerFromConsole("The announcement index must be within the displayed list.");
        }
        return idx;
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
                criteria = displayAndSelectNumberBedrooms();
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
        System.out.printf("### Order Request ###\nOrder amount %s\nClient email: %s\n",
                orderAmount, controller.getUserPerson().get().getEmailAddress());
    }

    /**
     * Submit data.
     *
     * @param announcement the announcement
     */
    private void submitData(Announcement announcement) {
        OrderRequestResult confirmation = getController().orderRequest(announcement, orderAmount);
        switch (confirmation) {

            case PENDING_ORDER:
                System.out.println("You still have a pending order in this announcement, wait for a response.");
                break;
            case SAME_VALUE:
                System.out.println("The order amount submitted has already been posted for this property. Please contact the agent that is responsible for this property.");
                break;
            case SUCCESS:
                System.out.println("Your order was submitted with success. ");
                break;
        }
    }

    /**
     * Request data.
     */
    private void requestData(Announcement announcement) {
        orderAmount = requestOrderAmount(announcement);
    }

    private Double requestOrderAmount(Announcement announcement) {
        double value = Utils.readDoubleFromConsole("Introduce the order amount.");
        while (value > announcement.getRequest().getBusiness().getPrice()) {
            System.out.printf("The order amount can not be higher then\nthe amount asked in the announcement\n");
            value = Utils.readIntegerFromConsole("Introduce the order amount.");
        }
        return value;
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
    private Integer displayAndSelectNumberBedrooms() {
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


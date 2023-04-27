package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateRequestController;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.*;

public class CreateRequestUI implements Runnable {

    private final CreateRequestController controller = new CreateRequestController();
    private String propertyTypeDesignation;
    private String businessTypeDesignation;
    private Double amount;
    private Double area;
    private Integer contractDuration;
    private ArrayList<AvailableEquipment> availableEquipment;
    private String streetName;
    private String city;
    private String district;
    private String state;
    private String zipCode;
    private Boolean basement;
    private Boolean inhabitableLoft;
    private Integer parkingSpace;
    private Enum<SunExposureTypes> sunExposure;
    private Integer numberBedroom;
    private Integer numberBathroom;
    private Agent agent;
    private Agency agency;
    private Double distanceCityCenter;
    private ArrayList<Photograph> photograph;
    //  private Integer agencyID;

    public void run() {
        System.out.println("Create Request for Property Announcement");

        businessTypeDesignation = displayAndSelectBusinessType();

        propertyTypeDesignation = displayAndSelectPropertyType();

        // LEASE INFO

        requestData();

        submitData();
    }

    private void submitData() {
        Optional<Task> task = getController().createTask(taskReference, taskDescription, taskInformalDescription,
                taskTechnicalDescription, taskDuration, taskCost, taskCategoryDescription);

        if (task.isPresent()) {
            System.out.println("Task successfully created!");
        } else {
            System.out.println("Task not created!");
        }
    }

    private void requestData() {

        //Request the Request amount from the console
        amount = requestRequestAmount();

        //Request the Request area from the console
        area = requestRequestArea();

        //Request the Request's distanceCityCenter from the console
        distanceCityCenter = requestRequestDistanceCityCenter();

        //Request the Request's streetName from the console
        streetName = requestRequestStreetName();

        //Request the Request's city from the console
        city = requestRequestCity();

        //Request the Request's district from the console
        district = requestRequestDistrict();

        //Request the Request's state from the console
        state = requestRequestState();

        //Request the Request's zipcode from the console
        zipCode = requestRequestZipCode();

        //Request the Request's parkingSpace from the console
        parkingSpace = requestRequestParkingSpace();

        //Request the Request's numberBedroom from the console
        numberBedroom = requestRequestNumberBedroom();

        //Request the Request's numberBathroom from the console
        numberBathroom = requestRequestNumberBathroom();

        //Request the Request's availableEquipment from the console
        // availableEquipment = requestRequestAvailableEquipment();      HERE

        //Request the Request's inhabitableLoft from the console
        basement = requestRequestInhabitableLoft();

        //Request the Request's basement from the console
        inhabitableLoft = requestRequestBasement();

        //Request the Request's sunExposure from the console
        sunExposure = requestRequestSunExposure();

        //Request the Request's photograph from the console
        // photograph = requestRequestPhotograph();                         HERE

        // agent & agency
    }

    private Double requestRequestAmount() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property price:");
        return input.nextDouble();
    }

    private Double requestRequestArea() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property area:");
        return input.nextDouble();
    }

    private Double requestRequestDistanceCityCenter() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's distance from City Center:");
        return input.nextDouble();
    }

    private String requestRequestStreetName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's street name:");
        return input.nextLine();
    }

    private String requestRequestCity() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's city:");
        return input.nextLine();
    }

    private String requestRequestDistrict() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's district:");
        return input.nextLine();
    }

    private String requestRequestState() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's state:");
        return input.nextLine();
    }

    private String requestRequestZipCode() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's zip code:");
        return input.nextLine();
    }

    private Integer requestRequestParkingSpace() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's number of parking spaces:");
        return input.nextInt();
    }

    private Integer requestRequestNumberBedroom() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's number of bedrooms:");
        return input.nextInt();
    }

    private Integer requestRequestNumberBathroom() {
        Scanner input = new Scanner(System.in);
        System.out.println("Property's number of bathrooms:");
        return input.nextInt();
    }

    private Boolean requestRequestInhabitableLoft() {
        Scanner input = new Scanner(System.in);
        System.out.printf("Does your property have an inhabitable loft?%n1.Yes%n2.No)%n");
        int inputOption;
        do {
            inputOption = input.nextInt();;
            if (inputOption == 1) {
                return true;
            }
            if (inputOption == 2) {
                return false;
            }
            throw new InputMismatchException("Please select 1 (Yes) or 2 (No)%n");
        }while (!(inputOption != 1 & inputOption != 2));
    }

    private Boolean requestRequestBasement() {
        Scanner input = new Scanner(System.in);
        System.out.printf("Does your property have a basement?%n1.Yes%n2.No)%n");
        int inputOption;
        do {
            inputOption = input.nextInt();;
            if (inputOption == 1) {
                return true;
            }
            if (inputOption == 2) {
                return false;
            }
            throw new InputMismatchException("Please select 1 (Yes) or 2 (No)%n");
        }while (!(inputOption != 1 & inputOption != 2));
    }

    private Enum<SunExposureTypes> requestRequestSunExposure() {
        Scanner input = new Scanner(System.in);
        System.out.printf("Please sele");
        int inputOption;
        do {
            inputOption = input.nextInt();;
            if (inputOption == 1) {
                return true;
            }
            if (inputOption == 2) {
                return false;
            }
            throw new InputMismatchException("Please select 1 (Yes) or 2 (No)");
        }while (!(inputOption != 1 & inputOption != 2));
    }


//     private ArrayList<AvailableEquipment> requestRequestParkingSpace() {
//        Scanner input = new Scanner(System.in);
//        System.out.println("Property's number of bathrooms:");
//        return input.nextInt();
//    }


    private Double requestTaskCost() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Cost:");
        return input.nextDouble();
    }

    private Integer requestTaskDuration() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Duration:");
        return input.nextInt();
    }

    private String requestTaskTechnicalDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Technical Description:");
        return input.nextLine();
    }

    private String requestTaskInformalDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Informal Description:");
        return input.nextLine();
    }

    private String requestTaskDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Description:");
        return input.nextLine();
    }

    private String requestTaskReference() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Reference:");
        return input.nextLine();
    }

    private String displayAndSelectTaskCategory() {
        //Display the list of task categories
        List<TaskCategory> taskCategories = controller.getTaskCategories();

        int listSize = taskCategories.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayTaskCategoryOptions(taskCategories);
            System.out.println("Select a task category:");
            answer = input.nextInt();
        }

        String description = taskCategories.get(answer - 1).getDescription();
        return description;

    }

    private String displayAndSelectBusinessType() {
        //Display the list of business types
        List<BusinessType> businessTypes = controller.getBusinessTypes();

        int listSize = businessTypes.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayBusinessTypeOptions(businessTypes);
            System.out.println("Select a business type:");
            answer = input.nextInt();
        }
        return businessTypes.get(answer - 1).getDesignation();
    }

    private String displayAndSelectPropertyType() {
        //Display the list of property types
        List<PropertyType> propertyTypes = controller.getPropertyTypes();

        int listSize = propertyTypes.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayPropertyTypeOptions(propertyTypes);
            System.out.println("Select a property type:");
            answer = input.nextInt();
        }
        return propertyTypes.get(answer - 1).getDesignation();
    }

    private void displayTaskCategoryOptions(List<TaskCategory> taskCategories) {
        //display the task categories as a menu with number options to select
        int i = 1;
        for (TaskCategory taskCategory : taskCategories) {
            System.out.println(i + " - " + taskCategory.getDescription());
            i++;
        }
    }

    private void displayBusinessTypeOptions(List<BusinessType> businessTypes) {
        //display the business types as a menu with number options to select
        int i = 1;
        for (BusinessType businessType : businessTypes) {
            System.out.println(i + " - " + businessType.getDesignation());
            i++;
        }
    }

    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {
        //display the property types as a menu with number options to select
        int i = 1;
        for (PropertyType propertyType : propertyTypes) {
            System.out.println(i + " - " + propertyType.getDesignation());
            i++;
        }
    }
}

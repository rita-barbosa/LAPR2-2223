# US 002 - Publish an announcement

# 4. Tests 

**Test 1:** Checks if an Announcement is successfully created.  
```java
 @Test
    void ensureAnnouncementIsCreatedSuccessfully() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");


        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
    }
```
**Test 2:** Checks if an Announcement is successfully created.


*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class PublishAnnouncementController

```java
public Optional<Announcement> publishAnnouncement(Double commissionValue, String commissionTypeDesignation, String ownerEmail, String propertyTypeDesignation,
        String streetName, String city, String district, String state, String zipCode, Double area, Double distanceCityCenter, Double price, Integer numberBedroom,
        Integer numberParkingSpace, Boolean existenceBasement, Boolean inhabitableLoft, Integer numberBathroom, List<String> availableEquipmentDescriptionList,
        List<String> uriList, SunExposureTypes sunExposure) {

        String email = getEmailFromSession();
        Optional<Agency> agency = getAgencyByEmail(email);
        Employee agent = getAgentByEmail(email, agency);

        PropertyType propertyType = getPropertyTypeByDesignation(propertyTypeDesignation);

        Optional<Request> newRequest;
        Optional<Announcement> newAnnouncement = Optional.empty();

        CommissionType commissionType = getCommissionTypeByDesignation(commissionTypeDesignation);

        if (agency.isPresent()) {
        newRequest = agency.get().createSaleRequest(ownerEmail, propertyType, "sale", price, area, availableEquipmentDescriptionList, streetName, city, district, state,
        zipCode, existenceBasement, inhabitableLoft, numberParkingSpace, sunExposure, numberBedroom, numberBathroom, agent, distanceCityCenter, uriList);
        if (newRequest.isPresent()) {
        newAnnouncement = agency.get().publishAnnouncement(agent, commissionType, commissionValue, newRequest.get());
        }
        }
        return newAnnouncement;

        }
```


## Class Agency

```java
 public Optional<Announcement> publishAnnouncement(Employee agent, CommissionType commissionType, Double commissionValue, Request request) {

        Optional<Announcement> optionalValue = Optional.empty();

        Announcement announcement = new Announcement(agent, commissionType, commissionValue, request);

        if (addAnnouncement(announcement)) {
        optionalValue = Optional.of(announcement);
        }
        return optionalValue;
        }

```


# 6. Integration and Demo 

* A new option on the Employee menu options was added.


# 7. Observations

Agency class is getting too many responsibilities due to IE pattern and, therefore, is are becoming huge and hard to maintain. 

Is there any way to avoid this to happen?






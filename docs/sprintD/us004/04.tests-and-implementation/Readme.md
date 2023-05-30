# US 004 - To submit a Request for an Announcement

# 4. Tests

### CreateRequestController Tests

**Test 1:** Checks if Create Request works.

```java
   @Test
    void ensureCreateRequestWorks() {

            //Arrange
            //Get Repositories
            Repositories repositories = Repositories.getInstance();
            PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
            AgencyRepository agencyRepository = new AgencyRepository();
            BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
            AuthenticationRepository authenticationRepository = new AuthenticationRepository();

            PropertyType propertyType = new PropertyType("Land");
            propertyTypeRepository.add(propertyType);
            ArrayList<PropertyType> propType = new ArrayList<>();
        propType.add(propertyType);

        BusinessType businessType = new BusinessType("Sale");
        businessTypeRepository.add(businessType);
        ArrayList<BusinessType> bussType = new ArrayList<>();
        bussType.add(businessType);

        Agency agency = new Agency("RE/MAX");
        agencyRepository.add(agency);
        Employee employee = new Employee("employee@this.app", "agent");
        agency.addEmployee(employee);

        //String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();

        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("John", "client1@this.app", "01CLIet",
        AuthenticationController.ROLE_CLIENT);

        authenticationRepository.doLogin("client1@this.app", "01CLIet");

        CreateRequestController controller =
        new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);

        //Act
        Optional<Request> result = controller.createRequest("Land", "Sale", 2345.0,
        35.5, 0, av, "street", "city", "district", "state", "12345", null,
        null, null, null, null, null, employee, 89.3, uriList, agency);
        }
```

**Test 2:** Checks if Create Request fails for non-existent agency.

```java
    @Test
    void ensureCreateRequestForNonExistingAgencyFails() {
            //Arrange
            //Get Repositories
            Repositories repositories = Repositories.getInstance();
            PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
            AgencyRepository agencyRepository = new AgencyRepository();
            BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
            AuthenticationRepository authenticationRepository = new AuthenticationRepository();

            PropertyType propertyType = new PropertyType("Land");
            propertyTypeRepository.add(propertyType);
            ArrayList<PropertyType> propType = new ArrayList<>();
        propType.add(propertyType);

        BusinessType businessType = new BusinessType("Sale");
        businessTypeRepository.add(businessType);
        ArrayList<BusinessType> bussType = new ArrayList<>();
        bussType.add(businessType);

        Agency agency = new Agency("RE/MAX");
        Employee employee = new Employee("employee@this.app", "agent");
        agency.addEmployee(employee);

        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();

        authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
        authenticationRepository.addUserWithRole("John", "client1@this.app", "01CLInt",
        AuthenticationController.ROLE_CLIENT);

        authenticationRepository.doLogin("client1@this.app", "01CLInt");

        CreateRequestController controller =
        new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);

        //Act
        Optional<Request> result = controller.createRequest("Land", "Sale", 2345.0,
        35.5, 0, av, "street", "city", "district", "state", "12345", null,
        null, null, null, null, null, employee, 89.3, uriList, agency);

        assertTrue(result.isEmpty());
        }
```

**Test 3:** Checks if Get Property Type list works.

```java
@Test
    void ensureGetPropertyTypeListWorks() {

            Repositories repositories = Repositories.getInstance();
            PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
            AgencyRepository agencyRepository = new AgencyRepository();
            BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
            AuthenticationRepository authenticationRepository = new AuthenticationRepository();

            PropertyType propertyType = new PropertyType("House");
            propertyTypeRepository.add(propertyType);

            PropertyType propertyType1 = new PropertyType("Land");
            propertyTypeRepository.add(propertyType1);

            ArrayList<PropertyType> expected = new ArrayList<>();
        expected.add(propertyType);
        expected.add(propertyType1);

        CreateRequestController controller =
        new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);


        List<PropertyType> propertyTypeList = controller.getPropertyTypes();

        assertArrayEquals(expected.toArray(), propertyTypeList.toArray());
        }
```

**Test 4:** Checks if Get Business Type list works.

```java
     @Test
    void ensureGetBusinessTypeListWorks() {

            Repositories repositories = Repositories.getInstance();
            PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
            AgencyRepository agencyRepository = new AgencyRepository();
            BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
            AuthenticationRepository authenticationRepository = new AuthenticationRepository();


            BusinessType businessType = new BusinessType("Lease");
            businessTypeRepository.add(businessType);

            BusinessType businessType1 = new BusinessType("Sale");
            businessTypeRepository.add(businessType1);

            ArrayList<BusinessType> expected = new ArrayList<>();
        expected.add(businessType);
        expected.add(businessType1);

        CreateRequestController controller =
        new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);


        List<BusinessType> businessTypeList = controller.getBusinessTypes();

        assertArrayEquals(expected.toArray(), businessTypeList.toArray());
        }
```

**Test 5:** Checks if Get Agencies list works.

```java
     @Test
    void ensureGetAgenciesListWorks() {

            Repositories repositories = Repositories.getInstance();
            PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
            AgencyRepository agencyRepository = new AgencyRepository();
            BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
            AuthenticationRepository authenticationRepository = new AuthenticationRepository();


            Agency agency = new Agency(1234);
            Agency agency1 = new Agency(6789);

            agencyRepository.add(agency);
            agencyRepository.add(agency1);

            ArrayList<Agency> expected = new ArrayList<>();
        expected.add(agency);
        expected.add(agency1);

        CreateRequestController controller =
        new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);


        List<Agency> agencies = controller.getAgenciesList();

        assertArrayEquals(expected.toArray(), agencies.toArray());
        }
```

**Test 6:** Checks if Get Agents list works.

```java
    @Test
    void ensureGetAgentsListWorks() {
            Repositories repositories = Repositories.getInstance();
            PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
            AgencyRepository agencyRepository = new AgencyRepository();
            BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
            AuthenticationRepository authenticationRepository = new AuthenticationRepository();

            Agency agency = new Agency(6789);
            agencyRepository.add(agency);


            Employee employee = new Employee("employee@this.app", "agent");
            Employee employee1 = new Employee("employee1@this.app", "agent");

            agency.addEmployee(employee);
            agency.addEmployee(employee1);

            ArrayList<Employee> expected = new ArrayList<>();
        expected.add(employee);
        expected.add(employee1);

        CreateRequestController controller =
        new CreateRequestController(agencyRepository, propertyTypeRepository, businessTypeRepository, authenticationRepository);


        List<Employee> agents = controller.getAgents(agency);

        assertArrayEquals(expected.toArray(), agents.toArray());
        }
```

### Request Tests

**Test 1:** Checks if a Request is successfully created.

```java
      @Test
    void ensureRequestIsCreatedSuccessfully() {
            String ownerEmail = "owner@email.com";
            Employee employee = new Employee("employee@this.app.com", "Agent");
            List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
        "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
        }
```

### Agency Tests

**Test 1:** Checks if Create Request Works

```java
    @Test
    void ensureCreateRequestWorks() {
            Employee employee = new Employee("employee@this.app.com", "Agent");
            Location location = new Location("street", "city", "district", "state", "12345");
            Agency agency1 = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
            agency1.addEmployee(employee);

            String ownerEmail = "owner@email.com";
            List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();
        Property property = new Property(new PropertyType("land"), 35.5, 89.3, uriList, "street",
        "city", "district", "state", "12345");

        Request expected = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Optional<Request> request = agency1.createRequest(ownerEmail, new PropertyType("land"), "sale", 2345.0,
        35.5, 0, av, "street", "city", "district", "state", "12345", null,
        null, 0, null, 0, 0, employee, 89.3, uriList);

        assertNotNull(request);
        assertTrue(request.isPresent());
        assertEquals(request.get(), expected);
        }
```

### Property Type

**Test 1:** Checks if Get Designation works.

```java
    @Test
    void ensureGetDesignationWorks() {
        PropertyType propertyType = new PropertyType("Property Type");
        assertEquals("Property Type", propertyType.getDesignation());
    }
```

### Business Type

**Test 1:** Checks if Get Price works.

```java
  @Test
    void ensureGetPriceWorks() {
    Business b1 = new Business("Business Type", 2.0);

    assertEquals(b1.getPrice(), 2.0);
    }
```


# 5. Construction (Implementation)

## Class CreateRequestController

```java
       public Optional<Request> createRequest(String propertyTypeDesignation, String businessTypeDesignation, Double amount,
        Double area, Integer contractDuration, List<String> availableEquipmentDescription,
        String streetName, String city, String district, String state, String zipCode,
        Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure,
        Integer numberBedroom, Integer numberBathroom, Employee agent, Double distanceCityCenter,
        List<String> photograph, Agency agency) {

        String ownerEmail = getOwnerEmail();

        PropertyType propertyType = getPropertyTypeByDesignation(propertyTypeDesignation);

        Optional<Request> newRequest = Optional.empty();

        Optional<Agency> newAgency = getAgencyByID(agency.getId());

        if (newAgency.isPresent()) {
        newRequest = newAgency.get()
        .createRequest(ownerEmail, propertyType, businessTypeDesignation, amount, area, contractDuration,
        availableEquipmentDescription, streetName, city, district, state, zipCode, basement, inhabitableLoft,
        parkingSpace, sunExposure, numberBedroom, numberBathroom, agent, distanceCityCenter, photograph);
        return newRequest;
        }
        return newRequest;
        }
```

## Class Agency

```java
public Optional<Request> createRequest(String ownerEmail, PropertyType propertyType, String businessTypeDesignation, Double amount, Double area, Integer contractDuration, List<String> availableEquipmentDescription, String streetName, String city, String district, String state, String zipCode, Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure, Integer numberBedroom, Integer numberBathroom, Employee agent, Double distanceCityCenter, List<String> uri) {
    
        Optional<Request> optionalValue = Optional.empty();

        Request request;

        if (businessTypeDesignation.equalsIgnoreCase(LEASE_BUSINESSTYPE)) {
        request = new Request(ownerEmail, propertyType, businessTypeDesignation, amount, area, contractDuration, availableEquipmentDescription, streetName, city, district, state, zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom, numberBathroom, agent, distanceCityCenter, uri);
        } else {
        request = new Request(ownerEmail, propertyType, businessTypeDesignation, amount, area, availableEquipmentDescription, streetName, city, district, state, zipCode, basement, inhabitableLoft, parkingSpace, sunExposure, numberBedroom, numberBathroom, agent, distanceCityCenter, uri);
        }

        if (addRequest(request)) {
        optionalValue = Optional.of(request);
        }
        return optionalValue;
        }
```

# 6. Integration and Demo


# 7. Observations

Agency class is getting too many responsibilities due to IE pattern and, therefore, is becoming to extense and hard to
maintain.







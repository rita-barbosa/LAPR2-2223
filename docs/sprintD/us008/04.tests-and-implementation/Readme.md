# US 008 - To accept property announcement requests 

# 4. Tests 

### AcceptRequestsController Tests

**Test 1:** Check if Get Requests List Works 

```java
@Test
void ensureGetRequestsListWorks() {
        AgencyRepository agencyRepository = new AgencyRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();

        CommissionType c1 = new CommissionType("Fixed");
        commissionTypeRepository.add(c1);

        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
        Agency agency = new Agency(1234, "Make It Home Deluxe", "agency4@this.app", "999 444 5656", location);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("John", "employee@this.app", "01AGEnt",
                AuthenticationController.ROLE_AGENT);

        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);
        authenticationRepository.doLogin("employee@this.app", "01AGEnt");

        agency.addEmployee(employee);

        String ownerEmail = "owner@email.com";
        CommissionType commissionType = new CommissionType("fixed");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "st", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        agency.addRequest(request);
        agencyRepository.add(agency);

        ListRequestsController controller = new ListRequestsController(authenticationRepository, agencyRepository, commissionTypeRepository);

        Optional<List<RequestDto>> listRequestsDto = controller.getRequestsList();

        assertTrue(listRequestsDto.isPresent());
        assertEquals(1, listRequestsDto.get().size());
    }


```


**Test 2:** Check if get Commission Type List Works.

```java
@Test
void ensureGetCommissionTypeListWorks() {
        CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        CommissionType commissionType1 = new CommissionType("Commission Type Description One");
        commissionTypeRepository.add(commissionType1);

        CommissionType commissionType2 = new CommissionType("Commission Type Description Two");
        commissionTypeRepository.add(commissionType2);

        ArrayList<CommissionType> expected = new ArrayList<CommissionType>();
        expected.add(commissionType1);
        expected.add(commissionType2);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("John", "employee@this.app", "01AGEnt",
                AuthenticationController.ROLE_AGENT);
        Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");

        Agency agency = new Agency(1234);
        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);
        agency.addEmployee(employee);
        agencyRepository.add(agency);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("agent", "agent1@this.app","01AGEnt" ,
                AuthenticationController.ROLE_AGENT);

        authenticationRepository.doLogin("agent1@this.app", "01AGEnt");

        ListRequestsController controller = new ListRequestsController(authenticationRepository, agencyRepository, commissionTypeRepository);

        Optional<List<CommissionType>> commissionTypeList = controller.getCommissionTypeList();

        assertArrayEquals(expected.toArray(), commissionTypeList.toArray());
    }


```

**Test 3:** Check if get requests list works.

```java
@Test
void ensureGetRequestByIdDtoWorks() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "st", "12345");
        Business business = new Business("sale", 2345.0);
        Request request = new Request(ownerEmail, property, business, LocalDate.now(), employee);

        RequestDto dto = new RequestDto(property.toString(), business.toString(), 0, LocalDate.now().toString());

        Optional<Request> newRequest = new Optional<Request>(2345.0);
    }


```


**Test 4:** Check if publish announcement works.

```java
@Test
void ensurePublishAnnouncementWorks() {
        Repositories repositories = Repositories.getInstance();
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        CommissionTypeRepository commissionTypeRepository = new CommissionTypeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        CommissionType c1 = new CommissionType("Fixed");
        commissionTypeRepository.add(c1);

        PropertyType propertyType = new PropertyType("Land");
        propertyTypeRepository.add(propertyType);
        ArrayList<PropertyType> propType = new ArrayList<>();
        propType.add(propertyType);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("John", "agent1@this.app", "01AGEnt",
                AuthenticationController.ROLE_AGENT);

        Agency agency = new Agency(1234);
        Employee employee = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "1agent1@this.app", "agent", "423-423-2345", "city", "district", "AK", "12345", "street");
        agency.addEmployee(employee);
        agencyRepository.add(agency);

        authenticationRepository.doLogin("agent1@this.app", "01AGEnt");

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av = new ArrayList<>();

        AnnouncementDto a = new AnnouncementDto(ownerEmail, propertyType.getDesignation(), 4534, 453452, "St.Nicholas", "Pole North", "Madagascar", "NY", "14524", uriList, 35434, c1.getDesignation());

        PublishAnnouncementController ctrl = new PublishAnnouncementController(commissionTypeRepository, authenticationRepository, propertyTypeRepository, agencyRepository);

        Boolean result = ctrl.publishAnnouncement(a);
    }


```

### Request Tests


**Test 1:** Check if request is created.

```java
@Test
void ensureRequestIsCreatedSuccessfully() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);
    }


```


**Test 2:** Check if get request id works

```java
    @Test
    void getId() {
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        Property property = new Property(new PropertyType("land"), (35.5), (89.3), uriList, "street",
                "city", "district", "ST", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee, 0);

        assertEquals(0, request.getId());

    }

```

### RequestDto Tests

**Test 1:** Check if get property attributes works.

```java
@Test
    void ensureGetPropertyAttributesWorks() {
        String propertyAttributes = "propertyAttributes";
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto("propertyAttributes", "businessAttributes", id, requestDate);

        assertEquals(propertyAttributes, dto.getPropertyAttributes());
    }


```

**Test 2:** Check if get business attributes works.

```java
@Test
    void ensureGetBusinessAttributesWorks() {
        String businessAttributes = "businessAttributes";
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto("propertyAttributes", "businessAttributes", id, requestDate);

        assertEquals(businessAttributes, dto.getBusinessAttributes());
    }


```


**Test 3:** Check if get request date works.

```java
@Test
    void ensureGetRequestDateWorks() {
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto("propertyAttributes", "businessAttributes", id, requestDate);

        assertEquals(requestDate, dto.getRequestDate());
    }


```


**Test 4:** Check if get request id works.

```java
@Test
    void ensureGetRequestIdWorks() {
        Integer id = 0;
        String requestDate = "2020-10-10";

        RequestDto dto = new RequestDto("propertyAttributes", "businessAttributes", id, requestDate);

        assertEquals(id, dto.getRequestId());
    }


```


# 5. Construction (Implementation)


## Class ListRequestsController 

```java
public ListRequestsController(AuthenticationRepository authenticationRepository, AgencyRepository agencyRepository, CommissionTypeRepository commissionTypeRepository){
        this.authenticationRepository = authenticationRepository;
        this.agencyRepository = agencyRepository;
        this.commissionTypeRepository = commissionTypeRepository;
        }
```


## Class Request

```java
public Optional<Request> createRequest(String ownerEmail, PropertyType propertyType, String businessTypeDesignation, Double amount, Double area, Integer contractDuration, List<String> availableEquipmentDescription, String streetName, String city, String district, String state, String zipCode, Boolean basement, Boolean inhabitableLoft, Integer parkingSpace, Enum<SunExposureTypes> sunExposure, Integer numberBedroom, Integer numberBathroom, Employee agent, Double distanceCityCenter, List<String> uri) {

        // When a Request is added, it should fail if the Request already exists in the list of Request.
        // In order to not return null if the operation fails, we use the Optional class.
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

* A new option on the Agent menu options was added.

* Some demo purposes some tasks are bootstrapped while system starts.


# 7. Observations







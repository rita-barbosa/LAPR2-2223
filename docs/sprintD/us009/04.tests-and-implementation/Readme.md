# US 009 - Schedule a visit to a property

# 4. Tests

**Test 1:** Check that getting All Non Deal Announcements Dto works. 

```java
  @Test
    void getAllNonDealAnnouncementsDto() {
            PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
            AgencyRepository agencyRepository = new AgencyRepository();
            BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
            AuthenticationRepository authenticationRepository = new AuthenticationRepository();
            CriteriaRepository criteriaRepository = new CriteriaRepository();
            PersonRepository personRepository = new PersonRepository();

            ScheduleVisitController controller =
            new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);


            Optional<List<AnnouncementDto>> list = controller.getAllNonDealAnnouncementsDto();

        List<AnnouncementDto> expected = new ArrayList<>();

        if (list.isPresent() && list.get().size() > 0) {
        expected = list.get();
        }

        List<Announcement> actual = new ArrayList<>();

        for (Agency agency : agencyRepository.getAgenciesList()) {
        actual.addAll(agency.getAnnouncementsList());
        }

        List<AnnouncementDto> actualDto = new ArrayList<>();

        for (Announcement announcement : actual){
        actualDto.add(new AnnouncementDto(announcement.getId(), announcement.getRequestAttributes(), announcement.getCommissionAttributes(), announcement.getAcceptanceDate().toString(),
        OrderMapper.toDto(announcement.getListOfOrders())));
        }

        assertEquals(expected, actualDto);
        }
```
        

**Test 2:** Ensure that scheduling a visit works.

```java
    @Test
    void scheduleVisit() {
            PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
            AgencyRepository agencyRepository = new AgencyRepository();
            BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
            AuthenticationRepository authenticationRepository = new AuthenticationRepository();
            CriteriaRepository criteriaRepository = new CriteriaRepository();
            PersonRepository personRepository = new PersonRepository();

            ScheduleVisitController controller =
            new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

            authenticationRepository.addUserRole(AuthenticationController.ROLE_CLIENT, AuthenticationController.ROLE_CLIENT);
            authenticationRepository.addUserWithRole("Jake Moon", "client1@this.app", "01CLIen",
            AuthenticationController.ROLE_CLIENT);

            Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
            List<String> roles = new ArrayList<>();
        roles.add("client");
        String ownerEmail = "owner@email.com";
        Employee employee = new Employee(1234, "John", "C12345678", "456-45-3485", new Email("employee@this.app"), roles, "567-789-1234", location);
        authenticationRepository.doLogin("client1@this.app", "01CLIen");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
        uriList, "street", "city", "district", "AK", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);

        assertTrue(true);
        }
 ```

**Test 3:** Ensure that converting non deal announcements to DTO works.

```java
    @Test
    void toDto() {
            PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
            AgencyRepository agencyRepository = new AgencyRepository();
            BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
            AuthenticationRepository authenticationRepository = new AuthenticationRepository();
            CriteriaRepository criteriaRepository = new CriteriaRepository();
            PersonRepository personRepository = new PersonRepository();

            ScheduleVisitController controller =
            new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

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
        uriList, "street", "city", "district", "AK", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);
        Email client1 = new Email("client1@this.app");
        Order o1 = new Order(23000.0, client1);
        Announcement a1 = new Announcement(employee, commissionType, 234.0, request);
        a1.addOrder(o1);

        agency.addAnnouncement(a1);
        agencyRepository.add(agency);

        List<AnnouncementDto> actual;

        actual = controller.toDto(agency.getAnnouncements().getList()).get();

        String requestAttributes = a1.getRequestAttributes();
        String commissionAttributes = a1.getCommissionAttributes();
        int id = a1.getId();
        String acceptanceDate = a1.getAcceptanceDate().toString();
        List<Order> listOrders = a1.getListOfOrders();
        List<OrderDto> listOrdersDto = OrderMapper.toDto(listOrders);

        AnnouncementDto expected = new AnnouncementDto(id, requestAttributes, commissionAttributes, acceptanceDate, listOrdersDto);

        assertEquals(expected, actual.get(0));
        }
 ```

**Test 4:** Ensure that converting non deal announcement DTO to Model works.

```java
    @Test
    void toModel() {
            PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
            AgencyRepository agencyRepository = new AgencyRepository();
            BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
            AuthenticationRepository authenticationRepository = new AuthenticationRepository();
            CriteriaRepository criteriaRepository = new CriteriaRepository();
            PersonRepository personRepository = new PersonRepository();

            ScheduleVisitController controller =
            new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

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
        uriList, "street", "city", "district", "AK", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);
        Email client1 = new Email("client1@this.app");
        Order o1 = new Order(23000.0, client1);
        Announcement a1 = new Announcement(employee, commissionType, 234.0, request);
        a1.addOrder(o1);

        agency.addAnnouncement(a1);
        agencyRepository.add(agency);

        List<AnnouncementDto> actualList;

        actualList = controller.toDto(agency.getAnnouncements().getList()).get();

        String requestAttributes = a1.getRequestAttributes();
        String commissionAttributes = a1.getCommissionAttributes();
        int id = a1.getId();
        String acceptanceDate = a1.getAcceptanceDate().toString();
        List<Order> listOrders = a1.getListOfOrders();
        List<OrderDto> listOrdersDto = OrderMapper.toDto(listOrders);

        AnnouncementDto a2 = new AnnouncementDto(id, requestAttributes, commissionAttributes, acceptanceDate, listOrdersDto);


        Announcement expected = controller.toModel(a2).get();

        Announcement actual = controller.toModel(actualList.get(0)).get();

        assertEquals(expected, actual);
        }
 ```

**Test 4:** Ensure that list is filtered by choosen criteria.

```java
     @Test
    void getAnnouncementsByPropertyType() {
            PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
            AgencyRepository agencyRepository = new AgencyRepository();
            BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
            AuthenticationRepository authenticationRepository = new AuthenticationRepository();
            CriteriaRepository criteriaRepository = new CriteriaRepository();
            PersonRepository personRepository = new PersonRepository();

            Location location = new Location("street", "city", "district", "AK", "12345");
            Agency agency = new Agency(1234, "Description", "agency@email.com", "345 567 3456", location);
            String ownerEmail = "owner@email.com";
            String ownerEmail1 = "owner1@email.com";
            Employee employee = new Employee(1234, "John Doe", "C12000078",
            "004-45-6989", "employee@this.app", "agent", "623-456-7890",
            "New York", "Manhattan", "NY", "10001", "Broadway");
            CommissionType commissionType = new CommissionType("Commission Type");
            List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
        uriList, "street", "city", "district", "AK", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (65.5), (89.3),
        uriList, "street 1", "city 1", "district 1", "AK", "12345");
        Request request1 = new Request(ownerEmail1, property1, new Business("sale", 2345.0), LocalDate.now(), employee);

        agencyRepository.add(agency);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency.publishAnnouncement(employee, commissionType, 234.0, request1);

        ScheduleVisitController controller =
        new ScheduleVisitController(agencyRepository, personRepository, criteriaRepository, authenticationRepository, propertyTypeRepository, businessTypeRepository);

        List<AnnouncementDto> expected = controller.getAnnouncementsByPropertyType(new CriteriaDto("Land"));

        List<Announcement> actual = new ArrayList<>();

        List<Agency> agencies = agencyRepository.getAgenciesList();

        for (Agency agency1 : agencies) {
        List<Announcement> announcements = agency1.getAnnouncements().getList();
        for (Announcement announce : announcements) {
        if (announce.getRequest().getProperty().getPropertyType().getDesignation().equalsIgnoreCase("Land")) {
        actual.add(announce);
        }
        }
        }

        List<AnnouncementDto> actualDto = new ArrayList<>();

        for (Announcement announcement : actual){
        actualDto.add(new AnnouncementDto(announcement.getId(), announcement.getRequestAttributes(), announcement.getCommissionAttributes(), announcement.getAcceptanceDate().toString(),
        OrderMapper.toDto(announcement.getListOfOrders())));
        }

        assertEquals(expected, actualDto);
        }
 ```

# 5. Construction (Implementation)


## Class ScheduleVisitController 

```java
public Boolean scheduleVisit(Announcement announcement, Integer startHour, Integer endHour,
        Integer visitDay, Integer visitMonth, Integer visitYear) {
        Optional<Person> user = getUserPerson();
        String userName = "";
        String userPhoneNumber = "";

        if (user.isPresent()) {
            userName = user.get().getName();
            userPhoneNumber = user.get().getPhoneNumber();
        }

        Optional<Visit> newVisit = announcement.createVisit(visitDay, visitMonth, visitYear, startHour, endHour, userName, userPhoneNumber);

        if (newVisit.isPresent()) {
            String agentEmail = announcement.getAgentEmail();
            newVisit.get().sendNotification(agentEmail);
        }
        return newVisit.isPresent();
    }
```


## Class Announcement

```java
public Optional<Visit> createVisit(Integer visitDay, Integer visitMonth, Integer visitYear, Integer startHour, Integer endHour, String userName, String userPhoneNumber) {
        Optional<Visit> optionalValue = Optional.empty();

        Visit visit = new Visit(visitDay, visitMonth, visitYear, startHour, endHour, userName, userPhoneNumber);

        if (addVisit(visit)) {
            optionalValue = Optional.of(visit);
        }
        return optionalValue;
}
```

# 6. Integration and Demo 

* A new option on the Client menu options was added.

* Some demo purposes some announcements are bootstrapped while system starts.


# 7. Observations

To solve the problem referred in sprint B, the team decided to promote Collections to classes.
This led to a better distribution of responsibilities.
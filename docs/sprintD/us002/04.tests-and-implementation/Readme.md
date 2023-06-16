# US 002 - Publish an announcement

# 4. Tests

### PublishAnnouncementController Tests

**Test 1:** Checks if Publish Announcement works.

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

**Test 2:** Checks if Publish Announcement fails for non-existent agency.

```java
  @Test
    void ensurePublishAnnouncementForNonExistingAgencyFails() {
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
        authenticationRepository.addUserWithRole("John", "2agent2@this.app", "02AGEnt",
        AuthenticationController.ROLE_AGENT);

        Agency agency = new Agency(1234);
        Employee employee = new Employee(1234, "Elsa", "C12345678", "123-23-2345", "1agent1@this.app", "agent", "423-423-2345", "city", "district", "AK", "12345", "street");
        agency.addEmployee(employee);
        agencyRepository.add(agency);

        authenticationRepository.doLogin("2agent2@this.app", "02AGEnt");

        String ownerEmail = "owner@email.com";
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        AnnouncementDto a = new AnnouncementDto(ownerEmail, propertyType.getDesignation(), 4534, 453452, "St.Nicholas", "Pole North", "Madagascar", "NY", "14524", uriList, 35434,c1.getDesignation());

        PublishAnnouncementController ctrl = new PublishAnnouncementController(commissionTypeRepository, authenticationRepository, propertyTypeRepository, agencyRepository);

        Boolean result = ctrl.publishAnnouncement(a);
        assertFalse(result);
        }
```

**Test 3:** Checks if Get Property Type list works.

```java
    @Test
    void ensureGetPropertyTypeListWorks(){

            Repositories repositories=Repositories.getInstance();
            PropertyTypeRepository propertyTypeRepository=new PropertyTypeRepository();
            AgencyRepository agencyRepository=new AgencyRepository();
            AuthenticationRepository authenticationRepository=new AuthenticationRepository();

            PropertyType propertyType1=new PropertyType("Property Type Description One");
            propertyTypeRepository.add(propertyType1);

            PropertyType propertyType2=new PropertyType("Property Type Description Two");
            propertyTypeRepository.add(propertyType2);

            ArrayList<PropertyType> expected=new ArrayList<PropertyType>();
        expected.add(propertyType1);
        expected.add(propertyType2);

        Agency agency=new Agency(1234);
        Employee employee=new Employee("agent1@this.app","agent");
        agency.addEmployee(employee);
        agencyRepository.add(agency);


        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT,AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Agent","agent1@this.app","01AGEnt",
        AuthenticationController.ROLE_AGENT);

        authenticationRepository.doLogin("agent1@this.app","01AGEnt");

        PublishAnnouncementController controller=
        new PublishAnnouncementController(agencyRepository,propertyTypeRepository,authenticationRepository);


        List<PropertyType> propertyTypeList=controller.getPropertyTypeList();

        assertArrayEquals(expected.toArray(),propertyTypeList.toArray());
        }
```

**Test 4:** Checks if Get Commission Type list works.

```java
    @Test
    void ensureGetCommissionTypeListWorks(){
            Repositories repositories=Repositories.getInstance();
            CommissionTypeRepository commissionTypeRepository=new CommissionTypeRepository();
            AgencyRepository agencyRepository=new AgencyRepository();
            AuthenticationRepository authenticationRepository=new AuthenticationRepository();

            CommissionType commissionType1=new CommissionType("Commission Type Description One");
            commissionTypeRepository.add(commissionType1);

            CommissionType commissionType2=new CommissionType("Commission Type Description Two");
            commissionTypeRepository.add(commissionType2);

            ArrayList<CommissionType> expected=new ArrayList<CommissionType>();
        expected.add(commissionType1);
        expected.add(commissionType2);

        Agency agency=new Agency(1234);
        Employee employee=new Employee("agent1@this.app","agent");
        agency.addEmployee(employee);
        agencyRepository.add(agency);

        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT,AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("agent","agent1@this.app","01AGEnt",
        AuthenticationController.ROLE_AGENT);

        authenticationRepository.doLogin("agent1@this.app","01AGEnt");

        PublishAnnouncementController controller=
        new PublishAnnouncementController(agencyRepository,commissionTypeRepository,authenticationRepository);


        List<CommissionType> commissionTypeList=controller.getCommissionTypeList();

        assertArrayEquals(expected.toArray(),commissionTypeList.toArray());
        }
```

### Announcement Tests

**Test 1:** Checks if an Announcement is successfully created.

```java
    @Test
    void ensureAnnouncementIsCreatedSuccessfully(){
            String ownerEmail="owner@email.com";
            Employee employee=new Employee("employee@this.app.com","Agent");
            CommissionType commissionType=new CommissionType("Commission Type");
            List<String> uriList=new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");


        Property property=new Property(new PropertyType("land"),(35.5),(89.3),
        uriList,"street","city","district","state","12345");
        Request request=new Request(ownerEmail,property,new Business("sale",2345.0),LocalDate.now(),employee);

        Announcement announcement=new Announcement(employee,commissionType,234.0,request);
        }
```

**Test 2:** Checks if Get Acceptance Date works.

```java
    @Test
    void getAcceptanceDate(){
            String ownerEmail="owner@email.com";
            Employee employee=new Employee("employee@this.app.com","Agent");
            CommissionType commissionType=new CommissionType("Commission Type");
            Commission commission=new Commission(commissionType,234.0);
            LocalDate acceptanceDate=LocalDate.now();

            List<String> uriList=new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property=new Property(new PropertyType("land"),(35.5),(89.3),
        uriList,"street","city","district","state","12345");
        Request request=new Request(ownerEmail,property,new Business("sale",2345.0),LocalDate.now(),employee);

        Announcement announcement=new Announcement(employee,commission,request,acceptanceDate);
        assertEquals(acceptanceDate,announcement.getAcceptanceDate());
        }
```

### Agency Tests

**Test 1:** Checks if Publish Announcement Works

```java
    @Test
    void ensurePublishAnnouncementWorks(){
            Location location=new Location("street","city","district","state","12345");
            Agency agency=new Agency(1234,"Description","agency@email.com","345 567 3456",location);
            String ownerEmail="owner@email.com";
            Employee employee=new Employee("employee@this.app.com","Agent");
            CommissionType commissionType=new CommissionType("Commission Type");
            List<String> uriList=new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");


        Property property=new Property(new PropertyType("land"),(35.5),(89.3),
        uriList,"street","city","district","state","12345");
        Request request=new Request(ownerEmail,property,new Business("sale",2345.0),LocalDate.now(),employee);

        Announcement expected=new Announcement(employee,commissionType,234.0,request);

        Optional<Announcement> announcement=agency.publishAnnouncement(employee,commissionType,234.0,request);

        assertNotNull(announcement);
        assertTrue(announcement.isPresent());
        assertEquals(announcement.get(),expected);
        }
```

**Test 2:** Checks if Any Agent Has Email works.

```java
    @Test
    void ensureAnyAgentHasEmailWorks(){
            Employee employee=new Employee("employee@thisapp.com","agent");

            Location location=new Location("street","city","district","state","12345");
            Agency agency1=new Agency(1234,"Description","agency@email.com","345 567 3456",location);
            agency1.addEmployee(employee);


            assertTrue(agency1.anyAgentHasEmail("employee@thisapp.com"));
            }
```

**Test 3:** Checks if Create Sale Request works.

```java
    @Test
    void ensureCreateSaleRequestWorks(){
            Employee employee=new Employee("employee@this.app.com","Agent");
            Location location=new Location("street","city","district","state","12345");
            Agency agency=new Agency(1234,"Description","agency@email.com","345 567 3456",location);
            agency.addEmployee(employee);

            String ownerEmail="owner@email.com";
            List<String> uriList=new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        List<String> av=new ArrayList<>();
        Property property=new Property(new PropertyType("land"),35.5,89.3,uriList,"street",
        "city","district","state","12345");
        Request expected=new Request(ownerEmail,property,new Business("sale",2345.0),LocalDate.now(),employee);


        Optional<Request> request=agency.createSaleRequest(ownerEmail,new PropertyType("land"),"sale",2345.0,
        35.5,av,"street","city","district","state","12345",false,false,0,null,
        0,0,employee,89.3,uriList);

        assertNotNull(request);
        assertTrue(request.isPresent());
        assertEquals(request.get(),expected);
        }
```

**Test 4:** Checks if Get Agent By Email works.

```java
    @Test
    void ensureGetAgentByEmailWorks() {
        Employee employee1 = new Employee("employee@thisapp.com", "agent");
        Employee employee2 = new Employee("employee1@thisapp.com", "agent");
        Employee employee3 = new Employee("employee2@thisapp.com", "agent");

        Location location =new Location("street","city","district","state","12345");
        Agency agency1 = new Agency(1234,"Description","agency@email.com","345 567 3456",location);
        agency1.addEmployee(employee1);
        agency1.addEmployee(employee2);
        agency1.addEmployee(employee3);

        assertEquals(agency1.getAgentByEmail("employee@thisapp.com"), employee1);
    }
```

### Agency Repository

**Test 1:** Checks if Get Agency By Email works.

```java
 @Test
    void ensureGetAgencyByEmailWorks() {
        AgencyRepository agencyRepository = new AgencyRepository();
        Location location =new Location("street","city","district","state","12345");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 567 3456",location);
        String email = "employee@this.app.com";
        List<String> roles = new ArrayList<>();
        roles.add("agent");
        Employee employee = new Employee(2425,"Employee","C12345678","123-23-1234",new Email(email),roles,"345 345 3456",location);

        agency.addEmployee(employee);
        agencyRepository.add(agency);

        Optional<Agency> returnAgency = agencyRepository.getAgencyByEmployeeEmail("employee@this.app.com");

        assertEquals(agency, returnAgency.get());
    }
```

### Commission Type

**Test 1:** Checks if Get Designation works.

```java
 @Test
    void ensureGetDesignationWorks() {
        CommissionType commissionType1 = new CommissionType("Commission Type");
        assertEquals("Commission Type", commissionType1.getDesignation());
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


# 5. Construction (Implementation)

## Class PublishAnnouncementController

```java
        public Optional<Announcement> publishAnnouncement(Double commissionValue,String commissionTypeDesignation,String ownerEmail,String propertyTypeDesignation,
        String streetName,String city,String district,String state,String zipCode,Double area,Double distanceCityCenter,Double price,Integer numberBedroom,
        Integer numberParkingSpace,Boolean existenceBasement,Boolean inhabitableLoft,Integer numberBathroom,List<String> availableEquipmentDescriptionList,
        List<String> uriList,SunExposureTypes sunExposure){

        String email=getEmailFromSession();
        Optional<Agency> agency=getAgencyByEmail(email);
        Employee agent=getAgentByEmail(email,agency);

        PropertyType propertyType=getPropertyTypeByDesignation(propertyTypeDesignation);

        Optional<Request> newRequest;
        Optional<Announcement> newAnnouncement=Optional.empty();

        CommissionType commissionType=getCommissionTypeByDesignation(commissionTypeDesignation);

        if(agency.isPresent()){
        newRequest=agency.get().createSaleRequest(ownerEmail,propertyType,"sale",price,area,availableEquipmentDescriptionList,streetName,city,district,state,
        zipCode,existenceBasement,inhabitableLoft,numberParkingSpace,sunExposure,numberBedroom,numberBathroom,agent,distanceCityCenter,uriList);
        if(newRequest.isPresent()){
        newAnnouncement=agency.get().publishAnnouncement(agent,commissionType,commissionValue,newRequest.get());
        }
        }
        return newAnnouncement;

        }
```

## Class Agency

```java
 public Optional<Announcement> publishAnnouncement(Employee agent,CommissionType commissionType,Double commissionValue,Request request){

        Optional<Announcement> optionalValue=Optional.empty();

        Announcement announcement=new Announcement(agent,commissionType,commissionValue,request);

        if(addAnnouncement(announcement)){
        optionalValue=Optional.of(announcement);
        }
        return optionalValue;
        }
```

# 6. Integration and Demo



# 7. Observations

Agency class is getting too many responsibilities due to IE pattern and, therefore, is are becoming huge and hard to
maintain.







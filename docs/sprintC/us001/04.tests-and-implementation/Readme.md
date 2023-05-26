# US 001 - To display listed properties

# 4. Tests 

### DisplayPropertiesController Tests

**Test 1:** Check if Get Agencies list works. 

```java
	@Test
    void ensureGetAgenciesListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();

        Agency agency = new Agency(1705);
        Agency agency1 = new Agency(2345);

        agencyRepository.add(agency);
        agencyRepository.add(agency1);


        DisplayPropertiesController controller = new DisplayPropertiesController(agencyRepository, propertyTypeRepository, businessTypeRepository, criteriaRepository);

        List<Agency> agencies = controller.getAgenciesList();

    }
```
	

**Test 2:** Check if Get Announcements list works. 

```java
	@Test
    void ensureGetAnnouncementsListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        DisplayPropertiesController controller = new DisplayPropertiesController(agencyRepository, propertyTypeRepository, businessTypeRepository, criteriaRepository);

        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","cit1y","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (65.5), (70.5),
                uriList, "street 1", "city 1", "district 1", "st1", "67891");
        Request request1 = new Request(ownerEmail1, property1, new Business("sale", 5678.0), LocalDate.now(), employee1);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency1.publishAnnouncement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement);
        expected.add(announcement1);


        List<Announcement> resultAnnouncements = new ArrayList<>();
        resultAnnouncements = controller.getAnnouncementsList();

    }
```

**Test 3:** Check if sort announcements by most recent added works.

```java
        @Test
        void ensureSortAnnouncementsByMostRecentAdded() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        DisplayPropertiesController controller = new DisplayPropertiesController(agencyRepository, propertyTypeRepository, businessTypeRepository, criteriaRepository);

        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","cit1y","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);
        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.of(2023,5,15), employee);

        Property property1 = new Property(new PropertyType("land"), (65.5), (70.5),
                uriList, "street 1", "city 1", "district 1", "st1", "67891");
        Request request1 = new Request(ownerEmail1, property1, new Business("lease", 300.0), LocalDate.now(), employee1);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement);
        expected.add(announcement1);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency1.publishAnnouncement(employee, commissionType, 234.0, request1);

        List<Announcement> resultAnnouncements = new ArrayList<>();

        resultAnnouncements = controller.sortAnnouncementsByMostRecentAdded();
    }
```

**Test 4:** Check if Get Announcements by business type works.

```java
        @Test
        void ensureGetAnnouncementsByBusinessTypeWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        DisplayPropertiesController controller = new DisplayPropertiesController(agencyRepository, propertyTypeRepository, businessTypeRepository, criteriaRepository);

        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","cit1y","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2345.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (65.5), (70.5),
                uriList, "street 1", "city 1", "district 1", "st1", "67891");
        Request request1 = new Request(ownerEmail1, property1, new Business("lease", 300.0), LocalDate.now(), employee1);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency1.publishAnnouncement(employee, commissionType, 234.0, request1);

        List<Announcement> resultAnnouncements = new ArrayList<>();
        resultAnnouncements = controller.getAnnouncementsByBusinessType("Sale");


    }
```

**Test 5:** Check if Get Announcements by property type works.

```java
        @Test
        void ensureGetAnnouncementsByPropertyType() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        DisplayPropertiesController controller = new DisplayPropertiesController(agencyRepository, propertyTypeRepository, businessTypeRepository, criteriaRepository);

        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","cit1y","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
                uriList, "street", "city", "district", "state", "12345");
        Request request = new Request(ownerEmail, property, new Business("lease", 2345.0), LocalDate.now(), employee);

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");

        Property property1 = new House(new PropertyType("house"), av, 32.4,
                "street1", "city1", "district1", "st1", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request1 = new Request(ownerEmail1, property1, new Business("lease", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement1);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency1.publishAnnouncement(employee, commissionType, 234.0, request1);

        List<Announcement> resultAnnouncements = new ArrayList<>();
        resultAnnouncements = controller.getAnnouncementsByBusinessType("House");
    }
```


**Test 6:** Check if Get Announcements by number of bedrooms works.

```java
        @Test
        void ensureGetAnnouncementsByNumberBedrooms() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        DisplayPropertiesController controller = new DisplayPropertiesController(agencyRepository, propertyTypeRepository, businessTypeRepository, criteriaRepository);

        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","cit1y","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");

        Property property = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request = new Request(ownerEmail1, property, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property1 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city 4", "district 4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request1 = new Request(ownerEmail, property1, new Business("lease", 2345.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement1);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency.publishAnnouncement(employee, commissionType, 234.0, request1);

        List<Announcement> resultAnnouncements = new ArrayList<>();
        resultAnnouncements = controller.getAnnouncementsByNumberBedrooms(agency.getAnnouncementsList(), 1);
    }
```

**Test 7:** Check if Get Announcements by price works.

```java
        @Test
        void ensureGetAnnouncementsByPrice() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        DisplayPropertiesController controller = new DisplayPropertiesController(agencyRepository, propertyTypeRepository, businessTypeRepository, criteriaRepository);

        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","city1","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");

        Property property = new House(new PropertyType("house"), av, 32.4,
                "street 3", "city 3", "district 3", "st3", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request = new Request(ownerEmail1, property, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property1 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city 4", "district 4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request1 = new Request(ownerEmail, property1, new Business("lease", 5000.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement1);
        expected.add(announcement);

        agency.publishAnnouncement(employee, commissionType, 234.0, request);
        agency.publishAnnouncement(employee, commissionType, 234.0, request1);

        List<Announcement> resultAnnouncements = new ArrayList<>();
        resultAnnouncements = controller.getAnnouncementsByPrice(agency.getAnnouncementsList(), "Descending");
    }
```

**Test 8:** Check if Get Announcements by city works.

```java
        @Test
    void ensureGetAnnouncementsByCity() {
            PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
            AgencyRepository agencyRepository = new AgencyRepository();
            BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
            CriteriaRepository criteriaRepository = new CriteriaRepository();
            DisplayPropertiesController controller = new DisplayPropertiesController(agencyRepository, propertyTypeRepository, businessTypeRepository, criteriaRepository);

            Location location =new Location("street","city","district","state","12345");
            Location location1 =new Location("street1","city1","district1","state1","67891");
            Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
            Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
            List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");

        Property property = new House(new PropertyType("house"), av, 32.4,
        "street1", "city1", "district1", "st3", "12340", true, false, 2,
        SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request = new Request(ownerEmail1, property, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property1 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
        "city4", "district4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request1 = new Request(ownerEmail, property1, new Business("lease", 5000.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement1);
        expected.add(announcement);

        agency.publishAnnouncement(employee, commissionType, 234.0, request1);
        agency.publishAnnouncement(employee, commissionType, 234.0, request);

        List<Announcement> resultAnnouncements = new ArrayList<>();
        resultAnnouncements = controller.getAnnouncementsByCity(agency.getAnnouncementsList(), "Descending");
        }
```


**Test 9:** Check if Get Announcements by state works.

```java
@Test
    void ensureGetAnnouncementsByState() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        DisplayPropertiesController controller = new DisplayPropertiesController(agencyRepository, propertyTypeRepository, businessTypeRepository, criteriaRepository);

        Location location =new Location("street","city","district","state","12345");
        Location location1 =new Location("street1","city1","district1","state1","67891");
        Agency agency = new Agency(1234,"Description","agency@email.com","345 123 7219",location);
        Agency agency1 = new Agency(5678,"Description","agency1@email.com","789 123 7219",location1);
        List<Agency> agencies = new ArrayList<>();
        agencies.add(agency);
        agencies.add(agency1);

        String ownerEmail = "owner@email.com";
        String ownerEmail1 = "owner1@email.com";
        Employee employee = new Employee("employee@this.app.com", "Agent");
        Employee employee1 = new Employee("employee1@this.app.com", "Agent");
        CommissionType commissionType = new CommissionType("Commission Type");
        List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");

        Property property = new House(new PropertyType("house"), av, 32.4,
                "street1", "city1", "district1", "st5", "12340", true, false, 2,
                SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request = new Request(ownerEmail1, property, new Business("lease", 2345.0), LocalDate.now(), employee);

        Property property1 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
                "city4", "district4", "st4", "12348", 2, 1, 3, 15.2, uriList);
        Request request1 = new Request(ownerEmail, property1, new Business("lease", 5000.0), LocalDate.now(), employee);

        Announcement announcement = new Announcement(employee, commissionType, 234.0, request);
        Announcement announcement1 = new Announcement(employee, commissionType, 234.0, request1);

        List<Announcement> expected = new ArrayList<>();
        expected.add(announcement1);
        expected.add(announcement);

        agency.publishAnnouncement(employee, commissionType, 234.0, request1);
        agency.publishAnnouncement(employee, commissionType, 234.0, request);

        List<Announcement> resultAnnouncements = new ArrayList<>();
        resultAnnouncements = controller.getAnnouncementsByState(agency.getAnnouncementsList(), "Ascending");
    }
```
**Test 10:** Check if Get Business Type list works.

```java
@Test
    void ensureGetBusinessTypeListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        DisplayPropertiesController controller = new DisplayPropertiesController(agencyRepository, propertyTypeRepository, businessTypeRepository, criteriaRepository);

        BusinessType businessType = new BusinessType("Sale");
        businessTypeRepository.add(businessType);

        BusinessType businessType1 = new BusinessType("Lease");
        businessTypeRepository.add(businessType1);

        ArrayList<BusinessType> expected = new ArrayList<>();
        expected.add(businessType);
        expected.add(businessType1);

        List<BusinessType> businessTypeList = controller.getBusinessTypeList();

    }

```


**Test 11:** Check if Get Property Type list works.

```java
@Test
    void ensureGetPropertyTypeListWorks() {
        PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
        AgencyRepository agencyRepository = new AgencyRepository();
        BusinessTypeRepository businessTypeRepository = new BusinessTypeRepository();
        CriteriaRepository criteriaRepository = new CriteriaRepository();
        DisplayPropertiesController controller = new DisplayPropertiesController(agencyRepository, propertyTypeRepository, businessTypeRepository, criteriaRepository);

        PropertyType propertyType = new PropertyType("Land");
        propertyTypeRepository.add(propertyType);

        PropertyType propertyType1 = new PropertyType("Apartment");
        propertyTypeRepository.add(propertyType1);

        ArrayList<PropertyType> expected = new ArrayList<>();
        expected.add(propertyType);
        expected.add(propertyType1);

        List<PropertyType> propertyTypeList = controller.getPropertyTypeList();

    }
```


*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)


## Class DisplayPropertiesController 

```java
public DisplayPropertiesController(AgencyRepository agencyRepository,
        PropertyTypeRepository propertyTypeRepository,
        BusinessTypeRepository businessTypeRepository,
        CriteriaRepository criteriaRepository) {
        this.agencyRepository = agencyRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.businessTypeRepository = businessTypeRepository;
        this.criteriaRepository = criteriaRepository;
        this.announcementList = getAnnouncementsList();
        }
```


# 6. Integration and Demo 

* A new option on the main menu options was added.


# 7. Observations

Agency class is getting too many responsibilities due to IE pattern and, therefore, is are becoming huge and hard to
maintain.






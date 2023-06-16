# US 017 - To list all deals in the Network

# 4. Tests 

**Test 1:** Check that getting agency repository works. 

```java
    @Test
    void getAgencyRepository() {
        AgencyRepository agencyRepository = Repositories.getInstance().getAgencyRepository();
        ListDealsNetworkController controller = new ListDealsNetworkController();
        assertEquals(agencyRepository, controller.getAgencyRepository());
    }
```

**Test 2:** Check if getting all deals within the network works. 

```java
    @Test
    void getAllDealsList() {
            ListDealsNetworkController controller = new ListDealsNetworkController();

            AgencyRepository agencyRepository = controller.getAgencyRepository();
            Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
            Agency agency = new Agency(31, "Make It Home Deluxe", "agency4@this.app", "999 444 5656", location);
            agencyRepository.add(agency);
            String ownerEmail = "owner@email.com";
            String ownerEmail1 = "owner1@email.com";
            String ownerEmail2 = "owner2@email.com";
            Employee employee = new Employee("employee@this.app", "Agent");
            List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
        uriList, "street", "city", "district", "MO", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (100.0), (70.3),
        uriList, "street 1", "city 1", "district 1", "NV", "12346");
        Request request1 = new Request(ownerEmail1, property1, new Lease(6, "lease", 300.0), LocalDate.now(), employee);

        Property property2 = new Property(new PropertyType("land"), (30.5), (456.3),
        uriList, "street 2", "city 2", "district 2", "NY", "12347");
        Request request2 = new Request(ownerEmail1, property2, new Business("sale", 2385.0), LocalDate.now(), employee);

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property3 = new House(new PropertyType("house"), av, 32.4,
        "street 3", "city 3", "district 3", "OH", "12340", true, false, 2,
        SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request3 = new Request(ownerEmail2, property3, new Lease(3, "sale", 1234.0), LocalDate.now(), employee);


        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
        "city 4", "district 4", "PA", "12348", 2, 25, 3, 15.2, uriList);
        Request request4 = new Request(ownerEmail2, property4, new Lease(15, "sale", 2345.0), LocalDate.now(), employee);

        Property property5 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
        "city 4", "district 4", "SD", "12348", 2, 25, 3, 15.2, uriList);
        Request request5 = new Request(ownerEmail2, property5, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement a7 = new Announcement(employee, 35, request, "23-12-2022", 3245678d);
        Announcement a8 = new Announcement(employee, 3495, request1, "23-12-2022", 336678d);
        Announcement a9 = new Announcement(employee, 345, request2, "23-12-2022", 32458d);
        Announcement a10 = new Announcement(employee, 3435, request3, "23-12-2022", 123245678d);
        Announcement a11 = new Announcement(employee, 3, request4, "23-12-2022", 754245678d);
        Announcement a12 = new Announcement(employee, 25, request5, "23-12-2022", 45678d);
        agency.addAnnouncement(a7);
        agency.addAnnouncement(a8);
        agency.addAnnouncement(a9);
        agency.addAnnouncement(a10);
        agency.addAnnouncement(a11);
        agency.addAnnouncement(a12);

        AnnouncementList expected = new AnnouncementList(new ArrayList<>(Arrays.asList(a7, a8, a9, a10, a11, a12)));

        assertEquals(expected.sortAnnouncementsByMostRecentSaleDate(), controller.getAllDealsList());
        }
```

**Test 3:** Check if convertion of Announcement Deals to DTO is correct.

```java
     @Test
    void toDto() {
            ListDealsNetworkController controller = new ListDealsNetworkController();

            AgencyRepository agencyRepository = controller.getAgencyRepository();
            Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
            Agency agency = new Agency(31, "Make It Home Deluxe", "agency4@this.app", "999 444 5656", location);
            agencyRepository.add(agency);
            String ownerEmail = "owner@email.com";
            String ownerEmail1 = "owner1@email.com";
            String ownerEmail2 = "owner2@email.com";
            Employee employee = new Employee("employee@this.app", "Agent");
            List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
        uriList, "street", "city", "district", "MO", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (100.0), (70.3),
        uriList, "street 1", "city 1", "district 1", "NV", "12346");
        Request request1 = new Request(ownerEmail1, property1, new Lease(6, "lease", 300.0), LocalDate.now(), employee);

        Property property2 = new Property(new PropertyType("land"), (30.5), (456.3),
        uriList, "street 2", "city 2", "district 2", "NY", "12347");
        Request request2 = new Request(ownerEmail1, property2, new Business("sale", 2385.0), LocalDate.now(), employee);

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property3 = new House(new PropertyType("house"), av, 32.4,
        "street 3", "city 3", "district 3", "OH", "12340", true, false, 2,
        SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request3 = new Request(ownerEmail2, property3, new Lease(3, "sale", 1234.0), LocalDate.now(), employee);


        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
        "city 4", "district 4", "PA", "12348", 2, 25, 3, 15.2, uriList);
        Request request4 = new Request(ownerEmail2, property4, new Lease(15, "sale", 2345.0), LocalDate.now(), employee);

        Property property5 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
        "city 4", "district 4", "SD", "12348", 2, 25, 3, 15.2, uriList);
        Request request5 = new Request(ownerEmail2, property5, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement a7 = new Announcement(employee, 35, request, "23-12-2022", 3245678d);
        Announcement a8 = new Announcement(employee, 3495, request1, "23-12-2022", 336678d);
        Announcement a9 = new Announcement(employee, 345, request2, "23-12-2022", 32458d);
        Announcement a10 = new Announcement(employee, 3435, request3, "23-12-2022", 123245678d);
        Announcement a11 = new Announcement(employee, 3, request4, "23-12-2022", 754245678d);
        Announcement a12 = new Announcement(employee, 25, request5, "23-12-2022", 45678d);
        agency.addAnnouncement(a7);
        agency.addAnnouncement(a8);
        agency.addAnnouncement(a9);
        agency.addAnnouncement(a10);
        agency.addAnnouncement(a11);
        agency.addAnnouncement(a12);

        List<AnnouncementDto> networkDeals = new ArrayList<>();
        networkDeals.add(AnnouncementMapper.toNetworkDto(a7, agencyRepository.getAgenciesList()));
        networkDeals.add(AnnouncementMapper.toNetworkDto(a8, agencyRepository.getAgenciesList()));
        networkDeals.add(AnnouncementMapper.toNetworkDto(a9, agencyRepository.getAgenciesList()));
        networkDeals.add(AnnouncementMapper.toNetworkDto(a10, agencyRepository.getAgenciesList()));
        networkDeals.add(AnnouncementMapper.toNetworkDto(a11, agencyRepository.getAgenciesList()));
        networkDeals.add(AnnouncementMapper.toNetworkDto(a12, agencyRepository.getAgenciesList()));

        List<String> expected = new ArrayList<>();

        for (AnnouncementDto dto : networkDeals) {
        expected.add(dto.toDealString());
        }

        List<AnnouncementDto> networkDealsController = AnnouncementMapper.toNetworkDto(agency.getDealsAnnouncementList(), agencyRepository.getAgenciesList()).get();

        List<String> actual = new ArrayList<>();

        for (AnnouncementDto dto : networkDealsController) {
        actual.add(dto.toDealString());
        }

        assertEquals(expected, actual);
        }
```

**Test 4:** Check if list of deals is correctly sorted according to the chosen algorithm and sorting order.

```java
 @Test
    void getListSortedByAlgorithm() {
            ListDealsNetworkController controller = new ListDealsNetworkController();

            AgencyRepository agencyRepository = controller.getAgencyRepository();
            Location location = new Location("Saint Avenue", "Heaven", "Sky", "SK", "12345");
            Agency agency = new Agency(31, "Make It Home Deluxe", "agency4@this.app", "999 444 5656", location);
            agencyRepository.add(agency);
            String ownerEmail = "owner@email.com";
            String ownerEmail1 = "owner1@email.com";
            String ownerEmail2 = "owner2@email.com";
            Employee employee = new Employee("employee@this.app", "Agent");
            List<String> uriList = new ArrayList<>();
        uriList.add("https://www.example.com/images/photo.jpg");
        uriList.add("https://www.example.com/images/photo123.jpg");

        Property property = new Property(new PropertyType("land"), (35.5), (89.3),
        uriList, "street", "city", "district", "MO", "12345");
        Request request = new Request(ownerEmail, property, new Business("sale", 2500.0), LocalDate.now(), employee);

        Property property1 = new Property(new PropertyType("land"), (100.0), (70.3),
        uriList, "street 1", "city 1", "district 1", "NV", "12346");
        Request request1 = new Request(ownerEmail1, property1, new Lease(6, "lease", 300.0), LocalDate.now(), employee);

        Property property2 = new Property(new PropertyType("land"), (30.5), (456.3),
        uriList, "street 2", "city 2", "district 2", "NY", "12347");
        Request request2 = new Request(ownerEmail1, property2, new Business("sale", 2385.0), LocalDate.now(), employee);

        List<String> av = new ArrayList<>();
        av.add("AC");
        av.add("Coffee Machine");
        av.add("Heating Floor");
        av.add("Massage Chair");
        av.add("Jacuzzi");
        av.add("Automatic Lights System");

        Property property3 = new House(new PropertyType("house"), av, 32.4,
        "street 3", "city 3", "district 3", "OH", "12340", true, false, 2,
        SunExposureTypes.NORTH, 2, null, 12.5, uriList);
        Request request3 = new Request(ownerEmail2, property3, new Lease(3, "sale", 1234.0), LocalDate.now(), employee);


        Property property4 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
        "city 4", "district 4", "PA", "12348", 2, 25, 3, 15.2, uriList);
        Request request4 = new Request(ownerEmail2, property4, new Lease(15, "sale", 2345.0), LocalDate.now(), employee);

        Property property5 = new Residence(new PropertyType("apartment"), 125.4, av, "street 4",
        "city 4", "district 4", "SD", "12348", 2, 25, 3, 15.2, uriList);
        Request request5 = new Request(ownerEmail2, property5, new Business("sale", 2345.0), LocalDate.now(), employee);

        Announcement a7 = new Announcement(employee, 35, request, "23-12-2022", 3245678d);
        Announcement a8 = new Announcement(employee, 3495, request1, "23-12-2022", 336678d);
        Announcement a9 = new Announcement(employee, 345, request2, "23-12-2022", 32458d);
        Announcement a10 = new Announcement(employee, 3435, request3, "23-12-2022", 123245678d);
        Announcement a11 = new Announcement(employee, 3, request4, "23-12-2022", 754245678d);
        Announcement a12 = new Announcement(employee, 25, request5, "23-12-2022", 45678d);
        agency.addAnnouncement(a7);
        agency.addAnnouncement(a8);
        agency.addAnnouncement(a9);
        agency.addAnnouncement(a10);
        agency.addAnnouncement(a11);
        agency.addAnnouncement(a12);

        List<AnnouncementDto> networkDealsAscending = new ArrayList<>();
        networkDealsAscending.add(AnnouncementMapper.toNetworkDto(a9, agencyRepository.getAgenciesList()));
        networkDealsAscending.add(AnnouncementMapper.toNetworkDto(a10, agencyRepository.getAgenciesList()));
        networkDealsAscending.add(AnnouncementMapper.toNetworkDto(a7, agencyRepository.getAgenciesList()));
        networkDealsAscending.add(AnnouncementMapper.toNetworkDto(a8, agencyRepository.getAgenciesList()));
        networkDealsAscending.add(AnnouncementMapper.toNetworkDto(a12, agencyRepository.getAgenciesList()));
        networkDealsAscending.add(AnnouncementMapper.toNetworkDto(a11, agencyRepository.getAgenciesList()));

        List<String> expectedAscending = new ArrayList<>();

        for (AnnouncementDto dto : networkDealsAscending) {
        expectedAscending.add(dto.toDealString());
        }

        MergeAlgorithm mergeAlgorithm = new MergeAlgorithm(new ArrayList<>());

        List<AnnouncementDto> networkDealsControllerMergeAscending = AnnouncementMapper.toNetworkDto(mergeAlgorithm.sort("Ascending",
        agencyRepository.getAllDealsAnnouncements()), agencyRepository.getAgenciesList()).get();

        List<String> actualMergeAscending = new ArrayList<>();

        for (AnnouncementDto dto : networkDealsControllerMergeAscending) {
        actualMergeAscending.add(dto.toDealString());
        }


        List<AnnouncementDto> networkDealsDescening = new ArrayList<>();
        networkDealsDescening.add(AnnouncementMapper.toNetworkDto(a12, agencyRepository.getAgenciesList()));
        networkDealsDescening.add(AnnouncementMapper.toNetworkDto(a11, agencyRepository.getAgenciesList()));
        networkDealsDescening.add(AnnouncementMapper.toNetworkDto(a8, agencyRepository.getAgenciesList()));
        networkDealsDescening.add(AnnouncementMapper.toNetworkDto(a7, agencyRepository.getAgenciesList()));
        networkDealsDescening.add(AnnouncementMapper.toNetworkDto(a10, agencyRepository.getAgenciesList()));
        networkDealsDescening.add(AnnouncementMapper.toNetworkDto(a9, agencyRepository.getAgenciesList()));

        List<String> expectedDescening = new ArrayList<>();

        for (AnnouncementDto dto : networkDealsDescening) {
        expectedDescening.add(dto.toDealString());
        }

        MergeAlgorithm mergeAlgorithm2 = new MergeAlgorithm(new ArrayList<>());

        List<AnnouncementDto> networkDealsControllerMergeDescending = AnnouncementMapper.toNetworkDto(mergeAlgorithm2.sort("Descending",
        agencyRepository.getAllDealsAnnouncements()), agencyRepository.getAgenciesList()).get();

        List<String> actualMergeDescending = new ArrayList<>();

        for (AnnouncementDto dto : networkDealsControllerMergeDescending) {
        actualMergeDescending.add(dto.toDealString());
        }


        BubbleAlgorithm bubbleAlgorithm = new BubbleAlgorithm(new ArrayList<>());

        List<AnnouncementDto> networkDealsControllerBubbleDescending = AnnouncementMapper.toNetworkDto(bubbleAlgorithm.sort("Descending",
        agencyRepository.getAllDealsAnnouncements()), agencyRepository.getAgenciesList()).get();

        List<String> actualBubbleDescending = new ArrayList<>();

        for (AnnouncementDto dto : networkDealsControllerBubbleDescending) {
        actualBubbleDescending.add(dto.toDealString());
        }


        BubbleAlgorithm bubbleAlgorithm2 = new BubbleAlgorithm(new ArrayList<>());

        List<AnnouncementDto> networkDealsControllerBubbleAscending = AnnouncementMapper.toNetworkDto(bubbleAlgorithm2.sort("Ascending",
        agencyRepository.getAllDealsAnnouncements()), agencyRepository.getAgenciesList()).get();

        List<String> actualBubbleAscending = new ArrayList<>();

        for (AnnouncementDto dto : networkDealsControllerBubbleAscending) {
        actualBubbleAscending.add(dto.toDealString());
        }

        assertEquals(expectedAscending, actualMergeAscending);
        assertEquals(expectedDescening, actualMergeDescending);
        assertEquals(expectedAscending, actualBubbleAscending);
        assertEquals(expectedDescening, actualBubbleDescending);

        }
```

# 5. Construction (Implementation)

## Class ListDealsNetworkController

```java
    private Optional<List<AnnouncementDto>> getSortedList(String sortingOrder, String algorithm, List<Announcement> listOfAllDeals) {
        if (algorithm.equalsIgnoreCase("Merge Sort")) {
        MergeAlgorithm sortedList = new MergeAlgorithm(listOfAllDeals);
        sortedList.sort(sortingOrder, getAllDealsList());
        return toDto(sortedList.getSortedList());
        } else {
        BubbleAlgorithm sortedList = new BubbleAlgorithm(listOfAllDeals);
        sortedList.sort(sortingOrder, getAllDealsList());
        return toDto(sortedList.getSortedList());
        }
        }
```

## Class BubbleAlgorithm

```java
    public void sortAscending(Announcement[] announce, int size) {
        for (int i = 0; i < size; i++)
        for (int j = 0; j < size - 1; j++)
        if (announce[j].getRequest().getProperty().getArea() > announce[j + 1].getRequest().getProperty().getArea()) {
        Announcement temp = announce[j];
        announce[j] = announce[j + 1];
        announce[j + 1] = temp;
        }
        }
```

## Class MergeAlgorithm

```java
    private void mergeSort(Announcement[] announce, int low, int high, String sortingOrder) {
        if (high - low + 1 > 1) {
        int mid = (low + high) / 2;
        mergeSort(announce, low, mid, sortingOrder);
        mergeSort(announce, mid + 1, high, sortingOrder);
        if (sortingOrder.equalsIgnoreCase("Ascending")) {
        mergeAcending(announce, low, mid, high);
        } else {
        mergeDescending(announce, low, mid, high);
        }
        }
        }
```

```java
  private void mergeAcending(Announcement[] announce, int low, int mid, int high) {
        int i, j, k;
        Announcement[] c = new Announcement[high - low + 1];
        k = 0;
        i = low;
        j = mid + 1;
        while (i <= mid && j <= high) {
        if (announce[i].getRequest().getProperty().getArea() <= announce[j].getRequest().getProperty().getArea()) {
        c[k++] = announce[i++];
        } else {
        c[k++] = announce[j++];
        }
        }
        while (i <= mid) {
        c[k++] = announce[i++];
        }
        while (j <= high) {
        c[k++] = announce[j++];
        }
        k = 0;
        for (i = low; i <= high; i++) {
        announce[i] = c[k++];
        }
        }
```

# 6. Integration and Demo 

* A new option on the Network Manager menu options was added.

# 7. Observations

To solve the problem referred in sprint B, the team decided to promote Collections to classes.
This led to a better distribution of responsibilities.





